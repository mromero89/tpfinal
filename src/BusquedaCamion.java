import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.text.MaskFormatter;

import dominio.Camion;

public class BusquedaCamion extends JFrame {
	
	
	JLabel patente = new JLabel("Patente");
	JLabel modelo = new JLabel("Modelo");
	JLabel kmrec = new JLabel("KM recorridos");
	JLabel costokm = new JLabel("Costo por KM");
	JLabel costoh = new JLabel("Costo por Hora");
	JLabel fechacompra = new JLabel("Fecha de Compra");
	JTextArea areatext = new JTextArea();
	
	JFormattedTextField fecha;
	JTextField campopatente = new JTextField(10);
	JTextField campomodelo = new JTextField(20);
	JTextField campokm = new JTextField(5);
	JTextField campocostokm = new JTextField(5);
	JTextField campocostoh = new JTextField(5);
	
	
	JButton limpiar = new JButton("Limpiar");
	JButton consulta = new JButton("Consulta");
	JButton borrar = new JButton("Borrar");
	JButton modificar = new JButton("Modificar seleccionado");
	
	JPanel principal = new JPanel();
	
	JTable tabla  = new JTable(2,3);

	JScrollPane a = new JScrollPane();

	
	
	public BusquedaCamion(){
		super("Consulta/Búsqueda/Borrado de Camiones");
		this.setVisible(true);
		
		BorderLayout bl = new BorderLayout();
		
		this.setLayout(bl);
		
		 MaskFormatter mascara = null;
		try {
			mascara = new MaskFormatter("####/##/##");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		fecha = new JFormattedTextField(mascara);
		fecha.setPreferredSize(new Dimension(80,20));
		
		
		JPanel subpanel = new JPanel();
		JPanel subpanelinf = new JPanel();
		
		subpanel.add(patente);
		subpanel.add(campopatente);
		subpanel.add(modelo);
		subpanel.add(campomodelo);
		subpanel.add(kmrec);
		subpanel.add(campokm);
		subpanel.add(costokm);
		subpanel.add(campocostokm);
		subpanel.add(costoh);
		subpanel.add(campocostoh);
		subpanel.add(fechacompra);
		subpanel.add(fecha);
		subpanel.add(limpiar);
		subpanel.add(areatext);
		subpanel.add(consulta);
		
		subpanelinf.add(modificar);
		subpanelinf.add(borrar);
		
		this.add(subpanel, BorderLayout.NORTH);
		this.add(a, BorderLayout.CENTER);
		
		
	
		
		modificar.addActionListener(e->{
			if (tabla.getSelectedRow() != -1) {
				ModificacionCamionSM smenu = new ModificacionCamionSM(tabla.getValueAt(tabla.getSelectedRow(), 0).toString(), tabla.getValueAt(tabla.getSelectedRow(), 1).toString(), tabla.getValueAt(tabla.getSelectedRow(), 2).toString(), tabla.getValueAt(tabla.getSelectedRow(), 3).toString(), tabla.getValueAt(tabla.getSelectedRow(), 4).toString(), tabla.getValueAt(tabla.getSelectedRow(), 5).toString(), tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
			}
			
			
		});
		
		
		
		consulta.addActionListener(e->{
			this.add(subpanelinf, BorderLayout.SOUTH);
			consultar();
			this.revalidate();
			
		});
		
		limpiar.addActionListener(a -> {
			campopatente.setText("");
			campomodelo.setText("");
			campokm.setText("");
			campocostokm.setText("");
			campocostoh.setText("");
			fecha.setText("");
			
		});
		
		borrar.addActionListener(a -> {
			if (tabla.getSelectedRow() != -1) {
				System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
				try {
					dao.AMBCamion.borrarcamion(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			this.consultar();
			
		});
		
		
		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	
	private void consultar() {
		ArrayList<Camion> lista = new ArrayList<Camion>();
		String paramfecha;
		try {
			
			if (fecha.getText().equals("    /  /  ")) {
				paramfecha = "";
				lista = dao.AMBCamion.busqueda(campopatente.getText(), campomodelo.getText(), campokm.getText(), campocostokm.getText(), campocostoh.getText(), paramfecha);
				
			}
			else {
				if (this.validar()) {
					paramfecha = fecha.getText();
					lista = dao.AMBCamion.busqueda(campopatente.getText(), campomodelo.getText(), campokm.getText(), campocostokm.getText(), campocostoh.getText(), paramfecha);

				}
				else {
					 JOptionPane.showMessageDialog(null, "Debe colocar la fecha en un formato AAAA/MM/DD", "Error",0);

				}
				

			}
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int tamano = lista.size();
		String [][]aux = new String [tamano][6];
		int i = 0; int j = 0;
		for (Camion c : lista) {
			aux[i][0]=c.getPatente();
			aux[i][1]=c.getModelo();
			aux[i][2]=String.valueOf(c.getKmrec());
			aux[i][3]=String.valueOf(c.getCostokm());
			aux[i][4]=String.valueOf(c.getCostoh());
			aux[i][5]=c.getFechacompra();
			i++;
			
		}
		
		
	
		String titulos[] = {"Patente", "Modelo", "KM Recorridos", "Costo KM", "Costo Hora", "Fecha de compra"};


		tabla = new JTable(aux, titulos);
		
		this.remove(a);

		a = new JScrollPane(tabla);
		this.add(a,BorderLayout.CENTER);
		
		
		this.revalidate();
		this.repaint();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		
	}
	
	private boolean validar() {

		//comprobacion de fecha
		   boolean valido = true;
		 String campo = fecha.getText();
		
		 
		   String[] resul = campo.split("/");
		   if (Integer.parseInt(resul[1]) < 1 || Integer.parseInt(resul[1]) > 12)
			   valido = false;
		   if (Integer.parseInt(resul[2]) < 1 || Integer.parseInt(resul[2]) > 31)
			   valido = false;
		   for (int i = 0; i< resul.length; i++) {
			   System.out.println(resul[i]);
		   }
		   System.out.println(valido);
		   return valido;
		 
		   
	}
	
	
	
	
	
	

}
