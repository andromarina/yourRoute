package maps;

import org.mapsforge.core.model.GeoPoint;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 12/8/13
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ITouchHandler {

    public boolean onTouch(GeoPoint geoPoint);
}
