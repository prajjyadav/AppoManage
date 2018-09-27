package com.example.prajjwal_ubuntu.appomanage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Second extends AppCompatActivity {
    private Button log_out,op, appo, acc, adminAppo, adminSignup;
    private FirebaseAuth firebaseAuth;

    public static final String STATUS ="accepted";
    public static final String IS_ADMIN = "isAdmin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        op= (Button) findViewById(R.id.button2);
        op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminsList.class));
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        //log_out = (Button)findViewById(R.id.logout);

        log_out = (Button)findViewById(R.id.logout);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();
                finish();
                Intent intent  = new Intent(Second.this,MainActivity.class);
                startActivity(intent);
            }
        });

        appo =(Button) findViewById(R.id.button3);
        appo.setText("pending");

        appo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), AppointmentList.class);

                i.putExtra(STATUS , "pending");
                i.putExtra(IS_ADMIN, "false");
                startActivity(i);
            }
        });

        acc =(Button) findViewById(R.id.button4);
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), AppointmentList.class);

                i.putExtra(STATUS, "confirmed");
                i.putExtra(IS_ADMIN, "false");
                startActivity(i);
            }
        });

        adminAppo =(Button) findViewById(R.id.button5);
        adminAppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), AppointmentList.class);

                i.putExtra(STATUS, "pending");
                i.putExtra(IS_ADMIN, "true");
                startActivity(i);
            }
        });

        adminSignup =(Button) findViewById(R.id.button6);
        adminSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("myFile", Context.MODE_PRIVATE);
                String def = "defaul";
                String adminStat = sharedPreferences.getString("isAdmin",def);
                if(adminStat.equals("false"))
                startActivity(new Intent(getApplicationContext(), AdminData.class));
                else {
                    Toast.makeText(getApplicationContext(), "Already admin", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
