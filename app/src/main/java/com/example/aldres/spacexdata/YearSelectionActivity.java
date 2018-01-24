package com.example.aldres.spacexdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class YearSelectionActivity extends AppCompatActivity {

    Button showLaunchesButton;
    NumberPicker yearPicker;
    int yearSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_selection);
        showLaunchesButton = findViewById(R.id.show_launches_button);
        yearPicker = findViewById(R.id.numberPicker);

        yearPicker.setMinValue(2006);
        yearPicker.setMaxValue(2018);

        showLaunchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YearSelectionActivity.this, MainActivity.class);
                yearSelected = yearPicker.getValue();
                System.out.println(yearSelected);
                intent.putExtra("yearSelected", yearSelected);
                startActivity(intent);
            }
        });


    }

}


