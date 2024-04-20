import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConexionBD.Conexion;

public class Main {

    public static void main(String[] args) throws SQLException {
        Conexion c = new Conexion();
        Connection connection = c.getConexionPostgres();

        // Llamada a la función lectura para clientes
        System.out.println("Datos de Clientes:");
        lecturaClientes(connection);

        // Llamada a la función lectura para neumáticos
        System.out.println("\nDatos de Neumáticos:");
        lecturaNeumaticosOrden(connection);

        // Cierre de la conexión
        if (connection != null) {
            connection.close();
        }
    }

    public static void lecturaClientes(Connection connection) {
        // Sentencia SQL para seleccionar todos los datos de la tabla Cliente
        String sql = "SELECT * FROM Cliente";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterar a través de los resultados y mostrar la información
            while (resultSet.next()) {
                int carnetCliente = resultSet.getInt("Carnet_Cliente");
                String nombreCliente = resultSet.getString("Nombre_Cliente");
                String direccion = resultSet.getString("Direccion");
                String tipoCliente = resultSet.getString("Tipo_Cliente");
                String correoCliente = resultSet.getString("Correo_Cliente");

                System.out.println("Carnet Cliente: " + carnetCliente);
                System.out.println("Nombre Cliente: " + nombreCliente);
                System.out.println("Direccion: " + direccion);
                System.out.println("Tipo Cliente: " + tipoCliente);
                System.out.println("Correo Cliente: " + correoCliente);
                System.out.println("----------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void lecturaNeumaticos(Connection connection) {
        // Sentencia SQL para seleccionar todos los datos de la tabla Neumatico
        String sql = "SELECT * FROM Neumatico";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterar a través de los resultados y mostrar la información
            while (resultSet.next()) {
                int neumaticoId = resultSet.getInt("Neumatico_Id");
                double tamaño = resultSet.getDouble("Tamaño");
                String modelo = resultSet.getString("Modelo");
                boolean estado = resultSet.getBoolean("Estado");
                int proveedorId = resultSet.getInt("Proveedor_id");
                double precio=resultSet.getDouble("Precio");

                System.out.println("ID Neumático: " + neumaticoId);
                System.out.println("Tamaño: " + tamaño);
                System.out.println("Modelo: " + modelo);
                System.out.println("Estado: " + estado);
                System.out.println("ID Proveedor: " + proveedorId);
                System.out.println("Precio: " + precio);
                System.out.println("----------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void lecturaNeumaticosOrden(Connection connection) {
        // Sentencia SQL para seleccionar todos los datos de la tabla Neumatico
        String sql = "SELECT * FROM Neumatico ORDER BY neumatico_id;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterar a través de los resultados y mostrar la información
            while (resultSet.next()) {
                int neumaticoId = resultSet.getInt("Neumatico_Id");
                double tamaño = resultSet.getDouble("Tamaño");
                String modelo = resultSet.getString("Modelo");
                boolean estado = resultSet.getBoolean("Estado");
                int proveedorId = resultSet.getInt("Proveedor_id");
                double precio=resultSet.getDouble("Precio");

                System.out.println("ID Neumático: " + neumaticoId);
                System.out.println("Tamaño: " + tamaño);
                System.out.println("Modelo: " + modelo);
                System.out.println("Estado: " + estado);
                System.out.println("ID Proveedor: " + proveedorId);
                System.out.println("Precio: " + precio);
                System.out.println("----------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
