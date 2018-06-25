package com.example.macstudent.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class NewParkingActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner spnCarCompany, spnLot, spnSpot, spnPayment;
    TextView txtAmount, txtDateTime;
    EditText edtCarPlate;
    Button btnAddParking;
    RadioButton rdoOne, rdoTwo, rdoThree, rdoFour;

    int parkingRate[] = {10, 20, 30, 40};
    String lot[] = {"A", "B", "C", "D", "E", "F"};
    String spot[] = {"1", "2", "3", "4", "5"};
    String payment[] = {"Debit Card", "Credit Card", "Master Card", "American Express", "Cash"};

    String selectedLot, selectedSpot, selectedPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_parking);

        spnCarCompany = findViewById(R.id.spnCarCompany);
        spnLot = findViewById(R.id.spnLot);
        ArrayAdapter lotAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lot);
        lotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLot.setAdapter(lotAdapter);
        spnLot.setOnItemSelectedListener(this);


        spnSpot = findViewById(R.id.spnSpot);
        ArrayAdapter spotAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spot);
        spotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSpot.setAdapter(spotAdapter);
        spnSpot.setOnItemSelectedListener(this);

        spnPayment = findViewById(R.id.spnPayment);
        ArrayAdapter paymentAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, payment);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPayment.setAdapter(paymentAdapter);
        spnPayment.setOnItemSelectedListener(this);

        txtAmount = findViewById(R.id.txtAmount);
        txtDateTime = findViewById(R.id.txtDateTime);
        txtDateTime.setText(Calendar.getInstance().getTime().toString());

        edtCarPlate = findViewById(R.id.edtCarPlate);

        btnAddParking = findViewById(R.id.btnAddParking);
        btnAddParking.setOnClickListener(this);

        rdoOne = findViewById(R.id.rdoOne);
        rdoOne.setOnClickListener(this);

        rdoTwo = findViewById(R.id.rdoTwo);
        rdoTwo.setOnClickListener(this);

        rdoThree = findViewById(R.id.rdoThree);
        rdoThree.setOnClickListener(this);

        rdoFour = findViewById(R.id.rdoFour);
        rdoFour.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (rdoOne.isChecked()) {
            txtAmount.setText("$" + parkingRate[0]);
        } else if (rdoTwo.isChecked()) {
            txtAmount.setText("$" + parkingRate[1]);
        } else if (rdoThree.isChecked()) {
            txtAmount.setText("$" + parkingRate[2]);
        } else if (rdoFour.isChecked()) {
            txtAmount.setText("$" + parkingRate[3]);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == spnLot.getId()) {
            selectedLot = lot[position];
            }else if (parent.getId() == spnSpot.getId()){
            selectedSpot = spot[position];
        }else if (parent.getId() == spnPayment.getId()){
            selectedPayment = payment[position];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}