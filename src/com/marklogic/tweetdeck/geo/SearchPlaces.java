package com.marklogic.tweetdeck.geo;

import twitter4j.*;

public final class SearchPlaces {
    /**
     * Usage: java twitter4j.examples.geo.SearchPlaces [ip address] or [latitude] [longitude]
     *
     * @param args message
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java twitter4j.examples.geo.SearchPlaces [ip address] or [latitude] [longitude]");
            System.exit(-1);
        }
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            GeoQuery query;
            if (args.length == 2) {
                query = new GeoQuery(new GeoLocation(Double.parseDouble(args[0]), Double.parseDouble(args[1])));
            } else {
                query = new GeoQuery(args[0]);
            }
            ResponseList<Place> places = twitter.searchPlaces(query);
            if (places.size() == 0) {
                System.out.println("No location associated with the specified IP address or lat/lang");
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
