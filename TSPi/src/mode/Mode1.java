package mode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Mode1 {

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
					int tempCtr = (Integer) count.get(i).get(1);
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

	public ArrayList<ArrayList<Object>> sort(ArrayList<ArrayList<Object>> input){
		ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
		for(int i=0; i < input.size(); i++){
			result.add(input.get(i));
		}
		
		int dataSize = result.size();
		int indexData = 0;
		ArrayList<Object> tempData = new ArrayList<Object>();
		
		for(int i=0;i<dataSize;i++){
			indexData = i;
			for(int ctr = i;ctr<dataSize;ctr++){
				if((Integer)result.get(indexData).get(1) > (Integer)result.get(ctr).get(1)){
					indexData = ctr;
				}
			}
			if(indexData != i){
				tempData = result.get(i);
				result.add(i, result.get(indexData));
				result.remove(i+1);
				result.add(indexData, tempData);
				result.remove(indexData+1);
			}
		}
		
		return result;
	}

}
