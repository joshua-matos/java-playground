import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Accumulator {

	public static void main(String... args) {
		Accumulator testAccumulator = new Accumulator();
		String[] myArray = {};
		System.out.flush();
		System.out.println(testAccumulator.toSentence(myArray) + "\n");
		Integer[] numbers = new Integer[]{1, 1, -3};
		//System.out.println(testAccumulator.sumOfPostiveValues(numbers));

		String[] names = {"Alice", "Bob", "Kat"};
		//System.out.println(testAccumulator.toSentence(names));

		Runnable runner = () -> {
			//cannot call method below and sumOfPositiveValues(); new AtomicInteger locks thread;
			System.out.println(testAccumulator.addStrings("HelloWorld"));
		};
		runner.run();
		Thread helloWorldThread = new Thread(runner);
		helloWorldThread.start();
	}

	public String toSentence(String[] arrayOfStrings) {
		String joinString = "";
		if (arrayOfStrings == null || arrayOfStrings.length == 0) return "";
		if (arrayOfStrings.length == 1) {
			joinString = arrayOfStrings[0];
		} else if (arrayOfStrings.length == 2) {
			joinString = arrayOfStrings[0] + " and " + arrayOfStrings[1];
		} else {
			String[] temp = new String[arrayOfStrings.length - 1];
			System.arraycopy(arrayOfStrings, 0, temp, 0, arrayOfStrings.length - 1);

			joinString = String.join(", ", temp);
			joinString = joinString + " and " + arrayOfStrings[arrayOfStrings.length - 1];
		}
		return joinString;
	}


	public static Integer sumOfPostiveValues(Integer[] nums) {
		List<Integer> integers = Arrays.asList(nums);
		return integers.stream()
				.filter(i -> i > 0)
				.reduce(0, Integer::sum);
	}


	public HashMap addStrings(String characters) {
		//AtomicInteger signature
		HashMap<String, AtomicInteger> characterCount = new HashMap<>();
		//take a string and map each (char) to a list
		List<Character> listOfChars = characters
				.chars()
				.mapToObj(item -> (char) item)
				.toList();

		listOfChars.forEach(
				(e) ->  {
					characterCount.notify();
					//No value, initialize with 0
					characterCount
							.putIfAbsent(
									e.toString(),
									new AtomicInteger(0));
					//if it has a value add by 1
					characterCount
							.get(e.toString())
							.incrementAndGet();
		});
		return characterCount;
	}
}
