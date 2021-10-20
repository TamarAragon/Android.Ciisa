package com.example.AppLobsangBarriga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.AppLobsangBarriga.controllers.AuthController;
import com.example.AppLobsangBarriga.controllers.BmiController;
import com.example.AppLobsangBarriga.lib.TilValidator;
import com.example.AppLobsangBarriga.models.Bmi;
import com.example.AppLobsangBarriga.models.User;
import com.example.AppLobsangBarriga.ui.DatePickerFragment;
import com.example.AppLobsangBarriga.utils.DateUtils;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

public class BmiCreateActivity extends AppCompatActivity {

    private Button btnSaveBmi, btnBack;
    private TextInputLayout tilDate, tilWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_create);

        btnSaveBmi = findViewById(R.id.activity_bmi_create_btn_save);
        btnBack = findViewById(R.id.activity_bmi_create_btn_back);
        tilDate = findViewById(R.id.activity_bmi_create_til_date);
        tilWeight = findViewById(R.id.activity_bmi_create_til_weight);

        tilDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDate, new Date());
        });

        btnSaveBmi.setOnClickListener(view -> {
            String bmiDate = tilDate.getEditText().getText().toString();
            String bmiWeight = tilWeight.getEditText().getText().toString();

            boolean validDate = new TilValidator(tilDate)
                    .required()
                    .isValid();
            boolean validWeight = new TilValidator(tilWeight)
                    .required()
                    .strMin(1)
                    .isValid();

            if (!validDate || !validWeight) {
                return;
            }

            Date bmiDateFormatted = DateUtils.unsafeParse(bmiDate);
            double bmiWeightFormatted = Double.parseDouble(bmiWeight);

            AuthController authController = new AuthController(view.getContext());
            User user = authController.getUserSession();

            Bmi bmi = new Bmi(bmiDateFormatted, bmiWeightFormatted, bmiWeightFormatted / (user.getHeight() * user.getHeight()), user.getId());

            BmiController controller = new BmiController(view.getContext());
            controller.register(bmi);

            Toast.makeText(view.getContext(), "Registrar", Toast.LENGTH_SHORT).show();

            //Toast toast = Toast.makeText(getApplicationContext(), R.string.activity_bmi_create_txt_toast_save, Toast.LENGTH_SHORT);
            //toast.show();

            Intent i = new Intent(view.getContext(), MainActivity.class);
            startActivity(i);
            finish();
        });

        btnBack.setOnClickListener(view -> {
            super.onBackPressed();
        });
    }
}