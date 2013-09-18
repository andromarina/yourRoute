package contentProvider.Contracts;

import android.provider.BaseColumns;
import contentProvider.RoutesContentProvider;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 6/28/13
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Stops implements BaseColumns {

    // table name
    public static final String TABLE_NAME = "Stops";
    public static final String NAME = "StopName";
    public static final String NAME_FOR_SEARCH = "StopNameForSearch";
    public static final String STOP_NAME_FOR_SEARCH = "StopNameForSearch";
    public static final String STOP_INDEX_COLUMN_NAME = "StopIndex";
    public static final String ROUTE_ID_COLUMN_NAME = "RouteId";
    public static final String CITY_ID_COLUMN_NAME = "CityId";

    // paths
    public static final String STOPS_PATH = "Stops";
    public static final String STOP_PATH = STOPS_PATH + "/#";
    public static final String STOPS_SUGGESTIONS_PATH = "StopsSuggestions";

    // types
    public static final String DIR_TYPE = "vnd.android.cursor.dir/vnd." + RoutesContentProvider.AUTHORITY + "." + STOPS_PATH;
    public static final String ITEM_TYPE = "vnd.android.cursor.item/vnd." + RoutesContentProvider.AUTHORITY + "." + STOP_PATH;
}
