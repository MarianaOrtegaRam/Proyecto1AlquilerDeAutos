package alquilerAutos.consola;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import alquilerAutos.sistema.SistemaAlquilerAutos;

public class PanelEmpleadoInicial extends JPanel {
	
	private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel logoLabel;
    private JLabel infoLabel;
    
    public PanelEmpleadoInicial(SistemaAlquilerAutos sistema) {
    	// Configurar el panel principal
        setLayout(new BorderLayout());

        // Configurar el panel izquierdo
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2, 1));
        JButton rq1Button = new JButton("Entregar Vehiculo");
        JButton rq2Button = new JButton("Recibir Vehiculo");
      
        leftPanel.add(rq1Button);
        leftPanel.add(rq2Button);


        // Configurar el panel derecho con el logo inicial
        rightPanel = new JPanel();
        logoLabel = new JLabel(new ImageIcon("./data/empleado.png")); // Reemplaza con la ruta real de tu logo
        rightPanel.add(logoLabel);

        // Agregar Listeners a los botones
        rq1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PanelEntregarVehiculo panelEntregarVehiculo = new PanelEntregarVehiculo(sistema);
                rightPanel.removeAll();
                rightPanel.add(panelEntregarVehiculo);
                
                revalidate();
                repaint();
            }
        });
        
        rq2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PanelRecibirVehiculo panelRecibirVehiculo = new PanelRecibirVehiculo(sistema);
                rightPanel.removeAll();
                rightPanel.add(panelRecibirVehiculo);
                
                revalidate();
                repaint();
               
            }
        });
       
        
        
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

   
    }
   
}


