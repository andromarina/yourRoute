package com.andromarina.yourRoute;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.*;
import com.andromarina.R;
import com.andromarina.yourRoute.model.CitiesRepository;
import com.andromarina.yourRoute.model.RoutesRepository;
import com.andromarina.yourRoute.model.StopsRepository;

public class MainActivity extends FragmentActivity {

    private Button cityNameButton;
    private RadioGroup searchMode;
    private AutoCompleteTextView searchMain;
    private AutoCompleteTextView streetSearchOptional;
    private RelativeLayout optionalStreetSearchLayout;
    private Button searchButton;
    private ImageButton clearButton;
    private ImageButton clearButtonOptional;
    private MainActivityController mainActivityController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureSearch();

        this.cityNameButton = (Button) findViewById(R.id.city_name_button);
        this.searchMode = (RadioGroup) findViewById(R.id.search_mode);

        CitiesRepository citiesRepository = new CitiesRepository(getContentResolver());
        StopsRepository stopsRepository = new StopsRepository(getContentResolver());
        RoutesRepository routesRepository = new RoutesRepository(getContentResolver());

        this.mainActivityController = new MainActivityController(this, this, citiesRepository, stopsRepository, routesRepository);
        this.mainActivityController.initialize();

    }

    @SuppressWarnings("deprecation")
    private void configureSearch() {

        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setCustomView(R.layout.city_name_button);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        this.searchMain = (AutoCompleteTextView) findViewById(R.id.street_search);
        this.streetSearchOptional = (AutoCompleteTextView) findViewById(R.id.optional_street_search);
        this.optionalStreetSearchLayout = (RelativeLayout) findViewById(R.id.optional_street_search_layout);
        this.searchButton = (Button) findViewById(R.id.search_button);
        this.clearButton = (ImageButton) findViewById(R.id.clear_button);
        this.clearButtonOptional = (ImageButton) findViewById(R.id.clear_button_second);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public Button getCityNameButton() {
        return this.cityNameButton;
    }

    public AutoCompleteTextView getSearchMain() {
        return this.searchMain;
    }

    public AutoCompleteTextView getStreetSearchOptional() {
        return this.streetSearchOptional;
    }

    public RelativeLayout getOptionalStreetSearchLayout() {
        return this.optionalStreetSearchLayout;
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

    public ImageButton getClearButton() {
        return this.clearButton;
    }

    public ImageButton getClearButtonOptional() {
        return this.clearButtonOptional;
    }

}
