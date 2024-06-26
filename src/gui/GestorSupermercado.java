package gui;
import caja.Caja;
import productos.GestorProductos;
import productos.Producto;
import productos.StockException;
import productos.TipoProducto;
import usuarios.GestorUsuario;
import usuarios.Usuario;
import usuarios.clientes.Cliente;
import usuarios.empleados.administrador.Administrador;
import usuarios.empleados.vendedor.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;


public class GestorSupermercado {
    private JTabbedPane principalTabbedPane;
    private JPanel interfazUsuario;
    private JPanel administrdorJPanel;
    private JPanel vendedorJPanel;
    private JTextField usuarioTextField;
    private JPasswordField passwordField;
    private JButton ingresarButton;
    private JTextField dniClienteTextField;
    private JButton buscarButton;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JButton generarFacturaButton;
    private JComboBox tipoProductosJComboBox;
    private JLabel iniciarSesionLabel;
    private JLabel usuarioLabel;
    private JLabel contraseniaLabel;
    private JLabel vendedorLabel;
    private JLabel vendedorActivoLabel;
    private JLabel dniClienteLabel;
    private JLabel clienteLabel;
    private JLabel clienteEncontradoLabel;
    private JLabel productosLabel;
    private JLabel subtotalLabel;
    private JTextField admdniTextField1;
    private JButton ingresarButton2;
    private JButton ingresarButton1;
    private JButton ingresarButton3;
    private JPanel iniciarSesionJPanel;
    private JPanel seleccionClienteJPanel;
    private JPanel ventaJPanel;
    private JList productosJList;
    private JList carritoJList;
    private JLabel subtotalActualizadoLabel;
    private JList cantidadJList;
    private JPasswordField passwordField1;
    private JLabel administradorActivoLabel;
    private final String  archivoUsuarios = "usuarios.json";
    private final String  archivoProductos = "productos.json";
    private GestorUsuario <Vendedor> gestorVendedores;
    private GestorUsuario <Administrador> gestorAdministradores;
    private GestorUsuario <Cliente> gestorClientes;
    private GestorProductos gestorProductos;
    private List <Producto> listaProductosFiltrada;


