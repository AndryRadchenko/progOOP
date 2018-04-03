package basicOrderBookManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) {
		// creating input/output files
		File fileIn = new File("input.txt");
		File fileOut = new File("output.txt");
		File errorlog = new File("error.log");

		// set true when error logging is needed
		boolean errorsBeingLogged = false;

		OrderBook orders = new OrderBook();

		try (BufferedReader reader = new BufferedReader(new FileReader(fileIn));
				PrintWriter pWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileOut)));
				PrintWriter errWriter = new PrintWriter(new BufferedWriter(new FileWriter(errorlog)))) {

			// Parsing text file
			String inputLine = "";
			for (; (inputLine = reader.readLine()) != null;) {
				String[] elements = inputLine.split(",");
				char type = ' ';

				try {
					type = elements[0].charAt(0);
				} catch (StringIndexOutOfBoundsException e) {
					// if an empty line gets between the lines, the parsing will
					// continue
					// because that is not a critical error.
					// although assumed that this is pretty unlikely to happen,
					// but anyway
					continue;
				}

				switch (type) {
				case 'u': // a line is an order book update
					Order bookUpdate = createOrderUpdate(elements);
					try {
						orders.SetOrderBookUpdate(bookUpdate);
					} catch (LogicallyInvalidInputException e) {
						pWriter.println("error");
						if (errorsBeingLogged) {
							errWriter.println(e.getMessage());
						}
						// the program exits because of the logically invalid
						// input
						return;
						// e.printStackTrace();
					}
					break;
				case 'o': // a line is an incoming order
					IncomingOrder order = createIncomingOrder(elements);
					try {
						orders.executeOrder(order);
					} catch (OrderPendingException e) {
						if (errorsBeingLogged) {
							errWriter.println(e.getMessage());
						}
						// e.printStackTrace();
					}
					break;
				case 'q': // a line is a query
					Query query = createQuery(elements);
					String res = orders.executeQuery(query);
					pWriter.println(res);
					break;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// creates an OrderUpdate Object from an input line
	private static Order createOrderUpdate(String[] elements) {
		int price = Integer.parseInt(elements[1]);
		int size = Integer.parseInt(elements[2]);
		String typeStr = elements[3];
		char type = ' ';
		if (typeStr.equals("bid")) {
			type = 'b';
		} else if (typeStr.equals("ask")) {
			type = 'a';
		}
		return new Order(price, size, type);
	}

	// creates an IncomingOrder Object from an input line
	private static IncomingOrder createIncomingOrder(String[] elements) {
		String typeStr = elements[1];
		char type = ' ';
		if (typeStr.equals("buy")) {
			type = 'b';
		} else if (typeStr.equals("sell")) {
			type = 's';
		}
		int size = Integer.parseInt(elements[2]);
		return new IncomingOrder(type, size);
	}

	// creates a Query Object from an input line
	private static Query createQuery(String[] elements) {
		String typeStr = elements[1];
		char type = ' ';
		if (typeStr.equals("best_bid")) {
			type = 'b';
		} else if (typeStr.equals("best_ask")) {
			type = 'a';
		} else {
			type = 's';
			int price = Integer.parseInt(elements[2]);
			return new Query(type, price);
		}
		return new Query(type);
	}
}
