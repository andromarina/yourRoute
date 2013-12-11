package maps.item;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.yourRoute.R;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.Stop;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.android.maps.overlay.*;
import org.mapsforge.core.model.GeoPoint;

import java.util.ArrayList;
import java.util.Collection;
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

    public StopsCollectionGraphicItem(ArrayList<Stop> stopsCollection, int direction) {
        this.overlay = createStopsCollectionSymbols(stopsCollection, direction);
    }


    private GeoPoint createGeoPoint(Stop stop) {
        double latitude = stop.getLat();
        double longitude = stop.getLng();
        GeoPoint geoPoint = new GeoPoint(latitude, longitude);
        return geoPoint;
    }

    private MarkerWithText createMarker(GeoPoint geoPoint, int direction, String text) {
        MarkerWithText marker = new MarkerWithText(geoPoint, Marker.boundCenterBottom(getDrawableForMarker(direction)),
                text);
        return marker;
    }

    private Polyline createRectangular(GeoPoint geoPoint) {
        PolygonalChain chain = new PolygonalChain(createPointsForRectangular(geoPoint));
        Paint paintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintStroke.setStyle(Paint.Style.STROKE);
        paintStroke.setColor(Color.BLACK);

        paintStroke.setAlpha(128);
        paintStroke.setStrokeWidth(3);
        return new Polyline(chain, paintStroke);
    }

    private Collection<GeoPoint> createPointsForRectangular(GeoPoint geoPoint) {
        float delta = 0.001f;

        Collection<GeoPoint> collection = new ArrayList<GeoPoint>();
        collection.add(new GeoPoint(geoPoint.latitude - delta, geoPoint.longitude - delta));
        collection.add(new GeoPoint(geoPoint.latitude - delta, geoPoint.longitude + delta));
        collection.add(new GeoPoint(geoPoint.latitude + delta, geoPoint.longitude + delta));
        collection.add(new GeoPoint(geoPoint.latitude + delta, geoPoint.longitude - delta));
        collection.add(new GeoPoint(geoPoint.latitude - delta, geoPoint.longitude - delta));
        return collection;
    }

    private Drawable getDrawableForMarker(int direction) {
        Drawable itemDefaultMarker;
        switch (direction) {
            case 0:
                itemDefaultMarker = YourRouteApp.getYourRouteAppContext().getResources().getDrawable(R.drawable.point_red);
                return itemDefaultMarker;
            case 1:
                itemDefaultMarker = YourRouteApp.getYourRouteAppContext().getResources().getDrawable(R.drawable.point_blue);
                return itemDefaultMarker;
           }
         return null;
        }

    private Overlay createStopsCollectionSymbols(ArrayList<Stop> stopsCollection, int direction) {
        ListOverlay listOverlay = new ListOverlay();
        List<OverlayItem> overlayItems = listOverlay.getOverlayItems();

        for (int i = 0; i < stopsCollection.size(); ++i) {
            Stop stop = stopsCollection.get(i);
            MarkerWithText marker = createMarker(createGeoPoint(stop), direction, stop.getName());
        //    Polyline rectangular = createRectangular(createGeoPoint(stop));
            overlayItems.add(marker);
        //   overlayItems.add(rectangular);
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
