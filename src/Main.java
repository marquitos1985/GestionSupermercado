import productos.GestorProductos;
import productos.Producto;
import productos.TipoProducto;
import usuarios.empleados.administrador.GestorAdministrador;
import usuarios.empleados.vendedor.GestorVendedores;

public class Main {
    public static void main(String[] args) {
        GestorAdministrador gestorAdministrador = new GestorAdministrador();
        gestorAdministrador.levantarArchivoJsonAdministradores();
        gestorAdministrador.modificarSueldoAdministrador(42597483,80000F);
        gestorAdministrador.guardarArchivoJsonAdministradores();


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        GestorProductos gestorProductos = new GestorProductos();
        Producto fanta = new Producto("BEBIDAS10010", "Fanta", TipoProducto.BEBIDAS, 2500f, "2.25LTS", "12/12/24");
        Producto papasFritas = new Producto("SNACKS10020", "Lays", TipoProducto.SNACKS, 2800f, "120gr", "12/12/24" );
        gestorProductos.agregarProducto(fanta);
        gestorProductos.agregarProducto(papasFritas);
        gestorProductos.listarProductos();
        //gestorProductos.guardarArchivoJsonProductos();









    }
}
