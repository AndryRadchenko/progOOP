package basicOrderBookManagement;

@SuppressWarnings("serial")
public class OrderPendingException extends Exception {
	@Override
	public String getMessage() {
		return "OrderPendingException";
	}

}
