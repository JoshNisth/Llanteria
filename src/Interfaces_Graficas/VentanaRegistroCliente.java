package Interfaces_Graficas;

import javax.swing.*;

import DAO.ClienteDAO;
import Modelos.Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class VentanaRegistroCliente extends JFrame {
    private JTextField txtCarnetCliente;
    private JTextField txtNombreCliente;
    private JTextField txtDireccion;
    private JTextField txtTipoCliente;
    private JTextField txtCorreoCliente;
    private JTextField txtCelularCliente;
    private JButton btnBuscar;
    private JButton btnFinalizar;
    private ClienteDAO clienteDAO;
    private ActionListener registrarClienteListener; // Listener para el registro del cliente

    public VentanaRegistroCliente(Connection connection) {
        this.clienteDAO = new ClienteDAO(connection);

        // Configuración de la ventana
        setTitle("Registro de Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int xPos = (screenSize.width - getWidth()) / 2;
        int yPos = (screenSize.height - getHeight()) / 2;

        // Establecer la posición de la ventana
        setLocation(xPos, yPos);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        // Inicialización de componentes
        txtCarnetCliente = new JTextField();
        txtNombreCliente = new JTextField();
        txtDireccion = new JTextField();
        txtTipoCliente = new JTextField();
        txtCorreoCliente = new JTextField();
        txtCelularCliente = new JTextField();
        btnBuscar = new JButton("Buscar");
        btnFinalizar = new JButton("Finalizar");

        // Agregar componentes a la ventana
        add(new JLabel("Carnet Cliente:"));
        add(txtCarnetCliente);
        add(new JLabel("Nombre Cliente:"));
        add(txtNombreCliente);
        add(new JLabel("Dirección:"));
        add(txtDireccion);
        add(new JLabel("Tipo Cliente:"));
        add(txtTipoCliente);
        add(new JLabel("Correo Cliente:"));
        add(txtCorreoCliente);
        add(new JLabel("Celular Cliente:"));
        add(txtCelularCliente);
        add(btnBuscar);
        add(btnFinalizar);

        // Agregar listeners a los botones
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });

        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarRegistro();
            }
        });
    }

    private void buscarCliente() {
        try {
            int carnetCliente = Integer.parseInt(txtCarnetCliente.getText());
            Cliente cliente = clienteDAO.obtenerClientePorCarnet(carnetCliente);

            if (cliente != null) {
                // Si el cliente existe, llenar los campos con sus datos
                txtNombreCliente.setText(cliente.getNombreCliente());
                txtDireccion.setText(cliente.getDireccion());
                txtTipoCliente.setText(cliente.getTipoCliente());
                txtCorreoCliente.setText(cliente.getCorreoCliente());
                txtCelularCliente.setText(cliente.getCelularCliente());
                JOptionPane.showMessageDialog(this, "Cliente encontrado.");
            } else {
                // Si el cliente no existe, mostrar un mensaje
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para el carnet del cliente.");
        }
    }

    private void finalizarRegistro() {
        try {
            int carnetCliente = Integer.parseInt(txtCarnetCliente.getText());
            String nombreCliente = txtNombreCliente.getText();
            String direccion = txtDireccion.getText();
            String tipoCliente = txtTipoCliente.getText();
            String correoCliente = txtCorreoCliente.getText();
            String celularCliente = txtCelularCliente.getText();

            // Verificar si el cliente ya existe en la base de datos
            Cliente clienteExistente = clienteDAO.obtenerClientePorCarnet(carnetCliente);

            if (clienteExistente != null) {
                // Si el cliente ya existe, simplemente guardar el carnet del cliente
                JOptionPane.showMessageDialog(this, "Registrado.");
                // Llamar al listener si está establecido
                if (registrarClienteListener != null) {
                    registrarClienteListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                }
                // Cerrar la ventana actual
                dispose();
            } else {
                // Si el cliente no existe, realizar la inserción en la base de datos
                Cliente nuevoCliente = new Cliente(carnetCliente, nombreCliente, direccion, tipoCliente, correoCliente, celularCliente);
                if (clienteDAO.insertarCliente(nuevoCliente)) {
                    JOptionPane.showMessageDialog(this, "Cliente nuevo registrado exitosamente.");
                    // Llamar al listener si está establecido
                    if (registrarClienteListener != null) {
                        registrarClienteListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                    }
                    // Cerrar la ventana actual
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar el cliente.");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese valores válidos para el registro del cliente.");
        }
    }


    // Método para establecer el listener para el registro del cliente
    public void setRegistrarClienteListener(ActionListener listener) {
        this.registrarClienteListener = listener;
    }

    // Método para obtener el carnet del cliente ingresado
    public int getCarnetCliente() {
        return Integer.parseInt(txtCarnetCliente.getText());
    }
}
