package usuarios.empleados;

public class Administrador extends Empleado {


    public Administrador(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email) {
        super(nombreCompleto, dni, direccion, telefono, activo, email);
    }

    public String toString() {
        return String.format(
                "=====================\n" +
                        " Detalles del Administrador \n" +
                        "=====================\n" +
                        "%s" +  // Llama al toString de la clase Empleado
                        "=====================\n",
                super.toString()
                );
    }

}
