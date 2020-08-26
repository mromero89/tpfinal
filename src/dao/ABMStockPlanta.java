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
import dominio.InsumosPlantas;
import dominio.ItemPedido;
import dominio.Planta;
import dominio.Ruta;

public class ABMStockPlanta {
	
	public static void altastock(String nombreplanta, String insumo, String cantidad, String puntopedido) throws SQLException  {
		
	
		

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
		
		System.out.println("Palabra sql: "+"INSERT INTO insumosplantas (nombreplanta, insumo, cantidad, puntopedido) VALUES (\'"+nombreplanta+"\', \'"+insumo+"\',"+cantidad+", "+puntopedido+")");
			stn.executeUpdate("INSERT INTO insumosplantas (nombreplanta, insumo, cantidad, puntopedido) VALUES (\'"+nombreplanta+"\', \'"+insumo+"\',"+cantidad+", "+puntopedido+")");
		
		
		
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


public static void consultarstockmenor(ArrayList<InsumosPlantas> lista, String campoplanta, String campoinsumo) throws SQLException {
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
	String consulta = "SELECT * FROM insumosplantas WHERE cantidad < puntopedido";

	if (!campoplanta.equals(""))
		consulta = consulta + " AND nombreplanta = \'"+campoplanta+"\'";
	
	if (!campoinsumo.equals(""))
		consulta = consulta + " AND insumo = \'"+campoinsumo+"\'";
	
	
	PreparedStatement stn = connection.prepareStatement(consulta);
	ResultSet rs = stn.executeQuery();
	
	while(rs.next()) {
		InsumosPlantas aux = new InsumosPlantas();
		aux.setInsumo(rs.getString(1));
		aux.setCantidad(rs.getInt(2));
		aux.setPuntopedido(rs.getInt(3));
		aux.setNombreplanta(rs.getString(4));
		lista.add(aux);
	
	}
	
	stn.close();
	connection.close();
	
	
	 /*fin consulta sql
	
	 */
}

public static void consultaplantastock(ArrayList<Planta> rsconsulta, ItemPedido j, Planta i) throws SQLException {
	//consulta SQL 
	
	 try { 
		 Class.forName("org.postgresql.Driver");
		 } catch (ClassNotFoundException ex) {
			 System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
			 }
	 
	 Connection connection = null;
	 connection = DriverManager.getConnection(
   "jdbc:postgresql://localhost:5432/postgres",
   "postgres", "wilson222");
	 
	 String consulta = "SELECT * FROM insumosplantas WHERE insumo = \'"+j.getInsumo()+"\' AND cantidad >="+j.getCantidad()+" AND nombreplanta = \'"+i.getNombre()+"\'";
	 PreparedStatement stn = connection.prepareStatement(consulta);
	 ResultSet rs = stn.executeQuery();
	 while(rs.next()) { 
	 Planta aux = new Planta();
	 aux.setNombre(rs.getString("nombreplanta"));
	 rsconsulta.add(aux);
	 }
	 stn.close();
	 connection.close();
	 /*FIn consulta SQL */
}


	

}
