package com.marklogic.tweetdeck.geo;

import twitter4j.*;

public final class ReverseGeoCode {
    /**
     * Usage: java twitter4j.examples.geo.ReverseGeoCode [latitude] [longitude]
     *
     * @param args message
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java twitter4j.examples.geo.ReverseGeoCode [latitude] [longitude]");
            System.exit(-1);
        }
        
        getGeoCode(args[0], args[1]);
    }

    /**
     * Usage: java twitter4j.examples.geo.ReverseGeoCode [latitude] [longitude]
     *
     * @param args message
     */
    public static void getGeoCode(String lat, String lon) {
    	
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            GeoQuery query = new GeoQuery(new GeoLocation(Double.parseDouble(lat), Double.parseDouble(lon)));
            ResponseList<Place> places = twitter.reverseGeoCode(query);
            if (places.size() == 0) {
                System.out.println("No location associated with the specified lat/lang");
            } else {
                for (Place place : places) {
                    System.out.println("id: " + place.getId() + " name: " + place.getFullName());
                    Place[] containedWithinArray = place.getContainedWithIn();
                    if (containedWithinArray != null && containedWithinArray.length != 0) {
                        System.out.println("  contained within:");
                        for (Place containedWithinPlace : containedWithinArray) {
                            System.out.println("  id: " + containedWithinPlace.getId() + " name: " + containedWithinPlace.getFullName());
                        }
                    }
                }
            }
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to retrieve places: " + te.getMessage());
            System.exit(-1);
        }
    }

}
