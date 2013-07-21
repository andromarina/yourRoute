package com.yourroute;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.*;
import com.yourroute.model.CitiesRepository;
import com.yourroute.model.StopsRepository;

public class MainActivity extends FragmentActivity {

    private Button cityNameButton;
    private RadioButton streetName;
    private RadioButton routeNumber;
    private AutoCompleteTextView streetSearchMain;
    private ImageButton clearButton;
    private MainActivityController mainActivityController;


    @Override
    protected void onStart() {
        super.onStart();
        this.mainActivityController.initialize();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureSearch();

        this.cityNameButton = (Button) findViewById(R.id.city_name_button);
        this.streetName = (RadioButton) findViewById(R.id.street_name_radio_button);
        this.routeNumber = (RadioButton) findViewById(R.id.route_number_radio_button);

        CitiesRepository citiesRepository = new CitiesRepository(getContentResolver());
        StopsRepository stopsRepository = new StopsRepository(getContentResolver());

        this.mainActivityController = new MainActivityController(this, this, citiesRepository, stopsRepository);

    }

    @SuppressWarnings("deprecation")
    private void configureSearch() {

        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setCustomView(R.layout.city_name_button);
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        SlidingDrawer slidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer);
        final ImageButton handler = (ImageButton) findViewById(R.id.handle);
        slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                handler.setRotationX(180);
            }
        });
        slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                handler.setRotationX(360);
            }
        });
        this.streetSearchMain = (AutoCompleteTextView) findViewById(R.id.street_search);
        this.clearButton = (ImageButton) findViewById(R.id.clear_button);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public Button getCityNameButton() {
        return this.cityNameButton;
    }

    public AutoCompleteTextView getStreetSearchMain() {
        return this.streetSearchMain;
    }

    public RadioButton getStreetNameButton() {
        return this.streetName;
    }

    public RadioButton getRouteNumberButton() {
        return this.routeNumber;
    }

    public ImageButton getClearButton() {
        return this.clearButton;
    }

}
