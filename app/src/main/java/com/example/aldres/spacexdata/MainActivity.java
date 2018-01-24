package com.example.aldres.spacexdata;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton search;
    ProgressBar progressBar;
    ViewData viewData;
    int yearSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycler_view);
        viewData = new ViewData();
        search = findViewById(R.id.search_fab);
        progressBar = findViewById(R.id.progress_bar);

        Intent intent = getIntent();
        yearSelected = intent.getIntExtra("yearSelected", 2017);
        viewData.execute("https://api.spacexdata.com/v2/launches?launch_year=" + yearSelected);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToSearch = new Intent(MainActivity.this, YearSelectionActivity.class);
                startActivity(backToSearch);
            }
        });

    }

        class ViewData extends UrlTask {

            @Override
            protected void onPreExecute() {
                progressBar.setVisibility(View.VISIBLE);
            }


            @Override
            protected void onPostExecute(List<DataModel> dataModels) {
                super.onPostExecute(dataModels);
                progressBar.setVisibility(View.GONE);
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(dataModels);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerViewAdapter);

            }

        }
}
