package basicOrderBookManagement;

import java.util.HashMap;
import java.util.TreeSet;

public class OrderBook {
	HashMap<Integer, Order> orders = new HashMap<>();
	TreeSet<Integer> bidsSet = new TreeSet<>();
	TreeSet<Integer> asksSet = new TreeSet<>();

	public OrderBook() {
		super();
	}

	public Order getOrderBookUpdate(Integer price) {
		return orders.get(price);
	}

	public void SetOrderBookUpdate(Order bookUpdate) throws LogicallyInvalidInputException {
		char type = bookUpdate.getType();
		int price = bookUpdate.getPrice();

		if (type == 'a' && !bidsSet.contains(price)) {
			asksSet.add(price);
			orders.put(price, bookUpdate);
		} else if (type == 'b' && !asksSet.contains(price)) {
			bidsSet.add(price);
			orders.put(price, bookUpdate);
		} else {
			throw new LogicallyInvalidInputException();
		}
	}

	public void executeOrder(IncomingOrder order) throws OrderPendingException {
		char orderType = order.getType();
		int size = order.getSize();
		TreeSet<Integer> set = orderType == 's' ? bidsSet : asksSet;
		Integer key = null;

		while (size > 0) {
			if (!set.isEmpty()) {
				key = orderType == 's' ? set.last() : set.first();
				Order relevantOrder = orders.get(key);
				int sizeAvaliableAtThePrice = relevantOrder.getSize();

				if (size < sizeAvaliableAtThePrice) {
					relevantOrder.setSize(sizeAvaliableAtThePrice - size);
					size = 0;
				} else {
					size -= sizeAvaliableAtThePrice;
					orders.remove(key);
					set.remove(key);
				}
			} else {
				throw new OrderPendingException();
			}

		}
	}

	public String executeQuery(Query query) {
		String res = "0";
		switch (query.getType()) {
		case 'b':
			if (!bidsSet.isEmpty()) {
				Order order = orders.get(bidsSet.last());
				res = order.getPrice() + "," + order.getSize();
			} else {
				res = "empty";
			}
			break;
		case 'a':
			if (!asksSet.isEmpty()) {
				Order order = orders.get(asksSet.first());
				res = order.getPrice() + "," + order.getSize();
			} else {
				res = "empty";
			}
			break;
		case 's':
			Integer price = query.getPrice();

			if (orders.containsKey(price)) {
				Order order = orders.get(price);
				int size = order.getSize();
				res = Integer.toString(size);
			}
			break;
		}
		return res;
	}

//	public Order getOrder(Integer key) {
//		return orders.get(key);
//	}
//
//	public Order getSizeAtPrice(Integer price) {
//		return orders.get(price);
//	}

//	public void addToAsksSet(Integer price) {
//		asksSet.add(price);
//	}
//
//	public void addToBidsSet(Integer price) {
//		bidsSet.add(price);
//	}
}
