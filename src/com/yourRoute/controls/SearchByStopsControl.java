package com.yourRoute.controls;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import com.yourRoute.R;
import com.yourRoute.YourRouteApp;
import com.yourRoute.mainActivity.StopSuggestionsProvider;
import com.yourRoute.model.SelectedStops;
import com.yourRoute.searchResultsActivity.SearchResultsActivity;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 10/24/13
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchByStopsControl extends LinearLayout {
    private Context context;
    private LinearLayout layout;
    private CustomSearchField stopSearchMain;
    private CustomSearchField stopSearchOptional;

    public SearchByStopsControl(Context context, AttributeSet attr) {
        super(context, attr);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = (LinearLayout) inflater.inflate(R.layout.stop_name_search_tab, this);
        initializeSearchByStopName();
    }

    private void initializeSearchByStopName() {

        StopSuggestionsProvider stopSuggestionsProvider = new StopSuggestionsProvider();
        this.stopSearchMain = (CustomSearchField) this.layout.findViewById(R.id.stop_search_field);
        this.stopSearchMain.initialize(stopSuggestionsProvider);
        this.stopSearchOptional = (CustomSearchField) this.layout.findViewById(R.id.search_by_stop_optional);
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

        Button stopNameSearchButton = (Button) this.layout.findViewById(R.id.stop_name_search_button);
        stopNameSearchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForSearchByStopName();
            }
        });
    }

    private void startActivityForSearchByStopName() {

        String stopSearchMainKey = this.stopSearchMain.getValidatedString();
        int length = stopSearchMainKey.length();
        if (!StringUtils.isNotBlank(stopSearchMainKey) || length < 3) {
            Toast emptyStartStopField = Toast.makeText(this.context, R.string.need_to_fill_main_search_field, 3 * 1000);
            emptyStartStopField.show();
            return;
        }
        Intent intent = new Intent(this.context, SearchResultsActivity.class);
        intent.putExtra("SearchMode", 1);
        intent.putExtra("StopName", stopSearchMainKey);
        SelectedStops selectedStops = YourRouteApp.getSelectedStops();
        selectedStops.saveSelectedStop(stopSearchMainKey);
        String stopNameOptional = this.stopSearchOptional.getValidatedString();
        intent.putExtra("OptionalStopName", stopNameOptional);
        selectedStops.saveOptionalSelectedStop(stopNameOptional);
        this.context.startActivity(intent);
    }

    public void clearStopSearchFields() {
        this.stopSearchMain.getAutoCompleteTextView().setText("");
        this.stopSearchOptional.getAutoCompleteTextView().setText("");
    }
}
