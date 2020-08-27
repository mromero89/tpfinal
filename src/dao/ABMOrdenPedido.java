package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dominio.ItemPedido;
import dominio.OrdenPedido;

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
	
	public static void marcarentregada(int nropedido) {
		try {
			
		
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
		
		Statement stn;
		
			stn = connection.createStatement();
		
		
			stn.executeUpdate("UPDATE ordenespedidos SET estado = \'ENTREGADA\' WHERE nropedido = "+nropedido);
		
		
			
				stn.close();
			
		
		
			
				connection.close();
	
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}
	public static void consultarordenesprocesadas(ArrayList<OrdenPedido> lista) {
		try {
			
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
			PreparedStatement stn = connection.prepareStatement("SELECT * FROM ordenespedidos WHERE estado = \'PROCESADA\' ");
			ResultSet rs = stn.executeQuery();
			while(rs.next()) {
				//Camion(String patente, String modelo, int kmrec, int costokm, int costoh, String fechacompra)
				OrdenPedido aux = new OrdenPedido();
				
				
			
				
				aux.setNropedido(rs.getInt(1));
				aux.setPlantaorigen(rs.getString(2));
				aux.setPlantadestino(rs.getString(3));
				aux.setFechaentrega(rs.getString(5));
				aux.setEstado(rs.getString(6));



				
				
				lista.add(aux);
			}
			//stn.execute("INSERT INTO \"Libro\" (id, nombre) VALUES (4, \'Oscar\')");
			
			
			
			
				stn.close();
			
			
				connection.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public static void consultarordenescreadas(ArrayList<OrdenPedido> lista) {
		try {
			
			try { 
			    Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException ex) {
			    System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
			}
			
			Connection connection = null;

			connection = DriverManager.getConnection(
			        "jdbc:postgresql://localhost:5432/postgres",
			        "postgres", "wilson222");
			PreparedStatement stn = connection.prepareStatement("SELECT * FROM ordenespedidos WHERE estado = \'CREADA\' ");
			ResultSet rs = stn.executeQuery();
			while(rs.next()) {
				OrdenPedido aux = new OrdenPedido();
				
				
			
				
				aux.setNropedido(rs.getInt(1));
				aux.setPlantaorigen(rs.getString(2));
				aux.setPlantadestino(rs.getString(3));
				aux.setFechaentrega(rs.getString(5));
				aux.setEstado(rs.getString(6));



				
				
				lista.add(aux);
			}
			
			
			
			
				stn.close();
			
			
				connection.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public static void consultarorden(ArrayList<OrdenPedido> lista, String estado) {
		try {
			
			try { 
			    Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException ex) {
			    System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
			}
			
			Connection connection = null;

			connection = DriverManager.getConnection(
			        "jdbc:postgresql://localhost:5432/postgres",
			        "postgres", "wilson222");
			PreparedStatement stn = connection.prepareStatement("SELECT * FROM ordenespedidos WHERE estado = \'"+estado+"\'");
			ResultSet rs = stn.executeQuery();
			while(rs.next()) {
				OrdenPedido aux = new OrdenPedido();
				
				
			
				
				aux.setNropedido(rs.getInt(1));
				aux.setPlantaorigen(rs.getString(2));
				aux.setPlantadestino(rs.getString(3));
				aux.setFechaentrega(rs.getString(5));
				aux.setEstado(rs.getString(6));



				
				
				lista.add(aux);
			}
			
			
			
			
				stn.close();
			
			
				connection.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	public static void guardarordenpedido(String camponumeropedido, String destino, String campofechamaxima, ArrayList<ItemPedido> items) {
		
		try { 
		    Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException ex) {
		    System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
		}
		

		Connection connection = null;
		// Database connect
		// Conectamos con la base de datos
		try {
			connection = DriverManager.getConnection(
			        "jdbc:postgresql://localhost:5432/postgres",
			        "postgres", "wilson222");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Statement stn;
		try {
			stn = connection.createStatement();
			String palabra1 = "Sentencia 1: INSERT INTO ordenespedidos (nropedido, plantadestino, fechaentrega, estado) VALUES ("+camponumeropedido+", \'"+destino+"\', \'"+campofechamaxima+"\', \'CREADA\'";
			System.out.println(palabra1);
			stn.executeUpdate("INSERT INTO ordenespedidos (nropedido, plantadestino, fechaentrega, estado) VALUES ("+camponumeropedido+", \'"+destino+"\', \'"+campofechamaxima+"\', \'CREADA\')");
			
			for (ItemPedido i : items) {
				stn = connection.createStatement();
				String palabra2 = "Sentencia2: "+"INSERT INTO itemspedidos (nropedido, item, cantidad, costo) VALUES ("+camponumeropedido+", \'"+i.getInsumo()+"\' , "+i.getCantidad()+", "+i.getCosto()+")";
				System.out.println(palabra2);
				stn.executeUpdate("INSERT INTO itemspedidos (nropedido, item, cantidad, costo) VALUES ("+camponumeropedido+", \'"+i.getInsumo()+"\' , "+i.getCantidad()+", "+i.getCosto()+")");
				
			}
			
			stn.close();
			connection.close();
			JOptionPane.showMessageDialog(null, "Se guardó el registro con éxito", "Información",1);

			

		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			 JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar grabar los datos. Verifique que todos los campos esten completos y en el formato que corresponde", "Error",0);

			e2.printStackTrace();
		}
	}
	
	public static void marcarcancelada(int nropedido) throws SQLException {
		
		//hacer consulta SQL para marcar orden de pedido como CANCELADA
		 
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
		
			stn.executeUpdate("UPDATE ordenespedidos SET estado = \'CANCELADA\' WHERE nropedido = "+nropedido);
			
		
		
			stn.close();
		
		
			connection.close();
			
			//fin de consulta SQL
		
		
	}
	
	public static void consultaritems(int nropedido, ArrayList<ItemPedido> lista) {
		
		//CONSULTA SQL
				try {
					
					
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
					String consulta = "SELECT * FROM itemspedidos WHERE nropedido = "+nropedido;
					PreparedStatement stn = connection.prepareStatement(consulta);
					ResultSet rs = stn.executeQuery();
					while(rs.next()) {
						//Camion(String patente, String modelo, int kmrec, int costokm, int costoh, String fechacompra)
						ItemPedido aux = new ItemPedido();
						
						aux.setInsumo(rs.getString(1));
						aux.setCantidad(rs.getInt(2));
						aux.setCosto(rs.getInt(3));



						
						
						lista.add(aux);
					}
					
					
						stn.close();
					
					
						connection.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//fin de consulta SQL
		
	}
	

}
	
	
