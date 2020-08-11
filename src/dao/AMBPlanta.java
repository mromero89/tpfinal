package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Camion;
import dominio.Planta;

public class AMBPlanta {
	
	public static void altaplanta(String nombre) throws SQLException  {
		
		

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
		
			stn.executeUpdate("INSERT INTO plantas (nombre) VALUES (\'"+nombre+"\')");
		
		
		
			stn.close();
		
		
			connection.close();
		
		
		
	}
	
public static ArrayList<Planta> todos() throws SQLException  {
		
		ArrayList<Planta> resultados = new ArrayList<Planta>();

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
		PreparedStatement stn = connection.prepareStatement("SELECT * FROM plantas");
		ResultSet rs = stn.executeQuery();
		while(rs.next()) {
			//Camion(String patente, String modelo, int kmrec, int costokm, int costoh, String fechacompra)
			Planta aux = new Planta();
			aux.setNombre(rs.getString(1));
			resultados.add(aux);
		}
		//stn.execute("INSERT INTO \"Libro\" (id, nombre) VALUES (4, \'Oscar\')");
		
		
		
		
			stn.close();
		
		
			connection.close();
			return resultados;
		
		
		
	}
	


}
