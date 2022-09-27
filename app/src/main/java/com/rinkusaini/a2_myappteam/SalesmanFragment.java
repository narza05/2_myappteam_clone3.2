package com.rinkusaini.a2_myappteam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SalesmanFragment extends Fragment {

    TextView name;
    Button add;
    Button delete;
    DatabaseHelper DatabaseHelper;
    ListView listView;
    ArrayAdapter adapter;
    ArrayList arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_salesman, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        name = view.findViewById(R.id.salesmanName);
        add = view.findViewById(R.id.add_salesman);
        delete = view.findViewById(R.id.delete_salesman);
        listView = view.findViewById(R.id.ListSalesman);
        refresh();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametext = name.getText().toString();
                if (!nametext.isEmpty()){
                    if (DatabaseHelper.addText(nametext,"TABLE_SALESMAN")){
                     name.setText("");
                        Toast.makeText(getActivity(), "Inserted", Toast.LENGTH_SHORT).show();
                        refresh();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametext = name.getText().toString();
                if (!nametext.isEmpty()){
                    if (DatabaseHelper.deleteText(nametext, "TABLE_SALESMAN")){
                        name.setText("");
                        Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
                        refresh();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void refresh(){
        DatabaseHelper = new DatabaseHelper(getActivity());

        arrayList = DatabaseHelper.getAllText("TABLE_SALESMAN");

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(adapter);
    }

}