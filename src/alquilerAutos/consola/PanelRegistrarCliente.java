package alquilerAutos.consola;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import alquilerAutos.sistema.SistemaAlquilerAutos;

public class PanelRegistrarCliente extends JPanel{
	SistemaAlquilerAutos sistema;	

	public PanelRegistrarCliente(SistemaAlquilerAutos sistema) {
		this.sistema = sistema;
		 setLayout(new GridLayout(14, 2));
		 
		JLabel datosBasicos = new JLabel("¡Datos Basicos!");
		datosBasicos.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel datosLicencia = new JLabel("¡Datos Licencia!");
		datosLicencia.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNombre = new JLabel("Ingrese Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblPais = new JLabel("Ingrese pais");
		lblPais.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextField nombreField = new JTextField(10);
		JTextField paisField = new JTextField(20);
		
		JLabel lblDatoContacto = new JLabel("Ingrese dato de contacto");
		lblDatoContacto.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblNumero = new JLabel("Ingrese Numero");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextField datoContactoField = new JTextField(30);
		JTextField numeroField = new JTextField(30);
		
		JLabel lblFechaNacimiento = new JLabel("Ingrese fecha de nacimiento (DD-MM-AA)");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblFechaVencimiento = new JLabel("Ingrese fecha de vencimiento (MM-AA)");
		lblFechaVencimiento.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextField fechaNacimientoField = new JTextField(30);
		JTextField fechaVencimientoField = new JTextField(30);
		
		JLabel lblNacionalidad = new JLabel("Ingrese nacionalidad");
		lblNacionalidad.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblespacio1 = new JLabel("");
		JLabel lblespacio2 = new JLabel("");
		
		JTextField nacionalidadField = new JTextField(30);
		
		JLabel lblLogin = new JLabel("Ingrese login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblespacio3 = new JLabel("");
		JLabel lblespacio4 = new JLabel("");
		
		JTextField loginField = new JTextField(30);
		
		JLabel lblContraseña = new JLabel("Ingrese contraseña");
		lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblespacio5 = new JLabel("");
		JLabel lblespacio6 = new JLabel("");
		JLabel lblespacio7 = new JLabel("");
		
		JTextField contraseñaField = new JTextField(30);
		
		
		JButton aceptarButton = new JButton("Aceptar");
		
		
		add(datosBasicos);
		add(datosLicencia);
		
		add(lblNombre);
		add(lblPais);
		
		add(nombreField);
		add(paisField);
		
		add(lblDatoContacto);
		add(lblNumero);
		
		add(datoContactoField);
		add(numeroField);
		
		add(lblFechaNacimiento);
		add(lblFechaVencimiento);
		
		add(fechaNacimientoField);
		add(fechaVencimientoField);
		
		add(lblNacionalidad);
		add(lblespacio1);
		
		add(nacionalidadField);
		add(lblespacio2);
		
		add(lblLogin);
		add(lblespacio3);
		
		add(loginField);
		add(lblespacio4);
		
		add(lblContraseña);
		add(lblespacio5);
		
		add(contraseñaField);
		add(lblespacio6);
		add(lblespacio7);
		
		add(aceptarButton);
	

		
		JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//Datos Basicos
                String nombre = nombreField.getText();
                String contacto = datoContactoField.getText();
                String nacimiento = fechaNacimientoField.getText();
                String nacionalidad = nacionalidadField.getText();
                String login = loginField.getText();
                String contraseña = contraseñaField.getText();
                //Datos Licencia
                String paisLicencia = paisField.getText();
                String numeroLicencia = numeroField.getText();
                String fechaVencimiento = fechaVencimientoField.getText();
                
                boolean nuevoCliente = sistema.nuevoCliente(nombre, contacto, nacimiento, nacionalidad, login, contraseña,
                															paisLicencia, numeroLicencia, fechaVencimiento);

                // Mostrar una ventana emergente con el resultado
                if (nuevoCliente) {
                    JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear el cliente", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

	}
	
	 public static void main(String[] args) {
	        // Crear una instancia del sistema
	        SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();

	        // Crear una instancia del panel y pasarle el sistema
	        PanelRegistrarCliente panel = new PanelRegistrarCliente(sistema);

	        // Crear un marco (frame) y agregar el panel
	        JFrame frame = new JFrame("Registro de Cliente");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().add(panel);
	        
	        // Configurar el tamaño del marco
	        frame.setSize(700, 800);

	        // Hacer visible el marco
	        frame.setVisible(true);
	    }
	
}

