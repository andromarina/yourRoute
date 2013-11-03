package maps.download.Items;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import maps.download.Items.IGraphicItem;
import org.mapsforge.android.maps.MapView;
import org.mapsforge.android.maps.overlay.ArrayWayOverlay;
import org.mapsforge.android.maps.overlay.OverlayWay;
import org.mapsforge.core.GeoPoint;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 10/29/13
 * Time: 9:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class RouteGraphicItem implements IGraphicItem {

    public RouteGraphicItem() {

    }
    @Override
    public void draw(MapView mapView) {


    Paint wayDefaultPaintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
    wayDefaultPaintFill.setStyle(Paint.Style.STROKE);
    wayDefaultPaintFill.setColor(Color.BLUE);
    wayDefaultPaintFill.setAlpha(160);
    wayDefaultPaintFill.setStrokeWidth(7);
    wayDefaultPaintFill.setStrokeJoin(Paint.Join.ROUND);
    wayDefaultPaintFill.setPathEffect(new DashPathEffect(new float[]{20, 20}, 0));

    Paint wayDefaultPaintOutline = new Paint(Paint.ANTI_ALIAS_FLAG);
    wayDefaultPaintOutline.setStyle(Paint.Style.STROKE);
    wayDefaultPaintOutline.setColor(Color.BLUE);
    wayDefaultPaintOutline.setAlpha(128);
    wayDefaultPaintOutline.setStrokeWidth(7);
    wayDefaultPaintOutline.setStrokeJoin(Paint.Join.ROUND);

    // create an individual paint object for an overlay way
    Paint wayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    wayPaint.setStyle(Paint.Style.FILL);
    wayPaint.setColor(Color.YELLOW);
    wayPaint.setAlpha(192);

    // create the WayOverlay and add the ways
    ArrayWayOverlay wayOverlay = new ArrayWayOverlay(wayDefaultPaintFill,
            wayDefaultPaintOutline);
        int i = 0;
        GeoPoint[][] combined = new GeoPoint[1][139];
        combined[0][i++] = new GeoPoint(50.52401, 30.60166);
        combined[0][i++] = new GeoPoint(50.52394, 30.60187);
        combined[0][i++] = new GeoPoint(50.52387, 30.60211);
        combined[0][i++] = new GeoPoint(50.52382, 30.60235);
        combined[0][i++] = new GeoPoint(50.5238, 30.60249);
        combined[0][i++] = new GeoPoint(50.52376, 30.60273);
        combined[0][i++] = new GeoPoint(50.52374, 30.60299);
        combined[0][i++] = new GeoPoint(50.52371, 30.60354);
        combined[0][i++] = new GeoPoint(50.5237, 30.60388);
        combined[0][i++] = new GeoPoint(50.52365, 30.60477);
        combined[0][i++] = new GeoPoint(50.52364, 30.60504);
        combined[0][i++] = new GeoPoint(50.52362, 30.6055);
        combined[0][i++] = new GeoPoint(50.523589974712, 30.606193584147);
        combined[0][i++] = new GeoPoint(50.523543416179, 30.607269086276);
        combined[0][i++] = new GeoPoint(50.52352, 30.60781);
        combined[0][i++] = new GeoPoint(50.52344, 30.60943);
        combined[0][i++] = new GeoPoint(50.523401047642, 30.610668684975);
        combined[0][i++] = new GeoPoint(50.52339, 30.61102);
        combined[0][i++] = new GeoPoint(50.52333, 30.61221);
        combined[0][i++] = new GeoPoint(50.523232305833, 30.613995138878);
        combined[0][i++] = new GeoPoint(50.52322, 30.61422);
        combined[0][i++] = new GeoPoint(50.523193038926, 30.614740348734);
        combined[0][i++] = new GeoPoint(50.52312, 30.61615);
        combined[0][i++] = new GeoPoint(50.52306, 30.61733);
        combined[0][i++] = new GeoPoint(50.52304, 30.61766);
        combined[0][i++] = new GeoPoint(50.523025099618, 30.617999190881);
        combined[0][i++] = new GeoPoint(50.523021766305, 30.618075070114);
        combined[0][i++] = new GeoPoint(50.523002984212, 30.618502623925);
        combined[0][i++] = new GeoPoint(50.522957442102, 30.618679329448);
        combined[0][i++] = new GeoPoint(50.52292, 30.6188);
        combined[0][i++] = new GeoPoint(50.522883410536, 30.618892682209);
        combined[0][i++] = new GeoPoint(50.522844884192, 30.618978658895);
        combined[0][i++] = new GeoPoint(50.522799573682, 30.619051341105);
        combined[0][i++] = new GeoPoint(50.52271, 30.61916);
        combined[0][i++] = new GeoPoint(50.5226, 30.61927);
        combined[0][i++] = new GeoPoint(50.52254, 30.61933);
        combined[0][i++] = new GeoPoint(50.5225, 30.61936);
        combined[0][i++] = new GeoPoint(50.52246, 30.61939);
        combined[0][i++] = new GeoPoint(50.52241, 30.61941);
        combined[0][i++] = new GeoPoint(50.52234, 30.61945);
        combined[0][i++] = new GeoPoint(50.52228, 30.61946);
        combined[0][i++] = new GeoPoint(50.52221, 30.61949);
        combined[0][i++] = new GeoPoint(50.52194, 30.61945);
        combined[0][i++] = new GeoPoint(50.52162, 30.61939);
        combined[0][i++] = new GeoPoint(50.521272542216, 30.619313559288);
        combined[0][i++] = new GeoPoint(50.52112, 30.61928);
        combined[0][i++] = new GeoPoint(50.52076, 30.6192);
        combined[0][i++] = new GeoPoint(50.52045, 30.61911);
        combined[0][i++] = new GeoPoint(50.520176039722, 30.61902905719);
        combined[0][i++] = new GeoPoint(50.52001, 30.61898);
        combined[0][i++] = new GeoPoint(50.51965, 30.61886);
        combined[0][i++] = new GeoPoint(50.51903, 30.61863);
        combined[0][i++] = new GeoPoint(50.51899, 30.61861);
        combined[0][i++] = new GeoPoint(50.5189404974, 30.618587498818);
        combined[0][i++] = new GeoPoint(50.51822, 30.61826);
        combined[0][i++] = new GeoPoint(50.51763, 30.61796);
        combined[0][i++] = new GeoPoint(50.517241884342, 30.617736539469);
        combined[0][i++] = new GeoPoint(50.51697, 30.61758);
        combined[0][i++] = new GeoPoint(50.516586949357, 30.6173397818);
        combined[0][i++] = new GeoPoint(50.51638, 30.61721);
        combined[0][i++] = new GeoPoint(50.51601, 30.61694);
        combined[0][i++] = new GeoPoint(50.51463, 30.61584);
        combined[0][i++] = new GeoPoint(50.513973020697, 30.615246018712);
        combined[0][i++] = new GeoPoint(50.5139, 30.61518);
        combined[0][i++] = new GeoPoint(50.513683133093, 30.614969910184);
        combined[0][i++] = new GeoPoint(50.51358, 30.61487);
        combined[0][i++] = new GeoPoint(50.5133, 30.6146);
        combined[0][i++] = new GeoPoint(50.51319, 30.6144);
        combined[0][i++] = new GeoPoint(50.51313, 30.61427);
        combined[0][i++] = new GeoPoint(50.51309, 30.61418);
        combined[0][i++] = new GeoPoint(50.51306, 30.61406);
        combined[0][i++] = new GeoPoint(50.513027441564, 30.613864023313);
        combined[0][i++] = new GeoPoint(50.51299, 30.61363);
        combined[0][i++] = new GeoPoint(50.51296, 30.61356);
        combined[0][i++] = new GeoPoint(50.51293, 30.61349);
        combined[0][i++] = new GeoPoint(50.51289, 30.61343);
        combined[0][i++] = new GeoPoint(50.51285, 30.61338);
        combined[0][i++] = new GeoPoint(50.5128, 30.61333);
        combined[0][i++] = new GeoPoint(50.51274, 30.6133);
        combined[0][i++] = new GeoPoint(50.51269, 30.61329);
        combined[0][i++] = new GeoPoint(50.51263, 30.61328);
        combined[0][i++] = new GeoPoint(50.51258, 30.613283294477);
        combined[0][i++] = new GeoPoint(50.51252, 30.6133);
        combined[0][i++] = new GeoPoint(50.51247, 30.61333);
        combined[0][i++] = new GeoPoint(50.51242, 30.61338);
        combined[0][i++] = new GeoPoint(50.51237, 30.61343);
        combined[0][i++] = new GeoPoint(50.51234, 30.61349);
        combined[0][i++] = new GeoPoint(50.5123, 30.61356);
        combined[0][i++] = new GeoPoint(50.51228, 30.61364);
        combined[0][i++] = new GeoPoint(50.51226, 30.61372);
        combined[0][i++] = new GeoPoint(50.51224, 30.61381);
        combined[0][i++] = new GeoPoint(50.51224, 30.61386);
        combined[0][i++] = new GeoPoint(50.51226, 30.61411);
        combined[0][i++] = new GeoPoint(50.51227, 30.61432);
        combined[0][i++] = new GeoPoint(50.51226, 30.61445);
        combined[0][i++] = new GeoPoint(50.51224, 30.61458);
        combined[0][i++] = new GeoPoint(50.51221, 30.6147);
        combined[0][i++] = new GeoPoint(50.51204, 30.61528);
        combined[0][i++] = new GeoPoint(50.5119, 30.61561);
        combined[0][i++] = new GeoPoint(50.5113, 30.61706);
        combined[0][i++] = new GeoPoint(50.511172273581, 30.617362285858);
        combined[0][i++] = new GeoPoint(50.511, 30.61777);
        combined[0][i++] = new GeoPoint(50.510983012827, 30.617811386931);
        combined[0][i++] = new GeoPoint(50.51045, 30.61911);
        combined[0][i++] = new GeoPoint(50.51023, 30.61965);
        combined[0][i++] = new GeoPoint(50.509924457344, 30.620466090361);
        combined[0][i++] = new GeoPoint(50.509727760837, 30.620991457672);
        combined[0][i++] = new GeoPoint(50.509341098769, 30.620623438375);
        combined[0][i++] = new GeoPoint(50.50856, 30.61988);
        combined[0][i++] = new GeoPoint(50.507885905535, 30.619192816322);
        combined[0][i++] = new GeoPoint(50.50753, 30.61883);
        combined[0][i++] = new GeoPoint(50.507260652367, 30.618560652367);
        combined[0][i++] = new GeoPoint(50.50627, 30.61757);
        combined[0][i++] = new GeoPoint(50.5048, 30.61608);
        combined[0][i++] = new GeoPoint(50.50454, 30.61581);
        combined[0][i++] = new GeoPoint(50.504041770988, 30.615311770988);
        combined[0][i++] = new GeoPoint(50.5039, 30.61517);
        combined[0][i++] = new GeoPoint(50.503752941971, 30.615022941971);
        combined[0][i++] = new GeoPoint(50.50357, 30.61484);
        combined[0][i++] = new GeoPoint(50.50325, 30.6145);
        combined[0][i++] = new GeoPoint(50.50308, 30.61434);
        combined[0][i++] = new GeoPoint(50.50281, 30.61403);
        combined[0][i++] = new GeoPoint(50.50255, 30.61374);
        combined[0][i++] = new GeoPoint(50.50237, 30.6135);
        combined[0][i++] = new GeoPoint(50.50213, 30.61317);
        combined[0][i++] = new GeoPoint(50.50193, 30.61286);
        combined[0][i++] = new GeoPoint(50.50175, 30.61259);
        combined[0][i++] = new GeoPoint(50.5015, 30.61214);
        combined[0][i++] = new GeoPoint(50.50137, 30.61191);
        combined[0][i++] = new GeoPoint(50.50114, 30.61142);
        combined[0][i++] = new GeoPoint(50.500952199763, 30.611033352452);
        combined[0][i++] = new GeoPoint(50.5008, 30.61072);
        combined[0][i++] = new GeoPoint(50.500655684537, 30.610407316497);
        combined[0][i++] = new GeoPoint(50.5005, 30.61007);
        combined[0][i++] = new GeoPoint(50.50031, 30.60963);
        combined[0][i++] = new GeoPoint(50.50003, 30.60903);
        combined[0][i++] = new GeoPoint(50.49968, 30.60828);
        combined[0][i++] = new GeoPoint(50.4996, 30.60811);
        combined[0][i++] = new GeoPoint(50.499232235283, 30.607354039193);



    OverlayWay way1 = new OverlayWay(combined);

    wayOverlay.addWay(way1);
    mapView.getOverlays().add(wayOverlay);
    }
}
