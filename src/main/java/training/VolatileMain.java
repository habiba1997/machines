package training;

class VolatileData {
	private volatile int counter = 0;

	public int getCounter() {
		return counter;
	}

	public void increaseCounter() {
		++counter; // increases the value of counter by 1
	}
}

class VolatileThread extends Thread {
	private final VolatileData data;

	VolatileThread(final VolatileData data) {
		this.data = data;
	}

	@Override
	public void run() {
		int oldValue = data.getCounter();
		System.out.println("[Thread " + Thread.currentThread().getId() + "]: Old value = " + oldValue);
		data.increaseCounter();
		int newValue = data.getCounter();
		System.out.println("[Thread " + Thread.currentThread().getId() + "]: New value = " + newValue);
	}
}

public class VolatileMain {

	private static final int NO_OF_THREADS = 2;

	public static void main(final String[] args) throws InterruptedException {
		VolatileData volatileData = new VolatileData(); // object of VolatileData class
		Thread[] threads = new Thread[NO_OF_THREADS]; // creating Thread array
		for (int i = 0; i < NO_OF_THREADS; ++i) {
			threads[i] = new VolatileThread(volatileData);
		}
		for (int i = 0; i < NO_OF_THREADS; ++i) {
			threads[i].start(); // starts all reader threads
		}
		for (int i = 0; i < NO_OF_THREADS; ++i) {
			threads[i].join(); // wait for all threads
		}
	}
}
