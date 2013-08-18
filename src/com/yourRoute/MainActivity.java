package com.yourRoute;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.RadioGroup;
import com.yourRoute.controls.CustomSearchField;
import com.yourRoute.model.CitiesRepository;

public class MainActivity extends FragmentActivity {

    private Button cityNameButton;
    private RadioGroup searchMode;
    private CustomSearchField searchMain;
    private CustomSearchField streetSearchOptional;
    private Button searchButton;
    private MainActivityController mainActivityController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureSearch();

        this.cityNameButton = (Button) findViewById(R.id.city_name_button);
        this.searchMode = (RadioGroup) findViewById(R.id.search_mode);

        CitiesRepository citiesRepository = new CitiesRepository(getContentResolver());
        this.mainActivityController = new MainActivityController(this, this, citiesRepository);
        this.mainActivityController.initialize();

    }

    @SuppressWarnings("deprecation")
    private void configureSearch() {

        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setCustomView(R.layout.city_name_button);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        this.searchMain = (CustomSearchField) findViewById(R.id.search_by_stop);
        this.streetSearchOptional = (CustomSearchField) findViewById(R.id.search_by_stop_optional);
        this.searchButton = (Button) findViewById(R.id.search_button);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public Button getCityNameButton() {
        return this.cityNameButton;
    }

    public CustomSearchField getSearchMain() {
        return this.searchMain;
    }

    public CustomSearchField getStreetSearchOptional() {
        return this.streetSearchOptional;
    }

    public Button getSearchButton() {
        return this.searchButton;
    }

    public RadioGroup getSearchModeRadioGroup() {
        return this.searchMode;
    }

    public int getCurrentSearchMode() {
        int checkedButtonId = getSearchModeRadioGroup().getCheckedRadioButtonId();
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
