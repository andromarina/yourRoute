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
    public static final String NAME = "Number";
    public static final String FULL_NAME = "NumberString";
    public static final String ROUTE_DISTANCE_COLUMN_NAME = "Distance";
    public static final String ROUTE_INTERVAL_COLUMN_NAME = "Interval";
    public static final String ROUTE_WORK_TIME_COLUMN_NAME = "WorkTime";
    public static final String START_END_COLUMN_NAME = "StartEnd";
    public static final String TRANSPORT_TYPE_ID_COLUMN_NAME = "TransportTypeId";
    public static final String CITY_ID_COLUMN_NAME = "CityId";
    public static final String STOP_NAME_FOR_SEARCH = "StopNameForSearch";
    public static final String PRICE_COLUMN_NAME = "Price";
    public static final String EXTREME_STOP_FIRST_ID_COLUMN_NAME = "ExtremeStopFirstId";
    public static final String EXTREME_STOP_SECOND_ID_COLUMN_NAME = "ExtremeStopSecondId";
    public static final String GEOMETRY_FORWARD_COLUMN_NAME = "GeometryForward";
    public static final String GEOMETRY_BACKWARD_COLUMN_NAME = "GeometryBackward";


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
