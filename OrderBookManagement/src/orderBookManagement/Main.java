package orderBookManagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		// creating input/output files
		File fileIn = new File("input.txt");
		File fileOut = new File("output.txt");

		// SET THIS TRUE IF NON-UNIQUE ORDER IDs ARE LIKELY TO HAPPEN
		boolean isIdUniqunessChecked = true;
		HashSet<Integer> ids = new HashSet<>();

		OrderBook orders = new OrderBook();

		try (BufferedReader reader = new BufferedReader(new FileReader(fileIn));
				PrintWriter pWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileOut)))) {

			// Parsing text file
			String inputLine = "";
			for (; (inputLine = reader.readLine()) != null;) {
				String[] elements = inputLine.split(",");
				char type = ' ';

				try {
					type = elements[0].charAt(0);
				} catch (StringIndexOutOfBoundsException e) {
					// if an empty line gets between the lines,
					// the parsing will continue
					// because that is not a critical error.
					// although assumed that this is pretty unlikely to happen,
					// but anyway
					continue;
				}

				switch (type) {
				case 'o': // a line is an order
					Order order = createOrder(elements);

					if (isIdUniqunessChecked == true) {
						if (ids.contains(order.getId()) == true) {
							pWriter.println("ERROR");
							return;
						} else {
							ids.add(order.getId());
						}
					}

					orders.processOrder(order);
					break;
				case 'c': // a line is an order cancel request
					Integer id = Integer.parseInt(elements[1]);
					orders.removeOrderById(id);
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

	// creates an Order Object from an input line
	private static Order createOrder(String[] elements) {
		int id = Integer.parseInt(elements[1]);
		char side = elements[2].charAt(0);
		int price = Integer.parseInt(elements[3]);
		int size = Integer.parseInt(elements[4]);
		return new Order(id, side, price, size);
	}

	// creates a Query Object from an input line
	private static Query createQuery(String[] elements) {
		String typeStr = elements[1];

		char type = ' ';
		if (typeStr.equals("buyers")) {
			type = 'b';
		} else if (typeStr.equals("sellers")) {
			type = 's';
		} else {
			type = 'z';
			int price = Integer.parseInt(elements[2]);
			return new Query(type, price);
		}
		return new Query(type);
	}
}
