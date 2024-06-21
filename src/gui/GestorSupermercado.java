package gui;

import caja.Caja;
import productos.GestorProductos;
import productos.Producto;
import productos.TipoProducto;
import usuarios.GestorUsuario;
import usuarios.clientes.Cliente;
import usuarios.empleados.administrador.Administrador;
import usuarios.empleados.vendedor.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
    private JComboBox productosJComboBox;
    private JLabel iniciarSesionLabel;
    private JLabel usuarioLabel;
    private JLabel contraseniaLabel;
    private JLabel vendedorLabel;
    private JLabel vendedorActivoLabel;
    private JLabel seleccionClienteLabel;
    private JLabel dniClienteLabel;
    private JLabel clienteLabel;
    private JLabel clienteEncontradoLabel;
    private JLabel productosLabel;
    private JLabel ventaLabel;
    private JLabel carritoLabel;
    private JScrollPane productosScrollPane;
    private JLabel subtotalLabel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton ingresarButton2;
    private JButton ingresarButton1;
    private JButton ingreasrButton;
    private JPanel iniciarSesionJPanel;
    private JPanel seleccionClienteJPanel;
    private JPanel ventaJPanel;
    private JList productosJList;
    private JList carritoJList;
    private GestorUsuario <Vendedor> gestorVendedores;
    private GestorUsuario <Administrador> gestorAdministradores;
    private GestorUsuario <Cliente> gestorClientes;
    private GestorProductos gestorProductos;


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



    }

    public void iniciarSistema(){
        Caja caja = new Caja();
        levantarJson();
        inhabilitarBotones();

        for (TipoProducto tipo: TipoProducto.values()){
            productosJComboBox.addItem(tipo);
        }


        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Vendedor vendedor = gestorVendedores.buscarUsuarioPorDni(Integer.valueOf(usuarioTextField.getText()));
                if(vendedor != null){
                    if(vendedor.getContraseña().equals(new String(passwordField.getPassword()))){
                        JOptionPane.showMessageDialog(null, "Ingreso exitoso....");
                        vendedorActivoLabel.setText(("DNI: " + vendedor.getDni() + " - " + vendedor.getNombreCompleto()).toUpperCase());
                        caja.setVendedor(vendedor);
                        buscarButton.setEnabled(true);
                    }else {
                        JOptionPane.showMessageDialog(null, "Contraseña inválida...");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "El usuario no es vendedor o no existe...");
                }
            }
        });


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = gestorClientes.buscarUsuarioPorDni(Integer.valueOf(dniClienteTextField.getText()));

                if(cliente != null){
                    JOptionPane.showMessageDialog(null, "Cliente encontrado...");
                    clienteEncontradoLabel.setText(("DNI: " + cliente.getDni() + " - " + cliente.getNombreCompleto()).toUpperCase());
                    caja.setCliente(cliente);
                    agregarButton.setEnabled(true);
                    eliminarButton.setEnabled(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Cliente inexistente...");
                }


            }
        });


        productosJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List <Producto> lista = gestorProductos.buscarPorTipo((TipoProducto) productosJComboBox.getSelectedItem());
                productosScrollPane.createVerticalScrollBar();


            }
        });


    }

    private void inhabilitarBotones(){
        buscarButton.setEnabled(false);
        agregarButton.setEnabled(false);
        eliminarButton.setEnabled(false);
        generarFacturaButton.setEnabled(false);
    }
    private void levantarJson(){
        this.gestorVendedores.levantarArchivoJsonUsuarios("usuarios.json");
        this.gestorAdministradores.levantarArchivoJsonUsuarios("usuarios.json");
        this.gestorClientes.levantarArchivoJsonUsuarios("usuarios.json");
        this.gestorProductos.levantarArchivoJsonProductos("productos.json");
    }

    public static void main(String[] args) {
        GestorSupermercado gestor = new GestorSupermercado();
        gestor.iniciarSistema();

    }
}
