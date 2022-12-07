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

public class CategoryHandling extends AppCompatActivity {

    EditText txtCatName, txtCatDetails, txtCID;
    ImageView image;

    Database_Operations db;
    byte[] imageByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_handling);

        txtCatName = findViewById(R.id.txtCatName);
        txtCatDetails = findViewById(R.id.txtCatDetails);
        txtCID = findViewById(R.id.txtCID);
        image = findViewById(R.id.imgCat);

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

    public void insertCategory(View view){
        try {
            Category category = new Category();
            category.setCategoryName(txtCatName.getText().toString());
            category.setCDetails(txtCatDetails.getText().toString());
            category.setCImg(imageByte);
            try {
                if(db.insertCategory(category)>0){
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

    //Clear fields after entering data
    public void clear(){
        txtCatName.setText(null);
        txtCatDetails.setText(null);
        txtCID.setText(null);
        image.setImageBitmap(null);
    }

    //Update Categories
    public void updateCategory(View view){
        try{
            Category category = new Category();

            category.setCID(Integer.parseInt(txtCID.getText().toString()));
            category.setCategoryName(txtCatName.getText().toString());
            category.setCDetails(txtCatDetails.getText().toString());
            category.setCImg(imageByte);

            try {
                db.updateCategory(category);
                Toast.makeText(this,"Products Updated!",Toast.LENGTH_SHORT);
                clear();
            }catch (Exception ex){
                Log.e("Error -> ", ex.getMessage());
            }
        }catch (Exception e){
        Toast.makeText(this, "Please provide all the details", Toast.LENGTH_SHORT).show();
        }

    }

    //Category list find part
    public void findCategory(View view){
        try{
            Category category = new Category();
            category.setCID(Integer.parseInt(txtCID.getText().toString()));
            try {
                category =db.findCategory(category);
                if(category != null){
                    txtCatName.setText(category.getCategoryName());
                    txtCatDetails.setText(category.getCDetails());
                    //image.setImageURI(category.getCImg());
                    Bitmap bitmap= BitmapFactory.decodeByteArray(category.getCImg(),0,category.getCImg().length);
                    image.setImageBitmap(bitmap);
                }else {
                    Toast.makeText(this,"Category Not Found!",Toast.LENGTH_SHORT);
                    clear();
                }
            }catch (Exception ex){
                Log.e("Error -> ", ex.getMessage());
            }
        }catch (Exception e){
            Toast.makeText(this, "Please provide all the details", Toast.LENGTH_SHORT).show();
        }

    }

    //Delete Categories
    public void deleteCategories(View view){
        try{
            Category category = new Category();
            category.setCID(Integer.parseInt(txtCID.getText().toString()));

            try {
                db.deleteCategory(category);
                Toast.makeText(this,"Product Deleted!",Toast.LENGTH_SHORT);
                clear();
            }catch (Exception ex){
                Log.e("Error -> ", ex.getMessage());
            }
        }catch (Exception e){
            Toast.makeText(this, "Please provide all the details", Toast.LENGTH_SHORT).show();
        }

    }

    //View Categories
    public void viewAllCategories(View view){
        Intent intent = new Intent(CategoryHandling.this,CategoryList.class);
        startActivity(intent);
    }

}