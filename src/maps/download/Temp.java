//package com.example.mapsforge;
//
//import android.*;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.drawable.Drawable;
//import org.download.android.maps.MapView;
//import org.download.android.maps.overlay.ArrayWayOverlay;
//import org.download.android.maps.overlay.OverlayWay;
//import org.download.core.GeoPoint;
//
///**
// * Created with IntelliJ IDEA.
// * User: mara
// * Date: 10/28/13
// * Time: 4:40 PM
// * To change this template use File | Settings | File Templates.
// */
//public class Temp {
////    MapView mapView = new MapView(this);
////    mapView.setClickable(true);
////    mapView.setBuiltInZoomControls(true);
////    mapView.setMapFile(new File("/sdcard/osmdroid/Ukraine.map"));
////    setContentView(mapView);
////    GeoPoint center = new GeoPoint(50.4500, 30.5233);
////    GeoPoint geoPoint2 = new GeoPoint(50.4480, 30.5333);
////    mapView.setCenter(center);
//
//    Paint wayDefaultPaintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
//    wayDefaultPaintFill.setStyle(Paint.Style.STROKE);
//    wayDefaultPaintFill.setColor(Color.BLUE);
//    wayDefaultPaintFill.setAlpha(160);
//    wayDefaultPaintFill.setStrokeWidth(7);
//    wayDefaultPaintFill.setStrokeJoin(Paint.Join.ROUND);
//    wayDefaultPaintFill.setPathEffect(new DashPathEffect(new float[]{20, 20}, 0));
//
//    Paint wayDefaultPaintOutline = new Paint(Paint.ANTI_ALIAS_FLAG);
//    wayDefaultPaintOutline.setStyle(Paint.Style.STROKE);
//    wayDefaultPaintOutline.setColor(Color.BLUE);
//    wayDefaultPaintOutline.setAlpha(128);
//    wayDefaultPaintOutline.setStrokeWidth(7);
//    wayDefaultPaintOutline.setStrokeJoin(Paint.Join.ROUND);
//
//    // create an individual paint object for an overlay way
//    Paint wayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//    wayPaint.setStyle(Paint.Style.FILL);
//    wayPaint.setColor(Color.YELLOW);
//    wayPaint.setAlpha(192);
//
//    // create the WayOverlay and add the ways
//    ArrayWayOverlay wayOverlay = new ArrayWayOverlay(wayDefaultPaintFill,
//            wayDefaultPaintOutline);
//
//    GeoPoint[] outer = new GeoPoint[2];
//    outer[0] = center;
//    outer[1] = geoPoint2;
//
//    GeoPoint[] inner = new GeoPoint[0];
//    GeoPoint[][] combined = new GeoPoint[1][2];
//    combined[0][0] = center;
//    combined[0][1] = geoPoint2;
//
//    OverlayWay way1 = new OverlayWay(combined);
//
//    wayOverlay.addWay(way1);
//    mapView.getOverlays().add(wayOverlay);
//
////    // create the default marker for overlay items
////    Drawable itemDefaultMarker = getResources().getDrawable(android.R.drawable.star_off);
////
////    // create an individual marker for an overlay item
////    Drawable itemMarker = getResources().getDrawable(android.R.drawable.star_on);
//
//    // create the ItemizedOverlay and add the items
////        ArrayItemizedOverlay itemizedOverlay = new ArrayItemizedOverlay(itemDefaultMarker, true);
////        OverlayItem item1 = new OverlayItem(center, "Berlin Victory Column",
////                "The Victory Column is a monument in Berlin, Germany.");
////        OverlayItem item2 = new OverlayItem(geoPoint2, "Brandenburg Gate",
////                "The Brandenburg Gate is one of the main symbols of Berlin and Germany.",
////                ItemizedOverlay.boundCenterBottom(itemMarker));
////        itemizedOverlay.addItem(item1);
////        itemizedOverlay.addItem(item2);
////    mapView.getOverlays().add(itemizedOverlay);

//}
