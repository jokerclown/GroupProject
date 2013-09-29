package mode;

import java.util.ArrayList;

public class Mode2 {
	
	public boolean checkChildAlsoParent(ArrayList<Object> lineInput){
		char parent = lineInput.get(0).toString().charAt(0);
		ArrayList<Character> childContainer = (ArrayList<Character>) lineInput.get(1);
		
		for(int i = 0; i < childContainer.size(); i++){
			if(parent == childContainer.get(i)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkSameChild(ArrayList<Character> childContainer){
		
		for(int i =0; i < childContainer.size()-1; i++){
			
			char child = childContainer.get(i);
			for(int j = i; j < childContainer.size(); j++){
				if(child == childContainer.get(j)){
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	public boolean checkLineChildIsParentDependent(ArrayList<ArrayList<Object>> textInput, ArrayList<Object> lineInput){
		char parent = lineInput.get(0).toString().charAt(0);
		Mode0 mode0 = new Mode0();
		ArrayList<Character> childContainer = (ArrayList<Character>) lineInput.get(1);
		
		for(int i = 0; i < childContainer.size(); i++){
			char child = childContainer.get(i);
			if(mode0.checkChildParentDependent(textInput, child, parent)==true){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkFPOValid(ArrayList<ArrayList<Object>> textInput){
		
		
		return true;
	}
	
	public int checkValidation (){
		
		int result = 0;
		
		
		
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
