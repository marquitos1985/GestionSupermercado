package usuarios.empleados.administrador;

import usuarios.empleados.Empleado;

public class Administrador extends Empleado implements Comparable<Administrador>{

    public Administrador ( ){

    }

    public Administrador(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email, String contraseña, Float sueldo) {
        super(nombreCompleto, dni, direccion, telefono, activo, email, contraseña, sueldo);
    }

    public String toString() {
        return String.format("Administrador: " + super.toString());
    }

    @Override
    public int compareTo(Administrador o) {
        return this.getDni().compareTo(o.getDni());
    }
}


