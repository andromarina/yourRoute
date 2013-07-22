package com.yourroute;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.yourroute.model.RoutesRepository;

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

        RoutesRepository routesRepository = new RoutesRepository(getContentResolver());
        SearchResultsActivityController controller = new SearchResultsActivityController(this, this, routesRepository);
        controller.initialize();

    }

    public ListView getSearchResultsListView() {
        return this.searchResultsListView;
    }

    public TextView getNoSearchResultsTextView() {
        return this.noSearchResults;
    }
}
