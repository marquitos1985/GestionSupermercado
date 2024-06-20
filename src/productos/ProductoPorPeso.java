package productos;

public class ProductoPorPeso extends Producto {//// puede que hay a q implementar una interfaz pesable. Para modificar el precio, porque hay 2, el de precio por peso y el de precio (ver contructor)
    private float peso;
    private float precioPorPeso;

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

}
