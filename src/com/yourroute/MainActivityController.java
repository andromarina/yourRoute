package com.yourroute;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/26/13
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivityController {

    private Context context;
    private MainActivity activity;
    private CitiesRepository citiesRepository;
    private RoutesRepository routesRepository;
    private ArrayList<City> cities;

    public MainActivityController(Context context, MainActivity activity, CitiesRepository citiesRepository, RoutesRepository routesRepository) {
        this.context = context;
        this.activity = activity;
        this.citiesRepository = citiesRepository;
        this.routesRepository = routesRepository;
    }

    public void initialize() {

        Preferences.initialize(context, activity);
        int savedCityId = Preferences.getSavedCityId();

        initializeCityNameButton(savedCityId);
        initializeRoutesListView(savedCityId);
    }

    private void showCityChoiceDialog() {

        FragmentManager fm = this.activity.getSupportFragmentManager();
        cities = citiesRepository.getCities();

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onCityChanged(which);
                dialog.dismiss();
            }
        };
        CitiesChoiceDialog cityChoiceDialog = new CitiesChoiceDialog(context, cities, listener);
        cityChoiceDialog.show(fm, "CityChoiceDialog");
    }

    private void onCityChanged(int which) {

        Log.i("Test", cities.get(which).getName() + " was selected");
        String name = cities.get(which).getName();
        Log.i("Test", "chosen city " + name);
        activity.getCityNameButton().setText(name);
        int cityId = cities.get(which).getId();
        Preferences.saveCityId(cityId);
    }

    private void initializeRoutesListView(int savedCityId) {

        ArrayList<Route> routes = this.routesRepository.getRoutesByCityID(savedCityId);
        ListView listViewMain = activity.getRoutesListView();
        ListAdapter adapterList = new ListAdapter(context, R.layout.list_item, routes);
        listViewMain.setAdapter(adapterList);
    }

    private void initializeCityNameButton(int savedCityId) {

        String savedCityName = this.citiesRepository.getCity(savedCityId).getName();

        Button cityNameButton = activity.getCityNameButton();
        cityNameButton.setText(savedCityName);
        View.OnClickListener oclCityNameBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCityChoiceDialog();
            }
        };
        cityNameButton.setOnClickListener(oclCityNameBtn);
    }

}
