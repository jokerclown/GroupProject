import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Mode1 {

	public ArrayList<ArrayList<Object>> readText(String fileName){
		ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();

		try
		{			
			FileInputStream fileStream = new FileInputStream(fileName);
			DataInputStream inStream = new DataInputStream(fileStream);
			BufferedReader bufRead = new BufferedReader(new InputStreamReader(inStream));

			String strLine;
			while ((strLine = bufRead.readLine()) != null)   {
				ArrayList<Character> childContainer = new ArrayList<Character>();
				ArrayList<Object> lineContainer = new ArrayList<Object>();
				
				String[] splitStr = strLine.split(":");
				char parent = splitStr[0].charAt(0);
				
				String[] splitChild = splitStr[1].split(",");
				
				for(int i = 0; i < splitChild.length; i++){
					char child = splitChild[i].charAt(0);
					childContainer.add(child);
				}
				lineContainer.add(parent);
				lineContainer.add(childContainer);
				result.add(lineContainer);
			}
		}
		catch(Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}

		return result;
	}
	
	public void addCount(ArrayList<ArrayList<Object>> count, char child){
		if(count.size()==0){
			ArrayList<Object> result = new ArrayList<Object>();
			result.add(child);
			result.add(1);
			count.add(result);
		}
		else{
			boolean foundChild = false;
			for(int i = 0; i<count.size(); i++){
				
				if((Character)count.get(i).get(0)==child){
					int tempCtr = (int) count.get(i).get(1);
					count.get(i).remove(1);
					count.get(i).add(tempCtr+1);
					foundChild = true;
				}
				
			}
			
			if(foundChild == false){
				ArrayList<Object> result = new ArrayList<Object>();
				result.add(child);
				result.add(1);
				count.add(result);
			}
		}
	}
	
	public ArrayList<ArrayList<Object>> generateSequenceOfValue(ArrayList<ArrayList<Object>> input){
		ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
		
		for(int i = 0; i<input.size(); i++){
			ArrayList<Character> childContainer = (ArrayList<Character>) input.get(i).get(1);
			for(int j = 0; j < childContainer.size(); j++){
				this.addCount(result, childContainer.get(j));
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mode1 test = new Mode1();
		ArrayList<ArrayList<Object>> input = test.readText("textFile.txt");
		System.out.println(input);
		ArrayList<ArrayList<Object>> sov = test.generateSequenceOfValue(input);
		System.out.println(sov);
	}

}
