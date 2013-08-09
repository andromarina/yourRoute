package com.yourRoute;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.yourRoute.model.Route;
import com.yourRoute.model.RoutesRepository;
import com.yourRoute.model.Stop;
import com.yourRoute.model.StopsRepository;

import java.util.ArrayList;


public class RouteDetailsActivityController {
    private RouteDetailsActivity activity;
    private RoutesRepository routesRepository;
    private StopsRepository stopsRepository;
    private Route route;
    private int routeId;

    public RouteDetailsActivityController(RouteDetailsActivity activity, RoutesRepository routesRepository,
                                          StopsRepository stopsRepository) {
        this.activity = activity;
        this.routesRepository = routesRepository;
        this.stopsRepository = stopsRepository;
    }

    public void initialize() {
        initializeRouteDetailsFragment();
        initializeRoute();
        setCarTypeIcon();
        setStartEnd();
        setOperationHours();
        setInterval();
        setLength();
        setStopsList();
    }

    private void initializeRouteDetailsFragment() {
        FragmentManager fm = this.activity.getSupportFragmentManager();
        RouteDetailsFragment fragment = new RouteDetailsFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(fragment, "RouteDetailsFragment");
        initializeTabHost();
        ft.commit();
    }

    private void initializeTabHost() {

        TabHost direction_tabhost = activity.getDirection_tabhost();
        direction_tabhost.setup();
        String forward = activity.getResources().getString(R.string.forward);
        String backward = activity.getResources().getString(R.string.backward);
        direction_tabhost.addTab(direction_tabhost.newTabSpec("ForwardTab").setIndicator(forward).setContent(R.id.forward_stops_list));
        direction_tabhost.addTab(direction_tabhost.newTabSpec("BackwardTab").setIndicator(backward).setContent(R.id.backward_stops_list));
    }

    private void initializeRoute() {
        Intent intent = this.activity.getIntent();
        this.routeId = intent.getIntExtra("routeID", 1);
        this.route = this.routesRepository.getRoute(this.routeId);
        ArrayList<Stop> stops = this.stopsRepository.getStopsByRouteId(this.routeId);
        this.route.initializeStops(stops);
    }

    public String getRouteName() {

        String routeName = this.route.getName();
        return routeName;

    }

    public void setCarTypeIcon() {
        ImageView carType = activity.getCarTypeIcon();
        int carTypeId = this.route.getCarType();
        int resourceId = this.route.getIconResource(carTypeId);
        carType.setImageResource(resourceId);

    }

    private void setStartEnd() {
        TextView startEndView = this.activity.getStartEndView();
        String startEnd = this.route.getStartEnd();
        startEndView.setText(startEnd);
    }

    private void setOperationHours() {

        TextView operationHoursView = this.activity.getOperationHoursView();
        LinearLayout operationalHoursLayout = this.activity.getOperationHoursLayout();
        if (this.route.getWorkTime().isEmpty()) {
            operationalHoursLayout.setVisibility(View.GONE);
        } else {
            operationHoursView.setText(route.getWorkTime());
        }
    }

    private void setInterval() {
        TextView intervalView = this.activity.getIntervalView();
        LinearLayout intervalLayout = this.activity.getIntervalLayout();
        String interval = this.route.getInterval();
        if (interval.isEmpty()) {
            intervalLayout.setVisibility(View.GONE);
        } else {
            intervalView.setText(interval);
        }
    }

    private void setLength() {
        TextView lengthView = this.activity.getLengthView();
        LinearLayout lengthLayout = this.activity.getLengthLayout();

        if (route.getLength().isEmpty()) {
            lengthLayout.setVisibility(View.GONE);
        } else {
            lengthView.setText(this.route.getLength());
        }
    }

    private void setStopsList() {

        final ArrayList<Stop> forwardStops = this.route.getForwardStops();
        for (Stop stop : forwardStops) {
            Log.d("Test", "Forward stop name: " + stop.getName().toString() + "stopIndex: " + stop.getStopIndex());
        }
        ListView forwardStopsList = this.activity.getForwardStopsList();
        StopsListAdapter adapterForward = new StopsListAdapter(activity.getBaseContext(), R.layout.stop_item, forwardStops);
        forwardStopsList.setAdapter(adapterForward);
        StopsListListener forwardStopsListListener = new StopsListListener(activity.getBaseContext(), activity, forwardStops);
        forwardStopsList.setOnItemClickListener(forwardStopsListListener);

        final ArrayList<Stop> backwardStops = this.route.getBackwardStops();
        for (Stop stop : backwardStops) {
            Log.d("Test", "Backward stop name: " + stop.getName().toString() + "stopIndex: " + stop.getStopIndex());
        }
        ListView backwardStopsList = this.activity.getBackwardStopsList();
        StopsListListener backwardStopsListListener = new StopsListListener(activity.getBaseContext(), activity, backwardStops);
        backwardStopsList.setOnItemClickListener(backwardStopsListListener);
        StopsListAdapter adapterBackward = new StopsListAdapter(activity.getBaseContext(), R.layout.stop_item, backwardStops);
        backwardStopsList.setAdapter(adapterBackward);
    }
}
