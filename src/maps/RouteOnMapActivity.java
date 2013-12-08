package maps;

import android.os.Bundle;
import android.widget.Toast;
import com.yourRoute.YourRouteApp;
import com.yourRoute.model.Route;
import maps.item.IGraphicItem;
import org.mapsforge.android.maps.MapActivity;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.map.reader.header.FileOpenResult;

import java.io.File;
import java.util.ArrayList;

public class RouteOnMapActivity extends MapActivity {
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
        byte zoomLevel = (byte)13;
        this.mapView.getMapViewPosition().setZoomLevel(zoomLevel);
        OnMapViewListener listener = new OnMapViewListener(YourRouteApp.getMap().getHandler());
        this.mapView.setOnTouchListener(listener);
        FileOpenResult fileOpenResult = mapView.setMapFile(new File(Map.PATH_TO_THE_MAP));
        if (!fileOpenResult.isSuccess()) {
            Toast.makeText(this, fileOpenResult.getErrorMessage(), Toast.LENGTH_LONG).show();
            finish();
        }

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
    }
}