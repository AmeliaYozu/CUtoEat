import java.util.Comparator;
import java.util.List;

public class Business implements Comparator<Business>{
	String id;
	String name;
	Boolean is_closed;
	String url;
	List<String[]> categories;
	double distance;
	double rating;
	double lat;
	double lon;
	long reviewCount;
	
	public String toString(){
		String ctgr = null;
		if(this.categories!=null){
			ctgr = "[";
			for(int i = 0;i<this.categories.size();i++){
					ctgr = ctgr+ "["+categories.get(i)[0]+","+categories.get(i)[1]+"]";
			}
			ctgr = ctgr+"]";
		}
		String str = "id: "+ this.id +", name: " + this.name+", is_closed: " + this.is_closed
				+", url: " + this.url+", categories: " + ctgr
				+", distance: " + this.distance+", rating: " + this.rating+", lat: " + this.lat
				+", lon: " + this.lon+", reviewCount: " + this.reviewCount;
		
		return str;
		}	
	
	public int compare(Business b1, Business b2) {
		// TODO Auto-generated method stub
		return b1.id.compareTo(b2.id);
	}
}
