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

public class PanelInfoInicial extends JPanel {

    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel logoLabel;
    private JLabel infoLabel;

    public PanelInfoInicial(JPanel panelCategorias, JPanel panelInfoSedes) {
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
                rightPanel.add(panelCategorias);

                revalidate();
                repaint();
            }
        });

        locationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll();
                rightPanel.add(panelInfoSedes);
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