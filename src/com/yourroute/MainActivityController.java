package com.yourroute;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.yourroute.model.CitiesRepository;
import com.yourroute.model.City;
import com.yourroute.model.RoutesRepository;
import com.yourroute.model.StopsRepository;

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
    private RoutesRepository routesRepository;
    private StopsRepository stopsRepository;
    private AutoCompleteTextView streetSearchMain;
    private AutoCompleteTextView streetSearchOptional;
    private ArrayList<City> cities;

    public MainActivityController(Context context, MainActivity activity, CitiesRepository citiesRepository,
                                  StopsRepository stopsRepository, RoutesRepository routesRepository) {
        this.context = context;
        this.activity = activity;
        this.citiesRepository = citiesRepository;
        this.stopsRepository = stopsRepository;
        this.routesRepository = routesRepository;
    }

    public void initialize() {

        initializeSearchMain();
        initializeSearchOptional();
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
        streetSearchMain.setText("");
        streetSearchOptional.setText("");
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

    private void initializeSearchMain() {

        this.streetSearchMain = this.activity.getSearchMain();
        ImageButton clearFieldButton = activity.getClearButton();
        clearFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                streetSearchMain.setText("");
            }
        });
        initializeSearchModeRadioGroup();
        //   this.streetSearchMain.setOnEditorActionListener(this);
        streetSearchMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedSuggestion = (String) ((TextView) view).getText();
                streetSearchMain.setText(selectedSuggestion);
            }
        });
    }

    private void initializeSearchOptional() {

        this.streetSearchOptional = this.activity.getStreetSearchOptional();
        ImageButton clearFieldButton = activity.getClearButtonOptional();
        clearFieldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                streetSearchOptional.setText("");
            }
        });
        initializeSearchModeRadioGroup();
        //  this.streetSearchOptional.setOnEditorActionListener(this);
        streetSearchOptional.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedSuggestion = (String) ((TextView) view).getText();
                streetSearchOptional.setText(selectedSuggestion);
            }
        });

    }

    private void initializeSearchButton() {
        Button searchButton = this.activity.getSearchButton();
        searchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchResultsActivity.class);
                String searchKeyMain = activity.getSearchMain().getText().toString();
                intent.putExtra("SearchPhrase", searchKeyMain);
                String searchKeyOptional = activity.getStreetSearchOptional().getText().toString();
                intent.putExtra("OptionalSearchPhrase", searchKeyOptional);
                int searchMode = getCurrentSearchMode(activity.getSearchModeRadioGroup());
                intent.putExtra("SearchMode", searchMode);
                activity.startActivity(intent);
            }
        });
    }

    private void initializeSearchModeRadioGroup() {
        RadioGroup searchMode = activity.getSearchModeRadioGroup();
        SearchModeSwitchListener switchListener = new SearchModeSwitchListener(activity, stopsRepository, routesRepository);
        searchMode.setOnCheckedChangeListener(switchListener);
    }

//    @Override
//    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//        String searchKey = v.getText().toString();
//
//        if (actionId != EditorInfo.IME_ACTION_SEARCH) {
//            return false;
//        }
//
//        Intent intent = new Intent(context, SearchResultsActivity.class);
//
//        if (v.getId() == R.id.street_search) {
//            intent.putExtra("SearchPhrase", searchKey);
//        }
//
//        else if (v.getId() == R.id.optional_street_search) {
//            intent.putExtra("OptionalSearchPhrase", searchKey);
//        }
//
//        int searchMode = getCurrentSearchMode(activity.getSearchModeRadioGroup());
//        intent.putExtra("SearchMode", searchMode);
//        activity.startActivity(intent);
//
//        return true;
//    }


    public int getCurrentSearchMode(RadioGroup radioGroup) {
        int checkedButtonId = radioGroup.getCheckedRadioButtonId();
        int searchMode = 0;
        switch (checkedButtonId) {
            case (R.id.street_name_radio_button):
                searchMode = 1;
                break;
            case (R.id.route_number_radio_button):
                searchMode = 2;
                break;
        }
        return searchMode;
    }

}
