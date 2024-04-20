package Interfaces_Graficas;

import java.awt.EventQueue;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.util.List;
import DAO.VentaDAO;
import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.NeumaticoDAO;
import Modelos.Factura;
import Modelos.Venta;
import ConexionBD.Conexion;

public class VentanaFactura extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableFacturas;
    private DefaultTableModel modelFacturas;
    private NeumaticoDAO neumaticoDAO;
    private VentaDAO ventaDAO;
    private FacturaDAO facturaDAO;
    private JScrollPane scrollPane;  
    private JLabel lblTotalVenta;
    private int carnetFuncionario;
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaFactura frame = new VentanaFactura(7);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public VentanaFactura(int idVenta) {
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.getConexionPostgres()) {
            this.neumaticoDAO = new NeumaticoDAO(conn);
            this.ventaDAO = new VentaDAO(conn);
            this.facturaDAO = new FacturaDAO(conn);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 600,550);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setTitle("Ventana de factura");
            setSize(600, 600);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setContentPane(contentPane);
            contentPane.setLayout(null);

            JLabel lblSocoser = new JLabel("SOCOSER");
            lblSocoser.setFont(new Font("Tahoma", Font.BOLD, 25));
            lblSocoser.setBounds(226, 11, 150, 30);
            contentPane.add(lblSocoser);

            JLabel lblVentaNumero = new JLabel("VENTA NUMERO: " + idVenta);
            lblVentaNumero.setBounds(197, 80, 160, 14);
            contentPane.add(lblVentaNumero);

            if (conn != null) {
                try {
                    // Obtener datos de la venta
                    VentaDAO ventaDAO = new VentaDAO(conn);
                    Venta venta = ventaDAO.obtenerVentaPorId(idVenta);

                    if (venta != null) {
                        // Obtener la fecha de la venta
                        Date fechaVenta = venta.getFechaVenta();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String fechaFormateada = dateFormat.format(fechaVenta);
                    	carnetFuncionario = ventaDAO.obtenerCarnetFunlPorId(idVenta);

                        JLabel lblVentaFecha = new JLabel("FECHA: " + fechaFormateada);
                        lblVentaFecha.setBounds(197, 105, 160, 14);
                        contentPane.add(lblVentaFecha);

                        // Obtener el nombre del cliente
                        int ciCliente = venta.getCarnetCliente();
                        ClienteDAO clienteDAO = new ClienteDAO(conn);
                        String nombreCliente = clienteDAO.obtenerNombreClientePorCI(ciCliente);
                        JLabel lblNombreCliente = new JLabel("SEÑOR(A): " + nombreCliente);
                        lblNombreCliente.setBounds(197, 130, 160, 14);
                        contentPane.add(lblNombreCliente);

                        // Obtener el CI del cliente
                        JLabel lblCarnetCliente = new JLabel("CI: " + ciCliente);
                        lblCarnetCliente.setBounds(197, 155, 160, 14);
                        contentPane.add(lblCarnetCliente);
                        
                        lblTotalVenta = new JLabel("New label");
                        lblTotalVenta.setBounds(197, 421, 250, 20);
                        lblTotalVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
                        contentPane.add(lblTotalVenta);
                        double totalVenta = ventaDAO.obtenerMontoTotalPorId(idVenta);
                        lblTotalVenta.setText("TOTAL DE VENTA Bs: " + totalVenta);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Crear la tabla de facturas
            String[] columnas = {"DESCRIPCIÓN", "CANTIDAD", "P/U", "PRECIO"};
            modelFacturas = new DefaultTableModel(columnas, 0);
            tableFacturas = new JTable(modelFacturas);

            scrollPane = new JScrollPane(tableFacturas);  // Inicializar scrollPane aquí
            scrollPane.setBounds(10, 200, 560, 200);
            contentPane.add(scrollPane);
            
            JButton btnRegresarMenu = new JButton("Regresar al menu");
            btnRegresarMenu.setBounds(400, 500, 150, 23);
            contentPane.add(btnRegresarMenu);
            
            btnRegresarMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Verificar el valor de carnetFuncionario
                    System.out.println("Carnet del Funcionario: " + carnetFuncionario);

                    // Abrir la VentanaPrincipal con el carnetFuncionario obtenido
                    VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(carnetFuncionario);
                    ventanaPrincipal.setVisible(true);

                    // Cerrar la ventana actual
                    dispose();
                }
            });
            
            
            // Llenar la tabla con las facturas
            llenarTablaFacturas(idVenta);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    private void llenarTablaFacturas(int idVenta) {
        // Limpiar la tabla antes de agregar nuevas filas
        modelFacturas.setRowCount(0);

        // Obtener las facturas por el idVenta
        List<Factura> facturas = facturaDAO.obtenerFacturasPorIdVenta(idVenta);

        // Iterar sobre las facturas y llenar la tabla
        for (Factura factura : facturas) {
            int neumaticoId = factura.getNeumaticoId();
            String descripcion = neumaticoDAO.obtenerModeloPorId(neumaticoId);
            int cantidad = factura.getCantidad();
            double precioUnitario = factura.getPrecioUnitario();
            double precioTotal = cantidad * precioUnitario;

            // Agregar una fila a la tabla
            Object[] fila = {descripcion, cantidad, precioUnitario, precioTotal};
            modelFacturas.addRow(fila);
        }


        // Ajustar el ancho de las columnas
        TableColumnModel columnModel = tableFacturas.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(250); // Ancho de la columna "Descripción"
        columnModel.getColumn(1).setPreferredWidth(50);  // Ancho de la columna "Cantidad"
        
        
    }

}
