package Modelos;

public class Neumatico {
    private int neumaticoId;
    private double tamaño;
    private String modelo;
    private boolean estado;
    private int proveedorId;
    private int cantidadStock;
    private double precio;

    // Constructor
    public Neumatico(int neumaticoId, double tamaño, String modelo, boolean estado,
                     int proveedorId, int cantidadStock, double precio) {
        this.neumaticoId = neumaticoId;
        this.tamaño = tamaño;
        this.modelo = modelo;
        this.estado = estado;
        this.proveedorId = proveedorId;
        this.cantidadStock = cantidadStock;
        this.precio = precio;
    }

    // Métodos getters y setters
    public int getNeumaticoId() {
        return neumaticoId;
    }

    public void setNeumaticoId(int neumaticoId) {
        this.neumaticoId = neumaticoId;
    }

    public double getTamaño() {
        return tamaño;
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método toString para facilitar la visualización de objetos Neumatico
    @Override
    public String toString() {
        return "Neumatico{" +
                "neumaticoId=" + neumaticoId +
                ", tamaño=" + tamaño +
                ", modelo='" + modelo + '\'' +
                ", estado=" + estado +
                ", proveedorId=" + proveedorId +
                ", cantidadStock=" + cantidadStock +
                ", precio=" + precio +
                '}';
    }
}

