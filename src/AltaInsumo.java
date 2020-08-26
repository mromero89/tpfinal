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
		
			
		);
		
		principal.add(tipoinsumo);
		
		principal.add(descripcion);
		principal.add(campodescripcion);
		principal.add(umedida);
		principal.add(campoumedida);
		principal.add(costo);
		principal.add(campocosto);
		
		
		
		
		
		
		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	
	
	
	
	
	

}
