package alquilerAutos.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import alquilerAutos.modelo.Administrador;
import alquilerAutos.modelo.InfoEmpleado;
import alquilerAutos.modelo.Vehiculo;
import alquilerAutos.sistema.SistemaAlquilerAutos;

	
public class Interfaz {
	

public static void main(String[] args) throws FileNotFoundException, IOException {
	
	
	
	Interfaz consola = new Interfaz();
	consola.ejecutarOpcion();


}

public void ejecutarOpcion() throws FileNotFoundException, IOException {
	{
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();
				sistema.cargarInformacionVehiculos();
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println("\nBienvenido al Sistema Alquiler de Vehiculos");
				System.out.println("\n1. Iniciar sesion como cliente");
				System.out.println("2. Iniciar sesion como empleado");
				System.out.println("3. Iniciar sesion como administrador");
				System.out.println("4. Registrarse como nuevo cliente");
				System.out.println("5. Salir de la aplicacion");
				
				int opcionSeleccionada = Integer.parseInt(input("\nPor favor seleccione una opcion"));
				
				
				if (opcionSeleccionada == 1) {
					Scanner scanner = new Scanner(System.in); 
					System.out.println("----------------------------------------------------------------------------------------");
					System.out.println("\nLogin: ");
					String loginCliente = scanner.nextLine();
					System.out.println("\nContraseña: ");
					String contraseñaCliente = scanner.nextLine();
				}
				
				if (opcionSeleccionada == 2) {
					Scanner scanner = new Scanner(System.in); 
					System.out.println("----------------------------------------------------------------------------------------");
					System.out.println("\nLogin: ");
					String loginCliente = scanner.nextLine();
					System.out.println("\nContraseña: ");
					String contraseñaCliente = scanner.nextLine();
				}
			
			    if (opcionSeleccionada == 3) {
			    	Scanner scanner = new Scanner(System.in); 
			    	Administrador administrador = new Administrador();
			    	System.out.println("----------------------------------------------------------------------------------------");
			    	System.out.println("\nLogin: ");
					String loginAdmin = scanner.nextLine();
					System.out.println("\nContraseña: ");
					String contraseñaAdmin = scanner.nextLine();
					System.out.println();
					if(Administrador.getLogin().equals(loginAdmin) && Administrador.getContraseña().equals(contraseñaAdmin) ){
						System.out.println("----------------------------------------------------------------------------------------");
						System.out.println("\nBienvenido administrador " + Administrador.getLogin());
						System.out.println("\n1. Registrar empleado");	
						System.out.println("\n2. Registrar nuevo vehiculo");	
						int opcion = Integer.parseInt(input("\nPor favor seleccione una opcion"));
						
						if (opcion == 1) {
							administrador.registrarEmpleado();
					}else if(opcion == 2) {
						sistema.registrarNuevoVehiculo();
					}
			    }else{System.out.println("\nUsuario o contraseña incorrectos");}
		}
			
			    if (opcionSeleccionada == 4) {
			    	Scanner scanner = new Scanner(System.in); 
			    	System.out.println("----------------------------------------------------------------------------------------");
			    	ejecutarRegistrarCliente();
				
			    }
					else if (opcionSeleccionada == 5)
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

private void ejecutarRegistrarCliente() {
	SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();
	sistema.nuevoCliente();
	
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

