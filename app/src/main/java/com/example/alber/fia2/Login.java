package com.example.alber.fia2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextView Textemail;
    TextView Textpass;
    Button registerButton;
    Button loginButton;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener listener;
    Intent intent;

    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         editor = getSharedPreferences("SHARED PREFERENCES", MODE_PRIVATE).edit();


        Textemail = findViewById(R.id.correo);
        Textpass = findViewById(R.id.password);
        registerButton = findViewById(R.id.registro);
        mAuth = FirebaseAuth.getInstance();
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= mAuth.getCurrentUser();
                if(user == null) {
                //no esta logeado
                }
                else{
                    return;
                }
            }
        };
        loginButton = findViewById(R.id.login_button);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplication(),Register.class);
                startActivity(intent);
            }
        });
    }

    private void logIn(){
        String mail = Textemail.getText().toString();
        String pass = Textpass.getText().toString();

        if (!mail.isEmpty() && !pass.isEmpty()){
            mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                       // Toast.makeText(getApplicationContext(),task.getResult().getUser().getUid(),Toast.LENGTH_SHORT).show();
                        //editor.putString("user_id", task.getResult().getUser().getUid());
                        //editor.apply();

                        //Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (getApplication(),MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Incorrecto",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(listener != null){
            mAuth.removeAuthStateListener(listener);
        }
    }
}
