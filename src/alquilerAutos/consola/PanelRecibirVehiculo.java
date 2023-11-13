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

public class PanelRecibirVehiculo extends JPanel {

	SistemaAlquilerAutos sistema;	

	public PanelRecibirVehiculo(SistemaAlquilerAutos sistema) {
		this.sistema = sistema;
		setLayout(new GridLayout(14, 1));
	
		JLabel lblPlaca = new JLabel("Ingrese la placa del vehiculo");
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
                
                boolean entregarVehiculo = sistema.recibirVehiculo(placa);

                // Mostrar una ventana emergente con el resultado
                if (entregarVehiculo) {
                    JOptionPane.showMessageDialog(null, "Se ha modificado la disponibilidad del vehiculo exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error, no se encontro el vehiculo en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

	}	
	
}

