package com.example.dannykamo.shopapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dannykamo.shopapp.data.DBHelper;

public class AddProductActivity extends AppCompatActivity {
   private EditText txtUserId,txtProdName,txtProdDesc;
   private Button btnAddProd;
   private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        txtUserId=(EditText)findViewById(R.id.txt_user_id_reg);
        txtProdName=(EditText)findViewById(R.id.pro_name_reg);
        txtProdDesc=(EditText)findViewById(R.id.pro_desc_reg);
        btnAddProd=(Button)findViewById(R.id.btn_add_product);

        dbHelper=new DBHelper(this);
        btnAddProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct(txtUserId,txtProdName,txtProdDesc);
            }
        });
    }
    private void addProduct(EditText id,EditText name,EditText desc){
        if(!TextUtils.isEmpty(id.getText()) || !TextUtils.isEmpty(name.getText()) || !TextUtils.isEmpty(desc.getText())){
           long added= dbHelper.addProduct(Integer.parseInt(id.getText().toString()),name.getText().toString(),desc.getText().toString());
            if(added>0){
                Toast.makeText(this, "new product added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
            }
            else {
                Toast.makeText(this, "Product not added", Toast.LENGTH_SHORT).show();

            }
        }
        else{
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        }
    }
}
