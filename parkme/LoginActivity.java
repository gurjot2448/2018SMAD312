package com.example.macstudent.parkme;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnSignin;
    Button btnRegister;
    EditText edtUsername;
    EditText edtPassword;
    DBHelper dbHelper;
    SQLiteDatabase ParkingDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignin = findViewById(R.id.btnSignin);
        btnSignin.setOnClickListener(this);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnSignin.getId()) {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

   //         if (username.equals("Guri@gmail.com") && password.equals("simu123")) {
   //             Toast.makeText(this, "Signin Successful", Toast.LENGTH_LONG).show();
   //         }else {
   //             Toast.makeText(this, "Invalid username/password", Toast.LENGTH_LONG).show();
   //         }
   //             } else if (v.getId() == btnRegister.getId()) {
   //         Toast.makeText(this, "Creating an account", Toast.LENGTH_LONG).show();

   //         Intent signUpIntent = new Intent(this,SignupActivity.class);
   //         startActivity(signUpIntent);

            if (verifyLogin()){
                Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(this, HomeActivity.class));
            }else{
                Toast.makeText(this, "Invalid username/password", Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == btnRegister.getId()) {
            Toast.makeText(this, "Creating an account", Toast.LENGTH_LONG).show();

            finish();
            Intent signUpIntent = new Intent(this,SignupActivity.class);
            startActivity(signUpIntent);
        }
    }

    private boolean verifyLogin() {
        try{
            ParkingDB = dbHelper.getReadableDatabase();
            String columns[] = {"Username","Password"};
            String userData[] = {edtUsername.getText().toString(), edtPassword.getText().toString()};

            Cursor cursor = ParkingDB.query("UserInfo",columns, "Username = ? AND Password = ?",userData,
                    null,null,null);

            if(cursor != null){
                if (cursor.getCount() > 0){
                    return true;
                }
            }
            return false;

        }catch (Exception e){
            Log.e("LoginActivity", e.getMessage());
            return false;
        }finally {
            ParkingDB.close();
        }
    }
}

