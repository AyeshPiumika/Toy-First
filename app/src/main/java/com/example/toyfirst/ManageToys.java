package com.example.toyfirst;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ManageToys extends AppCompatActivity {

    EditText txtToyName, txtToyCategory, txtToyPrice, txtTID;
    ImageView image;

    Database_Operations db;
    byte[] imageByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_toys);

        txtToyName = findViewById(R.id.txtToyName);
        txtToyCategory = findViewById(R.id.txtToyCategory);
        txtToyPrice = findViewById(R.id.txtToyPrice);
        txtTID = findViewById(R.id.txtTID);
        image = findViewById(R.id.imgToy);

        db = new Database_Operations(this);
    }

    public void selectImage(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop","true");
        intent.putExtra("aspectX",0);
        intent.putExtra("aspectY",0);
        intent.putExtra("outputX",150);
        intent.putExtra("outputY",150);
        intent.putExtra("return-data", "true");
        startActivityForResult(Intent.createChooser(intent,"SELECT PRODUCT IMAGE"), 111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 111){
            if(data != null){
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.PNG,0,arrayOutputStream);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,0,arrayOutputStream);
                    imageByte = arrayOutputStream.toByteArray();
                    image.setImageBitmap(bitmap);
                }catch (IOException ex){
                    Log.e("Error -> ", ex.getMessage());
                }
            }
        }
    }

    public void insertToy(View view){
        try{
            Toy toy = new Toy();
            toy.setTName(txtToyName.getText().toString());
            toy.setTCategory(txtToyCategory.getText().toString());
            toy.setTPrice(txtToyPrice.getText().toString());
            toy.setTImg(imageByte);
            try {
                if(db.insertToys(toy)>0){
                    Toast.makeText(this,"Product Inserted!",Toast.LENGTH_SHORT);
                    clear();
                }
            }catch (Exception ex){
                Log.e("Error -> ", ex.getMessage());
            }
        }catch (Exception e){
            Toast.makeText(this, "Please provide all the details", Toast.LENGTH_SHORT).show();
        }

    }

    public void clear(){
        txtTID.setText(null);
        txtToyName.setText(null);
        txtToyCategory.setText(null);
        txtToyPrice.setText(null);
        image.setImageBitmap(null);
    }

    public void updateToy(View view){
        try{
            Toy toy = new Toy();

            toy.setTID(Integer.parseInt(txtTID.getText().toString()));
            toy.setTName(txtToyName.getText().toString());
            toy.setTCategory(txtToyCategory.getText().toString());
            toy.setTPrice(txtToyPrice.getText().toString());
            toy.setTImg(imageByte);

            try {
                db.updateToys(toy);
                Toast.makeText(this,"Products Updated!",Toast.LENGTH_SHORT);
                clear();
            }catch (Exception ex){
                Log.e("Error -> ", ex.getMessage());
            }
        }catch (Exception e){
            Toast.makeText(this, "Please provide all the details", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteToy(View view){
        try{
            Toy toy = new Toy();
            toy.setTID(Integer.parseInt(txtTID.getText().toString()));

            try {
                db.deleteToys(toy);
                Toast.makeText(this,"Product Deleted!",Toast.LENGTH_SHORT);
                clear();
            }catch (Exception ex){
                Log.e("Error -> ", ex.getMessage());
            }
        }catch (Exception e){
            Toast.makeText(this, "Please provide all the details", Toast.LENGTH_SHORT).show();
        }

    }

    public void findToy(View view){
        try{
            Toy toy = new Toy();
            toy.setTID(Integer.parseInt(txtTID.getText().toString()));
            try {
                toy =db.findToys(toy);
                if(toy != null){
                    txtToyName.setText(toy.getTName());
                    txtToyCategory.setText(toy.getTCategory());
                    txtToyPrice.setText(toy.getTPrice());
                    //image.setImageURI(category.getCImg());
                    Bitmap bitmap= BitmapFactory.decodeByteArray(toy.getTImg(),0,toy.getTImg().length);
                    image.setImageBitmap(bitmap);
                }else {
                    Toast.makeText(this,"Toy Not Found!",Toast.LENGTH_SHORT);
                    clear();
                }
            }catch (Exception ex){
                Log.e("Error -> ", ex.getMessage());
            }
        }catch (Exception e){
            Toast.makeText(this, "Please provide all the details", Toast.LENGTH_SHORT).show();
        }

    }

    public void viewAllToys(View view){
        Intent intent = new Intent(ManageToys.this,ToyList.class);
        startActivity(intent);
    }
}