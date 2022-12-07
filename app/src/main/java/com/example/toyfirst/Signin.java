package com.example.toyfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signin extends AppCompatActivity {

    EditText txtUsernameSign, txtPasswordSign;
    Button btnSignIn;
    Database_Operations db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        txtUsernameSign = findViewById(R.id.txtUsernameSign);
        txtPasswordSign = findViewById(R.id.txtPasswordSign);

        btnSignIn = findViewById(R.id.btnSignIn);

        db = new Database_Operations(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = txtUsernameSign.getText().toString();
                String pass = txtPasswordSign.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(Signin.this, "FILL THE INFORMATION", Toast.LENGTH_SHORT).show();

                } else {
                    Boolean result = db.checkNamePass(user, pass);

                    if (result == true) {

                        Intent intent = new Intent(Signin.this, Order.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(Signin.this, " WRONG CREDENTIALS", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    public void viewRegister(View view){
        Intent intent = new Intent(Signin.this, Registration.class);
        startActivity(intent);
    }
}