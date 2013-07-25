package com.yourroute;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yourroute.model.Route;

import java.util.ArrayList;

public class RouteListAdapter extends ArrayAdapter<Route> {

    private String LOG_TAG = "RouteListAdapter";

    public RouteListAdapter(Context context, int textViewResourceId, ArrayList<Route> routes) {
        super(context, textViewResourceId, routes);
    }

    @Override
    public View getView(int position, View item, ViewGroup parent) {
        int[] colors = new int[2];
        colors[0] = Color.parseColor("#F5F5F5");
        colors[1] = Color.parseColor("#E8E8E8");

        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.route_list_item, null);
        }
        Route route = super.getItem(position);

        TextView name = (TextView) item.findViewById(R.id.name);
        name.setText(route.getName());


        TextView startEnd = (TextView) item.findViewById(R.id.start_end);

        if (route.getStartEnd().isEmpty()) {
            startEnd.setVisibility(View.GONE);
        } else {
            startEnd.setText(route.getStartEnd());
        }
        ImageView carType = (ImageView) item.findViewById(R.id.carTypeImage);
        int carTypeId = route.getType().getId();
        int iconResource = route.getIconResource(carTypeId);
        carType.setImageResource(iconResource);
        item.setBackgroundColor(colors[position % 2]);

        return item;
    }

}