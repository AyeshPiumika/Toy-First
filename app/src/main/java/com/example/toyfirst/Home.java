package com.example.toyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void viewContactUsPage(View view){
        Intent intent = new Intent(Home.this, ContactUs.class);
        startActivity(intent);
    }

    public void goToCategoryList(View view){
        Intent intent = new Intent(Home.this, CategoryList.class);
        startActivity(intent);
    }

    public void goToToyList(View view){
        Intent intent = new Intent(Home.this, ToyList.class);
        startActivity(intent);
    }

    public void viewAdminSignInPage(View view){
        Intent intent = new Intent(Home.this, AdminSignIn.class);
        startActivity(intent);
    }

    public void viewLocationPage(View view){
        Intent intent = new Intent(Home.this, MapsActivity.class);
        startActivity(intent);
    }
}