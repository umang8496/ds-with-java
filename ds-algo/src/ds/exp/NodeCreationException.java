package ds.exp;

public class NodeCreationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NodeCreationException(String errorMsg) {
		super(errorMsg);
	}

	public NodeCreationException(String errorMsg, Throwable error) {
		super(errorMsg, error);
	}
}
