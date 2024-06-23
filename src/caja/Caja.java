package caja;

import caja.carrito.Carrito;
import caja.facturacion.Factura;
import productos.Almacen;
import productos.GestorProductos;
import productos.Producto;
import productos.StockException;
import usuarios.clientes.Cliente;
import usuarios.empleados.vendedor.Vendedor;

public class Caja {
    private Cliente cliente;
    private Vendedor vendedor;
    private Carrito carrito;
    private GestorProductos gestorProductos;
    private Factura factura;

    public Caja() {
        this.carrito = new Carrito();
        //this.gestorProductos = new GestorProductos();
    }

    public void agregarProducto(Producto producto) throws StockException {
        if (producto.getStock() > 0){
            carrito.agregar(producto);
            gestorProductos.modificarStock(producto, -1);
        }else {
            throw new StockException("Producto sin stock...");
        }
    }

    public void eliminarProducto(Producto producto) throws StockException{
        if(carrito.existe(producto)){
            carrito.restar(producto);
            gestorProductos.modificarStock(producto, 1 );
        }else {
            throw new StockException("Producto inexistente en carrito...");
        }
    }

    public void finalizarCompra(){
        Factura factura = new Factura(cliente,vendedor,getSubtotal(),carrito);
        factura.generarPdfFactura();
    }

    public GestorProductos getGestorProductos() {
        return gestorProductos;
    }

    public void setGestorProductos(GestorProductos gestorProductos) {
        this.gestorProductos = gestorProductos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public float getSubtotal(){
        return this.carrito.getSubtotalCarrito();
    }

}
