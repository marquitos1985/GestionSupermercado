package gui;

import productos.*;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

public class GestionProductosGui {
    private JComboBox tipoProductoComboBox1;
    private JTextField nombreTextField2;
    private JTextField marcaTextField3;
    private JTextField stockTextField4;
    private JTextField precioTextField5;
    private JTextField descripcionTextField6;
    private JTextField fechaVencTextField7;
    private JTextField pesoTextField8;
    private JTextField precioPesoTextField9;
    private JButton crearButton;
    private JTabbedPane crearTabbedPane1;
    private JPanel gestionProductosJPanel;
    private JTextField idProductoTextField1;
    private JButton buscarButton;
    private JTabbedPane tabbedPane2;
    private JTextField idProductoTextField10;
    private JButton buscarButton1;
    private JButton ELIMINARButton;
    private JTextField nombreTextField11;
    private JTextField marcaTextField12;
    private JComboBox tipoProductoComboBox2;
    private JTextField precioTextField13;
    private JTextField descripcionTextField14;
    private JTextField venciminetoTextField15;
    private JTextField stockTextField16;
    private JTextField pesoTextField17;
    private JTextField precioPorPesoTextField18;
    private JCheckBox porPesoCheckBox1;
    private JButton modificarButton;
    private JLabel prodEncontradoLabel;
    private JLabel productoEncontradoJLabel;
    private JComboBox verProductosPorTipoComboBox1;
    private JList verProductosPorTipoList1;
    private JList verTodosLosProductosList1;
    private JButton verButton;
    private JScrollPane verProductosPorTipoScrollPane;
    private JScrollPane verTodosLosProductosScrollPane;
    private final String archivoProductos;
    private GestorProductos gestorProductos;
    private Producto productoEliminar;
    private Producto productoModificar;

    public GestionProductosGui(GestorProductos gestorProductos, String archivoProductos) {

        this.gestorProductos = gestorProductos;
        this.archivoProductos = archivoProductos;


        JFrame gestionProductosFrame = new JFrame("Gestión de productos");
        gestionProductosFrame.setContentPane(this.gestionProductosJPanel);
        gestionProductosFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gestionProductosFrame.setVisible(true);
        acciones();


    }

