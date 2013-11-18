package maps.item;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import com.yourRoute.model.Point;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.android.maps.overlay.*;
import org.mapsforge.core.model.GeoPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 11/17/13
 * Time: 7:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class RouteLineGraphicItem implements IGraphicItem {
    private int direction;
    private ArrayList<Point> points;

    public RouteLineGraphicItem(ArrayList<Point> points, int direction) {
         this.direction = direction;
         this.points = points;
    }

    private ArrayList<GeoPoint> createGeoPointsArray(ArrayList<Point> points) {

        ArrayList<GeoPoint> result = new ArrayList<>();

        for(Point point : points) {
            float lat = point.getLat();
            float lng = point.getLng();
            GeoPoint geoPoint = new GeoPoint(lat, lng);
            result.add(geoPoint);
        }

        return result;
    }

    private Polyline createPolyline() {

        ArrayList<GeoPoint> geoPoints = createGeoPointsArray(this.points);
        PolygonalChain polygonalChain = new PolygonalChain(geoPoints);

        Paint paintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintStroke.setStyle(Paint.Style.STROKE);
        if (this.direction == 0) {
            paintStroke.setColor(Color.MAGENTA);
        } else {
            paintStroke.setColor(Color.BLUE);
        }

        paintStroke.setAlpha(128);
        paintStroke.setStrokeWidth(7);
        paintStroke.setPathEffect(new DashPathEffect(new float[] { 25, 15 }, 0));

        return new Polyline(polygonalChain, paintStroke);
    }
    @Override
    public void draw(MapView mapView) {
        ListOverlay listOverlay = new ListOverlay();
        List<OverlayItem> overlayItems = listOverlay.getOverlayItems();
        overlayItems.add(createPolyline());
        mapView.getOverlays().add(listOverlay);
    }
}
