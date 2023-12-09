package alquilerAutos.consolaCliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import alquilerAutos.modelo.DatosBasicos;
import alquilerAutos.modelo.DatosCliente;
import alquilerAutos.modelo.DatosLicencia;
import alquilerAutos.sistema.Reserva;
import alquilerAutos.sistema.SistemaAlquilerAutos;

public class RealizarReserva extends JPanel {
    private JComboBox<String> sedeComboBoxentrega;
    private JComboBox<String> sedeComboBoxrecoger;
    private JComboBox<String> tipoVehiculoComboBox;
    private JTextField fechaRecogerField;
    private JTextField rangoHoraEntregaField;
    private JTextField fechaEntregaField;
    private JComboBox<String> seguros;
    private JComboBox<String> categorias;
    private SistemaAlquilerAutos sistema;

    public RealizarReserva(SistemaAlquilerAutos sistema, DatosCliente elCliente) {
        setBackground(Color.BLUE);
        this.sistema = sistema;
        // String loginCliente = elCliente.getDatosBasicos().getLogin();
        setLayout(new GridLayout(10, 2));
        JLabel l0 = new JLabel("Ingrese su login");
        JLabel l1 = new JLabel("Tipo Vehiculo:");
        JLabel l2 = new JLabel("Sede Recoger:");
        JLabel l3 = new JLabel("Sede Entregar:");
        JLabel l4 = new JLabel("Fecha Recoger (DD-MM-AAAA):");
        JLabel l5 = new JLabel("Rango Hora Entrega (HH-HH):");
        JLabel l6 = new JLabel("Fecha Entrega(DD-MM-AAAA):");
        JLabel l7 = new JLabel("Seguro:");
        JLabel l8 = new JLabel("Categoria ('na' si no es automovil):");
        l0.setForeground(Color.WHITE);
        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);
        l4.setForeground(Color.WHITE);
        l5.setForeground(Color.WHITE);
        l6.setForeground(Color.WHITE);
        l7.setForeground(Color.WHITE);
        l8.setForeground(Color.WHITE);
        add(l0);
        JTextField txtLogin = new JTextField();
        add(txtLogin);
        add(l1);
        String[] tiposV = { "automovil", "moto", "motocicleta deportiva", "bicicleta", "bicicleta electrica",
                "patineta electrica" };
        tipoVehiculoComboBox = new JComboBox<>(tiposV);
        add(tipoVehiculoComboBox);

        add(l2);
        String[] sedesR = { "Oficina Central", "Aeropuerto Internacional", "Zona Costera" };
        sedeComboBoxrecoger = new JComboBox<>(sedesR);
        add(sedeComboBoxrecoger);

        add(l3);
        String[] sedesE = { "Oficina Central", "Aeropuerto Internacional", "Zona Costera" };
        sedeComboBoxentrega = new JComboBox<>(sedesE);
        add(sedeComboBoxentrega);

        add(l4);
        fechaRecogerField = new JTextField();
        add(fechaRecogerField);

        add(l5);
        rangoHoraEntregaField = new JTextField();
        add(rangoHoraEntregaField);

        add(l6);
        fechaEntregaField = new JTextField();
        add(fechaEntregaField);

        add(l7);
        String[] segurosDispo = { "Ninguno", "Proteccion contra Robo", "Accidente Personal",
                "Cobertura de neumaticos y parabrisas", "Responsabilidad Civil" };
        seguros = new JComboBox<>(segurosDispo);
        add(seguros);

        add(l8);
        String[] categoriasDispo = { "na", "economico", "intermedio", "transportemultiple",
                "vehiculolujo" };
        categorias = new JComboBox<>(categoriasDispo);
        add(categorias);

        JButton reservarButton = new JButton("Reservar");
        reservarButton.setBackground(Color.MAGENTA);
        reservarButton.setForeground(Color.WHITE);
        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    realizarReserva(txtLogin.getText());
                } catch (Exception e1) {
                    String mensaje = e1.getMessage();
                    if (mensaje.equals("No colocaron Datos")) {
                        JOptionPane.showMessageDialog(null, "Error, faltaron llenar campos", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (mensaje.equals("Debe seleccionar una categoria")) {
                        JOptionPane.showMessageDialog(null, "Error, Debe selecionar una categoria para automovil",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        add(reservarButton);

    }

    public void realizarReserva(String login) throws Exception {
        String tipoVeh = (String) tipoVehiculoComboBox.getSelectedItem();
        String sedeRecoger = (String) sedeComboBoxrecoger.getSelectedItem();
        String sedeEntrega = (String) sedeComboBoxentrega.getSelectedItem();
        String fechaRecoger = fechaRecogerField.getText();
        String rangoHoraEntrega = rangoHoraEntregaField.getText();
        String fechaEntrega = fechaEntregaField.getText();
        String seguro = (String) seguros.getSelectedItem();
        String categoriaDeseada = (String) categorias.getSelectedItem();
        Reserva reserva;

        if (fechaRecoger.equals("") || fechaRecoger.equals("") || rangoHoraEntrega.equals("")) {
            throw new Exception("No colocaron Datos");
        }

        if (tipoVeh.equals("automovil")) {
            if (categoriaDeseada.equals("na")) {
                throw new Exception("Debe seleccionar una categoria");
            }
            reserva = sistema.crearReserva(tipoVeh, sedeRecoger, sedeEntrega, fechaRecoger, rangoHoraEntrega,
                    fechaEntrega,
                    seguro, categoriaDeseada, login);
        }

        else {
            reserva = sistema.crearReservaNoAuto(tipoVeh, sedeRecoger, sedeEntrega, fechaRecoger, rangoHoraEntrega,
                    fechaEntrega,
                    seguro, "na", login);
        }

        if (reserva != null) {
            ArrayList<String> datosReserva = sistema.getDatosReserva(reserva);
            new ReservaCorrecta(datosReserva);
            String id = Integer.toString(reserva.getIdReserva());
            JOptionPane.showMessageDialog(null, "Se ha cargado el precio a su metodo de Pago registrado en el sistema",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            sistema.crearReciboPDF(id, sedeRecoger, sedeEntrega, fechaRecoger, rangoHoraEntrega, fechaEntrega, seguro,
                    categoriaDeseada);

        } else {
            JOptionPane.showMessageDialog(null,
                    "Error al realizar reserva Problabemente no tenemos unidades disponibles de lo que desea",
                    "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    public static void main(String[] args) {
        // Crear una instancia del sistema
        SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();

        // Supongamos que ya tienes un objeto DatosCliente
        DatosBasicos basico = new DatosBasicos("mariana ortega", "manan@hotmail.com", "19-06-2004", "colombiana",
                "manana2515", "seneca15", "cliente");
        DatosLicencia licencia = new DatosLicencia("colombia", 53153213, "18-12-2024");
        DatosCliente cliente = new DatosCliente(basico, licencia, "PayPal", 658741365, "07/29", 985, "jaja39");

        // Crear una instancia de la interfaz gráfica para realizar la reserva
        JFrame frame = new JFrame("Realizar Reserva");
        RealizarReserva realizarReservaPanel = new RealizarReserva(sistema, cliente);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(realizarReservaPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
