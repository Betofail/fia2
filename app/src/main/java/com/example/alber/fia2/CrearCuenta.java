package com.example.alber.fia2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearCuenta extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtApellido;
    private Button btnCuenta;

    //private DatabaseReference DB;
    private FirebaseFirestore db;
    private static final String TAG = "Creando cliente";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        db = FirebaseFirestore.getInstance();

        txtNombre = findViewById(R.id.nombre_cuenta);
        txtApellido = findViewById(R.id.apellido_cuenta);
        //DB = FirebaseDatabase.getInstance().getReference("Database");
        btnCuenta = findViewById(R.id.button_cuenta);

        btnCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearCuenta();
            }
        });
    }

    public void crearCuenta(){
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        if (!TextUtils.isEmpty(nombre)&& !TextUtils.isEmpty(apellido)){
            //String id = DB.push().getKey();
            //Database cuenta = new Database(id,nombre,apellido,"0");
            //DB.child("Cuentas").child(id).setValue(cuenta);

            Map<String, Object> client = new HashMap<>();
            client.put("name", nombre);
            client.put("apellido", apellido);

            db.collection("clients")
                    .add(client)
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

            showToast("Cuenta guardada");
        }else{
            showToast("Debe colocar nombre y apellido");
        }
    }


    private void showToast(String message){
        Toast.makeText(CrearCuenta.this,message,Toast.LENGTH_SHORT).show();
    }
}
