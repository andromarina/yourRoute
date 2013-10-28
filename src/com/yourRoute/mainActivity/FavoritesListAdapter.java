package com.yourRoute.mainActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yourRoute.R;
import com.yourRoute.model.Route;
import com.yourRoute.model.RoutesHolder;

import java.util.ArrayList;

public class FavoritesListAdapter extends ArrayAdapter<Route> {

    private String LOG_TAG = this.getClass().getSimpleName();
    private RoutesHolder routesHolder;

    public FavoritesListAdapter(Context context, int textViewResourceId, ArrayList<Route> routes, RoutesHolder routesHolder) {
        super(context, textViewResourceId, addSeparators(routes));
        this.routesHolder = routesHolder;
    }

    @Override
    public View getView(int position, View item, ViewGroup parent) {

        Route route = super.getItem(position);
        Route routeNext = getNextRoute(position);
        if (route.getId() == -1) {
            item = inflateSeparator(routeNext);
        } else {
            item = inflateItem(route);
        }

        return item;
    }

    private static ArrayList addSeparators(ArrayList<Route> routes) {
        int currentRouteCityId = 0;
        ArrayList<Route> arrayWithSeparators = routes;
        for (int i = 0; i < arrayWithSeparators.size(); ++i) {
            Route route = arrayWithSeparators.get(i);
            if(route.getCityId() != currentRouteCityId) {
                addSeparator(i, arrayWithSeparators);
                currentRouteCityId = route.getCityId();
            }
        }
        return arrayWithSeparators;
    }

    private static void addSeparator(int index, ArrayList<Route> routes) {
        Route separator = new Route(-1, "Fake", 1, "StartEnd", "Length", "Interval", "WorkTime", 24);
        routes.add(index, separator);
    }

    private View inflateItem(Route route) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.route_list_item, null);


        TextView name = (TextView) item.findViewById(R.id.name);
        name.setText(route.getName());

        TextView startEnd = (TextView) item.findViewById(R.id.start_end);

        if (route.getStartEnd().isEmpty()) {
            startEnd.setVisibility(View.GONE);
        } else {
            startEnd.setText(route.getStartEnd());
        }
        ImageView carType = (ImageView) item.findViewById(R.id.carTypeImage);
        int carTypeId = route.getCarType();
        int iconResource = route.getIconResource(carTypeId);
        carType.setImageResource(iconResource);

        return item;
    }

    private View inflateSeparator(Route route) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.separator, null);

        TextView textSeparator = (TextView) item.findViewById(R.id.textSeparator);
        int cityId = route.getCityId();

        String cityName = this.routesHolder.findCityById(cityId).getName();
        textSeparator.setText(cityName);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return item;
    }

    private Route getNextRoute(int position) {
        Route routeNext;
        if (position < super.getCount() - 1) {
            routeNext = super.getItem(position + 1);
        } else {
            routeNext = super.getItem(position);
        }
     return routeNext;
    }

}