package com.example.AppLobsangBarriga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.AppLobsangBarriga.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

public class BmiCreateActivity extends AppCompatActivity {

    private Button btnSaveBmi;
    private TextInputLayout tilDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_create);

        btnSaveBmi = findViewById(R.id.activity_bmi_create_btn_save);
        tilDate = findViewById(R.id.activity_bmi_create_til_date);

        tilDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDate);
        });

        btnSaveBmi.setOnClickListener(view -> {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.activity_bmi_create_txt_toast_save, Toast.LENGTH_SHORT);
            toast.show();
            Intent i = new Intent(view.getContext(), MainActivity.class);
            startActivity(i);
            finish();
        });
    }
}