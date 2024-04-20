package Modelos;

public class Proveedor {
    private int proveedorId;
    private String nombreEmpresa;
    private String direccionEmpresa;
    private int telefono;
    private String historial;

    // Constructor
    public Proveedor(int proveedorId, String nombreEmpresa, String direccionEmpresa, int telefono, String historial) {
        this.proveedorId = proveedorId;
        this.nombreEmpresa = nombreEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.telefono = telefono;
        this.historial = historial;
    }

    // Métodos de acceso (getters y setters)
    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "proveedorId=" + proveedorId +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                ", direccionEmpresa='" + direccionEmpresa + '\'' +
                ", telefono=" + telefono +
                ", historial='" + historial + '\'' +
                '}';
    }
}
