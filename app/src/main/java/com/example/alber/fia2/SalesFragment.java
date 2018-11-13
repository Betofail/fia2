package com.example.alber.fia2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class SalesFragment extends Fragment {

    private Button searchButton;
    private EditText searchField;
    private RecyclerView usersFoundList;
    private DatabaseReference nUserDatabase;


    public SalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View rootView = inflater.inflate(R.layout.fragment_sales, container, false);

        searchField = rootView.findViewById(R.id.search_user_field);
        searchButton = rootView.findViewById(R.id.search_user);
        usersFoundList = rootView.findViewById(R.id.search_users_list);

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getContext(),"Buscando Usuario",Toast.LENGTH_SHORT).show();
                firebaseUserSearch();
            }


        });

        return rootView;
    }

    private void firebaseUserSearch() {
        //FirebaseRecyclerAdapter<Users, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter
    }

    //---------------------------------------------------------------------------------------------//

    public class UsersViewHolder extends RecyclerView.ViewHolder{

        public UsersViewHolder(View itemView){
            super(itemView);
        }

    }


}
