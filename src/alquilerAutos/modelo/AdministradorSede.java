package alquilerAutos.modelo;

public class AdministradorSede {
	private String nombre;
	private int cedula;
	private String login;
	private String contraseña;

	public AdministradorSede(String nombre, int cedula, String login, String contraseña) {

		this.nombre = nombre;
		this.cedula = cedula;
		this.login = login;
		this.contraseña = contraseña;
	}

	public void cobrar30Alquiler() {

	}

	/*
	 * public float definirTarifa(String categoria, int precioDeseado) {
	 * return 0;
	 * }
	 */
}
