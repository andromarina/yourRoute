package com.yourRoute;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.TabHost;
import com.yourRoute.controls.CustomSearchField;
import com.yourRoute.model.CitiesRepository;

public class MainActivity extends FragmentActivity {

    private Button cityNameButton;
    private TabHost mainTabhost;
    private CustomSearchField stopSearchMain;
    private CustomSearchField stopSearchOptional;
    private CustomSearchField routeNumberSearch;
    private Button stopNameSearchButton;
    private Button routeNumberSearchButton;
    private MainActivityController mainActivityController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        configureActionBar();
        configureSearch();

        this.cityNameButton = (Button) findViewById(R.id.city_name_button);
        this.mainTabhost = (TabHost) findViewById(R.id.main_tabhost);

        CitiesRepository citiesRepository = new CitiesRepository(getContentResolver());
        this.mainActivityController = new MainActivityController(this, this, citiesRepository);
        this.mainActivityController.initialize();

    }

    @SuppressWarnings("deprecation")
    private void configureActionBar() {
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setCustomView(R.layout.city_name_button);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
    }

    private void configureSearch() {

        this.stopSearchMain = (CustomSearchField) findViewById(R.id.stop_search_field);
        this.stopSearchOptional = (CustomSearchField) findViewById(R.id.search_by_stop_optional);
        this.stopNameSearchButton = (Button) findViewById(R.id.stop_name_search_button);
        this.routeNumberSearch = (CustomSearchField) findViewById(R.id.route_number_search_field);
        this.routeNumberSearchButton = (Button) findViewById(R.id.route_number_search_button);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public Button getCityNameButton() {
        return this.cityNameButton;
    }

    public CustomSearchField getStopSearchMain() {
        return this.stopSearchMain;
    }

    public CustomSearchField getStopSearchOptional() {
        return this.stopSearchOptional;
    }

    public Button getStopNameSearchButton() {
        return this.stopNameSearchButton;
    }

    public TabHost getMainTabhost() {
        return this.mainTabhost;
    }

    public CustomSearchField getRouteNumberSearch() {
        return routeNumberSearch;
    }

    public Button getRouteNumberSearchButton() {
        return routeNumberSearchButton;
    }


}
