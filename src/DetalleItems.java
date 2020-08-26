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

import dao.ABMOrdenPedido;
import dao.ABMRuta;
import dao.ABMStockPlanta;
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
	Camion camionasig = new Camion();
	
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



  
      
		return queue.poll();
	}
	
	
	//CONSTRUCTOR
	DetalleItems(int nropedido, String plantadestino) throws SQLException{
		
		
		super("Detalle items de la orden "+nropedido);
		
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		
		this.nropedido = nropedido;
		this.plantadestino = plantadestino;
		
		//Se asigna el camion
		camionasig = asignarCamion();
		//System.out.println("EL CAMION ASIGNADO ES: "+camionasig.getPatente());

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
	


	

		this.setVisible(true);
		

	
		
		this.consultar();
		
		//CONSULTA DE PLANTAS CON STOCK
		ArrayList<Planta> rsconsulta = new ArrayList<Planta>();

		for (Planta i : listaplantas) {
			//String query = "SELECT nombreplanta FROM insumosplantas WHERE "
			for (ItemPedido j : lista) {
				//String cont = "insumo = \'"+j.getInsumo()+"\' AND cantidad >= "+j.getCantidad();
				//Ver si planta.cantItem >= canItemPedido
				
				
				ABMStockPlanta.consultaplantastock(rsconsulta, j, i);

				 
				 
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
			//System.out.println("No hay plantas que tengan todos los insumos del pedido!");
			 JOptionPane.showMessageDialog(null, "No existen plantas con insumos para cubrir esta orden. La orden se marcará como CANCELADA.", "Error",0);
			 
			 ABMOrdenPedido.marcarcancelada(this.nropedido);
			 this.dispose();
			 
			 
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
					}
			}
		
			
			info.setText("La planta elegida con el camino mas corto para ir a "+this.plantadestino+" es: "+plantaelegida+" Con el valor: "+minimo);
			
			
			
			
			
			
			
			//ELIGE EL/LOS CAMINOS MAS CORTOS PARA IR DE LA PLANTA ORIGEN A LA PLANTA DESTINO
			List<List<Vertice<String>>> a = graf.caminos(plantaelegida, this.plantadestino);
			ArrayList<String> rutaselegidas = new ArrayList<String>();
			for (List<Vertice<String>> q : a) {
				//List<Vertice<String>> aux = q;
				Integer suma = 0;
				int contador = 1;
				Vertice<String> auxiliar1 = null, auxiliar2 = null;
				for (Vertice<String> t : q) {
					//System.out.println("trabajando con elemento: "+t.getValor());
					if (contador % 2 != 0) {
						auxiliar1 = t;
						if (contador != 1) {
							suma += (Integer) graf.buscarArista(auxiliar2, auxiliar1).getValor();
						}
						contador++;
						//System.out.println("Valor de auxiliar1: "+t.getValor());
					}
					else {
						auxiliar2 = t;
						//System.out.println("Valor de auxiliar2: "+t.getValor());
						suma += (Integer) graf.buscarArista(auxiliar1, auxiliar2).getValor();
						//System.out.println("Suma:"+suma);
						contador++;
					}
					
				
				}
				int suma2 = suma;
				//System.out.println("Suma2: "+suma2);
				if (suma2 == minimo) {
					//System.out.println("El mejor camino es:"+q);
					rutaselegidas.add(q.toString());
					
				}
			}
			//System.out.println("para agregar a la interfaz grafica");
			/*for (String i : rutaselegidas) {
				System.out.println(i);
			}*/
			
			int tamanotabla = rutaselegidas.size();
			
			String [][]auxtabla = new String [tamanotabla][1];
			String []titulos = {"Rutas"};
			int contador = 0;
			for(String i : rutaselegidas) {
				auxtabla[contador][0] = i;
				contador++;
			}
			
			
			
			tablarutas = new JTable(auxtabla, titulos);
			
			
			

		}

		
		
			
		// HASTA ACA, CONSULTA DE PLANTAS QUE CUMPLEN CON STOCK
		JButton agregar = new JButton("Ruta mas corta en KM");
		JButton rutamasrapida = new JButton("Ruta mas rapida");
		
		
		//EVENTOS de los botones
		
		//RUTA MAS CORTA EN KM
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
			//System.out.println("Valor elegido como planta elegida de la tabla: ");
			//System.out.println(this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString());
			//System.out.println("Seleccionado: "+this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString());

			
			List<List<Vertice<String>>> a = graf.caminos(this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString(), this.plantadestino);
			ArrayList<String> rutaselegidas = new ArrayList<String>();
			for (List<Vertice<String>> q : a) {
				//List<Vertice<String>> aux = q;
				Integer suma = 0;
				int contador = 1;
				Vertice<String> auxiliar1 = null, auxiliar2 = null;
				for (Vertice<String> t : q) {
					//System.out.println("trabajando con elemento: "+t.getValor());
					if (contador % 2 != 0) {
						auxiliar1 = t;
						if (contador != 1) {
							suma += (Integer) graf.buscarArista(auxiliar2, auxiliar1).getValor();
						}
						contador++;
						//System.out.println("Valor de auxiliar1: "+t.getValor());
					}
					else {
						auxiliar2 = t;
						//System.out.println("Valor de auxiliar2: "+t.getValor());
						suma += (Integer) graf.buscarArista(auxiliar1, auxiliar2).getValor();
						//System.out.println("Suma:"+suma);
						contador++;
					}
					//System.out.println("Elemento: "+t);
					// aca tendria que haber una consulta de cuanto mide el tramo
				
				}
				int suma2 = suma;
				//System.out.println("Suma2: "+suma2);
				/*
				System.out.println("El camino es: "+q);
				rutaselegidas.add(q.toString());
*/
				
				if (suma2 == minimo) {
					//System.out.println("El mejor camino es:"+q);
					rutaselegidas.add(q.toString());
					
				}
			}
			//System.out.println("para agregar a la interfaz grafica");
			/*for (String i : rutaselegidas) {
				System.out.println(i);
			}*/
			
			int tamanotabla = rutaselegidas.size();
			
			String [][]auxtabla = new String [tamanotabla][1];
			String []titulos = {"Rutas"};
			int contador = 0;
			for(String i : rutaselegidas) {
				auxtabla[contador][0] = i;
				contador++;
			}
			
			
			
			JTable tablarutas = new JTable(auxtabla, titulos);
			
			SeleccionarRuta s = new SeleccionarRuta(tablarutas, "Rutas mas cortas en DISTANCIA", camionasig, this.nropedido);

			
		});
		
		
		//RUTA MAS RAPIDA
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
			//System.out.println("Valor elegido como planta elegida de la tabla: ");
			//System.out.println(this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString());
			//System.out.println("Seleccionado: "+this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString());

			
			List<List<Vertice<String>>> a = grafd.caminos(this.tabla2.getValueAt(tabla2.getSelectedRow(), 0).toString(), this.plantadestino);
			ArrayList<String> rutaselegidas = new ArrayList<String>();
			for (List<Vertice<String>> q : a) {
				//List<Vertice<String>> aux = q;
				Integer suma = 0;
				int contador = 1;
				Vertice<String> auxiliar1 = null, auxiliar2 = null;
				for (Vertice<String> t : q) {
					//System.out.println("trabajando con elemento: "+t.getValor());
					if (contador % 2 != 0) {
						auxiliar1 = t;
						if (contador != 1) {
							suma += (Integer) grafd.buscarArista(auxiliar2, auxiliar1).getValor();
						}
						contador++;
						//System.out.println("Valor de auxiliar1: "+t.getValor());
					}
					else {
						auxiliar2 = t;
						//System.out.println("Valor de auxiliar2: "+t.getValor());
						suma += (Integer) grafd.buscarArista(auxiliar1, auxiliar2).getValor();
						//System.out.println("Suma:"+suma);
						contador++;
					}
					//System.out.println("Elemento: "+t);
					// aca tendria que haber una consulta de cuanto mide el tramo
				
				}
				int suma2 = suma;
				//System.out.println("Suma2: "+suma2);
				/*
				System.out.println("El camino es: "+q);
				rutaselegidas.add(q.toString());
*/
				
				if (suma2 == minimo) {
					//System.out.println("El mejor camino es:"+q);
					rutaselegidas.add(q.toString());
					
				}
			}
			//System.out.println("para agregar a la interfaz grafica");
			/*for (String i : rutaselegidas) {
				System.out.println(i);
			}*/
			
			int tamanotabla = rutaselegidas.size();
			
			String [][]auxtabla = new String [tamanotabla][1];
			String []titulos = {"Rutas"};
			int contador = 0;
			for(String i : rutaselegidas) {
				auxtabla[contador][0] = i;
				contador++;
			}
			
			
			
			JTable tablarutas = new JTable(auxtabla, titulos);

			SeleccionarRuta s = new SeleccionarRuta(tablarutas, "Rutas mas cortas en DURACIÓN", camionasig, this.nropedido);


			
		});
		
		
	
		
		
		sur.add(agregar);
		sur.add(rutamasrapida);
		sur.add(info);

		
		
		this.pack();
	}
	
	
	
	//Crea la tabla con los elementos del pedido
	private void consultar() {
		
		//	public static void consultaritems(int nropedido, ArrayList<ItemPedido> lista) {

		ABMOrdenPedido.consultaritems(nropedido, lista);
		
		
		
		int tamano = lista.size();
		String [][]aux = new String [tamano][3];
		int i = 0; int j = 0;
		for (ItemPedido c : lista) {
			aux[i][0]=c.getInsumo();
			aux[i][1]=String.valueOf(c.getCantidad());
			aux[i][2]=String.valueOf(c.getCosto());
			
			i++;
			
		}
		
		
		
		String titulos[] = {"Insumo", "Cantidad", "Costo"};


		JTable tablaresu = new JTable(aux, titulos);
		tabla = new JTable(aux, titulos);
		
		principal.add(new JScrollPane(tabla),BorderLayout.CENTER);
		

		principal.revalidate();
		principal.repaint();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		
	}
	
	

}
