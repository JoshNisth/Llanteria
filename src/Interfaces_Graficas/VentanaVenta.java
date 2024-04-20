package Interfaces_Graficas;

import javax.swing.*;

import ConexionBD.Conexion;
import DAO.ClienteDAO;
import DAO.FacturaDAO;
import DAO.VentaDAO;
import DAO.FuncionarioDAO;
import Modelos.Factura;
import Modelos.Neumatico;
import Modelos.Venta;
import DAO.NeumaticoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.awt.Font;

public class VentanaVenta extends JFrame {
    private int carnetFuncionario;
    private int carnetCliente; 
    private NeumaticoDAO neumaticoDAO;
    private VentaDAO ventaDAO;
    private FacturaDAO facturaDAO;
 // Declaración de JLabels
    private JLabel txtCarnetCliente;
    private JLabel lblModelo1;
    private JLabel lblModelo2;
    private JLabel lblModelo3;
    private JLabel lblModelo4;
    private JLabel lblModelo5;
    private JLabel lblModelo6;
    private JLabel lblModelo7;
    private JLabel lblModelo8;
    private JLabel lblModelo9;
    private JLabel lblModelo10;
    private JComboBox<Integer> comboBoxCantidad1;
    private JComboBox<Integer> comboBoxCantidad2;
    private JComboBox<Integer> comboBoxCantidad3;
    private JComboBox<Integer> comboBoxCantidad4;
    private JComboBox<Integer> comboBoxCantidad5;
    private JComboBox<Integer> comboBoxCantidad6;
    private JComboBox<Integer> comboBoxCantidad7;
    private JComboBox<Integer> comboBoxCantidad8;
    private JComboBox<Integer> comboBoxCantidad9;
    private JComboBox<Integer> comboBoxCantidad10;
    private List<JComboBox<Integer>> comboBoxCantidad = new ArrayList<>();
    //prueba
    private JButton btnRegistrarCliente;
    private ClienteDAO clienteDAO;
    private JButton btnRegresar;
    
