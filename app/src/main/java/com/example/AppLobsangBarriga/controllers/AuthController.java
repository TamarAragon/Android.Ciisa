package com.example.AppLobsangBarriga.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.AppLobsangBarriga.LoginActivity;
import com.example.AppLobsangBarriga.MainActivity;
import com.example.AppLobsangBarriga.R;
import com.example.AppLobsangBarriga.models.User;

import java.util.Date;

// AuthController
// Controls everything that has to do with authentication.

public class AuthController {
    private final String KEY_USER_ID = "userId";
    private final String KEY_EMAIL = "userEmail";
    private final String KEY_FIRST_NAME = "userFirstName";
    private final String KEY_LAST_NAME = "userLastName";

    private Context ctx;
    private SharedPreferences preferences;

    public AuthController(Context ctx) {
        this.ctx = ctx;
        int PRIVATE_MODE = 0;
        String PREF_NAME = "TodoAppPref";
        this.preferences = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }

    private void setUserSession(User user) {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putLong(KEY_USER_ID,user.getId());
        editor.putString(KEY_EMAIL,user.getEmail());
        editor.putString(KEY_FIRST_NAME,user.getFirstName());
        editor.putString(KEY_LAST_NAME,user.getLastName());
        editor.apply();
    }

    /* TO BE IMPLEMENTED IN THE FUTURE
    public User getUserSession() {
        long id = preferences.getLong(KEY_USER_ID,0);
        String firstName = preferences.getString(KEY_FIRST_NAME,"");
        String lastName = preferences.getString(KEY_LAST_NAME,"");
        String email = preferences.getString(KEY_EMAIL,"");

        User user = new User(firstName, lastName, email, new Date());
        user.setId(id);

        return user;
    }
     */

    public void checkUserSession() {
        long id = preferences.getLong(KEY_USER_ID,0);
        if (id != 0) {
            Intent i = new Intent(ctx, MainActivity.class);
            ctx.startActivity(i);
            ((Activity) ctx).finish();
        }
    }

    public void register(User user) {
        Toast.makeText(ctx, String.format("Usuario %s registrado", user.getEmail()), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ctx, LoginActivity.class);
        ctx.startActivity(i);
    }

    public void login(String username, String password) {
        if (password.equals("123456")) {
            Toast.makeText(ctx, String.format(ctx.getResources().getString(R.string.activity_login_txt_toast_welcome), username), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ctx, MainActivity.class);
            ctx.startActivity(i);
            ((Activity) ctx).finish();
        } else {
            Toast.makeText(ctx, R.string.activity_login_txt_toast_wrong_password, Toast.LENGTH_SHORT).show();
        }
    }
}
