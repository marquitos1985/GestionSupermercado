package productos;

public class ProductoPorPeso extends Producto {
    private float peso;
    private float precioPorPeso;

    public ProductoPorPeso(String idProducto, String marca, TipoProducto tipoProducto, Float precio, String descripcion, String fechaDeVencimiento, float peso, float precioPorPeso) {
        super(idProducto, marca, tipoProducto, precio, descripcion, fechaDeVencimiento);
        this.peso = peso;
        this.precioPorPeso = precioPorPeso;
    }

}
