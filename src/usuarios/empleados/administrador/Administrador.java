package usuarios.empleados.administrador;

import usuarios.clientes.Cliente;
import usuarios.empleados.Empleado;

public class Administrador extends Empleado implements Comparable<Administrador>{

    public Administrador(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email, String contraseña, Float sueldo) {
        super(nombreCompleto, dni, direccion, telefono, activo, email, contraseña, sueldo);
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

    @Override
    public int compareTo(Administrador otroAdministrador) {
        if (otroAdministrador == null ) {
            throw new NullPointerException("El administrador a comparar no puede ser nulo");
        }
           return this.getDni().compareTo(otroAdministrador.getDni());
    }
}


