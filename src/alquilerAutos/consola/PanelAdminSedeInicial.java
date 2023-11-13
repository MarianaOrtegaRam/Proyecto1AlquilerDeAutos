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

public class PanelAdminSedeInicial extends JPanel {
	private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel logoLabel;
    private JLabel infoLabel;
    
    public PanelAdminSedeInicial(SistemaAlquilerAutos sistema) {
    	// Configurar el panel principal
        setLayout(new BorderLayout());

        // Configurar el panel izquierdo
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1));
        JButton rq1Button = new JButton("Cambiar sede");
        JLabel espacio1 = new JLabel("");
        JLabel espacio2 = new JLabel("");
  
        leftPanel.add(espacio1);
        leftPanel.add(rq1Button);
        leftPanel.add(espacio2);
    

        // Configurar el panel derecho con el logo inicial
        rightPanel = new JPanel();
        logoLabel = new JLabel(new ImageIcon("./data/sede.png")); 
        rightPanel.add(logoLabel);

        // Agregar Listeners a los botones
        rq1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PanelCambiarSede panelCambiarSede = new PanelCambiarSede(sistema);
                rightPanel.removeAll();
                rightPanel.add(panelCambiarSede);
                
                revalidate();
                repaint();
            }
        });
              
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

   
    }
   
}
