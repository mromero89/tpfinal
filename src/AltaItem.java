import java.sql.Statement;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import dominio.Camion;
import dominio.Insumo;
import dominio.ItemPedido;
import dominio.Planta;
import dominio.Ruta;

public class AltaItem extends JFrame {
	
	
	private JComboBox insumo;
	
	ArrayList<ItemPedido> items = new ArrayList<ItemPedido>();
	ArrayList<Insumo> todos = new ArrayList<Insumo>();
	int costoitem;

	
	public void recibirItem(ItemPedido i) {
		items.add(i);
	}
	
	AltaItem(){
		super("Agregar Item a la orden de pedido");
		this.setVisible(true);
		
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);

		//this.setLayout();
		JPanel principal = new JPanel();
		JPanel sur = new JPanel();

		//CargarCombos
		
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
		

		
		
		
		
	
		
		
		JLabel cantidad = new JLabel("Cantidad");
		
		JTextField campocantidad = new JTextField(10);
		
		JLabel costo = new JLabel("Costo del item: ");
		JLabel campocosto = new JLabel();
		
		
		
		
		
		JTable tabla = new JTable(2,3);
		
	
		
		
		JButton guardar = new JButton("Agregar a la orden");
		JButton calcularcosto = new JButton("Calcular costo");
		
		guardar.addActionListener(e-> {
			ItemPedido item = new ItemPedido();
			item.setCantidad(Integer.valueOf(campocantidad.getText()));
			item.setCosto(costoitem);
			item.setInsumo(insumo.getSelectedItem().toString());
			AltaOrdenPedido.recibirItem(item);
			this.dispose();
			
		});
		
		calcularcosto.addActionListener(e-> {
			for (Insumo i : todos) {
				if (i.getDescripcion().equals(insumo.getSelectedItem())) {
					costoitem = i.getCosto() * Integer.parseInt(campocantidad.getText());
				}
				campocosto.setText(String.valueOf(costoitem));
				sur.add(costo);
				sur.add(campocosto);
				sur.add(guardar);
				this.repaint();
				principal.revalidate();
				
			}
		});
		
		
		
		principal.add(insumo);
		
		principal.add(cantidad);
		principal.add(campocantidad);
		principal.add(calcularcosto);
		sur.add(costo);
		sur.add(campocosto);
		
		this.add(principal, BorderLayout.NORTH);
		this.add(sur, BorderLayout.SOUTH);
		
		
		
		this.pack();
	}
	
	
	
	
	
	

}
