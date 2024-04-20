package Interfaces_Graficas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ConexionBD.Conexion;
import DAO.NeumaticoDAO;
import Modelos.Neumatico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class VentanaStock extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaNeumaticos;
    private DefaultTableModel modeloTabla;

    public VentanaStock(int carnetFuncionario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        // Crear el modelo de la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID Neumático");
        modeloTabla.addColumn("Tamaño");
        modeloTabla.addColumn("Modelo");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("ID Proveedor");
        modeloTabla.addColumn("Cantidad Stock");
        modeloTabla.addColumn("Precio");

     // Crear la JTable con el modelo
        tablaNeumaticos = new JTable(modeloTabla);

        // Ajustar el ancho de la columna de modelo
        tablaNeumaticos.getColumnModel().getColumn(1).setPreferredWidth(40);
        tablaNeumaticos.getColumnModel().getColumn(2).setPreferredWidth(200);
        tablaNeumaticos.getColumnModel().getColumn(3).setPreferredWidth(60);
        // Agregar la JTable a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaNeumaticos);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Agregar un JScrollBar vertical al JScrollPane
        JScrollPane scroll = new JScrollPane(tablaNeumaticos, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contentPane.add(scroll);

        // Llamada al método para cargar los neumáticos
        cargarNeumaticos();
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                regresarAVentanaPrincipal(carnetFuncionario);
            }
        });
        contentPane.add(btnRegresar, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }
    
 // Método para regresar a VentanaPrincipal
    private void regresarAVentanaPrincipal(int carnetFuncionario) {
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(carnetFuncionario);
        ventanaPrincipal.setVisible(true);
        dispose();  // Cierra la ventana actual
    }

 // Método para cargar los neumáticos en la tabla
    private void cargarNeumaticos() {
        // Obtener los neumáticos desde el DAO
        Conexion conexion = new Conexion();
        Connection connection = conexion.getConexionPostgres();
        if (connection != null) {
            NeumaticoDAO neumaticoDAO = new NeumaticoDAO(connection);
            List<Neumatico> listaNeumaticos = neumaticoDAO.obtenerTodosNeumaticosOrdenadosPorId();

            // Limpiar el modelo de la tabla
            modeloTabla.setRowCount(0);

            // Llenar la tabla con los primeros 10 neumáticos
            int contadorFilas = 0;
            for (Neumatico neumatico : listaNeumaticos) {
                if (contadorFilas < 10) {
                    Object[] fila = {
                            neumatico.getNeumaticoId(),
                            neumatico.getTamaño(),
                            neumatico.getModelo(),
                            neumatico.isEstado() ? "En Stock" : "Sin Stock",
                            neumatico.getProveedorId(),
                            neumatico.getCantidadStock(),
                            neumatico.getPrecio()
                    };
                    modeloTabla.addRow(fila);
                    contadorFilas++;
                } else {
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaStock frame = new VentanaStock(109010);
                frame.setVisible(true);
            }
        });
    }
}
