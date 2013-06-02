package com.yourroute;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import com.yourroute.model.RoutesRepository;


public class RouteDetailsActivity extends FragmentActivity {
    private TabHost direction_tabhost;
    private TextView route_name;
    private TextView startEndView;
    private TextView operationHoursView;
    private LinearLayout operationHoursLayout;
    private TextView intervalView;
    private LinearLayout intervalLayout;
    private TextView lengthView;
    private LinearLayout lengthLayout;
    private RouteDetailsActivityController controller;

    @Override
    protected void onStart() {
        super.onStart();
        this.controller.setValues();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_card);
        RoutesRepository routesRepository = new RoutesRepository(getContentResolver());
        this.controller = new RouteDetailsActivityController(this, routesRepository);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setCustomView(R.layout.route_name_view);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

        this.direction_tabhost = (TabHost) findViewById(R.id.direction_tabhost);
        this.route_name = (TextView) findViewById(R.id.route_name_view);
        this.startEndView = (TextView) findViewById(R.id.start_end);
        this.operationHoursView = (TextView) findViewById(R.id.operation_time);
        this.operationHoursLayout = (LinearLayout) findViewById(R.id.operation_time_layout);
        this.intervalView = (TextView) findViewById(R.id.interval);
        this.intervalLayout = (LinearLayout) findViewById(R.id.interval_layout);
        this.lengthView = (TextView) findViewById(R.id.length);
        this.lengthLayout = (LinearLayout) findViewById(R.id.length_layout);

        this.controller.initialize();
    }

    public TabHost getDirection_tabhost() {
        return this.direction_tabhost;
    }

    public TextView getRoute_name_View() {
        return this.route_name;
    }

    public TextView getStartEndView() {
        return this.startEndView;
    }

    public TextView getOperationHoursView() {
        return this.operationHoursView;
    }

    public LinearLayout getOperationHoursLayout() {
        return this.operationHoursLayout;
    }

    public TextView getIntervalView() {
        return this.intervalView;
    }

    public LinearLayout getIntervalLayout() {
        return this.intervalLayout;
    }

    public TextView getLengthView() {
        return this.lengthView;
    }

    public LinearLayout getLengthLayout() {
        return this.lengthLayout;
    }

}


