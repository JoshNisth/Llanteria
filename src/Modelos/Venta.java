package Modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Venta {
    private int ventaId;
    private Date fechaVenta;
    private double montoTotal;
    private int carnetFuncionario;
    private int carnetCliente;

    // Constructor
    public Venta(int ventaId, Date fechaVenta, double montoTotal, int carnetFuncionario, int carnetCliente) {
        this.ventaId = ventaId;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
        this.carnetFuncionario = carnetFuncionario;
        this.carnetCliente = carnetCliente;
    }

    // Métodos getter
    public int getVentaId() {
        return ventaId;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public int getCarnetFuncionario() {
        return carnetFuncionario;
    }

    public int getCarnetCliente() {
        return carnetCliente;
    }

    // Método toString
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Venta{" +
                "ventaId=" + ventaId +
                ", fechaVenta=" + sdf.format(fechaVenta) +
                ", montoTotal=" + montoTotal +
                ", carnetFuncionario=" + carnetFuncionario +
                ", carnetCliente=" + carnetCliente +
                '}';
    }
}

