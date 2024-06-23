package caja.facturacion;

import caja.carrito.Carrito;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import productos.Producto;
import usuarios.clientes.Cliente;
import usuarios.clientes.Socio;
import usuarios.empleados.vendedor.Vendedor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import com.itextpdf.text.Image;
import java.awt.Desktop;
import java.io.InputStream;


public class Factura implements Socio {
    private static int numeroDeFactura = 1000;
    private Cliente cliente;
    private Vendedor vendedor;
    private Float total;
    private Carrito carrito;

    public Factura(Cliente cliente, Vendedor vendedor, Float total, Carrito carrito) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.total = total;
        this.carrito = carrito;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Boolean isSocio() {
        return cliente.getSocio();
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Float getTotal() {
        return total;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void generarPdfFactura() {
        try {
            // Obtener el directorio del escritorio del usuario
            String userHome = System.getProperty("user.home");
            String desktopPath = userHome + File.separator + "Desktop";
            File desktopDir = new File(desktopPath);

            // Verificar si la carpeta "Desktop" existe, si no, crearla
            if (!desktopDir.exists()) {
                desktopDir.mkdir();
            }

            // Verificar si la carpeta "Facturas" existe, si no, crearla
            String facturasPath = desktopPath + File.separator + "Facturas";
            File facturasDir = new File(facturasPath);
            if (!facturasDir.exists()) {
                if (!facturasDir.mkdir()) {
                    return;
                }
            }

            String filePath = facturasPath + File.separator + "facturaA"+cliente.getDni()+"_"+numeroDeFactura+".pdf";
            numeroDeFactura++;
            // Crear el documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            PdfPTable tableSupermercado = new PdfPTable(1);
            tableSupermercado.setWidthPercentage(100);

            PdfPCell cellSupermercado = new PdfPCell();
            cellSupermercado.setBorder(PdfPCell.NO_BORDER);
            cellSupermercado.addElement(new Paragraph("Supermercado COTO", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            cellSupermercado.addElement(new Paragraph("Direccion: Ruta 11, KM 404", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            cellSupermercado.addElement(new Paragraph("Telefono: 0225-4571826", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            cellSupermercado.addElement(new Paragraph("CUIT: 30-54808315-6", FontFactory.getFont(FontFactory.HELVETICA, 12)));
            String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            cellSupermercado.addElement(new Paragraph("Fecha y Hora: " + timeStamp, FontFactory.getFont(FontFactory.HELVETICA, 12)));
            tableSupermercado.addCell(cellSupermercado);

            PdfPTable tableVendedor = new PdfPTable(1);
            tableVendedor.setWidthPercentage(100);

            PdfPCell cellVendedor = new PdfPCell();
            cellVendedor.setBorder(PdfPCell.NO_BORDER);
            cellVendedor.addElement(new Paragraph("Atendido por:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            cellVendedor.addElement(new Paragraph("Nombre: " + getVendedor().getNombreCompleto(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
            tableVendedor.addCell(cellVendedor);

            PdfPTable tableCliente = new PdfPTable(1);
            tableCliente.setWidthPercentage(100);

            PdfPCell cellCliente = new PdfPCell();
            cellCliente.setBorder(PdfPCell.NO_BORDER);
            cellCliente.addElement(new Paragraph("Datos del Cliente:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            cellCliente.addElement(new Paragraph("Nombre Completo: " + getCliente().getNombreCompleto(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
            cellCliente.addElement(new Paragraph("DNI: " + getCliente().getDni(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
            tableCliente.addCell(cellCliente);

            // Agregar párrafo con imagen a la derecha
            PdfPTable tableWithImage = new PdfPTable(2);
            tableWithImage.setWidthPercentage(100);
            float[] columnWidths = {2f, 1f};
            tableWithImage.setWidths(columnWidths);

            // Celda con párrafo
            PdfPCell textCell = new PdfPCell();
            textCell.setBorder(PdfPCell.NO_BORDER);
            textCell.addElement(tableSupermercado);

            // Celda con imagen
            PdfPCell imageCell = new PdfPCell();
            imageCell.setBorder(PdfPCell.NO_BORDER);
            imageCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

            String imagePath = "/caja/facturacion/download.png"; // Ajusta esto a tu estructura de paquetes
            try (InputStream is = getClass().getResourceAsStream(imagePath)) {
                if (is != null) {
                    Image image = Image.getInstance(is.readAllBytes());
                    image.scaleToFit(100, 100); // Ajusta el tamaño de la imagen según sea necesario
                    imageCell.addElement(image);
                } else {
                    System.out.println("No se pudo encontrar la imagen en el paquete.");
                }
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }

            tableWithImage.addCell(textCell);
            tableWithImage.addCell(imageCell);
            tableWithImage.addCell(cellCliente);
            tableWithImage.addCell(cellVendedor);

            document.add(tableWithImage);

            // Agregar lista de productos
            PdfPTable table = new PdfPTable(4); // 4 columnas
            table.setWidthPercentage(100);
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Producto", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Precio x unidad", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Cantidad", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("SubTotal", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            table.addCell(cell);

            Iterator<Map.Entry<Producto, Integer>> iterator = getCarrito().getProductos().entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<Producto, Integer> entry = iterator.next();
                Producto producto = entry.getKey();
                Integer cantidad = entry.getValue();
                table.addCell(new Phrase(producto.getNombre(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                table.addCell(new Phrase(String.valueOf(producto.getPrecio()), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                table.addCell(new Phrase(String.valueOf(cantidad), FontFactory.getFont(FontFactory.HELVETICA, 12)));
                table.addCell(new Phrase(String.valueOf((cantidad* producto.getPrecio())), FontFactory.getFont(FontFactory.HELVETICA, 12)));
            }

            document.add(table);
            document.add(new Paragraph(" "));

            // Agregar descuentos y total a abonar
            document.add(new Paragraph("Sub Total: $" + total, FontFactory.getFont(FontFactory.HELVETICA, 12)));
            document.add(new Paragraph("Descuento: $" + this.descuento(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
            document.add(new Paragraph("~~~~~~~~~~~~~~~~~~~~~~~"));
            document.add(new Paragraph("Total a abonar: $" + (total-this.descuento()), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));

            document.close();

            // Abrir el documento PDF
            File pdfFile = new File(filePath);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("AWT Desktop no está soportado!");
                }
            } else {
                System.out.println("El archivo PDF no existe.");
            }
        } catch (DocumentException | FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public float descuento () {
        if (cliente.getSocio()){
            return (total * 0.1f);
        } else {
            return  0;
        }
    }
}
