package com.example.toyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class ToyList extends AppCompatActivity {

    ArrayList<Toy> toy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toy_list);

        Database_Operations db = new Database_Operations(this);
        ListView lstCategories = findViewById(R.id.lstToys);

        toy = db.viewToys();

        ToyAdapter adapter = new ToyAdapter(this,toy);
        lstCategories.setAdapter(adapter);
    }
    public void makeOrder(View view){
        Intent intent = new Intent(ToyList.this,Signin.class);
        startActivity(intent);
    }

}