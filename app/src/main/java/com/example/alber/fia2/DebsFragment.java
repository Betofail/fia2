package com.example.alber.fia2;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DebsFragment extends Fragment {

    public Integer contador = 0;
    public View rootView;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public CollectionReference clientesRef = db.collection("clients");
    public CollectionReference deudasRef = db.collection("Deudas");
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
        return rootView;
    }



    public void initData() {
        deudasRef.whereEqualTo("id_negocio", "hWfyjMYmx4c40GxXchUE").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        listHeader = new ArrayList<>();
                        hashMap = new HashMap<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){
                                listHeader.add(queryDocumentSnapshot.get("nombre").toString());
                                List<String> dev = new ArrayList<>();
                                dev.add(queryDocumentSnapshot.get("fecha").toString());
                                dev.add(queryDocumentSnapshot.get("monto").toString());
                                hashMap.put(listHeader.get(listHeader.size()-1),dev);
                        }
                        ExpandableListView elv =  rootView.findViewById(R.id.debs_list_view);
                        elv.setAdapter(new SavedTabsListAdapter());
                    }
                });
    }

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
