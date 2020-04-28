package ds.exp;

public class NullNodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NullNodeException(String errMsg) {
		super(errMsg);
	}

	public NullNodeException(String errMsg, Throwable error) {
		super(errMsg, error);
	}

}
