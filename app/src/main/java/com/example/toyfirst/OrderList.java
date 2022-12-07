package com.example.toyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderList extends AppCompatActivity {

    ArrayList<Ordering> orderings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        Database_Operations db = new Database_Operations(this);
        ListView lstOrders = findViewById(R.id.lstOrders);

        orderings = db.viewOrders();

        OrderAdapter adapter = new OrderAdapter(this,orderings);
        lstOrders.setAdapter(adapter);
    }
}