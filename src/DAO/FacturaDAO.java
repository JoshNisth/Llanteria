package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelos.Factura;

public class FacturaDAO {
    private Connection connection;

    // Constructor que recibe la conexión a la base de datos
    public FacturaDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar una nueva factura en la base de datos
    public boolean insertarFactura(Factura factura) {
        String sql = "INSERT INTO Factura(factura_id, venta_id, neumatico_id, cantidad, precio_unitario) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, factura.getFacturaId());
            preparedStatement.setInt(2, factura.getVentaId());
            preparedStatement.setInt(3, factura.getNeumaticoId());
            preparedStatement.setInt(4, factura.getCantidad());
            preparedStatement.setDouble(5, factura.getPrecioUnitario());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todas las facturas de la base de datos
    public List<Factura> obtenerTodasFacturas() {
        List<Factura> listaFacturas = new ArrayList<>();
        String sql = "SELECT * FROM Factura";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int facturaId = resultSet.getInt("factura_id");
                int ventaId = resultSet.getInt("venta_id");
                int neumaticoId = resultSet.getInt("neumatico_id");
                int cantidad = resultSet.getInt("cantidad");
                double precioUnitario = resultSet.getDouble("precio_unitario");

                Factura factura = new Factura(facturaId, ventaId, neumaticoId, cantidad, precioUnitario);
                listaFacturas.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaFacturas;
    }
    
    public int obtenerMaximoIdFactura() {
        String sql = "SELECT MAX(factura_id) FROM Factura";
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
    
    public List<Factura> obtenerFacturasPorIdVenta(int idVenta) {
        List<Factura> listaFacturas = new ArrayList<>();
        String sql = "SELECT * FROM Factura WHERE venta_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idVenta);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int facturaId = resultSet.getInt("factura_id");
                    int ventaId = resultSet.getInt("venta_id");
                    int neumaticoId = resultSet.getInt("neumatico_id");
                    int cantidad = resultSet.getInt("cantidad");
                    double precioUnitario = resultSet.getDouble("precio_unitario");

                    Factura factura = new Factura(facturaId, ventaId, neumaticoId, cantidad, precioUnitario);
                    listaFacturas.add(factura);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaFacturas;
    }
    
    // Otros métodos según tus necesidades...
}

