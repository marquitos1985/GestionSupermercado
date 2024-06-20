package productos;

import java.util.Comparator;
import java.util.Objects;

public class Producto implements Comparable<Producto> {
    private String idProducto;
    private String nombre;
    private String marca;
    private TipoProducto tipoProducto;
    private float precio;
    private String descripcion;//AGREGA INFORMACION ADICIONAL
    private String fechaDeVencimiento;
    private int stock;

    //private static int idBase = 10000;

    public Producto(String idProducto, String nombre, String marca, TipoProducto tipoProducto,
                    Float precio, String descripcion, String fechaDeVencimiento, int stock) {

        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.tipoProducto = tipoProducto;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fechaDeVencimiento = fechaDeVencimiento;
        if(stock >= 0){
            this.stock = stock;
        }else {
            this.stock = 0;
        }


    }

    public Producto() {
    }

    public String getIdProducto() {
        return idProducto;
    }

    private void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getMarca() {
        return marca;
    }

    protected void setMarca(String marca) {
        this.marca = marca;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    protected void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public float getPrecio() {
        return precio;
    }

    protected void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    protected void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    protected void setFechaDeVencimiento(String fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(idProducto, producto.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }

    @Override
    public int compareTo(Producto o) {
        return this.idProducto.compareTo(o.getIdProducto());
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto='" + idProducto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", tipoProducto=" + tipoProducto +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", fechaDeVencimiento='" + fechaDeVencimiento + '\'' +
                '}';
    }
}
