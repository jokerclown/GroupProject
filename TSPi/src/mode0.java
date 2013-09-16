import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class mode0 {
	
	public ArrayList<Character> generateSet(){

		ArrayList<Character> newSet = new ArrayList<Character>(Arrays.asList(
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'-', '+', '@', '#', '$', '%', '^', '&', '*', '|', '<', '>', '?'
				));

		return newSet;
	}
	
	public ArrayList<String> randomChar(ArrayList<Character> availableChar){
		
		ArrayList<String> result = new ArrayList<String>();
		Random rand = new Random();
		int randomNumber = rand.nextInt(availableChar.size()-1);
		result.add(Integer.toString(randomNumber));
		char randomChar = availableChar.get(randomNumber);
		result.add(Character.toString(randomChar));

		return result;
	}
	
	public ArrayList<ArrayList<Object>> generateFPO(int lengthX, int lengthY){
		ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
		ArrayList<Character> availableChar = this.generateSet();
		ArrayList<Character> excludeParent = new ArrayList<Character>();


		for(int i = 0; i<lengthY; i++){

			ArrayList<Object> lineContainer = new ArrayList<Object>();
			ArrayList<Character> childContainer = new ArrayList<Character>();

			char parent = Character.UNASSIGNED;
			boolean generateParent = true;

			while(generateParent){

				parent = this.randomChar(availableChar).get(1).charAt(0);
				if(excludeParent.size()!=0){

					boolean noSameParent = true;

					for(int j = 0; j<excludeParent.size(); j++){
						if(excludeParent.get(j)==parent){
							noSameParent = false;
						}
					}

					if(noSameParent){
						generateParent = false;
					}

				}else{
					generateParent = false;
				}

			}

			lineContainer.add(parent);

			for(int j = 0; j<lengthX; j++){

				boolean generateChild = true;
				char child = Character.UNASSIGNED;

				while(generateChild){

					child = this.randomChar(availableChar).get(1).charAt(0);

					if(parent!=child){

						boolean noSameChild = true;

						for(int m = 0; m<childContainer.size();m++){

							if(childContainer.get(m)==child){
								noSameChild = false;
							}

						}

						if(noSameChild){

							if (result.size()!=0){

								int indexParent = 0;
								boolean childIsParent = false;

								for(int k = 0; k<result.size(); k++){
									if(result.get(k).get(0).toString().charAt(0)==child){
										childIsParent = true;
										indexParent = k;
									}
								}

								if(childIsParent){

									ArrayList<Character> focusChild = (ArrayList<Character>) result.get(indexParent).get(1);
									boolean noChildParentDependent = true;

									for(int l = 0; l<focusChild.size(); l++){

										if(focusChild.get(l)==parent){
											noChildParentDependent = false;
										}

									}

									if(noChildParentDependent){
										generateChild = false;
									}


								}else{
									generateChild = false;
								}

							}else{
								generateChild = false;
							}

						}
					}
				}

				childContainer.add(child);

			}

			lineContainer.add(childContainer);


			result.add(lineContainer);
		}

		return result;
	}
	
	public void generateText(ArrayList<ArrayList<Object>> inputFPO){

		try{

			FileWriter fstream = new FileWriter("textFile.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			String oneLine = "";
			
			for(int i = 0; i<inputFPO.size(); i++){
				
				ArrayList<Object> focusLine = inputFPO.get(i);
				
				oneLine = focusLine.get(0).toString() + ":";
				
				ArrayList<Character> focusChild = (ArrayList<Character>) focusLine.get(1);
				
				for(int j =0; j<focusChild.size(); j++){
					oneLine = oneLine + focusChild.get(j);
					
					if(j!=inputFPO.size()-1){
						oneLine = oneLine + ",";
					}
				}
				
				out.write(oneLine);
				out.newLine();
				
			}

		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		mode0 test = new mode0();
		
		ArrayList<ArrayList<Object>> tes = test.generateFPO(10, 5);
		test.generateText(tes);
		System.out.println(tes);

	}

}
