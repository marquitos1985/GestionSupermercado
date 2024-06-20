package gui;

import usuarios.GestorUsuario;
import usuarios.Usuario;
import usuarios.clientes.Cliente;
import usuarios.empleados.vendedor.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JComboBox productosComboBox;
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
    private JScrollPane carritoScrollPane;
    private JLabel subtotalLabel;


    public GestorSupermercado() {
        JFrame frame = new JFrame("Gestor COTO");
        frame.setContentPane(this.principalTabbedPane);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);



    }

    public void iniciarSistema(){
        GestorUsuario <Cliente> gestorClientes = new GestorUsuario<>();
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //todo los usuarios tienen el dni como nro entero
                Usuario usuario = gestorClientes.buscarUsuarioPorDni(Integer.valueOf(dniClienteTextField.getText()));
                if(usuario != null && usuario instanceof Vendedor){
                    //todo hacer
                }else {

                }
            }
        });
    }

    public static void main(String[] args) {
        GestorSupermercado gestor = new GestorSupermercado();
        gestor.iniciarSistema();

    }
}
