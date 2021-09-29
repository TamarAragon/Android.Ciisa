package com.example.AppLobsangBarriga.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.AppLobsangBarriga.LoginActivity;
import com.example.AppLobsangBarriga.MainActivity;
import com.example.AppLobsangBarriga.R;
import com.example.AppLobsangBarriga.models.User;

public class AuthController {
    private Context ctx;

    public AuthController(Context ctx) {
        this.ctx = ctx;

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
