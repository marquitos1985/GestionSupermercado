package caja;

import caja.carrito.Carrito;
import productos.Almacen;
import productos.GestorProductos;
import productos.Producto;
import productos.StockException;

public class Caja {
    private Carrito carrito;
    private GestorProductos gestorProductos;

    public Caja() {
        this.carrito = new Carrito();
        this.gestorProductos = new GestorProductos();
    }

    public void agregarProducto(Producto producto) throws StockException {
        if (producto.getStock() > 0){
            carrito.agregarProducto(producto);
            gestorProductos.modificarStock(producto, -1);
        }else {
            throw new StockException("Producto sin stock...");
        }
    }

}
