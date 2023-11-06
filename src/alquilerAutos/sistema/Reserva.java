package alquilerAutos.sistema;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import alquilerAutos.modelo.Vehiculo;

public class Reserva {
	private int idReserva;
	private String categoriaSeleccionada;
	private String sedeRecoger;
	private String sedeEntrega;
	private String fechaHoraRecoger;
	private String rangoHoraEntrega;
	private String fechaEntrega;
	private String seguro;
	private int precioFinal;
	private Vehiculo vehiculo;

	public Reserva(String sedeRecoger, String sedeEntrega, String fechaHoraRecoger,
			String rangoHoraEntrega, String fechaEntrega, String seguro) {

		Random rand = new Random();
		this.idReserva = rand.nextInt(100);
		this.sedeRecoger = sedeRecoger;
		this.sedeEntrega = sedeEntrega;
		this.fechaHoraRecoger = fechaHoraRecoger;
		this.rangoHoraEntrega = rangoHoraEntrega;
		this.fechaEntrega = fechaEntrega;
		this.seguro = seguro;

	}

	public void setCategoriaSeleccionada(String categoria) {
		this.categoriaSeleccionada = categoria;
	}

	public void setPrecioFinal(int precioFinal) {
		this.precioFinal = precioFinal;
	}

	public void setSedeEntrega(String sedeEntrega) {
		this.sedeEntrega = sedeEntrega;
	}

	public void setRangoHoraEntrega(String rangoHoraEntrega) {
		this.rangoHoraEntrega = rangoHoraEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	////////////////////////////////////////////////////////
	public int getIdReserva() {
		return idReserva;
	}
	
	public String getSedeRecoger() {
		return sedeRecoger;
	}
	
	public String getSedeEntrega() {
		return sedeEntrega;
	}
	
	public String getFechaHoraRecoger() {
		return fechaHoraRecoger;
	}
	
	public String getRangoHoraEntrega() {
		return rangoHoraEntrega;
	}
	
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	
	public String getSeguro() {
		return seguro;
	}

	
	public void registrarReserva(Reserva reserva) {
		
		String sedeRecoger = reserva.getSedeRecoger();
		String sedeEntrega = reserva.getSedeEntrega();
		String fechaHoraRecoger = reserva.getFechaHoraRecoger();
		String rangoHoraEntrega = reserva.getRangoHoraEntrega();
		String fechaEntrega = reserva.getFechaEntrega();
		String seguro = reserva.getSeguro();
		
		agregarResreva(sedeRecoger + ";" + sedeEntrega + ";" + fechaHoraRecoger + ";"
				+ rangoHoraEntrega + ";" + fechaEntrega + ";" + seguro);
		
	}
	
	public void agregarResreva(String texto) {
		FileWriter filewriter;
		try {
			filewriter = new FileWriter("./inventario/reservas.txt", true);
			filewriter.write(texto.toLowerCase() + "\n");
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

}
