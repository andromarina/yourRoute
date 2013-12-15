package maps.download;

import android.app.DownloadManager;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;
import com.yourRoute.R;
import maps.Map;
import org.w3c.dom.DOMError;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 10/13/13
 * Time: 2:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapDownloadManager {
    private static Context context;
    private final String pathToFolder;
    private static final String url = "http://download.mapsforge.org/maps/europe/ukraine.map";

    public MapDownloadManager (Context context, String pathToFolder) {
       this.context = context;
       this.pathToFolder = pathToFolder;
    }


    private static boolean isDownloadManagerAvailable() {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setClassName("com.android.providers.downloads.ui", "com.android.providers.downloads.ui.DownloadList");
            List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
                    PackageManager.MATCH_DEFAULT_ONLY);
            return list.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isDirectoryExists() {
        File f = new File(this.pathToFolder);
        if(f.isDirectory()) {
           return true;
        }
        return false;
    }

    private boolean makeDir() {

        if(isDirectoryExists()){
           return true;
        }
        File folder = new File(this.pathToFolder);
        return folder.mkdir();
    }

    public boolean startDownload() {
        if (isDownloadManagerAvailable() == false) {
            Toast.makeText(context, "Download manager is not available", 3).show();
            return false;
        }

        if (makeDir() == false) {
            Toast.makeText(context, "Can/'t create directory", 3).show();
            return false;
        }

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription(context.getResources().getString(R.string.map_loading));
        request.setTitle(context.getResources().getString(R.string.app_name));

        // in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Map.FOLDER_NAME, Map.FILE_NAME);

       // get download service and enqueue file
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
        return true;
    }

    public void showDownloadDialog(FragmentManager fragmentManager) {

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startDownload();
                dialog.dismiss();
            }
        };
        MapDownloadDialog dialog = new MapDownloadDialog(context, listener);
        dialog.show(fragmentManager, "LoadMapDialog");
    }
}
