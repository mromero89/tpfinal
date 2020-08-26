import java.sql.Statement;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import dominio.Camion;
import dominio.Planta;

public class AltaPlanta extends JFrame {
	JTable tabla = new JTable();
	JScrollPane a = new JScrollPane();

	
	AltaPlanta(){
		super("Menu Alta Planta");
		this.setVisible(true);
		//this.setLayout();
		
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		
		
		JLabel nombre = new JLabel("Nombre de la Planta");
		
		JTextField camponombre = new JTextField(10);
		
		
		JPanel principal = new JPanel();

		
		
		JButton guardar = new JButton("Guardar");
		JButton limpiar = new JButton("Limpiar");
		JButton consulta = new JButton("Ver todas las plantas");
		JButton borrar = new JButton("Borrar");

		
		limpiar.addActionListener(e-> {
			camponombre.setText("");
		});
		
		consulta.addActionListener(e->{
			this.consultar();
		});
		
		
		guardar.addActionListener(e -> {
			try {
				dao.AMBPlanta.altaplanta(camponombre.getText());
				this.consultar();
				JOptionPane.showMessageDialog(null, "Se guardó el registro con éxito", "Información",1);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		borrar.addActionListener(a -> {
			if (tabla.getSelectedRow() != -1) {
				try {
					dao.AMBPlanta.borrarplanta(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			this.consultar();
			
		});
		
		
		
		
		principal.add(nombre);
		principal.add(camponombre);
		principal.add(guardar);
		principal.add(limpiar);
		principal.add(consulta);
		principal.add(borrar);
		
		this.add(principal, BorderLayout.NORTH);
		this.add(a, BorderLayout.CENTER);
		
		
		
		
		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	
	
	private void consultar() {
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
			
		}
		
		String titulos[] = {"Nombre"};
		
		this.remove(a);
		
		tabla = new JTable(aux, titulos);
		a = new JScrollPane(tabla);

		this.add(a, BorderLayout.CENTER);
		
		

		this.revalidate();
		this.repaint();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	
	
	
	

}
