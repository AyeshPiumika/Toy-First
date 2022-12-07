package com.example.toyfirst;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ToyAdapter extends BaseAdapter {
    TextView lblTID, lblTName, lblTCategory, lblTPrice;
    ImageView imgToyCustom;
    //Button btnOrderNow;

    Context context;
    ArrayList<Toy> toy;

    public ToyAdapter(Context context, ArrayList<Toy> toy) {
        this.context = context;
        this.toy = toy;
    }

    @Override
    public int getCount() {
        return toy.size();
    }

    @Override
    public Object getItem(int position) {
        return toy.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.customtoyview, parent, false);

        lblTID = view1.findViewById(R.id.lblTID);
        lblTName = view1.findViewById(R.id.lblTName);
        lblTCategory = view1.findViewById(R.id.lblTCategory);
        lblTPrice = view1.findViewById(R.id.lblTPrice);
        imgToyCustom = view1.findViewById(R.id.imgToyCustom);
        //btnOrderNow = view1.findViewById(R.id.btnOrderNow);

        Toy toys = toy.get(position);
        lblTID.setText("" + toys.getTID());
        lblTName.setText("" + toys.getTName());
        lblTCategory.setText("" + toys.getTCategory());
        lblTPrice.setText("" + toys.getTPrice());

        Bitmap bitmap = BitmapFactory.decodeByteArray(toys.getTImg(), 0, toys.getTImg().length);
        imgToyCustom.setImageBitmap(bitmap);

        return view1;

    }

}
