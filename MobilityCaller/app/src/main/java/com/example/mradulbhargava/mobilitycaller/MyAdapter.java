package com.example.mradulbhargava.mobilitycaller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by MRADUL BHARGAVA on 23-12-2016.
 */
public class MyAdapter extends BaseAdapter {
    ArrayList<Contacts> contactsArrayList;
    Context c;
    private static LayoutInflater inflater=null;
    MyAdapter(Context c,ArrayList<Contacts> contactsArrayList)
    {
        this.c=c;
        this.contactsArrayList=contactsArrayList;

    }
    @Override
    public int getCount() {
        return contactsArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactsArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return contactsArrayList.indexOf(getItem(i));
    }
    public class Holder
    {
        TextView tv1,tv2;

    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        Holder holder=new Holder();
        inflater = (LayoutInflater) c
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View rowView= inflater.inflate(R.layout.custom_row, null);
        holder.tv1=(TextView) rowView.findViewById(R.id.textView_Name);
        holder.tv2=(TextView) rowView.findViewById(R.id.textView_Number);

        holder.tv1.setText(contactsArrayList.get(i).getName());
        holder.tv2.setText(contactsArrayList.get(i).getContact());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Toast.makeText(c, "You Clicked " + (contactsArrayList.get(i).getName()), Toast.LENGTH_LONG).show();
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + contactsArrayList.get(i).getContact()));
                                c.startActivity(callIntent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setMessage("Do you want to Call "+contactsArrayList.get(i).getName()+"?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

            }
        });
        return rowView;
    }
}
