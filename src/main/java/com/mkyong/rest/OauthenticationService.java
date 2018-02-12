package com.mkyong.rest;
 
import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
 
@Path("/service")
public class OauthenticationService {
	Twitter twitter;
	RequestToken requestToken;
	
	@GET
	@Path("/signin")
	public Response Signin() {
 
		//Twitter twitter = new TwitterFactory().getInstance();
		URI uri=null;
		try{
    	    //RequestToken requestToken = twitter.getOAuthRequestToken();
    	   // OauthenticationService oauthservice=new OauthenticationService(requestToken);
    	    uri = new URI(requestToken.getAuthenticationURL());
	    
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
		return Response.temporaryRedirect(uri).build();
	}
	@GET
	@Path("/callback")
	public Response Callback(@QueryParam("oauth_token") String oauth_token,@QueryParam("oauth_verifier") String oauth_verifier) {
		AccessToken accesstoken=null;
		try {
			accesstoken=twitter.getOAuthAccessToken(requestToken,oauth_verifier);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(accesstoken.getScreenName()).build();	
	}
	OauthenticationService() throws TwitterException{
		twitter = new TwitterFactory().getInstance();
		requestToken = twitter.getOAuthRequestToken();
	}
		
}