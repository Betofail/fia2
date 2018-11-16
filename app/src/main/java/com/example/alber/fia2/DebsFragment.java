package com.example.alber.fia2;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class DebsFragment extends Fragment {


    public View rootView;
    public List<String> listHeader;
    public HashMap<String,List<String>> hashMap;

    public DebsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_debs, container, false);
        initData();
        ExpandableListView elv =  rootView.findViewById(R.id.debs_list_view);
        elv.setAdapter(new SavedTabsListAdapter());
        return rootView;
    }

    private void initData(){
        listHeader = new ArrayList<>();
        hashMap = new HashMap<>();

        listHeader.add("Primera");
        listHeader.add("Segunda");

        List<String> dev = new ArrayList<>();
        dev.add("Descripcion de primera");

        List<String> app = new ArrayList<>();
        app.add("Descripcion de segunda");

        hashMap.put(listHeader.get(0),dev);
        hashMap.put(listHeader.get(1),app);
    }
  /*  public void loadText(){

        deudas.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){
                    ArrayList lista = new ArrayList<>();
                    for (QueryDocumentSnapshot document: task.getResult()){
                        groups.
                    }
                }
                else{
                    Toast.makeText(getContext(), "no se pudo realizar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/

    public class SavedTabsListAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return listHeader.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return hashMap.get(listHeader.get(i)).size();
        }

        @Override
        public Object getGroup(int i) {
            return listHeader.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return hashMap.get(listHeader.get(i)).get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            String headerTitle = (String) getGroup(i);
            view = LayoutInflater.from(rootView.getContext()).inflate(R.layout.list_group,null);
            TextView textView = (TextView) view.findViewById(R.id.group_group);
            textView.setText(headerTitle);
            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            String item = (String) getChild(i,i1);
            view = LayoutInflater.from(rootView.getContext()).inflate(R.layout.list_item,null);
            TextView textView =  view.findViewById(R.id.item_group);
            textView.setText(item);
            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }

    }

}
