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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import alquilerAutos.modelo.DatosCliente;
import alquilerAutos.sistema.SistemaAlquilerAutos;

public class InicioCliente extends JPanel {
    private static JPanel cards;
    final static String PANEL_REALIZAR_RESERVA = "realizar reserva";
    final static String PANEL_ERROR = "error";
    public static Container pane;
    public SistemaAlquilerAutos sistema;
    final static String PANEL_LOGIN = "Inicio de Sesión";
    final static String PANEL_CONTENIDO = "Cliente";
    private DatosCliente elCliente;

    public InicioCliente(SistemaAlquilerAutos sistema) {
        this.sistema = sistema;
        JPanel inicio_sesion = new JPanel();
        inicio_sesion.setLayout(new GridLayout(12, 1));
        JTextField usernameField = new JTextField(20);
        JTextField passwordField = new JTextField(20);
        JButton loginButton = new JButton("Iniciar Sesión");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                boolean verificado = sistema.verificarCliente(username, password);
                if (verificado) {
                    CardLayout cardLayout = (CardLayout) cards.getLayout();
                    cardLayout.show(cards, PANEL_CONTENIDO);
                    elCliente = sistema.getDatosCliente(username);
                } else {
                	JOptionPane.showMessageDialog(null, "INTENTE DE NUEVO", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        Font fuente = new Font("verdana", Font.BOLD, 12);

        inicio_sesion.add(new JLabel(""));
        JLabel titulo = new JLabel("                   "
                + "                                              Inicio de sesion Cliente");
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

        // PANEL MENU cLIENTE
        JPanel contenidoPanel = new JPanel();
        contenidoPanel.setLayout(new BorderLayout());
        JPanel menuCliente = new JPanel();

        menuCliente.setLayout(new GridLayout(3, 1));

        JButton botonRq1 = new JButton("Realizar Reserva");
        menuCliente.add(botonRq1);

        JButton botonRq2 = new JButton("Modificar Reserva");
        menuCliente.add(botonRq2);
        JPanel panelEjecutar = new JPanel();
        JLabel logoLabel = new JLabel(new ImageIcon("./data/imagen.png")); // Reemplaza con la ruta real de tu logo
        panelEjecutar.add(logoLabel);
        
        JButton botonRq3 = new JButton("Registrar cliente");
        menuCliente.add(botonRq3);


        botonRq1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelEjecutar.removeAll();
                PanelRealizarReserva panelReserva = new PanelRealizarReserva(sistema,elCliente);
                panelEjecutar.add(panelReserva);
                revalidate();
                repaint();
            }

        });
        
        botonRq3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelEjecutar.removeAll();
                PanelRegistrarCliente panelRegistrar = new PanelRegistrarCliente(sistema);
                panelEjecutar.add(panelRegistrar);
                revalidate();
                repaint();
            }

        });

        JPanel mensajeError = new JPanel();
        mensajeError.add(new JLabel("Intente de nuevo!"));

        // Crear el panel que usa CardLayout
        cards = new JPanel(new CardLayout());
        cards.add(inicio_sesion);
        contenidoPanel.add(menuCliente, BorderLayout.WEST);
        contenidoPanel.add(panelEjecutar, BorderLayout.CENTER);
        cards.add(contenidoPanel, PANEL_CONTENIDO);
        cards.add(mensajeError, PANEL_ERROR);

        add(cards, BorderLayout.CENTER);
    }

}
