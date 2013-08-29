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
 * Time: 8:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class RouteByIdQueryBuilder extends BaseQueryBuilder {

    private static final String LOG_TAG = "RouteByIdQueryBuilder";

    public RouteByIdQueryBuilder(DataBaseHelper dataBaseHelper) {
        super(dataBaseHelper);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String routeId = uri.getLastPathSegment();
        Log.d(LOG_TAG, "URI_ROUTE_ID " + routeId);

        SQLiteQueryBuilder queryBuilder = createSQLiteQueryBuilder();
        queryBuilder.setTables(Routes.TABLE_NAME);
        if (TextUtils.isEmpty(selection)) {
            selection = Routes._ID + " = " + routeId;
        } else {
            selection = selection + " AND" + Routes._ID + " = " + routeId;
        }
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Override
    public String getType() {
        return Routes.ITEM_TYPE;
    }
}
