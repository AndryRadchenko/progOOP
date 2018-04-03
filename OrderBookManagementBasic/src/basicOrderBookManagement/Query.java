package basicOrderBookManagement;

public class Query {
	private final char type;
	private int price;
	
	public Query(char type, int price) {
		super();
		this.type = type;
		this.price = price;
	}

	public Query(char type) {
		super();
		this.type = type;
	}

	public char getType() {
		return type;
	}

	public int getPrice() {
		return price;
	}
}
