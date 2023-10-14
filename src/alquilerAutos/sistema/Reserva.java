package alquilerAutos.sistema;

public class Reserva {
	private float idReserva;
	private String categoriaSeleccionada;
	private String sedeRecoger;
	private String fechaHoraRecoger;
	private String rangoHoraEntrega;
	private String fechaEntrega;
	private String seguro;
	private float precioFinal;
	
	public Reserva(float idReserva, String categoriaSeleccionada, String sedeRecoger, String fechaHoraRecoger,
			String rangoHoraEntrega, String fechaEntrega, String seguro, float precioFinal) {
	
		this.idReserva = idReserva;
		this.categoriaSeleccionada = categoriaSeleccionada;
		this.sedeRecoger = sedeRecoger;
		this.fechaHoraRecoger = fechaHoraRecoger;
		this.rangoHoraEntrega = rangoHoraEntrega;
		this.fechaEntrega = fechaEntrega;
		this.seguro = seguro;
		this.precioFinal = precioFinal;
	}
}
