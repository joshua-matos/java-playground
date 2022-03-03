public class Accumulator {

	public static void main(String... args) {
		Accumulator testAccumulator = new Accumulator();
		String[] myArray = {};
		System.out.flush();
		System.out.println(testAccumulator.toSentence(myArray) + "\n");
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
}
