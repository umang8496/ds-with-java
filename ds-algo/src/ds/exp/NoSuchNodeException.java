package ds.exp;

public class NoSuchNodeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NoSuchNodeException(String errMsg) {
		super(errMsg);
	}
	
	public NoSuchNodeException(String errMsg, Throwable error) {
		super(errMsg, error);
	}
	
}
