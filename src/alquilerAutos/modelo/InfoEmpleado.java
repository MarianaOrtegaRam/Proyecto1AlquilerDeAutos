package alquilerAutos.modelo;

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
		FileWriter filewriter;
		try {
			filewriter = new FileWriter("./inventario/empleados.txt", true);
			filewriter.write(texto.toLowerCase() + "\n");
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
