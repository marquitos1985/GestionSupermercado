import usuarios.empleados.administrador.GestorAdministrador;

public class Main {
    public static void main(String[] args) {
        GestorAdministrador gestorAdministrador = new GestorAdministrador();
        gestorAdministrador.crearUnAdministrador("Nicolas Blanco Rio", 42597483, "Beltrami 1560","2233060608",true,"nicolasuxmal@gmail.com","lapulga", 60000F);
        gestorAdministrador.guardarArchivoJsonAdministradores();
    }
}
