package alquilerAutos.consolaCliente;

import javax.swing.*;

import com.itextpdf.text.Jpeg;

import alquilerAutos.modelo.DatosCliente;
import alquilerAutos.sistema.SistemaAlquilerAutos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioSesion extends JPanel {
    boolean veificadoRt;
    SistemaAlquilerAutos sistema;
    final static String PANEL_LOGIN = "Inicio de Sesión";
    final static String PANEL_CONTENIDO = "contenido";
    public DatosCliente elCliente;

    private static JPanel cards;

    public InicioSesion(SistemaAlquilerAutos sistema) {
        JPanel estePanel = new JPanel();
        this.sistema = sistema;
        estePanel.setLayout(new GridLayout(12, 1));
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
        String username = usernameField.getText();
        this.elCliente = sistema.getDatosCliente(username);
        cards = new JPanel(new CardLayout());

        Font fuente = new Font("verdana", Font.BOLD, 12);

        add(new JLabel(""));
        JLabel titulo = new JLabel("                   "
                + "                                 Inicio de sesion Cliente");
        titulo.setFont(fuente);
        estePanel.add(titulo);
        estePanel.add(new JLabel(""));
        estePanel.add(new JLabel("Usuario"));
        estePanel.add(usernameField);
        estePanel.add(new JLabel(""));
        estePanel.add(new JLabel("Contraseña"));
        estePanel.add(passwordField);
        estePanel.add(new JLabel(""));
        estePanel.add(loginButton);

        Logueado content = new Logueado(sistema, elCliente);
        cards.add(estePanel, PANEL_LOGIN);
        cards.add(content, PANEL_CONTENIDO);

        add(cards, BorderLayout.CENTER);

    }
}
