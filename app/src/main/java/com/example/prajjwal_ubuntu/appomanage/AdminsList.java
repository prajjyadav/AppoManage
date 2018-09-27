package com.example.prajjwal_ubuntu.appomanage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminsList extends AppCompatActivity {

    ListView listViewAdmin;
    DatabaseReference databaseAdminRef;
    FirebaseAuth firebaseAuth;
    List<Admin> adminlist;
    Button search;
    EditText searchbar;

    String sea="";

    public static final String ADMIN_ID ="id";
    public static final String ADMIN_NAME="name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admins_list);
        listViewAdmin =(ListView) findViewById(R.id.listViewAdmin);
        databaseAdminRef= FirebaseDatabase.getInstance().getReference("Admins");
        firebaseAuth = FirebaseAuth.getInstance();

        search = (Button) findViewById(R.id.searchButton);
        searchbar = (EditText) findViewById(R.id.editTextSearch);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sea= searchbar.getText().toString();
                onStart();

            }
        });

        adminlist = new ArrayList<>();
        listViewAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Admin ad =adminlist.get(i);
                Log.d("res",ad.getId());
                Intent intent = new Intent(getApplicationContext(), AdminDataShow.class);
                intent.putExtra(ADMIN_NAME, ad.getName());
                intent.putExtra(ADMIN_ID, ad.getId());

                //Intent intent =new  Intent(getApplicationContext(), Second.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
            Log.d("res", "onStart");
        databaseAdminRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("res", "onStart1");
                adminlist.clear();

                SharedPreferences sharedPreferences = getSharedPreferences("myFile", Context.MODE_PRIVATE);
                String def = "defaul";
                String userId = firebaseAuth.getCurrentUser().getUid();

                for(DataSnapshot adminSnapshot : dataSnapshot.getChildren()){
                    Admin a = adminSnapshot.getValue(Admin.class);
//                    if(a.getId().equals(userId))
//                        continue;
                    if(!(sea.equals(""))){
                        if(a.getName().equals(sea)||a.getSpecialization().equals(sea)){
                            adminlist.add(a);
                        }
                    }
                    else{
                        adminlist.add(a);
                    }
                }

//                Admin a =new Admin("a","sd","sd","sd","sd","sd","sd");
//                adminlist.add(a);
//                Admin b =new Admin("a","sad","asd","asd","sd","sd","sd");
//                adminlist.add(b);
                AdminArrayList adapter = new AdminArrayList(AdminsList.this, adminlist);
                listViewAdmin.setAdapter(adapter);
                if(adminlist.size()==0){
                    Toast.makeText(getApplicationContext(), "Nothing matches to your search", Toast.LENGTH_LONG).show();
                }
                //Log.d("res",adminlist.get(0).getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("res", databaseError.toException());
            }
        });
    }
}
