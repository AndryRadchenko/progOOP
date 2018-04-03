package basicOrderBookManagement;

public class IncomingOrder {
	private final char type;
	private final int size;
	
	public IncomingOrder(char type, int size) {
		super();
		this.type = type;
		this.size = size;
	}

	public char getType() {
		return type;
	}

	public int getSize() {
		return size;
	}	

}
