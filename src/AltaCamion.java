import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
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

public class AltaCamion extends JFrame {
	
	   JFormattedTextField fecha;

	
	AltaCamion(){
		

		
		super("Menu Alta Camion");
		
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		
		this.setVisible(true);
		
		JPanel campos = new JPanel();
		JPanel botones = new JPanel();
		
		
		//this.setLayout();
		 MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("####/##/##");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		fecha = new JFormattedTextField(mascara);
		fecha.setPreferredSize(new Dimension(80,20));

		JLabel patente = new JLabel("Patente");
		JLabel modelo = new JLabel("Modelo");
		JLabel kmrec = new JLabel("KM recorridos");
		JLabel costokm = new JLabel("Costo por KM");
		JLabel costoh = new JLabel("Costo por Hora");
		JLabel fechacompra = new JLabel("Fecha de Compra (AAAA/MM/DD)");
		//JTextArea areatext = new JTextArea();
		
		Dimension tamanocampos = new Dimension(50,20);
		JTextField campopatente = new JTextField(10);
		
		JTextField campomodelo = new JTextField(20);
		/*JTextField campokm = new JTextField(5);
		JTextField campocostokm = new JTextField(5);
		JTextField campocostoh = new JTextField(5);*/
		JFormattedTextField campokm = new JFormattedTextField(new Integer(0));
		campokm.setPreferredSize(tamanocampos);
		JFormattedTextField campocostokm = new JFormattedTextField(new Integer(0));
		campocostokm.setPreferredSize(tamanocampos);
		JFormattedTextField campocostoh = new JFormattedTextField(new Integer(0));
		campocostoh.setPreferredSize(tamanocampos);
		JTextField campofechacompra = new JTextField(10);
		
		JTable tabla = new JTable(2,3);
		//tabla.addColumn("Nombre");
		
		
		
		/*JPanel principal = new JPanel();
		this.setContentPane(principal);*/
	
		
		
		JButton guardar = new JButton("Guardar");
		JButton limpiar = new JButton("Limpiar");
		//JButton consulta = new JButton("Consulta");
		
		
		
		/*consulta.addActionListener(e->{
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
			
		});*/
		
		limpiar.addActionListener(a -> {
			campopatente.setText("");
			campomodelo.setText("");
			campokm.setText("");
			campocostokm.setText("");
			campocostoh.setText("");
			fecha.setText("");
			
			
			
		});
		
		guardar.addActionListener(e -> {
			if (validar()) {
				System.out.println("Todo bien cargado");
				try {
					//dao.AMBCamion.altacamion("abc123", "scania", 12, 1, 6, "2020/12/12");
					
					dao.AMBCamion.altacamion(campopatente.getText(), campomodelo.getText(), Integer.parseInt(campokm.getText()), Integer.parseInt(campocostokm.getText()), Integer.parseInt(campocostoh.getText()), fecha.getText());
					JOptionPane.showMessageDialog(null, "Se guardó el registro con éxito", "Información",1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					 JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar grabar los datos. Verifique que todos los campos esten completos y en el formato que corresponde", "Error",0);
				}
			}
			else {
				System.out.println("Falla algo");
				 JOptionPane.showMessageDialog(null, "Debe colocar la fecha en un formato AAAA/MM/DD", "Error",0);

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
		
		campos.add(patente);
		campos.add(campopatente);
		campos.add(modelo);
		campos.add(campomodelo);
		campos.add(kmrec);
		campos.add(campokm);
		campos.add(costokm);
		campos.add(campocostokm);
		campos.add(costoh);
		campos.add(campocostoh);
		campos.add(fechacompra);
		//principal.add(campofechacompra);
		campos.add(fecha);
		botones.add(guardar);
		botones.add(limpiar);
		//principal.add(consulta);
		
		this.add(campos, BorderLayout.NORTH);
		this.add(botones, BorderLayout.SOUTH);
		
		
		
		
		this.pack();
	}
	
	private boolean validar() {

		//comprobacion de fecha
		 String campo = fecha.getText();
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