    private void acciones() {
        inhabilitarCampos();
        inhabilitarBotones();
        for (TipoProducto tipo : TipoProducto.values()) {
            tipoProductoComboBox1.addItem(tipo);
            tipoProductoComboBox2.addItem(tipo);
            verProductosPorTipoComboBox1.addItem(tipo);
        }
        tipoProductoComboBox1.setSelectedItem(TipoProducto.BEBIDAS);


        productoEliminar = null;
        productoModificar = null;


        ////////////////////////////////////////////     SOLAPA CREAR     ////////////////////////////////////////////
        porPesoCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (porPesoCheckBox1.isSelected()) {
                    pesoTextField8.setEnabled(true);
                    precioPesoTextField9.setEnabled(true);
                } else {
                    pesoTextField8.setEnabled(false);
                    precioPesoTextField9.setEnabled(false);
                }

            }
        });
        crearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Producto producto = null;
                if (verificarCamposVaciosCreacion()) {
                    if (!porPesoCheckBox1.isSelected()) {
                        try {
                            producto = gestorProductos.crearProducto(nombreTextField2.getText(), marcaTextField3.getText(),
                                    (TipoProducto) tipoProductoComboBox1.getSelectedItem(), Float.valueOf(precioTextField5.getText()),
                                    descripcionTextField6.getText(), fechaVencTextField7.getText(), Integer.valueOf(stockTextField4.getText()));

                        } catch (StockException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }catch (PrecioNegativoException ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }


                    } else {
                        try {
                            producto = gestorProductos.crearProductoPorPeso(nombreTextField2.getText(), marcaTextField3.getText(),
                                    (TipoProducto) tipoProductoComboBox1.getSelectedItem(), Float.valueOf(precioTextField5.getText()),
                                    descripcionTextField6.getText(), fechaVencTextField7.getText(), Integer.valueOf(stockTextField4.getText()),
                                    Float.valueOf(pesoTextField8.getText()), Float.valueOf(precioPesoTextField9.getText()));


                        } catch (StockException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        } catch (PrecioNegativoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        } catch (PesoNegativoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    if (producto != null) {

                        gestorProductos.agregarProducto(producto);
                        gestorProductos.guardarArchivoJsonProductos("productos.json");
                        JOptionPane.showMessageDialog(null, "Creación exitosa...");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos...");
                }
            }
        });

        ////////////////////////////////////////////     SOLAPA MODIFICAR     ////////////////////////////////////////////
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if (!(idProductoTextField1.getText().isEmpty() && idProductoTextField1.getText().isBlank())) {
                if (!verificarCampoVacio(idProductoTextField1.getText())) {
                    productoModificar = gestorProductos.buscarPorId(idProductoTextField1.getText().toUpperCase());

                    if (productoModificar != null) {
                        JOptionPane.showMessageDialog(null, "Producto encontrado...");
                        prodEncontradoLabel.setText(productoModificar.toString());
                        setCamposModificar(productoModificar);

                        modificarButton.setEnabled(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Producto inexistente...");
                    }
                }
            }
        });


        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (verificarCamposVaciosModificacion()) {
                    if (productoModificar instanceof ProductoPorPeso) {
                        try {
                            gestorProductos.modificarProductoPorPeso((ProductoPorPeso) productoModificar, nombreTextField11.getText(),
                                    marcaTextField12.getText(), (TipoProducto) tipoProductoComboBox2.getSelectedItem(), Float.valueOf(precioTextField13.getText()),
                                    descripcionTextField14.getText(), venciminetoTextField15.getText(), Integer.valueOf(stockTextField16.getText()),
                                    Float.valueOf(pesoTextField17.getText()), Float.valueOf(precioPorPesoTextField18.getText()));
                            gestorProductos.guardarArchivoJsonProductos(archivoProductos);
                            JOptionPane.showMessageDialog(null, "Se modificó el producto...");
                        } catch (StockException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        } catch (PrecioNegativoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        } catch (PesoNegativoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {

                        try {
                            gestorProductos.modificarProducto(productoModificar, nombreTextField11.getText(),
                                    marcaTextField12.getText(), (TipoProducto) tipoProductoComboBox2.getSelectedItem(), Float.valueOf(precioTextField13.getText()),
                                    descripcionTextField14.getText(), venciminetoTextField15.getText(), Integer.valueOf(stockTextField16.getText()));
                            gestorProductos.guardarArchivoJsonProductos(archivoProductos);
                            JOptionPane.showMessageDialog(null, "Se modificó el producto...");
                        } catch (StockException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        } catch (PrecioNegativoException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }

                    }




                }

            }
        });
        ////////////////////////////////////////////     SOLAPA ELIMINAR     ////////////////////////////////////////////
        buscarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!verificarCampoVacio(idProductoTextField10.getText())) {
                    productoEliminar = gestorProductos.buscarPorId(idProductoTextField10.getText().toUpperCase());
                    if (productoEliminar != null) {
                        JOptionPane.showMessageDialog(null, "Producto encontrado...");
                        productoEncontradoJLabel.setText(productoEliminar.toString());
                        ELIMINARButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Producto inexistente...");
                    }
                }
            }
        });

        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gestorProductos.eliminarProducto(productoEliminar);
                gestorProductos.guardarArchivoJsonProductos(archivoProductos);
                JOptionPane.showMessageDialog(null, "Se eliminó el producto: " + productoEliminar);
            }
        });


        ////////////////////////////////////////////     SOLAPA VER PRODUCTOS     ////////////////////////////////////////////

        verProductosPorTipoComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Producto> lista = gestorProductos.buscarPorTipo((TipoProducto) verProductosPorTipoComboBox1.getSelectedItem());
                if (!lista.isEmpty()) {
                    actualizarJList(verProductosPorTipoList1, lista);
                } else {
                    JOptionPane.showMessageDialog(null, "El tipo no tiene productos....");
                }

            }
        });
        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Producto> lista = gestorProductos.listar();
                if (!lista.isEmpty()) {
                    actualizarJList(verTodosLosProductosList1, lista);
                } else {
                    JOptionPane.showMessageDialog(null, "No existen productos....");
                }

            }

        });

    }

    public boolean verificarCamposVaciosCreacion() {

        boolean salida = false;
        if (!(nombreTextField2.getText().isEmpty() && nombreTextField2.getText().isBlank()) &&
                !(marcaTextField3.getText().isEmpty() && marcaTextField3.getText().isBlank()) &&
                !(precioTextField5.getText().isEmpty() && precioTextField5.getText().isBlank()) &&
                !(descripcionTextField6.getText().isEmpty() && descripcionTextField6.getText().isBlank()) &&
                !(fechaVencTextField7.getText().isEmpty() && fechaVencTextField7.getText().isBlank()) &&
                !(stockTextField4.getText().isEmpty() && stockTextField4.getText().isBlank())) {

            if (porPesoCheckBox1.isSelected()){
                if(!(pesoTextField8.getText().isEmpty() && pesoTextField8.getText().isBlank()) &&
                        !(precioPesoTextField9.getText().isEmpty() && precioPesoTextField9.getText().isBlank())){
                    salida = true;
                }
            }else {
                salida = true;
            }


        }

        return salida;
    }

    public boolean verificarCamposVaciosModificacion() {

        boolean salida = false;
        if (!(nombreTextField11.getText().isEmpty() && nombreTextField11.getText().isBlank()) &&
                !(marcaTextField12.getText().isEmpty() && marcaTextField12.getText().isBlank()) &&
                !(precioTextField13.getText().isEmpty() && precioTextField13.getText().isBlank()) &&
                !(descripcionTextField14.getText().isEmpty() && descripcionTextField14.getText().isBlank()) &&
                !(venciminetoTextField15.getText().isEmpty() && venciminetoTextField15.getText().isBlank()) &&
                !(stockTextField16.getText().isEmpty() && stockTextField16.getText().isBlank())) {

            if (porPesoCheckBox1.isSelected() &&
                    !(pesoTextField17.getText().isEmpty() && pesoTextField17.getText().isBlank()) &&
                    !(precioPorPesoTextField18.getText().isEmpty() && precioPorPesoTextField18.getText().isBlank())) {
                salida = true;

            } else {
                salida = true;
            }

        }

        return salida;
    }

    private void inhabilitarBotones() {
        modificarButton.setEnabled(false);
        ELIMINARButton.setEnabled(false);
    }

    public void setCamposModificar(Producto producto) {
        nombreTextField11.setEnabled(true);
        marcaTextField12.setEnabled(true);
        precioTextField13.setEnabled(true);
        descripcionTextField14.setEnabled(true);
        venciminetoTextField15.setEnabled(true);
        stockTextField16.setEnabled(true);

        nombreTextField11.setText(producto.getNombre());
        marcaTextField12.setText(producto.getMarca());
        precioTextField13.setText(String.valueOf(producto.getPrecio()));
        descripcionTextField14.setText(producto.getDescripcion());
        venciminetoTextField15.setText(producto.getFechaDeVencimiento());
        stockTextField16.setText(String.valueOf(producto.getStock()));
        tipoProductoComboBox2.setSelectedItem(producto.getTipoProducto());

        if (producto instanceof ProductoPorPeso) {
            pesoTextField17.setEnabled(true);
            precioPorPesoTextField18.setEnabled(true);

            pesoTextField17.setText(String.valueOf(((ProductoPorPeso) producto).getPeso()));
            precioPorPesoTextField18.setText(String.valueOf(((ProductoPorPeso) producto).getPrecioPorPeso()));
        }
    }

    private void inhabilitarCampos() {

        //// SOLAPA CREAR

        porPesoCheckBox1.setSelected(false);
        pesoTextField8.setEnabled(false);
        precioPesoTextField9.setEnabled(false);

        //// SOLAPA MODIFICAR
        nombreTextField11.setEnabled(false);
        marcaTextField12.setEnabled(false);
        precioTextField13.setEnabled(false);
        descripcionTextField14.setEnabled(false);
        venciminetoTextField15.setEnabled(false);
        stockTextField16.setEnabled(false);
        pesoTextField17.setEnabled(false);
        precioPorPesoTextField18.setEnabled(false);
    }


    public <T> JList actualizarJList(JList jList, List<T> lista) {
        DefaultListModel<T> model = new DefaultListModel<>();
        for (T elemento : lista) {
            model.addElement(elemento);
        }
        jList.setModel(model);

        return jList;
    }

    private boolean verificarCampoVacio(String cadena){//true si el la cadena está vacía
        return (cadena.isBlank() && cadena.isEmpty());
    }



}