package com.yourRoute.searchResultsActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.yourRoute.R;
import com.yourRoute.model.RoutesRepository;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 7/15/13
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchResultsActivity extends Activity {
    ListView searchResultsListView;
    TextView noSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_activity);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle(R.string.search_results);

        this.searchResultsListView = (ListView) findViewById(R.id.search_results);
        this.noSearchResults = (TextView) findViewById(R.id.no_search_results);

        SearchResultsActivityController controller = new SearchResultsActivityController(this);
        controller.initialize();

    }

    public ListView getSearchResultsListView() {
        return this.searchResultsListView;
    }

    public TextView getNoSearchResultsTextView() {
        return this.noSearchResults;
    }
}
