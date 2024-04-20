package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Neumatico;

public class NeumaticoDAO {
    private Connection connection;

    // Constructor que recibe la conexión a la base de datos
    public NeumaticoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un nuevo neumático en la base de datos
    public boolean insertarNeumatico(Neumatico neumatico) {
        String sql = "INSERT INTO Neumatico(neumatico_id, tamaño, modelo, estado, proveedor_id, cantidad_stock, precio) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, neumatico.getNeumaticoId());
            preparedStatement.setDouble(2, neumatico.getTamaño());
            preparedStatement.setString(3, neumatico.getModelo());
            preparedStatement.setBoolean(4, neumatico.isEstado());
            preparedStatement.setInt(5, neumatico.getProveedorId());
            preparedStatement.setInt(6, neumatico.getCantidadStock());
            preparedStatement.setDouble(7, neumatico.getPrecio());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un neumático en la base de datos
    public boolean actualizarNeumatico(Neumatico neumatico) {
        String sql = "UPDATE Neumatico SET tamaño=?, modelo=?, estado=?, proveedor_id=?, " +
                     "cantidad_stock=?, precio=? WHERE neumatico_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, neumatico.getTamaño());
            preparedStatement.setString(2, neumatico.getModelo());
            preparedStatement.setBoolean(3, neumatico.isEstado());
            preparedStatement.setInt(4, neumatico.getProveedorId());
            preparedStatement.setInt(5, neumatico.getCantidadStock());
            preparedStatement.setDouble(6, neumatico.getPrecio());
            preparedStatement.setInt(7, neumatico.getNeumaticoId());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un neumático de la base de datos
    public boolean eliminarNeumatico(int neumaticoId) {
        String sql = "DELETE FROM Neumatico WHERE neumatico_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, neumaticoId);

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todos los neumáticos de la base de datos
    public List<Neumatico> obtenerTodosNeumaticos() {
        List<Neumatico> listaNeumaticos = new ArrayList<>();
        String sql = "SELECT * FROM Neumatico";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int neumaticoId = resultSet.getInt("neumatico_id");
                double tamaño = resultSet.getDouble("tamaño");
                String modelo = resultSet.getString("modelo");
                boolean estado = resultSet.getBoolean("estado");
                int proveedorId = resultSet.getInt("proveedor_id");
                int cantidadStock = resultSet.getInt("cantidad_stock");
                double precio = resultSet.getDouble("precio");

                Neumatico neumatico = new Neumatico(neumaticoId, tamaño, modelo, estado,
                        proveedorId, cantidadStock, precio);
                listaNeumaticos.add(neumatico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaNeumaticos;
    }
    
 // Método para obtener todos los neumáticos por proveedor
    public List<Neumatico> obtenerNeumaticosPorProveedor(int proveedorId) {
        List<Neumatico> listaNeumaticos = new ArrayList<>();
        String sql = "SELECT * FROM Neumatico WHERE proveedor_id = ? ORDER BY neumatico_id ASC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, proveedorId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int neumaticoId = resultSet.getInt("neumatico_id");
                    double tamaño = resultSet.getDouble("tamaño");
                    String modelo = resultSet.getString("modelo");
                    boolean estado = resultSet.getBoolean("estado");
                    int cantidadStock = resultSet.getInt("cantidad_stock");
                    double precio = resultSet.getDouble("precio");

                    Neumatico neumatico = new Neumatico(neumaticoId, tamaño, modelo, estado,
                            proveedorId, cantidadStock, precio);
                    listaNeumaticos.add(neumatico);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaNeumaticos;
    }

    // Método para obtener un neumático por su ID
    public Neumatico obtenerNeumaticoPorId(int neumaticoId) {
        String sql = "SELECT * FROM Neumatico WHERE neumatico_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, neumaticoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    double tamaño = resultSet.getDouble("tamaño");
                    String modelo = resultSet.getString("modelo");
                    boolean estado = resultSet.getBoolean("estado");
                    int proveedorId = resultSet.getInt("proveedor_id");
                    int cantidadStock = resultSet.getInt("cantidad_stock");
                    double precio = resultSet.getDouble("precio");

                    return new Neumatico(neumaticoId, tamaño, modelo, estado,
                            proveedorId, cantidadStock, precio);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra ningún neumático con el ID especificado
    }
    
    public boolean actualizarEstadoPorCantidadCero() {
        String sql = "UPDATE Neumatico SET estado=false WHERE cantidad_stock=0";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String obtenerModeloPorId(int neumaticoId) {
        String sql = "SELECT modelo FROM Neumatico WHERE neumatico_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, neumaticoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("modelo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra ningún neumático con el ID especificado
    }
    
    public Double obtenerPrecioNeumatico(int neumaticoId) {
        String sql = "SELECT precio FROM Neumatico WHERE neumatico_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, neumaticoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("precio");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra ningún neumático con el ID especificado
    }
    
 // Función para sumar la cantidad de stock de un neumático
    public boolean sumarCantidadStock(int neumaticoId, int cantidad) {
        String sql = "UPDATE Neumatico SET cantidad_stock = cantidad_stock + ? WHERE neumatico_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setInt(2, neumaticoId);

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Función para restar la cantidad de stock de un neumático
    public boolean restarCantidadStock(int neumaticoId, int cantidad) {
        String sql = "UPDATE Neumatico SET cantidad_stock = cantidad_stock - ? WHERE neumatico_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setInt(2, neumaticoId);

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 // Método para obtener la cantidad de stock de un neumático por su ID
    public int obtenerCantidadStock(int neumaticoId) {
        String sql = "SELECT cantidad_stock FROM Neumatico WHERE neumatico_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, neumaticoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("cantidad_stock");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorna 0 si no se encuentra ningún neumático con el ID especificado
    }
    
 // Método para actualizar el estado de un neumático en función de la cantidad de stock
    public boolean actualizarEstadoPorCantidadStock(int neumaticoId) {
        String sql = "UPDATE Neumatico SET estado = (cantidad_stock > 0) WHERE neumatico_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, neumaticoId);

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 // Método para obtener el ID de un neumático a partir de su modelo
    public int obtenerIdPorModelo(String modelo) {
        String sql = "SELECT neumatico_id FROM Neumatico WHERE modelo=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, modelo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("neumatico_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retorna 0 si no se encuentra ningún neumático con el modelo especificado
    }
 // Método para obtener todos los neumáticos ordenados por ID
    public List<Neumatico> obtenerTodosNeumaticosOrdenadosPorId() {
        List<Neumatico> listaNeumaticos = new ArrayList<>();
        String sql = "SELECT * FROM Neumatico ORDER BY neumatico_id ASC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int neumaticoId = resultSet.getInt("neumatico_id");
                double tamaño = resultSet.getDouble("tamaño");
                String modelo = resultSet.getString("modelo");
                boolean estado = resultSet.getBoolean("estado");
                int proveedorId = resultSet.getInt("proveedor_id");
                int cantidadStock = resultSet.getInt("cantidad_stock");
                double precio = resultSet.getDouble("precio");

                Neumatico neumatico = new Neumatico(neumaticoId, tamaño, modelo, estado,
                        proveedorId, cantidadStock, precio);
                listaNeumaticos.add(neumatico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaNeumaticos;
    }
}

