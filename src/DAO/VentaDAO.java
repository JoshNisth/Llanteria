package DAO;
import Modelos.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaDAO {
    private Connection connection;

    // Constructor que recibe la conexión a la base de datos
    public VentaDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar una nueva venta en la base de datos
    public boolean insertarVenta(Venta venta) {
        String sql = "INSERT INTO Venta(venta_id, fecha_venta, monto_total, carnet_fun, carnet_cliente) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, venta.getVentaId());
            preparedStatement.setDate(2, new java.sql.Date(venta.getFechaVenta().getTime()));
            preparedStatement.setDouble(3, venta.getMontoTotal());
            preparedStatement.setInt(4, venta.getCarnetFuncionario());
            preparedStatement.setInt(5, venta.getCarnetCliente());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todas las ventas de la base de datos
    public List<Venta> obtenerTodasVentas() {
        List<Venta> listaVentas = new ArrayList<>();
        String sql = "SELECT * FROM Venta";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int ventaId = resultSet.getInt("venta_id");
                Date fechaVenta = resultSet.getDate("fecha_venta");
                double montoTotal = resultSet.getDouble("monto_total");
                int carnetFuncionario = resultSet.getInt("carnet_fun");
                int carnetCliente = resultSet.getInt("carnet_cliente");

                Venta venta = new Venta(ventaId, fechaVenta, montoTotal, carnetFuncionario, carnetCliente);
                listaVentas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaVentas;
    }
    
    public int obtenerMaximoIdVenta() {
        String sql = "SELECT MAX(venta_id) FROM Venta";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Devuelve -1 en caso de error
    }
    public Venta obtenerVentaPorId(int idVenta) {
        String sql = "SELECT * FROM Venta WHERE venta_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idVenta);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Date fechaVenta = resultSet.getDate("fecha_venta");
                    double montoTotal = resultSet.getDouble("monto_total");
                    int carnetFuncionario = resultSet.getInt("carnet_fun");
                    int carnetCliente = resultSet.getInt("carnet_cliente");

                    return new Venta(idVenta, fechaVenta, montoTotal, carnetFuncionario, carnetCliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Devuelve null en caso de no encontrar la venta
    }
    public double obtenerMontoTotalPorId(int idVenta) {
        String sql = "SELECT monto_total FROM Venta WHERE venta_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idVenta);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("monto_total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0; // Devuelve 0.0 en caso de no encontrar la venta o si hay un error
    }
    
    public int obtenerCarnetFunlPorId(int idVenta) {
        String sql = "SELECT carnet_fun FROM Venta WHERE venta_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idVenta);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("carnet_fun");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Devuelve 0 en caso de no encontrar la venta o si hay un error
    }
    

    // Otros métodos que puedas necesitar (actualizar, eliminar, etc.)

    // Método toString para facilitar la visualización de objetos Venta
    @Override
    public String toString() {
        return "VentaDAO{" +
                "connection=" + connection +
                '}';
    }
}
