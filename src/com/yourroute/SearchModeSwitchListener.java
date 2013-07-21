package com.yourroute;

import android.text.InputType;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.SlidingDrawer;
import com.yourroute.model.RoutesRepository;
import com.yourroute.model.StopsRepository;

public class SearchModeSwitchListener implements RadioGroup.OnCheckedChangeListener {
    private MainActivity activity;
    private StopsRepository stopsRepository;
    private RoutesRepository routesRepository;
    SlidingDrawer slidingDrawer;
    AutoCompleteTextView searchField;

    public SearchModeSwitchListener(MainActivity activity, StopsRepository stopsRepository, RoutesRepository routesRepository) {
        this.activity = activity;
        this.stopsRepository = stopsRepository;
        this.routesRepository = routesRepository;
        this.slidingDrawer = activity.getSlidingDrawer();
        this.searchField = activity.getSearchMain();
        configureStreetNameSearch();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case (R.id.street_name_radio_button):
                configureStreetNameSearch();
                break;
            case (R.id.route_number_radio_button):
                configureRouteNumberSearch();
                break;
        }
    }

    private void configureStreetNameSearch() {
        StopsSuggestionsTextWatcher stopsSuggestionsWatcher = new StopsSuggestionsTextWatcher(stopsRepository, activity);
        searchField.addTextChangedListener(stopsSuggestionsWatcher);
        String streetNameHint = activity.getResources().getString(R.string.street_name_hint);
        searchField.setHint(streetNameHint);
        searchField.setText("");
        searchField.setInputType(InputType.TYPE_CLASS_TEXT);
        slidingDrawer.setVisibility(View.VISIBLE);
    }

    private void configureRouteNumberSearch() {

        RouteNumberSuggestionsTextWatcher routeNumberSuggestionsWatcher =
                new RouteNumberSuggestionsTextWatcher(routesRepository, activity);
        searchField.addTextChangedListener(routeNumberSuggestionsWatcher);
        String routeNumberHint = activity.getResources().getString(R.string.route_number_hint);
        searchField.setHint(routeNumberHint);
        searchField.setText("");
        searchField.setInputType(InputType.TYPE_CLASS_NUMBER);
        slidingDrawer.setVisibility(View.GONE);

    }

}
