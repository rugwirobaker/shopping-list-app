package com.example.dannykamo.shopapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dannykamo.shopapp.data.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {



    // UI references.
    private EditText mEmail;
    private EditText mPassword;
    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmail = (EditText) findViewById(R.id.email_login);

        mPassword = (EditText) findViewById(R.id.txt_pass_login);

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        Button btnRegFromLogin=(Button)findViewById(R.id.btn_reg_from_login);
        btnRegFromLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helper.isNotFirstTime()){
                    Toast.makeText(LoginActivity.this, "You are already registered", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                }

            }
        });
        helper=new DBHelper(this);
    }
    private void login() {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
         try {
             if (!TextUtils.isEmpty(password) || !TextUtils.isEmpty(email)) {
                 if(helper.login(email,password)){
                     startActivity(new Intent(this,MainActivity.class));
                 }
                 else {
                     Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_LONG).show();
                 }
             }
             else{
                 Toast.makeText(this, "Email and password are required", Toast.LENGTH_LONG).show();
             }
         }catch (Exception e){
             Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
         }

    }


}

