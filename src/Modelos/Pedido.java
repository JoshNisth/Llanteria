package Modelos;

import java.util.Date;

public class Pedido {
    private int pedidoId;
    private int cantidadPedida;
    private String productoPedido;
    private int carnetFuncionario;
    private int proveedorId;
    private Date fechaPedido;

    // Constructor
    public Pedido(int pedidoId, int cantidadPedida, String productoPedido, int carnetFuncionario, int proveedorId, Date fechaPedido) {
        this.pedidoId = pedidoId;
        this.cantidadPedida = cantidadPedida;
        this.productoPedido = productoPedido;
        this.carnetFuncionario = carnetFuncionario;
        this.proveedorId = proveedorId;
        this.fechaPedido = fechaPedido;
    }

    // Getters y setters

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(int cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public String getProductoPedido() {
        return productoPedido;
    }

    public void setProductoPedido(String productoPedido) {
        this.productoPedido = productoPedido;
    }

    public int getCarnetFuncionario() {
        return carnetFuncionario;
    }

    public void setCarnetFuncionario(int carnetFuncionario) {
        this.carnetFuncionario = carnetFuncionario;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    // Método toString para facilitar la visualización de objetos Pedido
    @Override
    public String toString() {
        return "Pedido{" +
                "pedidoId=" + pedidoId +
                ", cantidadPedida=" + cantidadPedida +
                ", productoPedido='" + productoPedido + '\'' +
                ", carnetFuncionario=" + carnetFuncionario +
                ", proveedorId=" + proveedorId +
                ", fechaPedido=" + fechaPedido +
                '}';
    }
}
