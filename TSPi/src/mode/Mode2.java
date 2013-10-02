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

	public boolean checkSameParent(ArrayList<ArrayList<Object>> textInput){
		for(int i =0; i < textInput.size()-1; i++){

			char parent = textInput.get(i).get(0).toString().charAt(0);
			for(int j = i; j < textInput.size(); j++){
				if(parent == textInput.get(j).get(0).toString().charAt(0)){
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

		for(int i=0; i<textInput.size(); i++){
			boolean childAlsoParent = this.checkChildAlsoParent(textInput.get(i));
			boolean sameChild = this.checkSameChild((ArrayList<Character>) textInput.get(i).get(1));
			boolean sameParent = this.checkSameParent(textInput);
			boolean childIsParentDependent = this.checkLineChildIsParentDependent(textInput, textInput.get(i));

			if(childAlsoParent || sameChild || childIsParentDependent || sameParent){
				return false;
			}
		}

		return true;
	}
	
	public ArrayList<ArrayList<Character>> mergeSameValue(ArrayList<ArrayList<Object>> textInput){
		ArrayList<ArrayList<Character>> result = new ArrayList<ArrayList<Character>>();
		ArrayList<ArrayList<Object>> textInputClone = new ArrayList<ArrayList<Object>>();
		
		
		for(int i=0; i<textInput.size(); i++){
			textInputClone.add(textInput.get(i));
		}
		
		for(int j=0; j<textInputClone.size();j++){
			ArrayList<Character> charContainer = new ArrayList<Character>();
			charContainer.add(textInputClone.get(j).get(0).toString().charAt(0));
			int focus = (Integer) textInputClone.get(j).get(1);
			textInputClone.remove(j);
			
			for(int k=j; k<textInputClone.size();k++){
				if(focus == (Integer) textInputClone.get(k).get(1)){
					charContainer.add(textInputClone.get(k).get(0).toString().charAt(0));
					textInputClone.remove(k);
					k--;
				}
			}
			j--;
			result.add(charContainer);
		}
		return result;
	}

	public boolean checkSoVValid(ArrayList<ArrayList<Object>> textInputFPO, ArrayList<ArrayList<Object>> textInputSoV){

		Mode1 mode1 = new Mode1();

		ArrayList<ArrayList<Object>> real = mode1.generateSequenceOfValue(textInputFPO);
		
		if(real.size()!=textInputSoV.size()){
			return false;
		}
		
		ArrayList<ArrayList<Character>> realMerge = this.mergeSameValue(real);
		int counter = 0;

		for (int i=0; i<realMerge.size();i++){
			
			switch(realMerge.get(i).size()){
			case 0 :
				if(!realMerge.get(i).contains((Character)textInputSoV.get(counter).get(0))){
					return false;
				}
				counter++;
				break;
			default:
				for(int j=0; j<realMerge.get(i).size(); j++){
					if(!realMerge.get(i).contains((Character)textInputSoV.get(counter).get(0))){
						return false;
					}
					counter++;
				}
				break;
			}
		}

		return true;
	}

	public int checkValidation (ArrayList<ArrayList<Object>> textInputFPO, ArrayList<ArrayList<Object>> textInputSoV){

		boolean validFPO = this.checkFPOValid(textInputFPO);

		if(validFPO == false){
			return 1;
		}
		
		boolean validSoV = this.checkSoVValid(textInputFPO, textInputSoV);
		
		if(validSoV == false){
			return 2;
		}
		
		return 0;
	}

}
