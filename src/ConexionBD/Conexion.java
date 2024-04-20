package ConexionBD;



import java.sql.*;

import javax.swing.JOptionPane;
public class Conexion {
	static final String DB_URL ="jdbc:postgresql://localhost/Llanteria";
	static final String USER = "postgres";
	static final String PASS = "admin";
	
	public Connection getConexionPostgres(){
		Connection conn=null;
	try {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
	}
	catch (SQLException ex) {
	//JOptionPane.showMessageDialog(null, "No se pudo conectar");
	return null;
	}
	//JOptionPane.showMessageDialog(null, "Conexion exitosa");
	return conn;
	}
}

