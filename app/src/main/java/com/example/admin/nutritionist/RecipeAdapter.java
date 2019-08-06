package com.example.admin.nutritionist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RecipeAdapter extends ArrayAdapter<Recipie_row> {
    List<Recipie_row> mitem;
    Context c;

    public RecipeAdapter(Context c, List<Recipie_row> mitem) {
        super(c,R.layout.recipielist,mitem);
        this.mitem = mitem;
        this.c = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.recipielist,parent,false);
        TextView textViewHead=view.findViewById(R.id.recipieid);
        TextView textViewDesc=view.findViewById(R.id.recipiename);
        Recipie_row item=mitem.get(position);
        textViewHead.setText(item.getRecipe_id());
        textViewDesc.setText(item.getRecipe_name());
        return view;
    }
}
