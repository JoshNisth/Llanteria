package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelos.Cliente;

public class ClienteDAO {
    private Connection connection;

    // Constructor que recibe la conexión a la base de datos
    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un nuevo cliente en la base de datos
    public boolean insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente(carnet_cliente, nombre_cliente, direccion, tipo_cliente, correo_cliente, celular_cliente) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cliente.getCarnetCliente());
            preparedStatement.setString(2, cliente.getNombreCliente());
            preparedStatement.setString(3, cliente.getDireccion());
            preparedStatement.setString(4, cliente.getTipoCliente());
            preparedStatement.setString(5, cliente.getCorreoCliente());
            preparedStatement.setString(6, cliente.getCelularCliente());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un cliente en la base de datos
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre_cliente=?, direccion=?, tipo_cliente=?, correo_cliente=?, celular_cliente=? " +
                     "WHERE carnet_cliente=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cliente.getNombreCliente());
            preparedStatement.setString(2, cliente.getDireccion());
            preparedStatement.setString(3, cliente.getTipoCliente());
            preparedStatement.setString(4, cliente.getCorreoCliente());
            preparedStatement.setString(5, cliente.getCelularCliente());
            preparedStatement.setInt(6, cliente.getCarnetCliente());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un cliente de la base de datos
    public boolean eliminarCliente(int carnetCliente) {
        String sql = "DELETE FROM Cliente WHERE carnet_cliente=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, carnetCliente);

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todos los clientes de la base de datos
    public List<Cliente> obtenerTodosClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int carnetCliente = resultSet.getInt("carnet_cliente");
                String nombreCliente = resultSet.getString("nombre_cliente");
                String direccion = resultSet.getString("direccion");
                String tipoCliente = resultSet.getString("tipo_cliente");
                String correoCliente = resultSet.getString("correo_cliente");
                String celularCliente = resultSet.getString("celular_cliente");

                Cliente cliente = new Cliente(carnetCliente, nombreCliente, direccion, tipoCliente, correoCliente, celularCliente);
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaClientes;
    }
    
    //carnet
    public Cliente obtenerClientePorCarnet(int carnetCliente) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE carnet_cliente=?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, carnetCliente);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Si se encuentra el cliente, crea un objeto Cliente con los datos
                    int carnet = resultSet.getInt("carnet_cliente");
                    String nombre = resultSet.getString("nombre_cliente");
                    String direccion = resultSet.getString("direccion");
                    String tipo = resultSet.getString("tipo_cliente");
                    String correo = resultSet.getString("correo_cliente");
                    String celular = resultSet.getString("celular_cliente");

                    cliente = new Cliente(carnet, nombre, direccion, tipo, correo, celular);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }
    public String obtenerNombreClientePorCI(int carnetCliente) {
        String nombreCliente = null;
        String sql = "SELECT nombre_cliente FROM Cliente WHERE carnet_cliente=?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, carnetCliente);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Si se encuentra el cliente, obtiene el nombre
                    nombreCliente = resultSet.getString("nombre_cliente");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreCliente;
    }

}

