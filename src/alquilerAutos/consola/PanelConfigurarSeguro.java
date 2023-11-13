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

public class PanelConfigurarSeguro extends JPanel {

	SistemaAlquilerAutos sistema;	

	public PanelConfigurarSeguro(SistemaAlquilerAutos sistema) {
		this.sistema = sistema;
		setLayout(new GridLayout(8, 1));
	
		JLabel lblNombre = new JLabel("Ingrese Nombre del seguro");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField nombreField = new JTextField(10);
		
		JLabel lblPrecio = new JLabel("Ingrese precio del seguro");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField precioField = new JTextField(30);
		
		JLabel lblBeneficios = new JLabel("Ingrese beneficios del seguro");
		lblBeneficios.setHorizontalAlignment(SwingConstants.CENTER);
		JTextField beneficiosField = new JTextField(30);
		
		
		JButton aceptarButton = new JButton("Aceptar");
		
		JLabel lblSpace = new JLabel("");
		
		add(lblNombre);
		add(nombreField);
		add(lblPrecio);
		add(precioField);
		add(lblBeneficios);
		add(beneficiosField);
		
		add(lblSpace);
		add(aceptarButton);
		
		JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String precio = precioField.getText();
                String beneficios = beneficiosField.getText();
                
                boolean empleadoAgregado = sistema.nuevoSeguro(nombre, precio, beneficios);

                // Mostrar una ventana emergente con el resultado
                if (empleadoAgregado) {
                    JOptionPane.showMessageDialog(null, "Seguro agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al agregar seguro", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });

	}	
	
}
