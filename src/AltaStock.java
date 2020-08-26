import java.sql.Statement;
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
import dominio.Camion;
import dominio.Insumo;
import dominio.Planta;

public class AltaStock extends JFrame {
	
	private JComboBox inicio, insumo;
	
	AltaStock(){
		super("Menu Alta Stock");
		this.setVisible(true);
		//this.setLayout();
		
		//CargarCombos
		ArrayList<Planta> todas;
		ArrayList<Insumo> todos;

		
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
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			todos = dao.ABMInsumo.todos();
			int tamanoi = todos.size();
			String [] nombreInsumos = new String[tamanoi];
			int k = 0;
			for (Insumo i : todos) {
				nombreInsumos[k] = i.getDescripcion();
				k++;
				
			}
			insumo = new JComboBox(nombreInsumos);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	
		
		
		JLabel cantidadinsumo = new JLabel("Cantidad de insumo");
		
		JFormattedTextField campocantidad = new JFormattedTextField(new Integer(0));
		campocantidad.setPreferredSize(new Dimension(50,20));
		
		JLabel puntopedido = new JLabel("Punto de pedido");
		
		JFormattedTextField campopuntopedido = new JFormattedTextField(new Integer(0));
		campopuntopedido.setPreferredSize(new Dimension(50,20));
		
		JLabel pesomaximo = new JLabel("Peso Maximo en KG");
		
		JTextField campopeso = new JTextField(10);
		
		JLabel nombre = new JLabel("Nombre Ruta");
		
		JTextField camponombre = new JTextField(10);
		
		
		
		JTable tabla = new JTable(2,3);
		
		
		
		JPanel principal = new JPanel();
		this.setContentPane(principal);
	
		
		
		JButton guardar = new JButton("Guardar");
		JButton limpiar = new JButton("Limpiar");
		
		limpiar.addActionListener(e-> {
			campocantidad.setText("");
			campopuntopedido.setText("");
			campopeso.setText("");


		});
		
		
		
		

		guardar.addActionListener(e -> {
			try {
				dao.ABMStockPlanta.altastock(inicio.getSelectedItem().toString(), insumo.getSelectedItem().toString(), campocantidad.getText(), campopuntopedido.getText());
				JOptionPane.showMessageDialog(null, "Se guardó el registro con éxito", "Información",1);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		principal.add(inicio);
		principal.add(insumo);

		
		
		principal.add(cantidadinsumo);
		principal.add(campocantidad);
		principal.add(puntopedido);
		principal.add(campopuntopedido);
		
		principal.add(guardar);
		principal.add(limpiar);
		
		
		
		
		this.pack();
	}
}
