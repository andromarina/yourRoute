package maps.download.Items;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.yourRoute.model.Stop;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.android.maps.overlay.ArrayItemizedOverlay;
import org.mapsforge.android.maps.overlay.Overlay;
import org.mapsforge.android.maps.overlay.OverlayItem;
import org.mapsforge.core.GeoPoint;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 11/3/13
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class StopsCollectionGraphicItem implements IGraphicItem {
    private Overlay overlay;

    public StopsCollectionGraphicItem(Context context, ArrayList<Stop> stopsCollection) {
        this.overlay = createStopsCollectionSymbols(context, stopsCollection);
    }


    private GeoPoint createGeoPoint(Stop stop) {
        double latitude = stop.getLat();
        double longitude = stop.getLng();
        GeoPoint geoPoint = new GeoPoint(latitude, longitude);
        return geoPoint;
    }

    private Overlay createStopsCollectionSymbols(Context context, ArrayList<Stop> stopsCollection) {
        Drawable itemDefaultMarker = context.getResources().getDrawable(R.drawable.star_on);
        ArrayItemizedOverlay itemizedOverlay = new ArrayItemizedOverlay(itemDefaultMarker, true);

        for (int i = 0; i < stopsCollection.size(); ++i) {
            Stop stop = stopsCollection.get(i);
            OverlayItem item1 = new OverlayItem(createGeoPoint(stop), stop.getName(), "");
            itemizedOverlay.addItem(item1);
        }
        return itemizedOverlay;
    }

    @Override
    public void draw(MapView mapView) {
        mapView.getOverlays().add(this.overlay);
    }
}
