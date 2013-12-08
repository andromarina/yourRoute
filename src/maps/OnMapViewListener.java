package maps;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.yourRoute.model.StopsCollection;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.core.model.GeoPoint;
import org.mapsforge.core.util.MercatorProjection;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 12/7/13
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class OnMapViewListener implements View.OnTouchListener {

    private final String LOG_TAG = getClass().getSimpleName();
    private ITouchHandler handler;

    public OnMapViewListener(ITouchHandler handler) {
         this.handler = handler;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() != MotionEvent.ACTION_DOWN) {
            return false;
        }
        double xClicked = event.getX();
        double yClicked = event.getY();
        MapView mapView = (MapView) v;
        GeoPoint touchPoint = createGeoPointFromTouch(xClicked, yClicked, mapView);

        if (handler.onTouch(touchPoint) == true) {
            return false;
        }
        return false;
    }

    private GeoPoint createGeoPointFromTouch(double xClicked, double yClicked, MapView mapView) {

        byte zoomLevel = mapView.getMapViewPosition().getZoomLevel();

        double minLongitude = mapView.getMapViewPosition().getBoundingBox().minLongitude;
        double minPixelX = MercatorProjection.longitudeToPixelX(minLongitude, zoomLevel);
        double longitude =  MercatorProjection.pixelXToLongitude(minPixelX + xClicked, zoomLevel);
        Log.d(LOG_TAG, "Longitude x = " + longitude);

        double minLatitude = mapView.getMapViewPosition().getBoundingBox().minLatitude;
        double minPixelY = MercatorProjection.latitudeToPixelY(minLatitude, zoomLevel);
        double latitude = MercatorProjection.pixelYToLatitude(minPixelY + yClicked, zoomLevel);
        Log.d(LOG_TAG, "Latitude y = " + latitude);

        GeoPoint geoPoint = new GeoPoint(latitude, longitude);
        return geoPoint;
    }

}
