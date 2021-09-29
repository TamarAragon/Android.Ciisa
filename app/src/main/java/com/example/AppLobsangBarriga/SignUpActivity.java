package com.example.AppLobsangBarriga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.AppLobsangBarriga.controllers.AuthController;
import com.example.AppLobsangBarriga.models.User;
import com.example.AppLobsangBarriga.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    private TextInputLayout tilUsername, tilFirstName, tilLastName, tilDateOfBirth, tilHeight, tilEmail, tilPassword;
    private Button btnSendSignUpForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSendSignUpForm = findViewById(R.id.activity_sign_up_btn_confirm_sign_up);
        tilUsername = findViewById(R.id.activity_sign_up_til_username);
        tilFirstName = findViewById(R.id.activity_sign_up_til_first_name);
        tilLastName = findViewById(R.id.activity_sign_up_til_last_name);
        tilDateOfBirth = findViewById(R.id.activity_sign_up_til_date_of_birth);
        tilHeight = findViewById(R.id.activity_sign_up_til_height);
        tilEmail = findViewById(R.id.activity_sign_up_til_email);
        tilPassword = findViewById(R.id.activity_sign_up_til_password);

        tilDateOfBirth.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDateOfBirth);
        });

        btnSendSignUpForm.setOnClickListener(view -> {
            // TODO: Validations on SignUp activity
            String username = tilUsername.getEditText().getText().toString();
            String firstName = tilFirstName.getEditText().getText().toString();
            String lastName = tilLastName.getEditText().getText().toString();
            String dateOfBirth = tilDateOfBirth.getEditText().getText().toString();
            //SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);
            //Date dateOfBirthFormatted = dateFormatter.parse(dateOfBirth);
            //double height = tilHeight.getEditText().getText().toString();
            String email = tilEmail.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();

            User user = new User(username, firstName, lastName, email);
            user.setPassword(password);

            AuthController controller = new AuthController(view.getContext());

            controller.register(user);

            Toast toast = Toast.makeText(getApplicationContext(), R.string.activity_sign_up_txt_toast_confirm_sign_up, Toast.LENGTH_SHORT);
            toast.show();
            Intent i = new Intent(view.getContext(), LoginActivity.class);
            startActivity(i);
            finish();
        });
    }
}