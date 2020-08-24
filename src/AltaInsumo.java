import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class AltaInsumo extends JFrame {
	
	String[] tipos = {"", "GENERAL", "LIQUIDO"};
	private JComboBox tipoinsumo = new JComboBox(tipos);
	
	AltaInsumo(){
		super("Menu Alta Insumos");
		this.setVisible(true);
		//this.setLayout();
		
		//CargarCombos
		ArrayList<Planta> todas;
		
		
		//Campos comunes a todos los insumos
		JLabel descripcion = new JLabel("Descripcion");
		
		JTextField campodescripcion = new JTextField(10);
		
		JLabel umedida = new JLabel("Unidad de medida");
		
		JTextField campoumedida = new JTextField(10);
		

		JLabel costo = new JLabel("Costo");
		
		JTextField campocosto = new JTextField(10);
		
		//Campos insumos generales

		JLabel peso = new JLabel("Peso en kg");
		
		JTextField campopeso = new JTextField(10);
		
		//Campos insumos liquidos

		JLabel densidad = new JLabel("Densidad");
		
		JTextField campodensidad = new JTextField(10);
		
		
		
		
		JTable tabla = new JTable(2,3);
		//tabla.addColumn("Nombre");
		
		
		
		JPanel principal = new JPanel();
		this.setContentPane(principal);
	
		
		
		JButton guardar = new JButton("Guardar");
		JButton limpiar = new JButton("Limpiar");
		//JButton consulta = new JButton("Consulta");
		
		
		limpiar.addActionListener(e-> {
			campodescripcion.setText("");
			campoumedida.setText("");
			campocosto.setText("");
			campopeso.setText("");
			campodensidad.setText("");
		});
		
		
		tipoinsumo.addActionListener(e-> {
			if (tipoinsumo.getSelectedIndex() == 1) {
				//System.out.println("insumo general");
				principal.add(peso);
				principal.add(campopeso);
				principal.remove(densidad);
				principal.remove(campodensidad);
				principal.add(guardar);
				principal.add(limpiar);
				
				principal.repaint();
				principal.revalidate();
			}
			else
				if (tipoinsumo.getSelectedIndex() == 2)
				{
					//System.out.println("Insumo liquido");
					principal.add(densidad);
					principal.add(campodensidad);
					principal.remove(peso);
					principal.remove(campopeso);
					principal.add(guardar);
					principal.add(limpiar);
					principal.repaint();
					principal.revalidate();
				}
			}

		);
		
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
		if (tipoinsumo.getSelectedIndex() == 1) {
				System.out.println("insumo general");
				try {
					dao.ABMInsumo.altainsumogeneral(campodescripcion.getText(), campoumedida.getText(), campocosto.getText(), campopeso.getText());
					JOptionPane.showMessageDialog(null, "Se guardó el registro con éxito", "Información",1);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					 JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar grabar los datos. Verifique que todos los campos esten completos y en el formato que corresponde", "Error",0);
				}
			}
			else
				if (tipoinsumo.getSelectedIndex() == 2)
				{
					try {
						dao.ABMInsumo.altainsumoliquido(campodescripcion.getText(), campoumedida.getText(), campocosto.getText(), campodensidad.getText());
						JOptionPane.showMessageDialog(null, "Se guardó el registro con éxito", "Información",1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						 JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar grabar los datos. Verifique que todos los campos esten completos y en el formato que corresponde", "Error",0);
					}


				}
			}
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
		);
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
		principal.add(tipoinsumo);
		
		principal.add(descripcion);
		principal.add(campodescripcion);
		principal.add(umedida);
		principal.add(campoumedida);
		//principal.add(duracion);
		principal.add(costo);
		principal.add(campocosto);
		
		
		
		
		
		
		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	
	
	
	
	
	

}
