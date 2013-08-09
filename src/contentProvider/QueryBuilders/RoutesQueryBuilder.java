package contentProvider.QueryBuilders;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import contentProvider.Contracts.Routes;
import contentProvider.DataBaseHelper;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 6/28/13
 * Time: 8:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoutesQueryBuilder extends BaseQueryBuilder {

    private static final String LOG_TAG = "RoutesQueryBuilder";

    public RoutesQueryBuilder(DataBaseHelper dataBaseHelper) {
        super(dataBaseHelper);
    }

    @Override
    public String getType() {
        return Routes.DIR_TYPE;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(LOG_TAG, "URI_ROUTES");
        SQLiteQueryBuilder queryBuilder = createSQLiteQueryBuilder();
        queryBuilder.setTables(Routes.TABLE_NAME);
        if (TextUtils.isEmpty(sortOrder)) {
            sortOrder = Routes.NAME + " ASC";
        }
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }
}
