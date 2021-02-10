package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private Button eLogin;
    private static String Username = "din_djarin";
    private static String Password = "baby_yoda_ftw";


    public static String getUsername() {
        return Username;
    }

    public static String getPassword() {
        return Password;
    }


    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = eName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();

                }else{
                    isValid = validate(inputName, inputPassword);
                    if(!isValid){
                        //Toast.makeText(MainActivity.this, "Incorrect credentials Entered", Toast.LENGTH_SHORT).show();
                        ePassword.setError("Invalid password");
                    }else{
                        //Add the code to go to new activity
//                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
//                        startActivity(intent);
                        nextActivity(v);
                    }
                }

            }
        });
    }

    private boolean validate(String name, String password){
        if(name.equals(Username) && password.equals(Password)){
            return true;
        }
        return false;
    }

    public void nextActivity(View view){
        Intent intent = HomePageActivity.getIntent(getApplicationContext(), "Welcome!! din_djarin" );
        startActivity(intent);
    }
}