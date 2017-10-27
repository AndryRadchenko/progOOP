package ex04DoubleCola;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	public static void main(String[] args) {

		Deque<String> boys = new ArrayDeque<>();
		boys.add("Sheldon");
		boys.add("Leonard");
		boys.add("Volovitc");
		boys.add("Kutrapalli");
		boys.add("Penny");

		int n = 2;

		for (int i = 0; i < n; i++) {
			boys.addLast(boys.peekFirst());
			boys.addLast(boys.pollFirst());
		}

		System.out.println(boys);

	}

}
