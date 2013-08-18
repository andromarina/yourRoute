package com.yourRoute;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.yourRoute.controls.CustomSearchField;
import com.yourRoute.model.CitiesRepository;
import com.yourRoute.model.City;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 5/26/13
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivityController {

    private final String LOG_TAG = "MainActivityController";
    private Context context;
    private MainActivity activity;
    private CitiesRepository citiesRepository;
    private CustomSearchField streetSearchMain;
    private CustomSearchField streetSearchOptional;
    private ArrayList<City> cities;

    public MainActivityController(Context context, MainActivity activity, CitiesRepository citiesRepository) {
        this.context = context;
        this.activity = activity;
        this.citiesRepository = citiesRepository;
    }

    public void initialize() {

        initializeSearchByStopName();
        initializeSearchButton();
        int savedCityId = Preferences.getSavedCityId();
        initializeCityNameButton(savedCityId);
    }

    private void showCityChoiceDialog() {

        FragmentManager fm = this.activity.getSupportFragmentManager();
        this.cities = this.citiesRepository.getCities();

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onCityChanged(which);
                dialog.dismiss();
            }
        };
        CitiesChoiceDialog cityChoiceDialog = new CitiesChoiceDialog(this.context, this.cities, listener);
        cityChoiceDialog.show(fm, "CityChoiceDialog");
    }

    private void onCityChanged(int which) {

        Log.i(LOG_TAG, this.cities.get(which).getName() + " was selected");
        String name = this.cities.get(which).getName();
        Log.i(LOG_TAG, "chosen city " + name);
        activity.getCityNameButton().setText(name);
        int cityId = this.cities.get(which).getId();
        Preferences.saveCityId(cityId);
        streetSearchMain.getAutoCompleteTextView().setText("");
        streetSearchOptional.getAutoCompleteTextView().setText("");
    }

    private void initializeCityNameButton(int savedCityId) {

        String savedCityName = this.citiesRepository.getCity(savedCityId).getName();

        Button cityNameButton = this.activity.getCityNameButton();
        cityNameButton.setText(savedCityName);
        View.OnClickListener oclCityNameBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCityChoiceDialog();
            }
        };
        cityNameButton.setOnClickListener(oclCityNameBtn);
    }

    private void initializeSearchByStopName() {

        StopSuggestionsProvider stopSuggestionsProvider = new StopSuggestionsProvider();
        this.streetSearchMain = this.activity.getSearchMain();
        this.streetSearchMain.initialize(stopSuggestionsProvider);
        this.streetSearchOptional = this.activity.getStreetSearchOptional();
        this.streetSearchOptional.initialize(stopSuggestionsProvider);
    }

    private void initializeSearchButton() {
        Button searchButton = this.activity.getSearchButton();
        SearchButtonListener searchButtonListener = new SearchButtonListener(activity, context);
        searchButton.setOnClickListener(searchButtonListener);
    }

}
