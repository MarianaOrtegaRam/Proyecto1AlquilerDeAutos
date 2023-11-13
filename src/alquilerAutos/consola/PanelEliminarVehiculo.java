package alquilerAutos.consola;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import alquilerAutos.sistema.SistemaAlquilerAutos;

public class PanelEliminarVehiculo extends JPanel {
	SistemaAlquilerAutos sistema;	
	
	public PanelEliminarVehiculo(SistemaAlquilerAutos sistema) {
		this.sistema = sistema;
		setLayout(new GridLayout(8, 1));
	
		JLabel lblPlaca = new JLabel("Ingrese Placa del Vehiculo");
		lblPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField placaField = new JTextField(10);
		
		JButton aceptarButton = new JButton("Aceptar");
		
		JLabel lblSpace = new JLabel("");
		
		add(lblPlaca);
		add(placaField);
		
		add(lblSpace);
		add(aceptarButton);
		
		JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placa = placaField.getText();
                
                boolean eliminarVehiculo = sistema.eliminarVehiculo(placa);

                // Mostrar una ventana emergente con el resultado
                if (eliminarVehiculo) {
                    JOptionPane.showMessageDialog(null, "El vehiculo se ha eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error, placa incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

	}	
	
}