package alquilerAutos.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InfoEmpleado {
	public DatosBasicos crearEmpleado(String nombreEmpleado, String datoContactoEmpleado,
			String fechaNacimientoEmpleado, String nacionalidadEmplado,
			String loginEmpleado, String contraseñaEmpleado) {

		guardarEmpleado(nombreEmpleado + ";" + datoContactoEmpleado + ";" + fechaNacimientoEmpleado + ";"
				+ nacionalidadEmplado + ";" + loginEmpleado + ";" + contraseñaEmpleado);

		DatosBasicos datosBasicosEmpleado = new DatosBasicos(nombreEmpleado, datoContactoEmpleado,
				fechaNacimientoEmpleado, nacionalidadEmplado, loginEmpleado, contraseñaEmpleado, "empleado");

		System.out.println("\nSe ha registrado el empleado en el sistema!\n");

		return datosBasicosEmpleado;
	}

	public void guardarEmpleado(String texto) {
		StringBuilder contenidoAntiguo = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader("./inventario/empleados.txt"))) {
		    String linea = br.readLine();
		    while (linea != null) {
		        contenidoAntiguo.append(linea).append("\n");
		        linea = br.readLine();
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}

		String contenidoNuevo = contenidoAntiguo.toString() + texto.toLowerCase();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("./inventario/empleados.txt"))) {
		    bw.write(contenidoNuevo);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
