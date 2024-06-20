package caja.carrito;

import productos.Producto;

import java.util.HashMap;

public class Carrito {
    private HashMap<Producto, Integer> productos;

    public Carrito() {
        this.productos = new HashMap<>();
    }

    public void agregarProducto(Producto producto){
        this.productos.put(producto, this.productos.get(producto) + 1);
    }

    public void eliminarProducto(Producto producto){
        int cantiad = 0;
        if(this.productos.containsKey(producto)){
            cantiad = this.productos.get(producto);
            cantiad = cantiad -1;
        }
    }

}
