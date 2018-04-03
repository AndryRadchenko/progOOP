package basicOrderBookManagement;

public class Order {
	private int price;
	private int size;
	private final char type;
	
	public Order(int price, int size, char type) {
		super();
		this.price = price;
		this.size = size;
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public int getSize() {
		return size;
	}

	public char getType() {
		return type;
	}

	public void setSize(int size) {
		this.size = size;
	}	
	
}
