package usuarios.empleados;

public class Vendedor extends Empleado {

    private Turno turnoLaboral;

    public Vendedor(String nombreCompleto, String dni, String direccion, String telefono, Boolean activo, String email) {
        super(nombreCompleto, dni, direccion, telefono, activo, email);
    }


    public Turno getTurnoLaboral() {
        return turnoLaboral;
    }

    public void setTurnoLaboral(Turno turnoLaboral) {
        this.turnoLaboral = turnoLaboral;
    }

    public String toString() {
        return String.format(
                "=====================\n" +
                        " Detalles del Vendedor \n" +
                        "=====================\n" +
                        "%s" +  // Llama al toString de la clase Usuario
                        "Turno Laboral: %s (%s)\n" +
                        "=====================\n",
                super.toString(),
                turnoLaboral.name(), turnoLaboral.getHorario());
    }
}
