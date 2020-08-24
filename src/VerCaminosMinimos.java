import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dao.ABMRuta;
import dao.AMBPlanta;
import dominio.Planta;
import dominio.Ruta;

public class VerCaminosMinimos extends JFrame {
	
	ScrollPane resu = new ScrollPane();

	
	
	public VerCaminosMinimos() throws SQLException {
		super("Ver matrices de caminos mínimos");
		
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
	
	
		
		JButton duracion = new JButton("Por Duración");
		JButton distancia = new JButton("Por distancia en KM");
		
		JTextArea resultado = new JTextArea();
		
		BorderLayout bl = new BorderLayout();
		
		this.setLayout(bl);
		
		this.setVisible(true);

		
		JPanel botones = new JPanel();
		botones.add(duracion);
		botones.add(distancia);
		
		this.add(botones, BorderLayout.NORTH);
		this.add(resu, BorderLayout.CENTER);
		

		this.pack();
		
		duracion.addActionListener(e-> {
			this.remove(resu);
			String salida = "Matrices de caminos mínimos según DURACIÓN \n";
			//salida.concat(grafdu.floydWarshall());
			salida = salida + grafdu.floydWarshall();
			resultado.setText(salida);
			resu.add(resultado);

			this.add(resu, BorderLayout.CENTER);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);

			this.repaint();
			this.revalidate();
			
		});
		
		distancia.addActionListener(e-> {
			this.remove(resu);
			String salida = "Matrices de caminos mínimos según DISTANCIA EN KM. \n";
			//salida.concat(grafdu.floydWarshall());
			salida = salida + graf.floydWarshall();
			resultado.setText(salida);
			resu.add(resultado);

			this.add(resu, BorderLayout.CENTER);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);

			this.repaint();
			this.revalidate();
		});
		
	}

}
