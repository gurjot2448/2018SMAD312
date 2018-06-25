package com.example.macstudent.login;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    Button btnSignUp;
    EditText edtName;
    EditText edtPhone;
    EditText edtEmail;
    EditText edtPassword;
    TextView txtDOB;
    DBHelper dbHelper;
    SQLiteDatabase ParkingDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = findViewById(R.id.btnSubmit);
        btnSignUp.setOnClickListener(this);

        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        txtDOB = findViewById(R.id.txtDOB);
        txtDOB.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    public void onClick(View view){
        if(view.getId() == btnSignUp.getId()){
//            String data = edtName.getText().toString() + " " + edtPhone.getText().toString() +
//                   " " + edtEmail.getText().toString() + " " + edtPassword.getText().toString();
//
//            Toast.makeText(this, data, Toast.LENGTH_LONG).show();
//
//            Intent loginIntent = new Intent(this, LoginActivity.class);
//            startActivity(loginIntent);
//
//            startActivity(new Intent(this, LoginActivity.class));

           insertData();
           displayData();
           Intent loginIntent = new Intent(this, LoginActivity.class);
           startActivity(loginIntent);
        }else if(view.getId() == txtDOB.getId()){
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, datePickerListener, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }

    }

    DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String DOB = String.valueOf(month+1) + "/" + String.valueOf(dayOfMonth) + "/" + String.valueOf(year);
            txtDOB.setText(DOB);
        }
    };

    private void insertData() {
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String dob = txtDOB.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put("Name", name);
        cv.put("Phone", phone);
        cv.put("Email", email);
        cv.put("Password", password);
        cv.put("DOB", dob);

        try {
            ParkingDB = dbHelper.getWritableDatabase();
            ParkingDB.insert("UserInfo", null, cv);
            Log.v("SignUpActivity", "Account created");
        } catch (Exception e) {
            Log.e("SignUpActivity", e.getMessage());
        } finally {

            ParkingDB.close();
        }
    }
        private void displayData(){
            try {
                ParkingDB = dbHelper.getReadableDatabase();
                String columns[] = {"Name", "Phone", "Email", "Password", "DOB"};

                Cursor cursor = ParkingDB.query("UserInfo", columns, null,
                        null, null, null, null);

                while (cursor.moveToNext()) {
                    String UserData = cursor.getString(cursor.getColumnIndex("Name"));
                    UserData += "\n" + cursor.getString(cursor.getColumnIndex("Phone"));
                    UserData += "\n" + cursor.getString(cursor.getColumnIndex("Email"));
                    UserData += "\n" + cursor.getString(cursor.getColumnIndex("Password"));
                    UserData += "\n" + cursor.getString(cursor.getColumnIndex("DOB"));

                    Toast.makeText(this, UserData, Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Log.e("SignUpActivity", e.getMessage());
            } finally {
                ParkingDB.close();
            }
        }

    }