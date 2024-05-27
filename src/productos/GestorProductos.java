package productos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import usuarios.clientes.Cliente;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class GestorProductos {
    private TreeSet<Producto> productos;

    public GestorProductos() {
        this.productos = new TreeSet<>();
    }

    //todo--> CREAR METODO ALTA PRODUCTO, TENIENDO EN CUENTA QUE SE LO IDENTIFICA SEGUN SU DESCRIPCION / NOMBRE PARA VERIFICAR QUE NO EXISTA


    public boolean agregarProducto(Producto producto) { // retorna true si el producto es agregado / false si no.
        return this.productos.add(producto);
    }

    public boolean eliminarProducto (Producto producto) { // retorna true si el producto es eliminado / false si no.
        return this.productos.remove(producto);
    }

    //todo --> Verificar como se desea buscar el producto y hacer el metodo
     //si hay q realizar busqueda vamos a tener q cambiar la clase de productos

    /*
    public Producto buscarPorId(String id){//CON LINKEDLIST
        Producto producto = null;
        int i = 0;
        while (i < this.productos.size() && this.productos.get(i) == null){
            i++;
        }
        if(i < this.productos.size()){
            producto = this.productos.get(i);
        }
        //this.productos.stream().filter(p -> p.getIdProducto().equals(id)).findAny().orElse(null); esta opcion es más corta
        return producto;
    }

     */
    public Producto buscarPorId(String id){
        return this.productos.stream().filter(p -> p.getIdProducto().equals(id)).findAny().orElse(null);
    }

    public List<Producto> buscarPorTipo(TipoProducto tipo){//retorna los productos de un tipo especificado
        return this.productos.stream().filter(producto -> producto.getTipoProducto().equals(tipo)).collect(Collectors.toList());
    }


    //todo --> Verificar si es necesario el metodo de modificar Producto
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
    public void listarProductos(){
        this.productos.stream().forEach(producto -> System.out.println(producto));
    }

    public int buscarUltimoIntId(TipoProducto tipoProducto){// genera un nuevo id por tipo de producto a generar
        int salida = 100001;
        List<Producto> lista = this.productos.stream().filter(producto -> producto.getTipoProducto().equals(tipoProducto)).collect(Collectors.toList());
        if(lista.size() != 0){
        List<Integer> numerosIdProducto = new ArrayList<>();
        for (Producto producto: lista){
            numerosIdProducto.add(Integer.parseInt(producto.getIdProducto().replace(producto.getTipoProducto().toString(), "")));//quita el tipo de producto del ID y pasa a entero la parte del numero
        }
            salida = Collections.max(numerosIdProducto) + 1;
        }
        return salida;
    }





    public boolean levantarArchivoJsonProductos(){
        try {
            ObjectMapper mapeador= new ObjectMapper();
            ArrayList<Producto> productos = mapeador.readValue(new File("productos.json"), new TypeReference<ArrayList<Producto>>(){});
            this.productos = new TreeSet<>(productos);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean guardarArchivoJsonProductos(){
        try{
            ObjectMapper mapeador = new ObjectMapper();
            List<Producto> productoList = new ArrayList<>(productos);
            mapeador.writeValue(new File("productos.json"), productoList);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
