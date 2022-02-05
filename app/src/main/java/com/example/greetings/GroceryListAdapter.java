package com.example.greetings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GroceryListAdapter extends ArrayAdapter<Welcome_Screen.Grocery> {

    private static final String TAG = "GroceryListAdapter";
    private Context mContext;
    int mResource;

    public GroceryListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Welcome_Screen.Grocery> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Gets the grocery information
        String name = getItem(position).getName();
        String price = getItem(position).getPrice();
        //double price = getItem(position).getPrice();

        //Creates a grocery object with the information
        //Welcome_Screen.Grocery grocery = new Welcome_Screen.Grocery(name,price);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView nameIDpassed = (TextView) convertView.findViewById(R.id.nameID);
        TextView priceIDpassed = (TextView) convertView.findViewById(R.id.priceID);

        nameIDpassed.setText(name);
        priceIDpassed.setText(price);

        return convertView;
    }
}
