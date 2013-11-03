package contentProvider.QueryBuilders;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import contentProvider.Contracts.Routes;
import contentProvider.DataBaseHelper;

public class RoutesByRouteNameQueryBuilder extends BaseQueryBuilder {
    private final String LOG_TAG = "RoutesByRouteNameQueryBuilder";

    public RoutesByRouteNameQueryBuilder(DataBaseHelper dataBaseHelper) {
        super(dataBaseHelper);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(LOG_TAG, " query was called");
        SQLiteQueryBuilder queryBuilder = createSQLiteQueryBuilder();
        queryBuilder.setTables(Routes.TABLE_NAME);
//
//        String[] projections = {Routes._ID,
//                Routes.NAME, Routes.FULL_NAME,
//                Routes.ROUTE_DISTANCE_COLUMN_NAME,
//                Routes.ROUTE_INTERVAL_COLUMN_NAME,
//                Routes.ROUTE_WORK_TIME_COLUMN_NAME,
//                Routes.START_END_COLUMN_NAME,
//                Routes.TRANSPORT_TYPE_ID_COLUMN_NAME,
//                Routes.CITY_ID_COLUMN_NAME,
//                Routes.STOP_NAME_FOR_SEARCH,
//                Routes.PRICE_COLUMN_NAME,
//                Routes.EXTREME_STOP_FIRST_ID_COLUMN_NAME,
//                Routes.EXTREME_STOP_SECOND_ID_COLUMN_NAME};

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, Routes.NAME, null, sortOrder);
        return cursor;
    }

    @Override
    public String getType() {
        return Routes.DIR_TYPE;
    }
}
