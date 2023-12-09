package alquilerAutos.consolaCliente;

import javax.swing.*;

import alquilerAutos.consola.PanelRealizarReserva;
import alquilerAutos.consola.PanelRegistrarCliente;
import alquilerAutos.modelo.DatosBasicos;
import alquilerAutos.modelo.DatosCliente;
import alquilerAutos.modelo.DatosLicencia;
import alquilerAutos.sistema.SistemaAlquilerAutos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Logueado extends JPanel {
    SistemaAlquilerAutos sistema;

    public Logueado(SistemaAlquilerAutos sistema, DatosCliente cliente) {
        JPanel contenidoPanel = new JPanel();
        contenidoPanel.setLayout(new BorderLayout());
        JPanel menuCliente = new JPanel();

        menuCliente.setLayout(new GridLayout(2, 1));

        JButton botonRq1 = new JButton("Realizar Reserva");
        menuCliente.add(botonRq1);

        JPanel panelEjecutar = new JPanel();
        JLabel logoLabel = new JLabel(new ImageIcon("./data/imagen.png")); // Reemplaza con la ruta real de tu logo
        panelEjecutar.add(logoLabel);

        JButton botonRq3 = new JButton("Consultar Disponibilidad");
        menuCliente.add(botonRq3);

        botonRq1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelEjecutar.removeAll();
                RealizarReserva panelReserva = new RealizarReserva(sistema, cliente);
                panelEjecutar.add(panelReserva);
                revalidate();
                repaint();
            }

        });

        botonRq3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelEjecutar.removeAll();
                ConsultarInfo panelC = new ConsultarInfo(sistema);
                panelEjecutar.add(panelC);
                revalidate();
                repaint();
            }

        });

        contenidoPanel.add(menuCliente, BorderLayout.WEST);
        contenidoPanel.add(panelEjecutar, BorderLayout.CENTER);
        add(contenidoPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ventana de Prueba");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            SistemaAlquilerAutos sistema = new SistemaAlquilerAutos(); // Crea una instancia del sistema (ajusta según
                                                                       // tu implementación)

            // Supongamos que ya tienes una instancia de DatosCliente (ajusta según tu
            // implementación)
            DatosBasicos basico = new DatosBasicos("mariana ortega", "manan@hotmail.com", "19-06-2004", "colombiana",
                    "manana2515", "seneca15", "cliente");
            DatosLicencia licencia = new DatosLicencia("colombia", 53153213, "18-12-2024");
            DatosCliente cliente = new DatosCliente(basico, licencia, "PayPal", 658741365, "07/29", 985, "jaja39");

            Logueado logueadoPanel = new Logueado(sistema, cliente);

            frame.getContentPane().add(logueadoPanel);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
