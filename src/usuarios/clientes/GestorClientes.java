package usuarios.clientes;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

public class GestorClientes {

    private TreeSet<Cliente> clientes;
    private ObjectMapper objectMapper;

    public GestorClientes() {
        this.clientes = new TreeSet<>();
    }

    public boolean agregarUnCliente(Cliente clienteNuevo) {
        return clientes.add(clienteNuevo);
    }

    public boolean crearUnCliente(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni() == dni) {
                return false;
            }
        }
        return agregarUnCliente(new Cliente(nombreCompleto, dni, direccion, telefono, activo, email));
    }

    public Cliente buscarClientePorDni(Integer dni) {
        for (Cliente cliente : clientes) {
            if (cliente.getDni() == dni) {
                return cliente;
            }
        }
        return null;
    }

    public boolean modificarNombreCliente(Integer dni, String nuevoNombreCompleto) {
        Cliente cliente = buscarClientePorDni(dni);
        if (cliente != null) {
            clientes.remove(cliente);
            cliente.setNombreCompleto(nuevoNombreCompleto);
            return agregarUnCliente(cliente);
        }
        return false;
    }
    public boolean modificarDireccionCliente(Integer dni, String nuevaDireccion) {
        Cliente cliente = buscarClientePorDni(dni);
        if (cliente != null) {
            clientes.remove(cliente);
            cliente.setDireccion(nuevaDireccion);
            return agregarUnCliente(cliente);
        }
        return false;
    }
    public boolean modificarTelefonoCliente(Integer dni, String nuevoTelefono) {
        Cliente cliente = buscarClientePorDni(dni);
        if (cliente != null) {
            clientes.remove(cliente);
            cliente.setTelefono(nuevoTelefono);
            return agregarUnCliente(cliente);
        }
        return false;
    }
    public boolean modificarEmailCliente(Integer dni, String nuevoEmail) {
        Cliente cliente = buscarClientePorDni(dni);
        if (cliente != null) {
            clientes.remove(cliente);
            cliente.setEmail(nuevoEmail);
            return agregarUnCliente(cliente);
        }
        return false;
    }
    public boolean levantarArchivoJson (){
        try {
            ObjectMapper mapeador= new ObjectMapper();
            ArrayList<Cliente> clientes = mapeador.readValue(new File("cliente.json"), new TypeReference<ArrayList<Cliente>>(){});
        this.clientes = new TreeSet<>(clientes);
        return true;
        } catch (Exception e) {
        e.printStackTrace();
        return false;
        }
    }


    public void muestraClientes (){
        if(levantarArchivoJson()){
            for(Cliente cliente : clientes){
                System.out.println(cliente.toString());
            }
        }else {
            System.out.println("No funciona we");
        }
    }
}