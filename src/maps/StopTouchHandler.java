package maps;

import android.widget.Toast;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.Point;
import com.yourRoute.model.Stop;
import com.yourRoute.model.StopsCollection;
import org.mapsforge.core.model.GeoPoint;
import org.mapsforge.core.model.MapPosition;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 12/8/13
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class StopTouchHandler implements ITouchHandler {
    private static float delta = 0.001f;
    @Override
    public boolean onTouch(GeoPoint clickPoint) {
//        Toast.makeText(YourRouteApp.getYourRouteAppContext(),
//                "Touch on Lat: " + geoPoint.latitude + " ,Lng: " + geoPoint.longitude, Toast.LENGTH_SHORT).show();
        StopsCollection forwardStops = YourRouteApp.getSelections().getSelectedRoute().getForwardStops();
        for (Stop stop: forwardStops) {
            GeoPoint stopPoint = new GeoPoint(stop.getLat(), stop.getLng());
            if (isNearPoint(clickPoint, stopPoint, delta)) {
                Toast.makeText(YourRouteApp.getYourRouteAppContext(), stop.getName(), Toast.LENGTH_SHORT).show();

            }
        }


        return true;
    }

    private boolean isNearPoint(GeoPoint clickPoint, GeoPoint originPoint, float delta) {
        if (clickPoint.latitude < originPoint.latitude - delta) {
            return false;
        }

        if (clickPoint.latitude > originPoint.latitude + delta) {
            return false;
        }

        if (clickPoint.longitude < originPoint.longitude - delta) {
            return false;
        }

        if (clickPoint.longitude > originPoint.longitude + delta) {
            return false;
        }
        return true;
    }
}
