import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.ABMRuta;
import dao.AMBPlanta;
import dominio.Planta;
import dominio.Ruta;

public class VerFlujoMaximo extends JFrame{
	
	
	JComboBox inicio = null, destino = null;

	
	public VerFlujoMaximo() throws SQLException {
		
			
		super("Flujo Máximo");
		
		BorderLayout bl = new BorderLayout();
		
		this.setLayout(bl);
		
		JPanel principal = new JPanel();
		
		this.setVisible(true);
		

		//this.setContentPane(principal);

		
		JLabel einicio = new JLabel("Planta Inicio");
		JLabel edestino = new JLabel("Planta Destino");
		
		
		JButton ejecutar = new JButton("Ejecutar");
		
		
		
		
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
				
				principal.add(einicio);
				principal.add(inicio);
				principal.add(edestino);
				principal.add(destino);
				principal.add(ejecutar);
				this.add(principal, BorderLayout.NORTH);
				
				this.pack();
		
		
		
		
	
	ejecutar.addActionListener(e->{
		//EJEMPLO DE CARGA DE GRAFO DE DISTANCIA
				ArrayList<Planta> listaplantas = null;
				try {
					listaplantas = AMBPlanta.todos();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<Ruta> listarutas = null;
				try {
					listarutas = ABMRuta.todos();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			Grafo<String> graf = new Grafo<String>();
			//CArgando el grafo de DISTANCIAS
			for (Planta i : listaplantas) {
				graf.addNodo(i.getNombre());
			}
			
			for (Ruta i : listarutas) {
				graf.conectar(i.getOrigen().getNombre(), i.getDestino().getNombre(), i.getPesomax());
			}
			
			
			//FIN DE CARGA DE GRAFO
		
		int fmax = 0;
		System.out.println("ingresando a rutina prototipo para fmax");
		System.out.println("Planta inicio: "+"Buenos Aires");
		System.out.println("Planta fin: "+"Entre Rios");
		List<List<Vertice<String>>> listnodes = graf.caminos(inicio.getSelectedItem().toString(), destino.getSelectedItem().toString());
		for (List<Vertice<String>> i : listnodes) {
			int minimoc = 99999;
			Vertice<String> nodo1, nodo2;
			nodo1 = null; nodo2 = null;
			List<Vertice<String>> auxi = i;
			List<Vertice<String>> auxi2 = auxi;
			for (Vertice<String> j : auxi) {
				//evaluar si el nodo no es el final
				//if (!(j.equals(this.plantadestino))) {
				if (!(j.equals(destino.getSelectedItem().toString()))) {
					
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
				if (!(j.equals(destino.getSelectedItem().toString()))) {
					
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
		//System.out.println("El flujo maximo FINAL obtenido es: "+fmax);
		JOptionPane.showMessageDialog(null, "El flujo máximo entre "+inicio.getSelectedItem().toString()+" y "+destino.getSelectedItem().toString()+" es "+fmax, "Información",1);

		
		
	});
	
	
		
	}
	
	

	
	
}
