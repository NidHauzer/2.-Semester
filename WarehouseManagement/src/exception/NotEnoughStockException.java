package exception;

public class NotEnoughStockException extends Exception {
		public NotEnoughStockException(String errorMessage) {
			super(errorMessage);
		}
}
