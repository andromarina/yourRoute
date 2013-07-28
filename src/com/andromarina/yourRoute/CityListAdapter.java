package com.andromarina.yourRoute;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.andromarina.R;
import com.andromarina.yourRoute.model.City;

import java.util.List;

public class CityListAdapter extends ArrayAdapter<City> {
    private List<City> objects;

    public CityListAdapter(Context context, int textViewResourceId, List<City> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;

    }

    @Override
    public View getView(int position, View item, ViewGroup parent) {

        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.city_list_item, null);
        }
        City city = this.objects.get(position);
        TextView name = (TextView) item.findViewById(R.id.city_name);
        name.setText(city.getName());

        return item;
    }
}
