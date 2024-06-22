package gui;

import usuarios.GestorUsuario;
import usuarios.clientes.Cliente;
import usuarios.empleados.administrador.Administrador;
import usuarios.empleados.vendedor.Turno;
import usuarios.empleados.vendedor.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionUsuariosGui {
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JCheckBox clienteCheckBox;
    private JCheckBox vendedorCheckBox;
    private JCheckBox administradorCheckBox;
    private JTextField dniTextField1;
    private JTextField nombreCompletoTextField2;
    private JTextField direccionTextField3;
    private JTextField textField4;
    private JTextField emailTextField5;
    private JTextField contraseñaTextField6;
    private JTextField sueldoTextField7;
    private JCheckBox socioCheckBox;
    private JComboBox turnoComboBox1;
    private JPanel gestionUsuariosJPanel;
    private JButton crearButton;

    private GestorUsuario<Cliente> gestorClientes;
    private GestorUsuario<Administrador> gestorAdministradores;
    private GestorUsuario<Vendedor> gestorVendedores;

    public GestionUsuariosGui(GestorUsuario<Cliente> gestorClientes, GestorUsuario<Administrador> gestorAdministradores, GestorUsuario<Vendedor> gestorVendedores) {
        this.gestorClientes = gestorClientes;
        this.gestorAdministradores = gestorAdministradores;
        this.gestorVendedores = gestorVendedores;

        JFrame frame = new JFrame("Gestor COTO");
        frame.setContentPane(this.gestionUsuariosJPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        acciones();



    }

    public void acciones(){

        for (Turno turno: Turno.values()){
            turnoComboBox1.addItem(turno);
        }
        inhabilitarCampos();



        /////////////////////////////    SOLAPA CREAR     /////////////////////////////////////


        clienteCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clienteCheckBox.isSelected()){
                    /*
                    vendedorCheckBox.setSelected(false);
                    administradorCheckBox.setSelected(false);
                    socioCheckBox.setEnabled(true);

                     */
                    habilitarCamposCliente();
                }

            }
        });

        administradorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(administradorCheckBox.isSelected()) {
                    /*
                    vendedorCheckBox.setSelected(false);
                    clienteCheckBox.setSelected(false);
                    contraseñaTextField6.setEnabled(true);
                    sueldoTextField7.setEnabled(true);
                    turnoComboBox1.setEnabled(true);

                     */
                    habilitarCamposAdministrador();
                }
            }
        });

        vendedorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vendedorCheckBox.isSelected()) {
                    /*
                    administradorCheckBox.setSelected(false);
                    clienteCheckBox.setSelected(false);
                    contraseñaTextField6.setEnabled(true);
                    sueldoTextField7.setEnabled(true);
                    turnoComboBox1.setEnabled(true);

                     */
                    habilitarCamposVendedor();
                }
            }
        });

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO HACEEEERRRRR
            }
        });








    }

    public void inhabilitarCampos(){
        contraseñaTextField6.setEnabled(false);
        sueldoTextField7.setEnabled(false);
        socioCheckBox.setEnabled(false);
        turnoComboBox1.setEnabled(false);
    }
    public void habilitarCamposCliente(){
        vendedorCheckBox.setSelected(false);
        administradorCheckBox.setSelected(false);

        contraseñaTextField6.setEnabled(false);
        sueldoTextField7.setEnabled(false);
        socioCheckBox.setEnabled(true);
        turnoComboBox1.setEnabled(false);
    }
    public void habilitarCamposVendedor(){
        administradorCheckBox.setSelected(false);
        clienteCheckBox.setSelected(false);

        contraseñaTextField6.setEnabled(true);
        sueldoTextField7.setEnabled(true);
        socioCheckBox.setEnabled(false);
        turnoComboBox1.setEnabled(true);
    }

    public void habilitarCamposAdministrador(){
        vendedorCheckBox.setSelected(false);
        clienteCheckBox.setSelected(false);

        contraseñaTextField6.setEnabled(true);
        sueldoTextField7.setEnabled(true);
        socioCheckBox.setEnabled(false);
        turnoComboBox1.setEnabled(true);
    }

}
