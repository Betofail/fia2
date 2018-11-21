package com.example.alber.fia2;


import android.content.Context;

import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public FloatingActionButton fab;
    public View rootView;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public ArrayList<String> lista_rut;
    public ArrayList<String> lista_suma;
    public ArrayList<String> lista_nombre;
    public ArrayList<String> lista_monto;
    public ArrayList<String> lista_monto_pago;
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

        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView = rootView.findViewById(R.id.home_list_view);
        initData(listView);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "mensaje", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
    public void initData(final ListView listview){
        lista_monto = new ArrayList<>();
        lista_monto_pago = new ArrayList<>();
        lista_nombre = new ArrayList<>();
        lista_rut = new ArrayList<>();
        lista_suma = new ArrayList<>();
        db.collection("Deudas").whereEqualTo("id_negocio","hWfyjMYmx4c40GxXchUE")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
              for (QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()) {
                  lista_rut.add((String) queryDocumentSnapshot.get("rut"));
                  lista_monto.add((String) queryDocumentSnapshot.get("monto"));
                  lista_nombre.add((String) queryDocumentSnapshot.get("nombre"));
              }
                db.collection("Pagos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){
                            if (lista_rut.contains(queryDocumentSnapshot.get("rut"))){
                                lista_monto_pago.add((String) queryDocumentSnapshot.get("monto"));
                            }
                            else{
                            }
                        }
                        for (int i = 0; i != lista_monto_pago.size(); i++) {
                            int suma = (Integer.parseInt(lista_monto_pago.get(i)) - Integer.parseInt(lista_monto.get(i)));
                            lista_suma.add(lista_nombre.get(i)+ ": " + Integer.toString(suma)  );
                        }
                        ArrayAdapter adapter = new ArrayAdapter(rootView.getContext(),android.R.layout.simple_list_item_1,lista_suma);
                        listview.setAdapter(adapter);
                    }
                });
            }
        });


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
