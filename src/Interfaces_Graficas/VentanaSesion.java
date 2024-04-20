package Interfaces_Graficas;

import javax.swing.*;

import ConexionBD.Conexion;
import DAO.FuncionarioDAO;
import Modelos.Funcionario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;

public class VentanaSesion extends JFrame {
    private JTextField campoCarnet;
    private JPasswordField campoContraseña;

    public VentanaSesion(FuncionarioDAO funcionarioDAO) {
    	getContentPane().setBackground(new Color(255, 255, 255));
    	setForeground(new Color(255, 255, 255));
    	getContentPane().setForeground(new Color(255, 255, 255));
        // Configuración de la ventana
        setTitle("Inicio de Sesión");
        setSize(472, 316);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de componentes
        JLabel etiquetaCarnet = new JLabel("Carnet:");
        etiquetaCarnet.setFont(new Font("Tahoma", Font.PLAIN, 14));
        etiquetaCarnet.setBounds(272, 80, 63, 30);
        JLabel etiquetaContraseña = new JLabel("Contraseña:");
        etiquetaContraseña.setFont(new Font("Tahoma", Font.PLAIN, 14));
        etiquetaContraseña.setBounds(272, 136, 80, 30);

        campoCarnet = new JTextField(10);
        campoCarnet.setFont(new Font("Tahoma", Font.PLAIN, 14));
        campoCarnet.setBorder(null);
        campoCarnet.setOpaque(false);
        campoCarnet.setBackground(new Color(0, 128, 128));
        campoCarnet.setBounds(272, 108, 135, 30);
        
        campoContraseña = new JPasswordField(10);
        campoContraseña.setBorder(null);
        campoContraseña.setOpaque(false);
        campoContraseña.setFont(new Font("Tahoma", Font.PLAIN, 14));
        campoContraseña.setBackground(new Color(0, 128, 128));
        campoContraseña.setBounds(270, 162, 135, 30);

        JButton botonIniciarSesion = new JButton("Iniciar Sesión");
        botonIniciarSesion.setBounds(270, 214, 135, 39);
        getContentPane().setLayout(null);
        
        JSeparator separatorContrasena = new JSeparator();
        separatorContrasena.setBackground(new Color(0, 0, 0));
        separatorContrasena.setForeground(Color.BLACK);
        separatorContrasena.setBounds(272, 190, 135, 2);
        getContentPane().add(separatorContrasena);
        
        //para la estetica
        JSeparator separatorUsuario = new JSeparator();
        separatorUsuario.setBackground(new Color(0, 0, 0));
        separatorUsuario.setForeground(new Color(0, 0, 0));
        separatorUsuario.setBounds(272, 136, 135, 2);
        getContentPane().add(separatorUsuario);

        // Adición de componentes a la ventana
        getContentPane().add(etiquetaCarnet);
        getContentPane().add(campoCarnet);
        getContentPane().add(etiquetaContraseña);
        getContentPane().add(campoContraseña);
        getContentPane().add(botonIniciarSesion);
        //para el logo
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(VentanaSesion.class.getResource("/CatalogoFotos/logo.png")));
        lblLogo.setBounds(60, 11, 291, 67);
        getContentPane().add(lblLogo);
        
        JLabel lblLlantaLogo = new JLabel("");
        lblLlantaLogo.setBounds(28, 80, 179, 186);
        getContentPane().add(lblLlantaLogo);
        rsscalelabel.RSScaleLabel.setScaleLabel(lblLlantaLogo,"src/CatalogoFotos/llantaLogo.png" );
        
        // Configuración del evento para el botón de iniciar sesión
        botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion(funcionarioDAO);
            }
        });
        
     // Agregar un KeyListener al campo de contraseña
        campoContraseña.addKeyListener((KeyListener) new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // Verificar si la tecla presionada es "Enter"
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Ejecutar la acción de iniciar sesión
                    iniciarSesion(funcionarioDAO);
                }
            }
        });
    }
    

    void iniciarSesion(FuncionarioDAO funcionarioDAO) {
        int carnetFuncionario = Integer.parseInt(campoCarnet.getText());
        String contraseña = new String(campoContraseña.getPassword());

        // Verificación de las credenciales
        Funcionario funcionario = funcionarioDAO.obtenerFuncionarioPorCarnet(carnetFuncionario);

        if (funcionario != null && contraseña.equals(funcionario.getContraseña())) {
            // Credenciales válidas, abrir la próxima ventana
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            abrirVentanaPrincipal(carnetFuncionario); // Pasa el carnet del funcionario
        } else {
            JOptionPane.showMessageDialog(this, "Carnet o contraseña incorrectos", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
            // Credenciales incorrectas, no cerrar la ventana
            limpiarCampos();
        }
    }
    
    private void limpiarCampos() {
        campoCarnet.setText("");
        campoContraseña.setText("");
    }


    void abrirVentanaPrincipal(int carnetFuncionario) {
        // Aquí deberías abrir la ventana principal del sistema, puedes pasar el carnet del funcionario
        // para que la ventana principal pueda acceder a la información del usuario que ha iniciado sesión.
        // Ejemplo:
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(carnetFuncionario);
        ventanaPrincipal.setVisible(true);
        // Cerrar la ventana actual
        dispose();
    }

    public static void main(String[] args) {
        // Este es solo un ejemplo para probar la ventana de inicio de sesión
        Conexion conexion = new Conexion();
        Connection connection = conexion.getConexionPostgres();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaSesion ventanaSesion = new VentanaSesion(funcionarioDAO);
                ventanaSesion.setVisible(true);
            }
        });
    }
}
