package orderBookManagement;

public class Order {
	private final int id;
	private final char side;
	private final int price;
	private int size;
	
	public Order(int id, char side, int price, int size) {
		super();
		this.id = id;
		this.side = side;
		this.price = price;
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public char getSide() {
		return side;
	}

	public int getPrice() {
		return price;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
}
