import usuarios.empleados.administrador.GestorAdministrador;
import usuarios.empleados.vendedor.GestorVendedores;

public class Main {
    public static void main(String[] args) {
        GestorVendedores gestorVendedores = new GestorVendedores();
        gestorVendedores.crearUnVendedor("Nicolas Blanco Rio", 42597483, "Beltrami 1560","2233060608",true,"nicolasuxmal@gmail.com","lapulga", 60000F);
        gestorVendedores.crearUnVendedor("Lucas Riggillo", 44692854, "aguado 1601","2235403203",true,"lucasriggillo@gmail.com","hola", 60000F);
        gestorVendedores.modificarDireccionVendedor(44692854,"tripulantes 2802");
        gestorVendedores.guardarArchivoJsonVendedores();
    }
}
