package usuarios.clientes;


import usuarios.Usuario;

public class Cliente extends Usuario implements Comparable{

    private Boolean socio;


    public Cliente(String nombreCompleto,Integer dni, String direccion, String telefono, Boolean socio, Boolean activo, String email) {
        super(nombreCompleto, dni, direccion, telefono, activo, email);
        this.socio = socio;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}


