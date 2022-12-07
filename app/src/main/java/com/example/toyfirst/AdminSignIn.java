package com.example.toyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminSignIn extends AppCompatActivity {

    EditText txtAdminUsername, txtAdminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_in);

        txtAdminUsername = findViewById(R.id.txtAdminUsername);
        txtAdminPassword = findViewById(R.id.txtAdminPassword);

    }

    public void GoToCategoryPart (View view){
        if(txtAdminUsername.getText().toString().equals("Admin123")&&(txtAdminPassword.getText().toString().equals("admin321"))) {
            //if((txtAdminsUsername.equals("Admin123")) && (txtAdminPassword.equals("admin321"))) {
            Intent intent = new Intent(AdminSignIn.this, CategoryHandling.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "You can't access this. Please try again!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void GoToToysHandling (View view){
        if(txtAdminUsername.getText().toString().equals("Admin123")&&(txtAdminPassword.getText().toString().equals("admin321"))) {
//        if((txtAdminsUsername.equals("Admin123")) && (txtAdminPassword.equals("admin321"))) {
            Intent intent = new Intent(AdminSignIn.this,ManageToys.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "You can't access this. Please try again!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void ViewUserOrders (View view){
        if(txtAdminUsername.getText().toString().equals("Admin123")&&(txtAdminPassword.getText().toString().equals("admin321"))) {
//        if((txtAdminsUsername.equals("Admin123")) && (txtAdminPassword.equals("admin321"))) {
            Intent intent = new Intent(AdminSignIn.this,OrderList.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "You can't access this. Please try again!!!", Toast.LENGTH_SHORT).show();
        }
    }


    public void goToHome(View view){
        Intent intent = new Intent(AdminSignIn.this, Home.class);
        startActivity(intent);
    }
}