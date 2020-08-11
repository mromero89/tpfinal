package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dominio.Camion;
import dominio.Insumo;
import dominio.Planta;
import dominio.Ruta;

public class ABMInsumo {
	
	public static void altainsumogeneral(String descripcion, String unidadmedida, String costo, String peso) throws SQLException  {
		
	
		

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
		
		System.out.println("Palabra sql: "+"INSERT INTO insumos (descripcion, unidadmedida, costo, peso, tipo) VALUES (\'"+descripcion+"\', \'"+unidadmedida+"\',"+costo+", "+peso+", \'GENERAL\')");
			stn.executeUpdate("INSERT INTO insumos (descripcion, unidadmedida, costo, peso, tipo) VALUES (\'"+descripcion+"\', \'"+unidadmedida+"\',"+costo+", "+peso+", \'GENERAL\')");
		
		
		
			stn.close();
		
		
			connection.close();
		
		
		
	}
	
public static void altainsumoliquido(String descripcion, String unidadmedida, String costo, String densidad) throws SQLException  {
		

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
	
	System.out.println("Palabra sql: "+"INSERT INTO insumos (descripcion, unidadmedida, costo, peso, tipo) VALUES (\'"+descripcion+"\', \'"+unidadmedida+"\',"+costo+", "+densidad+", \'GENERAL\')");
		stn.executeUpdate("INSERT INTO insumos (descripcion, unidadmedida, costo, densidad, tipo) VALUES (\'"+descripcion+"\', \'"+unidadmedida+"\',"+costo+", "+densidad+", \'LIQUIDO\')");
	
	
	
		stn.close();
	
	
		connection.close();
	
		
	}
	
public static ArrayList<Insumo> todos() throws SQLException  {
		
		ArrayList<Insumo> resultados = new ArrayList<Insumo>();

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
		PreparedStatement stn = connection.prepareStatement("SELECT * FROM insumos ");
		ResultSet rs = stn.executeQuery();
		while(rs.next()) {
			//Camion(String patente, String modelo, int kmrec, int costokm, int costoh, String fechacompra)
			Insumo aux = new Insumo();
			/*private String descripcion;
			private String unidadmedida;
			private int costo;
			private String tipo;
			private int peso;
			private int densidad;*/
			
		
			
			aux.setDescripcion(rs.getString(1));
			aux.setUnidadmedida(rs.getString(2));
			aux.setCosto(Integer.parseInt(rs.getString(3)));
			String peso = rs.getString(4);
			String densidad = rs.getString(5);
			if (peso != null) {
				aux.setPeso(Integer.parseInt(peso));

			}
			if (densidad != null) {
				aux.setDensidad(Integer.parseInt(densidad));

			}
			aux.setTipo(rs.getString(6));

			
			
			resultados.add(aux);
		}
		//stn.execute("INSERT INTO \"Libro\" (id, nombre) VALUES (4, \'Oscar\')");
		
		
		
		
			stn.close();
		
		
			connection.close();
			return resultados;
		
		
		
	}

public static ArrayList<Insumo> busqueda(String descripcion, String unidadmedida, String costo, String peso, 
		String densidad, String tipo) throws SQLException  {
	
	/*descripcion c
unidadmedida c
costo i
peso i
densidad i
tipo c
	  */
	int cont = 0;
	String busqueda = "SELECT * FROM insumos";
	if (!descripcion.equals("") || !unidadmedida.equals("") || !costo.equals("") || !peso.equals("") || !densidad.equals("") || !tipo.equals(""))
		busqueda = busqueda+" WHERE ";
	if (!descripcion.equals("")) {
		busqueda = busqueda+"descripcion = \'"+descripcion+"\'";
		cont++;
	}
	if (!unidadmedida.equals("")) {
		if (cont>0)
			busqueda = busqueda+"AND unidadmedida = \'"+unidadmedida+"\'";
		else {
			busqueda = busqueda+"unidadmedida = \'"+unidadmedida+"\'";
			cont++;
		}
		
	}
	if (!costo.equals("")) {
		if (cont>0)
			busqueda = busqueda+"AND costo = "+costo+" ";
		else {
			busqueda = busqueda+"costo ="+costo+" ";
			cont++;
		}
		
	}
	
	if (!peso.equals("")) {
		if (cont>0)
			busqueda = busqueda+"AND peso ="+peso+" ";
		else {
			busqueda = busqueda+"peso ="+peso+" ";
			cont++;
		}
		
	}
	
	if (!densidad.equals("")) {
		if (cont>0)
			busqueda = busqueda+"AND densidad ="+densidad+" ";
		else {
			busqueda = busqueda+"densidad ="+densidad+" ";
			cont++;
		}
		
	}
	
	if (!tipo.equals("")) {
		if (cont>0)
			busqueda = busqueda+"AND tipo = \'"+tipo+"\'";
		else {
			busqueda = busqueda+"tipo = \'"+tipo+"\'";
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
	
	
	ArrayList<Insumo> resultados = new ArrayList<Insumo>();


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
			Insumo aux = new Insumo();
			
			/* public class Insumo {
	
	private String descripcion;
	private String unidadmedida;
	private int costo;
	private int peso;
	private int densidad;
		private String tipo;
*/
			aux.setDescripcion(rs.getString(1));
			aux.setUnidadmedida(rs.getString(2));
			aux.setCosto(Integer.parseInt(rs.getString(3)));
			if (rs.getString(4) != null) {
				aux.setPeso(Integer.parseInt(rs.getString(4)));

			}
			if (rs.getString(5) != null) {
				aux.setDensidad(Integer.parseInt(rs.getString(5)));

			}
			aux.setTipo(rs.getString(6));

			
			//Camion aux2 = new Camion(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
			resultados.add(aux);
		}
		
	
	
		stn.close();
	
	
		connection.close();
		return resultados;
	
	
}
	

}
