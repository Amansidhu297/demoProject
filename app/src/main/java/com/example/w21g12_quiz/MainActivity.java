package com.example.w21g12_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText username,password,repassword;
    Button register,signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // to show full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        username=findViewById(R.id.editTxtName);
        password=findViewById(R.id.editTxtPasswrd);
        repassword=findViewById(R.id.editTxtRetypePasswrd);
        register=findViewById(R.id.BtnRegister);
        signin=findViewById(R.id.BtnSignIn);

        DB=new DBHelper(this);
        register.setOnClickListener((View v)-> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();
            if (user.equals("") || pass.equals("") || repass.equals(""))
                Toast.makeText(MainActivity.this, "Please enter all input fields", Toast.LENGTH_SHORT).show();
            else {
                if (pass.equals(repass)) {
                    Boolean checkuser = DB.checkusername((user));
                    if (checkuser == false) {
                        Boolean insert = DB.insertData(user, pass);
                        if (insert == true) {
                            Toast.makeText(this, "Registered sucessfully..." +
                                    "", Toast.LENGTH_SHORT).show();
                            Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
                        } else {
                            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }



        });
        signin.setOnClickListener((View v) ->{
            Intent myIntent=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(myIntent);

        });
    }
}