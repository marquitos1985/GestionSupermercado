package gui;

import usuarios.GestorUsuario;
import usuarios.Usuario;
import usuarios.clientes.Cliente;
import usuarios.empleados.Empleado;
import usuarios.empleados.administrador.Administrador;
import usuarios.empleados.vendedor.Turno;
import usuarios.empleados.vendedor.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GestionUsuariosGui {
    private JTabbedPane tabbedPane1;
    private JCheckBox clienteCheckBox;
    private JCheckBox vendedorCheckBox;
    private JCheckBox administradorCheckBox;
    private JTextField dniTextField1;
    private JTextField nombreCompletoTextField2;
    private JTextField direccionTextField3;
    private JTextField telefonoTextField4;
    private JTextField emailTextField5;
    private JTextField contraseñaTextField6;
    private JTextField sueldoTextField7;
    private JCheckBox socioCheckBox;
    private JComboBox turnoComboBox1;
    private JPanel gestionUsuariosJPanel;
    private JButton crearButton;
    private JComboBox usuariosPorTipoComboBox1;
    private JList usuariosPorTipoList1;
    private JList todoslosUsuariosList2;
    private JButton verButton;
    private JCheckBox clienteCheckBox1;
    private JCheckBox vendedorCheckBox1;
    private JCheckBox administradorCheckBox1;
    private JTextField nombreCompletoTextField22;
    private JTextField direccionTextField33;
    private JTextField telefonoTextField44;
    private JTextField emailTextField55;
    private JCheckBox socioCheckBox1;
    private JTextField contraseniaTextField66;
    private JTextField sueldoTextField77;
    private JComboBox turnoComboBox11;
    private JButton modificarButton;
    private JTextField ingresoDniTextField1;
    private JButton buscarButton;
    private JLabel usuarioModificarJLabel;
    private JTextField buscarDniTextField1;
    private JButton buscarButton1;
    private JButton eliminarButton;
    private JLabel usuarioAeliminarJLabel;
    private JCheckBox activarCheckBox;
    private JScrollPane usuariosPorTipoScrollPane;
    private JScrollPane todosLosUsuariosScrollPane;
    private final String archivoUsuarios;

    private GestorUsuario<Cliente> gestorClientes;
    private GestorUsuario<Administrador> gestorAdministradores;
    private GestorUsuario<Vendedor> gestorVendedores;
    private Usuario usuarioEncontrado;

    public GestionUsuariosGui(GestorUsuario<Cliente> gestorClientes, GestorUsuario<Administrador> gestorAdministradores, GestorUsuario<Vendedor> gestorVendedores, String archivoUsuarios) {
        this.gestorClientes = gestorClientes;
        this.gestorAdministradores = gestorAdministradores;
        this.gestorVendedores = gestorVendedores;
        this.archivoUsuarios = archivoUsuarios;
        this.usuarioEncontrado = null;

        JFrame frame = new JFrame("Gestor COTO");
        frame.setContentPane(this.gestionUsuariosJPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        acciones();



    }

    public void acciones(){
        inhabilitarCampos();
        for (Turno turno: Turno.values()){
            turnoComboBox1.addItem(turno);
            turnoComboBox11.addItem(turno);
        }

        turnoComboBox1.setSelectedItem(Turno.MAÑANA);
        clienteCheckBox.setSelected(true);

        usuariosPorTipoComboBox1.addItem("Cliente");
        usuariosPorTipoComboBox1.addItem("Vendedor");
        usuariosPorTipoComboBox1.addItem("Administrador");



        /////////////////////////////    SOLAPA CREAR     /////////////////////////////////////


        clienteCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clienteCheckBox.isSelected()){
                    habilitarCamposCliente();
                }

            }
        });

        administradorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(administradorCheckBox.isSelected()) {
                    habilitarCamposAdministrador();
                }
            }
        });

        vendedorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vendedorCheckBox.isSelected()) {
                    habilitarCamposVendedor();
                }
            }
        });

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = null;
                Vendedor vendedor = null;
                Administrador administrador = null;


                if(clienteCheckBox.isSelected()){
                    if(verificarCamposUsuario()){
                        if(verificarCampoNumerico(dniTextField1.getText())) {
                            if (!gestorClientes.dniExiste(Integer.valueOf(dniTextField1.getText())) &&
                                    !gestorVendedores.dniExiste(Integer.valueOf(dniTextField1.getText())) &&
                                    !gestorAdministradores.dniExiste(Integer.valueOf(dniTextField1.getText()))) {

                                cliente = new Cliente(nombreCompletoTextField2.getText(), Integer.valueOf(dniTextField1.getText()), direccionTextField3.getText(),
                                        telefonoTextField4.getText(), true, emailTextField5.getText());
                                if (socioCheckBox.isSelected()) {
                                    cliente.setSocio(true);
                                } else {
                                    cliente.setSocio(false);
                                }
                                gestorClientes.crearUsuario(cliente);
                                gestorClientes.guardarArchivoJsonUsuarios(archivoUsuarios);
                                JOptionPane.showMessageDialog(null, "Cliente creado exitosamente...");


                            } else {
                                JOptionPane.showMessageDialog(null, "Usuario ya existente...");
                            }
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Debe completar todos los campos correspondientes...");
                    }

                }else if(vendedorCheckBox.isSelected()){
                    if(verificarCamposEmpleado()) {
                        if(verificarCampoNumerico(dniTextField1.getText())) {

                            if (!gestorClientes.dniExiste(Integer.valueOf(dniTextField1.getText())) && !gestorVendedores.dniExiste(Integer.valueOf(dniTextField1.getText()))
                                    && !gestorAdministradores.dniExiste(Integer.valueOf(dniTextField1.getText()))) {

                                vendedor = new Vendedor(nombreCompletoTextField2.getText(), Integer.valueOf(dniTextField1.getText()), direccionTextField3.getText(),
                                        telefonoTextField4.getText(), true, emailTextField5.getText(), contraseñaTextField6.getText(),
                                        Float.valueOf(sueldoTextField7.getText()), (Turno) turnoComboBox1.getSelectedItem());
                                gestorVendedores.crearUsuario(vendedor);
                                gestorVendedores.guardarArchivoJsonUsuarios(archivoUsuarios);
                                JOptionPane.showMessageDialog(null, "Vendedor creado exitosamente...");


                            } else {
                                JOptionPane.showMessageDialog(null, "Usuario ya existente...");
                            }
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Debe completar todos los campos correspondientes...");
                    }
                } else if(administradorCheckBox.isSelected()) {
                    if (verificarCamposEmpleado()) {
                        if (verificarCampoNumerico(dniTextField1.getText())) {
                            if (!gestorClientes.dniExiste(Integer.valueOf(dniTextField1.getText())) && !gestorVendedores.dniExiste(Integer.valueOf(dniTextField1.getText())) && !gestorAdministradores.dniExiste(Integer.valueOf(dniTextField1.getText()))) {//VERIFICA EN LAS 3 listas 8clientes, vendedores y administradores)
                                administrador = new Administrador(nombreCompletoTextField2.getText(), Integer.valueOf(dniTextField1.getText()), direccionTextField3.getText(),
                                        telefonoTextField4.getText(), true, emailTextField5.getText(), contraseñaTextField6.getText(),
                                        Float.valueOf(sueldoTextField7.getText()));

                                gestorAdministradores.crearUsuario(administrador);
                                gestorAdministradores.guardarArchivoJsonUsuarios(archivoUsuarios);
                                JOptionPane.showMessageDialog(null, "Administrador creado exitosamente...");

                            } else {
                                JOptionPane.showMessageDialog(null, "Usuario ya existente...");
                            }
                        }
                        } else {
                            JOptionPane.showMessageDialog(null, "Debe completar todos los campos correspondientes...");
                        }
                    }
                    }

        });


    ////////////////////////////////////     SOLAPA MODIFICAR    ////////////////////////////////////////////////

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                usuarioModificarJLabel.setText("----------");
                //TODO limpiar campos

                if(!(ingresoDniTextField1.getText().isBlank() && ingresoDniTextField1.getText().isEmpty())){

                    usuarioEncontrado = gestorClientes.buscarUsuarioPorDni(Integer.valueOf(ingresoDniTextField1.getText()));
                    if(usuarioEncontrado == null){
                        usuarioEncontrado = gestorVendedores.buscarUsuarioPorDni(Integer.valueOf(ingresoDniTextField1.getText()));
                        if(usuarioEncontrado == null){
                            usuarioEncontrado = gestorAdministradores.buscarUsuarioPorDni(Integer.valueOf(ingresoDniTextField1.getText()));
                        }
                    }

                    if(usuarioEncontrado != null){
                        JOptionPane.showMessageDialog(null, "Usuario encontrado...");
                        usuarioModificarJLabel.setText(usuarioEncontrado.toString());

                        habilitarCamposComunesModificar();

                        if(usuarioEncontrado instanceof Cliente){
                            habilitarCamposClienteModificar();
                        }else if(usuarioEncontrado instanceof Vendedor){
                            habilitarCamposVendedorModificar();
                        }else {
                            habilitarCamposAdministradorModificar();
                        }

                        setCamposModificarComunes();

                        if(usuarioEncontrado instanceof Cliente){
                            if(((Cliente) usuarioEncontrado).getSocio()){
                                socioCheckBox1.setSelected(true);
                            }else {
                                socioCheckBox1.setSelected(false);
                            }
                        }else if(usuarioEncontrado instanceof Empleado){
                            contraseniaTextField66.setText(((Empleado) usuarioEncontrado).getContraseña());
                            sueldoTextField77.setText(String.valueOf(((Empleado) usuarioEncontrado).getSueldo()));
                            if(usuarioEncontrado instanceof Vendedor){
                                turnoComboBox11.setSelectedItem((((Vendedor) usuarioEncontrado).getTurnoLaboral()));
                            }
                        }

                        modificarButton.setEnabled(true);

                    }else {
                        JOptionPane.showMessageDialog(null, "Usuario inexistente...");
                    }

                }
            }
        });

        clienteCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clienteCheckBox.isSelected()){
                    habilitarCamposClienteModificar();
                }

            }
        });

        administradorCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(administradorCheckBox.isSelected()) {
                    habilitarCamposAdministradorModificar();
                }
            }
        });

        vendedorCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vendedorCheckBox.isSelected()) {
                    habilitarCamposVendedorModificar();
                }
            }
        });


        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usuarioEncontrado instanceof Empleado){
                    if(verificarCamposEmpleadoModificar()){

                        usuarioEncontrado.setNombreCompleto(nombreCompletoTextField22.getText());
                        usuarioEncontrado.setDireccion(direccionTextField33.getText());
                        usuarioEncontrado.setTelefono(telefonoTextField44.getText());
                        usuarioEncontrado.setEmail(emailTextField55.getText());

                        ((Empleado) usuarioEncontrado).setContraseña(contraseniaTextField66.getText());
                        ((Empleado) usuarioEncontrado).setSueldo(Float.valueOf(sueldoTextField77.getText()));


                        if(activarCheckBox.isSelected()){
                            usuarioEncontrado.setActivo(true);
                        }else {
                            usuarioEncontrado.setActivo(false);
                        }

                        if(usuarioEncontrado instanceof Vendedor){
                            ((Vendedor) usuarioEncontrado).setTurnoLaboral((Turno) turnoComboBox11.getSelectedItem());
                            gestorVendedores.guardarArchivoJsonUsuarios(archivoUsuarios);
                        }else {
                            gestorAdministradores.guardarArchivoJsonUsuarios(archivoUsuarios);
                        }

                        JOptionPane.showMessageDialog(null, "Usuario modificado con exito....");
                    }else {

                        JOptionPane.showMessageDialog(null, "Debe completar todos los campos...");
                    }

                }else {
                    if(verificarCamposUsuarioModificar()){
                        usuarioEncontrado.setNombreCompleto(nombreCompletoTextField22.getText());
                        usuarioEncontrado.setDireccion(direccionTextField33.getText());
                        usuarioEncontrado.setTelefono(telefonoTextField44.getText());
                        usuarioEncontrado.setEmail(emailTextField55.getText());

                        if(socioCheckBox1.isSelected()){
                            ((Cliente) usuarioEncontrado).setSocio(true);
                        }else {
                            ((Cliente) usuarioEncontrado).setSocio(false);
                        }

                        if(activarCheckBox.isSelected()){
                            usuarioEncontrado.setActivo(true);
                        }else {
                            usuarioEncontrado.setActivo(false);
                        }

                        gestorClientes.guardarArchivoJsonUsuarios(archivoUsuarios);
                        JOptionPane.showMessageDialog(null, "Usuario modificado con exito....");
                    }else {
                        JOptionPane.showMessageDialog(null, "Debe completar todos los campos...");
                    }
                }


            }
        });







    ////////////////////////////////////     SOLAPA VER USUARIOS     ////////////////////////////////////////////////
        usuariosPorTipoComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (String.valueOf(usuariosPorTipoComboBox1.getSelectedItem())){
                    case "Cliente":
                        actualizarJList(usuariosPorTipoList1, gestorClientes.listarClientes());
                        break;
                    case "Vendedor":
                        actualizarJList(usuariosPorTipoList1, gestorVendedores.listarVendeores());
                        //actualizarJList(usuariosPorTipoList1, gestorClientes.listarVendeores());
                        break;
                    case "Administrador":
                        actualizarJList(usuariosPorTipoList1, gestorAdministradores.listarAdministradores());
                        //actualizarJList(usuariosPorTipoList1, gestorClientes.listarAdministradores());
                        break;

                }
            }
        });

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Usuario> lista = new ArrayList<>();
                lista.addAll(gestorClientes.listarClientes());
                lista.addAll(gestorVendedores.listarVendeores());
                lista.addAll(gestorAdministradores.listarAdministradores());

                actualizarJList(todoslosUsuariosList2, lista);

            }
        });

    }

    private void inhabilitarCampos(){
        //SOLAPA CREAR
        contraseñaTextField6.setEnabled(false);
        sueldoTextField7.setEnabled(false);
        socioCheckBox.setEnabled(false);
        turnoComboBox1.setEnabled(false);

        // SOLAPA MODIFICAR
        clienteCheckBox1.setEnabled(false);
        vendedorCheckBox1.setEnabled(false);
        administradorCheckBox1.setEnabled(false);
        nombreCompletoTextField22.setEnabled(false);
        direccionTextField33.setEnabled(false);
        telefonoTextField44.setEnabled(false);
        emailTextField55.setEnabled(false);
        socioCheckBox1.setEnabled(false);
        contraseniaTextField66.setEnabled(false);
        sueldoTextField77.setEnabled(false);
        turnoComboBox11.setEnabled(false);
        activarCheckBox.setEnabled(false);
        modificarButton.setEnabled(false);


    }
    private void habilitarCamposCliente(){
        vendedorCheckBox.setSelected(false);
        administradorCheckBox.setSelected(false);

        contraseñaTextField6.setEnabled(false);
        sueldoTextField7.setEnabled(false);
        socioCheckBox.setEnabled(true);
        turnoComboBox1.setEnabled(false);
    }
    private void habilitarCamposVendedor(){
        administradorCheckBox.setSelected(false);
        clienteCheckBox.setSelected(false);

        contraseñaTextField6.setEnabled(true);
        sueldoTextField7.setEnabled(true);
        socioCheckBox.setEnabled(false);
        turnoComboBox1.setEnabled(true);
    }

    private void habilitarCamposAdministrador(){
        vendedorCheckBox.setSelected(false);
        clienteCheckBox.setSelected(false);

        contraseñaTextField6.setEnabled(true);
        sueldoTextField7.setEnabled(true);
        socioCheckBox.setEnabled(false);
        turnoComboBox1.setEnabled(false);
    }

    private void habilitarCamposComunesModificar(){
        clienteCheckBox1.setEnabled(false);
        vendedorCheckBox1.setEnabled(false);
        administradorCheckBox1.setEnabled(false);
        nombreCompletoTextField22.setEnabled(true);
        direccionTextField33.setEnabled(true);
        telefonoTextField44.setEnabled(true);
        emailTextField55.setEnabled(true);
        activarCheckBox.setEnabled(true);
    }
    private void habilitarCamposClienteModificar(){
        clienteCheckBox1.setSelected(true);
        vendedorCheckBox1.setSelected(false);
        administradorCheckBox1.setSelected(false);

        contraseniaTextField66.setEnabled(false);
        sueldoTextField77.setEnabled(false);
        socioCheckBox1.setEnabled(true);
        turnoComboBox11.setEnabled(false);
    }
    private void habilitarCamposVendedorModificar(){
        vendedorCheckBox1.setSelected(true);
        administradorCheckBox1.setSelected(false);
        clienteCheckBox1.setSelected(false);

        contraseniaTextField66.setEnabled(true);
        sueldoTextField77.setEnabled(true);
        socioCheckBox1.setEnabled(false);
        turnoComboBox11.setEnabled(true);
    }

    private void habilitarCamposAdministradorModificar(){
        administradorCheckBox1.setSelected(true);
        vendedorCheckBox1.setSelected(false);
        clienteCheckBox1.setSelected(false);

        contraseniaTextField66.setEnabled(true);
        sueldoTextField77.setEnabled(true);
        socioCheckBox1.setEnabled(false);
        turnoComboBox11.setEnabled(false);
    }

    private void setCamposModificarComunes(){
        nombreCompletoTextField22.setText(usuarioEncontrado.getNombreCompleto());
        direccionTextField33.setText(usuarioEncontrado.getDireccion());
        telefonoTextField44.setText(usuarioEncontrado.getTelefono());
        emailTextField55.setText(usuarioEncontrado.getEmail());
        if(usuarioEncontrado.getActivo()){
            activarCheckBox.setSelected(true);
        }else {
            activarCheckBox.setSelected(false);
        }
    }


    private boolean verificarCamposUsuario(){
        boolean salida = false;
        if (!(dniTextField1.getText().isEmpty() && dniTextField1.getText().isBlank()) &&
                !(nombreCompletoTextField2.getText().isEmpty() && nombreCompletoTextField2.getText().isBlank()) &&
                !(direccionTextField3.getText().isEmpty() && direccionTextField3.getText().isBlank()) &&
                !(telefonoTextField4.getText().isEmpty() && telefonoTextField4.getText().isBlank()) &&
                !(emailTextField5.getText().isEmpty() && emailTextField5.getText().isBlank())){
            salida = true;

        }

        return salida;
    }

    public boolean verificarCamposEmpleado(){

        boolean salida = false;
        if (verificarCamposUsuario() &&
                !(contraseñaTextField6.getText().isEmpty() && contraseñaTextField6.getText().isBlank()) &&
                !(sueldoTextField7.getText().isEmpty() && sueldoTextField7.getText().isBlank())){
                salida = true;

        }

        return salida;
    }

    private boolean verificarCamposUsuarioModificar(){
        boolean salida = false;
        if (!(nombreCompletoTextField22.getText().isEmpty() && nombreCompletoTextField22.getText().isBlank()) &&
                !(direccionTextField33.getText().isEmpty() && direccionTextField33.getText().isBlank()) &&
                !(telefonoTextField44.getText().isEmpty() && telefonoTextField44.getText().isBlank()) &&
                !(emailTextField55.getText().isEmpty() && emailTextField55.getText().isBlank())){
            salida = true;

        }
        return salida;
    }

    public boolean verificarCamposEmpleadoModificar(){

        boolean salida = false;
        if (verificarCamposUsuario() &&
                !(contraseniaTextField66.getText().isEmpty() && contraseniaTextField66.getText().isBlank()) &&
                !(sueldoTextField77.getText().isEmpty() && sueldoTextField77.getText().isBlank())){
                salida = true;

        }

        return salida;
    }

    public <T> JList actualizarJList(JList jList, List<T> lista){
        DefaultListModel<T> model = new DefaultListModel<>();
        for (T elemento: lista) {
            model.addElement(elemento);
        }
        jList.setModel(model);

        return  jList;
    }

    private boolean verificarCampoNumerico(String cadena){//true si la cadena contiene solo numeros
        return cadena.matches("[0-9]+");
    }


}
