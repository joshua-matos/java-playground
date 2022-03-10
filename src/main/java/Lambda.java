import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

public class Lambda {

	public static void main(String... args){
		Lambda test = new Lambda();
		test.arrayAsLights();

	}
	public static void forEachLambda(){

		List<String> fileNames = new ArrayList<>();

		fileNames.add("My Report.csv");
		fileNames.add("The Best Deck.pdf");
		fileNames.add("Thanks for all the fishes.doc");
System.out.flush();

		fileNames.forEach(name -> System.out.println(name.replaceAll(String.valueOf(' '), "-")));

	}

	public void arrayAsLights(){
		StringBuilder newJoinString = new StringBuilder();
		List<String> array1 = Arrays.asList("a", "b", "f", "f");
		List<String> array2 = Arrays.asList("d", "e", "f", "c");
		List<String> array3 =
				IntStream.range(0,
								array1.size())
						.mapToObj(i -> array1.get(i) + array2.get(i))
						.distinct()
						.toList();
		array3.forEach(newJoinString::append);
		System.out.println(newJoinString);

	}
	public class LogLevels {

		public static String message(String logLine) {
			logLine = logLine.replace("[ERROR]: ", "");
			logLine = logLine.replace("[WARNING]: ", "");
			logLine = logLine.replace("[INFO]: ", "");
			return logLine.trim();
		}

		public static String logLevel(String logLine) {
			if(logLine.contains("[ERROR]: ")) {
				logLine = "error";
			}
			if(logLine.contains("[WARNING]: ")) {
				logLine = "warning";
			}
			if(logLine.contains("[INFO]: ")) {
				logLine = "info";
			}
			return logLine.toLowerCase(Locale.ROOT);


		}

		public static String reformat(String logLine) {
			return (reformat(logLine) + " " + "(" + logLevel(logLine) + ")");
		}
	}
}
