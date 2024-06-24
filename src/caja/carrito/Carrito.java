package caja.carrito;

import productos.Producto;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class Carrito {
    private LinkedHashMap<Producto, Integer> productos;

    public Carrito() {
        this.productos = new LinkedHashMap<>();
    }

    public void agregar(Producto producto){
        if(this.productos.containsKey(producto)){
            this.productos.put(producto, this.productos.get(producto) + 1);
        }else{
            this.productos.put(producto, 1);
        }

    }

    public LinkedHashMap<Producto, Integer> getProductos() {
        return productos;
    }

    public void restar(Producto producto){
        int cantidad = 0;
        if(this.productos.containsKey(producto)){
            cantidad = this.productos.get(producto);
            cantidad = cantidad -1;
            if(cantidad == 0){
                this.productos.remove(producto);
            }else{
                this.productos.put(producto, cantidad);
            }

        }
    }

    public List<String> listar(){
        LinkedList<String> lista = new LinkedList<>();

        for (Producto producto: this.productos.keySet()){
            lista.add(producto.getNombre() + " - " + producto.getMarca() + " - " + producto.getDescripcion() + " - cant: " + this.productos.get(producto));
        }
        return lista;
    }

    public boolean existe(Producto producto){
        return this.productos.containsKey(producto);
    }

    public float subTotal(){
        float suma = 0;
        for (Producto producto: this.productos.keySet()){
            suma = suma + (producto.getPrecio()*this.productos.get(producto));
        }
        return suma;
    }

    public float getSubtotalCarrito(){
        float subtotal = 0;
        for (Producto producto : this.productos.keySet()){
            subtotal = subtotal + (producto.getPrecio()*this.productos.get(producto));
        }
        return subtotal;
    }
    public boolean isVacio(){
        return this.productos.isEmpty();
    }
}
