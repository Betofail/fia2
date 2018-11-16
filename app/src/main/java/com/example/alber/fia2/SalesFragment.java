package com.example.alber.fia2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SalesFragment extends Fragment {

    private LinearLayout searchLayout;

    private Button searchButton, selectClient;
    private EditText searchField;

    private FirebaseFirestore db;
    private DatabaseReference clientsDatabase;
    private CollectionReference clientsReference;

    private TextView name, lastName, rut;

    private static final String TAG = "GETTING CLIENT BY RUT";

    private boolean userFound=false;




    public SalesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sales, container, false);

        searchLayout = rootView.findViewById(R.id.search_layout);

        searchField = rootView.findViewById(R.id.search_client_field);
        searchButton = rootView.findViewById(R.id.search_client);
        selectClient = rootView.findViewById(R.id.select_client_button);

        name = rootView.findViewById(R.id.find_name);
        lastName = rootView.findViewById(R.id.find_last_name);
        rut = rootView.findViewById(R.id.find_rut);

        db = FirebaseFirestore.getInstance();
        clientsReference = db.collection("clients");

        searchButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String searchText = searchField.getText().toString();

                Query clientsQuery = clientsReference.whereEqualTo("rut",searchText);


                clientsQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            //showToast("Buscando cliente");

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, "CLIENTE ENCONTRADO" + " => " + document.getData());
                                name.setText(document.getString("nombre"));
                                lastName.setText(document.getString("apellido"));
                                rut.setText(document.getString("rut"));

                                userFound = true;

                            }
                        }
                        else{
                            showToast("Usuario no encontrado");
                            userFound = false;
                        }
                    }
                });

            }
        });

        selectClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userFound == true)
                searchLayout.setVisibility(View.GONE);
                else
                    showToast("Seleccione un cliente primero");
            }
        });



        return rootView;
    }

    public void showToast(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }




}
