import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

import dao.ABMRuta;
import dao.AMBPlanta;
import dominio.Camion;
import dominio.Insumo;
import dominio.ItemPedido;
import dominio.OrdenPedido;
import dominio.Planta;
import dominio.Ruta;

public class DetalleItems extends JFrame {
	
	private int nropedido;
	String plantadestino;
	JPanel principal = new JPanel();
	ArrayList<ItemPedido> lista = new ArrayList<ItemPedido>();

	
	JTable tabla;
	JTextField plantasci = new JTextField("Plantas con todos los insumos del pedido:");
	JTextField campoplantasci;
	
	
	DetalleItems(int nropedido, String plantadestino) throws SQLException{
		
		
		super("Detalle items de la orden "+nropedido);
		this.nropedido = nropedido;
		this.plantadestino = plantadestino;

		//EJEMPLO DE CARGA DE GRAFO DE DISTANCIA
		ArrayList<Planta> listaplantas = AMBPlanta.todos();
		ArrayList<Ruta> listarutas = ABMRuta.todos();

	Grafo<String> graf = new Grafo<String>();
	//CArgando el grafo de DISTANCIAS
	for (Planta i : listaplantas) {
		graf.addNodo(i.getNombre());
	}
	
	for (Ruta i : listarutas) {
		graf.conectar(i.getOrigen().getNombre(), i.getDestino().getNombre(), i.getDistanciakm());
	}
	
	
	
	//public void conectar(T n1,T n2,Number valor)	
	
	
	//public Map<T,Integer> caminosMinimoDikstra(T valorOrigen){

	
	
	/*ESTO ES UNA GENIALIDAD*/
	
	/*
	int minimo = 99999;
	String plantaelegida = "";
	Map<String, Integer> a = graf.caminosMinimoDikstra("Santa Fe");
	Iterator it = a.keySet().iterator();
	while(it.hasNext()){
	  String key = (String) it.next();
	  if (key.equals("Buenos Aires")) {
		  if (a.get(key) <= minimo) {
			  minimo = a.get(key);
			  plantaelegida = "Santa Fe";
		  }
			  
	  }
	  //System.out.println("Clave: " + key + " -> Valor: " + lista.get(key));
	}
	System.out.println("La planta elegida es: "+plantaelegida);
	
	graf.caminosMinimoDikstra("Cordoba");
	graf.caminos("Santa Fe", "Buenos Aires");
	graf.caminos("Cordoba", "Buenos Aires");
	*/
	
	/*HASTA ACA LA GENIALIDAD*/
	
	
	//System.out.println("Camino SF -> ER"+graf.existeCamino(new Vertice<String>("Santa Fe"), new Vertice<String>("Buenos Aires"), 0));
	
	//List<List<Vertice<String>>> a = graf.caminos("Nodo1", "Nodo4");
	
	
/*
	Map<String, Integer> lista = graf.caminosMinimoDikstra("Nodo1");
	/*
	for (String i : lista) {
		System.out.println(i);
	}
Iterator it = lista.keySet().iterator();
while(it.hasNext()){
  String key = (String) it.next();
  System.out.println("Clave: " + key + " -> Valor: " + lista.get(key));
}

*/

		this.setVisible(true);
		//this.setLayout();
		
		
	
		
		
		
		
		
		
		JTable tabla = new JTable(2,3);
		//tabla.addColumn("Nombre");
		
		
		
		//JPanel principal = new JPanel();
		this.setContentPane(principal);
	
		
		this.consultar();
		//CONSULTA DE PLANTAS CON STOCK
		ArrayList<Planta> rsconsulta = new ArrayList<Planta>();

		for (Planta i : listaplantas) {
			//String query = "SELECT nombreplanta FROM insumosplantas WHERE "
			for (ItemPedido j : lista) {
				//String cont = "insumo = \'"+j.getInsumo()+"\' AND cantidad >= "+j.getCantidad();
				//Ver si planta.cantItem >= canItemPedido
				//consulta SQL 
				
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
		String consulta = "SELECT * FROM insumosplantas WHERE insumo = \'"+j.getInsumo()+"\' AND cantidad >="+j.getCantidad()+" AND nombreplanta = \'"+i.getNombre()+"\'";
		//System.out.println(consulta);
		PreparedStatement stn = connection.prepareStatement(consulta);
		ResultSet rs = stn.executeQuery();
		while(rs.next()) { 
				 Planta aux = new Planta();
				 aux.setNombre(rs.getString("nombreplanta"));
				 rsconsulta.add(aux);
				/*FIn consulta SQL */
			}
		}
		}
		/*
		for (Planta i : rsconsulta) {
			System.out.println("Nombre planta: "+i.getNombre());
		}*/
		
		/*
		 rsconsulta = ArrayList<Planta>
int tamanoconsulta = .... ;
for (Planta i : todaslasplantas)
	int cont =0;
	for(Planta j : rsconsulta)
	if (i.getNombre().equals(j.getNombre()){
	cont++;
	}
	if (cont == tamanoconsulta)
	//agregar planta a lista de plantas que contienen todos los insumos
		  
		*/
		ArrayList<Planta> plantascontodosinsumos = new ArrayList<Planta>();
		int tamanoconsulta = lista.size();
		for (Planta i : listaplantas) {
			int cont = 0;
			for (Planta j : rsconsulta) {
				if (i.getNombre().equals(j.getNombre()))
					cont++;
			}
			if (cont == tamanoconsulta)
				plantascontodosinsumos.add(i);
		}
		
		if (plantascontodosinsumos.size() == 0)
			System.out.println("No hay plantas que tengan todos los insumos del pedido!");
		
		else
		{
			//System.out.println("Las plantas que tienen todos los insumos del pedido son las siguientes:");
			
			String plantascontodos = "";
			for (Planta k : plantascontodosinsumos) {
				plantascontodos = plantascontodos + k.getNombre() + "; ";
				//System.out.println(k.getNombre());
				
			}
			campoplantasci = new JTextField(plantascontodos);
			principal.add(plantasci);
			principal.add(campoplantasci);
			principal.revalidate();
			
			int minimo = 99999;
			String plantaelegida = "";
			for (Planta p : plantascontodosinsumos) {
				Map<String, Integer> a = graf.caminosMinimoDikstra(p.getNombre());
				Iterator it = a.keySet().iterator();
				while(it.hasNext()){
					  String key = (String) it.next();
					  //System.out.println("Planta destino recibida: "+this.plantadestino);
					  //System.out.println("Nro pedido: "+this.nropedido);
					  if (key.equals(this.plantadestino)) {
						  if (a.get(key) <= minimo) {
							  minimo = a.get(key);
							  plantaelegida = p.getNombre();
						  }
							  
					  }
					  //System.out.println("Clave: " + key + " -> Valor: " + lista.get(key));
					}
			}
		
			System.out.println("La planta elegida con el camino mas corto para ir a "+this.plantadestino+" es: "+plantaelegida+" Con el valor: "+minimo);
			System.out.println("Imprimiendo caminos de la planta origen elegida al destino: ");
			List<List<Vertice<String>>> a = graf.caminos(plantaelegida, this.plantadestino);
			for (List<Vertice<String>> q : a) {
				//List<Vertice<String>> aux = q;
				Integer suma = 0;
				int contador = 1;
				Vertice<String> auxiliar1 = null, auxiliar2 = null;
				for (Vertice<String> t : q) {
					System.out.println("trabajando con elemento: "+t.getValor());
					if (contador % 2 != 0) {
						auxiliar1 = t;
						if (contador != 1) {
							suma += (Integer) graf.buscarArista(auxiliar2, auxiliar1).getValor();
						}
						contador++;
						System.out.println("Valor de auxiliar1: "+t.getValor());
					}
					else {
						auxiliar2 = t;
						System.out.println("Valor de auxiliar2: "+t.getValor());
						suma += (Integer) graf.buscarArista(auxiliar1, auxiliar2).getValor();
						System.out.println("Suma:"+suma);
						contador++;
					}
					//System.out.println("Elemento: "+t);
					// aca tendria que haber una consulta de cuanto mide el tramo
				
				}
				int suma2 = suma;
				System.out.println("Suma2: "+suma2);
				if (suma2 == minimo) {
					System.out.println("El mejor camino es:"+q);
				}
			}
			/*
			graf.caminosMinimoDikstra("Cordoba");
			graf.caminos("Santa Fe", "Buenos Aires");
			graf.caminos("Cordoba", "Buenos Aires");*/

		}

		
		
			
		// HASTA ACA, CONSULTA DE PLANTAS QUE CUMPLEN CON STOCK
		JButton agregar = new JButton("Detalle de items");
		
		
		agregar.addActionListener(e->{
			AltaItem alta = new AltaItem();
		});
		
		
		
	
		
		//principal.add(destino);
		
		//principal.add(fechamaxima);
		//principal.add(campofechamaxima);
		//principal.add(numeropedido);
		//principal.add(camponumeropedido);
		principal.add(agregar);
		
	
		
		
		
		this.pack();
	}
	
	
	
