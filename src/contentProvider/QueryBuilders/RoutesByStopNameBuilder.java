package contentProvider.QueryBuilders;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import contentProvider.Contracts.Routes;
import contentProvider.DataBaseHelper;

public class RoutesByStopNameBuilder extends BaseQueryBuilder {

    private static final String LOG_TAG = "RoutesByStopNameBuilder";

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

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, "RouteId", null, sortOrder);
        return cursor;
    }

    @Override
    public String getType() {
        return Routes.DIR_TYPE;
    }
}
