package com.rinkusaini.a2_myappteam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class SalesFragment extends Fragment {

    BarChart barChart;
    PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sales, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barChart = view.findViewById(R.id.barchart);
        pieChart = view.findViewById(R.id.piechart);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        for (int i=1; i<=4; i++){
            float value = (float) i*10;

            BarEntry barEntry = new BarEntry(i,value);  // distance between bars
            PieEntry pieEntry = new PieEntry(i,value);  // i == diference

            barEntries.add(barEntry);
            pieEntries.add(pieEntry);

        }

        refresh(barEntries,pieEntries);

    }

    public void refresh(List<BarEntry> bentry, List<PieEntry> pentry){
        List<BarEntry> barEntries = bentry;
        List<PieEntry> pieEntries = pentry;

        BarDataSet barDataSet = new BarDataSet(barEntries,"sales");

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setDrawValues(true);
        barChart.setData(new BarData(barDataSet));
        barChart.animateY(3000);

        PieDataSet pieDataSet = new PieDataSet(pieEntries,"salesman");

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(new PieData(pieDataSet));
        pieChart.animateXY(3000, 3000);
        pieChart.getDescription().setEnabled(false);
    }

}