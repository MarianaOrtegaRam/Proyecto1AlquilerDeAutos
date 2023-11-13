package alquilerAutos.consola;

import java.awt.CardLayout;
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

public class PanelAgregarVehiculo extends JPanel {
	SistemaAlquilerAutos sistema;	
	
	public PanelAgregarVehiculo(SistemaAlquilerAutos sistema) {
		this.sistema = sistema;
		setLayout(new GridLayout(24, 1));
	
		JLabel lblPlaca = new JLabel("Ingrese la placa");
		lblPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField placaField = new JTextField(10);
		
		JLabel lblMarca = new JLabel("Ingrese la marca");
		lblMarca.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField marcaField = new JTextField(30);
		
		JLabel lblTamaño = new JLabel("Ingrese tamaño");
		lblTamaño.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField tamañoField = new JTextField(30);
		
		JLabel lblModelo = new JLabel("Ingrese modelo");
		lblModelo.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField modeloField = new JTextField(30);
		
		JLabel lblColor = new JLabel("Ingrese color");
		lblColor.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField colorField = new JTextField(30);
		
		JLabel lblCaja = new JLabel("Ingrese caja");
		lblCaja.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField cajaField = new JTextField(30);
		
		JLabel lblPrecio = new JLabel("Ingrese precio por dia");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField precioField = new JTextField(30);
		
		JLabel lblMaletas = new JLabel("Ingrese maletas");
		lblMaletas.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField maletasField = new JTextField(30);
		
		JLabel lblCapacidad = new JLabel("Ingrese capacidad vehiculo");
		lblCapacidad.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField capacidadField = new JTextField(30);
		
		JLabel lblCategoria = new JLabel("Ingrese categoria vehiculo");
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField categoriaField = new JTextField(30);
		
		JLabel lblSede = new JLabel("Ingrese sede vehiculo");
		lblSede.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField sedeField = new JTextField(30);
		
		
		JButton aceptarButton = new JButton("Aceptar");
		
		JLabel lblSpace = new JLabel("");
		
		add(lblPlaca);
		add(placaField);
		add(lblMarca);
		add(marcaField);
		add(lblTamaño);
		add(tamañoField);
		add(lblModelo);
		add(modeloField);
		add(lblColor);
		add(colorField);
		add(lblCaja);
		add(cajaField);
		add(lblPrecio);
		add(precioField);
		add(lblMaletas);
		add(maletasField);
		add(lblCapacidad);
		add(capacidadField);
		add(lblCategoria);
		add(categoriaField);
		add(lblSede);
		add(sedeField);
		add(lblSpace);
		add(aceptarButton);
		
		JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = placaField.getText();
                String marca = marcaField.getText();
                String tamaño = tamañoField.getText();
                String modelo = modeloField.getText();
                String color = colorField.getText();
                String caja = cajaField.getText();
                String precio = precioField.getText();
                String maletas = maletasField.getText();
                String capacidad = capacidadField.getText();
                String categoria = categoriaField.getText();
                String sede = sedeField.getText();
                
                boolean vehiculoAgregado = sistema.registrarNuevoVehiculo(placa, marca, tamaño, modelo, color, caja, precio, maletas, capacidad, categoria, sede);

                // Mostrar una ventana emergente con el resultado
                if (vehiculoAgregado) {
                    JOptionPane.showMessageDialog(null, "Vehiculo agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al agregar Vehiculo", "Error", JOptionPane.ERROR_MESSAGE);
                }            }
        });

	}	
	
}
