package com.yourRoute;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.yourRoute.model.Stop;

import java.util.ArrayList;

public class StopsListAdapter extends ArrayAdapter<Stop> {

    public StopsListAdapter(Context context, int textViewResourceId, ArrayList<Stop> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View item, ViewGroup parent) {
//        int[] colors = new int[2];
//        colors[0] = Color.parseColor("#F5F5F5");
//        colors[1] = Color.parseColor("#E8E8E8");

        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.stop_item, null);
        }
        Stop stop = super.getItem(position);
        TextView name = (TextView) item.findViewById(R.id.stop_name);
        String searchKey = YourRouteApp.getSearchPhrase().toLowerCase();
        String optionalSearchKey = YourRouteApp.getOptionalSearchPhrase().toLowerCase();
        String stopName = stop.getName();
        name.setText(stopName);
        if (stopName.toLowerCase().contains(searchKey)) {
            name.setTextColor(Color.parseColor("#33b5e5"));
        } else if (stopName.toLowerCase().contains(optionalSearchKey) && !optionalSearchKey.isEmpty()) {
            name.setTextColor(Color.parseColor("#33b5e5"));
        } else {
            name.setTextColor(Color.BLACK);
        }

        //   item.setBackgroundColor(colors[position % 2]);
        return item;
    }

}


