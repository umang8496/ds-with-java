package ds.exp;

public class GraphNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GraphNotFoundException(String errMsg) {
		super(errMsg);
	}

	public GraphNotFoundException(String errMsg, Throwable error) {
		super(errMsg, error);
	}

}
