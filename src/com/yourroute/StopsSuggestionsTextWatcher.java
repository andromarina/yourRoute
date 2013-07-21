package com.yourroute;

import android.content.Context;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import com.yourroute.model.StopsRepository;

public class StopsSuggestionsTextWatcher implements TextWatcher {
    private StopsRepository stopsRepository;
    private MainActivity activity;

    public StopsSuggestionsTextWatcher(StopsRepository stopsRepository, MainActivity activity) {
        this.stopsRepository = stopsRepository;
        this.activity = activity;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        String searchKey = s.toString();
        ImageButton clearButton = activity.getClearButton();
        Log.d("Test", "c " + searchKey);
        if (searchKey.isEmpty()) {
            Log.d("Test", "I'm empty " + searchKey + "...");
            clearButton.setVisibility(View.INVISIBLE);
            return;
        }

        int savedCityId = Preferences.getSavedCityId();
        clearButton.setVisibility(View.VISIBLE);
        Cursor cursor = this.stopsRepository.getStopsSuggestionsCursor(searchKey, savedCityId);
        String[] columns = new String[]{StopsRepository.STOP_NAME_COLUMN_NAME};
        int[] columnTextId = new int[]{android.R.id.text1};
        Context context = activity.getBaseContext();
        SimpleCursorAdapter simple = new SimpleCursorAdapter(context,
                android.R.layout.simple_list_item_1, cursor, columns, columnTextId, 0);
        AutoCompleteTextView searchField = activity.getStreetSearchMain();
        searchField.setAdapter(simple);

    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
