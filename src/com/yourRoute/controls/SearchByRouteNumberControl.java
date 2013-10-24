package com.yourRoute.controls;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.yourRoute.R;
import com.yourRoute.mainActivity.RouteNumberSuggestionsProvider;
import com.yourRoute.searchResultsActivity.SearchResultsActivity;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 10/24/13
 * Time: 7:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchByRouteNumberControl extends LinearLayout {
    private Context context;
    private LinearLayout layout;
    private CustomSearchField routeNumbersSearch;

    public SearchByRouteNumberControl(Context context, AttributeSet attr) {
        super(context, attr);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = (LinearLayout) inflater.inflate(R.layout.route_number_search_tab, this);
        initializeRouteNumbersSearch();
    }

    private void initializeRouteNumbersSearch() {

        RouteNumberSuggestionsProvider routeNumberSuggestionsProvider = new RouteNumberSuggestionsProvider();
        this.routeNumbersSearch = (CustomSearchField) this.layout.findViewById(R.id.route_number_search_field);
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
        Button routeNumberSearchButton = (Button) this.layout.findViewById(R.id.route_number_search_button);
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
        this.context.startActivity(intent);
    }

    public void clearRouteNumberSearchField() {
        this.routeNumbersSearch.getAutoCompleteTextView().setText("");
    }

}
