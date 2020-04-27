package ds.exp;

public class ObjectCreationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectCreationException(String errorMsg) {
		super(errorMsg);
	}

	public ObjectCreationException(String errorMsg, Throwable error) {
		super(errorMsg, error);
	}
}
