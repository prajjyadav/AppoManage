package com.example.prajjwal_ubuntu.appomanage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by prajjwal-ubuntu on 24/9/18.
 */

public class AdminArrayList extends ArrayAdapter<Admin> {
    private Activity context;
    private List<Admin> adminList;

    public AdminArrayList(Activity context, List<Admin> adminList){
        super(context, R.layout.admin_list_layout, adminList);
        this.context=context;
        this.adminList=adminList;

    }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
       LayoutInflater inflater = context.getLayoutInflater();
       View listViewItem = inflater.inflate(R.layout.admin_list_layout, null, true);

       TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
       TextView textViewSpec = (TextView) listViewItem.findViewById(R.id.textViewSpecs);

       Admin ad = adminList.get(position);
       textViewName.setText(ad.getName());
       textViewSpec.setText(ad.getSpecialization());

       return listViewItem;
   }
}
