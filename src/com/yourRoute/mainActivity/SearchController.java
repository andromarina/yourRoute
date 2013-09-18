package com.yourRoute.mainActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.yourRoute.R;
import com.yourRoute.YourRouteApp;
import com.yourRoute.controls.CustomSearchField;
import com.yourRoute.searchResultsActivity.SearchResultsActivity;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 9/2/13
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchController {
    private MainActivity activity;
    private Context context;
    private CustomSearchField stopSearchMain;
    private CustomSearchField stopSearchOptional;
    private CustomSearchField routeNumbersSearch;

    public SearchController(Context context, MainActivity activity) {
        this.activity = activity;
        this.context = context;
    }

    public void initialize() {
        initializeSearchByStopName();
        initializeRouteNumbersSearch();
    }

    private void initializeSearchByStopName() {

        StopSuggestionsProvider stopSuggestionsProvider = new StopSuggestionsProvider();
        this.stopSearchMain = this.activity.getStopSearchMain();
        this.stopSearchMain.initialize(stopSuggestionsProvider);
        this.stopSearchOptional = this.activity.getStopSearchOptional();
        this.stopSearchOptional.initialize(stopSuggestionsProvider);
        this.stopSearchMain.getAutoCompleteTextView().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    stopSearchOptional.requestFocus();
                    return true;
                }
                return false;
            }
        });
        this.stopSearchOptional.getAutoCompleteTextView().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    startActivityForSearchByStopName();
                    return true;
                }
                return false;
            }
        });

        Button stopNameSearchButton = this.activity.getStopNameSearchButton();
        stopNameSearchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForSearchByStopName();
            }
        });
    }

    private void initializeRouteNumbersSearch() {

        RouteNumberSuggestionsProvider routeNumberSuggestionsProvider = new RouteNumberSuggestionsProvider();
        this.routeNumbersSearch = this.activity.getRouteNumberSearch();
        this.routeNumbersSearch.initialize(routeNumberSuggestionsProvider);
        this.routeNumbersSearch.getAutoCompleteTextView().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    startActivityForSearchByRouteNumber();
                    return true;
                }
                return false;
            }
        });
        Button routeNumberSearchButton = this.activity.getRouteNumberSearchButton();
        routeNumberSearchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForSearchByRouteNumber();
            }
        });
    }

    private void startActivityForSearchByRouteNumber() {

        String routeNumberSearchKey = routeNumbersSearch.getValidatedString();
        if (!StringUtils.isNotBlank(routeNumberSearchKey)) {
            Toast emptyRouteNumberField = Toast.makeText(context, R.string.need_to_fill_route_search_field, 3 * 1000);
            emptyRouteNumberField.show();
            return;
        }
        Intent intent = new Intent(context, SearchResultsActivity.class);
        intent.putExtra("SearchMode", 2);
        intent.putExtra("RouteNumber", routeNumberSearchKey);
        activity.startActivity(intent);
    }

    private void startActivityForSearchByStopName() {

        String stopSearchMainKey = stopSearchMain.getValidatedString();
        int length = stopSearchMainKey.length();
        if (!StringUtils.isNotBlank(stopSearchMainKey) || length < 3) {
            Toast emptyStartStopField = Toast.makeText(context, R.string.need_to_fill_main_search_field, 3 * 1000);
            emptyStartStopField.show();
            return;
        }
        Intent intent = new Intent(context, SearchResultsActivity.class);
        intent.putExtra("SearchMode", 1);
        intent.putExtra("StopName", stopSearchMainKey);
        YourRouteApp.saveSearchPhrase(stopSearchMainKey);
        String stopNameOptional = stopSearchOptional.getValidatedString();
        intent.putExtra("OptionalStopName", stopNameOptional);
        YourRouteApp.saveOptionalSearchPhrase(stopNameOptional);
        activity.startActivity(intent);
    }

    public void clearAllSearchFields() {

        this.stopSearchMain.getAutoCompleteTextView().setText("");
        this.stopSearchOptional.getAutoCompleteTextView().setText("");
        this.routeNumbersSearch.getAutoCompleteTextView().setText("");
    }
}
