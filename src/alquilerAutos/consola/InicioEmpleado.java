package alquilerAutos.consola;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import alquilerAutos.sistema.SistemaAlquilerAutos;

public class InicioEmpleado extends JPanel {
	
	
    private static JPanel cards;
    public SistemaAlquilerAutos sistema;
    final static String PANEL_LOGIN = "Inicio de Sesión";
    final static String PANEL_CONTENIDO = "Empleado";
    public static Container pane;
    
    
    public InicioEmpleado (SistemaAlquilerAutos sistema) {
    	this.sistema = sistema;
    	JPanel inicio_sesion = new JPanel();
    	inicio_sesion.setPreferredSize(new Dimension(300, 200));
    	inicio_sesion.add(new JLabel("Ingrese su nombre de usuario y contraseña:"));
        JTextField usernameField = new JTextField(20);
        JTextField passwordField = new JTextField(20);
        JButton loginButton = new JButton("Iniciar Sesión");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                boolean verificado  = sistema.verificarEmpleado(username, password);
                if (verificado) {
                    CardLayout cardLayout = (CardLayout) cards.getLayout();
                    cardLayout.show(cards, PANEL_CONTENIDO);
                }
            }
        });

        inicio_sesion.add(usernameField);
        inicio_sesion.add(passwordField);
        inicio_sesion.add(loginButton);

        JPanel interfazEmpleado = new JPanel();
        interfazEmpleado.add(new JLabel("¡Inicio de Sesión Exitoso!"));

        // Crear el panel que usa CardLayout
        cards = new JPanel(new CardLayout());
        cards.add(inicio_sesion, PANEL_LOGIN);
        cards.add(interfazEmpleado, PANEL_CONTENIDO);

        add(cards, BorderLayout.CENTER);
    }
}

