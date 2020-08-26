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

import dao.ABMStockPlanta;
import dominio.Camion;
import dominio.Insumo;
import dominio.InsumosPlantas;

public class PlantasStockMenor extends JFrame {
	JLabel planta = new JLabel("Nombre Planta");
	JLabel insumo = new JLabel("Nombre Insumo");
	
	JTextField campoplanta = new JTextField(10);
	JTextField campoinsumo = new JTextField(20);
	
	
	JButton consulta = new JButton("Consulta");
	
	JPanel principal = new JPanel();
	
	JTable tabla  = new JTable(2,3);
	
	JScrollPane a = new JScrollPane();

	
	
	public PlantasStockMenor(){
		super("Plantas con stock menor al punto de pedido");
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
		
		
		
		
		
		
		principal.add(planta);
		principal.add(campoplanta);
		principal.add(insumo);
		principal.add(campoinsumo);
		principal.add(consulta);
		
		this.add(principal, BorderLayout.NORTH);
		
		this.add(a, BorderLayout.CENTER);
	
		
		
		
		
		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	
	private void consultar() throws SQLException {
		
		ArrayList<InsumosPlantas> lista = new ArrayList<InsumosPlantas>();
		ABMStockPlanta.consultarstockmenor(lista, campoplanta.getText(), campoinsumo.getText());

	
		int tamano = lista.size();
		String [][]aux = new String [tamano][5];
		int i = 0;
		for (InsumosPlantas c : lista) {
			aux[i][0]=c.getInsumo();
			aux[i][1]=String.valueOf(c.getCantidad());
			aux[i][2]=String.valueOf(c.getPuntopedido());
			aux[i][3]=c.getNombreplanta();
			
			/*Consulta SQL para obtener suma*/
			Connection connection = null;
			connection = DriverManager.getConnection(
			        "jdbc:postgresql://localhost:5432/postgres",
			        "postgres", "wilson222");
			String ins = "SELECT SUM(cantidad) FROM insumosplantas WHERE insumo = \'"+c.getInsumo()+"\'";
			
			PreparedStatement stn = connection.prepareStatement(ins);
			
			ResultSet rs = stn.executeQuery();
			while(rs.next()) {
				aux[i][4]=String.valueOf(rs.getInt(1));
				

			}
			
			
			
			
				stn.close();
			
			
				connection.close();
			/*Fin de Consulta SQL*/
			
			i++;
			
		}
		
		

		String titulos[] = {"Nombre Insumo", "Cantidad", "Punto Pedido", "Nombre Planta", "Total de Stock"};


		JTable tablaresu = new JTable(aux, titulos);
		this.remove(tabla);
		tabla = new JTable(aux, titulos);
		
		
		this.remove(a);
		a = new JScrollPane(tabla);

		this.add(a,BorderLayout.CENTER);
		
		

		this.revalidate();
		this.repaint();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		
	}
	
	
	
	
	
	

}