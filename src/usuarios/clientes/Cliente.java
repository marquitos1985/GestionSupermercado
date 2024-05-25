package usuarios.clientes;


import usuarios.Usuario;

public class Cliente extends Usuario implements Comparable{

    private Boolean socio;

    public Cliente(){

    }
    public Cliente(String nombreCompleto,Integer dni, String direccion, String telefono, Boolean activo, String email) {
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
    public int compareTo(Object o) {
        if (o instanceof Cliente) {
            Cliente otroCliente = (Cliente) o;
            return this.getDni().compareTo(otroCliente.getDni());
        }
        return 0;
    }
}


