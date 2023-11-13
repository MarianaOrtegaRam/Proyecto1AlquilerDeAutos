package alquilerAutos.consola;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import alquilerAutos.modelo.DatosCliente;
import alquilerAutos.sistema.Reserva;
import alquilerAutos.sistema.SistemaAlquilerAutos;

public class PanelRealizarReserva extends JPanel {
    private JComboBox<String> sedeComboBoxentrega;
    private JComboBox<String> sedeComboBoxrecoger;
    private JTextField fechaRecogerField;
    private JTextField rangoHoraEntregaField;
    private JTextField fechaEntregaField;
    private JComboBox<String> seguros;
    private JComboBox<String> categorias;
    private SistemaAlquilerAutos sistema;

    public PanelRealizarReserva(SistemaAlquilerAutos sistema, DatosCliente elCliente) {
        this.sistema = sistema;
        String loginCliente = elCliente.getDatosBasicos().getLogin();
        setLayout(new GridLayout(8, 2));
        add(new JLabel("Sede Recoger:"));
        String[] sedesR = { "Oficina Central", "Aeropuerto Internacional", "Zona Costera" };
        sedeComboBoxrecoger = new JComboBox<>(sedesR);
        add(sedeComboBoxrecoger);

        add(new JLabel("Sede Entregar:"));
        String[] sedesE = { "Oficina Central", "Aeropuerto Internacional", "Zona Costera" };
        sedeComboBoxentrega = new JComboBox<>(sedesE);
        add(sedeComboBoxentrega);

        add(new JLabel("Fecha Recoger:"));
        fechaRecogerField = new JTextField();
        add(fechaRecogerField);

        add(new JLabel("Rango Hora Entrega:"));
        rangoHoraEntregaField = new JTextField();
        add(rangoHoraEntregaField);

        add(new JLabel("Fecha Entrega:"));
        fechaEntregaField = new JTextField();
        add(fechaEntregaField);

        add(new JLabel("Seguro:"));
        String[] segurosDispo = { "Ninguno", "Proteccion contra Robo", "Accidente Personal",
                "Cobertura de neumaticos y parabrisas", "Responsabilidad Civil" };
        seguros = new JComboBox<>(segurosDispo);
        add(seguros);

        add(new JLabel("Categoria:"));
        String[] categoriasDispo = { "economico", "intermedio", "transportemultiple",
                "vehiculolujo" };
        categorias = new JComboBox<>(categoriasDispo);
        add(categorias);

        JButton reservarButton = new JButton("Reservar");
        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarReserva(loginCliente);
            }
        });
        add(reservarButton);

    }

    public void realizarReserva(String login) {

        String sedeRecoger = (String) sedeComboBoxrecoger.getSelectedItem();
        String sedeEntrega = (String) sedeComboBoxentrega.getSelectedItem();
        String fechaRecoger = fechaRecogerField.getText();
        String rangoHoraEntrega = rangoHoraEntregaField.getText();
        String fechaEntrega = fechaEntregaField.getText();
        String seguro = (String) seguros.getSelectedItem();
        String categoriaDeseada = (String) categorias.getSelectedItem();
        Reserva reserva = sistema.crearReserva(sedeRecoger, sedeEntrega, fechaRecoger, rangoHoraEntrega, fechaEntrega,
                seguro, categoriaDeseada,login);
        if (reserva != null) {
        	ArrayList<String> datosReserva = sistema.getDatosReserva(reserva);
        	new ReservaCorrecta(datosReserva);	
        }
        else {
        	JFrame error = new JFrame("error en reserva");
        	JPanel mensajeError = new JPanel();
            mensajeError.add(new JLabel("Intente de nuevo!"));
            error.add(mensajeError);
            error.setLocationRelativeTo(null);
	        error.setResizable(true);
	        error.setVisible(true);
            
        }

    }

}
