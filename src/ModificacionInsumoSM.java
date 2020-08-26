import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;

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

public class ModificacionInsumoSM extends JFrame{
	JLabel descripcion = new JLabel("Descripcion");
	JLabel umedida = new JLabel("Unidad de Medida");
	JLabel costo = new JLabel("Costo");
	JLabel peso = new JLabel("Peso");
	JLabel densidad = new JLabel("Densidad");
	JLabel tipo = new JLabel("Tipo");
	JTextArea areatext = new JTextArea();
	
	JTextField campodescripcion = new JTextField(10);
	JTextField campoumedida = new JTextField(20);
	JTextField campocosto = new JTextField(5);
	JTextField campopeso = new JTextField(5);
	JTextField campodensidad = new JTextField(5);
	JTextField campotipo = new JTextField(10);
	
	
	
	
	JButton modificar = new JButton("Modificar");
	
	JPanel principal = new JPanel();
	String clave;
	
	JTable tabla  = new JTable(2,3);
	//String titulos[] = {"Patente", "Modelo", "KM Recorridos", "Costo KM", "Costo Hora", "Fecha de compra"};


	
	
	public ModificacionInsumoSM(String cpatente, String cmodelo, String ckmrec, String ccostokm, 
			String ccostohora, String cfecha, String clave){
		super("Modificar datos de Insumo");
		this.setVisible(true);
		this.campodescripcion.setText(cpatente);
		this.campoumedida.setText(cmodelo);
		this.campocosto.setText(ckmrec);
		this.campopeso.setText(ccostokm);
		this.campodensidad.setText(ccostohora);
		this.campotipo.setText(cfecha);
		this.clave = clave;
		
		
		
		
		
		
		this.setContentPane(principal);
	
		
		
		
		
		
		modificar.addActionListener(e->{
			if (campotipo.getText().equals("GENERAL") || campotipo.getText().equals("LIQUIDO")) {
				try {
					dao.ABMInsumo.modificar(campodescripcion.getText(), campoumedida.getText(), campocosto.getText(), campopeso.getText(), campodensidad.getText(), campotipo.getText());
					JOptionPane.showMessageDialog(null, "Se modific� el registro con �xito", "Informaci�n",1);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					 JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar grabar los datos. Verifique que todos los campos esten completos y en el formato que corresponde", "Error",0);

					//e1.printStackTrace();
				}
				
			}
			else
			{
				 JOptionPane.showMessageDialog(null, "El tipo debe ser GENERAL o LIQUIDO", "Error",0);

			}
			
			
		});
		
		

		
		principal.add(descripcion);
		principal.add(campodescripcion);
		principal.add(umedida);
		principal.add(campoumedida);
		principal.add(costo);
		principal.add(campocosto);
		principal.add(peso);
		principal.add(campopeso);
		principal.add(densidad);
		principal.add(campodensidad);
		principal.add(tipo);
		principal.add(campotipo);
		//principal.add(areatext);
		principal.add(modificar);
		
		
		
		
		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	

}