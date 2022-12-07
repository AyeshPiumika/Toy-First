package com.example.toyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText txtEMail, txtUName, txtUsername, txtPassword;
    Database_Operations db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        txtEMail = findViewById(R.id.txtEMail);
        txtUName = findViewById(R.id.txtUName);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        db = new Database_Operations(this);
    }

    //Register now
    public void RegisterNow(View view){
        User user = new User();
        user.setMail(txtEMail.getText().toString());
        user.setUName(txtUName.getText().toString());
        user.setUsername(txtUsername.getText().toString());
        user.setPassword(txtPassword.getText().toString());
        try {
            if(db.insertRegisteringDetails(user)>0){
                Toast.makeText(this,"User Registration Completed!!",Toast.LENGTH_SHORT);
                clear();
            }
        }catch (Exception ex){
            Log.e("Error -> ", ex.getMessage());
        }
    }

    //Clear fields after entering data
    public void clear(){
        txtEMail.setText(null);
        txtUName.setText(null);
        txtUsername.setText(null);
        txtPassword.setText(null);
    }

    public void viewUserSignInPage(View view){
        Intent intent = new Intent(Registration.this, Signin.class);
        startActivity(intent);
    }
}