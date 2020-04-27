package ds.exp;

public class NoRootAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoRootAvailableException(String errorMsg) {
		super(errorMsg);
	}

	public NoRootAvailableException(String errorMsg, Throwable error) {
		super(errorMsg, error);
	}

}
