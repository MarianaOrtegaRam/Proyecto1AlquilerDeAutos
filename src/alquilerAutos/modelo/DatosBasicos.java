package alquilerAutos.modelo;

import java.io.File;

public class DatosBasicos {
	private String nombre;
	private String datoContacto;
	private String fechaNacimiento;
	private String nacionalidad;
	private String login;
	private String contraseña;
	private String tipoUsuario;
	private File imagen;
	
	public DatosBasicos(String nombre, String datoContacto, String fechaNacimiento, String nacionalidad, String login,
			String contraseña, String tipoUsuario, File imagen) {
	
		this.nombre = nombre;
		this.datoContacto = datoContacto;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.login = login;
		this.contraseña = contraseña;
		this.tipoUsuario = tipoUsuario;
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public String getLogin() {
		return login;
	}

	public String getContraseña() {
		return contraseña;
	}


	
	
	
	

}
