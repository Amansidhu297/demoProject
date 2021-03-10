package com.example.w21g12_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText username,password;
    Button btnlogin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.signInUserName);
        password=findViewById(R.id.signInPasswrd);
        btnlogin=findViewById(R.id.SignInBtn);

        DB=new DBHelper(this);
        btnlogin.setOnClickListener((View v)-> {

            String user=username.getText().toString();
            String pass=password.getText().toString();
            if(user.equals("")||pass.equals(""))
                Toast.makeText(this, "Please fill all input fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                if (checkuserpass == true) {
                    Toast.makeText(this, "User Sign In sucessfully", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(this, QuizListActivity.class);
                    startActivity(myIntent);
                } else {
                    Toast.makeText(this, "Invalid User name and password", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}