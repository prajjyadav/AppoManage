package com.example.prajjwal_ubuntu.appomanage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by prajjwal-ubuntu on 26/9/18.
 */

public class AppoArrayList extends ArrayAdapter<Appointment>{

    private Activity context;
    private List<Appointment> appoList;

    public AppoArrayList(Activity context, List<Appointment> appoList){
        super(context, R.layout.appointment_list_layout, appoList);
        this.context=context;
        this.appoList=appoList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.appointment_list_layout, null, true);

        TextView textViewTitle = (TextView) listViewItem.findViewById(R.id.textViewTilte);
        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.textViewDate);

        Appointment appo = appoList.get(position);
        textViewTitle.setText(appo.getTitle());
        textViewDate.setText(appo.getDate());

        return listViewItem;
    }
}
