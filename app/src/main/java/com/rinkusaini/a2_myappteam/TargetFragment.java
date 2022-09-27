package com.rinkusaini.a2_myappteam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class TargetFragment extends Fragment {
    ArrayList<String> arrayList;
    ArrayList<String> arrayList2;
    Spinner spinner;
    SalesmanFragment salesmanFragment;
    DatabaseHelper DatabaseHelper;
    ArrayAdapter adapter;
    ArrayAdapter adapter2;
    ListView listView;
    TextView numberofsales;
    Button add;
    Button delete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_target, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        spinner = view.findViewById(R.id.spinner);
        listView = view.findViewById(R.id.listview);
        numberofsales = view.findViewById(R.id.numberofsales);
        add = view.findViewById(R.id.add);
        delete = view.findViewById(R.id.delete);

        //dropdownmenu
//            DatabaseHelper = new DatabaseHelper(getActivity());
//            arrayList = DatabaseHelper.getAllText("TABLE_SALESMAN");
//        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,arrayList);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        //LISTVIEW
        arrayList2 = new ArrayList<String>();
        adapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayList2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(numberofsales.getText().toString());
                String value = numberofsales.getText().toString();
                arrayList2.add(value);
                listView.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = numberofsales.getText().toString();
                arrayList2.remove(value);
            }
        });

    }
}