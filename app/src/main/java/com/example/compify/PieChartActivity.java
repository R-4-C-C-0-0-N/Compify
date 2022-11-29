package com.example.compify;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {
    PieChart pieChart1, pieChart2, pieChart3;
    Bundle dataRetriever;
    String firstModelName, firstModelScore1, firstModelScore2, firstModelScore3, firstModelScore4, firstModelScore5, firstModelScore6, firstModelScore7;
    String secondModelName, secondModelScore1, secondModelScore2, secondModelScore3, secondModelScore4, secondModelScore5, secondModelScore6, secondModelScore7;
    Float fFirstModelScore1, fFirstModelScore2, fFirstModelScore3, fFirstModelScore4, fFirstModelScore5, fFirstModelScore6, fFirstModelScore7;
    Float fSecondModelScore1, fSecondModelScore2, fSecondModelScore3, fSecondModelScore4, fSecondModelScore5, fSecondModelScore6, fSecondModelScore7;
    Float firstModelAvg1, firstModelAvg2;
    Float secondModelAvg1, secondModelAvg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        initActivity();
        getData();
        castPointsData();
        calculations();
    }

    public void initActivity() {
        pieChart1 = findViewById(R.id.PieChart1);
        pieChart2 = findViewById(R.id.PieChart2);
        pieChart3 = findViewById(R.id.PieChart3);
    }

    public void getData() {
        dataRetriever = getIntent().getExtras();
        firstModelName = dataRetriever != null ? dataRetriever.getString("firstModelName") : null;
        secondModelName = dataRetriever != null ? dataRetriever.getString("secondModelName") : null;
        firstModelScore1 = dataRetriever != null ? dataRetriever.getString("firstModelScore1") : null;
        firstModelScore2 = dataRetriever != null ? dataRetriever.getString("firstModelScore2") : null;
        firstModelScore3 = dataRetriever != null ? dataRetriever.getString("firstModelScore3") : null;
        firstModelScore4 = dataRetriever != null ? dataRetriever.getString("firstModelScore4") : null;
        firstModelScore5 = dataRetriever != null ? dataRetriever.getString("firstModelScore5") : null;
        firstModelScore6 = dataRetriever != null ? dataRetriever.getString("firstModelScore6") : null;
        firstModelScore7 = dataRetriever != null ? dataRetriever.getString("firstModelScore7") : null;
        secondModelScore1 = dataRetriever != null ? dataRetriever.getString("secondModelScore1") : null;
        secondModelScore2 = dataRetriever != null ? dataRetriever.getString("secondModelScore2") : null;
        secondModelScore3 = dataRetriever != null ? dataRetriever.getString("secondModelScore3") : null;
        secondModelScore4 = dataRetriever != null ? dataRetriever.getString("secondModelScore4") : null;
        secondModelScore5 = dataRetriever != null ? dataRetriever.getString("secondModelScore5") : null;
        secondModelScore6 = dataRetriever != null ? dataRetriever.getString("secondModelScore6") : null;
        secondModelScore7 = dataRetriever != null ? dataRetriever.getString("secondModelScore7") : null;
    }

    public void castPointsData() {
        fFirstModelScore1 = Float.parseFloat(firstModelScore1);
        fFirstModelScore2 = Float.parseFloat(firstModelScore2);
        fFirstModelScore3 = Float.parseFloat(firstModelScore3);
        fFirstModelScore4 = Float.parseFloat(firstModelScore4);
        fFirstModelScore5 = Float.parseFloat(firstModelScore5);
        fFirstModelScore6 = Float.parseFloat(firstModelScore6);
        fFirstModelScore7 = Float.parseFloat(firstModelScore7);
        fSecondModelScore1 = Float.parseFloat(secondModelScore1);
        fSecondModelScore2 = Float.parseFloat(secondModelScore2);
        fSecondModelScore3 = Float.parseFloat(secondModelScore3);
        fSecondModelScore4 = Float.parseFloat(secondModelScore4);
        fSecondModelScore5 = Float.parseFloat(secondModelScore5);
        fSecondModelScore6 = Float.parseFloat(secondModelScore6);
        fSecondModelScore7 = Float.parseFloat(secondModelScore7);
    }

    public void calculations() {
        firstModelAvg1 = ((((30 * fFirstModelScore2) / 100) + ((60 * fFirstModelScore3) / 100) + ((10 * fFirstModelScore4) / 100)) / 3);
        firstModelAvg2 = ((((30 * fFirstModelScore5) / 100) + ((60 * fFirstModelScore6) / 100) + ((10 * fFirstModelScore7) / 100)) / 3);
        secondModelAvg1 = ((((30 * fSecondModelScore2) / 100) + ((60 * fSecondModelScore3) / 100) + ((10 * fSecondModelScore4) / 100)) / 3);
        secondModelAvg2 = ((((30 * fSecondModelScore5) / 100) + ((60 * fSecondModelScore6) / 100) + ((10 * fSecondModelScore7) / 100)) / 3);

        List<PieEntry> pieEntries1 = new ArrayList<>();
        List<PieEntry> pieEntries2 = new ArrayList<>();
        List<PieEntry> pieEntries3 = new ArrayList<>();

        pieEntries1.add(new PieEntry(fFirstModelScore1, firstModelName));
        pieEntries1.add(new PieEntry(fSecondModelScore1, secondModelName));
        pieEntries2.add(new PieEntry(firstModelAvg1, firstModelName));
        pieEntries2.add(new PieEntry(secondModelAvg1, secondModelName));
        pieEntries3.add(new PieEntry(firstModelAvg2, firstModelName));
        pieEntries3.add(new PieEntry(secondModelAvg2, secondModelName));

        PieDataSet dataSet1 = new PieDataSet(pieEntries1, "Effective Speed");
        PieDataSet dataSet2 = new PieDataSet(pieEntries2, "Average User Bench");
        PieDataSet dataSet3 = new PieDataSet(pieEntries3, "Peak Overclocked Bench");

        PieData data1 = new PieData(dataSet1);
        PieData data2 = new PieData(dataSet2);
        PieData data3 = new PieData(dataSet3);

        pieChart1.setData(data1);
        pieChart2.setData(data2);
        pieChart3.setData(data3);

        pieChart1.setUsePercentValues(true);
        pieChart2.setUsePercentValues(true);
        pieChart3.setUsePercentValues(true);

        dataSet1.setSliceSpace(3f);
        dataSet2.setSliceSpace(3f);
        dataSet3.setSliceSpace(3f);

        dataSet1.setSelectionShift(5f);
        dataSet2.setSelectionShift(5f);
        dataSet3.setSelectionShift(5f);

        dataSet1.setColors(new int[] { R.color.colorGreen, R.color.colorBlue }, PieChartActivity.this);
        dataSet2.setColors(new int[] { R.color.colorGreen, R.color.colorBlue }, PieChartActivity.this);
        dataSet3.setColors(new int[] { R.color.colorGreen, R.color.colorBlue }, PieChartActivity.this);

        data1.setValueTextSize(12f);
        data2.setValueTextSize(12f);
        data3.setValueTextSize(12f);

        data1.setValueTextColor(Color.WHITE);
        data2.setValueTextColor(Color.WHITE);
        data3.setValueTextColor(Color.WHITE);

        pieChart1.getDescription().setEnabled(false);
        pieChart2.getDescription().setEnabled(false);
        pieChart3.getDescription().setEnabled(false);

        pieChart1.setHoleColor(0xFF292729);
        pieChart2.setHoleColor(0xFF292729);
        pieChart3.setHoleColor(0xFF292729);

        pieChart1.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        pieChart2.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        pieChart3.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        pieChart1.getLegend().setTextSize(12f);
        pieChart2.getLegend().setTextSize(12f);
        pieChart3.getLegend().setTextSize(12f);

        pieChart1.getLegend().setTextColor(Color.WHITE);
        pieChart2.getLegend().setTextColor(Color.WHITE);
        pieChart3.getLegend().setTextColor(Color.WHITE);

        pieChart1.animateY(1500, Easing.EaseInCubic);
        pieChart2.animateY(1500, Easing.EaseInCubic);
        pieChart3.animateY(1500, Easing.EaseInCubic);

        pieChart1.invalidate();
        pieChart2.invalidate();
        pieChart3.invalidate();
    }
}