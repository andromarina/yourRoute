package maps;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Environment;
import maps.item.IGraphicItem;
import maps.download.MapDownloadManager;

import java.io.File;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 10/28/13
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Map {
    public static final String FILE_NAME = "Ukraine.map";
    public static final String FOLDER_NAME = "yourroute";
    public static final String PATH_TO_THE_FOLDER = Environment.getExternalStorageDirectory() + File.separator + FOLDER_NAME;
    public static final String PATH_TO_THE_MAP = PATH_TO_THE_FOLDER + File.separator + FILE_NAME;
    private ArrayList<IGraphicItem> items;


    public Map() {
        this.items = new ArrayList<>();
    }

    public ArrayList<IGraphicItem> getGraphicItems() {
       return this.items;
    }

    public void clearGraphicItems() {
        this.items.clear();
    }

    private boolean isMapDownloaded() {
        File file = new File(PATH_TO_THE_MAP);
        if(file.exists()) {
            return true;
        }
        return false;
    }

    public boolean prepareMap(Context activityContext, FragmentManager fragmentManager) {
        if (isMapDownloaded()) {
            return true;
        }

        MapDownloadManager mapDownloadManager = new MapDownloadManager(activityContext, PATH_TO_THE_FOLDER);
        mapDownloadManager.showDownloadDialog(fragmentManager);

        return false;
    }


}
