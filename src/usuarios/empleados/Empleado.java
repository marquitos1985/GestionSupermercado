package usuarios.empleados;

import usuarios.Usuario;

public abstract class Empleado extends Usuario {

    private String contraseña;
    private Float sueldo;


    public Empleado(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email, String contraseña, Float sueldo) {
        super(nombreCompleto, dni, direccion, telefono, activo, email);
        this.contraseña = contraseña;
        this.sueldo = sueldo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Float getSueldo() {
        return sueldo;
    }

    public void setSueldo(Float sueldo) {
        this.sueldo = sueldo;
    }
}
