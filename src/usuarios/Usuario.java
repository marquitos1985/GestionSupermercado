package usuarios;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import usuarios.clientes.Cliente;
import usuarios.empleados.administrador.Administrador;
import usuarios.empleados.vendedor.Vendedor;

@JsonTypeInfo(use = Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @Type(value = Cliente.class, name = "cliente"),
        @Type(value = Administrador.class, name = "administrador"),
        @Type(value = Vendedor.class, name = "vendedor")
})

public abstract class Usuario {

    private String nombreCompleto;

    private Integer dni;

    private String direccion;

    private String telefono;

    private Boolean activo;

    private String email;




    public Usuario(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email) {
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activo = activo;
        this.email = email;
    }

    protected Usuario() {
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
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
