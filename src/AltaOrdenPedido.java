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

import dao.ABMOrdenPedido;
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
				

				ABMOrdenPedido.guardarordenpedido(camponumeropedido.getText(), destino.getSelectedItem().toString(), campofechamaxima.getText(), items);
				
			
				
			}
			else {
				 JOptionPane.showMessageDialog(null, "Debe colocar la fecha en un formato AAAA/MM/DD", "Error",0);

			}
			});
		
		principal.add(destino);
		
		principal.add(fechamaxima);
		principal.add(campofechamaxima);
		principal.add(numeropedido);
		principal.add(camponumeropedido);
		principal.add(agregar);
		principal.add(guardar);
		
		
		
		
		
		
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
