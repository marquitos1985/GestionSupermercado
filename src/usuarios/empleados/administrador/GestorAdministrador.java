package usuarios.empleados.administrador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import usuarios.clientes.Cliente;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class GestorAdministrador {

    private TreeSet<Administrador> administradores;
    private ObjectMapper objectMapper;
    public GestorAdministrador() {
        this.administradores = new TreeSet<>();
    }

    public boolean agregarUnAdministrador(Administrador administradorNuevo) {
        return administradores.add(administradorNuevo);
    }

    public boolean crearUnAdministrador(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email, String contraseña, Float sueldo) {
        for (Administrador administrador : administradores) {
            if (administrador.getDni().equals(dni)) {
                return false;
            }
        }
        return agregarUnAdministrador(new Administrador(nombreCompleto, dni, direccion, telefono, activo, email, contraseña, sueldo));
    }

    public Administrador buscarAdministradorPorDni(Integer dni) {
        for (Administrador administrador : administradores) {
            if (administrador.getDni().equals(dni)) {
                return administrador;
            }
        }
        return null;
    }
    public boolean modificarNombreAdministrador(Integer dni, String nuevoNombreCompleto) {
        Administrador administrador = buscarAdministradorPorDni(dni);
        if (administrador != null) {
            administradores.remove(administrador);
            administrador.setNombreCompleto(nuevoNombreCompleto);
            return agregarUnAdministrador(administrador);
        }
        return false;
    }
    public boolean modificarDireccionAdministrador(Integer dni, String nuevaDireccion) {
        Administrador administrador = buscarAdministradorPorDni(dni);
        if (administrador != null) {
            administradores.remove(administrador);
            administrador.setDireccion(nuevaDireccion);
            return agregarUnAdministrador(administrador);
        }
        return false;
    }
    public boolean modificarTelefonoAdministrador(Integer dni, String nuevoTelefono) {
        Administrador administrador = buscarAdministradorPorDni(dni);
        if (administrador != null) {
            administradores.remove(administrador);
            administrador.setTelefono(nuevoTelefono);
            return agregarUnAdministrador(administrador);
        }
        return false;
    }
    public boolean modificarEmailAdministrador(Integer dni, String nuevoEmail) {
        Administrador administrador = buscarAdministradorPorDni(dni);
        if (administrador != null) {
            administradores.remove(administrador);
            administrador.setEmail(nuevoEmail);
            return agregarUnAdministrador(administrador);
        }
        return false;
    }
    public boolean modificarContraseñaAdministrador(Integer dni, String nuevaContraseña) {
        Administrador administrador = buscarAdministradorPorDni(dni);
        if (administrador != null) {
            administradores.remove(administrador);
            administrador.setContraseña(nuevaContraseña);
            return agregarUnAdministrador(administrador);
        }
        return false;
    }
    public boolean modificarSueldoAdministrador(Integer dni, Float nuevoSueldo) {
        Administrador administrador = buscarAdministradorPorDni(dni);
        if (administrador != null) {
            administradores.remove(administrador);
            administrador.setSueldo(nuevoSueldo);
            return agregarUnAdministrador(administrador);
        }
        return false;
    }
    public boolean levantarArchivoJsonAdministradores (){
        try {
            ObjectMapper mapeador= new ObjectMapper();
            ArrayList<Administrador> administrador = mapeador.readValue(new File("administrador.json"), new TypeReference<ArrayList<Administrador>>(){});
            this.administradores = new TreeSet<>(administrador);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean guardarArchivoJsonAdministradores(){
        try{
            ObjectMapper mapeador = new ObjectMapper();
            List<Administrador> administradorList = new ArrayList<>(administradores);
            mapeador.writeValue(new File("administrador.json"), administradorList);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
