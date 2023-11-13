package alquilerAutos.consola;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import alquilerAutos.sistema.SistemaAlquilerAutos;

public class PanelAdminInicial extends JPanel {
	private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel logoLabel;
    private JLabel infoLabel;
    
    public PanelAdminInicial(SistemaAlquilerAutos sistema) {
    	// Configurar el panel principal
        setLayout(new BorderLayout());

        // Configurar el panel izquierdo
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(4, 1));
        JButton rq1Button = new JButton("Eliminar Vehiculo");
        JButton rq2Button = new JButton("Agregar Vehiculo");
        JButton rq3Button = new JButton("Agregar Empleado");
        JButton rq4Button = new JButton("Configurar Seguro");
        leftPanel.add(rq1Button);
        leftPanel.add(rq2Button);
        leftPanel.add(rq3Button);
        leftPanel.add(rq4Button);
        


        // Configurar el panel derecho con el logo inicial
        rightPanel = new JPanel();
        logoLabel = new JLabel(new ImageIcon("./data/AdministradorInicio.png")); // Reemplaza con la ruta real de tu logo
        rightPanel.add(logoLabel);

        // Agregar Listeners a los botones
        rq1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PanelEliminarVehiculo panelEliminarVehciulo = new PanelEliminarVehiculo(sistema);
                rightPanel.removeAll();
                rightPanel.add(panelEliminarVehciulo);
                
                revalidate();
                repaint();
            }
        });
        
        rq2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PanelAgregarVehiculo panelAgregarVehiculo = new PanelAgregarVehiculo(sistema);
                rightPanel.removeAll();
                rightPanel.add(panelAgregarVehiculo);
                
                revalidate();
                repaint();
               
            }
        });
        
        rq3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PanelAgregarEmpleado panelAgregarEmpleado = new PanelAgregarEmpleado(sistema);
                rightPanel.removeAll();
                rightPanel.add(panelAgregarEmpleado);
                
                revalidate();
                repaint();
                
            }
        });
        
        rq4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PanelConfigurarSeguro panelConfigurarSeguro = new PanelConfigurarSeguro(sistema);
                rightPanel.removeAll();
                rightPanel.add(panelConfigurarSeguro);
                
                revalidate();
                repaint();
    
            }
        });
        
        
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

   
    }
   
}

