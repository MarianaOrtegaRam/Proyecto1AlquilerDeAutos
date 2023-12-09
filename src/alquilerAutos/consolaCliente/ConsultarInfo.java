package alquilerAutos.consolaCliente;

import javax.swing.*;

import alquilerAutos.consola.PanelRegistrarCliente;
import alquilerAutos.modelo.Vehiculo;
import alquilerAutos.sistema.Reserva;
import alquilerAutos.sistema.SistemaAlquilerAutos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.*;

public class ConsultarInfo extends JPanel {

    private static JPanel cards;
    public SistemaAlquilerAutos sistema;
    final static String PANEL_PREGUNTA = "pregunta";
    final static String PANEL_RTA = "contenido";
    JComboBox<String> tipos;
    JComboBox<String> sedes;

    public ConsultarInfo(SistemaAlquilerAutos sistema) {
        this.sistema = sistema;
        JPanel pregunta = new JPanel();
        pregunta.setLayout(new GridLayout(5, 2));
        JLabel lblTipoVehiculo = new JLabel("Tipo Vehiculo");
        pregunta.add(lblTipoVehiculo);
        String[] tiposV = { "automovil", "moto", "motocicleta deportiva", "bicicleta", "bicicleta electrica",
                "patineta electrica" };
        tipos = new JComboBox<>(tiposV);
        pregunta.add(tipos);
        JLabel lblSede = new JLabel("Sede");
        pregunta.add(lblSede);
        String[] sedesE = { "Oficina Central", "Aeropuerto Internacional", "Zona Costera" };
        sedes = new JComboBox<>(sedesE);
        pregunta.add(sedes);
        JLabel lblFechaI = new JLabel("Fecha Inicial (DD-MM-AAAA)");
        pregunta.add(lblFechaI);
        JTextField txtFechaI = new JTextField(30);
        pregunta.add(txtFechaI);
        JLabel lblFechaF = new JLabel("Fecha Final (DD-MM-AAAA)");
        pregunta.add(lblFechaF);
        JTextField txtFechaF = new JTextField(30);
        pregunta.add(txtFechaF);

        JButton botonBuscar = new JButton("BUSCAR");
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tipoDeVehiculo = (String) tipos.getSelectedItem();
                String sede = (String) sedes.getSelectedItem();
                String fecha1 = txtFechaI.getText();
                String[] partes1 = fecha1.split("-");
                int dia1 = Integer.parseInt(partes1[0]);
                int mes1 = Integer.parseInt(partes1[1]);
                int anio1 = Integer.parseInt(partes1[2]);
                int dias1 = dia1 + (mes1 * 30) + (anio1 * 365);
                String fecha2 = txtFechaF.getText();
                String[] partes2 = fecha2.split("-");
                int dia2 = Integer.parseInt(partes2[0]);
                int mes2 = Integer.parseInt(partes2[1]);
                int anio2 = Integer.parseInt(partes2[2]);
                int dias2 = dia2 + (mes2 * 30) + (anio2 * 365);
                int contadorNoDispo = 0;
                Map<String, ArrayList<String>> sedes = sistema.getMapaSede(sede, tipoDeVehiculo);
                ArrayList<Reserva> reservas = sistema.getReservasTot();
                ArrayList<String> vehiculosEnSede = sedes.get(sede);
                for (String placa : vehiculosEnSede) {
                    for (Reserva unaReserva : reservas) {
                        String placaDeUnaReserva = unaReserva.getVehiculo().getPlaca();
                        if (placaDeUnaReserva.equals(placa)) {
                            String fechaInicioReserva = unaReserva.getFechaHoraRecoger();
                            String fechaFinalReserva = unaReserva.getFechaEntrega();
                            String[] partesInicio = fechaInicioReserva.split("-");
                            int diaRecoger = Integer.parseInt(partesInicio[0]);
                            int mesRecoger = Integer.parseInt(partesInicio[1]);
                            int anioRecoger = Integer.parseInt(partesInicio[2]);
                            int diasRecoger = diaRecoger + (mesRecoger * 30) + (anioRecoger * 365);

                            String[] partesFinal = fechaFinalReserva.split("-");
                            int diaEntregar = Integer.parseInt(partesFinal[0]);
                            int mesEntregar = Integer.parseInt(partesFinal[1]);
                            int anioEntregar = Integer.parseInt(partesFinal[2]);
                            int diasEntregar = diaEntregar + (mesEntregar * 30) + (anioEntregar * 365);

                            if ((dias1 > diasRecoger && dias1 < diasEntregar)
                                    || (dias2 > diasRecoger && dias2 < diasEntregar)) {
                                contadorNoDispo += 1;
                            }
                        }

                    }
                }
                int totalVehiculosSede = vehiculosEnSede.size();
                int vehiculosYaRespuesta = totalVehiculosSede - contadorNoDispo;
                String mensajeMostrar = "Hay un total de " + String.valueOf(vehiculosYaRespuesta)
                        + " vehiculos disponibles en esa fecha y sede";
                // JOptionPane.showMessageDialog(null,
                // "Hay un total de " + String.valueOf(vehiculosYaRespuesta)
                // + " vehiculos disponibles en esa fecha y sede",
                // "Éxito", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null,
                        mensajeMostrar,
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);

            }

        });
        pregunta.add(botonBuscar);
        add(pregunta);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Consultar Información");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            ConsultarInfo consultarInfo = new ConsultarInfo(new SistemaAlquilerAutos());

            frame.getContentPane().add(consultarInfo);
            frame.setVisible(true);
        });
    }
}
