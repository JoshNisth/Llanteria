package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Proveedor;

public class ProveedorDAO {
    private Connection connection;

    // Constructor que recibe la conexión a la base de datos
    public ProveedorDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un nuevo proveedor en la base de datos
    public boolean insertarProveedor(Proveedor proveedor) {
        String sql = "INSERT INTO Proveedor(proveedor_id, nombre_empresa, direccion_empresa, telefono, historial) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, proveedor.getProveedorId());
            preparedStatement.setString(2, proveedor.getNombreEmpresa());
            preparedStatement.setString(3, proveedor.getDireccionEmpresa());
            preparedStatement.setInt(4, proveedor.getTelefono());
            preparedStatement.setString(5, proveedor.getHistorial());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un proveedor en la base de datos
    public boolean actualizarProveedor(Proveedor proveedor) {
        String sql = "UPDATE Proveedor SET nombre_empresa=?, direccion_empresa=?, telefono=?, historial=? " +
                     "WHERE proveedor_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, proveedor.getNombreEmpresa());
            preparedStatement.setString(2, proveedor.getDireccionEmpresa());
            preparedStatement.setInt(3, proveedor.getTelefono());
            preparedStatement.setString(4, proveedor.getHistorial());
            preparedStatement.setInt(5, proveedor.getProveedorId());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un proveedor de la base de datos
    public boolean eliminarProveedor(int proveedorId) {
        String sql = "DELETE FROM Proveedor WHERE proveedor_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, proveedorId);

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todos los proveedores de la base de datos
    public List<Proveedor> obtenerTodosProveedores() {
        List<Proveedor> listaProveedores = new ArrayList<>();
        String sql = "SELECT * FROM Proveedor";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int proveedorId = resultSet.getInt("proveedor_id");
                String nombreEmpresa = resultSet.getString("nombre_empresa");
                String direccionEmpresa = resultSet.getString("direccion_empresa");
                int telefono = resultSet.getInt("telefono");
                String historial = resultSet.getString("historial");

                Proveedor proveedor = new Proveedor(proveedorId, nombreEmpresa, direccionEmpresa, telefono, historial);
                listaProveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProveedores;
    }

    // Método para obtener un proveedor por su ID
    public Proveedor obtenerProveedorPorId(int proveedorId) {
        String sql = "SELECT * FROM Proveedor WHERE proveedor_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, proveedorId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nombreEmpresa = resultSet.getString("nombre_empresa");
                    String direccionEmpresa = resultSet.getString("direccion_empresa");
                    int telefono = resultSet.getInt("telefono");
                    String historial = resultSet.getString("historial");

                    return new Proveedor(proveedorId, nombreEmpresa, direccionEmpresa, telefono, historial);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra ningún proveedor con el ID especificado
    }
}
