package maps.download;

import android.content.Context;
import android.os.Bundle;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.Route;
import maps.download.Items.IGraphicItem;
import org.mapsforge.android.maps.MapActivity;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.core.GeoPoint;

import java.io.File;
import java.util.ArrayList;

public class RouteOnMapActivity extends MapActivity {
    private final String PATH_TO_THE_MAP = "/sdcard/osmdroid/Ukraine.map";
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mapView = new MapView(this);
        configureMapView();
        setContentView(this.mapView);
        configureActionBar();
    }

    private void configureMapView() {
        this.mapView.setClickable(true);
        this.mapView.setBuiltInZoomControls(true);
        this.mapView.setMapFile(new File(PATH_TO_THE_MAP));
        GeoPoint centerOfUkraine = new GeoPoint(50.4500, 30.5233);
        this.mapView.setCenter(centerOfUkraine);
        drawAllGraphicItems();
    }

    private void configureActionBar() {

        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle(getRouteName());
    }

    private String getRouteName() {
        Route route = YourRouteApp.getSelections().getSelectedRoute();
        String routeName = route.getName();
        return routeName;
    }

    private void drawAllGraphicItems() {
        ArrayList<IGraphicItem> items = YourRouteApp.getMap().getGraphicItems();
        for(IGraphicItem item : items) {
            item.draw(this.mapView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        YourRouteApp.getMap().clearGraphicItems();
        this.mapView.getOverlays().clear();
    }
}