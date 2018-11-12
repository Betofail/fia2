package com.example.alber.fia2;


import android.content.Context;

import android.content.Intent;

import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import static android.content.Context.MODE_PRIVATE;



/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public FloatingActionButton fab;

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

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "mensaje", Toast.LENGTH_SHORT).show();
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
        SharedPreferences preferences = context.getSharedPreferences("SHARED PREFERENCES", 0);
        String userId = preferences.getString("user_id", "missing");

        Toast.makeText(this.getContext(),userId + "USUARIO ENCONTRADO",Toast.LENGTH_SHORT).show();
    }
}
