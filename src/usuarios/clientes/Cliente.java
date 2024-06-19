package usuarios.clientes;
import usuarios.Usuario;

public class Cliente extends Usuario implements Comparable<Cliente> {

    private Boolean socio;

    public Cliente () {

    }

    public Cliente(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email) {
        super(nombreCompleto, dni, direccion, telefono, activo, email);
        this.socio = true;
    }

    public Boolean getSocio() {
        return socio;
    }

    public void setSocio(Boolean socio) {
        this.socio = socio;
    }

    @Override
    public int compareTo(Cliente otroCliente) {
        if (otroCliente == null) {
            throw new NullPointerException("El cliente a comparar no puede ser nulo");
        }
        return this.getDni().compareTo(otroCliente.getDni());
    }

    @Override
    public String toString() {
        return String.format(
                "=====================\n" +
                        " Detalles del Cliente \n" +
                        "=====================\n" +
                        "Nombre Completo: %s\n" +
                        "DNI: %d\n" +
                        "Dirección: %s\n" +
                        "Teléfono: %s\n" +
                        "Activo: %b\n" +
                        "Email: %s\n" +
                        "Socio: %b\n" +
                        "=====================\n",
                getNombreCompleto(), getDni(), getDireccion(), getTelefono(), getActivo(), getEmail(), socio);
    }
}
