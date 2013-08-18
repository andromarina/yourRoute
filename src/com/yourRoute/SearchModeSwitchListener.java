//package com.yourRoute;
//
//import android.text.InputType;
//import android.widget.AutoCompleteTextView;
//import android.widget.RadioGroup;
//import android.widget.RelativeLayout;
//import com.yourRoute.controls.CustomSearchField;
//import com.yourRoute.model.RoutesRepository;
//import com.yourRoute.model.StopsRepository;
//
//public class SearchModeSwitchListener implements RadioGroup.OnCheckedChangeListener {
//    private MainActivity activity;
//    private StopsRepository stopsRepository;
//    private RoutesRepository routesRepository;
//    private CustomSearchField searchField;
//    private CustomSearchField optionalSearchField;
//    RelativeLayout optionalStreetSearchLayout;
//
//    public SearchModeSwitchListener(MainActivity activity, StopsRepository stopsRepository, RoutesRepository routesRepository) {
//        this.activity = activity;
//        this.stopsRepository = stopsRepository;
//        this.routesRepository = routesRepository;
//        this.searchField = activity.getSearchMain();
//        this.optionalSearchField = activity.getStreetSearchOptional();
//        configureStreetNameSearch();
//
//    }
//
//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//        switch (checkedId) {
//            case (R.id.street_name_radio_button):
//                configureStreetNameSearch();
//                break;
//            case (R.id.route_number_radio_button):
//                configureRouteNumberSearch();
//                break;
//        }
//    }
//
//    private void configureStreetNameSearch() {
//        StopsSuggestionsTextWatcher stopsSuggestionsWatcher = new StopsSuggestionsTextWatcher(stopsRepository, activity);
//        StopsSuggestionsTextWatcherOptional suggestionsTextWatcherOptional = new StopsSuggestionsTextWatcherOptional(stopsRepository, activity);
//        optionalSearchField.addTextChangedListener(suggestionsTextWatcherOptional);
//        String streetNameHint = activity.getResources().getString(R.string.street_name_hint);
//        searchField.setHint(streetNameHint);
//        searchField.setText("");
//        searchField.setInputType(InputType.TYPE_CLASS_TEXT);
//        optionalSearchField.setText("");
//        //     optionalStreetSearchLayout.setVisibility(View.VISIBLE);
//    }
//
//    private void configureRouteNumberSearch() {
//
//        RouteNumberSuggestionsTextWatcher routeNumberSuggestionsWatcher =
//                new RouteNumberSuggestionsTextWatcher(routesRepository, activity);
//        searchField.addTextChangedListener(routeNumberSuggestionsWatcher);
//        String routeNumberHint = activity.getResources().getString(R.string.route_number_hint);
//        searchField.setHint(routeNumberHint);
//        searchField.setText("");
//        searchField.setInputType(InputType.TYPE_CLASS_NUMBER);
//        //     optionalStreetSearchLayout.setVisibility(View.INVISIBLE);
//
//    }
//
//}
