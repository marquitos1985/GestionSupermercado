package usuarios;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import usuarios.clientes.Cliente;
import usuarios.empleados.Empleado;
import usuarios.empleados.administrador.Administrador;
import usuarios.empleados.vendedor.Turno;
import usuarios.empleados.vendedor.Vendedor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GestorUsuario<T extends Usuario & Comparable<T>> {

    private List<Cliente> clientes;
    private List<Administrador> administradores;
    private List<Vendedor> vendedores;
    private ObjectMapper objectMapper;

    public GestorUsuario() {
        this.clientes = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.vendedores = new ArrayList<>();
        this.objectMapper = new ObjectMapper();
    }

    public boolean agregarUsuario(T usuarioNuevo) {
        if (usuarioNuevo instanceof Cliente) {
            return clientes.add((Cliente) usuarioNuevo);
        } else if (usuarioNuevo instanceof Administrador) {
            return administradores.add((Administrador) usuarioNuevo);
        } else if (usuarioNuevo instanceof Vendedor) {
            return vendedores.add((Vendedor) usuarioNuevo);
        } else {
            return false;
        }
    }

    public Cliente crearCliente(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email) {
        return new Cliente(nombreCompleto, dni, direccion, telefono, true, email);
    }

    public Vendedor crearVendedor(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email, String contraseña, Float sueldo, Turno turnoLaboral) {
        return new Vendedor(nombreCompleto, dni, direccion, telefono, true, email, contraseña, sueldo, turnoLaboral);
    }

    public Administrador crearAdministrador(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email, String contraseña, Float sueldo) {
        return new Administrador(nombreCompleto, dni, direccion, telefono, true, email, contraseña, sueldo);
    }

    public Usuario crearUsuario(String tipo, String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email, String contraseña, Float sueldo, Turno turnoLaboral) {
        if (dniExiste(dni)) {
            return null;
        }

        switch (tipo) {
            case "Cliente":
                return crearCliente(nombreCompleto, dni, direccion, telefono, true, email);
            case "Vendedor":
                return crearVendedor(nombreCompleto, dni, direccion, telefono, true, email, contraseña, sueldo, turnoLaboral);
            case "Administrador":
                return crearAdministrador(nombreCompleto, dni, direccion, telefono, true, email, contraseña, sueldo);
            default:
                return null;
        }
    }

    public boolean dniExiste(Integer dni) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equals(dni)) {
                return true;
            }
        }
        for (Administrador admin : administradores) {
            if (admin.getDni().equals(dni)) {
                return true;
            }
        }
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getDni().equals(dni)) {
                return true;
            }
        }
        return false;

    }

    public boolean modificarSueldoUsario(Integer dni, float sueldoNuevo) {
        T usuario = buscarUsuarioPorDni(dni);
        if (usuario != null && usuario instanceof Empleado) {
            Empleado empleado = (Empleado) usuario;
            if (empleado instanceof Administrador) {
                administradores.remove(usuario);
                empleado.setSueldo(sueldoNuevo);
                return administradores.add((Administrador) empleado);
            } else if (empleado instanceof Vendedor) {
                vendedores.remove(usuario);
                empleado.setSueldo(sueldoNuevo);
                return vendedores.add((Vendedor) empleado);
            }
        }
        return false;
    }

       public boolean crearUsuario(T usuarioNuevo) {
            if (usuarioNuevo instanceof Cliente) {
                for (Cliente cliente : clientes) {
                    if (cliente.getDni().equals(usuarioNuevo.getDni())) {
                        return false;
                   }
               }
               return clientes.add((Cliente) usuarioNuevo);
            } else if (usuarioNuevo instanceof Administrador) {
               for (Administrador admin : administradores) {
                   if (admin.getDni().equals(usuarioNuevo.getDni())) {
                        return false;
                   }
                }
               return administradores.add((Administrador) usuarioNuevo);
            } else if (usuarioNuevo instanceof Vendedor) {
               for (Vendedor vendedor : vendedores) {
                    if (vendedor.getDni().equals(usuarioNuevo.getDni())) {
                       return false;
                    }
                }
                return vendedores.add((Vendedor) usuarioNuevo);
            } else {
                return false;
           }
        }



    public T buscarUsuarioPorDni(Integer dni) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equals(dni)) {
                return (T) cliente;
            }
        }
        for (Administrador admin : administradores) {
            if (admin.getDni().equals(dni)) {
                return (T) admin;
            }
        }
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getDni().equals(dni)) {
                return (T) vendedor;
            }
        }
        return null;
    }

    public boolean modificarNombreUsuario(Integer dni, String nuevoNombreCompleto) {
        T usuario = buscarUsuarioPorDni(dni);
        if (usuario != null) {
            usuario.setNombreCompleto(nuevoNombreCompleto);
            return true;
        }
        return false;
    }

    public boolean modificarDireccionUsuario(Integer dni, String nuevaDireccion) {
        T usuario = buscarUsuarioPorDni(dni);
        if (usuario != null) {
            usuario.setDireccion(nuevaDireccion);
            return true;
        }
        return false;
    }

    public boolean modificarTelefonoUsuario(Integer dni, String nuevoTelefono) {
        T usuario = buscarUsuarioPorDni(dni);
        if (usuario != null) {
            usuario.setTelefono(nuevoTelefono);
            return true;
        }
        return false;
    }

    public boolean modificarEmailUsuario(Integer dni, String nuevoEmail) {
        T usuario = buscarUsuarioPorDni(dni);
        if (usuario != null) {
            usuario.setEmail(nuevoEmail);
            return true;
        }
        return false;
    }

    public boolean levantarArchivoJsonUsuarios(String nombreArchivo) {
        try {
            List<Object> usuariosGenericos = objectMapper.readValue(new File(nombreArchivo), new TypeReference<List<Object>>() {});
            for (Object usuarioGenerico : usuariosGenericos) {
                JsonNode nodo = objectMapper.valueToTree(usuarioGenerico);
                String type = nodo.get("type").asText();
                switch (type) {
                    case "cliente":
                        Cliente cliente = objectMapper.convertValue(usuarioGenerico, Cliente.class);
                        clientes.add(cliente);
                        break;
                    case "administrador":
                        Administrador administrador = objectMapper.convertValue(usuarioGenerico, Administrador.class);
                        administradores.add(administrador);
                        break;
                    case "vendedor":
                        Vendedor vendedor = objectMapper.convertValue(usuarioGenerico, Vendedor.class);
                        vendedores.add(vendedor);
                        break;
                    default:
                        System.out.println("Tipo de usuario no reconocido: " + type);
                        break;
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean guardarArchivoJsonUsuarios(String nombreArchivo) {
        try {
            List<Map<String, Object>> usuarios = new ArrayList<>();

            for (Cliente cliente : clientes) {
                Map<String, Object> clienteMap = objectMapper.convertValue(cliente, Map.class);
                clienteMap.put("type", "cliente");
                usuarios.add(clienteMap);
            }

            for (Administrador administrador : administradores) {
                Map<String, Object> administradorMap = objectMapper.convertValue(administrador, Map.class);
                administradorMap.put("type", "administrador");
                usuarios.add(administradorMap);
            }

            for (Vendedor vendedor : vendedores) {
                Map<String, Object> vendedorMap = objectMapper.convertValue(vendedor, Map.class);
                vendedorMap.put("type", "vendedor");
                usuarios.add(vendedorMap);
            }

            objectMapper.writeValue(new File(nombreArchivo), usuarios);
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
        } else {
            System.out.println("Usuario con DNI " + dni + " no encontrado.");
        }
    }
    public List<Cliente> listarClientes(){//Muestra los clientes activos
        List<Cliente> lista = new LinkedList<>();
        for (Cliente cliente: this.clientes){
            if(cliente.getActivo()){
                lista.add(cliente);
            }
        }
        return lista;
    }
    public List<Vendedor> listarVendeores(){//Muestra los vendedores activos

        List<Vendedor> lista = new LinkedList<>();
        for (Vendedor vendedor: this.vendedores){
            if(vendedor.getActivo()){
                lista.add(vendedor);
            }
        }
        return lista;

    }
    public List<Administrador> listarAdministradores(){//Muestra los administradores activos
        List<Administrador> lista = new LinkedList<>();
        for (Administrador administrador: this.administradores){
            if(administrador.getActivo()){
                lista.add(administrador);
            }
        }
        return lista;
    }


}
