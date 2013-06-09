package com.yourroute;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import com.yourroute.model.Route;
import com.yourroute.model.RoutesRepository;
import com.yourroute.model.Stop;
import com.yourroute.model.StopsRepository;

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
        setRouteName();
        setStartEnd();
        setOperationHours();
        setInterval();
        setLength();
        setStopsList(this.routeId);

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

    private void setRouteName() {
        TextView routeNameView = this.activity.getRoute_name_View();
        String routeName = this.route.getName();
        String carType = this.route.getType().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(routeName);
        sb.append(" ");
        sb.append(carType);
        String composedInfo = sb.toString();
        routeNameView.setText(composedInfo);
    }

    private void setStartEnd() {
        TextView startEndView = this.activity.getStartEndView();
        String startEnd = this.route.getStartEnd();
        startEndView.setText(startEnd);
    }

    private void setOperationHours() {

        TextView operationHoursView = this.activity.getOperationHoursView();
        LinearLayout operationalHoursLayout = this.activity.getOperationHoursLayout();
        if (this.route.getOperationalHours().isEmpty()) {
            operationalHoursLayout.setVisibility(View.GONE);
        } else {
            operationHoursView.setText(route.getOperationalHours());
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

        if (route.getLengthAndDuration().isEmpty()) {
            lengthLayout.setVisibility(View.GONE);
        } else {
            lengthView.setText(this.route.getLengthAndDuration());
        }
    }

    private void setStopsList(int routeId) {

        final ArrayList<Stop> stops = this.stopsRepository.getStopsByRouteId(routeId);
        for (Stop stop : stops) {
            Log.i("Test", "stop name: " + stop.getName().toString() + "stopIndex: " + stop.getStopIndex());
        }
        ListView forwardStopsList = this.activity.getForwardStopsList();
        StopsListAdapter adapterForward = new StopsListAdapter(activity.getBaseContext(), R.layout.stop_item, this.route.getForwardStops());
        forwardStopsList.setAdapter(adapterForward);

        StopsListAdapter adapterBackward = new StopsListAdapter(activity.getBaseContext(), R.layout.stop_item, this.route.getBackwardStops());
        ListView backwardStopsList = this.activity.getBackwardStopsList();
        backwardStopsList.setAdapter(adapterBackward);
    }
}
