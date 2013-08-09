package com.yourRoute;

import android.content.Context;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import com.yourRoute.model.RoutesRepository;

public class RouteNumberSuggestionsTextWatcher implements TextWatcher {
    private RoutesRepository routesRepository;
    private MainActivity activity;

    public RouteNumberSuggestionsTextWatcher(RoutesRepository routesRepository, MainActivity activity) {
        this.routesRepository = routesRepository;
        this.activity = activity;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        String searchKey = s.toString();
        ImageButton clearButton = activity.getClearButton();

        if (searchKey.isEmpty()) {
            clearButton.setVisibility(View.INVISIBLE);
            return;
        }

        int savedCityId = Preferences.getSavedCityId();
        clearButton.setVisibility(View.VISIBLE);
        Cursor cursor = this.routesRepository.getRouteSuggestionsCursor(searchKey, savedCityId);
        String[] columns = new String[]{RoutesRepository.ROUTE_NAME_COLUMN_NAME};
        int[] columnTextId = new int[]{android.R.id.text1};
        Context context = activity.getBaseContext();
        SimpleCursorAdapter simple = new SimpleCursorAdapter(context,
                android.R.layout.simple_list_item_1, cursor, columns, columnTextId, 0);
        AutoCompleteTextView searchField = activity.getSearchMain();
        searchField.setAdapter(simple);

    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
