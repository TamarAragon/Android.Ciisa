package com.example.AppLobsangBarriga.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.AppLobsangBarriga.dao.BmiDao;
import com.example.AppLobsangBarriga.lib.AppLobsangBarrigaDatabase;
import com.example.AppLobsangBarriga.models.Bmi;
import com.example.AppLobsangBarriga.models.BmiEntity;
import com.example.AppLobsangBarriga.models.BmiMapper;
import com.example.AppLobsangBarriga.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BmiController {
    private Context ctx;
    private BmiDao bmiDao;

    public BmiController(Context ctx) {
        this.ctx = ctx;
        this.bmiDao = AppLobsangBarrigaDatabase.getInstance(ctx).bmiDao();
    }

    public void register(Bmi bmi) {
        BmiMapper mapper = new BmiMapper(bmi);
        BmiEntity newBmi = mapper.toEntity();
        bmiDao.insert(newBmi);
        ((Activity) ctx).onBackPressed();
    }

    public void delete(long id) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    try {
                        bmiDao.delete(id);
                        ((Activity) ctx).onBackPressed();
                    } catch(Error error) {
                        error.printStackTrace();
                        Toast.makeText(this.ctx, "Error al eliminar la evaluacion", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this.ctx);
        builder.setMessage("Estas seguro de eliminar la tarea?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show();
    }

    public List<Bmi> getAll() {
        AuthController authController = new AuthController(ctx);
        User user = authController.getUserSession();
        List<BmiEntity> bmiEntityList = bmiDao.findAll(user.getId());
        List<Bmi> bmiList = new ArrayList<>();

        for(BmiEntity bmiEntity : bmiEntityList) {
            BmiMapper mapper = new BmiMapper(bmiEntity);
            Bmi bmi = mapper.toBase();
            bmiList.add(bmi);
        }

        return bmiList;
    }

    public List<Bmi> getRange(Date from, Date to) {
        AuthController authController = new AuthController(ctx);
        User user = authController.getUserSession();
        List<BmiEntity> bmiEntityList = bmiDao.findByRange(from, to, user.getId());
        List<Bmi> bmiList = new ArrayList<>();

        for(BmiEntity bmiEntity : bmiEntityList) {
            BmiMapper mapper = new BmiMapper(bmiEntity);
            Bmi bmi = mapper.toBase();
            bmiList.add(bmi);
        }

        return bmiList;
    }
}
