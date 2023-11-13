package alquilerAutos.consola;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import alquilerAutos.sistema.SistemaAlquilerAutos;

import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PanelTabb extends JPanel {
	public SistemaAlquilerAutos sistema;
	
	public PanelTabb(SistemaAlquilerAutos sistema) throws FileNotFoundException, IOException {
		
		super(new GridLayout(1, 1)); 
		sistema.cargarInformacionVehiculos();
		sistema.cargarInformacionCliente();
		sistema.cargarInformacionEmpleado();
		sistema.cargarInformacionCondicionesCategoria();
		sistema.cargarInformacionSeguros();
		sistema.cargarInformacionSedes();
		sistema.cargarAdminSedes();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel panelCategorias = new PanelCategoria(sistema);
        JPanel panelInfoSedes = new PanelInfoSedes(sistema);
        PanelInfoInicial panelInicial = new PanelInfoInicial(panelCategorias, panelInfoSedes);
        tabbedPane.addTab("About Us", panelInicial);
		InicioEmpleado inicioEmpleado = new InicioEmpleado(sistema);
		InicioAdmin inicioAdmin = new InicioAdmin(sistema);
		InicioAdminSede inicioAdminSede = new InicioAdminSede(sistema);
		InicioCliente inicioCliente = new InicioCliente(sistema);
        tabbedPane.addTab("inicio cliente",inicioCliente);
        tabbedPane.addTab("inicio empleado",inicioEmpleado);
        tabbedPane.addTab("inicio admin",inicioAdmin);
        tabbedPane.addTab("inicio admin sede",inicioAdminSede);
      
        //Add the tabbed pane to this panel.
        add(tabbedPane);
         
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
     
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
     
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = PanelTabb.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    private static void createAndShowGUI(SistemaAlquilerAutos sistema) throws FileNotFoundException, IOException {
        //Create and set up the window.
        JFrame frame = new JFrame("Alquiler De Autos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Add content to the window.
        frame.add(new PanelTabb(sistema), BorderLayout.CENTER);
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }    
   
    public void cargarArchivos(SistemaAlquilerAutos sistema)
	{
		try
		{
			sistema.cargarInformacionVehiculos();
			sistema.cargarInformacionCliente();
			sistema.cargarInformacionEmpleado();
			sistema.cargarInformacionCondicionesCategoria();
			sistema.cargarInformacionSeguros();
			sistema.cargarInformacionSedes();
			;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, "Hubo un error leyendo los archivos", "Error de lectura",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}
    	

    public static void main(String[] args)throws IOException, ClassNotFoundException, InstantiationException,
	IllegalAccessException, UnsupportedLookAndFeelException {
    	
    	SistemaAlquilerAutos sistema =  new SistemaAlquilerAutos();
    
    	PanelTabb principal = new PanelTabb(sistema);
    	principal.cargarArchivos(sistema);
    	
    	
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        try {
			createAndShowGUI(sistema);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            }
        });
    }
}
