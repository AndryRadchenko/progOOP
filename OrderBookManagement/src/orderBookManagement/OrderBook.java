package orderBookManagement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class OrderBook {
	// HashMap <orderPrice, LinkedHashSet <orderId>>, a basic orders container
	HashMap<Integer, LinkedHashSet<Integer>> ordersMap = new HashMap<>();

	// HashMap <orderId, Order> for finding an order to cancel
	HashMap<Integer, Order> idsMap = new HashMap<>();

	// two sets to control bid/ask prices
	TreeSet<Integer> bidsSet = new TreeSet<>();
	TreeSet<Integer> asksSet = new TreeSet<>();

	public void processOrder(Order order) {
		char side = order.getSide();
		int price = order.getPrice();

		if (side == 'b') { // buy
			// if there is a sell order at this or better price
			if (!asksSet.isEmpty() && asksSet.first() <= price) {
				executeBuyOrder(order);
			} else {
				registerOrder(order);
			}
		} else if (side == 's') { // sell
			// if there is a buy order at this or better price
			if (!bidsSet.isEmpty() && bidsSet.last() >= price) {
				executeSellOrder(order);
			} else {
				registerOrder(order);
			}
		}
	}

	private void registerOrder(Order order) {
		int price = order.getPrice();
		int id = order.getId();
		char side = order.getSide();

		// the order id is put into the id map
		idsMap.put(id, order);

		LinkedHashSet<Integer> hashSet = ordersMap.get(price);

		if (hashSet == null) { // if the order price is NOT in the orders book
			// a new id set is created
			// the order id is added to the set
			// and the set is put into the ordersMap
			LinkedHashSet<Integer> idsSet = new LinkedHashSet<>();
			idsSet.add(id);
			ordersMap.put(price, idsSet);

			// the price is put into the relevant price set
			if (side == 'b') {
				bidsSet.add(price);
			} else {
				asksSet.add(price);
			}
		} else {// the order price is already IN the orders book
			hashSet.add(id);
		}
	}

	public void removeOrderById(Integer id) {
		Order order = idsMap.get(id);

		if (order != null) {
			Integer price = order.getPrice();
			Character side = order.getSide();

			LinkedHashSet<Integer> hashSet = ordersMap.get(price);
			idsMap.remove(id);
			hashSet.remove(id);

			if (hashSet.isEmpty()) {
				ordersMap.remove(price);

				if (side.equals('b')) {
					bidsSet.remove(price);
				} else {
					asksSet.remove(price);
				}
			}
		}
	}

	public void executeBuyOrder(Order order) {
		int price = order.getPrice();
		int size = order.getSize();
		// first asksSet in this method is never empty
		int betterPrice = asksSet.first();

		while (betterPrice <= price && size > 0) {
			LinkedHashSet<Integer> hashSet = ordersMap.get(betterPrice);
			size = iterateOverOrdersSet(hashSet, size);

			if (!asksSet.isEmpty()) {
				betterPrice = asksSet.first();
			} else {
				break;
			}
		}

		if (size > 0) {
			order.setSize(size);
			registerOrder(order);
		}

	}

	public void executeSellOrder(Order order) {
		int price = order.getPrice();
		int size = order.getSize();
		// first bidsSet in this method is never empty
		int betterPrice = bidsSet.last();

		while (betterPrice >= price && size > 0) {
			LinkedHashSet<Integer> hashSet = ordersMap.get(betterPrice);
			size = iterateOverOrdersSet(hashSet, size);

			if (!bidsSet.isEmpty()) {
				betterPrice = bidsSet.last();
			} else {
				break;
			}
		}

		if (size > 0) {
			order.setSize(size);
			registerOrder(order);
		}
	}
	
	private int iterateOverOrdersSet(LinkedHashSet<Integer> hashSet, int size) {
		Iterator<Integer> itr = hashSet.iterator();
		HashSet<Integer> idsToDelete = new HashSet<>();

		while (itr.hasNext() && size > 0) {
			Integer nextId = itr.next();

			Order nextOrder = idsMap.get(nextId);
			int nextSize = nextOrder.getSize();

			if (nextSize <= size) {
				size -= nextSize;
				idsToDelete.add(nextId);
			} else {
				nextOrder.setSize(nextSize - size);
				size = 0;
			}
		}

		for (Integer id : idsToDelete) {
			removeOrderById(id);
		}

		return size;
	}

	public String executeQuery(Query query) {
		String res = "0";
		char type = query.getType();
		
		switch (type) {
		case 'b':
			if (!bidsSet.isEmpty()) {
				int price = bidsSet.last();
				int size = getOrdersTotalSizeAtPrice(price);
				res = price + "," + size;
			} else {
				res = "empty";
			}
			break;
		case 's':
			if (!asksSet.isEmpty()) {
				int price = asksSet.first();
				int size = getOrdersTotalSizeAtPrice(price);
				res = price + "," + size;
			} else {
				res = "empty";
			}
			break;
		case 'z':
			int price = query.getPrice();
			if (ordersMap.containsKey(price)) {
				int size = getOrdersTotalSizeAtPrice(price);
				res = Integer.toString(size);
			} else {
				res = "0";
			}
			break;
		}
		return res;
	}

	private int getOrdersTotalSizeAtPrice(int price) {
		LinkedHashSet<Integer> hashSet = ordersMap.get(price);
		Iterator<Integer> itr = hashSet.iterator();
		int size = 0;

		while (itr.hasNext()) {
			Integer nextId = itr.next();
			Order nextOrder = idsMap.get(nextId);
			size += nextOrder.getSize();
		}
		return size;
	}
}
