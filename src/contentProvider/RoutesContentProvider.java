package contentProvider;

import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.yourRoute.splashActivity.LoadingTask;
import contentProvider.Contracts.Cities;
import contentProvider.Contracts.Routes;
import contentProvider.Contracts.Stops;
import contentProvider.QueryBuilders.*;

import java.util.Map;
import java.util.TreeMap;

public class RoutesContentProvider extends BaseContentProvider {
    final String LOG_TAG = this.getClass().getSimpleName().toString();
    private Map<Integer, BaseQueryBuilder> queryBuilders;
    private static RoutesContentProvider self;

    // Uri
    public static final String AUTHORITY = "your.route.DB";


    static final int URI_CITIES = 1;
    static final int URI_CITY_ID = 2;
    static final int URI_ROUTES = 3;
    static final int URI_ROUTES_BY_STOP_NAME = 4;
    static final int URI_ROUTE_ID = 5;
    static final int URI_ROUTES_BY_ROUTE_ID = 6;
    static final int URI_STOPS = 8;
    static final int URI_STOPS_SUGGESTIONS = 9;
    static final int URI_ROUTES_BY_ROUTE_NAME = 10;
    // UriMatcher
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, Cities.CITIES_PATH, URI_CITIES);
        uriMatcher.addURI(AUTHORITY, Cities.CITY_PATH, URI_CITY_ID);

        uriMatcher.addURI(AUTHORITY, Routes.ROUTES_PATH, URI_ROUTES);
        uriMatcher.addURI(AUTHORITY, Routes.ROUTES_BY_STOP_NAME_PATH, URI_ROUTES_BY_STOP_NAME);
        uriMatcher.addURI(AUTHORITY, Routes.ROUTES_BY_ROUTE_NAME_PATH, URI_ROUTES_BY_ROUTE_NAME);
        uriMatcher.addURI(AUTHORITY, Routes.ROUTES_BY_ROUTE_ID_PATH, URI_ROUTES_BY_ROUTE_ID);
        uriMatcher.addURI(AUTHORITY, Routes.ROUTE_PATH, URI_ROUTE_ID);

        uriMatcher.addURI(AUTHORITY, Stops.STOPS_PATH, URI_STOPS);
        uriMatcher.addURI(AUTHORITY, Stops.STOPS_SUGGESTIONS_PATH, URI_STOPS_SUGGESTIONS);
    }

    DataBaseHelper dbHelper;

    public RoutesContentProvider() {
        Log.d(LOG_TAG, "My provider called");
        self = this;
    }

    static public RoutesContentProvider instance() {
        return self;
    }

    @Override
    public String getType(Uri uri) {
        Log.d(LOG_TAG, "getType, " + uri.toString());

        int queryId = uriMatcher.match(uri);
        if (!queryBuilders.containsKey(queryId)) {
            throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        BaseQueryBuilder queryBuilder = queryBuilders.get(queryId);
        return queryBuilder.getType();
    }

    @Override
    public boolean onCreate() {
        Log.d(LOG_TAG, "onCreate");
//        dbHelper = new DataBaseHelper(getContext());
//        try {
//            dbHelper.createDataBase();
//        } catch (Throwable ex) {
//            Toast.makeText(getContext(), "Error DB creation", 5).show();
//            return false;
//        }

        dbHelper = new DataBaseHelper(getContext());
        this.queryBuilders = initializeQueryBuilders(dbHelper);
//
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d(LOG_TAG, "query, " + uri.toString());

        int queryId = uriMatcher.match(uri);
        if (!queryBuilders.containsKey(queryId)) {
            throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        BaseQueryBuilder queryBuilder = queryBuilders.get(queryId);
        return queryBuilder.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public boolean initializeDB() {

        try {
            dbHelper.createDataBase();
        } catch (Throwable ex) {
            Toast.makeText(getContext(), "Error DB creation", 5).show();
            return false;
        }
//        this.queryBuilders = initializeQueryBuilders(dbHelper);
        return true;
    }

    private static Map<Integer, BaseQueryBuilder> initializeQueryBuilders(DataBaseHelper dataBaseHelper) {
        Map<Integer, BaseQueryBuilder> queryBuilders = new TreeMap<Integer, BaseQueryBuilder>();
        queryBuilders.put(URI_CITIES, new CitiesQueryBuilder(dataBaseHelper));
        queryBuilders.put(URI_CITY_ID, new CityByIdQueryBuilder(dataBaseHelper));
        queryBuilders.put(URI_ROUTES, new RoutesQueryBuilder(dataBaseHelper));
        queryBuilders.put(URI_ROUTES_BY_STOP_NAME, new RoutesByStopNameBuilder(dataBaseHelper));
        queryBuilders.put(URI_ROUTE_ID, new RouteByIdQueryBuilder(dataBaseHelper));
        queryBuilders.put(URI_STOPS, new StopsQueryBuilder(dataBaseHelper));
        queryBuilders.put(URI_STOPS_SUGGESTIONS, new StopsSuggestionsQueryBuilder(dataBaseHelper));
        queryBuilders.put(URI_ROUTES_BY_ROUTE_NAME, new RoutesByRouteNameQueryBuilder(dataBaseHelper));
        queryBuilders.put(URI_ROUTES_BY_ROUTE_ID, new RoutesByRouteIDQueryBuilder(dataBaseHelper));
        return queryBuilders;
    }

}
