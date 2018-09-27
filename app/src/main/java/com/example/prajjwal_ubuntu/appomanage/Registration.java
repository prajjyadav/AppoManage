package com.example.prajjwal_ubuntu.appomanage;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    private EditText user_name,user_email,user_password, user_age, user_mobile;
    private Button sign_up,login;

    private ProgressDialog progressDialog;

    //final String userName, userEmail, userMobile, userAge, userPassword;

    private DatabaseReference databaseUser;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth= FirebaseAuth.getInstance();
        databaseUser = FirebaseDatabase.getInstance().getReference("Users");

        user_name = (EditText)findViewById(R.id.user_name);
        user_email = (EditText)findViewById(R.id.email);
        user_age = (EditText) findViewById(R.id.editSignupAge);
        user_mobile = (EditText) findViewById(R.id.editSignupMobile);
        user_password = (EditText)findViewById(R.id.password);
        sign_up = (Button)findViewById(R.id.sign_up_button);
        login = (Button)findViewById(R.id.login_button);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);



        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    progressDialog.setMessage("Saving your data!!");
                    progressDialog.show();
                    //data
                    final String userEmail = user_email.getText().toString().trim();
                    final String userPassword = user_password.getText().toString().trim();
                    final String userAge = user_age.getText().toString().trim();
                    final String userMobile = user_mobile.getText().toString().trim();
                    final String userName = user_name.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();

                                String id = firebaseAuth.getCurrentUser().getUid();
                                User user =new User(id,userName,userAge, userMobile,userEmail);
                                databaseUser.child(id).setValue(user);

                                SharedPreferences sharedPreferences =getSharedPreferences("myFile", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor =sharedPreferences.edit();
                                editor.putString("name", userName);
                                editor.putString("id", id);
                                editor.putString("email", userEmail);
                                editor.putString("mobile", userMobile);
                                editor.putString("age", userAge);
                                editor.putString("name", userName);
                                editor.putString("isAdmin", "false");
                                editor.commit();




                          // Toast.makeText(Registration.this,"Registration Successful",Toast.LENGTH_LONG).show();
                            //    firebaseAuth.signOut();
                              //  finish();
                                //Intent intent = new Intent(Registration.this,MainActivity.class);
                                //startActivity(intent);
                                sendEmailVerification();

                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(Registration.this,"Registration Failed",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validate(){
        Boolean result = false;
        String name = user_name.getText().toString();
        String passWord = user_password.getText().toString();
        String userEmail = user_email.getText().toString();
        final String userAge = user_age.getText().toString().trim();
        final String userMobile = user_mobile.getText().toString().trim();
        if(name.isEmpty()||passWord.isEmpty()||userEmail.isEmpty()|| userAge.isEmpty() || userMobile.isEmpty())
        {
            Toast.makeText(this,"Please fill all details",Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;
    }
    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(Registration.this,"Successfully Registered and Email send !!",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        Intent intent = new Intent(Registration.this,MainActivity.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(Registration.this,"Verification mail not send ",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
