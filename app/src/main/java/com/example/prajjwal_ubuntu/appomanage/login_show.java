package com.example.prajjwal_ubuntu.appomanage;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class login_show extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_show);

        listView = (ListView)findViewById(R.id.list_view);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

    }

    public void onClick(View v){
        Intent intent = new Intent(login_show.this,add_to_do.class);
        startActivity(intent);
    }
}
