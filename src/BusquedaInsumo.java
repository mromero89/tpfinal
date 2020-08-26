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
	JButton modificar = new JButton("Modificar selección");
	
	JScrollPane a = new JScrollPane();
	
	JPanel principal = new JPanel();
	
	JTable tabla  = new JTable(2,3);


	
	
	public BusquedaInsumo(){
		super("Menu Busqueda Insumos");
		this.setVisible(true);
		
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		
	
		
		
		consulta.addActionListener(e->{
			
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
			
			
			
			
		});
		
		modificar.addActionListener(e-> {
			//ejemplo de obtencion de datos de una tabla
			if (tabla.getSelectedRow() != -1) {
				
				ModificacionInsumoSM smenu = new ModificacionInsumoSM(tabla.getValueAt(tabla.getSelectedRow(), 0).toString(), tabla.getValueAt(tabla.getSelectedRow(), 1).toString(), tabla.getValueAt(tabla.getSelectedRow(), 2).toString(), tabla.getValueAt(tabla.getSelectedRow(), 3).toString(), tabla.getValueAt(tabla.getSelectedRow(), 4).toString(), tabla.getValueAt(tabla.getSelectedRow(), 5).toString(), tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
			}
			
		});
		
		borrar.addActionListener(a -> {
			//ejemplo de obtencion de datos de una tabla
			if (tabla.getSelectedRow() != -1) {
				System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
				try {
					dao.ABMInsumo.borrar(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		});
		
		

		
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
		principal.add(consulta);
		principal.add(borrar);
		this.add(modificar, BorderLayout.SOUTH);
		
		this.add(principal, BorderLayout.NORTH);
		this.add(a, BorderLayout.CENTER);
		
		
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
			
			/*Consulta SQL para sumar cantidad total de Insumo*/
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
			
			
			
			
				stn.close();
			
			
				connection.close();
			/*Fin de Consulta SQL*/
			
			i++;
			
		}
		
		
		
		String titulos[] = {"Descripcion", "UnidadMedida", "Costo", "Peso", "Densidad", "Tipo", "Stock total del insumo"};


		JTable tablaresu = new JTable(aux, titulos);
		this.remove(a);
		tabla = new JTable(aux, titulos);


		a = new JScrollPane(tabla);

		this.add(a,BorderLayout.CENTER);
		
		
		
		this.revalidate();
		this.repaint();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		
	}
	
	
	
	
	
	

}