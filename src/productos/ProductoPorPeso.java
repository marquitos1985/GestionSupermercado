package productos;

public class ProductoPorPeso extends Producto{
    private float peso;
    private float precioPorPeso;

    public ProductoPorPeso() {

    }

    public ProductoPorPeso(String idProducto, String nombre, String marca, TipoProducto tipoProducto,
                           float precio, String descripcion, String fechaDeVencimiento,  int stock,
                           float peso, float precioPorPeso) {
        super(idProducto, nombre, marca, tipoProducto, precio, descripcion, fechaDeVencimiento, stock);
        this.peso = peso;
        this.precioPorPeso = precioPorPeso;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getPrecioPorPeso() {
        return precioPorPeso;
    }

    public void setPrecioPorPeso(float precioPorPeso) {
        this.precioPorPeso = precioPorPeso;
    }

    @Override
    public String toString() {
        return String.format(super.toString() + " - Peso: %s - Precio por peso: %s", this.peso, this.precioPorPeso);
    }

}
