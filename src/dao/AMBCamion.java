package dao;
import dominio.Camion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JDialog;

public class AMBCamion {
	
	public static void altacamion(String patente, String modelo, int kmrec, int costokm, 
			int costohora, String fecha) throws SQLException  {
		
		

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
		
			stn.executeUpdate("INSERT INTO camiones (patente, modelo, kmrec, costokm, costohora, fechacompra) VALUES (\'"+patente+"\', \'"+modelo+"\' , "+kmrec+", "+costokm+", "+costohora+", \'"+fecha+"\')");
		
		
		
			stn.close();
		
		
			connection.close();
		
		
		
	}
	
	public static ArrayList<Camion> todos() throws SQLException  {
		
		ArrayList<Camion> resultados = new ArrayList<Camion>();

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
		PreparedStatement stn = connection.prepareStatement("SELECT * FROM camiones");
		ResultSet rs = stn.executeQuery();
		while(rs.next()) {
			//Camion(String patente, String modelo, int kmrec, int costokm, int costoh, String fechacompra)
			Camion aux = new Camion(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
			resultados.add(aux);
		}
		//stn.execute("INSERT INTO \"Libro\" (id, nombre) VALUES (4, \'Oscar\')");
		
		
		
		
			stn.close();
		
		
			connection.close();
			return resultados;
		
		
		
	}
	
	public static ArrayList<Camion> busqueda(String patente, String modelo, String kmrec, String costokm, 
			String costohora, String fecha) throws SQLException  {
		int cont = 0;
		String busqueda = "SELECT * FROM camiones";
		if (!patente.equals("") || !modelo.equals("") || !kmrec.equals("") || !costokm.equals("") || !costohora.equals("") || !fecha.equals(""))
			busqueda = busqueda+" WHERE ";
		if (!patente.equals("")) {
			busqueda = busqueda+"patente = \'"+patente+"\'";
			cont++;
		}
		if (!modelo.equals("")) {
			if (cont>0)
				busqueda = busqueda+"AND modelo = \'"+modelo+"\'";
			else {
				busqueda = busqueda+"modelo = \'"+modelo+"\'";
				cont++;
			}
			
		}
		if (!kmrec.equals("")) {
			if (cont>0)
				busqueda = busqueda+"AND kmrec = "+kmrec+" ";
			else {
				busqueda = busqueda+"kmrec ="+kmrec+" ";
				cont++;
			}
			
		}
		
		if (!costokm.equals("")) {
			if (cont>0)
				busqueda = busqueda+"AND costokm ="+costokm+" ";
			else {
				busqueda = busqueda+"costokm ="+costokm+" ";
				cont++;
			}
			
		}
		
		if (!costohora.equals("")) {
			if (cont>0)
				busqueda = busqueda+"AND costohora ="+costohora+" ";
			else {
				busqueda = busqueda+"costohora ="+costohora+" ";
				cont++;
			}
			
		}
		
		if (!fecha.equals("")) {
			if (cont>0)
				busqueda = busqueda+"AND fechacompra = \'"+fecha+"\'";
			else {
				busqueda = busqueda+"fechacompra = \'"+fecha+"\'";
				cont++;
			}
			
		}
		
		//System.out.println("palabra sql: "+busqueda);
		
		/*
		if (!patente.equals(""))
			busqueda = busqueda+"kmrec = "+kmrec+" ";
		if (!patente.equals(""))
			busqueda = busqueda+"costokm = "+costokm+" ";
		if (!patente.equals(""))
			busqueda = busqueda+"costohora = "+costohora+" ";
		if (!patente.equals(""))
			busqueda = busqueda+"fechacompra = \'"+fecha+"\'";
		*/
		
		
		ArrayList<Camion> resultados = new ArrayList<Camion>();


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
		PreparedStatement stn = connection.prepareStatement(busqueda);
		//stn.execute("INSERT INTO \"Libro\" (id, nombre) VALUES (4, \'Oscar\')");
		/*
			stn.setString(1, "*");
			stn.setString(2, modelo);
			stn.setInt(3, kmrec);
			stn.setInt(4, costokm);
			stn.setInt(5, costohora);*/
		/*
		stn.setString(1, "*");
		stn.setString(2,  "*");
		stn.setString(3,  "*");
		stn.setString(4,  "*");
		stn.setString(5,  "*");
		*/
			//stn.setString(6, fecha);
			
			ResultSet rs = stn.executeQuery();
			while(rs.next()) {
				//Camion(String patente, String modelo, int kmrec, int costokm, int costoh, String fechacompra)
				Camion aux = new Camion(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
				resultados.add(aux);
			}
			
		
		
			stn.close();
		
		
			connection.close();
			return resultados;
		
		
	}
	
	public static void modificarcamion(String patente, String modelo, String kmrec, String costokm, 
			String costohora, String fecha, String clave) throws SQLException  {
		
		
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
		
			stn.executeUpdate("UPDATE camiones SET patente = \'"+patente+"\', modelo = \'"+modelo+"\', kmrec ="+kmrec+", costokm ="+costokm+", costohora="+costohora+", fechacompra= \'"+fecha+"\' WHERE patente = \'"+clave+"\'");
			clave = patente;
					//WHERE patente = ... (patente, modelo, kmrec, costokm, costohora, fechacompra) VALUES (\'"+patente+"\', \'"+modelo+"\' , "+kmrec+", "+costokm+", "+costohora+", \'"+fecha+"\')");
		
		
		
			stn.close();
		
		
			connection.close();
		
		
		
	}
	
	public static void borrarcamion(String patente) throws SQLException  {
		
		
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
		
			stn.executeUpdate("DELETE FROM camiones WHERE patente = \'"+patente+"\'");
					//WHERE patente = ... (patente, modelo, kmrec, costokm, costohora, fechacompra) VALUES (\'"+patente+"\', \'"+modelo+"\' , "+kmrec+", "+costokm+", "+costohora+", \'"+fecha+"\')");
		
		
		
			stn.close();
		
		
			connection.close();
		
		
		
	}


}
