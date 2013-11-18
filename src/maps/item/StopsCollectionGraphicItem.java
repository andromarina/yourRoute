package maps.item;

import android.R;
import android.graphics.drawable.Drawable;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.Stop;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.android.maps.MapViewPosition;
import org.mapsforge.android.maps.overlay.ListOverlay;
import org.mapsforge.android.maps.overlay.Marker;
import org.mapsforge.android.maps.overlay.Overlay;
import org.mapsforge.android.maps.overlay.OverlayItem;
import org.mapsforge.core.model.GeoPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 11/3/13
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class StopsCollectionGraphicItem implements IGraphicItem {
    private Overlay overlay;
    private GeoPoint centerGeoPoint;
    private int direction;

    public StopsCollectionGraphicItem(ArrayList<Stop> stopsCollection, int direction) {
        this.overlay = createStopsCollectionSymbols(stopsCollection, direction);
        this.direction = direction;
    }


    private GeoPoint createGeoPoint(Stop stop) {
        double latitude = stop.getLat();
        double longitude = stop.getLng();
        GeoPoint geoPoint = new GeoPoint(latitude, longitude);
        return geoPoint;
    }

    private Marker createMarker(GeoPoint geoPoint) {

        return new Marker(geoPoint, Marker.boundCenterBottom(getDrawableForMarker()));
    }

    private Drawable getDrawableForMarker() {
        Drawable itemDefaultMarker;
        switch (this.direction) {
            case 0:
                itemDefaultMarker = YourRouteApp.getYourRouteAppContext().getResources().getDrawable(R.drawable.star_on);
                return itemDefaultMarker;
            case 1:
                itemDefaultMarker = YourRouteApp.getYourRouteAppContext().getResources().getDrawable(R.drawable.star_off);
                return itemDefaultMarker;
           }
         return null;
        }

    private Overlay createStopsCollectionSymbols(ArrayList<Stop> stopsCollection, int direction) {
        ListOverlay listOverlay = new ListOverlay();
        List<OverlayItem> overlayItems = listOverlay.getOverlayItems();

        for (int i = 0; i < stopsCollection.size(); ++i) {
            Stop stop = stopsCollection.get(i);
            Marker marker = createMarker(createGeoPoint(stop));
            overlayItems.add(marker);
        }
        if (direction == 0) {
           this.centerGeoPoint = createCenterGeoPoint(stopsCollection);
        }
        return listOverlay;
    }

    @Override
    public void draw(MapView mapView) {
        mapView.getOverlays().add(this.overlay);
        if(this.centerGeoPoint != null) {
            mapView.getMapViewPosition().setCenter(this.centerGeoPoint);
        }
    }

    private GeoPoint createCenterGeoPoint(ArrayList<Stop> stopsCollection) {
        int middleStopIndex = stopsCollection.size()/2;
        Stop middleStop = stopsCollection.get(middleStopIndex);
        GeoPoint geoPoint = createGeoPoint(middleStop);
        return geoPoint;
    }
}
