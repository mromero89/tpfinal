package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.DetalleEnvio;

public class ABMDetalleEnvio {
	
public static ArrayList<DetalleEnvio> todos() throws SQLException  {
		
		ArrayList<DetalleEnvio> resultados = new ArrayList<DetalleEnvio>();

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
		PreparedStatement stn = connection.prepareStatement("SELECT * FROM detallesenvios");
		ResultSet rs = stn.executeQuery();
		while(rs.next()) {
			//Camion(String patente, String modelo, int kmrec, int costokm, int costoh, String fechacompra)
			DetalleEnvio aux = new DetalleEnvio();
			aux.setNropedido(rs.getInt(1));
			aux.setPatente(rs.getString(2));
			aux.setRuta(rs.getString(3));
			aux.setCosto(rs.getInt(4));
			resultados.add(aux);
		}
		//stn.execute("INSERT INTO \"Libro\" (id, nombre) VALUES (4, \'Oscar\')");
		
		
		
		
			stn.close();
		
		
			connection.close();
			return resultados;
		
		
		
	}

}
