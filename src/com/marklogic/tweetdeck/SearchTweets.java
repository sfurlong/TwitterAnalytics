/*
 * Extract Tweets
 */

package com.marklogic.tweetdeck;

import twitter4j.*;
import java.util.List;

import com.marklogic.tweetdeck.geo.ReverseGeoCode;

public class SearchTweets {
	/**
	 * Usage: java twitter4j.examples.search.SearchTweets [query]
	 *
	 * @param args
	 *            search query
	 */
	public static void main(String[] args) {
		// USAGE: java twitter4j.examples.search.SearchTweets [query]

		SearchTweets.GetSearchedTweets("MarkLogic NoSQL");
	}

	public static void GetSearchedTweets(String searchString) {

		Twitter twitter = new TwitterFactory().getInstance();
		double lat, lon = 0.0;

		try {
			Query query = new Query(searchString);
			QueryResult result = twitter.search(query);

			while ((query = result.nextQuery()) != null) {

				List<Status> tweets = result.getTweets();
				
				for (Status tweet : tweets) {

					GeoLocation geoLoc = tweet.getGeoLocation();
					Place p = tweet.getPlace();
					System.out.println("USER LOCATION: "
							+ tweet.getUser().getLocation());
					if (p != null) {
						System.out.println("Place Exists!!! " + p.getName());
					}
					if (geoLoc != null) {
						System.out.println("!!!!!!!GEO!!!!!!!!"
								+ tweet.getPlace().getName());
						lat = tweet.getGeoLocation().getLatitude();
						lon = tweet.getGeoLocation().getLongitude();
						ReverseGeoCode.getGeoCode(Double.toString(lat),
								Double.toString(lon));
					} else {
						lat = 0.0;
						lon = 0.0;
					}

					System.out.println("@" + tweet.getUser().getScreenName()
							+ "lat:" + lat + ", lon: " + lon + " - "
							+ tweet.getText());
					
					//Store on DISK as JSON file for each Tweet.
					String rawJSON = TwitterObjectFactory.getRawJSON(tweet);
					String fileName = "json-tweets/" + tweet.getId() + ".json";
					if (rawJSON == null) {
						System.out.println("WTF null JSON: " + rawJSON);
					} else {
						twitter4j.examples.SaveRawJSON.storeJSON(rawJSON,
								fileName);
					}

				}
			}
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to search tweets: " + e.getMessage());
		}
	}
}
