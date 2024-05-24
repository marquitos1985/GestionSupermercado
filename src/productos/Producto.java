package productos;

public class Producto {
    private String idProducto;
    private String marca;
    private TipoProducto tipoProducto;
    private Float precio;
    private String descripcion;
    private String fechaDeVencimiento;

    //private static int idBase = 10000;

    public Producto(String idProducto, String marca, TipoProducto tipoProducto, Float precio, String descripcion, String fechaDeVencimiento) {

        this.idProducto = idProducto;
        this.marca = marca;
        this.tipoProducto = tipoProducto;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public Producto() {
    }


}
