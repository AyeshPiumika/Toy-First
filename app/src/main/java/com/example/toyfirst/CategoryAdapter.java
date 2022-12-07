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

public class CategoryAdapter extends BaseAdapter {
    TextView lblCID, lblCName, lblCDetails;
    ImageView CustomImg;

    Context context;
    ArrayList<Category> category;

    public CategoryAdapter(Context context, ArrayList<Category> category) {
        this.context = context;
        this.category = category;
    }

    @Override
    public int getCount() {
        return category.size();
    }

    @Override
    public Object getItem(int position) {
        return category.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.customcategoryview,parent,false);

        lblCID= view1.findViewById(R.id.lblCID);
        lblCName= view1.findViewById(R.id.lblCName);
        lblCDetails= view1.findViewById(R.id.lblCDetails);
        CustomImg= view1.findViewById(R.id.CustomImg);

        Category categories = category.get(position);
        lblCID.setText(""+categories.getCID());
        lblCName.setText(""+categories.getCategoryName());
        lblCDetails.setText(""+categories.getCDetails());

        Bitmap bitmap = BitmapFactory.decodeByteArray(categories.getCImg(),0,categories.getCImg().length);
        CustomImg.setImageBitmap(bitmap);

        return view1;
    }
}
