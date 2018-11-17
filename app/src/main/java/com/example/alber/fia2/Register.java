package com.example.alber.fia2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private static final String TAG = "Adding new user";

    private EditText textEmail;
    private EditText textPass;
    private  EditText textName;
    private Button btnRegister;
    private Button btnLogout;
    private Intent intent;

    private FirebaseAuth firebaseAuth;
    private  FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        textEmail = findViewById(R.id.register_email);
        textPass = findViewById(R.id.register_pass);
        textName = findViewById(R.id.register_name);

        btnRegister = findViewById(R.id.guardar);
        btnLogout = findViewById(R.id.quit);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registar();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplication(),Login.class);
                startActivity(intent);
            }
        });
    }

    private void registar(){
        String email = textEmail.getText().toString().trim();
        String pass = textPass.getText().toString().trim();
        final String name = textName.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            showToast("Introduzca un correo");
            return;
        }
        if(TextUtils.isEmpty(pass)){
            showToast("Introduzca una contraseña");
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Map<String, Object> user = new HashMap<>();
                    user.put("id", task.getResult().getUser().getUid());
                    user.put("name", name);
                    user.put("email", task.getResult().getUser().getEmail());

                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {


                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });

                    showToast("Se ha registrado el usuario");
                    intent = new Intent(getApplication(),Login.class);
                    startActivity(intent);
                }
                else{
                    showToast("Ocurrió un error");
                }
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(Register.this,message,Toast.LENGTH_SHORT).show();
    }

}
