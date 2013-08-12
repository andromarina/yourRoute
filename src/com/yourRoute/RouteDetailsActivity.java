package com.yourRoute;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.*;
import com.yourRoute.model.RoutesRepository;
import com.yourRoute.model.StopsRepository;


public class RouteDetailsActivity extends FragmentActivity {
    private TabHost direction_tabhost;
    private TextView startEndView;
    private ImageView carTypeIcon;
    private TextView operationHoursView;
    private LinearLayout operationHoursLayout;
    private TextView intervalView;
    private LinearLayout intervalLayout;
    private TextView lengthView;
    private LinearLayout lengthLayout;
    private ListView forwardStopsList;
    private ListView backwardStopsList;
    private RouteDetailsActivityController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_card);
        RoutesRepository routesRepository = new RoutesRepository(getContentResolver());
        StopsRepository stopsRepository = new StopsRepository(getContentResolver());

        this.direction_tabhost = (TabHost) findViewById(R.id.direction_tabhost);
        this.startEndView = (TextView) findViewById(R.id.start_end);
        this.carTypeIcon = (ImageView) findViewById(R.id.ic_car_type);
        this.operationHoursView = (TextView) findViewById(R.id.operation_time);
        this.operationHoursLayout = (LinearLayout) findViewById(R.id.operation_time_layout);
        this.intervalView = (TextView) findViewById(R.id.interval);
        this.intervalLayout = (LinearLayout) findViewById(R.id.interval_layout);
        this.lengthView = (TextView) findViewById(R.id.length);
        this.lengthLayout = (LinearLayout) findViewById(R.id.length_layout);
        this.forwardStopsList = (ListView) findViewById(R.id.forward_stops_list);
        this.backwardStopsList = (ListView) findViewById(R.id.backward_stops_list);

        this.controller = new RouteDetailsActivityController(this, routesRepository, stopsRepository);
        this.controller.initialize();
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle(controller.getRouteName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public TabHost getDirection_tabhost() {
        return this.direction_tabhost;
    }

    public TextView getStartEndView() {
        return this.startEndView;
    }

    public ImageView getCarTypeIcon() {
        return this.carTypeIcon;
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

    public ListView getForwardStopsList() {
        return this.forwardStopsList;
    }

    public ListView getBackwardStopsList() {
        return this.backwardStopsList;
    }

}

