package com.example.alber.fia2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


public class RealizarPago extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);
        Button button = findViewById(R.id.realizar_pago);
        final EditText monto = findViewById(R.id.monto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String rut = getIntent().getStringExtra("rut");
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Map<String,Object> pago = new HashMap<>();
                pago.put("monto",monto.getText().toString());
                pago.put("rut",rut);
                pago.put("fecha",timestamp);

                db.collection("Pagos").document().set(pago)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Se realizo el pago",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"ocurrio un error al realizar el pago",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }
}
