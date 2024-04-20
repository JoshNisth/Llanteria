package Modelos;

public class Cliente {
    private int carnetCliente;
    private String nombreCliente;
    private String direccion;
    private String tipoCliente;
    private String correoCliente;
    private String celularCliente;

    // Constructor
    public Cliente(int carnetCliente, String nombreCliente, String direccion, String tipoCliente, String correoCliente, String celularCliente) {
        this.carnetCliente = carnetCliente;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.tipoCliente = tipoCliente;
        this.correoCliente = correoCliente;
        this.celularCliente = celularCliente;
    }

    // Getters y Setters
    public int getCarnetCliente() {
        return carnetCliente;
    }

    public void setCarnetCliente(int carnetCliente) {
        this.carnetCliente = carnetCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }

    // Método toString para facilitar la visualización de objetos Cliente
    @Override
    public String toString() {
        return "Cliente{" +
                "carnetCliente=" + carnetCliente +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", direccion='" + direccion + '\'' +
                ", tipoCliente='" + tipoCliente + '\'' +
                ", correoCliente='" + correoCliente + '\'' +
                ", celularCliente='" + celularCliente + '\'' +
                '}';
    }
}

