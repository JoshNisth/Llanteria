package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Modelos.Pedido;

public class PedidoDAO {
    private Connection connection;

    // Constructor que recibe la conexión a la base de datos
    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un nuevo pedido en la base de datos
    public boolean insertarPedido(Pedido pedido) {
        String sql = "INSERT INTO Pedido(pedido_id, cantidad_pedida, producto_pedido, carnet_fun, proveedor_id, fecha_pedido) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pedido.getPedidoId());
            preparedStatement.setInt(2, pedido.getCantidadPedida());
            preparedStatement.setString(3, pedido.getProductoPedido());
            preparedStatement.setInt(4, pedido.getCarnetFuncionario());
            preparedStatement.setInt(5, pedido.getProveedorId());
            preparedStatement.setDate(6, new java.sql.Date(pedido.getFechaPedido().getTime()));

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un pedido en la base de datos
    public boolean actualizarPedido(Pedido pedido) {
        String sql = "UPDATE Pedido SET cantidad_pedida=?, producto_pedido=?, carnet_fun=?, proveedor_id=?, fecha_pedido=? " +
                     "WHERE pedido_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pedido.getCantidadPedida());
            preparedStatement.setString(2, pedido.getProductoPedido());
            preparedStatement.setInt(3, pedido.getCarnetFuncionario());
            preparedStatement.setInt(4, pedido.getProveedorId());
            preparedStatement.setDate(5, new java.sql.Date(pedido.getFechaPedido().getTime()));
            preparedStatement.setInt(6, pedido.getPedidoId());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un pedido de la base de datos
    public boolean eliminarPedido(int pedidoId) {
        String sql = "DELETE FROM Pedido WHERE pedido_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pedidoId);

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todos los pedidos de la base de datos
    public List<Pedido> obtenerTodosPedidos() {
        List<Pedido> listaPedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int pedidoId = resultSet.getInt("pedido_id");
                int cantidadPedida = resultSet.getInt("cantidad_pedida");
                String productoPedido = resultSet.getString("producto_pedido");
                int carnetFuncionario = resultSet.getInt("carnet_fun");
                int proveedorId = resultSet.getInt("proveedor_id");
                Date fechaPedido = resultSet.getDate("fecha_pedido");

                Pedido pedido = new Pedido(pedidoId, cantidadPedida, productoPedido, carnetFuncionario, proveedorId, fechaPedido);
                listaPedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPedidos;
    }
}

