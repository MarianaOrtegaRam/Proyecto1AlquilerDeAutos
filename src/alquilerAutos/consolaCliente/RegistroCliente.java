package alquilerAutos.consolaCliente;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import alquilerAutos.modelo.DatosCliente;
import alquilerAutos.sistema.SistemaAlquilerAutos;

public class RegistroCliente extends JPanel {

    final static String PANEL_LOGIN = "registr";
    final static String PANEL_CONTENIDO = "contenido";
    private DatosCliente elCliente;

    private static JPanel cards;
    SistemaAlquilerAutos sistema;
    private JComboBox<String> metodos;
    JTextField nombreField;
    JTextField paisField;
    JTextField datoContactoField;
    JTextField numeroField;
    JTextField fechaNacimientoField;
    JTextField fechaVencimientoField;
    JTextField nacionalidadField;
    JTextField loginField;
    JTextField contraseñaField;
    JTextField txtNumTarj;
    JTextField txtFechaVenT;
    JTextField txtCodSeg;
    JTextField txtPass;

    public RegistroCliente(SistemaAlquilerAutos sistema) {
        this.sistema = sistema;
        JPanel estePanel = new JPanel();
        estePanel.setLayout(new GridLayout(14, 3));

        JLabel datosBasicos = new JLabel("¡Datos Basicos!");
        datosBasicos.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel datosLicencia = new JLabel("¡Datos Licencia!");
        datosLicencia.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel datosPago = new JLabel("¡Datos Metodo de Pago!");
        datosPago.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNombre = new JLabel("Ingrese Nombre");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblPais = new JLabel("Ingrese pais");
        lblPais.setHorizontalAlignment(SwingConstants.CENTER);

        nombreField = new JTextField(10);
        paisField = new JTextField(20);

        JLabel lblDatoContacto = new JLabel("Ingrese su correo");
        lblDatoContacto.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblNumero = new JLabel("Ingrese Numero");
        lblNumero.setHorizontalAlignment(SwingConstants.CENTER);

        datoContactoField = new JTextField(30);
        numeroField = new JTextField(30);

        JLabel lblFechaNacimiento = new JLabel("Ingrese fecha de nacimiento (DD-MM-AA)");
        lblFechaNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblFechaVencimiento = new JLabel("Ingrese fecha de vencimiento (MM-AA)");
        lblFechaVencimiento.setHorizontalAlignment(SwingConstants.CENTER);

        fechaNacimientoField = new JTextField(30);
        fechaVencimientoField = new JTextField(30);

        JLabel lblNacionalidad = new JLabel("Ingrese nacionalidad");
        lblNacionalidad.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblespacio1 = new JLabel("");
        JLabel lblespacio2 = new JLabel("");

        nacionalidadField = new JTextField(30);

        JLabel lblLogin = new JLabel("Ingrese login");
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblespacio3 = new JLabel("");
        JLabel lblespacio4 = new JLabel("");

        loginField = new JTextField(30);

        JLabel lblContraseña = new JLabel("Ingrese contraseña");
        lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblespacio5 = new JLabel("");
        JLabel lblespacio6 = new JLabel("");
        JLabel lblespacio7 = new JLabel("");

        contraseñaField = new JTextField(30);

        JLabel lblMetodo = new JLabel("Ingrese Metodo de Pago");
        lblMetodo.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblNumTarj = new JLabel("Ingrese el num de su tarjeta");
        lblNumTarj.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblFechaVenTarj = new JLabel("Ingrese Fecha Vencimiento tarjeta (MM/DD)");
        lblFechaVenTarj.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblCodSeg = new JLabel("Ingrese el codigo de seguridad (###)");
        lblCodSeg.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblPasswordTarj = new JLabel("Ingrese su clave virtual");
        lblPasswordTarj.setHorizontalAlignment(SwingConstants.CENTER);

        String[] met = { "PayPal", "PayU", "Stripe" };
        metodos = new JComboBox<>(met);

        txtNumTarj = new JTextField(30);
        txtFechaVenT = new JTextField(20);
        txtCodSeg = new JTextField(10);
        txtPass = new JTextField(30);
        JLabel lblespacio8 = new JLabel("");

        JButton aceptarButton = new JButton("Aceptar");

        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Datos Basicos
                String nombre = nombreField.getText();
                String contacto = datoContactoField.getText();
                String nacimiento = fechaNacimientoField.getText();
                String nacionalidad = nacionalidadField.getText();
                String login = loginField.getText();
                String contraseña = contraseñaField.getText();
                // Datos Licencia
                String paisLicencia = paisField.getText();
                String numeroLicencia = numeroField.getText();
                String fechaVencimiento = fechaVencimientoField.getText();
                // Datos Pago
                String metodoPago = (String) metodos.getSelectedItem();
                int numeroDeTarjeta = Integer.parseInt(txtNumTarj.getText());
                String fechaVT = txtFechaVenT.getText();
                int codigoSeg = Integer.parseInt(txtCodSeg.getText());
                String passwrd = txtPass.getText();

                boolean nuevoCliente = sistema.nuevoCliente(nombre, contacto, nacimiento, nacionalidad, login,
                        contraseña,
                        paisLicencia, numeroLicencia, fechaVencimiento, metodoPago, numeroDeTarjeta, fechaVT,
                        codigoSeg, passwrd);

                if (nuevoCliente) {
                    JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente", "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    elCliente = sistema.getDatosCliente(login);
                    CardLayout cardLayout = (CardLayout) cards.getLayout();
                    cardLayout.show(cards, PANEL_CONTENIDO);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear el cliente", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });
        estePanel.add(datosBasicos);
        estePanel.add(datosLicencia);
        estePanel.add(datosPago);

        estePanel.add(lblNombre);
        estePanel.add(lblPais);
        estePanel.add(lblMetodo);

        estePanel.add(nombreField);
        estePanel.add(paisField);
        estePanel.add(metodos);

        estePanel.add(lblDatoContacto);
        estePanel.add(lblNumero);
        estePanel.add(lblNumTarj);

        estePanel.add(datoContactoField);
        estePanel.add(numeroField);
        estePanel.add(txtNumTarj);

        estePanel.add(lblFechaNacimiento);
        estePanel.add(lblFechaVencimiento);
        estePanel.add(lblFechaVenTarj);

        estePanel.add(fechaNacimientoField);
        estePanel.add(fechaVencimientoField);
        estePanel.add(txtFechaVenT);

        estePanel.add(lblNacionalidad);
        estePanel.add(lblespacio1);
        estePanel.add(lblCodSeg);

        estePanel.add(nacionalidadField);
        estePanel.add(lblespacio2);
        estePanel.add(txtCodSeg);

        estePanel.add(lblLogin);
        estePanel.add(lblespacio3);
        estePanel.add(lblPasswordTarj);

        estePanel.add(loginField);
        estePanel.add(lblespacio4);
        estePanel.add(txtPass);

        estePanel.add(lblContraseña);
        estePanel.add(lblespacio5);
        estePanel.add(lblespacio8);

        estePanel.add(contraseñaField);
        estePanel.add(lblespacio6);
        estePanel.add(lblespacio7);
        estePanel.add(aceptarButton);

        Logueado content = new Logueado(sistema, elCliente);
        cards = new JPanel(new CardLayout());

        cards.add(estePanel, PANEL_LOGIN);
        cards.add(content, PANEL_CONTENIDO);
        add(cards, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        // Crear una instancia del sistema
        SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();

        // Crear una instancia del panel y pasarle el sistema
        RegistroCliente panel;
        panel = new RegistroCliente(sistema);
        JFrame frame = new JFrame("Registro de Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);

        // Configurar el tamaño del marco
        frame.setSize(700, 800);

        // Hacer visible el marco
        frame.setVisible(true);

        // Crear un marco (frame) y agregar el panel

    }

}
