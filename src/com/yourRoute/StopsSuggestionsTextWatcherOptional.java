package com.yourRoute;

import android.content.Context;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.SimpleCursorAdapter;
import com.yourRoute.model.StopsRepository;

public class StopsSuggestionsTextWatcherOptional implements TextWatcher {
    private StopsRepository stopsRepository;
    private MainActivity activity;

    public StopsSuggestionsTextWatcherOptional(StopsRepository stopsRepository, MainActivity activity) {
        this.stopsRepository = stopsRepository;
        this.activity = activity;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        String searchKey = s.toString();
        Log.d("Test", "c " + searchKey);

        int savedCityId = Preferences.getSavedCityId();
        Cursor cursor = this.stopsRepository.getStopsSuggestionsCursor(searchKey, savedCityId);
        String[] columns = new String[]{StopsRepository.STOP_NAME_COLUMN_NAME};
        int[] columnTextId = new int[]{android.R.id.text1};
        Context context = activity.getBaseContext();
        SimpleCursorAdapter simple = new SimpleCursorAdapter(context,
                android.R.layout.simple_list_item_1, cursor, columns, columnTextId, 0);
        AutoCompleteTextView searchField = activity.getStreetSearchOptional().getAutoCompleteTextView();
        searchField.setAdapter(simple);

    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
