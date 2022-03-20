import java.time.Instant;
import java.lang.Math;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class ListExample {
	final String GOO = "GOOG";
	final String AMA = "AMAZ";
	private final int iterations = 3;
	private long mSeconds;

	class ShopCart {
		final private int items;
		final private String UID;
		final private String company;
		double UID_SUF = Math.random();

		ShopCart(int company, int items) {
			this.items = items;
			if (company == 0) {
				this.UID = GOO + UID_SUF;
				this.company = "Google";
			} else {
				this.UID = AMA + UID_SUF;
				this.company = "Amazon";
			}
		}

		@Override
		public String toString() {
			return "Your shopping " + this.company + " with " + this.items + " items   || UID " + this.UID + "\n";
		}
	}

	public static void main(String[] args) {
		int numberOfTimes;

		ArrayList<ShopCart> arrayList = new ArrayList<>();
		LinkedList<ShopCart> linkedList = new LinkedList<>();
		ListExample list = new ListExample();
		Scanner keyboard = new Scanner(System.in);

		//program runs 3 times then exits
		for (int b = 0; b < list.iterations; b++) {
			System.out.println("Number of times to create Shopping Carts: 1=10000, 2=100000, 3=1000000");
			int input = keyboard.nextInt();

			switch (input) {
				case 1:
					numberOfTimes = 10000;
					break;
				case 2:
					numberOfTimes = 100000;
					break;
				case 3:
					numberOfTimes = 1000000;
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + input);
			}

			System.out.println("Type of Shopping cart: 1=ArrayList, 2=LinkedList");

			int input2 = keyboard.nextInt();

			long execTimeStart = Instant.now().toEpochMilli();

			Random randomIndex = new Random();

			switch (input2) {
				case 1:
					for (int i = 0; i < numberOfTimes; i++) {
						ListExample.ShopCart amazon = list.new ShopCart(1, new Random().nextInt(5000));
						ListExample.ShopCart google = list.new ShopCart(0, new Random().nextInt(5000));

						arrayList.add(google);
						arrayList.add(amazon);
						arrayList.remove(randomIndex.nextInt(arrayList.size()));

						System.out.println(arrayList.get(randomIndex.nextInt(arrayList.size())));
					}
					break;
				case 2:
					for (int i = 0; i < numberOfTimes; i++) {
						var amazon = list.new ShopCart(1, new Random().nextInt(5000));
						var google = list.new ShopCart(0, new Random().nextInt(5000));

						linkedList.add(google);
						linkedList.add(amazon);

						linkedList.remove(randomIndex.nextInt(linkedList.size()));

						System.out.println(linkedList.get(randomIndex.nextInt(linkedList.size())));
					}
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + input);
			}

			long execTimeEnd = Instant.now().toEpochMilli();
			long millis = execTimeEnd - execTimeStart;
			System.out.println("Exec time minutes " + TimeUnit.MILLISECONDS.toMinutes(millis));
			System.out.println("Exec time seconds " + TimeUnit.MILLISECONDS.toSeconds(millis));
			System.out.println("Exec time milliseconds " + millis);

			list.storeTime(millis);
		}
		System.out.println(list.getMillis());
	}

	public void storeTime(double mSeconds) {
		this.mSeconds += mSeconds;
	}

	public String getMillis() {
		return "The average was " + TimeUnit.MILLISECONDS.toSeconds(this.mSeconds) / this.iterations + " seconds and " + this.mSeconds / this.iterations + " milliseconds";
	}
}