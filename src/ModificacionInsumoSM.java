import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dominio.Camion;

public class ModificacionInsumoSM extends JFrame{
	JLabel patente = new JLabel("Patente");
	JLabel modelo = new JLabel("Modelo");
	JLabel kmrec = new JLabel("KM recorridos");
	JLabel costokm = new JLabel("Costo por KM");
	JLabel costoh = new JLabel("Costo por Hora");
	JLabel fechacompra = new JLabel("Fecha de Compra");
	JTextArea areatext = new JTextArea();
	
	JTextField campopatente = new JTextField(10);
	JTextField campomodelo = new JTextField(20);
	JTextField campokm = new JTextField(5);
	JTextField campocostokm = new JTextField(5);
	JTextField campocostoh = new JTextField(5);
	JTextField campofechacompra = new JTextField(10);
	
	
	
	
	JButton modificar = new JButton("Modificar");
	
	JPanel principal = new JPanel();
	String clave;
	
	JTable tabla  = new JTable(2,3);
	//String titulos[] = {"Patente", "Modelo", "KM Recorridos", "Costo KM", "Costo Hora", "Fecha de compra"};


	
	
	public ModificacionInsumoSM(String cpatente, String cmodelo, String ckmrec, String ccostokm, 
			String ccostohora, String cfecha, String clave){
		super("Modificar datos de Camion");
		this.setVisible(true);
		//this.setLayout();
		this.campopatente.setText(cpatente);
		this.campomodelo.setText(cmodelo);
		this.campokm.setText(ckmrec);
		this.campocostokm.setText(ccostokm);
		this.campocostoh.setText(ccostohora);
		this.campofechacompra.setText(cfecha);
		this.clave = clave;
		
		
		
		//tabla.addColumn("Nombre");
		
		
		
		this.setContentPane(principal);
	
		
		
		
		
		
		modificar.addActionListener(e->{
			try {
				dao.AMBCamion.modificarcamion(campopatente.getText(), campomodelo.getText(), campokm.getText(), campocostokm.getText(), campocostoh.getText(), campofechacompra.getText(), clave);
				JOptionPane.showMessageDialog(null, "Se modificó el registro con éxito", "Información",1);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				 JOptionPane.showMessageDialog(null, "Ocurrio un error al intentar grabar los datos. Verifique que todos los campos esten completos y en el formato que corresponde", "Error",0);

				//e1.printStackTrace();
			}
			
		});
		
		

		
		principal.add(patente);
		principal.add(campopatente);
		principal.add(modelo);
		principal.add(campomodelo);
		principal.add(kmrec);
		principal.add(campokm);
		principal.add(costokm);
		principal.add(campocostokm);
		principal.add(costoh);
		principal.add(campocostoh);
		principal.add(fechacompra);
		principal.add(campofechacompra);
		principal.add(areatext);
		principal.add(modificar);
		
		
		
		
		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
	

	private void consultar() {
		ArrayList<Camion> lista = new ArrayList<Camion>();
		try {
			lista = dao.AMBCamion.busqueda(campopatente.getText(), campomodelo.getText(), campokm.getText(), campocostokm.getText(), campocostoh.getText(), campofechacompra.getText());
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
			
			//areatext.append(i.getPatente()+"\n");
		}
		
		
		//principal.remove(tabla);
		//principal.revalidate();
		//pack();
		String titulos[] = {"Patente", "Modelo", "KM Recorridos", "Costo KM", "Costo Hora", "Fecha de compra"};


		JTable tablaresu = new JTable(aux, titulos);
		this.remove(tabla);
		tabla = new JTable(aux, titulos);
		
		//principal.add(tablaresu);
		//principal.add(tabla);
		
		//este anda bien
		principal.remove(tabla);
		principal.add(new JScrollPane(tabla),BorderLayout.CENTER);
		
		
		//principal.add(tabla);
		//tabla.repaint();
		
		//tabla.repaint();

		principal.revalidate();
		principal.repaint();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);


}
}