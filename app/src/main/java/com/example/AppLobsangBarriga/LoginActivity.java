package com.example.AppLobsangBarriga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import com.example.AppLobsangBarriga.controllers.AuthController;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private Button btnSendLoginForm, btnSignUp;
    private TextInputLayout tilUsername, tilPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSendLoginForm = findViewById(R.id.activity_login_btn_confirm_login);
        btnSignUp = findViewById(R.id.activity_login_btn_create_account);
        tilUsername = findViewById(R.id.activity_login_til_username);
        tilPassword = findViewById(R.id.activity_login_til_password);

        btnSendLoginForm.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), R.string.activity_login_txt_toast_confirm_login, Toast.LENGTH_SHORT).show();

            String username = tilUsername.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();

            boolean usernameValid = !username.isEmpty() && username.matches("^\\S*$");
            boolean passwordValid = !password.isEmpty();

            if (!usernameValid) {
                tilUsername.setError(getString(R.string.activity_login_txt_input_error_username));
            } else {
                tilUsername.setError(null);
                tilUsername.setErrorEnabled(false);
            }

            if (!passwordValid) {
                tilPassword.setError(getResources().getString(R.string.activity_login_txt_input_required));
            } else {
                tilPassword.setError(null);
                tilPassword.setErrorEnabled(false);
            }

            if (usernameValid && passwordValid) {
                AuthController controller = new AuthController(view.getContext());
                controller.login(username, password);
            }
        });

        btnSignUp.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), SignUpActivity.class);
            startActivity(i);
            finish();
        });
    }
}