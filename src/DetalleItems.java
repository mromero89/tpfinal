import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.ABMRuta;
import dao.AMBCamion;
import dao.AMBPlanta;
import dominio.Camion;
import dominio.DetalleEnvio;
import dominio.Insumo;
import dominio.ItemPedido;
import dominio.OrdenPedido;
import dominio.Planta;
import dominio.Ruta;

public class DetalleItems extends JFrame {
	
	//Se reciben como parametros el nro de pedido y la planta de destino
	private int nropedido;
	String plantadestino;
	
	JPanel principal = new JPanel();
	JPanel sur = new JPanel();
	JTable tablarutas;
	
	//LISTA CON TODOS LOS ITEMS DEL NUMERO DE PEDIDO DADO, SE CARGA CON THIS.CONSULTA()	
	ArrayList<ItemPedido> lista = new ArrayList<ItemPedido>(); 

	
	JTable tabla;
	JTable tabla2;
	JLabel info = new JLabel();

	
	JLabel plantasci = new JLabel("Plantas con todos los insumos del pedido:");
	JTextField campoplantasci;
	
	private Camion asignarCamion() {
		//public Camion(String patente, String modelo, int kmrec, int costokm, int costoh, String fechacompra)
        PriorityQueue<Camion> queue = new PriorityQueue<Camion>(); 
        try {
			ArrayList<Camion> listacamiones = dao.AMBCamion.todos();
			ArrayList<DetalleEnvio> listadetallesenvio = dao.ABMDetalleEnvio.todos();
			//Obtengo las patentes de los camiones que tienen asignados pedidos
			ArrayList<String> patentes = new ArrayList<String>();
			for (DetalleEnvio i : listadetallesenvio) {
				patentes.add(i.getPatente());
			}
			
			for (Camion i : listacamiones) {
				//Si el camion seleccionado no se encuentra destinado a un envio, se agrega a la cola de prioridades
				if (!(patentes.contains(i.getPatente()))){
					queue.add(i);

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



  
        System.out.println("Priority queue values are: " + queue); 
        for (Camion i : queue) {
        	System.out.println(i.getPatente());
        }
		return queue.poll();
	}
	
	
	//CONSTRUCTOR
	DetalleItems(int nropedido, String plantadestino) throws SQLException{
		
		
		super("Detalle items de la orden "+nropedido);
		
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		
		this.nropedido = nropedido;
		this.plantadestino = plantadestino;
		
		Camion camionasig = asignarCamion();
		System.out.println("EL CAMION ASIGNADO ES: "+camionasig.getPatente());

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
	
	
	//FIN DE CARGA DE GRAFO
	
	
	
	Grafo<String> graf2 = new Grafo<String>();
	graf2.addNodo("A");
	graf2.addNodo("B");
	graf2.addNodo("C");
	graf2.conectar("A", "B");
	graf2.conectar("A", "C");
	graf2.conectar("B", "C");
	graf2.conectar("C", "A");



	
	
	//ALGORITMO PAGERANK
	/*
		Map<String, Double> prank = new HashMap<String, Double>();
		List<Vertice<String>> vertices = graf2.getVertices();
		List<Vertice<String>> vertices2 = vertices;
		for (Vertice<String> i : vertices) {
			System.out.println(i.getValor());
			
			prank.put(i.getValor(), (double) 1);
		}
		
		for (int v = 0; v<12 ; v++) {
		for (Vertice<String> i : vertices) {
			double suma = 0.0;
			for(Vertice<String> j : vertices2) {
				List<Vertice<String>> adyacentes = graf2.getAdyacentes(j);
				for (Vertice<String> k : adyacentes) {
					System.out.println("Adyacente a "+j+": "+k.getValor());
				}
				if (adyacentes.contains(i)) {
					System.out.println("el elemento "+j+" es adyacente a "+i);
					double gradoS = graf2.gradoSalida(j);
					System.out.println("Grado de salida de "+j+" es "+gradoS);
					Double prnod = prank.get(j.getValor());
					suma += prnod / gradoS;
					//suma += prank.
				}
				
				
			}
			double prnodo = 0.5 + 0.5*suma;
			System.out.println("El PR del nodo "+i.getValor()+" es "+prnodo);
			prank.put(i.getValor(), prnodo);
		
		
		}
		
	}
	
	fin de page rank
	*/	
	
	
	
	
	
	
	//CARGA DE GRAFO DE DURACIONES
	Grafo<String> grafd = new Grafo<String>();
	//CArgando el grafo de DISTANCIAS
	for (Planta i : listaplantas) {
		grafd.addNodo(i.getNombre());
	}
	
	for (Ruta i : listarutas) {
		grafd.conectar(i.getOrigen().getNombre(), i.getDestino().getNombre(), i.getDuracionh());
	}
	//FIN DE CARGA DE GRAFO DURACIONES
	


	
	
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
	
	

		this.setVisible(true);
		

		//this.setContentPane(principal);
	
		
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
		stn.close();
		
		
		connection.close();
		}
		}
		
		
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
		
		if (plantascontodosinsumos.size() == 0) {
			System.out.println("No hay plantas que tengan todos los insumos del pedido!");
			 JOptionPane.showMessageDialog(null, "No existen plantas con insumos para cubrir esta orden. La orden se marcará como CANCELADA.", "Error",0);
			 //hacer consulta SQL para marcar orden de pedido como CANCELADA
			 
			
					
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
					//stn.execute("INSERT INTO \"Libro\" (id, nombre) VALUES (4, \'Oscar\')");
					
					//System.out.println("palabra sql: UPDATE camiones SET patente = \'"+patente+"\', modelo = \'"+modelo+"\', kmrec ="+kmrec+", costokm ="+costokm+", costohora="+costohora+", fechacompra= \'"+fecha+"\' WHERE patente = \'"+clave+"\'");
					
						stn.executeUpdate("UPDATE ordenespedidos SET estado = \'CANCELADA\' WHERE nropedido = "+this.nropedido);
						
					
					
						stn.close();
					
					
						connection.close();
					
					
					
				
			 
			 
		}
		
		else
		{
			//System.out.println("Las plantas que tienen todos los insumos del pedido son las siguientes:");
			
			String plantascontodos = "";
			
			//SE PREPARA LA TABLA CON TODAS LAS PLANTAS QUE TIENEN TODOS LOS ITEMS DEL PEDIDO
			int tamanolistaplantas = plantascontodosinsumos.size();
			String[][] auxiliar = new String[tamanolistaplantas][1];
			int z = 0;
			for (Planta k : plantascontodosinsumos) {
				plantascontodos = plantascontodos + k.getNombre() + "; ";
				auxiliar[z][0] = k.getNombre();
				z++;
				
				
			}
			String titles[] = {"Plantas"};
			tabla2 = new JTable(auxiliar, titles);
			sur.add(plantasci);
			//JScrollPane pane = new JScrollPane();
			sur.add(tabla2);

			this.add(sur, BorderLayout.SOUTH);

			//campoplantasci = new JTextField(plantascontodos);
			//principal.add(campoplantasci);
			principal.revalidate();
			this.add(principal, BorderLayout.CENTER);
			
			//SE ELIGE LA PLANTA CON EL CAMINO MAS CORTO
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
			info.setText("La planta elegida con el camino mas corto para ir a "+this.plantadestino+" es: "+plantaelegida+" Con el valor: "+minimo);
			
			
			
			/*
			 * System.out.println("Planta inicio: "+plantaelegida);
			System.out.println("Planta fin: "+this.plantadestino);
			List<List<Vertice<String>>> listnodes = graf.caminos(plantaelegida, this.plantadestino);
			 */
			int fmax = 0;
			System.out.println("ingresando a rutina prototipo para fmax");
			System.out.println("Planta inicio: "+"Buenos Aires");
			System.out.println("Planta fin: "+"Entre Rios");
			List<List<Vertice<String>>> listnodes = graf.caminos("Buenos Aires", "Entre Rios");
			for (List<Vertice<String>> i : listnodes) {
				int minimoc = 99999;
				Vertice<String> nodo1, nodo2;
				nodo1 = null; nodo2 = null;
				List<Vertice<String>> auxi = i;
				List<Vertice<String>> auxi2 = auxi;
				for (Vertice<String> j : auxi) {
					//evaluar si el nodo no es el final
					//if (!(j.equals(this.plantadestino))) {
					if (!(j.equals("Entre Rios"))) {
						
						//asignacion de nodos
						if (nodo1 == null) {
							nodo1 =j;
						}
						else if ((nodo1 != null) && (nodo2 == null)){
							nodo2 = j;

						}
						else {
							nodo1 = nodo2;
							nodo2 = j;
						}
					if (nodo1 != null && nodo2 != null) {
						
						//buscar arista en la lista de aristas del grafo
						Arista<String> aux = null;
						//List<Arista<T>> getAristas()
						List<Arista<String>> listadearistas = graf.getAristas();
						
						aux = graf.buscarArista(nodo1, nodo2);
						System.out.println("La arista seleccionada es la q va de "+aux.getInicio()+" a "+aux.getFin());
						System.out.println("con el valor: "+aux.getValor());
						
						/* metodo que se reemplaza por el ya disponible en clase GRAFO
						for(Arista<String> a : listadearistas) {
							if((a.getInicio().equals(nodo1)) && (a.getFin().equals(nodo2))) {
								aux = a;
								System.out.println("La arista seleccionada es la q va de "+a.getInicio()+" a "+a.getFin());
								System.out.println("con el valor: "+a.getValor());
							}
						}
						*/
						
						

						
						
						int valor = (int) aux.getValor();
						if ((valor < minimoc) && (valor > 0)) {
							minimoc = valor;
						}
					
					}
					
					
				}
					
				
			}
				if (minimoc != 99999) {
					fmax += minimoc;
				}
				System.out.println("El minimo obtenido es: "+minimoc);
				System.out.println("El flujo maximo es: "+fmax);
				
				nodo1 = null; nodo2 = null;
				//aca se deberia modificar el valor de las aristas restandole el minimo a cada una
				for (Vertice<String> j : auxi2) {
					//evaluar si el nodo no es el final
					//if (!(j.equals(this.plantadestino))) {
					if (!(j.equals("Entre Rios"))) {
						
						//asignacion de nodos
						if (nodo1 == null) {
							nodo1 =j;
						}
						else if ((nodo1 != null) && (nodo2 == null)){
							nodo2 = j;

						}
						else {
							nodo1 = nodo2;
							nodo2 = j;
						}
					if (nodo1 != null && nodo2 != null) {
						
						//buscar arista en la lista de aristas del grafo
						//Arista<String> aux = null;
						System.out.println("Valor de la arista que une "+nodo1+" y "+nodo2+"= "+graf.buscarArista(nodo1, nodo2).getValor());
						
						int value = (int) graf.buscarArista(nodo1, nodo2).getValor()-minimoc;
						graf.buscarArista(nodo1, nodo2).setValor(value);
						System.out.println("Despues de restarle el minimo: "+graf.buscarArista(nodo1, nodo2).getValor());
					
					}
					
					
				}
					
				
			}
			
				
			}
			System.out.println("El flujo maximo FINAL obtenido es: "+fmax);
			
			
			
			//OLA KE ASÉ  --- ELIGE EL/LOS CAMINOS MAS CORTOS PARA IR DE LA PLANTA ORIGEN A LA PLANTA DESTINO
			List<List<Vertice<String>>> a = graf.caminos(plantaelegida, this.plantadestino);
			ArrayList<String> rutaselegidas = new ArrayList<String>();
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
					rutaselegidas.add(q.toString());
					
				}
			}
			System.out.println("para agregar a la interfaz grafica");
			for (String i : rutaselegidas) {
				System.out.println(i);
			}
			
			int tamanotabla = rutaselegidas.size();
			
			String [][]auxtabla = new String [tamanotabla][1];
			String []titulos = {"Rutas"};
			int contador = 0;
			for(String i : rutaselegidas) {
				auxtabla[contador][0] = i;
				contador++;
			}
			
			
			
			tablarutas = new JTable(auxtabla, titulos);

			JFrame rutas = new JFrame("Rutas ");
			rutas.add(tablarutas);
			rutas.setVisible(true);
			

		}

		
		
			
		// HASTA ACA, CONSULTA DE PLANTAS QUE CUMPLEN CON STOCK
		JButton agregar = new JButton("Ruta mas corta en KM");
		JButton rutamasrapida = new JButton("Ruta mas rapida");
		
		
		agregar.addActionListener(e->{
			int minimo = 99999;
			String plantaelegida = "";
			//for (Planta p : plantascontodosinsumos) {
				Map<String, Integer> b = graf.caminosMinimoDikstra(this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString());
				Iterator it = b.keySet().iterator();
				while(it.hasNext()){
					  String key = (String) it.next();
					  //System.out.println("Planta destino recibida: "+this.plantadestino);
					  //System.out.println("Nro pedido: "+this.nropedido);
					  if (key.equals(this.plantadestino)) {
						  if (b.get(key) <= minimo) {
							  minimo = b.get(key);
							  plantaelegida = key;
						  }
							  
					  }
					  //System.out.println("Clave: " + key + " -> Valor: " + lista.get(key));
					}
			//}
			
			//ELIGE EL/LOS CAMINOS MAS CORTOS PARA IR DE LA PLANTA ORIGEN A LA PLANTA DESTINO
			//Integer.valueOf(this.tablarutas.getValueAt(tabla.getSelectedRow(), 0).toString())
			System.out.println("Valor elegido como planta elegida de la tabla: ");
			//System.out.println(this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString());
			System.out.println("Seleccionado: "+this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString());

			
			List<List<Vertice<String>>> a = graf.caminos(this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString(), this.plantadestino);
			ArrayList<String> rutaselegidas = new ArrayList<String>();
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
				/*
				System.out.println("El camino es: "+q);
				rutaselegidas.add(q.toString());
*/
				
				if (suma2 == minimo) {
					System.out.println("El mejor camino es:"+q);
					rutaselegidas.add(q.toString());
					
				}
			}
			System.out.println("para agregar a la interfaz grafica");
			for (String i : rutaselegidas) {
				System.out.println(i);
			}
			
			int tamanotabla = rutaselegidas.size();
			
			String [][]auxtabla = new String [tamanotabla][1];
			String []titulos = {"Rutas"};
			int contador = 0;
			for(String i : rutaselegidas) {
				auxtabla[contador][0] = i;
				contador++;
			}
			
			
			
			JTable tablarutas = new JTable(auxtabla, titulos);

			JFrame rutas = new JFrame("Ruta mas corta en KM");
			JButton seleccionar = new JButton("Seleccionar ruta");
			JPanel panel = new JPanel();
			
			rutas.setContentPane(panel);
			panel.add(tablarutas);
			panel.add(seleccionar);
			
			rutas.setVisible(true);
			
			
			
		});
		
		rutamasrapida.addActionListener(e->{
			int minimo = 99999;
			String plantaelegida = "";
			//for (Planta p : plantascontodosinsumos) {
				Map<String, Integer> b = grafd.caminosMinimoDikstra(this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString());
				Iterator it = b.keySet().iterator();
				while(it.hasNext()){
					  String key = (String) it.next();
					  //System.out.println("Planta destino recibida: "+this.plantadestino);
					  //System.out.println("Nro pedido: "+this.nropedido);
					  if (key.equals(this.plantadestino)) {
						  if (b.get(key) <= minimo) {
							  minimo = b.get(key);
							  plantaelegida = key;
						  }
							  
					  }
					  //System.out.println("Clave: " + key + " -> Valor: " + lista.get(key));
					}
			//}
			
			//ELIGE EL/LOS CAMINOS MAS CORTOS PARA IR DE LA PLANTA ORIGEN A LA PLANTA DESTINO
			//Integer.valueOf(this.tablarutas.getValueAt(tabla.getSelectedRow(), 0).toString())
			System.out.println("Valor elegido como planta elegida de la tabla: ");
			//System.out.println(this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString());
			System.out.println("Seleccionado: "+this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString());

			
			List<List<Vertice<String>>> a = grafd.caminos(this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString(), this.plantadestino);
			ArrayList<String> rutaselegidas = new ArrayList<String>();
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
							suma += (Integer) grafd.buscarArista(auxiliar2, auxiliar1).getValor();
						}
						contador++;
						System.out.println("Valor de auxiliar1: "+t.getValor());
					}
					else {
						auxiliar2 = t;
						System.out.println("Valor de auxiliar2: "+t.getValor());
						suma += (Integer) grafd.buscarArista(auxiliar1, auxiliar2).getValor();
						System.out.println("Suma:"+suma);
						contador++;
					}
					//System.out.println("Elemento: "+t);
					// aca tendria que haber una consulta de cuanto mide el tramo
				
				}
				int suma2 = suma;
				System.out.println("Suma2: "+suma2);
				/*
				System.out.println("El camino es: "+q);
				rutaselegidas.add(q.toString());
*/
				
				if (suma2 == minimo) {
					System.out.println("El mejor camino es:"+q);
					rutaselegidas.add(q.toString());
					
				}
			}
			System.out.println("para agregar a la interfaz grafica");
			for (String i : rutaselegidas) {
				System.out.println(i);
			}
			
			int tamanotabla = rutaselegidas.size();
			
			String [][]auxtabla = new String [tamanotabla][1];
			String []titulos = {"Rutas"};
			int contador = 0;
			for(String i : rutaselegidas) {
				auxtabla[contador][0] = i;
				contador++;
			}
			
			
			
			JTable tablarutas = new JTable(auxtabla, titulos);

			JFrame rutas = new JFrame("Ruta mas corta en duracion");
			//JPanel panelruta = new JPanel();
			//rutas.setContentPane(panelruta);
			JButton seleccionar = new JButton("Seleccionar ruta");
			//Boton que tiene que: insertar la orden y actualizar datos del camion

		
			
			JPanel panel = new JPanel();
			
			rutas.setContentPane(panel);
			panel.add(tablarutas);
			panel.add(seleccionar);
			rutas.setVisible(true);

			
			
		});
		
		
	
		
		//principal.add(destino);
		
		//principal.add(fechamaxima);
		//principal.add(campofechamaxima);
		//principal.add(numeropedido);
		//principal.add(camponumeropedido);
		sur.add(agregar);
		sur.add(rutamasrapida);
		sur.add(info);

		
		
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
