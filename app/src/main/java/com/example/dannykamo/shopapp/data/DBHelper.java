package com.example.dannykamo.shopapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.dannykamo.shopapp.items.ProdItem;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    Context mContext;

    public DBHelper(Context context) {

        super(context, "test.db", null, 1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
			/* There you can create tables */
            db.execSQL("CREATE TABLE IF NOT EXISTS products("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "'user_id' INTEGER, 'prod_name' TEXT, 'prod_desc' TEXT);");

            db.execSQL("CREATE TABLE IF NOT EXISTS users("
                    + "user_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "'full_name' TEXT, 'email' TEXT, 'password' TEXT);");
        } catch (Exception ex) {
            Toast.makeText(mContext,
                    "Error on create: " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {

            db.execSQL("DROP TABLE IF EXISTS products;");
            db.execSQL("DROP TABLE IF EXISTS users;");
        } catch (Exception ex) {
            Toast.makeText(mContext,
                    "Error on upgrade: " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
    public ArrayList<ProdItem> getAllProducts() {
        ArrayList<ProdItem> prodItems = new ArrayList<ProdItem>();
        SQLiteDatabase sqldg = this.getReadableDatabase();
        Cursor cur = sqldg.rawQuery("select * from products" , null);
        if (cur != null) {

            while (cur.moveToNext()) {
                ProdItem p = new ProdItem(cur.getInt(1), cur.getString(2), cur.getString(3));
                prodItems.add(p);
            }
        }
        return prodItems;
    }
    public long addProduct(int userId,String pName,String pDesc){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("user_id", userId);
        values.put("prod_name", pName);
        values.put("prod_desc", pDesc);

        long rowInserted=db.insert("products", null, values);
        return rowInserted;
    }
    public long register(String name,String email,String password){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("full_name", name);
        values.put("email", email);
        values.put("password", password);

        long rowInserted=db.insert("users", null, values);
        return rowInserted;
    }
    public boolean login(String email,String password){
        SQLiteDatabase sqldg = this.getReadableDatabase();
        boolean isUserExist=false;
        Cursor cur = sqldg.rawQuery("select * from users where email='"+email+"' and password='"+password+"' ", null);
        if (cur != null && cur.moveToNext()) {
         isUserExist=true;
        }
        return isUserExist;
    }
    public boolean isNotFirstTime(){
        SQLiteDatabase sqldg = this.getReadableDatabase();
        boolean isUserExist=false;
        Cursor cur = sqldg.rawQuery("select * from users", null);
        if (cur != null && cur.moveToNext()) {
            isUserExist=true;
        }
        return isUserExist;
    }
}
