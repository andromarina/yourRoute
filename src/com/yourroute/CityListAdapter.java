package com.yourroute;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/12/13
 * Time: 5:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class CityListAdapter extends ArrayAdapter<City> {
    private List<City> objects;
    private Context context;

    public CityListAdapter(Context context, int textViewResourceId ,List<City> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
        this.context = context;

    }

    @Override
    public View getView(int position, View item, ViewGroup parent) {

        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.city_item, null);
        }
        City city = this.objects.get(position);
        TextView name = (TextView) item.findViewById(R.id.city_name);
        name.setText(city.getName());

        return item;
    }
}
