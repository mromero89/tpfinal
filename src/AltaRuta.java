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
		
		
		
		JPanel principal = new JPanel();
		JPanel botonera = new JPanel();
	
		
		
		JButton guardar = new JButton("Guardar");
		JButton limpiar = new JButton("Limpiar");
		JButton consulta = new JButton("Consulta");
		
		limpiar.addActionListener(e-> {
			campodistancia.setText("");
			campoduracion.setText("");
			campopeso.setText("");


		});
		
		
		
		
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
			
			
		});
		
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
		
		
		
		
		
		
		this.pack();
	}
	
	
	
	
	
	

}
