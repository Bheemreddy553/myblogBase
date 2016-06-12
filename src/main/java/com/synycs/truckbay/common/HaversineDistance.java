package com.synycs.truckbay.common;

/**
 * Created by hadoop on 19/08/15.
 * calculated distance
 */
public class HaversineDistance {
    public static final double R = 6372.8;

    private static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

    public static double distanceBetweenPoints(GeoPoint geoPoint1,GeoPoint geoPoint2){
        return haversineDistance(geoPoint1.getLatitude(),geoPoint1.getLongitude(),geoPoint2.getLatitude(),geoPoint2.getLongitude());

    }
}
