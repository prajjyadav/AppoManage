package com.example.prajjwal_ubuntu.appomanage;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminDataShow extends AppCompatActivity {

    private TextView textViewName;
    private FloatingActionButton fab;
    private TextView textViewSpec;
    private Admin admin;
    String id;
    public static final String ADMIN_ID1 ="id";
    public static final String ADMIN_NAME2 ="name";

    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_data_show);
        Log.d("res", "AdminShowData");

        Intent intent =getIntent();

        final String name =intent.getStringExtra(AdminsList.ADMIN_NAME);
        id =intent.getStringExtra(AdminsList.ADMIN_ID);

        Log.d("res",id);
        textViewName =(TextView) findViewById(R.id.textViewAdmin);
        textViewName.setText(name);
        databaseRef= FirebaseDatabase.getInstance().getReference("Admins");

        textViewSpec =(TextView) findViewById(R.id.specTextView);
        fab =(FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), AddNewAppo.class);
                intent.putExtra(ADMIN_ID1, id);
                intent.putExtra(ADMIN_NAME2, name);
                Log.d("res",id);
                Log.d("res", name);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot adminSnapshot : dataSnapshot.getChildren()){
                    admin = adminSnapshot.getValue(Admin.class);
                        Log.d("res", "*"+admin.getId()+"*");

                    if(admin.getId().equals(id)){
                        Log.d("res","matches");
                        break;
                    }
                }

//                Admin a =new Admin("a","sd","sd","sd","sd","sd","sd");
//                adminlist.add(a);
//                Admin b =new Admin("a","sad","asd","asd","sd","sd","sd");
//                adminlist.add(b);
                textViewSpec.setText(admin.getSpecialization());
                textViewName.setText(admin.getName());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("res", databaseError.toException());
            }
        });

//
//        textViewName.setText("hello");
//        textViewSpec.setText("asfs");

    }
}
