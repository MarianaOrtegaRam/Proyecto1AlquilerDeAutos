package alquilerAutos.modelo;

import java.util.Scanner;

public class Empleado {
	private DatosBasicos datosEmpleado;
	
	public String entregarAuto(){
		Scanner scanner = new Scanner(System.in); 
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("\nIngrese la placa del vehiculo a entregar: ");
		String placaAuto = scanner.nextLine();
		return placaAuto;

	}

	public String recibirAuto(){
		Scanner scanner = new Scanner(System.in); 
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("\nIngrese la placa del vehiculo a recibir: ");
		String placaAuto = scanner.nextLine();
		return placaAuto;
	}
}
