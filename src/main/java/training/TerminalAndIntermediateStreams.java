package training;

import java.util.stream.IntStream;

public class TerminalAndIntermediateStreams {

	public static void main(String[] args) {
		int[] holder = new int[] { 2 };
		System.out.println("hello");
		try (IntStream sums = IntStream
				.of(1, 2, 3)
				.map(val -> val + holder[0])) {

			System.out.println("hello2");
			holder[0] = 0;

			System.out.println(sums.sum());
		}
	}
}
