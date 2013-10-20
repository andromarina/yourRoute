package com.yourRoute.mainActivity;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.RoutesHolder;
import com.yourRoute.model.RoutesRepository;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 8/18/13
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class RouteNumberSuggestionsProvider implements SearchProvider {

    @Override
    public SimpleCursorAdapter getSuggestions(Context context, String searchKey) {

        RoutesHolder routesHolder = YourRouteApp.getRoutesHolder();
        int savedCityId = routesHolder.getSavedCityId();
        Cursor cursor = routesHolder.createRouteSuggestionsCursor(searchKey, savedCityId);
        String[] columns = new String[]{RoutesRepository.ROUTE_NAME_COLUMN_NAME};
        int[] columnTextId = new int[]{android.R.id.text1};
        SimpleCursorAdapter simple = new SimpleCursorAdapter(context,
                android.R.layout.simple_list_item_1, cursor, columns, columnTextId, 0);
        return simple;
    }
}
