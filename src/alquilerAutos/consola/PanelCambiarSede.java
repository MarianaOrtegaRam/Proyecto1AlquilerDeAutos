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

public class PanelCambiarSede extends JPanel{
	SistemaAlquilerAutos sistema;
	
	public PanelCambiarSede(SistemaAlquilerAutos sistema) {
		this.sistema = sistema;
		setLayout(new GridLayout(14, 1));
	
		JLabel lblPlaca = new JLabel("Ingrese Placa del Vehiculo");
		lblPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField placaField = new JTextField(10);
		
		JLabel lblSede = new JLabel("Ingrese sede de destino");
		lblSede.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField sedeField = new JTextField(30);
		
		
		JButton aceptarButton = new JButton("Aceptar");
		
		JLabel lblSpace = new JLabel("");
		
		add(lblPlaca);
		add(placaField);
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
                String sede = sedeField.getText();
                
                boolean cambiarSede = sistema.cambiarVehiculoSede(placa, sede);

                // Mostrar una ventana emergente con el resultado
                if (cambiarSede) {
                    JOptionPane.showMessageDialog(null, "El vehiculo se ha cambiado de sede exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error, placa o sede incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

	}	
	
}
