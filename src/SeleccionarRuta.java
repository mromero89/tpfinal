import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.ABMDetalleEnvio;
import dao.ABMOrdenPedido;
import dao.ABMRuta;
import dao.AMBCamion;
import dao.AMBPlanta;
import dominio.Camion;
import dominio.Planta;
import dominio.Ruta;

public class SeleccionarRuta extends JFrame{
	int costo = 0;
	
	public SeleccionarRuta(JTable tabla, String nombre, Camion camion, int nropedido) {
		super(nombre);
		this.setVisible(true);
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);
		
		
		JTable tablarutas = tabla;
		JButton seleccionar = new JButton("Seleccionar ruta");
		this.add(tablarutas, BorderLayout.CENTER);
		this.add(seleccionar, BorderLayout.SOUTH);
		pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//System.out.println("Camion asignado es:" + camion.getPatente());
		
		


		
		seleccionar.addActionListener(f->{
			//Ruta seleccionada
			System.out.println(tablarutas.getValueAt(tabla.getSelectedRow(), 0));
			
			String[] resul = tablarutas.getValueAt(tabla.getSelectedRow(), 0).toString().split(",");
			
			
			
			
			String[] listaSalida = new String[resul.length];
			listaSalida[0] = resul[0].substring(1);
			listaSalida[resul.length-1]= resul[resul.length-1].substring(1, resul[resul.length-1].length()-1);
			
			
			for(int i=1; i<resul.length-1; i++) {
				
					listaSalida[i] = resul[i].substring(1);
				
							
			}
			

			
			//planta de inicio de la ruta seleccionada
			String plantainicio = resul[0].substring(1);
			String plantafin = resul[resul.length-1].substring(1, resul[resul.length-1].length()-1);
			
			
			try {
				costo = costo(camion, listaSalida);
				//System.out.println("El costo total es de "+costo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int kilometros;
			String km = "";
			try {
				kilometros = distancia(camion, listaSalida) + camion.getKmrec();
				km = String.valueOf(kilometros);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
				
			try {
				AMBCamion.modificarcamion(camion.getPatente(), camion.getModelo(), km, String.valueOf(camion.getCostokm()), String.valueOf(camion.getCostoh()), camion.getFechacompra(), camion.getPatente());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				ABMDetalleEnvio.alta(String.valueOf(nropedido), camion.getPatente(), tablarutas.getValueAt(tabla.getSelectedRow(), 0).toString(), String.valueOf(costo));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				ABMOrdenPedido.modificar(String.valueOf(nropedido), plantainicio, "PROCESADA");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, "Se guardó la orden como PROCESADA. Camión Seleccionado: "+camion.getPatente()+". Costo del Envio: "+costo, "Información",1);
			this.dispose();

		});
		
		
		


	}
	
	
	private int costo(Camion camion, String[] ruta) throws SQLException {
		//CARGAR LOS GRAFOS
		
		// CARGA DE GRAFO DE DISTANCIA
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
			
			//FIN CARGA GRAFO DISTANCIA
			
			
			
			// CARGA DE GRAFO DE DURACION		

				Grafo<String> grafdu = new Grafo<String>();
				//CArgando el grafo de DISTANCIAS
				for (Planta i : listaplantas) {
					grafdu.addNodo(i.getNombre());
				}
				
				for (Ruta i : listarutas) {
					grafdu.conectar(i.getOrigen().getNombre(), i.getDestino().getNombre(), i.getDuracionh());
				}
				//FIN CARGA GRAFO DURACION
		
				int duracion =0; int distancia = 0;
				for (int i = 0; i<ruta.length; i++) {
					if (i<(ruta.length-1)) {
						Arista<String> a = grafdu.buscarArista(ruta[i], ruta[i+1]);
						int valor = (int) a.getValor();
						duracion += valor;
						//System.out.println("se suma el valor: "+valor);
						//System.out.println("Arista va de "+a.getInicio()+" a "+a.getFin());
						
						Arista<String> b = graf.buscarArista(ruta[i], ruta[i+1]);
						int valor2 = (int) b.getValor();
						distancia += valor2;
					}
				}
		
		int c = 0;
		c = camion.getCostokm()*distancia+camion.getCostoh()*duracion;
		
		return c;
	}
	
	private int distancia(Camion camion, String[] ruta) throws SQLException {
		//CARGAR LOS GRAFOS
		
		// CARGA DE GRAFO DE DISTANCIA
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
			
			//FIN CARGA GRAFO DISTANCIA
			
			
		
				int distancia = 0;
				for (int i = 0; i<ruta.length; i++) {
					if (i<(ruta.length-1)) {
						
						
						Arista<String> b = graf.buscarArista(ruta[i], ruta[i+1]);
						int valor2 = (int) b.getValor();
						distancia += valor2;
					}
				}
		
		
		return distancia;
	}
	

}
