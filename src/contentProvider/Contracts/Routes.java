package contentProvider.Contracts;

import android.provider.BaseColumns;
import contentProvider.RoutesContentProvider;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 6/28/13
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Routes implements BaseColumns {

    // table name
    public static final String TABLE_NAME = "Routes";

    // columns
    public static final String NAME = "RouteName";

    // paths
    public static final String ROUTES_PATH = "Routes";
    public static final String ROUTE_PATH = ROUTES_PATH + "/#";
    public static final String ROUTES_BY_STOP_NAME_PATH = "RouteByStopName";
    public static final String ROUTES_BY_ROUTE_NAME_PATH = "RoutesByRouteName";
    public static final String ROUTES_BY_ROUTE_ID_PATH = "RoutesByRouteId";

    // types
    public static final String DIR_TYPE = "vnd.android.cursor.dir/vnd." + RoutesContentProvider.AUTHORITY + "." + ROUTES_PATH;
    public static final String ITEM_TYPE = "vnd.android.cursor.item/vnd." + RoutesContentProvider.AUTHORITY + "." + ROUTE_PATH;
}