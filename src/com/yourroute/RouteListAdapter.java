package com.yourroute;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yourroute.model.Route;

import java.util.ArrayList;

public class RouteListAdapter extends ArrayAdapter<Route> {
    private ArrayList<Route> routes;
    private String LOG_TAG = "RouteListAdapter";
    private RoutesFilter filter;

    public RouteListAdapter(Context context, int textViewResourceId, ArrayList<Route> routes) {
        super(context, textViewResourceId, routes);
        this.routes = routes;
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
        Route route = this.routes.get(position);
        TextView name = (TextView) item.findViewById(R.id.name);
        name.setText(route.getName());
        TextView startEnd = (TextView) item.findViewById(R.id.startEnd);
        startEnd.setText(route.getStartEnd());
        ImageView carType = (ImageView) item.findViewById(R.id.carTypeImage);
        int carTypeId = route.getType().getId();
        switch (carTypeId) {
            case 1:
                carType.setImageResource(R.drawable.ic_bus);
                break;
            case 2:
                carType.setImageResource(R.drawable.ic_trolley);
                break;
            case 3:
                carType.setImageResource(R.drawable.ic_tram);
                break;
            case 4:
                carType.setImageResource(R.drawable.ic_minibus);
                break;
            case 5:
                carType.setImageResource(R.drawable.ic_bus);
                break;
        }


        item.setBackgroundColor(colors[position % 2]);

        return item;
    }

    @Override
    public Filter getFilter() {
        if (this.filter == null) {
            this.filter = new RoutesFilter(this);
        }
        return filter;
    }

    public ArrayList<Route> getRoutes() {
        return this.routes;
    }
}