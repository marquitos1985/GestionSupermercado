package productos;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GestorProductos {
    private TreeSet<Producto> productos;
    private ObjectMapper objectMapper;


    public GestorProductos() {
        this.productos = new TreeSet<>();
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
/*
    public Producto crearProducto (String idProducto, String nombre, String marca, TipoProducto tipoProducto, Float precio, String descripcion, String fechaDeVencimiento, int stock) {
        return new Producto(crearId(tipoProducto), nombre, marca, tipoProducto, precio, descripcion, fechaDeVencimiento, stock);
    }
    public Producto crearProductoPorPeso (String idProducto, String nombre, String marca, TipoProducto tipoProducto, float precio, String descripcion, String fechaDeVencimiento, int stock,
                                          float peso, float precioPorPeso){
        return new ProductoPorPeso(crearId(tipoProducto), nombre, marca, tipoProducto, precio, descripcion, fechaDeVencimiento, stock, peso, precioPorPeso);

    }


 */
    public Producto crearProducto (String nombre, String marca, TipoProducto tipoProducto, Float precio, String descripcion, String fechaDeVencimiento, int stock) {
        return new Producto(crearId(tipoProducto), nombre, marca, tipoProducto, precio, descripcion, fechaDeVencimiento, stock);
    }
    public Producto crearProductoPorPeso (String nombre, String marca, TipoProducto tipoProducto, float precio, String descripcion, String fechaDeVencimiento, int stock,
                                          float peso, float precioPorPeso){
        return new ProductoPorPeso(crearId(tipoProducto), nombre, marca, tipoProducto, precio, descripcion, fechaDeVencimiento, stock, peso, precioPorPeso);

    }

    public boolean agregarProducto(Producto producto) { // retorna true si el producto es agregado / false si no.
        return this.productos.add(producto);
    }

    public boolean eliminarProducto (Producto producto) { // retorna true si el producto es eliminado / false si no.
        return this.productos.remove(producto);
    }

    public Producto buscarPorId(String id){
        return this.productos.stream().filter(p -> p.getIdProducto().equals(id)).findAny().orElse(null);
    }

    public List<Producto> buscarPorTipo(TipoProducto tipo){//retorna los productos de un tipo especificado
        return this.productos.stream().filter(producto -> producto.getTipoProducto().equals(tipo)).collect(Collectors.toList());
    }
    public List<Producto> buscarPorNombre(String nombre){//retorna los productos con un nombre especificado
        return this.productos.stream().filter(producto -> producto.getNombre().equals(nombre)).collect(Collectors.toList());
    }
    public List<Producto> buscarPorMarca(String marca){//retorna los productos con una marca especificado
        return this.productos.stream().filter(producto -> producto.getMarca().equals(marca)).collect(Collectors.toList());
    }

    public boolean modificarNombre(Producto producto, String nombre){
        boolean salida = false;
        if(this.productos.contains(producto)){
            producto.setMarca(nombre);
            salida = true;
        }
        return salida;
    }
    public boolean modificarMarca(Producto producto, String marca){
        boolean salida = false;
        if(this.productos.contains(producto)){
            producto.setMarca(marca);
            salida = true;
        }
        return salida;
    }
    public boolean modificarTipoProducto(Producto producto, TipoProducto tipo){
        boolean salida = false;
        if(this.productos.contains(producto)){
            producto.setTipoProducto(tipo);
            salida = true;
        }
        return salida;
    }
    public boolean modificarPrecio(Producto producto, float precio){
        boolean salida = false;
        if(this.productos.contains(producto)){
            producto.setPrecio(precio);
            salida = true;
        }
        return salida;
    }
    public boolean modificarDescripcion(Producto producto, String descripcion){
        boolean salida = false;
        if(this.productos.contains(producto)){
            producto.setDescripcion(descripcion);
            salida = true;
        }
        return salida;
    }
    public boolean modificarFechaVenc(Producto producto, String fechaVenc){
        boolean salida = false;
        if(this.productos.contains(producto)){
            producto.setFechaDeVencimiento(fechaVenc);
            salida = true;
        }
        return salida;
    }
    /*
    public void listarProductos(){//TODO: DEVOLVER UNA LISTA
        this.productos.stream().forEach(producto -> System.out.println(producto));
    }

     */
    public List<Producto> listar(){
        return this.productos.stream().toList();
    }

    public int buscarUltimoIntId (TipoProducto tipoProducto){// genera un nuevo id por tipo de producto a generar
        int salida = 100001;
        List<Producto> lista = this.productos.stream().filter(producto -> producto.getTipoProducto().equals(tipoProducto)).collect(Collectors.toList());
        if(!lista.isEmpty()){
            List<Integer> numerosIdProducto = new ArrayList<>();
            for (Producto producto: lista){
                numerosIdProducto.add(Integer.parseInt(producto.getIdProducto().replace(producto.getTipoProducto().toString(), "")));//quita el tipo de producto del ID y pasa a entero la parte del numero
            }
            salida = Collections.max(numerosIdProducto) + 1;
        }
        return salida;
    }

    public String crearId (TipoProducto tipoProducto) {
        return String.format("%s",tipoProducto.toString() + buscarUltimoIntId(tipoProducto));
    }

    public boolean levantarArchivoJsonProductos(String nombreArchivo) {
        try {
            JsonNode root = objectMapper.readTree(new File(nombreArchivo));
            for (JsonNode nodo : root) {
                if (nodo.has("peso")) {
                    ProductoPorPeso productoPorPeso = objectMapper.treeToValue(nodo, ProductoPorPeso.class);
                    this.productos.add(productoPorPeso);
                } else {
                    Producto producto = objectMapper.treeToValue(nodo, Producto.class);
                    this.productos.add(producto);
                }
            }
            return true;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        }



    public boolean guardarArchivoJsonProductos(String nombreArchivo) {
            try {
                List<Map<String, Object>> productos = new ArrayList<>();

                for (Producto prod : this.productos) {
                    Map<String, Object> productosMap = objectMapper.convertValue(prod, Map.class);
                    productosMap.put("type", "producto");
                    productos.add(productosMap);
                }
                for (Producto peso : this.productos) {
                    Map<String, Object> prodPesoMap = objectMapper.convertValue(peso, Map.class);
                    prodPesoMap.put("type", "porPeso");
                    productos.add(prodPesoMap);
                }

                objectMapper.writeValue(new File(nombreArchivo), productos);
            return true;
            }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void modificarStock (Producto producto, int cantidad) throws StockException {
        if (this.productos.contains(producto)){
            if (cantidad >= 0){
                producto.setStock(producto.getStock() + cantidad);
            }else{
                if(producto.getStock() >= (- cantidad)){
                    producto.setStock(producto.getStock() + cantidad);// se suma porque la cantidad es negativa

                } else {
                    throw new StockException("Stock existente menor a stock a eliminar.");
                }
            }
        } else {
            if (cantidad >= 0) {
                producto.setStock(cantidad);
                this.productos.add(producto);

            } else {
                throw new StockException("Producto inexistente, stock negativo.");
            }
        }
    }

}
