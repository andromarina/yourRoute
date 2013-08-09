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

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, Routes.NAME, null, sortOrder);
        return cursor;
    }

    @Override
    public String getType() {
        return Routes.DIR_TYPE;
    }
}
