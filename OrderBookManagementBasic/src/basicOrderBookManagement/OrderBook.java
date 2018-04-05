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
		int size = bookUpdate.getSize();
		int price = bookUpdate.getPrice();
		char type = bookUpdate.getType();
		
		if (size==0){
			removeOrder(price, type);
			return;
		}
		
		if (type == 'a' && (bidsSet.isEmpty() || price > bidsSet.last())){
			asksSet.add(price);
			orders.put(price, bookUpdate);
		} else if (type == 'b' && (asksSet.isEmpty() || price < asksSet.first())) {
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
		Integer price = null;

		while (size > 0) {
			if (!set.isEmpty()) {
				price = orderType == 's' ? set.last() : set.first();
				Order relevantOrder = orders.get(price);
				int sizeAvaliableAtThePrice = relevantOrder.getSize();

				if (size < sizeAvaliableAtThePrice) {
					relevantOrder.setSize(sizeAvaliableAtThePrice - size);
					size = 0;
				} else {
					size -= sizeAvaliableAtThePrice;
					orders.remove(price);
					set.remove(price);
				}
			} else {
				throw new OrderPendingException();
			}

		}
	}
	
	public void removeOrder(int price, char type){
		TreeSet<Integer> set = type == 'a' ? asksSet : bidsSet;
		set.remove(price);
		orders.remove(price);

	}

	public String executeQuery(Query query) {
		String res = "0";
		switch (query.getType()) {
		case 'b': // best_bid query
			if (!bidsSet.isEmpty()) {
				Order order = orders.get(bidsSet.last());
				res = order.getPrice() + "," + order.getSize();
			} else {
				res = "empty";
			}
			break;
		case 'a': // best_ask query
			if (!asksSet.isEmpty()) {
				Order order = orders.get(asksSet.first());
				res = order.getPrice() + "," + order.getSize();
			} else {
				res = "empty";
			}
			break;
		case 's': // size (at a specified price) query
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
}
