package com.example.prajjwal_ubuntu.appomanage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewAppo extends AppCompatActivity {

    private EditText title, date, time, description;
    private Button send;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseRefAdmin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_appo);

        databaseReference = FirebaseDatabase.getInstance().getReference("userAppointments");
        databaseRefAdmin = FirebaseDatabase.getInstance().getReference("adminAppointments");
        firebaseAuth = FirebaseAuth.getInstance();

        Intent intent =getIntent();
        final String id1 =intent.getStringExtra(AdminDataShow.ADMIN_ID1);
        final String name =intent.getStringExtra(AdminDataShow.ADMIN_NAME2);
        Log.d("res","id "+id1);
        Log.d("res","name "+name);

        title = (EditText) findViewById(R.id.appoTitle);
        date = (EditText) findViewById(R.id.appoDate);
        time = (EditText) findViewById(R.id.appoTime);
        description = (EditText) findViewById(R.id.appoDescription);

        send = (Button) findViewById( R.id.appoSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("myFile", Context.MODE_PRIVATE);
                String def = "defaul";
                String userId = firebaseAuth.getCurrentUser().getUid();

                String tit = title.getText().toString().trim();
                String dat = date.getText().toString().trim();
                String ti = time.getText().toString().trim();
                String des = description.getText().toString().trim();

                String id = databaseReference.child(userId).push().getKey();

//                String id2 = databaseRefAdmin.child(id1).push().getKey();



                Appointment appo =new Appointment(id, tit, id1, userId, dat, ti, des, "pending");
                Appointment appo1 =new Appointment(id, tit, id1, userId, dat, ti, des, "pending");

                databaseReference.child(userId).child(id).setValue(appo);
                databaseRefAdmin.child(id1).child(id).setValue(appo1);

                startActivity(new Intent(getApplicationContext(), AdminsList.class));

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}
