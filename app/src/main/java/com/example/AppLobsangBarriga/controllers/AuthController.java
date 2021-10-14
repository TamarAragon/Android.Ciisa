package com.example.AppLobsangBarriga.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.AppLobsangBarriga.LoginActivity;
import com.example.AppLobsangBarriga.MainActivity;
import com.example.AppLobsangBarriga.R;
import com.example.AppLobsangBarriga.dao.UserDao;
import com.example.AppLobsangBarriga.lib.AppLobsangBarrigaDatabase;
import com.example.AppLobsangBarriga.lib.BCrypt;
import com.example.AppLobsangBarriga.models.IUser;
import com.example.AppLobsangBarriga.models.User;
import com.example.AppLobsangBarriga.models.UserEntity;
import com.example.AppLobsangBarriga.models.UserMapper;

import java.util.Date;

public class AuthController {
    private final String KEY_USER_ID = "userId";
    private final String KEY_USERNAME = "userUsername";
    private final String KEY_FIRST_NAME = "userFirstName";
    private final String KEY_LAST_NAME = "userLastName";
    private final String KEY_EMAIL = "userEmail";

    private UserDao userDao;
    private Context ctx;
    private SharedPreferences preferences;
    private Object IUser; // to fix

    public AuthController(Context ctx) {
        this.ctx = ctx;
        int PRIVATE_MODE = 0;
        String PREF_NAME = "AppLobsangBarrigaPref";
        this.preferences = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.userDao = AppLobsangBarrigaDatabase.getInstance(ctx).userDao();

    }

    private void setUserSession(User user) {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putLong(KEY_USER_ID,user.getId());
        editor.putString(KEY_EMAIL,user.getEmail());
        editor.putString(KEY_FIRST_NAME,user.getFirstName());
        editor.putString(KEY_LAST_NAME,user.getLastName());
        editor.apply();
    }

    public User getUserSession() {
        long id = preferences.getLong(KEY_USER_ID,0);
        String username = preferences.getString(KEY_USERNAME, ""); // to fix
        String firstName = preferences.getString(KEY_FIRST_NAME,"");
        String lastName = preferences.getString(KEY_LAST_NAME,"");
        String email = preferences.getString(KEY_EMAIL,"");

        User user = new User(username, firstName, lastName, new Date(), 1.23, email); // to fix
        user.setId(id);

        return user;
    }

    public void checkUserSession() {
        long id = preferences.getLong(KEY_USER_ID,0);
        if (id != 0) {
            Intent i = new Intent(ctx, MainActivity.class);
            ctx.startActivity(i);
            ((Activity) ctx).finish();
        }
    }

    public void register(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        UserEntity userEntity = new UserMapper(user).toEntity(); // to fix

        userDao.insert(userEntity);

        Toast.makeText(ctx, String.format("Usuario %s registrado", user.getEmail()), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ctx, LoginActivity.class);
        ctx.startActivity(i);
    }

    public void login(String username, String password) {
        UserEntity userEntity = userDao.findByUsername(username);
        if (userEntity == null) {
            Toast.makeText(ctx, R.string.activity_login_txt_toast_user_not_found, Toast.LENGTH_SHORT).show();
        } else {
            User user = new UserMapper(userEntity).toBase();

            if (BCrypt.checkpw(password, user.getPassword())) {
                setUserSession(user);
                Toast.makeText(ctx, String.format(ctx.getResources().getString(R.string.activity_login_txt_toast_welcome), username), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ctx, MainActivity.class);
                ctx.startActivity(i);
                ((Activity) ctx).finish();
            } else {
                Toast.makeText(ctx, R.string.activity_login_txt_toast_wrong_password, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void logout() {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.clear();
        editor.apply();
        Toast.makeText(ctx, ctx.getResources().getString(R.string.activity_main_txt_logout), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(ctx, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
        ((Activity) ctx).finish();
    }
}
