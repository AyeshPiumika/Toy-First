package com.example.toyfirst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {

    EditText txtPhoneNum, txtMessage;
    Button sendMessage;
    TextView lblCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        sendMessage = findViewById(R.id.btnSendMessage);
        txtPhoneNum = findViewById(R.id.txtPhoneNum);
        txtMessage = findViewById(R.id.txtMessage);
        lblCount = findViewById(R.id.lblCount);

        txtMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lblCount.setText(charSequence.length()+"/160 left");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void sendSMS(View view){
        String number = txtPhoneNum.getText().toString();
        String msg = txtMessage.getText().toString();

        SmsManager smsManager = SmsManager.getDefault();

        if(Build.VERSION.SDK_INT >= 23){
            if(ContextCompat.checkSelfPermission(ContactUs.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.SEND_SMS},999);
            }
        }

        smsManager.sendTextMessage(number, null, msg, null, null);

        Toast.makeText(this, "SMS SENT", Toast.LENGTH_SHORT).show();

    }
}