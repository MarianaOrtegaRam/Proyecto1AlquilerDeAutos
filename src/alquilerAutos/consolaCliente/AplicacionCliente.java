package alquilerAutos.consolaCliente;

import javax.swing.*;

import alquilerAutos.consola.PanelTabb;
import alquilerAutos.sistema.SistemaAlquilerAutos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AplicacionCliente extends JPanel {

    private static JPanel cards;
    final static String PANEL_LOGIN = "Inicio de Sesión";
    final static String PANEL_INICIAL = "Pagina Principal";
    final static String PANEL_REGISTRO = "Registro";

    SistemaAlquilerAutos sistema;

    public AplicacionCliente(SistemaAlquilerAutos sistema) {
        this.sistema = sistema;
        // pagina inicial
        JPanel paginaPrincipal = new JPanel();
        paginaPrincipal.setLayout(new GridLayout(11, 1));
        JButton inicio = new JButton("Inicie Sesión");
        JButton registro = new JButton("Registrate!");

        inicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, PANEL_LOGIN);

            }
        });

        registro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) cards.getLayout();
                cardLayout.show(cards, PANEL_REGISTRO);
            }
        });

        Font fuente = new Font("verdana", Font.BOLD, 12);

        paginaPrincipal.add(new JLabel(""));
        JLabel titulo = new JLabel("                   "
                + "                                 Bienvid@ a RentYourOwn");
        titulo.setFont(fuente);
        paginaPrincipal.add(titulo);
        paginaPrincipal.add(new JLabel(""));
        paginaPrincipal.add(new JLabel("¿Ya tienes una cuenta?"));
        paginaPrincipal.add(inicio);
        paginaPrincipal.add(new JLabel(""));
        paginaPrincipal.add(new JLabel("¿Deseas crear una cuenta?"));
        paginaPrincipal.add(registro);
        paginaPrincipal.add(new JLabel(""));

        InicioSesion inicioSesion = new InicioSesion(sistema);
        RegistroCliente registroPanel = new RegistroCliente(sistema);

        cards = new JPanel(new CardLayout());
        cards.add(paginaPrincipal, PANEL_INICIAL);
        cards.add(inicioSesion, PANEL_LOGIN);
        cards.add(registroPanel, PANEL_REGISTRO);

        add(cards, BorderLayout.CENTER);

    }

    public void cargarArchivos(SistemaAlquilerAutos sistema) {
        try {
            sistema.cargarInformacionVehiculos();
            sistema.cargarInformacionOtros();
            sistema.cargarInformacionCliente();
            sistema.cargarInformacionEmpleado();
            sistema.cargarInformacionCondicionesCategoria();
            sistema.cargarInformacionSeguros();
            sistema.cargarInformacionSedes();
            sistema.cargarInformacionSedesOtras();
            sistema.cargarAdminSedes();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hubo un error leyendo los archivos", "Error de lectura",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    private static void createAndShowGUI(SistemaAlquilerAutos sistema) throws FileNotFoundException, IOException {
        // Create and set up the window.
        JFrame frame = new JFrame("App Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add content to the window.
        frame.add(new AplicacionCliente(sistema), BorderLayout.CENTER);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Crear una instancia del sistema
        SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();

        AplicacionCliente principal = new AplicacionCliente(sistema);
        principal.cargarArchivos(sistema);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                try {
                    createAndShowGUI(sistema);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

    }
}
