package com.example.prajjwal_ubuntu.appomanage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button login,sign_up;
    private EditText user_name,password;
   private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button)findViewById(R.id.login_button);
        sign_up = (Button)findViewById(R.id.sign_up_button);
        user_name = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            Intent intent = new Intent(MainActivity.this,Second.class);
            startActivity(intent);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate1()){
                validate(user_name.getText().toString(),password.getText().toString());}
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        });
    }
    private void validate(String userName,String userPassword){

        progressDialog.setMessage("Checking your credintials!!");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(user_name.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    //Toast.makeText(MainActivity.this, "Login Successful   !!", Toast.LENGTH_SHORT).show();
                   // Intent intent = new Intent(MainActivity.this,Second.class);
                 //   startActivity(intent);
                    checkEmailVerification();

                }
                else{

                    Toast.makeText(MainActivity.this, "Login failed !!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });

    }
    public Boolean validate1(){
        Boolean result = false;
        String name = user_name.getText().toString();
        String passWord = password.getText().toString();
       // String userEmail = user_name.getText().toString();
        if(name.isEmpty()||passWord.isEmpty())
        {
            Toast.makeText(this,"Please fill all details",Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;
    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();
        if(emailflag)
        {

            Toast.makeText(MainActivity.this, "Login Successful   !!", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(MainActivity.this,UserData.class);
               startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this,"Please verify email!!",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }


    }




}
