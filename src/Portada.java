import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Portada extends ContenedorPrincipal implements ActionListener
{
	JPanel panelCentro,panelConsulta;

    public Portada(){
        super("MENUPRINCIPAL                                                                          ");
        setLayout(new GridLayout(1,1));   
        panelCentro();

    }

    public  void panelCentro()
    {
        panelCentro = new JPanel(new GridLayout(5,1));
        JButton consulta = new JButton("Consulta");   
        JButton modificar = new JButton("Modificar");
        JButton agregar = new JButton("Agregar");
        JButton remover = new JButton("Remover");
        JButton salir = new JButton("Salir");
        panelCentro.add(consulta);
        consulta.addActionListener(this);
        panelCentro.add(modificar);
        modificar.addActionListener(this);
        panelCentro.add(agregar);
        agregar.addActionListener(this);
        panelCentro.add(remover);
        remover.addActionListener(this);
        panelCentro.add(salir);
        salir.addActionListener(this);
        add(panelCentro);
    }

    public  void panelConsulta()
    {
        panelConsulta = new JPanel(new GridLayout(2,1));
        JButton ingresarID = new JButton("Ingresar ID");
        JButton cancelar = new JButton("Cancelar");
        panelConsulta.add(ingresarID);
        ingresarID.addActionListener(this);
        panelConsulta.add(cancelar); 
        cancelar.addActionListener(this);
        add(panelConsulta);
    }

    public void actionPerformed(ActionEvent e)
    {
        // Eventos botones panelCentro
       
        if (e.getActionCommand().equals("Salir"))
        {
            System.exit(0);
        }     
        if (e.getActionCommand().equals("Consulta"))
        {
            panelCentro.setVisible(false);remove(panelCentro); 
            panelConsulta();
        }
        if (e.getActionCommand().equals("Modificar"))
        {
          //Aqui va el evento que se produciria al pulsar "Modificar"
        }
        if (e.getActionCommand().equals("Agregar"))
        {
             //Aqui va el evento que se produciria al pulsar "Agregar"
        }
        if (e.getActionCommand().equals("Remover"))
        {
             //Aqui va el evento que se produciria al pulsar "Remover"
        }
        // Eventos botones panelConsulta
        if(e.getActionCommand().equals("Cancelar"))
        {
            panelConsulta.setVisible(false);remove(panelConsulta);panelCentro();
        }
        if(e.getActionCommand().equals("Ingresar ID"))
        {
            //Aqui va el evento que se produciria al pulsar "Ingresar ID"
        }
    }
}