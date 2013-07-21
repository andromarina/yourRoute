package com.yourroute;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.*;
import com.yourroute.model.CitiesRepository;
import com.yourroute.model.RoutesRepository;
import com.yourroute.model.StopsRepository;

public class MainActivity extends FragmentActivity {

    private Button cityNameButton;
    private RadioGroup searchMode;
    private AutoCompleteTextView searchMain;
    private SlidingDrawer slidingDrawer;
    private ImageButton clearButton;
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
        this.slidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer);
        final ImageButton handler = (ImageButton) findViewById(R.id.handle);
        this.slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                handler.setRotationX(180);
            }
        });
        this.slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                handler.setRotationX(360);
            }
        });
        this.searchMain = (AutoCompleteTextView) findViewById(R.id.street_search);
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

    public AutoCompleteTextView getSearchMain() {
        return this.searchMain;
    }

    public RadioGroup getSearchModeRadioGroup() {
        return this.searchMode;
    }

    public ImageButton getClearButton() {
        return this.clearButton;
    }

    public SlidingDrawer getSlidingDrawer() {
        return this.slidingDrawer;
    }

}
