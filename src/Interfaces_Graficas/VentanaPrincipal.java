package Interfaces_Graficas;

import javax.swing.*;

import ConexionBD.Conexion;
import DAO.FuncionarioDAO;
import Modelos.Funcionario;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.Font;

public class VentanaPrincipal extends JFrame {
    private int carnetFuncionario;
    private JTextField textField;

    public VentanaPrincipal(int carnetFuncionario) {
        
        this.carnetFuncionario = carnetFuncionario;

        // Configuración de la ventana
        setTitle("Ventana Principal");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de componentes
        JButton botonConsultaVentas = new JButton("Consultar Ventas");
        botonConsultaVentas.setBounds(280, 170, 160, 43);
        JButton botonRealizarVenta = new JButton("Realizar Venta");
        botonRealizarVenta.setBounds(50, 100, 160, 45);
        JButton btnActualizarInventario = new JButton("Actualizar inventario");
        btnActualizarInventario.setBounds(280, 100, 160, 45);
        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        botonCerrarSesion.setBounds(300, 300, 132, 23);
        getContentPane().setLayout(null);
        getContentPane().add(botonConsultaVentas);
        getContentPane().add(botonRealizarVenta);
        getContentPane().add(btnActualizarInventario);
        getContentPane().add(botonCerrarSesion);
        
        textField = new JTextField();
        textField.setBounds(50, 300, 86, 20);
        getContentPane().add(textField);
        textField.setColumns(10);
        textField.setText(String.valueOf(carnetFuncionario));
        
        JLabel lblMenu = new JLabel("Menú de Opciones");
        lblMenu.setFont(new Font("Mongolian Baiti", Font.BOLD, 30));
        lblMenu.setBounds(120, 40, 247, 43);
        getContentPane().add(lblMenu);
        
        JButton botonConsultaStock = new JButton("Consultar Stock");
        botonConsultaStock.setBounds(50, 170, 160, 43);
        getContentPane().add(botonConsultaStock);
        
        btnActualizarInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir la ventana VentanaActualizarStock
                abrirVentanaActualizarStock(carnetFuncionario);
            }
        });

        

        // Configuración de eventos
        botonConsultaVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para consultar ventas
            	 abrirVentanaHistorialVentas(carnetFuncionario);
            }
        });

        botonRealizarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para realizar venta
            	abrirVentanaVenta(carnetFuncionario);
            }
        });
        
        botonConsultaStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaStock(carnetFuncionario);
            }
        });

        botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();  // Llama al método desde la instancia actual de VentanaPrincipal
            }
        });
    }

    private void cerrarSesion() {
    	 Conexion conexion = new Conexion();
         Connection connection = conexion.getConexionPostgres();
         FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);
        VentanaSesion ventanaSesion = new VentanaSesion(funcionarioDAO);
        ventanaSesion.setVisible(true);
        dispose();  // Cierra la ventana actual
    }
    private void abrirVentanaVenta(int carnetFuncionario) {
        VentanaVenta ventanaVenta = new VentanaVenta(carnetFuncionario);
        ventanaVenta.setVisible(true);
        dispose();
    }
    private void abrirVentanaHistorialVentas(int carnetFuncionario) {
        VentanaHistorialVentas ventanaHistorial = new VentanaHistorialVentas(carnetFuncionario);
        ventanaHistorial.setVisible(true);
        dispose();
    }
    private void abrirVentanaStock(int carnetFuncionario) {
        VentanaStock ventanaStock = new VentanaStock(carnetFuncionario);
        ventanaStock.setVisible(true);
        dispose();
    }
    
    private void abrirVentanaActualizarStock(int carnetFuncionario) {
        VentanaActualizarStock ventanaActualizarStock = new VentanaActualizarStock(carnetFuncionario);
        ventanaActualizarStock.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // En este caso, simplemente muestra un mensaje ya que no se proporciona un carnet de funcionario
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(880088);  // Puedes usar cualquier valor que indique la ausencia de un carnet
                ventanaPrincipal.setVisible(true);
            }
        });
    }
}
