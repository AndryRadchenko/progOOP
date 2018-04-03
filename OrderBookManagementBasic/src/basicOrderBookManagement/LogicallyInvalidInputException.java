package basicOrderBookManagement;

@SuppressWarnings("serial")
public class LogicallyInvalidInputException extends Exception{

	@Override
	public String getMessage() {
		return "LogicallyInvalidInputException";
	}

}
