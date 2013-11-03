package maps.download.Items;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.Stop;
import maps.download.Items.IGraphicItem;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.android.maps.overlay.ArrayItemizedOverlay;
import org.mapsforge.android.maps.overlay.Overlay;
import org.mapsforge.android.maps.overlay.OverlayItem;
import org.mapsforge.core.GeoPoint;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 10/28/13
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class StopItem implements IGraphicItem {
    private Stop stop;
    private Overlay overlay;

    public StopItem(Context context, Stop stop) {
        this.stop = stop;
        this.overlay = createSymbol(context);
    }

    private Overlay createSymbol(Context context) {
        Drawable itemDefaultMarker = context.getResources().getDrawable(R.drawable.star_on);

        ArrayItemizedOverlay itemizedOverlay = new ArrayItemizedOverlay(itemDefaultMarker, true);

        OverlayItem item1 = new OverlayItem(createGeoPoint(), this.stop.getName(), "");
        itemizedOverlay.addItem(item1);
        return itemizedOverlay;
    }


    private GeoPoint createGeoPoint() {
        double latitude = this.stop.getLat();
        double longitude = this.stop.getLng();
        GeoPoint geoPoint = new GeoPoint(latitude, longitude);
        return geoPoint;
    }

    @Override
    public void draw(MapView mapView) {
        mapView.getOverlays().add(this.overlay);
    }

}
