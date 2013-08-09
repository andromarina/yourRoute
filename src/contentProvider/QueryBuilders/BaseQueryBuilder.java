package contentProvider.QueryBuilders;


import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import contentProvider.DataBaseHelper;

public abstract class BaseQueryBuilder {
    protected DataBaseHelper dataBaseHelper;

    public BaseQueryBuilder(DataBaseHelper dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
    }

    public abstract Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder);

    public abstract String getType();

    protected SQLiteQueryBuilder createSQLiteQueryBuilder() {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        return qb;
    }
}
