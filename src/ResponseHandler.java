import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import yelpAPI.Yelp;

public class ResponseHandler {
	Yelp yelp = new Yelp();
	String response;
//	Vector<String> resultVec = new Vector<String>();
	HashMap<String,Business> bnMap = new HashMap<String,Business>();
	
	public String getResponse(){
		return this.response;
	}
	
	public int getTotal(int distance){
		this.sendRequest(0, distance);
		int total = 0;
		String tStr = this.toJson().get("total").toString();
		total = Integer.parseInt(tStr);
		return total;
	}
	
	public void sendRequest(int offset, int distance){
		String dist = distance+"";
		this.response = yelp.searchForBusinesses(offset, dist);
	}
	
	//get JSON type response
	public JSONObject toJson(){
		JSONParser jp = new JSONParser();
		JSONObject json = new JSONObject();
		try {
			json = (JSONObject) jp.parse(this.getResponse());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public void updateBusinessSet(){
		JSONArray ja =(JSONArray) this.toJson().get("businesses");
		for(int i = 0; i < ja.size(); i++){
			String str = ja.get(i).toString();
			Business bn = this.getBsnsObj(str);
			this.bnMap.put(bn.id, bn);
		}
	}
	
	public Business getBsnsObj(String jsonStr){
		JSONParser jp = new JSONParser();
		JSONObject json = null;
		Business bsns = new Business();
		try {
			json = (JSONObject) jp.parse(jsonStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = (String) json.get("id");
		String name = (String) json.get("name"); 
		boolean is_closed = (Boolean) json.get("is_closed");
		String url = (String) json.get("url");
		JSONArray ctArray = (JSONArray) json.get("categories");
		JSONObject location = (JSONObject) json.get("location");
		JSONObject coordinate = (JSONObject) location.get("coordinate");
		double lat = Double.parseDouble(coordinate.get("latitude").toString());
		double lon = Double.parseDouble(coordinate.get("longitude").toString());		
		double distance = (double) json.get("distance");
		double rating = (double) json.get("rating");
		long reviewCount = (Long) json.get("review_count");
		
		//handle categories
		List<String[]> categories = null;
		if(json.get("categories")!=null){
			categories = new ArrayList<String[]>();
			for(int i = 0;i<ctArray.size();i++){
				JSONArray jsonPair = (JSONArray) ctArray.get(i);
				String[] strPair = {jsonPair.get(0).toString(),jsonPair.get(1).toString()};
				categories.add(strPair);
			}	
		}
		bsns.id = id;
		bsns.name = name;
		bsns.is_closed = is_closed;
		bsns.url = url;
		bsns.categories = categories;
		bsns.distance = distance;
		bsns.rating = rating;
		bsns.lat = lat;
		bsns.lon = lon;
		bsns.reviewCount = reviewCount;
		
		return bsns;
	}
	
	
	public HashMap<String,Business> getBusinessSet(){
		return this.bnMap;
	}
	
	
}
