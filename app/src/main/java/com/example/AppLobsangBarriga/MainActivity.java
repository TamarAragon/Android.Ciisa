package com.example.AppLobsangBarriga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.AppLobsangBarriga.models.Bmi;
import com.example.AppLobsangBarriga.ui.BmiAdapter;
import com.example.AppLobsangBarriga.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ListView lvAllBmi;
    private List<Bmi> bmiList = new ArrayList<>();
    private TextInputLayout tilStartDate, tilEndDate;
    private Button btnFilter, btnNewBmi, btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvAllBmi = findViewById(R.id.activity_main_lv_bmi);
        tilStartDate = findViewById(R.id.activity_main_til_start_date);
        tilEndDate = findViewById(R.id.activity_main_til_end_date);
        btnFilter = findViewById(R.id.activity_main_btn_filter);
        btnNewBmi = findViewById(R.id.activity_main_btn_new_bmi);
        btnSignOut = findViewById(R.id.activity_main_btn_sign_out);

        for (int i = 0; i <= 10; i++) {
            String randomDate = Integer.toString(new Random().nextInt(29) + 1) + "/" + Integer.toString(new Random().nextInt(11) + 1) + "/2021";
            String randomWeight = Integer.toString(new Random().nextInt(6) + 79);
            //Bmi newBmi = new Bmi(String.format("Date %d", i),String.format("Weight %d",i),String.format("Calculated BMI: %d",i));
            Bmi newBmi = new Bmi("Date: " + randomDate,"Weight: " + randomWeight,"Calculated BMI: " + Double.toString(Double.parseDouble(randomWeight)/1.72));
            newBmi.setId(i);
            bmiList.add(newBmi);
        }

        BmiAdapter adapter = new BmiAdapter(this, bmiList);

        lvAllBmi.setAdapter(adapter);

        tilStartDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilStartDate);
        });

        tilEndDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilEndDate);
        });

        btnFilter.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), R.string.activity_main_txt_toast_filter, Toast.LENGTH_SHORT).show();
        });

        btnNewBmi.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), BmiCreateActivity.class);
            startActivity(i);
            finish();
        });

        btnSignOut.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), LoginActivity.class);
            startActivity(i);
            finish();
        });
    }
}