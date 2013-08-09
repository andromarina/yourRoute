package contentProvider.QueryBuilders;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import contentProvider.Contracts.Cities;
import contentProvider.DataBaseHelper;

public class CitiesQueryBuilder extends BaseQueryBuilder {

    private static final String LOG_TAG = "CitiesQueryBuilder";

    public CitiesQueryBuilder(DataBaseHelper dataBaseHelper) {
        super(dataBaseHelper);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(LOG_TAG, "URI_CITIES");
        SQLiteQueryBuilder queryBuilder = createSQLiteQueryBuilder();
        queryBuilder.setTables(Cities.TABLE_NAME);

        if (TextUtils.isEmpty(sortOrder)) {
            sortOrder = Cities.NAME + " ASC";
        }

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Override
    public String getType() {
        return Cities.DIR_TYPE;
    }
}
