import java.sql.Statement;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import dominio.Camion;
import dominio.Insumo;

public class BusquedaInsumo extends JFrame {
	JLabel descripcion = new JLabel("Descripcion");
	JLabel unidadmedida = new JLabel("Unidad Medida");
	JLabel costo = new JLabel("Costo");
	JLabel peso = new JLabel("Peso");
	JLabel densidad = new JLabel("Densidad");
	JLabel tipo = new JLabel("Tipo");
	JTextArea areatext = new JTextArea();
	
	JTextField campodescripcion = new JTextField(10);
	JTextField campomedida = new JTextField(20);
	JTextField campocosto = new JTextField(5);
	JTextField campopeso = new JTextField(5);
	JTextField campodensidad = new JTextField(5);
	JTextField campotipo = new JTextField(10);
	
	
	JButton limpiar = new JButton("Limpiar");
	JButton consulta = new JButton("Consulta");
	JButton borrar = new JButton("Borrar");
	
	JPanel principal = new JPanel();
	
	JTable tabla  = new JTable(2,3);
	//String titulos[] = {"Patente", "Modelo", "KM Recorridos", "Costo KM", "Costo Hora", "Fecha de compra"};


	
	
	public BusquedaInsumo(){
		super("Menu Busqueda Insumos");
		this.setVisible(true);
		//this.setLayout();
		
		
		
		//tabla.addColumn("Nombre");
		
		
		
		this.setContentPane(principal);
	
		
		
		
		
		
		consulta.addActionListener(e->{/*
			ArrayList<Camion> lista = new ArrayList<Camion>();
			try {
				lista = dao.AMBCamion.busqueda(campopatente.getText(), campomodelo.getText(), campokm.getText(), campocostokm.getText(), campocostoh.getText(), campofechacompra.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int tamano = lista.size();
			String [][]aux = new String [tamano][6];
			int i = 0; int j = 0;
			for (Camion c : lista) {
				aux[i][0]=c.getPatente();
				aux[i][1]=c.getModelo();
				aux[i][2]=String.valueOf(c.getKmrec());
				aux[i][3]=String.valueOf(c.getCostokm());
				aux[i][4]=String.valueOf(c.getCostoh());
				aux[i][5]=c.getFechacompra();
				i++;
				
				//areatext.append(i.getPatente()+"\n");
			}
			
			String titulos[] = {"Patente", "Modelo", "KM Recorridos", "Costo KM", "Costo Hora", "Fecha de compra"};
			
			
			JTable tablaresu = new JTable(aux, titulos);
			principal.add(tablaresu);
			principal.revalidate();*/
			
			try {
				consultar();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		limpiar.addActionListener(a -> {
			campodescripcion.setText("");
			campomedida.setText("");
			campocosto.setText("");
			campopeso.setText("");
			campodensidad.setText("");
			campotipo.setText("");
			
			//ejemplo de obtencion de datos de una tabla
			if (tabla.getSelectedRow() != -1) {
				System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
				ModificacionCamionSM smenu = new ModificacionCamionSM(tabla.getValueAt(tabla.getSelectedRow(), 0).toString(), tabla.getValueAt(tabla.getSelectedRow(), 1).toString(), tabla.getValueAt(tabla.getSelectedRow(), 2).toString(), tabla.getValueAt(tabla.getSelectedRow(), 3).toString(), tabla.getValueAt(tabla.getSelectedRow(), 4).toString(), tabla.getValueAt(tabla.getSelectedRow(), 5).toString(), tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
			}
			
			
		});
		
		borrar.addActionListener(a -> {
			//ejemplo de obtencion de datos de una tabla
			if (tabla.getSelectedRow() != -1) {
				System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
				try {
					dao.AMBCamion.borrarcamion(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		});
		
		
		/*Gestion de base de datos
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
		stn.execute("INSERT INTO \"Libro\" (id, nombre) VALUES (4, \'Oscar\')");
		
		stn.close();
		connection.close();
	
	FIn de gestion de base de datos
	*/
		
		principal.add(descripcion);
		principal.add(campodescripcion);
		principal.add(unidadmedida);
		principal.add(campomedida);
		principal.add(costo);
		principal.add(campocosto);
		principal.add(peso);
		principal.add(campopeso);
		principal.add(densidad);
		principal.add(campodensidad);
		principal.add(tipo);
		principal.add(campotipo);
		principal.add(limpiar);
		principal.add(areatext);
		principal.add(consulta);
		principal.add(borrar);
		//principal.add(tabla);
		
		
		
		
		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	
	private void consultar() throws SQLException {
		ArrayList<Insumo> lista = new ArrayList<Insumo>();
		try {
			lista = dao.ABMInsumo.busqueda(campodescripcion.getText(), campomedida.getText(), campocosto.getText(), campopeso.getText(), campodensidad.getText(), campotipo.getText());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int tamano = lista.size();
		String [][]aux = new String [tamano][7];
		int i = 0; int j = 0;
		for (Insumo c : lista) {
			aux[i][0]=c.getDescripcion();
			aux[i][1]=c.getUnidadmedida();
			aux[i][2]=String.valueOf(c.getCosto());
			aux[i][3]=String.valueOf(c.getPeso());
			aux[i][4]=String.valueOf(c.getDensidad());
			aux[i][5]=c.getTipo();
			
			/*Consulta SQL*/
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
			String ins = "SELECT SUM(cantidad) FROM insumosplantas WHERE insumo = \'"+c.getDescripcion()+"\'";
			
			PreparedStatement stn = connection.prepareStatement(ins);
			
			ResultSet rs = stn.executeQuery();
			while(rs.next()) {
				aux[i][6]=String.valueOf(rs.getInt(1));
				

			}
			//stn.execute("INSERT INTO \"Libro\" (id, nombre) VALUES (4, \'Oscar\')");
			
			
			
			
				stn.close();
			
			
				connection.close();
			/*Fin de Consulta SQL*/
			
			i++;
			
			//SELECT SUM(cantidad) FROM insumosplantas WHERE insumo = 'cal';
		}
		
		
		//principal.remove(tabla);
		//principal.revalidate();
		//pack();
		String titulos[] = {"Descripcion", "UnidadMedida", "Costo", "Peso", "Densidad", "Tipo", "Stock total del insumo"};


		JTable tablaresu = new JTable(aux, titulos);
		this.remove(tabla);
		tabla = new JTable(aux, titulos);
		
		//principal.add(tablaresu);
		//principal.add(tabla);
		
		//este anda bien
		principal.remove(tabla);
		principal.add(new JScrollPane(tabla),BorderLayout.CENTER);
		
		
		//principal.add(tabla);
		//tabla.repaint();
		
		//tabla.repaint();

		principal.revalidate();
		principal.repaint();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		
	}
	
	
	
	
	
	

}