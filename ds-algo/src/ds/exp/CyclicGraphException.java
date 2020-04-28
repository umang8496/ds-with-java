package ds.exp;

public class CyclicGraphException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CyclicGraphException(String errMsg) {
		super(errMsg);
	}

	public CyclicGraphException(String errMsg, Throwable error) {
		super(errMsg, error);
	}

}
