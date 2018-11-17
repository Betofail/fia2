package com.example.alber.fia2;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CreateClientFragment extends Fragment {

    private EditText nombre, apellido, rut;
    private Button ingresarClienteButton;
    private FirebaseFirestore db;
    private static final String TAG = "Creando cliente";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_client, container, false);

        this.nombre = rootView.findViewById(R.id.nombre_cliente);
        this.apellido = rootView.findViewById(R.id.apellido_cliente);
        this.rut = rootView.findViewById(R.id.rut_cliente);

        this.ingresarClienteButton = rootView.findViewById(R.id.button_ingresar_cliente);
        this.db = FirebaseFirestore.getInstance();

        this.ingresarClienteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                crearCliente();
            }
        });

        return rootView;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_main,menu);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void showToast(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    public void crearCliente(){
        String nombre = this.nombre.getText().toString().trim();
        String apellido = this.apellido.getText().toString().trim();
        String rut = this.rut.getText().toString().trim();

        if(this.validateFields(nombre,apellido,rut)){
            Map<String, Object> client = new HashMap<>();
            client.put("nombre", nombre);
            client.put("apellido", apellido);
            client.put("rut",rut);

            this.db.collection("clients")
                    .add(client)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            showToast("Nuevo cliente registrado");
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        }
        else{
            showToast("Ingrese todos los campos");
        }


    }

    private boolean validateFields(String nombre, String apellido, String rut){
        if (!TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(apellido) && !TextUtils.isEmpty(rut)){
            return true;
        }
        else {
            return false;
        }
    }
}
