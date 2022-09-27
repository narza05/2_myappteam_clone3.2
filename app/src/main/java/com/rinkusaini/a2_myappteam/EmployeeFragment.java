package com.rinkusaini.a2_myappteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class EmployeeFragment extends Fragment {

    TextView name;
    Button add;
    Button delete;
    DatabaseHelper DatabaseHelper;
    ListView listView;
    ArrayAdapter adapter;
    ArrayList arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_employee, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        name = view.findViewById(R.id.employeeName);
        add = view.findViewById(R.id.add_employee);
        delete = view.findViewById(R.id.delete_employee);
        listView = view.findViewById(R.id.ListEmployee);
        refresh();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametext = name.getText().toString();
                if (!nametext.isEmpty()){
                    if (DatabaseHelper.addText(nametext,"TABLE_EMPLOYEE")){
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
                    if (DatabaseHelper.deleteText(nametext, "TABLE_EMPLOYEE")){
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

        arrayList = DatabaseHelper.getAllText("TABLE_EMPLOYEE");

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(adapter);
    }

}