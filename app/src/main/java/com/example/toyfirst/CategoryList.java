package com.example.toyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryList extends AppCompatActivity {

    ArrayList<Category> category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        Database_Operations db = new Database_Operations(this);
        ListView lstCategories = findViewById(R.id.lstCategories);

        category = db.viewCategory();

        CategoryAdapter adapter = new CategoryAdapter(this,category);
        lstCategories.setAdapter(adapter);
    }
}