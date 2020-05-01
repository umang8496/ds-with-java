package ds.exp;

public class IncompatibleGraphTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IncompatibleGraphTypeException(String errMsg) {
		super(errMsg);
	}

	public IncompatibleGraphTypeException(String errMsg, Throwable error) {
		super(errMsg, error);
	}

}
