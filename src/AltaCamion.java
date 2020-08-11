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

public class AltaCamion extends JFrame {
	
	AltaCamion(){
		super("Menu Alta Camion");
		this.setVisible(true);
		//this.setLayout();
		
		JLabel patente = new JLabel("Patente");
		JLabel modelo = new JLabel("Modelo");
		JLabel kmrec = new JLabel("KM recorridos");
		JLabel costokm = new JLabel("Costo por KM");
		JLabel costoh = new JLabel("Costo por Hora");
		JLabel fechacompra = new JLabel("Fecha de Compra");
		JTextArea areatext = new JTextArea();
		
		JTextField campopatente = new JTextField(10);
		JTextField campomodelo = new JTextField(20);
		JTextField campokm = new JTextField(5);
		JTextField campocostokm = new JTextField(5);
		JTextField campocostoh = new JTextField(5);
		JTextField campofechacompra = new JTextField(10);
		
		JTable tabla = new JTable(2,3);
		//tabla.addColumn("Nombre");
		
		
		
		JPanel principal = new JPanel();
		this.setContentPane(principal);
	
		
		
		JButton guardar = new JButton("Guardar");
		JButton limpiar = new JButton("Limpiar");
		JButton consulta = new JButton("Consulta");
		
		
		
		consulta.addActionListener(e->{
			ArrayList<Camion> lista = new ArrayList<Camion>();
			try {
				lista = dao.AMBCamion.todos();
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
			principal.revalidate();
			
		});
		
		limpiar.addActionListener(a -> {
			campopatente.setText("");
			campomodelo.setText("");
			campokm.setText("");
			campocostokm.setText("");
			campocostoh.setText("");
			campofechacompra.setText("");
			
		});
		
		guardar.addActionListener(e -> {
			try {
				//dao.AMBCamion.altacamion("abc123", "scania", 12, 1, 6, "2020/12/12");
				
				dao.AMBCamion.altacamion(campopatente.getText(), campomodelo.getText(), Integer.parseInt(campokm.getText()), Integer.parseInt(campocostokm.getText()), Integer.parseInt(campocostoh.getText()), campofechacompra.getText());
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
		
		principal.add(patente);
		principal.add(campopatente);
		principal.add(modelo);
		principal.add(campomodelo);
		principal.add(kmrec);
		principal.add(campokm);
		principal.add(costokm);
		principal.add(campocostokm);
		principal.add(costoh);
		principal.add(campocostoh);
		principal.add(fechacompra);
		principal.add(campofechacompra);
		principal.add(guardar);
		principal.add(limpiar);
		principal.add(areatext);
		principal.add(consulta);
		
		
		
		
		this.pack();
	}
	
	
	
	
	
	

}
