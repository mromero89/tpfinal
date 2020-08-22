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
import dominio.Ruta;

public class ABMRuta {
	
	public static void altaruta(String origen, String destino, String distanciakm, String duracionh, String pesomax, String nombre) throws SQLException  {
		
	
		

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
		
		//System.out.println("Palabra sql: "+"INSERT INTO rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES (\'"+origen+"\', \'"+destino+"\',"+distanciakm+", "+duracionh+", "+pesomax+", \'"+nombre+"\')");
			stn.executeUpdate("INSERT INTO rutas (origen, destino, distancia, duracion, pesomax, nombre) VALUES (\'"+origen+"\', \'"+destino+"\',"+distanciakm+", "+duracionh+", "+pesomax+", \'"+nombre+"\')");
		
		
		
			stn.close();
		
		
			connection.close();
		
		
		
	}
	
public static ArrayList<Ruta> todos() throws SQLException  {
		
		ArrayList<Ruta> resultados = new ArrayList<Ruta>();

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
		PreparedStatement stn = connection.prepareStatement("SELECT * FROM rutas ");
		ResultSet rs = stn.executeQuery();
		while(rs.next()) {
			//Camion(String patente, String modelo, int kmrec, int costokm, int costoh, String fechacompra)
			Ruta aux = new Ruta();
			Planta origen = new Planta();
			Planta destino = new Planta();
			origen.setNombre(rs.getString(1));
			destino.setNombre(rs.getString(2));
			
			aux.setOrigen(origen);
			aux.setDestino(destino);
			aux.setDistanciakm(Integer.parseInt(rs.getString(3)));
			aux.setDuracionh(Integer.parseInt(rs.getString(4)));
			aux.setPesomax(Integer.parseInt(rs.getString(5)));
			aux.setNombre(rs.getString(6));
			
			
			resultados.add(aux);
		}
		//stn.execute("INSERT INTO \"Libro\" (id, nombre) VALUES (4, \'Oscar\')");
		
		
		
		
			stn.close();
		
		
			connection.close();
			return resultados;
		
		
		
	}
	

}
