import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import dominio.Camion;
import dominio.Planta;

public class AltaPlanta extends JFrame {
	
	AltaPlanta(){
		super("Menu Alta Planta");
		this.setVisible(true);
		//this.setLayout();
		
		JLabel nombre = new JLabel("Nombre de la Planta");
		
		JTextField camponombre = new JTextField(10);
		
		JTable tabla = new JTable(2,3);
		//tabla.addColumn("Nombre");
		
		
		
		JPanel principal = new JPanel();
		this.setContentPane(principal);
	
		
		
		JButton guardar = new JButton("Guardar");
		JButton limpiar = new JButton("Limpiar");
		JButton consulta = new JButton("Consulta");
		
		limpiar.addActionListener(e-> {
			camponombre.setText("");
		});
		
		consulta.addActionListener(e->{
			ArrayList<Planta> lista = new ArrayList<Planta>();
			try {
				lista = dao.AMBPlanta.todos();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int tamano = lista.size();
			String [][] aux = new String [tamano][1];
			int i = 0; int j = 0;
			for (Planta c : lista) {
				aux[i][0]=c.getNombre();
				
				i++;
				
				//areatext.append(i.getPatente()+"\n");
			}
			
			String titulos[] = {"Nombre"};
			
			
			JTable tablaresu = new JTable(aux, titulos);
			principal.add(tablaresu);
			principal.revalidate();
			
		});
		/*
		limpiar.addActionListener(a -> {
			campopatente.setText("");
			campomodelo.setText("");
			campokm.setText("");
			campocostokm.setText("");
			campocostoh.setText("");
			campofechacompra.setText("");
			
		});*/
		
		guardar.addActionListener(e -> {
			try {
				//dao.AMBCamion.altacamion("abc123", "scania", 12, 1, 6, "2020/12/12");
				dao.AMBPlanta.altaplanta(camponombre.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//dao.AMBCamion.altacamion(patente.getText(), modelo.getText(), Integer.parseInt(kmrec.getText()), Integer.parseInt(costokm.getText()), Integer.parseInt(costohora.getText()), fechacompra.getText());
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
		
		principal.add(nombre);
		principal.add(camponombre);
		principal.add(guardar);
		principal.add(limpiar);
		principal.add(consulta);
		
		
		
		
		this.pack();
	}
	
	
	
	
	
	

}
