package alquilerAutos.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
	
public class Sistema {
	

public static void main(String[] args) throws FileNotFoundException, IOException {
	
	Sistema consola = new Sistema();
	consola.ejecutarOpcion();


}

public void ejecutarOpcion() throws FileNotFoundException, IOException {
	{
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println("\nSistema Alquiler de Vehiculos");
				System.out.println("\n1. Registrar cliente");
				System.out.println("2. Registrar empleado");
				System.out.println("2. Registrar vehiculo");
				System.out.println("4. Salir de la aplicacion");
				
				int opcionSeleccionada = Integer.parseInt(input("\nPor favor seleccione una opcion"));
				
				
				if (opcionSeleccionada == 1) {
					Scanner scanner = new Scanner(System.in); 
					System.out.println("----------------------------------------------------------------------------------------");
					System.out.println("\nIngrese nombre: ");
					String nombreCliente = scanner.nextLine();
					System.out.println("\nIngrese cedula: ");
					String cedulaCliente = scanner.nextLine();
					System.out.println("\nIngrese fecha de nacimiento(DD-MM-AA): ");
					String fechaNacimientoCliente = scanner.nextLine();
					System.out.println("\nIngrese Nacionalidad: ");
					String nacionalidadCliente = scanner.nextLine();
					agregarCliente(nombreCliente+";"+cedulaCliente+";"+fechaNacimientoCliente+";"+nacionalidadCliente);
					
					System.out.println("\nCliente registrado!");
				}
				
				if (opcionSeleccionada == 2) {
					Scanner scanner = new Scanner(System.in); 
					System.out.println("----------------------------------------------------------------------------------------");
					System.out.println("\nIngrese login empleado: ");
					String loginEmpleado = scanner.nextLine();
					System.out.println("\nIngrese password empleado: ");
					String passwordEmpleado = scanner.nextLine();
					System.out.println("\nIngrese nombre empleado: ");
					String nombreEmpleado = scanner.nextLine();
					System.out.println("\nIngrese cedula empleado: ");
					String cedulaEmpleado = scanner.nextLine();
					System.out.println("\nIngrese fecha de nacimiento(DD-MM-AA) empleado: ");
					String fechaNacimientoEmpleado = scanner.nextLine();
					System.out.println("\nIngrese Nacionalidad empleado: ");
					String nacionalidadEmpleado = scanner.nextLine();
					agregarEmpleado(loginEmpleado+";"+passwordEmpleado+";"+nombreEmpleado+";"+cedulaEmpleado+";"+fechaNacimientoEmpleado+";"+nacionalidadEmpleado);
					
					System.out.println("\nEmpleado registrado!");
				}
			
			if (opcionSeleccionada == 3) {
				Scanner scanner = new Scanner(System.in); 
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println("\nIngrese placa: ");
				String placaVehiculo = scanner.nextLine();
				System.out.println("\nIngrese marca: ");
				String marcaVehiculo = scanner.nextLine();
				System.out.println("\nIngrese modelo: ");
				String modeloVehiculo = scanner.nextLine();
				System.out.println("\nIngrese color: ");
				String colorVehiculo = scanner.nextLine();
				System.out.println("\nIngrese tipo de transmision: ");
				String tipoTransmisionVehiculo = scanner.nextLine();
				System.out.println("\nIngrese categoria: ");
				String categoriaVehiculo = scanner.nextLine();
				agregarVehiculo(placaVehiculo+";"+marcaVehiculo+";"+modeloVehiculo+";"+colorVehiculo+";"+tipoTransmisionVehiculo+";"+categoriaVehiculo);
				
				System.out.println("\nVehiculo añadido!");
			}
				else if (opcionSeleccionada == 4)
				{
					System.out.println("Saliendo de la aplicacion ...");
					continuar = false;
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}
}

public void agregarCliente(String texto){
	FileWriter filewriter;
	try {
		filewriter = new FileWriter("./inventario/clientes.txt",true);
		filewriter.write(texto.toLowerCase()+"\n");
		filewriter.close();
	}catch (IOException e) {
		e.printStackTrace();
	}
}

public void agregarEmpleado(String texto){
	FileWriter filewriter;
	try {
		filewriter = new FileWriter("./inventario/empleados.txt",true);
		filewriter.write(texto.toLowerCase()+"\n");
		filewriter.close();
	}catch (IOException e) {
		e.printStackTrace();
	}
}

public void agregarVehiculo(String texto){
	FileWriter filewriter;
	try {
		filewriter = new FileWriter("./inventario/vehiculos.txt",true);
		filewriter.write(texto.toLowerCase()+"\n");
		filewriter.close();
	}catch (IOException e) {
		e.printStackTrace();
	}
}

public String input(String mensaje)
{
	try
	{
		System.out.print(mensaje + ": ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}
	catch (IOException e)
	{
		System.out.println("Error leyendo de la consola");
		e.printStackTrace();
	}
	return null;
}

}

