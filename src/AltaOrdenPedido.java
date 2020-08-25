import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
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
import javax.swing.text.MaskFormatter;

import dominio.Camion;
import dominio.ItemPedido;
import dominio.Planta;
import dominio.Ruta;

public class AltaOrdenPedido extends JFrame {
	
	private JComboBox inicio, destino;
	private JFormattedTextField campofechamaxima;
	
	static ArrayList<ItemPedido> items = new ArrayList<ItemPedido>();
	
	public static void recibirItem(ItemPedido i) {
		items.add(i);
		System.out.println("Item agregado: "+i.getInsumo());
	}
	
	AltaOrdenPedido(){
		super("Menu Alta de Orden de Pedido");
		this.setVisible(true);
		
		 MaskFormatter mascara = null;
			try {
				mascara = new MaskFormatter("####/##/##");
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		//this.setLayout();
		items = new ArrayList<ItemPedido>();
		
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
		
		
	
		
		
		JLabel fechamaxima = new JLabel("Fecha maxima");
		
		
		campofechamaxima = new JFormattedTextField(mascara);
		campofechamaxima.setPreferredSize(new Dimension(80,20));
		
		JLabel numeropedido = new JLabel("Numero de pedido");
		JTextField camponumeropedido = new JTextField();
		camponumeropedido.setPreferredSize(new Dimension(80,20));
		
		
		
		
		JTable tabla = new JTable(2,3);
		//tabla.addColumn("Nombre");
		
		
		
		JPanel principal = new JPanel();
		this.setContentPane(principal);
	
		
		
		JButton guardar = new JButton("Guardar orden de pedido");
		JButton agregar = new JButton("Agregar item");
		
		agregar.addActionListener(e->{
			AltaItem alta = new AltaItem();
		});
		
		
		
		guardar.addActionListener(e -> {
			if (this.validar()) {
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
					String palabra1 = "Sentencia 1: INSERT INTO ordenespedidos (nropedido, plantadestino, fechaentrega, estado) VALUES ("+camponumeropedido.getText()+", \'"+destino.getSelectedItem().toString()+"\', \'"+campofechamaxima.getText()+"\', \'CREADA\'";
					System.out.println(palabra1);
					stn.executeUpdate("INSERT INTO ordenespedidos (nropedido, plantadestino, fechaentrega, estado) VALUES ("+camponumeropedido.getText()+", \'"+destino.getSelectedItem().toString()+"\', \'"+campofechamaxima.getText()+"\', \'CREADA\')");
					
					for (ItemPedido i : items) {
						stn = connection.createStatement();
						String palabra2 = "Sentencia2: "+"INSERT INTO itemspedidos (nropedido, item, cantidad, costo) VALUES ("+camponumeropedido.getText()+", \'"+i.getInsumo()+"\' , "+i.getCantidad()+", "+i.getCosto()+")";
						System.out.println(palabra2);
						stn.executeUpdate("INSERT INTO itemspedidos (nropedido, item, cantidad, costo) VALUES ("+camponumeropedido.getText()+", \'"+i.getInsumo()+"\' , "+i.getCantidad()+", "+i.getCosto()+")");
						
					}
					
					stn.close();
					connection.close();
					JOptionPane.showMessageDialog(null, "Se guardó el registro con éxito", "Información",1);

					

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					 JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar grabar los datos. Verifique que todos los campos esten completos y en el formato que corresponde", "Error",0);

					e2.printStackTrace();
				}
				//stn.execute("INSERT INTO \"Libro\" (id, nombre) VALUES (4, \'Oscar\')");
				
				
			
				
				/*
				try {
					dao.ABMRuta.altaruta(inicio.getSelectedItem().toString(), destino.getSelectedItem().toString(), campodistancia.getText(), campoduracion.getText(), campopeso.getText(), camponombre.getText());
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
			
			}
			else {
				 JOptionPane.showMessageDialog(null, "Debe colocar la fecha en un formato AAAA/MM/DD", "Error",0);

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
		//principal.add(inicio);
		principal.add(destino);
		
		principal.add(fechamaxima);
		principal.add(campofechamaxima);
		principal.add(numeropedido);
		principal.add(camponumeropedido);
		principal.add(agregar);
		principal.add(guardar);
		
		
		
		/*
		 * 	JLabel distancia = new JLabel("Distancia en KM");
		
		JTextField campodistancia = new JTextField(10);
		
		JLabel duracion = new JLabel("Duracion horas");
		
		JTextField campoduracion = new JTextField(10);
		
		
		JLabel pesomaximo = new JLabel("Peso Maximo en KG");
		
		JTextField campopeso = new JTextField(10);*/
		
		
		
		this.pack();
	}
	
	
	private boolean validar() {

		//comprobacion de fecha
		 String campo = campofechamaxima.getText();
		   String[] resul = campo.split("/");
		   boolean valido = true;
		   if (Integer.parseInt(resul[1]) < 1 || Integer.parseInt(resul[1]) > 12)
			   valido = false;
		   if (Integer.parseInt(resul[2]) < 1 || Integer.parseInt(resul[2]) > 31)
			   valido = false;
		   for (int i = 0; i< resul.length; i++) {
			   System.out.println(resul[i]);
		   }
		   System.out.println(valido);
		   return valido;
		   
	}
	
	
	

}
