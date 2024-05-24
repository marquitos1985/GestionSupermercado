package productos;

import java.util.TreeSet;

public class GestorProductos {
    private TreeSet<Producto> productos;

    public GestorProductos() {
        this.productos = new TreeSet<>();
    }

    public boolean agregarProducto(Producto producto) { // retorna true si el producto es agregado / false si no.
        return this.productos.add(producto);
    }

    public boolean eliminarProducto (Producto producto) { // retorna true si el producto es eliminado / false si no.
        return this.productos.remove(producto);
    }

    //todo --> Verificar como se desea buscar el producto y hacer el metodo
    //todo --> Verificar si es necesario el metodo de modificar Producto


}
