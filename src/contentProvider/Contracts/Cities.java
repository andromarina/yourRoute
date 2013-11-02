package contentProvider.Contracts;

import android.provider.BaseColumns;
import contentProvider.RoutesContentProvider;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 6/28/13
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Cities implements BaseColumns {

    // table name
    public static final String TABLE_NAME = "Cities";

    // columns
    public static final String NAME = "Name";
    public static final String LAT = "Lat";
    public static final String LNG = "Lng";

    // paths
    public static final String CITIES_PATH = "Cities";
    public static final String CITY_PATH = CITIES_PATH + "/#";

    // types
    public static final String DIR_TYPE = "vnd.android.cursor.dir/vnd." + RoutesContentProvider.AUTHORITY + "." + CITIES_PATH;
    public static final String ITEM_TYPE = "vnd.android.cursor.item/vnd." + RoutesContentProvider.AUTHORITY + "." + CITY_PATH;

}
