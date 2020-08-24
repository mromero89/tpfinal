import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dao.ABMRuta;
import dao.AMBPlanta;
import dominio.Planta;
import dominio.Ruta;

public class VerPageRank {
	private TableRowSorter<TableModel> modeloOrdenado;


	
	public VerPageRank() throws SQLException {
		
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

//ALGORITMO PAGERANK
		Map<String, Double> prank = new HashMap<String, Double>();
		List<Vertice<String>> vertices = graf.getVertices();
		List<Vertice<String>> vertices2 = vertices;
		for (Vertice<String> i : vertices) {
			//System.out.println(i.getValor());
			
			prank.put(i.getValor(), (double) 1);
		}
		
		for (int w = 0; w<12 ; w++) {
		for (Vertice<String> i : vertices) {
			double suma = 0.0;
			for(Vertice<String> j : vertices2) {
				List<Vertice<String>> adyacentes = graf.getAdyacentes(j);
				for (Vertice<String> k : adyacentes) {
					//System.out.println("Adyacente a "+j+": "+k.getValor());
				}
				if (adyacentes.contains(i)) {
					//System.out.println("el elemento "+j+" es adyacente a "+i);
					double gradoS = graf.gradoSalida(j);
					//System.out.println("Grado de salida de "+j+" es "+gradoS);
					Double prnod = prank.get(j.getValor());
					suma += prnod / gradoS;
					//suma += prank.
				}
				/*if (adyacentes.contains(i)) {
					int gradoS = graf.gradoSalida(j);
					suma += prank.get(j) / gradoS;
					
				}*/
				
			}
			double prnodo = 0.5 + 0.5*suma;
			//System.out.println("El PR del nodo "+i.getValor()+" es "+prnodo);
			prank.put(i.getValor(), prnodo);
		
		
		}
		
	}
		System.out.println("Imprimiendo MAPA");
		Iterator it = prank.keySet().iterator();
		while(it.hasNext()){
		  String key = (String) it.next();
		  System.out.println("Clave: " + key + " -> Valor: " + prank.get(key));
		}
		
		
		JFrame v = new JFrame("Prueba JTable");

		// Modelo de datos, segunda columna Integer y primera String. Los
		// índices empiezan en cero.
		DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public Class getColumnClass(int columna) {
				if (columna == 1)
					return Integer.class;
				return String.class;
			}
		};

		// Añadimos unos datos.
		modelo.addColumn("Planta");
		modelo.addColumn("Page Rank");
		/*for (int i = 0; i < 10; i++) {
			modelo.addRow(new Object[] { "" + i, 100 - i });
		}*/
		Iterator it2 = prank.keySet().iterator();

		while(it2.hasNext()){
			  String key = (String) it2.next();
			  //System.out.println("Clave: " + key + " -> Valor: " + prank.get(key));
			  modelo.addRow(new Object[] { key, prank.get(key) });
			}

		

		// Lo pintamos todo en la ventana y la mostramos.
		JTable tabla = new JTable(modelo);
		
		// Metemos el modelo ordenable en la tabla.
				modeloOrdenado = new TableRowSorter<TableModel>(modelo);
				List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
				sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
				modeloOrdenado.setSortKeys(sortKeys);
				
				tabla.setRowSorter(modeloOrdenado);
				
				//modeloOrdenado.setRowFilter(RowFilter.regexFilter("2", 1));
		
		JScrollPane scroll = new JScrollPane(tabla);
		v.getContentPane().add(scroll);
		v.pack();
		v.setVisible(true);
		v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	
	}	
}
