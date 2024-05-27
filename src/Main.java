import productos.GestorProductos;
import productos.Producto;
import productos.TipoProducto;
import usuarios.empleados.administrador.GestorAdministrador;
import usuarios.empleados.vendedor.GestorVendedores;

public class Main {
    public static void main(String[] args) {
        GestorProductos gestorProductos = new GestorProductos();
        Producto fanta = new Producto("P10010", "Fanta", TipoProducto.BEBIDAS, 2500f, "2.25LTS", "12/12/24");
        Producto papasFritas = new Producto("P10020", "Lays", TipoProducto.SNACKS, 2800f, "120gr", "12/12/24" );
        gestorProductos.agregarProducto(fanta);
        gestorProductos.agregarProducto(papasFritas);
        gestorProductos.listarProductos();
        gestorProductos.guardarArchivoJsonProductos();
    }
}
