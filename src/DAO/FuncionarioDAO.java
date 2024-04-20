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

import Modelos.Funcionario;

public class FuncionarioDAO {
    private Connection connection;

    // Constructor que recibe la conexión a la base de datos
    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para insertar un nuevo funcionario en la base de datos
    public boolean insertarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionario(carnet_fun, nombre_funcionario, sueldo, fecha_contratacion, " +
                     "numero_celular, correo_funcionario, contraseña) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, funcionario.getCarnetFuncionario());
            preparedStatement.setString(2, funcionario.getNombreFuncionario());
            preparedStatement.setInt(3, funcionario.getSueldo());
            preparedStatement.setDate(4, new java.sql.Date(funcionario.getFechaContratacion().getTime()));
            preparedStatement.setInt(5, funcionario.getNumeroCelular());
            preparedStatement.setString(6, funcionario.getCorreoFuncionario());
            preparedStatement.setString(7, funcionario.getContraseña());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un funcionario en la base de datos
    public boolean actualizarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE Funcionario SET nombre_funcionario=?, sueldo=?, fecha_contratacion=?, " +
                     "numero_celular=?, correo_funcionario=?, contraseña=? " +
                     "WHERE carnet_fun=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, funcionario.getNombreFuncionario());
            preparedStatement.setInt(2, funcionario.getSueldo());
            preparedStatement.setDate(3, new java.sql.Date(funcionario.getFechaContratacion().getTime()));
            preparedStatement.setInt(4, funcionario.getNumeroCelular());
            preparedStatement.setString(5, funcionario.getCorreoFuncionario());
            preparedStatement.setString(6, funcionario.getContraseña());
            preparedStatement.setInt(7, funcionario.getCarnetFuncionario());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un funcionario de la base de datos
    public boolean eliminarFuncionario(int carnetFuncionario) {
        String sql = "DELETE FROM Funcionario WHERE carnet_fun=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, carnetFuncionario);

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todos los funcionarios de la base de datos
    public List<Funcionario> obtenerTodosFuncionarios() {
        List<Funcionario> listaFuncionarios = new ArrayList<>();
        String sql = "SELECT * FROM Funcionario";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int carnetFuncionario = resultSet.getInt("carnet_fun");
                String nombreFuncionario = resultSet.getString("nombre_funcionario");
                int sueldo = resultSet.getInt("sueldo");
                Date fechaContratacion = resultSet.getDate("fecha_contratacion");
                int numeroCelular = resultSet.getInt("numero_celular");
                String correoFuncionario = resultSet.getString("correo_funcionario");
                String contraseña = resultSet.getString("contraseña");

                Funcionario funcionario = new Funcionario(carnetFuncionario, nombreFuncionario, sueldo,
                        fechaContratacion, numeroCelular, correoFuncionario, contraseña);
                listaFuncionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaFuncionarios;
    }
    public Funcionario obtenerFuncionarioPorCarnet(int carnetFuncionario) {
        String sql = "SELECT * FROM Funcionario WHERE carnet_fun=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, carnetFuncionario);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nombreFuncionario = resultSet.getString("nombre_funcionario");
                    int sueldo = resultSet.getInt("sueldo");
                    Date fechaContratacion = resultSet.getDate("fecha_contratacion");
                    int numeroCelular = resultSet.getInt("numero_celular");
                    String correoFuncionario = resultSet.getString("correo_funcionario");
                    String contraseña = resultSet.getString("contraseña");

                    return new Funcionario(carnetFuncionario, nombreFuncionario, sueldo,
                            fechaContratacion, numeroCelular, correoFuncionario, contraseña);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra ningún funcionario con el carnet especificado
    }
}

