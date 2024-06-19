package productos;

public class StockException extends Exception{
    public StockException() {
    }

    public StockException(String message) {
        super(message);
    }
}
