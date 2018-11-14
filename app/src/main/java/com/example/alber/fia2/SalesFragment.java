package com.example.alber.fia2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SalesFragment extends Fragment {

    private Button searchButton;
    private EditText searchField;
    private RecyclerView clientsFoundList;
    private DatabaseReference clientsDatabase;


    public SalesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sales, container, false);

        searchField = rootView.findViewById(R.id.search_client_field);
        searchButton = rootView.findViewById(R.id.search_client);

        clientsFoundList = rootView.findViewById(R.id.search_clients_list);
        clientsFoundList.setHasFixedSize(true);
        clientsFoundList.setLayoutManager(new LinearLayoutManager(getContext()));

        clientsDatabase = FirebaseDatabase.getInstance().getReference("clients");

        searchButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String searchText = searchField.getText().toString();
                Toast.makeText(getContext(),"Buscando Usuario",Toast.LENGTH_SHORT).show();
                //firebaseUserSearch(searchText);
            }
        });

        return rootView;
    }

    /*private void firebaseUserSearch(String name) {

        Query firebaseSearchQuery = clientsDatabase.orderByChild("nombre").startAt(name).endAt(name + "\uf8ff");

        FirebaseRecyclerAdapter<Clients, ClientsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Clients, ClientsViewHolder>(

                Clients.class,
                R.layout.fragment_sales,
                ClientsViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(ClientsViewHolder viewHolder, Clients model, int position) {


                viewHolder.setDetails(getContext(), model.getName());

            }
        };

        clientsFoundList.setAdapter(firebaseRecyclerAdapter);

    }*/

    //---------------------------------------------------------------------------------------------//

    public static class ClientsViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public ClientsViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx, String name){

            //TextView user_name = mView.findViewById(R.id.name_text);
            //TextView user_status = (TextView) mView.findViewById(R.id.status_text);

            //user_name.setText(userName);
            //user_status.setText(userStatus);

        }




    }


}
