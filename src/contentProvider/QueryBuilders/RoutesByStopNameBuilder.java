package contentProvider.QueryBuilders;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Debug;
import android.util.Log;
import contentProvider.Contracts.Routes;
import contentProvider.DataBaseHelper;

public class RoutesByStopNameBuilder extends BaseQueryBuilder {

    private final String LOG_TAG = getClass().getSimpleName();

    public RoutesByStopNameBuilder(DataBaseHelper dataBaseHelper) {
        super(dataBaseHelper);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Log.d(LOG_TAG, " query called");
        SQLiteQueryBuilder queryBuilder = createSQLiteQueryBuilder();
        queryBuilder.setTables("Routes\n" +
                "INNER JOIN StopsOnRoutes ON  StopsOnRoutes.RouteId=Routes._id\n" +
                "INNER JOIN Stops ON StopsOnRoutes.StopId=Stops._id");
        projection = new String[]{
                Routes.TABLE_NAME + "." + Routes._ID,
                Routes.TABLE_NAME + "." + Routes.FULL_NAME,
                Routes.TABLE_NAME + "." + Routes.START_END_COLUMN_NAME,
                Routes.TABLE_NAME + "." + Routes.TRANSPORT_TYPE_ID_COLUMN_NAME,
                Routes.TABLE_NAME + "." + Routes.ROUTE_DISTANCE_COLUMN_NAME,
                Routes.TABLE_NAME + "." + Routes.ROUTE_INTERVAL_COLUMN_NAME,
                Routes.TABLE_NAME + "." + Routes.ROUTE_WORK_TIME_COLUMN_NAME,
                Routes.TABLE_NAME + "." + Routes.CITY_ID_COLUMN_NAME,
                Routes.TABLE_NAME + "." + Routes.PRICE_COLUMN_NAME,
                Routes.TABLE_NAME + "." + Routes.EXTREME_STOP_FIRST_ID_COLUMN_NAME,
                Routes.TABLE_NAME + "." + Routes.EXTREME_STOP_SECOND_ID_COLUMN_NAME,
                Routes.TABLE_NAME + "." + Routes.GEOMETRY_FORWARD_COLUMN_NAME,
                Routes.TABLE_NAME + "." + Routes.GEOMETRY_BACKWARD_COLUMN_NAME
        };
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, "RouteId", null, sortOrder);

        return cursor;
    }

    @Override
    public String getType() {
        return Routes.DIR_TYPE;
    }
}
