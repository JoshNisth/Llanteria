package Interfaces_Graficas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import ConexionBD.Conexion;
import DAO.NeumaticoDAO;
import Modelos.Neumatico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.List;

public class VentanaActualizarStock extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField cantidadTextField;
    private NeumaticoDAO neumaticoDAO;
    private String proveedorSeleccionado;

    public VentanaActualizarStock(int carnetFuncionario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 410); // Reducí la altura de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

     // Crear un modelo de tabla
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Solo permite editar la columna "Cantidad a Agregar"
                return column == 2;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 2 ? Integer.class : super.getColumnClass(columnIndex);
            }
        };

        tableModel.addColumn("Modelo");
        tableModel.addColumn("Cantidad en stock");
        tableModel.addColumn("Cantidad a Agregar"); // Nueva columna

        // Crear la tabla con el modelo
        table = new JTable(tableModel);
        table.setRowHeight(30); // Ajustar la altura de las filas

        // Desactivar la edición en la columna "Cantidad en stock"
        table.getColumnModel().getColumn(1).setCellEditor(null);

        // Agregar la tabla a un JScrollPane para que sea desplazable
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Panel para los controles (etiqueta, campo de texto y botón)
        JPanel controlPanel = new JPanel();
        contentPane.add(controlPanel, BorderLayout.SOUTH);

        JButton btnActualizar = new JButton("Actualizar Stock");
        controlPanel.add(btnActualizar);
        
     // Crear botón para regresar
        JButton btnRegresar = new JButton("Regresar");
        controlPanel.add(btnRegresar);
        
     // Crear un JComboBox con las opciones de proveedores
        JComboBox<String> comboBoxProveedores = new JComboBox<>();
        comboBoxProveedores.addItem("Todos");
        comboBoxProveedores.addItem("Michelin");
        comboBoxProveedores.addItem("Bridgestone");
        comboBoxProveedores.addItem("Goodyear");
        // Crear un nuevo panel para el JComboBox
        JPanel comboPanel = new JPanel();
        comboPanel.add(new JLabel("Proveedor: "));
        comboPanel.add(comboBoxProveedores);

        // Agregar el panel con el JComboBox arriba de la tabla
        contentPane.add(comboPanel, BorderLayout.NORTH);
        
     // Variables para almacenar la selección del proveedor
        proveedorSeleccionado = "Todos";

        // Agregar un ActionListener al JComboBox para manejar los cambios de selección
     // Agregar un ActionListener al JComboBox para manejar los cambios de selección
        comboBoxProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualizar la variable de selección del proveedor
                proveedorSeleccionado = comboBoxProveedores.getSelectedItem().toString();

                // Filtrar la tabla según la selección del proveedor
                if (proveedorSeleccionado.equals("Todos")) {
                    // Mostrar todos los neumáticos
                    cargarDatosEnTabla();
                } else {
                    // Filtrar por proveedor
                    int proveedorId = obtenerIdProveedorPorNombre(proveedorSeleccionado);
                    cargarDatosPorProveedorEnTabla(proveedorId);
                }
            }
        });


        // Agregar evento al botón de regresar
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual
                dispose();

                // Abrir la VentanaPrincipal con el mismo carnetFuncionario
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(carnetFuncionario);
                ventanaPrincipal.setVisible(true);
            }
        });

        // Obtener una instancia del NeumaticoDAO usando la conexión a la base de datos
        Conexion conexion = new Conexion();
        Connection connection = conexion.getConexionPostgres();
        neumaticoDAO = new NeumaticoDAO(connection);

        // Cargar los datos de neumáticos en la tabla al inicio
        cargarDatosEnTabla();
     // Agregar un MouseListener a la tabla para detectar clics en la tercera columna
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());

                // Verificar si el clic fue en la tercera columna
                if (column == 2) {
                    // Limpiar el contenido de la celda al hacer clic
                    table.setValueAt(null, row, column);
                }
            }
        });
        // Agregar evento al botón de actualizar
     // Agregar un ActionListener al botón de actualizar
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Detener la edición de la celda actual
                if (table.isEditing()) {
                    TableCellEditor cellEditor = table.getCellEditor();
                    if (cellEditor != null) {
                        cellEditor.stopCellEditing();
                    }
                }

                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                int rowCount = tableModel.getRowCount();

                // Asegurarse de que todos los cambios en la edición se reflejen en el modelo de la tabla
                tableModel.fireTableDataChanged();

                // Iterar sobre todas las filas de la tabla
                for (int row = 0; row < rowCount; row++) {
                    String modelo = (String) table.getValueAt(row, 0); // Obtener el modelo de la primera columna

                    // Obtener la cantidad a agregar, si el campo está vacío, usar 0
                    int cantidadAgregar;
                    Object cantidadObject = table.getValueAt(row, 2);
                    if (cantidadObject == null || cantidadObject.toString().isEmpty()) {
                        cantidadAgregar = 0;
                    } else {
                        cantidadAgregar = Integer.parseInt(cantidadObject.toString());
                    }

                    // Lógica para actualizar el stock solo si la cantidad a agregar es diferente de 0
                    if (cantidadAgregar != 0) {
                        // Lógica para actualizar el stock utilizando el NeumaticoDAO
                        if (actualizarStock(modelo, cantidadAgregar)) {
                            // Si la actualización es exitosa, recargar los datos en la tabla
                            if (proveedorSeleccionado.equals("Todos")) {
                                // Mostrar todos los neumáticos
                                cargarDatosEnTabla();
                            } else {
                                // Filtrar por proveedor
                                int proveedorId = obtenerIdProveedorPorNombre(proveedorSeleccionado);
                                cargarDatosPorProveedorEnTabla(proveedorId);
                            }
                        } else {
                            JOptionPane.showMessageDialog(VentanaActualizarStock.this, "Error al actualizar el stock.");
                        }
                    }
                }
            }
        });

    }

    // Método para actualizar el stock utilizando el NeumaticoDAO
    private boolean actualizarStock(String modelo, int cantidad) {
        // Obtener el ID del neumático a partir del modelo
        int neumaticoId = obtenerNeumaticoIdPorModelo(modelo);
        // Llamar al método DAO para restar la cantidad de stock
        return neumaticoDAO.sumarCantidadStock(neumaticoId, cantidad);
    }

    // Método para cargar los datos de neumáticos en la tabla
    private void cargarDatosEnTabla() {
        // Obtener todos los neumáticos
        List<Neumatico> listaNeumaticos = neumaticoDAO.obtenerTodosNeumaticosOrdenadosPorId();

        // Limpiar el modelo de la tabla
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        // Llenar la tabla con los datos de los neumáticos
        for (Neumatico neumatico : listaNeumaticos) {
            Object[] rowData = {neumatico.getModelo(), neumatico.getCantidadStock(), 0};
            tableModel.addRow(rowData);
        }
    }

    // Método para obtener el ID de un neumático a partir de su modelo
    private int obtenerNeumaticoIdPorModelo(String modelo) {
        // Llamar al método DAO para obtener el ID por modelo
        return neumaticoDAO.obtenerIdPorModelo(modelo);
    }
    
 // Método para obtener el ID de proveedor por nombre
    private int obtenerIdProveedorPorNombre(String nombreProveedor) {
        // Implementa la lógica para obtener el ID del proveedor por su nombre
        // Aquí debes tener un array o lista con los nombres de proveedores y sus IDs
        // En este ejemplo, asumiré que los proveedores son "Michelin", "Bridgestone" y "Goodyear"
        if (nombreProveedor.equals("Michelin")) {
            return 1;
        } else if (nombreProveedor.equals("Bridgestone")) {
            return 2;
        } else if (nombreProveedor.equals("Goodyear")) {
            return 3;
        }
        return 0; // Retorna 0 si no se encuentra ningún proveedor con el nombre especificado
    }
    
    // Método para cargar los datos de neumáticos en la tabla según el proveedor
    private void cargarDatosPorProveedorEnTabla(int proveedorId) {
        // Obtener los neumáticos por proveedor
        List<Neumatico> listaNeumaticos = neumaticoDAO.obtenerNeumaticosPorProveedor(proveedorId);

        // Limpiar el modelo de la tabla
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        // Llenar la tabla con los datos de los neumáticos
        for (Neumatico neumatico : listaNeumaticos) {
            Object[] rowData = {neumatico.getModelo(), neumatico.getCantidadStock(), 0};
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaActualizarStock frame = new VentanaActualizarStock(109010);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
