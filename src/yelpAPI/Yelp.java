package yelpAPI;

import org.scribe.model.OAuthRequest;

public class Yelp extends YelpAPI {
	
	private static final String DEFAULT_TERM = "Restaurants";
	private static final String DEFAULT_LOCATION = "Butler Library";
//	private static final String DEFAULT_CLL = "40.806266,-73.963330";
//	private static final String DEFAULT_RADIUS = "500";
	private static final int SEARCH_LIMIT = 20;
	private static final String SEARCH_PATH = "/v2/search";
	
	
	private static final String COMSUMER_KEY = "";
	private static final String COMSUMER_SECRET = "";
	private static final String TOKEN = "";
	private static final String TOKENSECRET = "";
	
	
	public Yelp(String consumerKey, String consumerSecret, String token, String tokenSecret) {
		super(consumerKey, consumerSecret, token, tokenSecret);
		// TODO Auto-generated constructor stub
	}
	
	public Yelp(){
		super(Yelp.COMSUMER_KEY,Yelp.COMSUMER_SECRET,Yelp.TOKEN,Yelp.TOKENSECRET);
	}
	
	public String searchForBusinesses(int offset, String distance) {
	    OAuthRequest request = createOAuthRequest(SEARCH_PATH);
	    request.addQuerystringParameter("term", DEFAULT_TERM);
	    request.addQuerystringParameter("location", DEFAULT_LOCATION);
	    request.addQuerystringParameter("radius_filter", distance);
	    request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));
	    request.addQuerystringParameter("offset", String.valueOf(offset));
	    return sendRequestAndGetResponse(request);
	  }

}
