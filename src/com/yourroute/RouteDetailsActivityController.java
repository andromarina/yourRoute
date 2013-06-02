package com.yourroute;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import com.yourroute.model.Route;
import com.yourroute.model.RoutesRepository;


public class RouteDetailsActivityController {
    private RouteDetailsActivity activity;
    private RoutesRepository routesRepository;
    private Route route;

    public RouteDetailsActivityController(RouteDetailsActivity activity, RoutesRepository routesRepository) {
        this.activity = activity;
        this.routesRepository = routesRepository;
    }

    public void initialize() {
        initializeRouteDetailsFragment();
        initializeRoute();
    }

    public void setValues() {
        setRouteName();
        setStartEnd();
        setOperationHours();
        setInterval();
        setLength();
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
        direction_tabhost.addTab(direction_tabhost.newTabSpec("ForwardTab").setIndicator(forward).setContent(R.id.textView));
        direction_tabhost.addTab(direction_tabhost.newTabSpec("BackwardTab").setIndicator(backward).setContent(R.id.textView1));
    }

    private Route initializeRoute() {
        Intent intent = this.activity.getIntent();
        int routeID = intent.getIntExtra("routeID", 1);
        this.route = this.routesRepository.getRoute(routeID);
        return route;
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
        String startTime = this.route.getStartTime();
        String endTime = this.route.getEndTime();
        if (startTime == null && endTime == null) {
            operationalHoursLayout.setVisibility(View.GONE);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(startTime);
            sb.append(" - ");
            sb.append(endTime);
            String composedString = sb.toString();
            operationHoursView.setText(composedString);
        }
    }

    private void setInterval() {
        TextView intervalView = this.activity.getIntervalView();
        LinearLayout intervalLayout = this.activity.getIntervalLayout();
        String interval = this.route.getInterval();
        if (interval == null) {
            intervalLayout.setVisibility(View.GONE);
        } else {
            intervalView.setText(interval);
        }
    }

    private void setLength() {
        TextView lengthView = this.activity.getLengthView();
        LinearLayout lengthLayout = this.activity.getLengthLayout();
        String length = this.route.getLength();
        String km = this.activity.getResources().getString(R.string.km);
        String duration = this.route.getDuration();
        String min = this.activity.getResources().getString(R.string.min);

        if (length == null && duration == null) {
            lengthLayout.setVisibility(View.GONE);
        } else if (length == null && duration != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(duration);
            sb.append(" ");
            sb.append(min);
            String composedString = sb.toString();
            lengthView.setText(composedString);
        } else if (duration == null && length != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(length);
            sb.append(" ");
            sb.append(km);
            String composedString = sb.toString();
            lengthView.setText(composedString);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(length);
            sb.append(" ");
            sb.append(km);
            sb.append("/");
            sb.append(duration);
            sb.append(" ");
            sb.append(min);
            String composedString = sb.toString();
            lengthView.setText(composedString);
        }
    }
}
