package com.swetha.transitly.features.admin.routemanage;

import com.swetha.transitly.data.dto.Route;
import com.swetha.transitly.data.repository.TransitlyDB;

import java.util.ArrayList;

public class RouteManageModel {
    private final RouteManageView routeManageView;

    RouteManageModel(RouteManageView routeManageView) {
        this.routeManageView = routeManageView;
    }

    public boolean doesRouteExist(int routeId) {
        return TransitlyDB.getInstance().getRouteById(routeId) != null;
    }

    public void addRoute(int routeId, String source, String destination, ArrayList<String> stops) {
        Route route = new Route();

        route.setRouteId(routeId);
        route.setSource(source);
        route.setDestination(destination);
        route.setStops(stops);

        TransitlyDB.getInstance().addRoute(route);
        routeManageView.onRouteAdded("Route added successfully.");
    }

    public void getAllRoutes() {
        ArrayList<Route> routes = TransitlyDB.getInstance().getAllRoutes();

        if(routes.isEmpty()) {
            routeManageView.showMessage("No routes available.");
            return;
        }

        routeManageView.showRouteTable(routes);
    }
}
