import java.sql.Statement;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.ABMOrdenPedido;
import dao.ABMRuta;
import dao.AMBPlanta;
import dominio.Camion;
import dominio.Insumo;
import dominio.ItemPedido;
import dominio.OrdenPedido;
import dominio.Planta;
import dominio.Ruta;

public class VerOrdenesCreadas extends JFrame {
	
	JPanel principal = new JPanel();
	
	JTable tabla;
	
	
	
	VerOrdenesCreadas() throws SQLException{
		
		
		
		super("Ver ordenes creadas");
		ArrayList<Planta> listaplantas = AMBPlanta.todos();
		ArrayList<Ruta> listarutas = ABMRuta.todos();

		this.setVisible(true);
		
		
		
		
		this.setContentPane(principal);
	
		
		this.consultar();
		
		JButton agregar = new JButton("Detalle de items");
		
		agregar.addActionListener(e->{
			try {
				DetalleItems alta = new DetalleItems(Integer.valueOf(this.tabla.getValueAt(tabla.getSelectedRow(), 0).toString()), this.tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	
		principal.add(agregar);
		
	
		
		
		
		this.pack();
	}
	
	
	
	private void consultar() {
		ArrayList<OrdenPedido> lista = new ArrayList<OrdenPedido>();
		ABMOrdenPedido.consultarordenescreadas(lista);
		
		
		
		int tamano = lista.size();
		String [][]aux = new String [tamano][5];
		int i = 0; int j = 0;
		for (OrdenPedido c : lista) {
			aux[i][0]=String.valueOf(c.getNropedido());
			aux[i][1]=c.getPlantaorigen();
			aux[i][2]=c.getPlantadestino();
			aux[i][3]=c.getFechaentrega();
			aux[i][4]=c.getEstado();
			i++;
			
			//areatext.append(i.getPatente()+"\n");
		}
		
		
		
		String titulos[] = {"Numero de pedido", "Planta de origen", "Planta Destino", "Fecha entrega", "Estado"};


		JTable tablaresu = new JTable(aux, titulos);
		tabla = new JTable(aux, titulos);
		
		
		principal.add(new JScrollPane(tabla),BorderLayout.CENTER);
		
		
		
		principal.revalidate();
		principal.repaint();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		
	}
	
	

}
