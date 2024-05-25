package usuarios.empleados.vendedor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import usuarios.clientes.Cliente;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class GestorVendedores {
    private TreeSet<Vendedor> vendedores;
    private ObjectMapper objectMapper;

    public GestorVendedores() {
        this.vendedores = new TreeSet<>();
    }

    public boolean agregarUnVendedor(Vendedor vendedorNuevo) {
        return vendedores.add(vendedorNuevo);
    }

    public boolean crearUnVendedor(String nombreCompleto, Integer dni, String direccion, String telefono, Boolean activo, String email, String contraseña, Float sueldo) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getDni().equals(dni)) {
                return false;
            }
        }
        return agregarUnVendedor(new Vendedor(nombreCompleto, dni, direccion, telefono, activo, email, contraseña, sueldo));
    }

    public Vendedor buscarVendedorPorDni(Integer dni) {
        for (Vendedor vendedor: vendedores) {
            if (vendedor.getDni().equals(dni)) {
                return vendedor;
            }
        }
        return null;
    }

    public boolean modificarNombreVendedor(Integer dni, String nuevoNombreCompleto) {
        Vendedor vendedor = buscarVendedorPorDni(dni);
        if (vendedor != null) {
            vendedores.remove(vendedor);
            vendedor.setNombreCompleto(nuevoNombreCompleto);
            return agregarUnVendedor(vendedor);
        }
        return false;
    }
    public boolean modificarDireccionVendedor(Integer dni, String nuevaDireccion) {
        Vendedor vendedor = buscarVendedorPorDni(dni);
        if (vendedor != null) {
            vendedores.remove(vendedor);
            vendedor.setDireccion(nuevaDireccion);
            return agregarUnVendedor(vendedor);
        }
        return false;
    }
    public boolean modificarTelefonoVendedor(Integer dni, String nuevoTelefono) {
      Vendedor vendedor = buscarVendedorPorDni(dni);
        if (vendedor != null) {
            vendedores.remove(vendedor);
            vendedor.setTelefono(nuevoTelefono);
            return agregarUnVendedor(vendedor);
        }
        return false;
    }
    public boolean modificarEmailVendedor(Integer dni, String nuevoEmail) {
       Vendedor vendedor = buscarVendedorPorDni(dni);
        if (vendedor != null) {
            vendedores.remove(vendedor);
            vendedor.setEmail(nuevoEmail);
            return agregarUnVendedor(vendedor);
        }
        return false;
    }
    public boolean levantarArchivoJsonVendedores (){
        try {
            ObjectMapper mapeador= new ObjectMapper();
            ArrayList<Vendedor> clientes = mapeador.readValue(new File("vendedores.json"), new TypeReference<ArrayList<Vendedor>>(){});
            this.vendedores = new TreeSet<>(vendedores);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean guardarArchivoJsonVendedores(){
        try{
            ObjectMapper mapeador = new ObjectMapper();
            List<Vendedor> clienteList = new ArrayList<>(vendedores);
            mapeador.writeValue(new File("vendedores.json"), clienteList);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void muestraUnClientePorDni(Integer dni){
        Vendedor vendedor = buscarVendedorPorDni(dni);
        if(vendedor !=null){
            System.out.println(vendedor.toString());
        }
    }
}


