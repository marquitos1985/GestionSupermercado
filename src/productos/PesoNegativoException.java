package productos;

public class PesoNegativoException extends Exception{
    public PesoNegativoException() {
    }

    public PesoNegativoException(String message) {
        super(message);
    }
}
