package usuarios.empleados.vendedor;

import usuarios.empleados.Empleado;

public class Vendedor extends Empleado implements Comparable<Vendedor> {

    private Turno turnoLaboral;

    public Vendedor () {

    }

    public Vendedor(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email, String contraseña, Float sueldo, Turno turnoLaboral) {
        super(nombreCompleto, dni, direccion, telefono, activo, email, contraseña, sueldo);
        this.turnoLaboral = turnoLaboral;
    }

    public Turno getTurnoLaboral() {
        return turnoLaboral;
    }

    public void setTurnoLaboral(Turno turnoLaboral) {
        this.turnoLaboral = turnoLaboral;
    }
/*
    @Override
    public String toString() {
        return String.format(
                "=====================\n" +
                        " Detalles del Vendedor \n" +
                        "=====================\n" +
                        "%s" +  // Llama al toString de la clase Empleado
                        "Turno Laboral: %s (%s)\n" +
                        "=====================\n",
                super.toString(),
                turnoLaboral.name(), turnoLaboral.getHorario());
    }

 */

    @Override
    public String toString() {
        return String.format("Vendedor: " + super.toString() + " Turno: %s (%s)", turnoLaboral.name(), turnoLaboral.getHorario());
    }


    @Override
    public int compareTo(Vendedor otroVendedor) {
        if (otroVendedor == null) {
            throw new NullPointerException("El vendedor a comparar no puede ser nulo");
        }
        return this.getDni().compareTo(otroVendedor.getDni());
    }
}


