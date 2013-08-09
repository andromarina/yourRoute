package contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.net.Uri;


/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 6/30/13
 * Time: 5:32 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseContentProvider extends ContentProvider {

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        return null;
    }

}
