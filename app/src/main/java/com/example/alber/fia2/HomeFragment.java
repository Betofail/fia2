package com.example.alber.fia2;


import android.content.Context;

import android.content.Intent;

import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;



/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public FloatingActionButton fab;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public Task<QuerySnapshot> deuda;
    public ArrayList<String> lista_rut;
    public ArrayList<String> lista_nombre;
    public ArrayList<String> lista_monto;
    private SharedPreferences.Editor editor;



    public HomeFragment() {
        // Required empty public constructor
        //editor = getSharedPreferences("SHARED PREFERENCES", MODE_PRIVATE).edit();
        //SharedPreferences prefs = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        //Toast.makeText(this.getContext(),"Incorrecto",Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "mensaje", Toast.LENGTH_SHORT).show();
            }
        });

        /*db.collection("Deudas").whereEqualTo("id_negocio", "hWfyjMYmx4c40GxXchUE").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                         lista_rut = new ArrayList<>();
                         lista_nombre = new ArrayList<>();
                         lista_monto = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                            lista_rut.add((String) queryDocumentSnapshot.get("rut"));
                            lista_nombre.add((String) queryDocumentSnapshot.get("nombre"));
                            lista_monto.add((String) queryDocumentSnapshot.get("monto"));
                        }
                    }
                });
        Toast.makeText(rootView.getContext(), (CharSequence) lista_monto, Toast.LENGTH_SHORT).show();
        db.collection("pagos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ListView lista = rootView.findViewById(R.id.home_list_view);
                ArrayList<String> coleccion = new ArrayList<>();
                Integer contador = 0;
                for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                    if (lista_rut.get(contador) == queryDocumentSnapshot.get("rut")){
                        int monto1 = Integer.parseInt((String) queryDocumentSnapshot.get("monto"));
                        int monto2 = Integer.parseInt(lista_monto.get(contador));
                        int suma = monto1 - monto2;
                        coleccion.add((String.format("%s: %s", lista_nombre.get(contador), suma)));

                    }
                    else{
                        continue;
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),android.R.layout.simple_list_item_1,coleccion);
                lista.setAdapter(adapter);
            }
        });*/

        return rootView;

    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_main,menu);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        SharedPreferences preferences = context.getSharedPreferences("SHARED PREFERENCES", 0);
        String userId = preferences.getString("user_id", "missing");
    }
}
