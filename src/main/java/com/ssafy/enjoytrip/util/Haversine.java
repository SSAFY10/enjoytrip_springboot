package com.ssafy.enjoytrip.util;

public class Haversine {
	public static double getDistanceInMeter(double lat1, double lon1, double lat2, double lon2) {
        // distance between latitudes
        // and longitudes
        double dLat = (lat2 - lat1) *
        		Math.PI / 180.0;
        double dLon = (lon2 - lon1) * 
        		Math.PI / 180.0;
 
        // convert to radians
        lat1 = (lat1) * Math.PI / 180.0;
        lat2 = (lat2) * Math.PI / 180.0;
 
        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) + 
        		Math.pow(Math.sin(dLon / 2), 2) * 
        		Math.cos(lat1) * Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return Math.round(rad * c * 1000);
	}
}
