package com.example.macstudent.parkme;

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

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister;
    EditText edtName;
    EditText edtUsername;
    EditText edtPassword;
    EditText edtPostalAddress;
    EditText edtPhone;
    TextView txtDOB;
    DBHelper dbHelper;
    SQLiteDatabase ParkingDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        edtName = findViewById(R.id.edtName);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtPostalAddress = findViewById(R.id.edtPostalAddress);
        edtPhone = findViewById(R.id.edtPhone);


        txtDOB = findViewById(R.id.txtDOB);
        txtDOB.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == btnRegister.getId()) {
     //       String data = edtName.getText().toString() + " " + edtUsername.getText().toString() +
     //               " " + edtPassword.getText().toString() + " " + edtPostalAddress.getText().toString() +
     //               " " + edtPhone.getText();

     //       Toast.makeText(this, data, Toast.LENGTH_LONG).show();

            //   Intent loginIntent = new Intent(this, LoginActivity.class);
            //  startActivity(loginIntent);

      //      startActivity(new Intent(this, LoginActivity.class));
            insertData();
            displayData();
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        } else if (v.getId() == txtDOB.getId()) {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, datePickerListener, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String DOB = String.valueOf(month + 1) + "/" + String.valueOf(dayOfMonth) + "/" + String.valueOf(year);
            txtDOB.setText(DOB);
        }
    };

    private void insertData() {
        String name = edtName.getText().toString();
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        String postaladdress = edtPostalAddress.getText().toString();
        String phone = edtPhone.getText().toString();
        String dob = txtDOB.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put("Name", name);
        cv.put("Username", username);
        cv.put("Password", password);
        cv.put("PostalAddress", postaladdress);
        cv.put("Phone", phone);
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
            String columns[] = {"Name", "Username", "Password", "PostalAddress", "Phone", "DOB"};

            Cursor cursor = ParkingDB.query("UserInfo", columns, null,
                    null, null, null, null);

            while (cursor.moveToNext()) {
                String UserData = cursor.getString(cursor.getColumnIndex("Name"));
                UserData += "\n" + cursor.getString(cursor.getColumnIndex("Username"));
                UserData += "\n" + cursor.getString(cursor.getColumnIndex("Password"));
                UserData += "\n" + cursor.getString(cursor.getColumnIndex("PostalAddress"));
                UserData += "\n" + cursor.getString(cursor.getColumnIndex("Phone"));
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





