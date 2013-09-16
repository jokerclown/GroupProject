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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
