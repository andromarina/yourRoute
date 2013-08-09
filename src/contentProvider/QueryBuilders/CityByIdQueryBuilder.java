package contentProvider.QueryBuilders;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import contentProvider.Contracts.Cities;
import contentProvider.DataBaseHelper;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 6/28/13
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class CityByIdQueryBuilder extends BaseQueryBuilder {

    private static final String LOG_TAG = "CityByIdQueryBuilder";

    public CityByIdQueryBuilder(DataBaseHelper dataBaseHelper) {
        super(dataBaseHelper);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String cityId = uri.getLastPathSegment();
        Log.d(LOG_TAG, "URI_CITY_ID, " + cityId);

        SQLiteQueryBuilder queryBuilder = createSQLiteQueryBuilder();
        queryBuilder.setTables(Cities.TABLE_NAME);
        if (TextUtils.isEmpty(selection)) {
            selection = Cities._ID + " = " + cityId;
        } else {
            selection = selection + " AND " + Cities._ID + " = " + cityId;
        }

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Override
    public String getType() {
        return Cities.ITEM_TYPE;
    }
}
