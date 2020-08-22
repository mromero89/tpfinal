import java.sql.Statement;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import dominio.Camion;
import dominio.Planta;
import dominio.Ruta;

public class AltaRuta extends JFrame {
	
	private JComboBox inicio, destino;
	
	AltaRuta(){
		super("Menu Alta Ruta");
		this.setVisible(true);
		//this.setLayout();
		

		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		
		
		//CargarCombos
		ArrayList<Planta> todas;
		
		try {
			todas = dao.AMBPlanta.todos();
			int tamano = todas.size();
			String[] nombrePlantas = new String[tamano];
			int j=0;
			for (Planta i : todas) {
				nombrePlantas[j] = i.getNombre();
				j++;
				
				

			}
			inicio = new JComboBox(nombrePlantas);
			destino = new JComboBox(nombrePlantas);
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
	
		Dimension tamanocampos = new Dimension(50,20);

		/*
		 JFormattedTextField campokm = new JFormattedTextField(new Integer(0));
		campokm.setPreferredSize(tamanocampos);*/
		
		JLabel distancia = new JLabel("Distancia en KM");
		JLabel linicio = new JLabel("Inicio");
		JLabel ldestino = new JLabel("Destino");
		
		JFormattedTextField campodistancia = new JFormattedTextField(new Integer(0));
		campodistancia.setPreferredSize(tamanocampos);
		
		JLabel duracion = new JLabel("Duracion horas");
		
		JFormattedTextField campoduracion = new JFormattedTextField(new Integer(0));
		campoduracion.setPreferredSize(tamanocampos);
		
		JLabel pesomaximo = new JLabel("Peso Maximo en KG");
		
		JFormattedTextField campopeso = new JFormattedTextField(new Integer(0));
		campopeso.setPreferredSize(tamanocampos);
		
		JLabel nombre = new JLabel("Nombre Ruta");
		
		JTextField camponombre = new JTextField(10);
		
		
		
		JTable tabla = new JTable(2,3);
		//tabla.addColumn("Nombre");
		
		
		
		JPanel principal = new JPanel();
		//this.setContentPane(principal);
		JPanel botonera = new JPanel();
	
		
		
		JButton guardar = new JButton("Guardar");
		JButton limpiar = new JButton("Limpiar");
		JButton consulta = new JButton("Consulta");
		
		limpiar.addActionListener(e-> {
			campodistancia.setText("");
			campoduracion.setText("");
			campopeso.setText("");


		});
		
		/*
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
		*/
		
		/*
		limpiar.addActionListener(a -> {
			campopatente.setText("");
			campomodelo.setText("");
			campokm.setText("");
			campocostokm.setText("");
			campocostoh.setText("");
			campofechacompra.setText("");
			
		});*/
		
		
		/*public static void altaruta(String origen, String destino, String distanciakm, String duracionh, String pesomax, String nombre) throws SQLException  {
	*/
		guardar.addActionListener(e -> {
			try {
				if (inicio.getSelectedItem().toString().equals(destino.getSelectedItem().toString())){
					 JOptionPane.showMessageDialog(null, "Las ciudades de inicio y destino deben ser distintas", "Error",0);

		}
				else {
				dao.ABMRuta.altaruta(inicio.getSelectedItem().toString(), destino.getSelectedItem().toString(), campodistancia.getText(), campoduracion.getText(), campopeso.getText(), camponombre.getText());
				JOptionPane.showMessageDialog(null, "Se guardó el registro con éxito", "Información",1);
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*
			try {
				//dao.AMBCamion.altacamion("abc123", "scania", 12, 1, 6, "2020/12/12");
				dao.AMBPlanta.altaplanta(camponombre.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			
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
		principal.add(linicio);
		principal.add(inicio);
		principal.add(ldestino);
		principal.add(destino);
		
		principal.add(distancia);
		principal.add(campodistancia);
		principal.add(duracion);
		principal.add(campoduracion);
		//principal.add(duracion);
		principal.add(pesomaximo);
		principal.add(campopeso);
		principal.add(nombre);
		principal.add(camponombre);
		
		this.add(principal, BorderLayout.NORTH);
		
		botonera.add(guardar);
		botonera.add(limpiar);
		botonera.add(consulta);
		
		this.add(botonera, BorderLayout.SOUTH);
		
		
		/*
		 * 	JLabel distancia = new JLabel("Distancia en KM");
		
		JTextField campodistancia = new JTextField(10);
		
		JLabel duracion = new JLabel("Duracion horas");
		
		JTextField campoduracion = new JTextField(10);
		
		
		JLabel pesomaximo = new JLabel("Peso Maximo en KG");
		
		JTextField campopeso = new JTextField(10);*/
		
		
		
		this.pack();
	}
	
	
	
	
	
	

}
