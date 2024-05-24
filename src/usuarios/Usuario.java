package usuarios;

public abstract class Usuario {

    private String nombreCompleto;

    private String dni;

    private String direccion;

    private String telefono;

    private Boolean activo;

    private String email;

    public Usuario(String nombreCompleto, String dni, String direccion, String telefono, Boolean activo, String email) {
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activo = activo;
        this.email = email;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                        "Nombre Completo: %s\n" +
                        "DNI: %s\n" +
                        "Dirección: %s\n" +
                        "Teléfono: %s\n" +
                                "Email: %s\n" +
                        "=====================\n",
                nombreCompleto, dni, direccion, telefono, email);
    }
}