    //lista de totales
    private List<Double> subTotales = Arrays.asList(0.0, 0.0 , 0.0 ,0.0, 0.0, 0.0 , 0.0, 0.0, 0.0, 0.0 );
    private JTextField txtTotal;
    //AGREGAR PARA EL LABEL DE TOTAL
    public VentanaVenta(int carnetFuncionario) {
        this.carnetFuncionario = carnetFuncionario;
        Conexion conexion = new Conexion();
        Connection connection = conexion.getConexionPostgres();
        this.neumaticoDAO = new NeumaticoDAO(connection);
        this.ventaDAO = new VentaDAO(connection);  // Inicializa la variable ventaDAO
        this.facturaDAO = new FacturaDAO(connection); 
        // Configuración de la ventana
        setTitle("Ventana de Venta");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Cierra solo la ventana actual al hacer clic en "Cerrar"
        setLocationRelativeTo(null);

        // Creación de componentes
        JLabel etiquetaCarnet = new JLabel("Carnet del cliente: ");
       etiquetaCarnet.setFont(new Font("Tahoma", Font.PLAIN, 14));
        etiquetaCarnet.setBounds(650, 550, 250, 30);
        
     // Agregar un JLabel para mostrar el carnet del cliente
        JLabel lblCarnetCliente = new JLabel("Carnet del funcionario: "  + carnetFuncionario);
        lblCarnetCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCarnetCliente.setBounds(22, 21, 200, 20);
        getContentPane().add(lblCarnetCliente);

        // Configuración de eventos
        JButton btnRealizarVenta = new JButton("Realizar Venta");
        btnRealizarVenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRealizarVenta.setBounds(950, 600, 150, 30);
        getContentPane().setLayout(null);
        
        JButton btnRegistrarCliente = new JButton("Registrar Cliente");
        btnRegistrarCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRegistrarCliente.setBounds(750, 600, 150, 30);
        getContentPane().add(btnRegistrarCliente);
        getContentPane().add(etiquetaCarnet);
        getContentPane().add(btnRealizarVenta);
        
        JLabel lblFoto1 = new JLabel("");
        lblFoto1.setIcon(new ImageIcon(VentanaVenta.class.getResource("/CatalogoFotos/neumatico1.png")));
        lblFoto1.setBounds(22, 52, 150, 129);
        getContentPane().add(lblFoto1);
        rsscalelabel.RSScaleLabel.setScaleLabel(lblFoto1,"src/CatalogoFotos/neumatico1.png" );
        
        JLabel lblFoto2 = new JLabel("");
        lblFoto2.setIcon(new ImageIcon(VentanaVenta.class.getResource("/CatalogoFotos/neumatico2.png")));
        lblFoto2.setBounds(220, 52, 150, 129);
        getContentPane().add(lblFoto2);
        rsscalelabel.RSScaleLabel.setScaleLabel(lblFoto2,"src/CatalogoFotos/neumatico2.png" );
        
        JLabel lblFoto3 = new JLabel("");
        lblFoto3.setIcon(new ImageIcon(VentanaVenta.class.getResource("/CatalogoFotos/neumatico3.png")));
        lblFoto3.setBounds(455, 52, 150, 129);
        getContentPane().add(lblFoto3);
        rsscalelabel.RSScaleLabel.setScaleLabel(lblFoto3,"src/CatalogoFotos/neumatico3.png" );
        
        JLabel lblFoto4 = new JLabel("");
        lblFoto4.setIcon(new ImageIcon(VentanaVenta.class.getResource("/CatalogoFotos/neumatico4.png")));
        lblFoto4.setBounds(725, 52, 150, 129);
        getContentPane().add(lblFoto4);
        rsscalelabel.RSScaleLabel.setScaleLabel(lblFoto4,"src/CatalogoFotos/neumatico4.png" );
        
        JLabel lblFoto5 = new JLabel("");
        lblFoto5.setIcon(new ImageIcon(VentanaVenta.class.getResource("/CatalogoFotos/neumatico5.png")));
        lblFoto5.setBounds(950, 52, 150, 129);
        getContentPane().add(lblFoto5);
        rsscalelabel.RSScaleLabel.setScaleLabel(lblFoto5,"src/CatalogoFotos/neumatico5.png" );
        
        JLabel lblFoto6 = new JLabel("");
        lblFoto6.setIcon(new ImageIcon(VentanaVenta.class.getResource("/CatalogoFotos/neumatico6.png")));
        lblFoto6.setBounds(22, 310, 150, 129);
        getContentPane().add(lblFoto6);
        rsscalelabel.RSScaleLabel.setScaleLabel(lblFoto6,"src/CatalogoFotos/neumatico6.png" );
        
        JLabel lblFoto7 = new JLabel("");
        lblFoto7.setIcon(new ImageIcon(VentanaVenta.class.getResource("/CatalogoFotos/neumatico7.png")));
        lblFoto7.setBounds(250, 310, 150, 129);
        getContentPane().add(lblFoto7);
        rsscalelabel.RSScaleLabel.setScaleLabel(lblFoto7,"src/CatalogoFotos/neumatico7.png" );
        
        JLabel lblFoto8 = new JLabel("");
        lblFoto8.setIcon(new ImageIcon(VentanaVenta.class.getResource("/CatalogoFotos/neumatico8.png")));
        lblFoto8.setBounds(455, 310, 150, 129);
        getContentPane().add(lblFoto8);
        rsscalelabel.RSScaleLabel.setScaleLabel(lblFoto8,"src/CatalogoFotos/neumatico8.png" );
        
        JLabel lblFoto9 = new JLabel("");
        lblFoto9.setIcon(new ImageIcon(VentanaVenta.class.getResource("/CatalogoFotos/neumatico9.png")));
        lblFoto9.setBounds(725, 310, 150, 129);
        getContentPane().add(lblFoto9);
        rsscalelabel.RSScaleLabel.setScaleLabel(lblFoto9,"src/CatalogoFotos/neumatico9.png" );
        
        JLabel lblFoto10 = new JLabel("");
        lblFoto10.setIcon(new ImageIcon(VentanaVenta.class.getResource("/CatalogoFotos/neumatico10.png")));
        lblFoto10.setBounds(950, 310, 150, 129);
        getContentPane().add(lblFoto10);
        rsscalelabel.RSScaleLabel.setScaleLabel(lblFoto10,"src/CatalogoFotos/neumatico10.png" );
        
        //LBL PARA MODELO DE LLANTAS
        JLabel lblModelo1 = new JLabel("New label");
        lblModelo1.setBounds(25, 192, 150, 14);
        getContentPane().add(lblModelo1);
        //lblModelo2.setText(neumaticoDAO.obtenerModeloPorId(1));
        lblModelo1.setText("Michelin Defender T+H");
        
        JLabel lblModelo2 = new JLabel("New label");
        lblModelo2.setBounds(215, 192, 180, 14);
        getContentPane().add(lblModelo2);
        lblModelo2.setText(neumaticoDAO.obtenerModeloPorId(2));
        
        JLabel lblModelo3 = new JLabel("New label");
        lblModelo3.setBounds(430, 192, 260, 14);
        getContentPane().add(lblModelo3);
        lblModelo3.setText(neumaticoDAO.obtenerModeloPorId(3));
        
        JLabel lblModelo4 = new JLabel("New label");
        lblModelo4.setBounds(750, 192, 140, 14);
        getContentPane().add(lblModelo4);
        lblModelo4.setText(neumaticoDAO.obtenerModeloPorId(4));
        
        JLabel lblModelo5 = new JLabel("New label");
        lblModelo5.setBounds(950, 192, 180, 14);
        getContentPane().add(lblModelo5);
        lblModelo5.setText(neumaticoDAO.obtenerModeloPorId(5));
        
        JLabel lblModelo6 = new JLabel("New label");
        lblModelo6.setBounds(25, 450, 200, 14);
        getContentPane().add(lblModelo6);
        lblModelo6.setText(neumaticoDAO.obtenerModeloPorId(6));
        
        JLabel lblModelo7 = new JLabel("New label");
        lblModelo7.setBounds(270, 450, 110, 14);
        getContentPane().add(lblModelo7);
        lblModelo7.setText(neumaticoDAO.obtenerModeloPorId(7));
        
        JLabel lblModelo8 = new JLabel("New label");
        lblModelo8.setBounds(450, 450, 180, 14);
        getContentPane().add(lblModelo8);
        lblModelo8.setText(neumaticoDAO.obtenerModeloPorId(8));
        
        JLabel lblModelo9 = new JLabel("New label");
        lblModelo9.setBounds(690, 450, 210, 14);
        getContentPane().add(lblModelo9);
        lblModelo9.setText(neumaticoDAO.obtenerModeloPorId(9));
        
        JLabel lblModelo10 = new JLabel("New label");
        lblModelo10.setBounds(950, 450, 150, 14);
        getContentPane().add(lblModelo10);
        lblModelo10.setText(neumaticoDAO.obtenerModeloPorId(10));
        //precios
        JLabel lblPrecio1 = new JLabel("New label");
        lblPrecio1.setBounds(22, 217, 100, 14);
        getContentPane().add(lblPrecio1);
        lblPrecio1.setText("Precio 150Bs");
        
        JLabel lblPrecio2 = new JLabel("Precio 180Bs");
        lblPrecio2.setBounds(215, 217, 100, 14);
        getContentPane().add(lblPrecio2);
        
        JLabel lblPrecio3 = new JLabel("Precio 200Bs");
        lblPrecio3.setBounds(430, 217, 100, 14);
        getContentPane().add(lblPrecio3);
        
        JLabel lblPrecio4 = new JLabel("Precio 200Bs");
        lblPrecio4.setBounds(725, 217, 100, 14);
        getContentPane().add(lblPrecio4);
        
        JLabel lblPrecio5 = new JLabel("Precio 180Bs");
        lblPrecio5.setBounds(950, 217, 100, 14);
        getContentPane().add(lblPrecio5);
        
        JLabel lblPrecio6 = new JLabel("Precio 220Bs");
        lblPrecio6.setBounds(22, 475, 100, 14);
        getContentPane().add(lblPrecio6);
        
        JLabel lblPrecio7 = new JLabel("Precio 190Bs");
        lblPrecio7.setBounds(245, 475, 100, 14);
        getContentPane().add(lblPrecio7);
        
        JLabel lblPrecio8 = new JLabel("Precio 210Bs");
        lblPrecio8.setBounds(430, 475, 100, 14);
        getContentPane().add(lblPrecio8);
        
        JLabel lblPrecio9 = new JLabel("Precio 170Bs");
        lblPrecio9.setBounds(725, 475, 100, 14);
        getContentPane().add(lblPrecio9);
        
        JLabel lblPrecio10 = new JLabel("Precio 240Bs");
        lblPrecio10.setBounds(950, 475, 100, 14);
        getContentPane().add(lblPrecio10);
        //COMBOBOXES
        JComboBox <Integer> comboBoxCantidad1 = new JComboBox();
        for(int i=0;i<=10;i++) {
        	comboBoxCantidad1.addItem(i);
        }
        comboBoxCantidad1.setBounds(132, 213, 45, 22);
        getContentPane().add(comboBoxCantidad1);
        
        JComboBox <Integer> comboBoxCantidad2 = new JComboBox();
        for(int i=0;i<=10;i++) {
        	comboBoxCantidad2.addItem(i);
        }
        comboBoxCantidad2.setBounds(325, 213, 45, 22);
        getContentPane().add(comboBoxCantidad2);
        
        JComboBox <Integer>  comboBoxCantidad3 = new JComboBox();
        for(int i=0;i<=10;i++) {
        	comboBoxCantidad3.addItem(i);
        }
        comboBoxCantidad3.setBounds(560, 213, 45, 22);
        getContentPane().add(comboBoxCantidad3);
        
        JComboBox <Integer>  comboBoxCantidad4 = new JComboBox();
        for(int i=0;i<=10;i++) {
        	comboBoxCantidad4.addItem(i);
        }
        comboBoxCantidad4.setBounds(830, 217, 45, 22);
        getContentPane().add(comboBoxCantidad4);
        
        JComboBox <Integer>  comboBoxCantidad5 = new JComboBox();
        for(int i=0;i<=10;i++) {
        	comboBoxCantidad5.addItem(i);
        }
        comboBoxCantidad5.setBounds(1055, 217, 45, 22);
        getContentPane().add(comboBoxCantidad5);
        
        JComboBox<Integer>  comboBoxCantidad6 = new JComboBox();
        for(int i=0;i<=10;i++) {
        	comboBoxCantidad6.addItem(i);
        }
        comboBoxCantidad6.setBounds(127, 475, 45, 22);
        getContentPane().add(comboBoxCantidad6);
        
        JComboBox<Integer>  comboBoxCantidad7 = new JComboBox();
        for(int i=0;i<=10;i++) {
        	comboBoxCantidad7.addItem(i);
        }
        comboBoxCantidad7.setBounds(355, 475, 45, 22);
        getContentPane().add(comboBoxCantidad7);
        
        JComboBox <Integer> comboBoxCantidad8 = new JComboBox();
        for(int i=0;i<=10;i++) {
        	comboBoxCantidad8.addItem(i);
        }
        comboBoxCantidad8.setBounds(560, 475, 45, 22);
        getContentPane().add(comboBoxCantidad8);
        
        JComboBox<Integer>  comboBoxCantidad9 = new JComboBox();
        for(int i=0;i<=10;i++) {
        	comboBoxCantidad9.addItem(i);
        }
        comboBoxCantidad9.setBounds(830, 475, 45, 22);
        getContentPane().add(comboBoxCantidad9);
        
        JComboBox <Integer> comboBoxCantidad10 = new JComboBox();
        for(int i=0;i<=10;i++) {
        	comboBoxCantidad10.addItem(i);
        }
        comboBoxCantidad10.setBounds(1055, 471, 45, 22);
        getContentPane().add(comboBoxCantidad10);
        //agrego al vector
        comboBoxCantidad.add(comboBoxCantidad1);
        comboBoxCantidad.add(comboBoxCantidad2);
        comboBoxCantidad.add(comboBoxCantidad3);
        comboBoxCantidad.add(comboBoxCantidad4);
        comboBoxCantidad.add(comboBoxCantidad5);
        comboBoxCantidad.add(comboBoxCantidad6);
        comboBoxCantidad.add(comboBoxCantidad7);
        comboBoxCantidad.add(comboBoxCantidad8);
        comboBoxCantidad.add(comboBoxCantidad9);
        comboBoxCantidad.add(comboBoxCantidad10);
        
        txtTotal = new JTextField();
        txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 17));
        txtTotal.setBounds(951, 550, 200, 30);
        txtTotal.setBorder(null);
        txtTotal.setOpaque(false);
        getContentPane().add(txtTotal);
        txtTotal.setColumns(10);
        
     // Creación del botón btnRegresar
        btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnRegresar.setBounds(50, 600, 150, 30);
        getContentPane().add(btnRegresar);

        // Agregar ActionListener al botón "Regresar"
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para regresar a la VentanaPrincipal
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(carnetFuncionario);
                ventanaPrincipal.setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });
        
        //PARA LOS BOTONES
     // Agregar ActionListener al botón "Registrar Cliente"
        btnRegistrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de registro de cliente al hacer clic en "Registrar Cliente"
                VentanaRegistroCliente ventanaRegistroCliente = new VentanaRegistroCliente(connection);
                ventanaRegistroCliente.setVisible(true);

                // Agregar el listener para recibir el carnet del cliente registrado
                ventanaRegistroCliente.setRegistrarClienteListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtener el carnet del cliente registrado
                        carnetCliente = ventanaRegistroCliente.getCarnetCliente();  // Usar la variable de instancia
                        // Hacer algo con el carnet del cliente, como mostrarlo en un JLabel
                        // Ejemplo:
                        etiquetaCarnet.setText("Carnet del Cliente: " + carnetCliente);
                    }
                });
            }
        });
        getContentPane().add(btnRegistrarCliente);
        
        
        comboBoxCantidad1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = 150;
                int cantidadSeleccionada = (int)(comboBoxCantidad1.getSelectedItem());
                subTotales.set(0, precioProducto * cantidadSeleccionada);
                calcularTotal(subTotales);
            }
        });
        comboBoxCantidad2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = 180;
                int cantidadSeleccionada = (int)(comboBoxCantidad2.getSelectedItem());
                subTotales.set(1, precioProducto * cantidadSeleccionada);
                calcularTotal(subTotales);
            }
        });
        comboBoxCantidad3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = 200;
                int cantidadSeleccionada = (int)(comboBoxCantidad3.getSelectedItem());
                subTotales.set(2, precioProducto * cantidadSeleccionada);
                calcularTotal(subTotales);
            }
        });
        comboBoxCantidad4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = 200;
                int cantidadSeleccionada = (int)(comboBoxCantidad4.getSelectedItem());
                subTotales.set(3, precioProducto * cantidadSeleccionada);
                calcularTotal(subTotales);
            }
        });
        comboBoxCantidad5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = 180;
                int cantidadSeleccionada = (int)(comboBoxCantidad5.getSelectedItem());
                subTotales.set(4, precioProducto * cantidadSeleccionada);
                calcularTotal(subTotales);
            }
        });
        comboBoxCantidad6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = 220;
                int cantidadSeleccionada = (int)(comboBoxCantidad6.getSelectedItem());
                subTotales.set(5, precioProducto * cantidadSeleccionada);
                calcularTotal(subTotales);
            }
        });
        comboBoxCantidad7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = 190;
                int cantidadSeleccionada = (int)(comboBoxCantidad7.getSelectedItem());
                subTotales.set(6, precioProducto * cantidadSeleccionada);
                calcularTotal(subTotales);
            }
        });
        comboBoxCantidad8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = 210;
                int cantidadSeleccionada = (int)(comboBoxCantidad8.getSelectedItem());
                subTotales.set(7, precioProducto * cantidadSeleccionada);
                calcularTotal(subTotales);
            }
        });
        comboBoxCantidad9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = 170;
                int cantidadSeleccionada = (int)(comboBoxCantidad9.getSelectedItem());
                subTotales.set(8, precioProducto * cantidadSeleccionada);
                calcularTotal(subTotales);
            }
        });
        comboBoxCantidad10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double precioProducto = 240;
                int cantidadSeleccionada = (int)(comboBoxCantidad10.getSelectedItem());
                subTotales.set(9, precioProducto * cantidadSeleccionada);
                calcularTotal(subTotales);
            }
        });

        //PRUEBA FACTURA
        btnRealizarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Lógica para verificar el stock antes de la venta
                for (int i = 0; i < subTotales.size(); i++) {
                    int cantidadSeleccionada = (int) comboBoxCantidad.get(i).getSelectedItem();
                    int idNeumatico = i + 1;

                    // Obtener la cantidad en stock del neumático desde el DAO
                    int cantidadStock = neumaticoDAO.obtenerCantidadStock(idNeumatico);

                    // Verificar si la cantidad seleccionada es mayor que el stock disponible
                    if (cantidadSeleccionada > cantidadStock) {
                        JOptionPane.showMessageDialog(VentanaVenta.this, "Neumático con insuficiente stock.");
                        return; // Salir del ActionListener si hay insuficiente stock
                    }
                }
            	
                // Lógica para registrar la venta
                double montoTotal = calcularTotalBoton(subTotales);

                // Verificar si se ha registrado el carnet del cliente
                if (carnetCliente == 0) {
                    JOptionPane.showMessageDialog(VentanaVenta.this, "Por favor, registre al cliente primero.");
                    return;
                }
                if (montoTotal == 0) {
                    JOptionPane.showMessageDialog(VentanaVenta.this, "Por favor, seleccione algún producto.");
                    return;
                }

                // Obtener el nuevo ID de venta
                int nuevoIdVenta = ventaDAO.obtenerMaximoIdVenta() + 1;

                // Obtener la fecha actual
                Date fecha = new Date();

                // Crear y registrar la nueva venta
                Venta nuevaVenta = new Venta(nuevoIdVenta, fecha, montoTotal, carnetFuncionario, carnetCliente);
                if (!ventaDAO.insertarVenta(nuevaVenta)) {
                    JOptionPane.showMessageDialog(VentanaVenta.this, "Error al registrar la venta.");
                    return;
                }

                JOptionPane.showMessageDialog(VentanaVenta.this, "Venta registrada exitosamente.");

                // Obtener el ID de la venta recién registrada
                int idVenta = ventaDAO.obtenerMaximoIdVenta();

                // Lógica para registrar las facturas
                for (int i = 0; i < subTotales.size(); i++) {
                    double subtotal = subTotales.get(i);
                    int cantidadSeleccionada = (int) comboBoxCantidad.get(i).getSelectedItem();

                    // Si la cantidad seleccionada es mayor que 0, proceder a registrar la factura
                    if (cantidadSeleccionada > 0) {
                        // Obtener el ID del neumático (suponiendo que la posición en el vector representa el ID)
                        int idNeumatico = i + 1;

                        // Obtener el precio unitario del neumático desde el DAO
                        double precioUnitario = neumaticoDAO.obtenerPrecioNeumatico(idNeumatico);
                        boolean resta=neumaticoDAO.restarCantidadStock(idNeumatico, cantidadSeleccionada);
                        boolean estado=neumaticoDAO.actualizarEstadoPorCantidadStock(idNeumatico);
                        // Calcular el nuevo ID de factura
                        int nuevoIdFactura = facturaDAO.obtenerMaximoIdFactura() + 1;

                        // Crear y registrar la nueva factura
                        Factura nuevaFactura = new Factura(nuevoIdFactura, idVenta, idNeumatico, cantidadSeleccionada, precioUnitario);
                        if (!facturaDAO.insertarFactura(nuevaFactura)) {
                            JOptionPane.showMessageDialog(VentanaVenta.this, "Error al registrar la factura.");
                            return;
                        }
                    }
                }

                // Si llegamos aquí, todas las facturas se registraron exitosamente
                JOptionPane.showMessageDialog(VentanaVenta.this, "Facturas registradas exitosamente.");

                // Abrir la ventana de factura
                VentanaFactura ventanaFactura = new VentanaFactura(idVenta);
                ventanaFactura.setVisible(true);
                dispose();
            }
        });

    }

    private void calcularTotal(List<Double> subTotales) {
        double total = 0;
        for (int i =0;i<10;i++) {
            total = subTotales.get(i)+total;
        }
        total = Math.round(total * 100.0) / 100.0;
        if (total > 0) {
            txtTotal.setText("Precio Total: " + String.format("%.2f", total) + " Bs.");
        } else {
            txtTotal.setText("Precio Total: " + String.valueOf(0) + " Bs.");
        }
    }
    private double calcularTotalBoton(List<Double> subTotales) {
    	double total=0;
    	for (Double subTotal : subTotales) {
            total += subTotal;
        }
    	total=Math.round(total*100.0)/100.0;
    	return total;
    }
    
    
    //borrar esto luego de acabar este publix static void es para correr directamente la ventana
    
    public static void main(String[] args) {
        // Ejemplo de uso
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // En este caso, simplemente muestra un mensaje ya que no se proporciona un carnet de funcionario
                VentanaVenta ventanaVenta = new VentanaVenta(880088);  // Puedes usar cualquier valor que indique la ausencia de un carnet
                ventanaVenta.setVisible(true);
            }
        });
    }
}

