 package com.example.dannykamo.shopapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannykamo.shopapp.adapter.ProductAdapter;
import com.example.dannykamo.shopapp.data.DBHelper;
import com.example.dannykamo.shopapp.items.ProdItem;

import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity {
   private DBHelper dbHelper;
   private RecyclerView recProductList;
   private ArrayList<ProdItem> prodItems;
   private TextView txtNoProdcut;
   private ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recProductList=(RecyclerView)findViewById(R.id.rec_prod_list);
        txtNoProdcut=(TextView)findViewById(R.id.txt_no_prod);
        dbHelper=new DBHelper(this);
        try {
            //Bind data to views
             prodItems=dbHelper.getAllProducts();
            if (prodItems.size()>0) {
                txtNoProdcut.setText(getString(R.string.you_have)+" "+prodItems.size()+" "+ getString(R.string.p_message));
                adapter = new ProductAdapter(prodItems, this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this, 1, false);
                recProductList.setLayoutManager(layoutManager);
                recProductList.setHasFixedSize(true);
                recProductList.setAdapter(adapter);
            }
        }catch (Exception e){
            Toast.makeText(this, "In main activity: "+e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mnu_prod,menu);
         MenuItem itemNewProduct=menu.findItem(R.id.item_new_product_1);
         itemNewProduct.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
             @Override
             public boolean onMenuItemClick(MenuItem item) {
                 startActivity(new Intent(MainActivity.this,AddProductActivity.class));
                 finish();
                 return true;
             }
         });
         return true;
     }

     @Override
     public void onBackPressed() {
         super.onBackPressed();
         startActivity(new Intent(MainActivity.this,LoginActivity.class));
     }
 }
