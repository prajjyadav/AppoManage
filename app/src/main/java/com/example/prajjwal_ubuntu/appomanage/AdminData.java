package com.example.prajjwal_ubuntu.appomanage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminData extends AppCompatActivity {

    Button update;
    DatabaseReference databaseUser;
    FirebaseAuth firebaseAuth;

    EditText username, email, mobile, age, specialization, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_data);

        databaseUser = FirebaseDatabase.getInstance().getReference("Admins");
        firebaseAuth = FirebaseAuth.getInstance();

        update =(Button) findViewById(R.id.buttonUserUpdate);

        username =(EditText) findViewById(R.id.editTextUsername);
        email =(EditText) findViewById(R.id.editTextEmail);
        mobile = (EditText) findViewById(R.id.editTextMobile);
        age= (EditText) findViewById(R.id.editTextAge);
        specialization= (EditText) findViewById(R.id.editTextSpecialization);
        address= (EditText) findViewById(R.id.editTextAddress);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sp,name, ema, ag, mo;
                name =username.getText().toString();
                ema=email.getText().toString();
                ag=age.getText().toString();
                mo=mobile.getText().toString();
                sp =specialization.getText().toString();
                String add= address.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("myFile", Context.MODE_PRIVATE);
                String def = "defaul";
                String userId = firebaseAuth.getCurrentUser().getUid();


                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putString("isAdmin", "true");
                editor.commit();

                Admin admin =new Admin(userId,name,ema, sp, ag,add,  mo );

                databaseUser.child(userId).setValue(admin);

                Toast.makeText(getApplicationContext(), "info upadted", Toast.LENGTH_LONG).show();

                startActivity(new Intent(getApplicationContext(), Second.class));


            }
        });


    }
}
