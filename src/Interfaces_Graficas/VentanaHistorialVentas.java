package Interfaces_Graficas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import ConexionBD.Conexion;
import DAO.VentaDAO;
import Modelos.Venta;

public class VentanaHistorialVentas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaVentas;
    private DefaultTableModel modeloTabla;

    public VentanaHistorialVentas(int carnetFuncionario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        // Crear el modelo de la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID Venta");
        modeloTabla.addColumn("Fecha Venta");
        modeloTabla.addColumn("Monto Total");
        modeloTabla.addColumn("Carnet Funcionario");
        modeloTabla.addColumn("Carnet Cliente");

        // Crear la JTable con el modelo
        tablaVentas = new JTable(modeloTabla);

        // Agregar la JTable a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaVentas);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Agregar un JScrollBar vertical al JScrollPane
        JScrollPane scroll = new JScrollPane(tablaVentas, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contentPane.add(scroll);

        // Agregar un botón "Regresar" debajo de la JTable
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAVentanaPrincipal(carnetFuncionario);
            }
        });
        contentPane.add(btnRegresar, BorderLayout.SOUTH);

        // Llamada al método para cargar las ventas
        cargarVentas();

        setLocationRelativeTo(null);
    }

    // Método para cargar las ventas en la tabla
    private void cargarVentas() {
        // Obtener las ventas desde el DAO
        Conexion conexion = new Conexion();
        Connection connection = conexion.getConexionPostgres();
        if (connection != null) {
            VentaDAO ventaDAO = new VentaDAO(connection);
            List<Venta> listaVentas = ventaDAO.obtenerTodasVentas();

            // Limpiar el modelo de la tabla
            modeloTabla.setRowCount(0);

            // Llenar la tabla con las ventas
            for (Venta venta : listaVentas) {
                Object[] fila = {
                        venta.getVentaId(),
                        venta.getFechaVenta(),
                        venta.getMontoTotal(),
                        venta.getCarnetFuncionario(),
                        venta.getCarnetCliente()
                };
                modeloTabla.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos");
        }
    }

    // Método para regresar a la VentanaPrincipal con el carnet del funcionario
    private void regresarAVentanaPrincipal(int carnetFuncionario) {
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(carnetFuncionario);
        ventanaPrincipal.setVisible(true);
        dispose();  // Cierra la ventana actual
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaHistorialVentas frame = new VentanaHistorialVentas(109010);
                frame.setVisible(true);
            }
        });
    }
}
