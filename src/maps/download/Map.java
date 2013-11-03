package maps.download;

import android.content.Context;
import android.view.View;
import maps.download.Items.IGraphicItem;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.core.GeoPoint;

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
    private final String PATH_TO_THE_MAP = "/sdcard/osmdroid/Ukraine.map";
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
}
