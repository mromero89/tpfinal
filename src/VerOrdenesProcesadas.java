import java.sql.Statement;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.ABMRuta;
import dao.AMBPlanta;
import dominio.Camion;
import dominio.Insumo;
import dominio.ItemPedido;
import dominio.OrdenPedido;
import dominio.Planta;
import dominio.Ruta;

public class VerOrdenesProcesadas extends JFrame {
	
	JPanel principal = new JPanel();
	
	JTable tabla;
	
	
	
	VerOrdenesProcesadas() throws SQLException{
		
		
		
		super("Ver ordenes procesadas");
		//EJEMPLO DE CARGA DE GRAFO DE DISTANCIA
		ArrayList<Planta> listaplantas = AMBPlanta.todos();
		ArrayList<Ruta> listarutas = ABMRuta.todos();

		this.setVisible(true);
		
		
		
		
		this.setContentPane(principal);
	
		
		this.consultar();
		
		JButton agregar = new JButton("Marcar como Entregada");
		
		agregar.addActionListener(e->{
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
			
			
				stn.executeUpdate("UPDATE ordenespedidos SET estado = \'ENTREGADA\' WHERE nropedido = "+Integer.valueOf(this.tabla.getValueAt(tabla.getSelectedRow(), 0).toString()));
			
			
				
					stn.close();
				
			
			
				
					connection.close();
					JOptionPane.showMessageDialog(null, "Se guardó el registro con éxito", "Información",1);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

				
			
	
		});
		
	
		principal.add(agregar);
		
	
		
		
		
		this.pack();
	}
	
	
	
	private void consultar() {
		ArrayList<OrdenPedido> lista = new ArrayList<OrdenPedido>();
		try {
			//lista = dao.AMBCamion.busqueda(campopatente.getText(), campomodelo.getText(), campokm.getText(), campocostokm.getText(), campocostoh.getText(), campofechacompra.getText());
			
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
		int tamano = lista.size();
		String [][]aux = new String [tamano][5];
		int i = 0; int j = 0;
		for (OrdenPedido c : lista) {
			aux[i][0]=String.valueOf(c.getNropedido());
			aux[i][1]=c.getPlantaorigen();
			aux[i][2]=c.getPlantadestino();
			aux[i][3]=c.getFechaentrega();
			aux[i][4]=c.getEstado();
			i++;
			
			//areatext.append(i.getPatente()+"\n");
		}
		
		
		
		String titulos[] = {"Numero de pedido", "Planta de origen", "Planta Destino", "Fecha entrega", "Estado"};


		JTable tablaresu = new JTable(aux, titulos);
		//this.remove(tabla);
		tabla = new JTable(aux, titulos);
		
		//principal.add(tablaresu);
		//principal.add(tabla);
		
		//este anda bien
		//principal.remove(tabla);
		principal.add(new JScrollPane(tabla),BorderLayout.CENTER);
		
		
		
		principal.revalidate();
		principal.repaint();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		
	}
	
	

}
