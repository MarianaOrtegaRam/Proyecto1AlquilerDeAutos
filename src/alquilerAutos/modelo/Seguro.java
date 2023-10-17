package alquilerAutos.modelo;

public class Seguro {
	private String nombreSeguro;
	private int precio;
	private String beneficios;
	
	public Seguro(String nombreSeguro, int precio, String beneficios) {
		
		this.nombreSeguro = nombreSeguro;
		this.precio = precio;
		this.beneficios = beneficios;
	}

	public String getNombreSeguro() {
		return nombreSeguro;
	}

	public int getPrecio() {
		return precio;
	}

	public String getBeneficios() {
		return beneficios;
	}

}
