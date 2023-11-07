package alquilerAutos.consola;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;

public class PanelInfoInicial extends JPanel{
	
	private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel logoLabel;
    private JLabel infoLabel;

    public PanelInfoInicial() {
        // Configurar el panel principal
        setLayout(new BorderLayout());

        // Configurar el panel izquierdo
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1));
        JButton categoriesButton = new JButton("Categorías Disponibles");
        JButton locationsButton = new JButton("Nuestras Sedes");
        JButton faqButton = new JButton("Preguntas Frecuentes");
        leftPanel.add(categoriesButton);
        leftPanel.add(locationsButton);
        leftPanel.add(faqButton);

        // Configurar el panel derecho con el logo inicial
        rightPanel = new JPanel();
        logoLabel = new JLabel(new ImageIcon("./data/imagen.png")); // Reemplaza con la ruta real de tu logo
        rightPanel.add(logoLabel);

        // Agregar Listeners a los botones
        categoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                infoLabel = new JLabel("Mostrando Categorías Disponibles");
                rightPanel.add(infoLabel);
                revalidate();
                repaint();
            }
        });

        locationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                infoLabel = new JLabel("Mostrando Nuestras Sedes");
                rightPanel.add(infoLabel);
                revalidate();
                repaint();
            }
        });

        faqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                infoLabel = new JLabel("Mostrando Preguntas Frecuentes");
                rightPanel.add(infoLabel);
                revalidate();
                repaint();
            }
        });

        // Añadir los paneles al panel principal
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }
}
/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Car Rental Panel Example");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                PanelInfoInicial carRentalPanel = new PanelInfoInicial();
                frame.add(carRentalPanel);
                frame.setSize(800, 600);
                frame.setVisible(true);
            }
        });
    }
}
*/