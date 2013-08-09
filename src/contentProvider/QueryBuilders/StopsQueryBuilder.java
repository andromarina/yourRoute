package contentProvider.QueryBuilders;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import contentProvider.Contracts.Stops;
import contentProvider.DataBaseHelper;

public class StopsQueryBuilder extends BaseQueryBuilder {
    private static final String LOG_TAG = "StopsQueryBuilder";

    public StopsQueryBuilder(DataBaseHelper dataBaseHelper) {
        super(dataBaseHelper);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(LOG_TAG, " query was called");
        SQLiteQueryBuilder queryBuilder = createSQLiteQueryBuilder();
        queryBuilder.setTables("Stops INNER JOIN StopsOnRoutes ON (Stops._id = StopsOnRoutes.StopId )");

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Override
    public String getType() {
        return Stops.DIR_TYPE;
    }
}
