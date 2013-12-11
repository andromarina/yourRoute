package maps.item;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import org.mapsforge.android.maps.overlay.Marker;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.GeoPoint;
import org.mapsforge.core.model.Point;
import org.mapsforge.core.util.MercatorProjection;
import android.graphics.Paint;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 12/9/13
 * Time: 8:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class MarkerWithText extends Marker {
    private String text;
    private GeoPoint geoPoint;

    /**
     * @param geoPoint the initial geographical coordinates of this marker (may be null).
     * @param drawable the initial {@code Drawable} of this marker (may be null).
     */
    public MarkerWithText(GeoPoint geoPoint, Drawable drawable, String text) {
        super(geoPoint, drawable);
        this.text = text;
        this.geoPoint = geoPoint;
    }

    @Override
    public synchronized boolean draw(BoundingBox boundingBox, byte zoomLevel, Canvas canvas, Point canvasPosition) {
        double latitude = this.geoPoint.latitude;
        double longitude = this.geoPoint.longitude;
        int offset = 20;

        int pixelX = (int) (MercatorProjection.longitudeToPixelX(longitude, zoomLevel) - canvasPosition.x + offset/2);
        int pixelY = (int) (MercatorProjection.latitudeToPixelY(latitude, zoomLevel) - canvasPosition.y) + offset;
        Paint textStyle = new Paint(Paint.ANTI_ALIAS_FLAG);
        textStyle.setColor(Color.BLACK);
        textStyle.setTextSize(25);
        textStyle.setFakeBoldText(true);
        if (zoomLevel > 15) {
            canvas.drawText(this.text, pixelX, pixelY, textStyle);
        }
        return super.draw(boundingBox, zoomLevel, canvas, canvasPosition);
    }
}
