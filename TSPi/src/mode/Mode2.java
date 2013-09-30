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

	public boolean checkSoVValid(ArrayList<ArrayList<Object>> textInputFPO, ArrayList<ArrayList<Object>> textInputSoV){

		Mode1 mode1 = new Mode1();

		ArrayList<ArrayList<Object>> real = mode1.generateSequenceOfValue(textInputFPO);

		for (int i=0; i<real.size();i++){
			int index = -1;

			char focus = real.get(i).get(0).toString().charAt(0);

			for(int j=0; j<textInputSoV.size(); j++){
				if (textInputSoV.get(j).get(0).toString().charAt(0)==focus){
					index = j;
				}
			}
			
			if(index>-1){
				if((Integer)real.get(i).get(1) != (Integer)textInputSoV.get(i).get(1)){
					return false;
				}
			}else{
				return false;
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
