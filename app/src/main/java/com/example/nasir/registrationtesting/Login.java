package com.example.nasir.registrationtesting;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    //Declare Database
    SqliteDB myDatabaseHelper;

    //Declare Buttons
    private Button register;
    private Button login;

    //Declare EditText
    private EditText userName;
    private EditText password;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialized EditText
        userName = (EditText)findViewById(R.id.loginUserName);
        password = (EditText)findViewById(R.id.loginPassword);

        //Initialized a button
        register = (Button)findViewById(R.id.btnRegister);
        login = (Button)findViewById(R.id.btnLogin);

        //Initialized Database
        myDatabaseHelper = new SqliteDB(this);

        //Call Login method
        Login();

        //Call Register method
        Register();
    }
    public void Register(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                startActivity(new Intent(Login.this,RegistrationActivity.class));
            }
        });
    }

    //Login Method
    public void Login(){

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
//GetValue for EditText field
                String user = userName.getText().toString();
                String pass = password.getText().toString();

                String currentUser = myDatabaseHelper.SearchExistingAccount(user);

                if (pass.equals(currentUser)){
                    Toast.makeText(Login.this, "Login Successfuly", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Login.this, "Account Does not Exist", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
