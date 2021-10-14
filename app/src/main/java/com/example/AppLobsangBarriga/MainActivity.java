package com.example.AppLobsangBarriga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AppLobsangBarriga.controllers.AuthController;
import com.example.AppLobsangBarriga.models.Bmi;
import com.example.AppLobsangBarriga.models.User;
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
    private AuthController authController;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authController = new AuthController(this);

        lvAllBmi = findViewById(R.id.activity_main_lv_bmi);
        tilStartDate = findViewById(R.id.activity_main_til_start_date);
        tilEndDate = findViewById(R.id.activity_main_til_end_date);
        btnFilter = findViewById(R.id.activity_main_btn_filter);
        btnNewBmi = findViewById(R.id.activity_main_btn_new_bmi);
        btnSignOut = findViewById(R.id.activity_main_btn_sign_out);
        tvTitle = findViewById(R.id.activity_main_txt_title);

        User user = authController.getUserSession();

        //tvTitle.setText(String.format(R.string.activity_main_txt_toast_filter, user.getFirstName()));
        tvTitle.setText(String.format("Evaluaciones de %s", user.getFirstName()));

        for (int i = 0; i <= 10; i++) {
            //Bmi newBmi = new Bmi(String.format("Date %d", i),String.format("Weight %d",i),String.format("Calculated BMI: %d",i));
            String randomDate = Integer.toString(new Random().nextInt(29) + 1) + "/" + Integer.toString(new Random().nextInt(11) + 1) + "/2021";
            String randomWeight = Integer.toString(new Random().nextInt(6) + 79);
            String calculatedBmi = Double.toString(Double.parseDouble(randomWeight)/1.72);
            Bmi newBmi = new Bmi(getResources().getString(R.string.activity_main_txt_item_date) + " " + randomDate,
                    getResources().getString(R.string.activity_main_txt_item_weight) + " " + randomWeight + getResources().getString(R.string.activity_main_txt_item_weight_kg),
                    getResources().getString(R.string.activity_main_txt_item_bmi) + " " + calculatedBmi);
            newBmi.setId(i);
            bmiList.add(newBmi);
        }

        BmiAdapter adapter = new BmiAdapter(this, bmiList);

        lvAllBmi.setAdapter(adapter);

        lvAllBmi.setOnItemClickListener(((adapterView, view, index, id) -> {
            Bmi bmi = bmiList.get(index);
            Intent i = new Intent(view.getContext(), BmiDetailActivity.class);
            i.putExtra("bmi",bmi);
            view.getContext().startActivity(i);
        }));

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
            authController.logout();
            //Intent i = new Intent(view.getContext(), LoginActivity.class);
            //startActivity(i);
            //finish();
        });
    }
}