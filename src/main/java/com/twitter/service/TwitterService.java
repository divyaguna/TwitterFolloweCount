package com.twitter.service;

import java.net.URI;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterService {
	Twitter twitter;
	RequestToken requestToken;
	public URI uri;
	AccessToken accesstoken;
	public TwitterService(){
		try {
		twitter = new TwitterFactory().getInstance();
		requestToken = twitter.getOAuthRequestToken();
 	    uri = new URI(requestToken.getAuthenticationURL()); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String getAccessToken(String oauthVerifier){
		try {
			accesstoken=twitter.getOAuthAccessToken(requestToken, oauthVerifier);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return accesstoken.getScreenName();
	}
}
