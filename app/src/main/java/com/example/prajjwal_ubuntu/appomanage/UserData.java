package com.example.prajjwal_ubuntu.appomanage;

import android.content.Intent;
import android.icu.lang.UCharacterEnums;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserData extends AppCompatActivity {

    private DatabaseReference databaseUser;

    private Button update;
    private EditText username, email, age, mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        databaseUser = FirebaseDatabase.getInstance().getReference("Users");
        update =(Button ) findViewById(R.id.buttonUserUpdate);

        username =(EditText) findViewById(R.id.editTextUsername);
        email =(EditText) findViewById(R.id.editTextEmail);
        mobile = (EditText) findViewById(R.id.editTextMobile);
        age= (EditText) findViewById(R.id.editTextAge);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, ema, ag, mo;
                name =username.getText().toString();
                ema=email.getText().toString();
                ag=age.getText().toString();
                mo=mobile.getText().toString();


                String id = databaseUser.push().getKey();

                User user =new User(id,name,ag, mo,ema);

                databaseUser.child(id).setValue(user);

                Toast.makeText(getApplicationContext(), "info upadted", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), AdminData.class));

            }
        });


    }

}
