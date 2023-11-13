package alquilerAutos.consola;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import alquilerAutos.sistema.SistemaAlquilerAutos;

public class InicioAdmin extends JPanel {
	
	
    private static JPanel cards;
    public SistemaAlquilerAutos sistema;
    final static String PANEL_LOGIN = "Inicio de Sesión";
    final static String PANEL_CONTENIDO = "contenido";
    final static String PANEL_ERROR = "error";
    public static Container pane;
    
    public InicioAdmin (SistemaAlquilerAutos sistema) {
    	this.sistema = sistema;
    	//PANEL INICIO DE SESION
    	JPanel inicio_sesion = new JPanel();
    	inicio_sesion.setLayout(new GridLayout(12,1));
        JTextField usernameField = new JTextField(20);
        JTextField passwordField = new JTextField(20);
        JButton loginButton = new JButton("Iniciar Sesión");
        //

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                boolean verificado  = sistema.verificarAdministrador(username, password);
                if (verificado) {
                    CardLayout cardLayout = (CardLayout) cards.getLayout();
                    cardLayout.show(cards, PANEL_CONTENIDO);
                } else {
                	CardLayout cardLayout = (CardLayout) cards.getLayout();
                    cardLayout.show(cards, PANEL_ERROR);
                }
            }
        });
        
        
        
        Font fuente = new Font("verdana", Font.BOLD, 12); 
        
        inicio_sesion.add(new JLabel(""));
        JLabel titulo = new JLabel("                   "
        		+ "                                 Inicio de sesion Administrador General");
        titulo.setFont(fuente);
        inicio_sesion.add(titulo);
        inicio_sesion.add(new JLabel(""));
        inicio_sesion.add(new JLabel("Usuario"));
        inicio_sesion.add(usernameField);
        inicio_sesion.add(new JLabel(""));
        inicio_sesion.add(new JLabel("Contraseña"));
        inicio_sesion.add(passwordField);
        inicio_sesion.add(new JLabel(""));
        inicio_sesion.add(loginButton);
        
        //PANEL MENU ADMINISTRADOR
        PanelAdminInicial panelInicial = new PanelAdminInicial(sistema);
        //
  
        JPanel mensajeError = new JPanel();
        mensajeError.add(new JLabel("Intente de nuevo!"));

        // Crear el panel que usa CardLayout
        cards = new JPanel(new CardLayout());
        cards.add(inicio_sesion, PANEL_LOGIN );
        cards.add(panelInicial, PANEL_CONTENIDO);
        cards.add(mensajeError, PANEL_ERROR);

        add(cards, BorderLayout.CENTER);
    }
}


