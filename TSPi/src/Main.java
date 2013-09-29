
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

import mode.*; 

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//mode0(10, 10);
		mode1("textFile.txt");
	}

	public static void printText(ArrayList<String> inputText, String textName){
		try{
			FileWriter fstream = new FileWriter(textName);
			BufferedWriter out = new BufferedWriter(fstream);
			
			for(int i=0; i < inputText.size(); i++){
				out.write(inputText.get(i));
				out.newLine();
			}
			out.close();
			
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}

	}
	
	public static ArrayList<String> getText(String textName){
		ArrayList<String> result = new ArrayList<String>();
		
		try{
			
			FileInputStream fileStream = new FileInputStream(textName);
			DataInputStream inStream = new DataInputStream(fileStream);
			BufferedReader bufRead = new BufferedReader(new InputStreamReader(inStream));
			
			String strLine;
			while ((strLine = bufRead.readLine()) != null){
				result.add(strLine);
			}
		}catch(Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
		
		return result;
	}

	public static void mode0(int lengthX, int lengthY){

		Mode0 mode0 = new Mode0();

		ArrayList<ArrayList<Object>> result = mode0.generateFPO(lengthX, lengthY);
		ArrayList<String> resultText = new ArrayList<String>();
		
		for(int i=0; i<result.size(); i++){
			
			ArrayList<Object> focusLine = result.get(i);

			String oneLine = focusLine.get(0).toString() + ":";

			ArrayList<Character> focusChild = (ArrayList<Character>) focusLine.get(1);

			for(int j =0; j<focusChild.size(); j++){
				oneLine = oneLine + focusChild.get(j);

				if(j!=focusChild.size()-1){
					oneLine = oneLine + ",";
				}
			}

			resultText.add(oneLine);
		}
		
		printText(resultText, "textFile.txt");

	}
	
	public static void mode1(String textName){
		
		Mode1 mode1 = new Mode1();
		
		ArrayList<String> stringText = getText(textName);
		ArrayList<ArrayList<Object>> inputText = new ArrayList<ArrayList<Object>>();
		
		for(int i=0; i < stringText.size(); i++){
			ArrayList<Character> childContainer = new ArrayList<Character>();
			ArrayList<Object> lineContainer = new ArrayList<Object>();
			
			String[] splitStr = stringText.get(i).split(":");
			char parent = splitStr[0].charAt(0);

			String[] splitChild = splitStr[1].split(",");
			
			for(int j = 0; j < splitChild.length; j++){
				char child = splitChild[j].charAt(0);
				childContainer.add(child);
			}
			lineContainer.add(parent);
			lineContainer.add(childContainer);
			inputText.add(lineContainer);
		}
		
		ArrayList<ArrayList<Object>> result = mode1.generateSequenceOfValue(inputText);
		ArrayList<ArrayList<Object>> sortResult = mode1.sort(result);
		
		ArrayList<String> resultText = new ArrayList<String>();
		
		for(int k=sortResult.size()-1; k>-1; k--){
			resultText.add(sortResult.get(k).get(0).toString());
		}
		
		printText(resultText, "textFile1.txt");
	}

}
