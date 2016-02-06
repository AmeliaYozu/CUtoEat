import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class testMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		HashMap<String,Business> bnMap = new HashMap<String,Business>();//avoid duplicate entries
		ResponseHandler r = new ResponseHandler();
		
		int total = r.getTotal(500);//500 is the radius from Columbia university, for final running, supposed to be 3000 in meters
		int reqNum = total/20+1;
		
		System.out.println("Total: "+total);
		for(int i = 0;i<reqNum;i++){
			System.out.print("Query "+i+"(offset: "+i*20+"): ");
			r.sendRequest(i*20,500);//500 is the radius from Columbia university, for final running, supposed to be 3000 in meters
			r.updateBusinessSet();
		}
		
		r.updateBusinessSet();
		bnMap = r.getBusinessSet();
System.out.println("***********************************************************");	
File file = new File("output.txt");

//		 if file doesn't exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		int i = 0;
		for(Entry<String, Business> bn: bnMap.entrySet()){
			System.out.println("#"+i+": "+bn.getValue().toString());
			bw.write("#"+i+": "+bn.getValue().toString()+"\r\n");
			i++;
		}
		 bw.close(); 
	}

}