    public GestorSupermercado() {
        JFrame frame = new JFrame("Gestor COTO");
        frame.setContentPane(this.principalTabbedPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        this.gestorVendedores = new GestorUsuario<>();
        this.gestorAdministradores = new GestorUsuario<>();
        this.gestorClientes = new GestorUsuario<>();
        this.gestorProductos = new GestorProductos();
        levantarJson();
    }


    public static void main(String[] args) {
        GestorSupermercado gestor = new GestorSupermercado();
        gestor.iniciarSistema();

    }



    public void iniciarSistema(){
        Caja caja = new Caja();
        caja.setGestorProductos(this.gestorProductos);
        inhailitarCampos();
        inhabilitarBotones();

        for (TipoProducto tipo: TipoProducto.values()){
            tipoProductosJComboBox.addItem(tipo);
        }

        this.listaProductosFiltrada = gestorProductos.buscarPorTipo((TipoProducto) tipoProductosJComboBox.getSelectedItem());

        DefaultListModel modelProductos = new DefaultListModel<>();

        //////////////////////////////////////////    SOLAPA VENDEDOR     //////////////////////////////////////////
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if(verificarCampoNumerico(usuarioTextField.getText())){
                    Usuario usuario = gestorVendedores.buscarUsuarioPorDni(Integer.valueOf(usuarioTextField.getText()));

                    if(usuario != null && usuario instanceof Vendedor){
                        Vendedor vendedor = (Vendedor) usuario;
                        if(vendedor.getContraseña().equals(new String(passwordField.getPassword()))){
                            JOptionPane.showMessageDialog(null, "Ingreso exitoso....");
                            vendedorActivoLabel.setText(("DNI: " + vendedor.getDni() + " - " + vendedor.getNombreCompleto()).toUpperCase());
                            caja.setVendedor(vendedor);
                            buscarButton.setEnabled(true);
                        }else {
                            JOptionPane.showMessageDialog(null, "Contraseña inválida...");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Vendedor inexistente...");
                    }
                }
            }
        });



        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(verificarCampoNumerico(dniClienteTextField.getText())) {
                    Usuario usuario = gestorClientes.buscarUsuarioPorDni(Integer.valueOf(dniClienteTextField.getText()));

                    if (usuario != null && usuario instanceof Cliente) {
                        Cliente cliente = (Cliente) usuario;
                        JOptionPane.showMessageDialog(null, "Cliente encontrado...");
                        clienteEncontradoLabel.setText(("DNI: " + cliente.getDni() + " - " + cliente.getNombreCompleto()).toUpperCase());
                        caja.setCliente(cliente);

                        setEnabledProductosYcarrito();

                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente inexistente...");
                    }
                }
            }
        });

        tipoProductosJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoProducto tipoProductoSeleccionado = (TipoProducto) tipoProductosJComboBox.getSelectedItem();

                listaProductosFiltrada = gestorProductos.buscarPorTipo(tipoProductoSeleccionado);
                for (Producto prod: listaProductosFiltrada) {
                    modelProductos.addElement(prod);
                }
                productosJList = actualizarJList(productosJList, listaProductosFiltrada);
            }
        });

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto prod = (Producto) productosJList.getSelectedValue();
                if(prod != null){
                    try {
                        caja.agregarProducto(prod);

                        carritoJList = actualizarJList(carritoJList, caja.getCarrito().getProductos().keySet().stream().toList());
                        cantidadJList = actualizarJList(cantidadJList, caja.getCarrito().getProductos().values().stream().toList());

                        TipoProducto tipoProductoSeleccionado = (TipoProducto) tipoProductosJComboBox.getSelectedItem();
                        listaProductosFiltrada = gestorProductos.buscarPorTipo(tipoProductoSeleccionado);
                        productosJList = actualizarJList(productosJList, listaProductosFiltrada);
                        subtotalActualizadoLabel.setText(String.valueOf(caja.getSubtotal()));
                    } catch (StockException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "No seleccinó producto para a agregar al carrito...");
                }

            }
        });


        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto prod = (Producto) carritoJList.getSelectedValue();
                if(prod != null){
                    try {
                        caja.eliminarProducto(prod);
                        carritoJList = actualizarJList(carritoJList, caja.getCarrito().getProductos().keySet().stream().toList());
                        cantidadJList = actualizarJList(cantidadJList, caja.getCarrito().getProductos().values().stream().toList());
                        TipoProducto tipoProductoSeleccionado = (TipoProducto) tipoProductosJComboBox.getSelectedItem();
                        listaProductosFiltrada = gestorProductos.buscarPorTipo(tipoProductoSeleccionado);
                        productosJList = actualizarJList(productosJList, listaProductosFiltrada);
                        subtotalActualizadoLabel.setText(String.valueOf(caja.getSubtotal()));

                    } catch (StockException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "No seleccinó producto para eliminar del carrito...");
                }

            }
        });
        generarFacturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!caja.getCarrito().isVacio()){
                    caja.finalizarCompra();
                }else {
                    JOptionPane.showMessageDialog(null, "Carrito vacío...");
                }

        }});
    //////////////////////////////////////////    SOLAPA ADMINISTRADOR     //////////////////////////////////////////

        ingresarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(verificarCampoNumerico(admdniTextField1.getText())){
                    Usuario usuario = gestorAdministradores.buscarUsuarioPorDni(Integer.valueOf(admdniTextField1.getText()));
                    //if(administrador != null){
                    if(usuario != null && usuario instanceof Administrador){
                        Administrador administrador = (Administrador) usuario;
                        if(administrador.getContraseña().equals(new String(passwordField1.getPassword()))){
                            JOptionPane.showMessageDialog(null, "Ingreso exitoso....");
                            administradorActivoLabel.setText(("DNI: " + administrador.getDni() + " - " + administrador.getNombreCompleto()).toUpperCase());
                            ingresarButton1.setEnabled(true);
                            ingresarButton3.setEnabled(true);
                        }else {
                            JOptionPane.showMessageDialog(null, "Contraseña inválida...");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "El administrador no existe...");
                    }
                }

            }
        });




        ingresarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GestionProductosGui gestionProductosGui = new GestionProductosGui(gestorProductos, archivoProductos);

            }
        });

        ingresarButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionUsuariosGui gestionUsuariosGui = new GestionUsuariosGui(gestorClientes, gestorAdministradores, gestorVendedores, archivoUsuarios);
            }
        });


    }


    private void inhabilitarBotones(){
        buscarButton.setEnabled(false);
        agregarButton.setEnabled(false);
        eliminarButton.setEnabled(false);
        generarFacturaButton.setEnabled(false);
        ingresarButton1.setEnabled(false);
        ingresarButton3.setEnabled(false);
    }

    private void inhailitarCampos(){
        productosJList.setEnabled(false);
        carritoJList.setEnabled(false);
        tipoProductosJComboBox.setEnabled(false);
    }
    private void levantarJson(){
        this.gestorVendedores.levantarArchivoJsonUsuarios(archivoUsuarios);
        this.gestorAdministradores.levantarArchivoJsonUsuarios(archivoUsuarios);
        this.gestorClientes.levantarArchivoJsonUsuarios(archivoUsuarios);
        this.gestorProductos.levantarArchivoJsonProductos(archivoProductos);
    }

    public <T> JList actualizarJList(JList jList, List<T> lista){
        DefaultListModel<T> model = new DefaultListModel<>();
        for (T elemento: lista) {
            model.addElement(elemento);
        }
        jList.setModel(model);

        return  jList;
    }


    private boolean verificarCampoVacio(String cadena){//true si el la cadena está vacía
        return (cadena.isBlank() && cadena.isEmpty());
    }
    private boolean verificarCampoNumerico(String cadena){//true si la cadena contiene solo numeros
        return cadena.matches("[0-9]+");
    }

    private boolean verificarCampoDni(String cadena){//true si la cadena no está vacía y contiene sólo números
        return !verificarCampoVacio(cadena) && verificarCampoNumerico(cadena);
    }

    private void setEnabledProductosYcarrito(){
        tipoProductosJComboBox.setEnabled(true);
        productosJList.setEnabled(true);
        carritoJList.setEnabled(true);
        agregarButton.setEnabled(true);
        eliminarButton.setEnabled(true);
        generarFacturaButton.setEnabled(true);
    }



}
