package com.example.alber.fia2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Register extends AppCompatActivity {

    private EditText textEmail;
    private EditText textPass;
    private Button btnRegister;
    private Button btnLogout;
    private Intent intent;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();

        textEmail = findViewById(R.id.register_email);
        textPass = findViewById(R.id.register_pass);

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

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"introdusca un correo", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"introdusca una contraseña",Toast.LENGTH_SHORT).show();
        }
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Register.this,"se a resgritado el usuario",Toast.LENGTH_SHORT).show();
                    intent = new Intent(getApplication(),Login.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Register.this,"se produjo un error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
