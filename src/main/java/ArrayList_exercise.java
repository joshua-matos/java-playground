import java.util.ArrayList;
import java.util.Arrays;

public class ArrayList_exercise {




	public static void main(String... args){
		String[] myStringArray = new String[]{"B", "C",  "E", "F", "D","A"};

		ArrayList_exercise sortedStrings = new ArrayList_exercise();

		System.out.println(sortedStrings.sortArray(myStringArray));


	}
	// Implement the sortArray method below
	public ArrayList<String> sortArray(String[] inputArray) {
		ArrayList<String> sortMyArray = new ArrayList<>(Arrays.asList(inputArray));
		sortMyArray.sort(null);
		return sortMyArray;
	}
}
