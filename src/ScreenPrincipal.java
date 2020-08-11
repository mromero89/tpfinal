import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import dao.ABMRuta;
import dao.AMBPlanta;
import dominio.Planta;
import dominio.Ruta;

public class ScreenPrincipal extends JFrame {
	
	ScreenPrincipal() {
		super("Sistema de gestion de plantas");
		this.setVisible(true);

		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel principal = new JPanel();
		this.setContentPane(principal);
		
		JMenuBar barramenu = new JMenuBar();
		
		//Menu Camiones
		JMenu camiones = new JMenu("Camiones");
		JMenuItem acamion = new JMenuItem("Alta");
		JMenuItem busquedacamion = new JMenuItem("Busqueda/Modificacion/Eliminacion de Camion");
		
		camiones.add(acamion);
		camiones.add(busquedacamion);
		
		//Menu Plantas
		JMenu plantas = new JMenu("Plantas");
		JMenuItem aplanta = new JMenuItem("Alta");
		JMenuItem bplanta = new JMenuItem("Baja");
		JMenuItem mplanta = new JMenuItem("Modificacion");
		JMenuItem registrar = new JMenuItem("Registrar Stock");
		JMenuItem stockinferior = new JMenuItem("Plantas c/ stock menor a punto de pedido");

		
		plantas.add(aplanta);
		plantas.add(bplanta);
		plantas.add(mplanta);
		plantas.add(registrar);
		plantas.add(stockinferior);
		
		//Menu Rutas
		JMenu rutas = new JMenu("Rutas");
		JMenuItem aruta = new JMenuItem("Alta");
		JMenuItem bruta = new JMenuItem("Baja");
		JMenuItem mruta = new JMenuItem("Modificacion");
		JMenuItem busquedaruta = new JMenuItem("Busqueda de Rutas");
		
		rutas.add(aruta);
		rutas.add(bruta);
		rutas.add(mruta);
		rutas.add(busquedaruta);


		
		
		//Menu Insumos
		JMenu insumos = new JMenu("Insumos");
		JMenuItem ainsumo = new JMenuItem("Alta");
		JMenuItem binsumo = new JMenuItem("Baja");
		JMenuItem minsumo = new JMenuItem("Modificacion");
		JMenuItem busquedainsumo = new JMenuItem("Busqueda de Insumos");
				
		insumos.add(ainsumo);
		insumos.add(binsumo);
		insumos.add(minsumo);
		insumos.add(busquedainsumo);
		
		//Menu ordenes de pedido
		JMenu ordenes = new JMenu("Ordenes de pedido");
		JMenuItem aorden = new JMenuItem("Alta");
		JMenuItem creadas = new JMenuItem("Ver ordenes CREADAS");
		
		
		ordenes.add(aorden);
		ordenes.add(creadas);
		
		barramenu.add(camiones);
		barramenu.add(plantas);
		barramenu.add(rutas);
		barramenu.add(insumos);
		barramenu.add(ordenes);
		
		

		
		
		this.setJMenuBar(barramenu);
		
		//Eventos de Menues
		
		creadas.addActionListener(e-> {
			try {
				VerOrdenesCreadas ver = new VerOrdenesCreadas();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		aorden.addActionListener(e->{
			AltaOrdenPedido alta = new AltaOrdenPedido();
		});
		
		stockinferior.addActionListener(e-> {
			PlantasStockMenor psm = new PlantasStockMenor();
		});
		
		busquedainsumo.addActionListener(e-> {
			BusquedaInsumo busqueda = new BusquedaInsumo();
		});
		
		registrar.addActionListener(e-> {
			AltaStock alta = new AltaStock();
		});
		
		ainsumo.addActionListener(e -> {
			AltaInsumo alta = new AltaInsumo();
		});
		
		acamion.addActionListener(e -> {
			AltaCamion alta = new AltaCamion();
			
			
			});
		
		aplanta.addActionListener(e -> {
			AltaPlanta alta = new AltaPlanta();
			
			
			});
		
		busquedacamion.addActionListener(e -> {
			BusquedaCamion busqueda = new BusquedaCamion();
			
		});
		
		aruta.addActionListener(e-> {
			AltaRuta alta = new AltaRuta();
		});
		
		this.pack();
		this.setSize(800, 800);
		
	}
	

}
