package com.example.macstudent.parkme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        final Context context = this;

        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                }catch(Exception ex){
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG);
                }finally {
                    finish();
                    startActivity(new Intent(context, LoginActivity.class));
                }
            }
        };

        timer.start();
    }
}
