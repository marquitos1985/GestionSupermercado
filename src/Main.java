import usuarios.empleados.administrador.GestorAdministrador;
import usuarios.empleados.vendedor.GestorVendedores;

public class Main {
    public static void main(String[] args) {
        GestorAdministrador gestorAdministrador = new GestorAdministrador();
        gestorAdministrador.levantarArchivoJsonAdministradores();
        gestorAdministrador.modificarSueldoAdministrador(42597483,80000F);
        gestorAdministrador.guardarArchivoJsonAdministradores();
    }
}
