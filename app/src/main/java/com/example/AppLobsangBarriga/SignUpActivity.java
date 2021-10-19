package com.example.AppLobsangBarriga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import com.example.AppLobsangBarriga.controllers.AuthController;
import com.example.AppLobsangBarriga.models.User;
import com.example.AppLobsangBarriga.ui.DatePickerFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {
    private final String DATE_PATTERN = "yyyy-MM-dd";
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
            DatePickerFragment.showDatePickerDialog(this, tilDateOfBirth, new Date());
        });

        btnSendSignUpForm.setOnClickListener(view -> {
            String username = tilUsername.getEditText().getText().toString();
            String firstName = tilFirstName.getEditText().getText().toString();
            String lastName = tilLastName.getEditText().getText().toString();
            String dateOfBirth = tilDateOfBirth.getEditText().getText().toString();
            Date dateOfBirthFormatted = null;
            try {
                dateOfBirthFormatted = new SimpleDateFormat(DATE_PATTERN).parse(dateOfBirth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String height = tilHeight.getEditText().getText().toString();
            String email = tilEmail.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();

            boolean usernameValid = !username.isEmpty() && username.matches("^\\S*$");
            boolean firstNameValid = !firstName.isEmpty();
            boolean lastNameValid = !lastName.isEmpty();
            boolean dateOfBirthValid = !dateOfBirth.isEmpty();
            boolean heightValid = !height.isEmpty();
            boolean emailValid = !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
            boolean passwordValid = !password.isEmpty();

            if (usernameValid && firstNameValid && lastNameValid && dateOfBirthValid && heightValid && emailValid && passwordValid) {
                User user = new User(username, firstName, lastName, dateOfBirthFormatted, Double.parseDouble(height), email);
                user.setPassword(password);

                AuthController controller = new AuthController(view.getContext());

                controller.register(user);

                Toast toast = Toast.makeText(getApplicationContext(), R.string.activity_sign_up_txt_toast_confirm_sign_up, Toast.LENGTH_SHORT);
                toast.show();
                Intent i = new Intent(view.getContext(), LoginActivity.class);
                startActivity(i);
                finish();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.activity_sign_up_txt_toast_error, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}