	private void consultar() {
		try {
			//lista = dao.AMBCamion.busqueda(campopatente.getText(), campomodelo.getText(), campokm.getText(), campocostokm.getText(), campocostoh.getText(), campofechacompra.getText());
			
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
			String consulta = "SELECT * FROM itemspedidos WHERE nropedido = "+nropedido;
			PreparedStatement stn = connection.prepareStatement(consulta);
			ResultSet rs = stn.executeQuery();
			while(rs.next()) {
				//Camion(String patente, String modelo, int kmrec, int costokm, int costoh, String fechacompra)
				ItemPedido aux = new ItemPedido();
				/*private String descripcion;
				private String unidadmedida;
				private int costo;
				private String tipo;
				private int peso;
				private int densidad;*/
				aux.setInsumo(rs.getString(1));
				aux.setCantidad(rs.getInt(2));
				aux.setCosto(rs.getInt(3));



				
				
				lista.add(aux);
			}
			//stn.execute("INSERT INTO \"Libro\" (id, nombre) VALUES (4, \'Oscar\')");
			
			
			
			
				stn.close();
			
			
				connection.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int tamano = lista.size();
		String [][]aux = new String [tamano][3];
		int i = 0; int j = 0;
		for (ItemPedido c : lista) {
			aux[i][0]=c.getInsumo();
			aux[i][1]=String.valueOf(c.getCantidad());
			aux[i][2]=String.valueOf(c.getCosto());
			
			i++;
			
			//areatext.append(i.getPatente()+"\n");
		}
		
		
		//principal.remove(tabla);
		//principal.revalidate();
		//pack();
		String titulos[] = {"Insumo", "Cantidad", "Costo"};


		JTable tablaresu = new JTable(aux, titulos);
		//this.remove(tabla);
		tabla = new JTable(aux, titulos);
		
		//principal.add(tablaresu);
		//principal.add(tabla);
		
		//este anda bien
		//principal.remove(tabla);
		principal.add(new JScrollPane(tabla),BorderLayout.CENTER);
		
		
		//principal.add(tabla);
		//tabla.repaint();
		
		//tabla.repaint();

		principal.revalidate();
		principal.repaint();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		
	}
	
	

}
