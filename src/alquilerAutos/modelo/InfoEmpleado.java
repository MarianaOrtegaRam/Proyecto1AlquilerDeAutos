package alquilerAutos.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InfoEmpleado {
	public DatosBasicos crearEmpleado(){
		
		Scanner scanner = new Scanner(System.in); 
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("\t\t\tDatos Basicos Empleado\n");
		System.out.println("Ingrese su nombre: ");
		String nombreEmpleado = scanner.nextLine();
		System.out.println("\nIngrese dato de contacto: ");
		String datoContactoEmpleado = scanner.nextLine();
		System.out.println("\nIngrese fecha de nacimiento(DD-MM-AA): ");
		String fechaNacimientoEmpleado = scanner.nextLine();
		System.out.println("\nIngrese Nacionalidad: ");
		String nacionalidadEmplado = scanner.nextLine();
		System.out.println("\nIngrese login para registrarse: ");
		String loginEmpleado = scanner.nextLine();
		System.out.println("\nIngrese contraseña: ");
		String contraseñaEmpleado = scanner.nextLine();
		
		guardarEmpleado(nombreEmpleado+";"+datoContactoEmpleado+";"+fechaNacimientoEmpleado+";"+nacionalidadEmplado+";"+loginEmpleado+";"+contraseñaEmpleado);
		
		DatosBasicos datosBasicosEmpleado = new DatosBasicos(nombreEmpleado,datoContactoEmpleado,fechaNacimientoEmpleado,nacionalidadEmplado,loginEmpleado,contraseñaEmpleado,"empleado");
		
		System.out.println("\nSe ha registrado el empleado en el sistema!\n");
		
		return datosBasicosEmpleado;
	}
	
	public void guardarEmpleado(String texto) {
		FileWriter filewriter;
		try {
			filewriter = new FileWriter("./inventario/empleados.txt",true);
			filewriter.write(texto.toLowerCase()+"\n");
			filewriter.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
