package com.example.nasir.registrationtesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    //Call Database
    SqliteDB myDatabaseHelper;

    //Initialized Button
    private Button register;

    //Initialized Edit Text
    private EditText StudentID,UserName,Password,FirstName,LastName,Address,City,Postal_Code;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        UserName = (EditText)findViewById(R.id.editUserName);
        Password = (EditText)findViewById(R.id.editPassword);
        FirstName = (EditText)findViewById(R.id.editFName);
        LastName = (EditText)findViewById(R.id.editLName);
        Address = (EditText)findViewById(R.id.editAddress);
        City = (EditText)findViewById(R.id.editCity);
        Postal_Code = (EditText)findViewById(R.id.editPostCode);

        //Initialized Button
        register = (Button)findViewById(R.id.btnRegistration);

        //Initialized Database
        myDatabaseHelper = new SqliteDB(this);

        //Called AddData
        AddData();
    }

    public void AddData(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                String userName = UserName.getText().toString();
                String password = Password.getText().toString();
                String fName = FirstName.getText().toString();
                String lname = LastName.getText().toString();
                String address = Address.getText().toString();
                String city = City.getText().toString();
                String postalCode = Postal_Code.getText().toString();
                //Check if Data Inserted properly
                boolean isInserted = myDatabaseHelper.InsertRegistrationData(userName, password, fName, lname, address, city, postalCode);

                if (isInserted == true) {
                    ToastMessage("Data Inserted Successfully!");
                    Intent i = new Intent(RegistrationActivity.this,
                            Login.class);
                    startActivity(i);
                }
                else {
                    ToastMessage("Data Already Existed");
                }

            }
        });
    }

    private void ToastMessage(String message){
        Toast.makeText(RegistrationActivity.this,message, Toast.LENGTH_LONG).show();
    }

}
