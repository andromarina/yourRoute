package com.yourRoute.model;

import android.content.ContentResolver;
import android.database.Cursor;
import com.yourRoute.Preferences;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mara
 * Date: 10/18/13
 * Time: 9:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoutesHolder {
    private CitiesRepository citiesRepository;
    private RoutesRepository routesRepository;
    private StopsRepository stopsRepository;

    public RoutesHolder(ContentResolver contentResolver) {
        initializeRepositories(contentResolver);
    }

    private void initializeRepositories(ContentResolver contentResolver) {
        this.citiesRepository = new CitiesRepository(contentResolver);
        this.routesRepository = new RoutesRepository(contentResolver);
        this.stopsRepository = new StopsRepository(contentResolver);
    }

    public ArrayList<City> getAllCities() {
        ArrayList<City> cities = this.citiesRepository.getCities();
        return cities;
    }

    public City findCityById(int cityId) {
        City city = this.citiesRepository.getCity(cityId);
        return city;
    }

    public Route findRouteById(int cityId) {
        Route route = this.routesRepository.getRoute(cityId);
        return route;
    }

    public ArrayList<Route> findRoutesByStopName(String stopName, String optionalStopName, int cityId) {
        ArrayList<Route> routes = this.routesRepository.getRoutesByStopName(stopName, cityId);
        if(optionalStopName == null || optionalStopName.isEmpty()) {
            return routes;
        }
        ArrayList<Route> routesOptional = this.routesRepository.getRoutesByStopName(optionalStopName,cityId);
        ArrayList<Route> routesResult = this.routesRepository.uniteRoutes(routes, routesOptional);
        return routesResult;
    }

    public ArrayList<Route> findRoutesByRouteName(String query, int cityId) {
        ArrayList<Route> routes = this.routesRepository.getRoutesByRouteName(query, cityId);
        return routes;
    }

    public Cursor createRouteSuggestionsCursor(String text, int cityId) {
        Cursor routeSuggestionsCursor = this.routesRepository.getRouteSuggestionsCursor(text, cityId);
        return routeSuggestionsCursor;
    }

    public ArrayList<Stop> findStopsByRouteId(int routeId) {
        ArrayList<Stop> stops = this.stopsRepository.getStopsByRouteId(routeId);
        return stops;
    }

    public Cursor createStopsSuggestionsCursor(String text, int cityId) {
        Cursor stopsSuggestionsCursor = this.stopsRepository.getStopsSuggestionsCursor(text, cityId);
        return stopsSuggestionsCursor;
    }

    public void saveCityId(int cityId) {
        Preferences.saveCityId(cityId);
    }

    public int getSavedCityId() {
        return Preferences.getSavedCityId();
    }
}
