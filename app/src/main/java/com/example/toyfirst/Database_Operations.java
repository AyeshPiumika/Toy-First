package com.example.toyfirst;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database_Operations extends SQLiteOpenHelper {
    public Database_Operations(@Nullable Context context) {
        super(context, "db_ToyFirst3", null, 1);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //User registering part
        String sql = "CREATE TABLE tblUser(Username VARCHAR(100) PRIMARY KEY, E_mail VARCHAR(100), Name VARCHAR(100), Password VARCHAR(100))";
        sqLiteDatabase.execSQL(sql);

        //Category list create table part
        String sql1 = "CREATE TABLE tblCategory(CID INTEGER PRIMARY KEY AUTOINCREMENT, CategoryName VARCHAR(100), CDetails VARCHAR(200), CImg BLOG)";
        sqLiteDatabase.execSQL(sql1);

        //Toy list create table part
        String sql2 = "CREATE TABLE tblToy(TID INTEGER PRIMARY KEY AUTOINCREMENT, TName VARCHAR(100), TCategory VARCHAR(200), TPrice VARCHAR(100), TImg BLOG)";
        sqLiteDatabase.execSQL(sql2);

        //Ordering process
        String sql3 = "CREATE TABLE tblOrder(TID INTEGER PRIMARY KEY AUTOINCREMENT, NICNO VARCHAR(100), FullName VARCHAR(200), PAddress VARCHAR(100), ContactNO VARCHAR(100))";
        sqLiteDatabase.execSQL(sql3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //User registering part
        String sql = "DROP TABLE IF EXISTS tblUser";
        sqLiteDatabase.execSQL(sql);

        //Category List part
        String sql1 = "DROP TABLE IF EXISTS tblCategory";
        sqLiteDatabase.execSQL(sql1);

        //Category List part
        String sql2 = "DROP TABLE IF EXISTS tblToy";
        sqLiteDatabase.execSQL(sql2);

        //Ordering process
        String sql3 = "DROP TABLE IF EXISTS tblOrder";
        sqLiteDatabase.execSQL(sql3);

    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //User registering - details inserting part (Registering part)
    public long insertRegisteringDetails(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", user.getUsername());
        values.put("E_mail", user.getMail());
        values.put("Name", user.getUName());
        values.put("Password", user.getPassword());

        return db.insert("tblUser",null,values);
    }

    //Inserted data validating part
    public Boolean checkNamePass(String NAME, String PASS) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select*from tblUser where Username = ? and Password = ?", new String[]{NAME,PASS});
        if (cursor.getCount() > 0) {

            return true;
        } else {
            return false;
        }
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //Category list record insert part
    public long insertCategory(Category category){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CategoryName", category.getCategoryName());
        values.put("CDetails", category.getCDetails());
        values.put("CImg", category.getCImg());

        return db.insert("tblCategory",null,values);
    }

    //Category list view part
    public ArrayList<Category> viewCategory(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM tblCategory";

        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<Category> category = new ArrayList<>();
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                Category category1 = new Category();
                category1.setCID(cursor.getInt(0));
                category1.setCategoryName(cursor.getString(1));
                category1.setCDetails(cursor.getString(2));
                category1.setCImg(cursor.getBlob(3));

                category.add(category1);
            }
        }else{
            category = null;
        }
        return category;
    }

    //Category list update part
    public void updateCategory(Category c){
        String sql = "UPDATE tblCategory SET CategoryName='"+c.getCategoryName()+"', CDetails='"+c.getCDetails()+"', CImg='"+c.getCImg()+"' WHERE CID="+c.getCID();
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);

    }

    //Category list delete part=
    public void deleteCategory(Category c){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM tblCategory WHERE CID="+c.getCID();
        db.execSQL(sql);
    }

    //Category list find part
    public Category findCategory(Category c){
        String sql = "SELECT * FROM tblCategory WHERE CID="+c.getCID();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        Category category = new Category();

        if(count != 0){
            if(cursor.moveToFirst()){
                category.setCID(cursor.getInt(cursor.getColumnIndexOrThrow("CID")));
                category.setCategoryName(cursor.getString(cursor.getColumnIndexOrThrow("CategoryName")));
                category.setCDetails(cursor.getString(cursor.getColumnIndexOrThrow("CDetails")));
                category.setCImg(cursor.getBlob(cursor.getColumnIndexOrThrow("CImg")));
            }
        }else{
            category = null;
        }
        return category;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public long insertToys(Toy toy){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TName", toy.getTName());
        values.put("TCategory", toy.getTCategory());
        values.put("TPrice ", toy.getTPrice());
        values.put("TImg", toy.getTImg());

        return db.insert("tblToy",null,values);
    }

    public void updateToys(Toy c){
        String sql = "UPDATE tblToy SET TName='"+c.getTName()+"', TCategory='"+c.getTCategory()+"', TPrice='"+c.getTPrice()+"', TImg='"+c.getTImg()+"' WHERE TID="+c.getTID();
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public void deleteToys(Toy c){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM tblToy WHERE TID="+c.getTID();
        db.execSQL(sql);
    }

    public Toy findToys(Toy c){
        String sql = "SELECT * FROM tblToy WHERE TID="+c.getTID();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        Toy toy = new Toy();

        if(count != 0){
            if(cursor.moveToFirst()){
                toy.setTID(cursor.getInt(cursor.getColumnIndexOrThrow("TID")));
                toy.setTName(cursor.getString(cursor.getColumnIndexOrThrow("TName")));
                toy.setTCategory(cursor.getString(cursor.getColumnIndexOrThrow("TCategory")));
                toy.setTPrice(cursor.getString(cursor.getColumnIndexOrThrow("TPrice")));
                toy.setTImg(cursor.getBlob(cursor.getColumnIndexOrThrow("TImg")));
            }
        }else{
            toy = null;
        }
        return toy;
    }

    public ArrayList<Toy> viewToys(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM tblToy";

        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<Toy> toy = new ArrayList<>();
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                Toy toys = new Toy();
                toys.setTID(cursor.getInt(0));
                toys.setTName(cursor.getString(1));
                toys.setTCategory(cursor.getString(2));
                toys.setTPrice(cursor.getString(3));
                toys.setTImg(cursor.getBlob(4));

                toy.add(toys);
            }
        }else{
            toy = null;
        }
        return toy;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //Ordering Process - insert

    public long insertOrders(Ordering order){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NICNO", order.getNICNO());
        values.put("FullName", order.getFullName());
        values.put("PAddress", order.getPAddress());
        values.put("ContactNO ", order.getContactNO());

        return db.insert("tblOrder",null,values);
    }

    public ArrayList<Ordering> viewOrders(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM tblOrder";

        Cursor cursor = db.rawQuery(sql,null);
        ArrayList<Ordering> order = new ArrayList<>();
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                Ordering ordering = new Ordering();
                ordering.setNICNO(cursor.getString(0));
                ordering.setFullName(cursor.getString(1));
                ordering.setPAddress(cursor.getString(2));
                ordering.setContactNO(cursor.getString(3));

                order.add(ordering);
            }
        }else{
            order = null;
        }
        return order;
    }

}
