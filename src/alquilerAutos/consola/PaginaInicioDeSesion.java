package alquilerAutos.consola;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import alquilerAutos.modelo.Administrador;
import alquilerAutos.sistema.SistemaAlquilerAutos;


public class PaginaInicioDeSesion extends JPanel {
	
	
    private static JPanel cards;
    public SistemaAlquilerAutos sistema;
    final static String PANEL_LOGIN = "Inicio de Sesión";
    final static String PANEL_CONTENIDO = "Cliente";
    
    
    public PaginaInicioDeSesion (SistemaAlquilerAutos sistema, String usuario) {
    	this.sistema = sistema;
    	//this.pane = new Container();
    	JPanel interfaz = new JPanel();
        interfaz.add(new JLabel("¡Inicio de Sesión Exitoso!"));

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
                //boolean verificado  = verificarLoginContraseña(username, password,usuario);
                boolean verificado  = false;

                if (usuario.equals("cliente")) {
            		verificado = sistema.verificarCliente(username, password);
            	}
            	else if (usuario.equals("empleado")) {
            		verificado = sistema.verificarEmpleado(username, password);
            	}
            	else if (usuario.equals("administrador")) {
            		verificado = sistema.verificarAdministrador(username, password);
            	}
            	else if(usuario.equals("administradorSede")) {
            		verificado = sistema.verificarAdminSede(username, password);
            	}

                if (verificado) {
                	System.out.println("whyyyyyyyyyyyyyyyyyy");
                	CardLayout cardLayout = (CardLayout) cards.getLayout();
                    cardLayout.show(cards, PANEL_CONTENIDO);
                }
            }
        });

        inicio_sesion.add(usernameField);
        inicio_sesion.add(passwordField);
        inicio_sesion.add(loginButton);

        
        // Crear el panel que usa CardLayout
        cards = new JPanel(new CardLayout());
        cards.add(inicio_sesion, PANEL_LOGIN);
        cards.add(interfaz, PANEL_CONTENIDO);

        add(cards, BorderLayout.CENTER);
    }
}

//NO QUISO SERVIR
    

/*
public boolean verificarLoginContraseña(String login, String password, String usuario) {
	boolean verificado = false;
	if (usuario.equals("cliente")) {
		verificado = sistema.verificarCliente(login, password);
	}
	else if (usuario.equals("empleado")) {
		verificado = sistema.verificarEmpleado(login, password);
	}
	else if (usuario.equals("administrador")) {
		verificado = sistema.verificarAdministrador(login, password);
	}
	else if(usuario.equals("administrador sede")) {
		verificado = sistema.verificarAdminSede(login, password);
	}

	return verificado;
}
}
*/

/*   
    private static void createAndShowGUI() throws FileNotFoundException, IOException {
        // Crear y configurar la ventana
        JFrame frame = new JFrame("Página de Inicio de Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear una instancia de la aplicación
        pane = frame.getContentPane();
        SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();
        ArrayList<DatosCliente> clientes = new ArrayList<>();
        sistema.cargarInformacionCliente();
        PaginaInicioDeSesion inicio = new PaginaInicioDeSesion(sistema);

        // Mostrar la ventana
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Programar la creación y visualización de la GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
					createAndShowGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
   */


