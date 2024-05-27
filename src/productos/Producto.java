package productos;

import java.util.Comparator;
import java.util.Objects;

public class Producto implements Comparable {
    private String idProducto;
    //todo--> AGREGAR NOMBRE PARA PODER COMPARAR Y VERIFICAR LA EXISTENCIA JUNTO CON LA MARCA
    private String marca;
    private TipoProducto tipoProducto;
    private float precio;
    private String descripcion;//AGREGA INFORMACION ADICIONAL
    private String fechaDeVencimiento;

    //private static int idBase = 10000;

    public Producto(String idProducto, String marca, TipoProducto tipoProducto,
                    Float precio, String descripcion, String fechaDeVencimiento) {

        this.idProducto = idProducto;
        this.marca = marca;
        this.tipoProducto = tipoProducto;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fechaDeVencimiento = fechaDeVencimiento;

    }

    public Producto() {
    }

    public String getIdProducto() {
        return idProducto;
    }

    private void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
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

    public Float getPrecio() {
        return precio;
    }

    protected void setPrecio(Float precio) {
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
    public int compareTo(Object o) {
        return this.getIdProducto().compareTo(((Producto) o).getIdProducto());
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto='" + idProducto + '\'' +
                ", marca='" + marca + '\'' +
                ", tipoProducto=" + tipoProducto +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", fechaDeVencimiento='" + fechaDeVencimiento + '\'' +
                '}';
    }
}
