package ex01;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) {
		superlist();
	}

	public static void superlist() {
		Deque<Integer> mylist = new ArrayDeque<>();

		for (int i = 0; i < 10; i++) {
			mylist.add(((int) (Math.random() * 10)));
		}

		mylist.pollFirst();
		mylist.pollFirst();
		mylist.pollLast();
		System.out.println(mylist);
	}
}
