package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ABMOrdenPedido {

	public static void modificar(String nropedido, String plantaorigen, String estado) throws SQLException  {
		
		
		try { 
		    Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException ex) {
		    System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
		}
		

		Connection connection = null;
		// Database connect
		// Conectamos con la base de datos
		connection = DriverManager.getConnection(
		        "jdbc:postgresql://localhost:5432/postgres",
		        "postgres", "wilson222");
		Statement stn = connection.createStatement();
		//stn.execute("INSERT INTO \"Libro\" (id, nombre) VALUES (4, \'Oscar\')");
		
		//System.out.println("palabra sql: UPDATE camiones SET patente = \'"+patente+"\', modelo = \'"+modelo+"\', kmrec ="+kmrec+", costokm ="+costokm+", costohora="+costohora+", fechacompra= \'"+fecha+"\' WHERE patente = \'"+clave+"\'");
		
			stn.executeUpdate("UPDATE ordenespedidos SET nropedido = "+nropedido+", plantaorigen = \'"+plantaorigen+"\', estado =\'"+estado+"\' WHERE nropedido = "+nropedido);
					//WHERE patente = ... (patente, modelo, kmrec, costokm, costohora, fechacompra) VALUES (\'"+patente+"\', \'"+modelo+"\' , "+kmrec+", "+costokm+", "+costohora+", \'"+fecha+"\')");
		
		
		
			stn.close();
		
		
			connection.close();
		
		
		
	}
	
}
