package usuarios.empleados.vendedor;

public enum Turno {
    MAÑANA("08:00 - 16:00"),
    TARDE("16:00 - 22:00");

    private final String horario;

    Turno(String horario) {
        this.horario = horario;
    }

    public String getHorario() {
        return horario;
    }
}
