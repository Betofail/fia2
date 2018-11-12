package com.example.alber.fia2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrearCuenta extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtApellido;
    private Button btnCuenta;

    private DatabaseReference DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        txtNombre = findViewById(R.id.nombre_cuenta);
        txtApellido = findViewById(R.id.apellido_cuenta);
        DB = FirebaseDatabase.getInstance().getReference("Database");
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
            String id = DB.push().getKey();
            Database cuenta = new Database(id,nombre,apellido,"0");
            DB.child("Cuentas").child(id).setValue(cuenta);

            Toast.makeText(this, "Cuenta guardada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debe colocar nombre y apellido", Toast.LENGTH_SHORT).show();
        }
    }
}
