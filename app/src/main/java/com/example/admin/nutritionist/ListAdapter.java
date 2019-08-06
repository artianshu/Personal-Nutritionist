package com.example.admin.nutritionist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Listitem> {

    List<Listitem> mitem;
    Context c;

    public ListAdapter(Context c, List<Listitem> mitem) {
        super(c,R.layout.foodlist,mitem);
        this.mitem = mitem;
        this.c = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.foodlist,parent,false);
        TextView textViewHead=view.findViewById(R.id.textViewHead);
        TextView textViewDesc=view.findViewById(R.id.textViewDesc);
        Listitem item=mitem.get(position);
        textViewHead.setText(item.getID());
        textViewDesc.setText(item.getTitle());
        return view;
    }
}
