package TechHub.ShopErp.helper;

public class ResorcenotFoundException extends RuntimeException {

	public ResorcenotFoundException() {
		super("Resource not found on server !!");
	}
	
	public ResorcenotFoundException(String msg) {
		super(msg);
	}
	
}