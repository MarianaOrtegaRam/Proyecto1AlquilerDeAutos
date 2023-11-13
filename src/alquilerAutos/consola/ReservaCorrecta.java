package alquilerAutos.consola;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ReservaCorrecta extends JFrame {

		
		public ReservaCorrecta(ArrayList<String> datosReserva)
		{
			
			
			setTitle("Reserva Exitosa");
			GridLayout layout = new GridLayout(10,2);
			setLayout(layout);
			String sedeRecoger_ = datosReserva.get(0);
    		String sedeEntregar_ = datosReserva.get(1);
    		String fechaRecoger_  = datosReserva.get(2);
    		String rango_ = datosReserva.get(3);
    		String fechaEntrega_  = datosReserva.get(4);
    		String seguro_ = datosReserva.get(5);
    		String idReserva_ = datosReserva.get(6);
    		String categoria_ = datosReserva.get(7);
    		String loginCliente_ =datosReserva.get(8);
    		String placa_ = datosReserva.get(9);
    		String precio_ = datosReserva.get(10);
			
			
			JLabel lblsedeRecoger = new JLabel("SedeRecoger:");
			JTextField txtsedeRecoger = new JTextField();
			txtsedeRecoger.setText(sedeRecoger_);
			
			
			txtsedeRecoger.setEditable(false);
			
			JLabel lblsedeEntregar = new JLabel("SedeEntregar:");
			JTextField txtsedeEntregar = new JTextField();
			txtsedeEntregar.setText(sedeEntregar_);
			txtsedeEntregar.setEditable(false);
			
			JLabel lblFechaRecoger = new JLabel("FechaRecoger:");
			JTextField txtFechaRecoger = new JTextField();
			txtFechaRecoger.setText(fechaRecoger_);
			txtFechaRecoger.setEditable(false);
			
			JLabel lblFechaEntregar = new JLabel("FechaEntregar:");
			JTextField txtFechaEntregar = new JTextField();
			txtFechaEntregar.setText(fechaEntrega_);
			txtFechaEntregar.setEditable(false);
			
			JLabel lblRangoHoras = new JLabel("RangoHoras:");
			JTextField txtRangoHoras = new JTextField();
			txtRangoHoras.setText(rango_);
			txtRangoHoras.setEditable(false);
			
			JLabel lblSeguro = new JLabel("Seguro:");
			JTextField txtSeguro = new JTextField();
			txtSeguro.setText(seguro_);
			txtSeguro.setEditable(false);
			
			JLabel lblIDreserva = new JLabel("IDreserva:");
			JTextField txtIDreserva = new JTextField();
			txtIDreserva.setText(idReserva_);
			txtIDreserva.setEditable(false);

			JLabel lblCategoria = new JLabel("Categoria:");
			JTextField txtCategoria = new JTextField();
			txtCategoria.setText(categoria_);
			txtCategoria.setEditable(false);
			
			JLabel lblPlacaVehiculo = new JLabel("PlacaVehiculo:");
			JTextField txtPlacaVehiculo = new JTextField();
			txtPlacaVehiculo.setText(placa_);
			txtPlacaVehiculo.setEditable(false);
			
			JLabel lblPrecioFinal = new JLabel("PrecioFinal:");
			JTextField txtPrecioFinal = new JTextField();
			txtPrecioFinal.setText(precio_);
			txtPlacaVehiculo.setEditable(false);
			
			this.add(lblsedeRecoger);
			this.add(txtsedeRecoger);
			this.add(lblsedeEntregar);
			this.add(txtsedeEntregar);
			this.add(lblFechaRecoger);
			this.add(txtFechaRecoger);
			this.add(lblRangoHoras);
			this.add(txtRangoHoras);
			this.add(lblFechaEntregar);
			this.add(txtFechaEntregar);
			this.add(lblSeguro);
			this.add(txtSeguro);
			this.add(lblCategoria);
			this.add(txtCategoria);
			this.add(lblPlacaVehiculo);
			this.add(txtPlacaVehiculo);
			this.add(lblPrecioFinal);
			this.add(txtPrecioFinal);
			this.add(lblIDreserva);
			this.add(txtIDreserva);
			
			setLocationRelativeTo(null);
	        setResizable(true);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setSize(500,500);
	        setVisible(true);
			
		
}
		public static void main(String[] args) {
	        // Supongamos que tienes una lista de datos de reserva
	        ArrayList<String> datosReserva = new ArrayList<>();
	        datosReserva.add("SedeRecoger");
	        datosReserva.add("SedeEntregar");
	        datosReserva.add("FechaRecoger");
	        datosReserva.add("RangoHoras");
	        datosReserva.add("FechaEntregar");
	        datosReserva.add("Seguro");
	        datosReserva.add("IDreserva");
	        datosReserva.add("Categoria");
	        datosReserva.add("LoginCliente");
	        datosReserva.add("PlacaVehiculo");
	        datosReserva.add("PrecioFinal");

	        // Crear una instancia de la ventana ReservaCorrecta y mostrarla
	        ReservaCorrecta ventanaReserva = new ReservaCorrecta(datosReserva);
	        ventanaReserva.setSize(400, 400); // Establecer un tamaño adecuado
	    }
}
