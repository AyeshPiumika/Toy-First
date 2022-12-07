package com.example.toyfirst;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {
    TextView lblNicNO, lblFullName, lblAddress, lblConNO;

    Context context;
    ArrayList<Ordering> orderings;

    public OrderAdapter(Context context, ArrayList<Ordering> orderings) {
        this.context = context;
        this.orderings = orderings;
    }

    @Override
    public int getCount() {
        return orderings.size();
    }

    @Override
    public Object getItem(int position) {
        return orderings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.customoderview,parent,false);

        lblNicNO= view1.findViewById(R.id.lblNicNO);
        lblFullName= view1.findViewById(R.id.lblFullName);
        lblAddress= view1.findViewById(R.id.lblAddress);
        lblConNO= view1.findViewById(R.id.lblConNO);

        Ordering ordering = orderings.get(position);
        lblNicNO.setText(""+ordering.getNICNO());
        lblFullName.setText(""+ordering.getFullName());
        lblAddress.setText(""+ordering.getPAddress());
        lblConNO.setText(""+ordering.getContactNO());

        return view1;
    }
}
