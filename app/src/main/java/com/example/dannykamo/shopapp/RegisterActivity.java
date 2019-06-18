package com.example.dannykamo.shopapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dannykamo.shopapp.data.DBHelper;

public class RegisterActivity extends AppCompatActivity {
 private EditText txtFullName,txtEmail,txtPassword;
 private Button btnReg;
 private DBHelper helper;
 private Button btnLoginFromReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        helper=new DBHelper(this);
        if(helper.isNotFirstTime()){
            startActivity(new Intent(this,LoginActivity.class));
        }
        else{
            txtFullName=(EditText)findViewById(R.id.names_reg);
            txtEmail=(EditText)findViewById(R.id.email_reg);
            txtPassword=(EditText)findViewById(R.id.password_reg);
            btnReg=(Button)findViewById(R.id.btn_reg);
            btnReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    register(txtFullName,txtEmail,txtPassword);

                }
            });
            btnLoginFromReg= (Button) findViewById(R.id.btn_login_reg);
            btnLoginFromReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }
            });
        }

    }
    private void register(EditText names,EditText email,EditText password){

        if(names.getText().toString().equals("") || email.getText().toString().equals("")
                || password.getText().toString().equals("")){
            Toast.makeText(this, "Fill all fields!", Toast.LENGTH_LONG).show();
        }else {
            long done = helper.register(names.getText().toString(), email.getText().toString(), password.getText().toString());
            if (done > 0) {
                Toast.makeText(this, "You're successfully registered!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,MainActivity.class));
            } else {
                Toast.makeText(this, "Not registered!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
