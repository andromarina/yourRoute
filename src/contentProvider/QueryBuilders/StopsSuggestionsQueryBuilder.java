package contentProvider.QueryBuilders;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;
import contentProvider.Contracts.Stops;
import contentProvider.DataBaseHelper;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 7/7/13
 * Time: 3:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class StopsSuggestionsQueryBuilder extends BaseQueryBuilder {
    private static final String LOG_TAG = "StopsSuggestionsQueryBuilder";

    public StopsSuggestionsQueryBuilder(DataBaseHelper dataBaseHelper) {
        super(dataBaseHelper);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(LOG_TAG, " query was called");
        SQLiteQueryBuilder queryBuilder = createSQLiteQueryBuilder();
        queryBuilder.setTables(Stops.TABLE_NAME);

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, Stops.NAME_FOR_SEARCH, null, sortOrder);
        return cursor;
    }

    @Override
    public String getType() {
        return Stops.DIR_TYPE;
    }
}
