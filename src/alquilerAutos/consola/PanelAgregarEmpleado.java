package alquilerAutos.consola;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import alquilerAutos.sistema.SistemaAlquilerAutos;

public class PanelAgregarEmpleado extends JPanel{
	SistemaAlquilerAutos sistema;	

	public PanelAgregarEmpleado(SistemaAlquilerAutos sistema) {
		this.sistema = sistema;
		setLayout(new GridLayout(14, 1));
	
		JLabel lblNombre = new JLabel("Ingrese Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField nombreField = new JTextField(10);
		
		JLabel lblDatoContacto = new JLabel("Ingrese dato de contacto");
		lblDatoContacto.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField datoContactoField = new JTextField(30);
		
		JLabel lblFechaNacimiento = new JLabel("Ingrese fecha de nacimiento");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField fechaNacimientoField = new JTextField(30);
		
		JLabel lblNacionalidad = new JLabel("Ingrese nacionalidad");
		lblNacionalidad.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField nacionalidadField = new JTextField(30);
		
		JLabel lblLogin = new JLabel("Ingrese login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField loginField = new JTextField(30);
		
		JLabel lblContraseña = new JLabel("Ingrese contraseña");
		lblContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField ContraseñaField = new JTextField(30);
		
		
		JButton aceptarButton = new JButton("Aceptar");
		
		JLabel lblSpace = new JLabel("");
		
		add(lblNombre);
		add(nombreField);
		add(lblDatoContacto);
		add(datoContactoField);
		add(lblFechaNacimiento);
		add(fechaNacimientoField);
		add(lblNacionalidad);
		add(nacionalidadField);
		add(lblLogin);
		add(loginField);
		add(lblContraseña);
		add(ContraseñaField);
		
		add(lblSpace);
		add(aceptarButton);
		
		JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String contacto = datoContactoField.getText();
                String nacimiento = fechaNacimientoField.getText();
                String nacionalidad = nacionalidadField.getText();
                String login = loginField.getText();
                String contraseña = ContraseñaField.getText();
                
                boolean empleadoAgregado = sistema.nuevoEmpleado(nombre, contacto, nacimiento, nacionalidad, login, contraseña);

                // Mostrar una ventana emergente con el resultado
                if (empleadoAgregado) {
                    JOptionPane.showMessageDialog(null, "Empleado agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al agregar empleado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

	}	
	
}