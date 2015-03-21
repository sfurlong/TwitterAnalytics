package com.marklogic.tweetdeck.geo;

import twitter4j.Place;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public final class GetGeoDetails {
    /**
     * Usage: java twitter4j.examples.geo.GetGeoDetails [place id]
     *
     * @param args message
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java twitter4j.examples.geo.GetGeoDetails [place id]");
            System.exit(-1);
        }
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            Place place = twitter.getGeoDetails(args[0]);
            System.out.println("name: " + place.getName());
            System.out.println("country: " + place.getCountry());
            System.out.println("country code: " + place.getCountryCode());
            System.out.println("full name: " + place.getFullName());
            System.out.println("id: " + place.getId());
            System.out.println("place type: " + place.getPlaceType());
            System.out.println("street address: " + place.getStreetAddress());
            Place[] containedWithinArray = place.getContainedWithIn();
            if (containedWithinArray != null && containedWithinArray.length != 0) {
                System.out.println("  contained within:");
                for (Place containedWithinPlace : containedWithinArray) {
                    System.out.println("  id: " + containedWithinPlace.getId() + " name: " + containedWithinPlace.getFullName());
                }
            }
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to retrieve geo details: " + te.getMessage());
            System.exit(-1);
        }
    }
}
