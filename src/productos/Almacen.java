package productos;

import java.util.HashMap;
import java.util.Map;

public class Almacen {
    private HashMap<Producto, Integer> stock;

    public Almacen() {
        this.stock = new HashMap<>();
    }

    public void modificarStock (Producto producto, Integer cantidad) throws StockException {
        if (this.stock.containsKey(producto)){
            if (cantidad >= 0){
                this.stock.replace(producto, this.stock.get(producto) + cantidad);
            }else{
                if(this.stock.get(producto) >= cantidad){
                    this.stock.replace(producto, this.stock.get(producto) - cantidad);
                } else {
                    throw new StockException("Stock existente menor a stock a eliminar.");
                }
            }
        } else {
            if (cantidad >= 0) {
                this.stock.put(producto, cantidad);
            } else {
                throw new StockException("Producto inexistente, stock negativo.");
            }
        }
    }

    public int buscarStockProducto (Producto producto) throws StockException {
        if (this.stock.containsKey(producto)) {
            return this.stock.get(producto);
        } else {
            throw new StockException("Producto inexistente en stock");
        }
    }

}
