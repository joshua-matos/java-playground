import java.util.ArrayList;

public final class Counter {
	private volatile int counter;
	 int num;

	public synchronized int increment() {
		return counter +=1;
	}

	public  synchronized void setCounter(){
		this.counter +=1;
	}

	public void setNum(){
		this.num += 5;
	}

	public int getNum(){
		return num;
	}


	public static void main(String[] args) {
		Counter counter = new Counter();

		counter.setNum();

		Counter counter2 = counter;
		counter.setNum();
		counter2.setNum();

		ArrayList<Counter> arry = new ArrayList<>();

		System.out.println(counter.getNum());

	}
}