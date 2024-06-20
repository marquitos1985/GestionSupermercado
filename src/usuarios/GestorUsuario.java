package usuarios;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import usuarios.clientes.Cliente;
import usuarios.empleados.Empleado;
import usuarios.empleados.administrador.Administrador;
import usuarios.empleados.vendedor.Vendedor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    /*

    public boolean guardarArchivoJsonUsuarios(String nombreArchivo) {
        try {
            List<Object> usuarios = new ArrayList<>();
            usuarios.addAll(clientes);
            usuarios.addAll(administradores);
            usuarios.addAll(vendedores);
            objectMapper.writeValue(new File(nombreArchivo), usuarios);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

     */
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
}
