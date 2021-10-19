package com.example.AppLobsangBarriga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.AppLobsangBarriga.controllers.BmiController;
import com.example.AppLobsangBarriga.models.Bmi;

public class BmiDetailActivity extends AppCompatActivity {

    private TextView tvTitle, tvDate, tvWeight, tvBmi;
    private Button btnDelete, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_detail);

        Bmi bmi = (Bmi) getIntent().getSerializableExtra("bmi");

        tvTitle = findViewById(R.id.activity_bmi_detail_text_title);
        tvDate = findViewById(R.id.activity_bmi_detail_text_date);
        tvWeight = findViewById(R.id.activity_bmi_detail_text_weight);
        tvBmi = findViewById(R.id.activity_bmi_detail_text_bmi);
        btnDelete = findViewById(R.id.activity_bmi_detail_btn_delete);
        btnBack = findViewById(R.id.activity_bmi_detail_btn_back);

        tvTitle.setText(String.format(getString(R.string.activity_bmi_detail_txt_title), bmi.getId()));
        tvDate.setText("Fecha: " + bmi.getDateAsString());
        tvWeight.setText("Peso (Kg): " + bmi.getWeightAsString());
        tvBmi.setText("IMC: " + bmi.getCalculatedBmiAsString());

        btnDelete.setOnClickListener(view -> {
            BmiController controller = new BmiController(view.getContext());
            controller.delete(bmi.getId());
        });

        btnBack.setOnClickListener(view -> {
            super.onBackPressed();
        });
    }
}