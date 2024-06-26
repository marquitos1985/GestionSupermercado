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
        String socioStr;
        if (this.socio) {
            socioStr = "activo";
        } else {
            socioStr = "inactivo";
        }
        return String.format("Cliente: " + super.toString() + "Socio: %s",socioStr);
    };
}



