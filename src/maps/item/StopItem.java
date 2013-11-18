package maps.item;

import android.R;
import android.graphics.drawable.Drawable;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.Stop;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.android.maps.overlay.ListOverlay;
import org.mapsforge.android.maps.overlay.Marker;
import org.mapsforge.android.maps.overlay.OverlayItem;
import org.mapsforge.core.model.GeoPoint;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 10/28/13
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class StopItem implements IGraphicItem {
    private Stop stop;

    public StopItem(Stop stop) {
        this.stop = stop;
    }

    private GeoPoint createGeoPoint() {
        double latitude = this.stop.getLat();
        double longitude = this.stop.getLng();
        GeoPoint geoPoint = new GeoPoint(latitude, longitude);
        return geoPoint;
    }

    @Override
    public void draw(MapView mapView) {
        ListOverlay listOverlay = new ListOverlay();
        List<OverlayItem> overlayItems = listOverlay.getOverlayItems();
        Marker marker = createMarker(createGeoPoint());
        overlayItems.add(marker);
        mapView.getOverlays().add(listOverlay);
    }

    private Marker createMarker(GeoPoint geoPoint) {
        Drawable itemDefaultMarker = YourRouteApp.getYourRouteAppContext().getResources().getDrawable(R.drawable.star_on);
        return new Marker(geoPoint, Marker.boundCenterBottom(itemDefaultMarker));
    }

}
