package usuarios.empleados.empleado;

import usuarios.Usuario;

public abstract class Empleado extends Usuario {

    private String contraseña;
    private Float sueldo;


    public Empleado(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email) {
        super(nombreCompleto, dni, direccion, telefono, activo, email);
    }
}
