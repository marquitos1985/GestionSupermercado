package usuarios.clientes;


import usuarios.Usuario;

public class Cliente extends Usuario {

    private Boolean socio;


    public Cliente(String nombreCompleto, String dni, String direccion, String telefono, Boolean socio, Boolean activo, String email) {
        super(nombreCompleto, dni, direccion, telefono, activo, email);
        this.socio = socio;
    }
}


