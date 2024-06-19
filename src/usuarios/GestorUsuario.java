package usuarios;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import usuarios.Usuario;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class GestorUsuario<T extends Usuario & Comparable<T>> {
    private TreeSet<T> usuarios;
    private ObjectMapper objectMapper;

    public GestorUsuario() {
        this.usuarios = new TreeSet<>();
        this.objectMapper = new ObjectMapper();
    }

    public boolean agregarUsuario(T usuarioNuevo) {
        return usuarios.add(usuarioNuevo);
    }

    public boolean crearUsuario(T usuarioNuevo) {
        for (T usuario : usuarios) {
            if (usuario.getDni().equals(usuarioNuevo.getDni())) {
                return false;
            }
        }
        return agregarUsuario(usuarioNuevo);
    }

    public T buscarUsuarioPorDni(Integer dni) {
        for (T usuario : usuarios) {
            if (usuario.getDni().equals(dni)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean modificarNombreUsuario(Integer dni, String nuevoNombreCompleto) {
        T usuario = buscarUsuarioPorDni(dni);
        if (usuario != null) {
            usuarios.remove(usuario);
            usuario.setNombreCompleto(nuevoNombreCompleto);
            return agregarUsuario(usuario);
        }
        return false;
    }

    public boolean modificarDireccionUsuario(Integer dni, String nuevaDireccion) {
        T usuario = buscarUsuarioPorDni(dni);
        if (usuario != null) {
            usuarios.remove(usuario);
            usuario.setDireccion(nuevaDireccion);
            return agregarUsuario(usuario);
        }
        return false;
    }

    public boolean modificarTelefonoUsuario(Integer dni, String nuevoTelefono) {
        T usuario = buscarUsuarioPorDni(dni);
        if (usuario != null) {
            usuarios.remove(usuario);
            usuario.setTelefono(nuevoTelefono);
            return agregarUsuario(usuario);
        }
        return false;
    }

    public boolean modificarEmailUsuario(Integer dni, String nuevoEmail) {
        T usuario = buscarUsuarioPorDni(dni);
        if (usuario != null) {
            usuarios.remove(usuario);
            usuario.setEmail(nuevoEmail);
            return agregarUsuario(usuario);
        }
        return false;
    }

    public boolean levantarArchivoJsonUsuarios(String nombreArchivo) {
        try {
            List<T> usuariosList = objectMapper.readValue(new File(nombreArchivo), new TypeReference<List<T>>() {});
            this.usuarios = new TreeSet<>(usuariosList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean guardarArchivoJsonUsuarios(String nombreArchivo) {
        try {
            List<T> usuariosList = new ArrayList<>(usuarios);
            objectMapper.writeValue(new File(nombreArchivo), usuariosList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void muestraUsuarioPorDni(Integer dni) {
        T usuario = buscarUsuarioPorDni(dni);
        if (usuario != null) {
            System.out.println(usuario.toString());
        }
    }
}

