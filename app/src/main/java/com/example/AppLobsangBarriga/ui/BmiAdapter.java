package com.example.AppLobsangBarriga.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.AppLobsangBarriga.R;
import com.example.AppLobsangBarriga.models.Bmi;

import java.util.List;

// BmiAdapter makes it so the bmi list works as intended

public class BmiAdapter extends BaseAdapter {
    private Context ctx;
    private List<Bmi> bmiList;

    public BmiAdapter(Context ctx, List<Bmi> bmiList) {
        this.ctx = ctx;
        this.bmiList = bmiList;
    }

    @Override
    public int getCount() {
        return bmiList.size();
    }

    @Override
    public Object getItem(int i) {
        return bmiList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return bmiList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);

        view = inflater.inflate(R.layout.item_bmi, null);

        Bmi bmi = bmiList.get(i);

        TextView tvId = view.findViewById(R.id.item_bmi_lv_id);
        TextView tvDate = view.findViewById(R.id.item_bmi_lv_date);
        TextView tvWeight = view.findViewById(R.id.item_bmi_lv_weight);
        TextView tvBmi = view.findViewById(R.id.item_bmi_lv_bmi);

        tvId.setText(Long.toString(bmi.getId()));
        tvDate.setText(bmi.getDate());
        tvWeight.setText(bmi.getWeight());
        tvBmi.setText(bmi.getCalculatedBmi());

        return view;
    }
}
