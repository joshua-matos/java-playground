import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//tie variables to the user
public class User {
	private int recordedTries = 1;
	private String userName = "";
	private final List<Integer> storeUserTries = new ArrayList<>();

	public String getNumberOfTries() {
		return "" + recordedTries;
	}

	public void incrementNumberOfTries(){
		this.recordedTries++;
	}

	public String getUserName(){
		return this.userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}

	public void setUserTriedNumbers(Integer userTriedNumbers){
		storeUserTries.add(userTriedNumbers);
	}

	public String getRecordedTriesList(){
		//the only way to join an array as a string
		return storeUserTries.stream()
				.map(Object::toString)
				.collect(Collectors.joining(", "));
	}


}
