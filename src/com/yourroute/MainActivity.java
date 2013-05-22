package com.yourroute;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private ArrayList<City> cities;
    private Button cityNameButton;
    private CitiesRepository citiesRepository;
    private RoutesRepository routesRepository;

    @Override
    protected void onStart() {
        super.onStart();
        this.cityNameButton = (Button) findViewById(R.id.city_name_button);
        int savedCityId = Preferences.getSavedCityId();
        String savedCityName = citiesRepository.getCity(savedCityId).getName();
        cityNameButton.setText(savedCityName);
        View.OnClickListener oclCityNameBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCityChoiceDialog();
            }
        };
        cityNameButton.setOnClickListener(oclCityNameBtn);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Preferences.initialize(getBaseContext(), this);

        this.routesRepository = new RoutesRepository(getContentResolver());
        ArrayList<Route> routes = this.routesRepository.getRoutes();


        getActionBar().setDisplayShowTitleEnabled(false);

        this.citiesRepository = new CitiesRepository(getContentResolver());
        this.cities = citiesRepository.getCities();

        ListView listViewMain = (ListView) findViewById(R.id.listViewMain);

        ListAdapter adapterList = new ListAdapter(this, R.layout.list_item, routes);

        listViewMain.setAdapter(adapterList);
        getActionBar().setCustomView(R.layout.city_name_indicator);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);

    }

    protected void showCityChoiceDialog() {

        FragmentManager fm = getSupportFragmentManager();
        CitiesChoiceDialog cityChoiceDialog = new CitiesChoiceDialog(this, cities, this.cityNameButton);
        cityChoiceDialog.show(fm, "CityChoiceDialog");
    }
}
