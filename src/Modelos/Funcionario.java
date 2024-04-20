package Modelos;

import java.util.Date;

public class Funcionario {
    private int carnetFuncionario;
    private String nombreFuncionario;
    private int sueldo;
    private Date fechaContratacion;
    private int numeroCelular;
    private String correoFuncionario;
    private String contraseña;

    // Constructor
    public Funcionario(int carnetFuncionario, String nombreFuncionario, int sueldo, Date fechaContratacion,
                       int numeroCelular, String correoFuncionario, String contraseña) {
        this.carnetFuncionario = carnetFuncionario;
        this.nombreFuncionario = nombreFuncionario;
        this.sueldo = sueldo;
        this.fechaContratacion = fechaContratacion;
        this.numeroCelular = numeroCelular;
        this.correoFuncionario = correoFuncionario;
        this.contraseña = contraseña;
    }

    // Getters y Setters
    public int getCarnetFuncionario() {
        return carnetFuncionario;
    }

    public void setCarnetFuncionario(int carnetFuncionario) {
        this.carnetFuncionario = carnetFuncionario;
    }

    public String getNombreFuncionario() {
        return nombreFuncionario;
    }

    public void setNombreFuncionario(String nombreFuncionario) {
        this.nombreFuncionario = nombreFuncionario;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public int getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(int numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getCorreoFuncionario() {
        return correoFuncionario;
    }

    public void setCorreoFuncionario(String correoFuncionario) {
        this.correoFuncionario = correoFuncionario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    // Método toString para facilitar la visualización de objetos Funcionario
    @Override
    public String toString() {
        return "Funcionario{" +
                "carnetFuncionario=" + carnetFuncionario +
                ", nombreFuncionario='" + nombreFuncionario + '\'' +
                ", sueldo=" + sueldo +
                ", fechaContratacion=" + fechaContratacion +
                ", numeroCelular=" + numeroCelular +
                ", correoFuncionario='" + correoFuncionario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}
