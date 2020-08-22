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
import dominio.InsumosPlantas;

public class PlantasStockMenor extends JFrame {
	JLabel planta = new JLabel("Nombre Planta");
	JLabel insumo = new JLabel("Nombre Insumo");
	
	JTextField campoplanta = new JTextField(10);
	JTextField campoinsumo = new JTextField(20);
	
	
	JButton consulta = new JButton("Consulta");
	
	JPanel principal = new JPanel();
	
	JTable tabla  = new JTable(2,3);
	
	JScrollPane a = new JScrollPane();

	//String titulos[] = {"Patente", "Modelo", "KM Recorridos", "Costo KM", "Costo Hora", "Fecha de compra"};


	
	
	public PlantasStockMenor(){
		super("Plantas con stock menor al punto de pedido");
		this.setVisible(true);
		
		
		this.setContentPane(principal);
		
		
		consulta.addActionListener(e->{
			
			try {
				consultar();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		
		
		
		
		
		
		
		principal.add(planta);
		principal.add(campoplanta);
		principal.add(insumo);
		principal.add(campoinsumo);
		principal.add(consulta);
		principal.add(a);
	
		
		
		
		
		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	
	private void consultar() throws SQLException {
		
		
		/*CONSULTA SQL*/
		
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

		if (!campoplanta.getText().equals(""))
			consulta = consulta + " AND nombreplanta = \'"+campoplanta.getText()+"\'";
		
		if (!campoinsumo.getText().equals(""))
			consulta = consulta + " AND insumo = \'"+campoinsumo.getText()+"\'";
		
		
		PreparedStatement stn = connection.prepareStatement(consulta);
		ResultSet rs = stn.executeQuery();
		
		ArrayList<InsumosPlantas> lista = new ArrayList<InsumosPlantas>();
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
		
		
		int tamano = lista.size();
		String [][]aux = new String [tamano][5];
		int i = 0;
		for (InsumosPlantas c : lista) {
			aux[i][0]=c.getInsumo();
			aux[i][1]=String.valueOf(c.getCantidad());
			aux[i][2]=String.valueOf(c.getPuntopedido());
			aux[i][3]=c.getNombreplanta();
			
			/*Consulta SQL*/
			connection = DriverManager.getConnection(
			        "jdbc:postgresql://localhost:5432/postgres",
			        "postgres", "wilson222");
			String ins = "SELECT SUM(cantidad) FROM insumosplantas WHERE insumo = \'"+c.getInsumo()+"\'";
			
			stn = connection.prepareStatement(ins);
			
			rs = stn.executeQuery();
			while(rs.next()) {
				aux[i][4]=String.valueOf(rs.getInt(1));
				

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
		String titulos[] = {"Nombre Insumo", "Cantidad", "Punto Pedido", "Nombre Planta", "Total de Stock"};


		JTable tablaresu = new JTable(aux, titulos);
		this.remove(tabla);
		tabla = new JTable(aux, titulos);
		
		
		//este anda bien
		principal.remove(a);
		a = new JScrollPane(tabla);

		principal.add(a,BorderLayout.CENTER);
		
		

		principal.revalidate();
		principal.repaint();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		
	}
	
	
	
	
	
	

}