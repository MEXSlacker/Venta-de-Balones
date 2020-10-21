package com.example.bdtercerparcial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class CustomAdapter extends ArrayAdapter<HashMap<Integer, String>> {
    private HashMap<Integer, String> items;
    Context con;

    public CustomAdapter (Context context, int resource, HashMap<Integer, String> map){
        super(context, resource);

        items = map;
        con = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.custom_list, null,true);
        TextView txt = (TextView) row.findViewById(R.id.txtItem);
        ImageView img = (ImageView)row.findViewById(R.id.imgItem);
        txt.setText(items.get(position).toString());

        Context context = img.getContext();
        int id = context.getResources().getIdentifier("a"+position,"drawable", context.getPackageName());
        img.setImageResource(id);
        return row;
    }
}
