package productos;

public class PrecioNegativoException extends Exception{
    public PrecioNegativoException() {
    }

    public PrecioNegativoException(String message) {
        super(message);
    }
}
