package productos;

public enum TipoProducto {
    LACTEOS("Lácteos"),
    PANES("Panes"),
    SNACKS("Snacks"),
    BEBIDAS("Bebidas"),
    GALLETITAS("Galletitas"),
    GOLOSINAS("Golisinas"),
    CARNES("Carnes"),
    FRUTAS("Frutas"),
    VERDURAS("Verduras");

    private final String tipoProducto;

    TipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

}