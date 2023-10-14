package alquilerAutos.sistema;

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
	
	public Reserva(String categoriaSeleccionada, String sedeRecoger, String sedeEntrega, String fechaHoraRecoger,
			String rangoHoraEntrega, String fechaEntrega, String seguro, int precioFinal) {
	
		Random rand = new Random();
		this.idReserva = rand.nextInt(100);
		this.categoriaSeleccionada = categoriaSeleccionada;
		this.sedeRecoger = sedeRecoger;
		this.sedeEntrega = sedeEntrega;
		this.fechaHoraRecoger = fechaHoraRecoger;
		this.rangoHoraEntrega = rangoHoraEntrega;
		this.fechaEntrega = fechaEntrega;
		this.seguro = seguro;
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
	
}
