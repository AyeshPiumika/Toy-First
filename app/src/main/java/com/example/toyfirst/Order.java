package com.example.toyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Order extends AppCompatActivity {

    EditText txtNICNO, txtFullName, txtAddress, txtContactNO;

    Database_Operations db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        txtNICNO = findViewById(R.id.txtNICNO);
        txtFullName = findViewById(R.id.txtFullName);
        txtAddress = findViewById(R.id.txtAddress);
        txtContactNO = findViewById(R.id.txtContactNO);

        db = new Database_Operations(this);
    }

    public void insertOrder(View view){
        try{
            Ordering order = new Ordering();
            order.setNICNO(txtNICNO.getText().toString());
            order.setFullName(txtFullName.getText().toString());
            order.setPAddress(txtAddress.getText().toString());
            order.setContactNO(txtContactNO.getText().toString());
            try {
                if(db.insertOrders(order)>0){
                    Toast.makeText(this,"Ordering Process Success!",Toast.LENGTH_SHORT);
                    clear();
                    Intent intent = new Intent(Order.this, Home.class);
                    startActivity(intent);
                }
            }catch (Exception ex){
                Log.e("Error -> ", ex.getMessage());
            }
        }catch (Exception e){
            Toast.makeText(this, "Please provide all the details", Toast.LENGTH_SHORT).show();
        }

    }

    public void clear(){
        txtNICNO.setText(null);
        txtFullName.setText(null);
        txtAddress.setText(null);
        txtContactNO.setText(null);
    }

}