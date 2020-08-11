import java.sql.Statement;
import java.util.ArrayList;
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
		//this.setLayout();
		JPanel principal = new JPanel();

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
		//tabla.addColumn("Nombre");
		
		
		
		this.setContentPane(principal);
	
		
		
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
				principal.add(costo);
				principal.add(campocosto);
				principal.add(guardar);
				principal.revalidate();
				
			}
		});
		
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
		guardar.addActionListener(e -> {/*
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
		});
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

		principal.add(insumo);
		
		principal.add(cantidad);
		principal.add(campocantidad);
		principal.add(calcularcosto);
		
		
		
		this.pack();
	}
	
	
	
	
	
	

}
