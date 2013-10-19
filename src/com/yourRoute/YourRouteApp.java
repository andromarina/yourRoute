package com.yourRoute;

import android.app.Application;
import android.util.Log;
import com.yourRoute.model.RoutesHolder;
import com.yourRoute.model.SearchPhraseSelection;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 7/22/13
 * Time: 8:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class YourRouteApp extends Application {
    private final String LOG_TAG = "YourRouteApp";
    private static RoutesHolder routesHolder;
    private static SearchPhraseSelection searchPhraseSelection;

    @Override
    public void onCreate() {

        Log.d(LOG_TAG, "Application was created");
        super.onCreate();
        Preferences.initialize(this);
        routesHolder = new RoutesHolder(getContentResolver());
        searchPhraseSelection = new SearchPhraseSelection();

    }

    public  static RoutesHolder getRoutesHolder() {
        return routesHolder;
    }

    public static SearchPhraseSelection getSearchPhraseSelection() {
        return searchPhraseSelection;
    }

}
