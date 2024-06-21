package caja.facturacion;

import caja.carrito.Carrito;
import usuarios.clientes.Cliente;
import usuarios.clientes.Socio;
import usuarios.empleados.vendedor.Vendedor;

public class Factura implements Socio {

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

    public Boolean isSocio () {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n----------------------------------\n");
        sb.append("              FACTURA\n");
        sb.append("----------------------------------\n");
        sb.append("Cliente: ").append(cliente).append("\n");
        sb.append("Vendedor: ").append(vendedor).append("\n");
        sb.append("Fecha: ").append(java.time.LocalDate.now()).append("\n");
        sb.append("\n----------------------------------\n");
        sb.append("Productos:\n");
        sb.append("Cantidad  Descripción       Precio Unitario  Total\n");
        sb.append("--------  ----------------  --------------  -------\n");

        for (String producto : carrito.listar()) {
            sb.append("1         ").append(producto).append("\n");
        }

        sb.append("\n----------------------------------\n");
        sb.append("Total: ").append(descuento()).append(" €\n");
        sb.append("\n----------------------------------\n");
        sb.append("Observaciones: Gracias por su compra.\n");
        sb.append("\n----------------------------------\n");
        sb.append("Vendedor: ").append(vendedor).append("\n");
        sb.append("Dirección: " + getCliente().getDireccion()+ "\n");
        sb.append("Teléfono: " + getCliente().getTelefono() + "\n");
        sb.append("Correo electrónico: " + getCliente().getEmail() + "\n");
        sb.append("\n----------------------------------\n");

        return sb.toString();
    }

    @Override
    public Float descuento() {
        if (isSocio()) {
            return (float) (total * 0.9);
        }
        else {
            return total;
        }
    }
}
