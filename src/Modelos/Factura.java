package Modelos;


import java.util.Objects;

public class Factura {
    private int facturaId;
    private int ventaId;
    private int neumaticoId;
    private int cantidad;
    private double precioUnitario;

    // Constructor
    public Factura(int facturaId, int ventaId, int neumaticoId, int cantidad, double precioUnitario) {
        this.facturaId = facturaId;
        this.ventaId = ventaId;
        this.neumaticoId = neumaticoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Getters y Setters
    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public int getNeumaticoId() {
        return neumaticoId;
    }

    public void setNeumaticoId(int neumaticoId) {
        this.neumaticoId = neumaticoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    // Método toString para facilitar la visualización de objetos Factura
    @Override
    public String toString() {
        return "Factura{" +
                "facturaId=" + facturaId +
                ", ventaId=" + ventaId +
                ", neumaticoId=" + neumaticoId +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                '}';
    }

    // Método equals para comparar objetos Factura
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return facturaId == factura.facturaId &&
                ventaId == factura.ventaId &&
                neumaticoId == factura.neumaticoId &&
                cantidad == factura.cantidad &&
                Double.compare(factura.precioUnitario, precioUnitario) == 0;
    }

    // Método hashCode para generar código hash de objetos Factura
    @Override
    public int hashCode() {
        return Objects.hash(facturaId, ventaId, neumaticoId, cantidad, precioUnitario);
    }
}

