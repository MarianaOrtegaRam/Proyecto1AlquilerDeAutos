package alquilerAutos.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InfoCliente {
	
	public DatosCliente crearCliente() {
		DatosBasicos datosBasicos = datosBasicosCliente();
		DatosLicencia datosLicencia = datoslicenciaCliente();
		DatosCliente datosCliente = new DatosCliente(datosBasicos,datosLicencia);
		return datosCliente;
	}
	public DatosBasicos datosBasicosCliente(){
		Scanner scanner = new Scanner(System.in); 
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("\t\t\tDatos Basicos\n");
		System.out.println("Ingrese su nombre: ");
		String nombreCliente = scanner.nextLine();
		System.out.println("\nIngrese su dato de contacto: ");
		String datoContactoCliente = scanner.nextLine();
		System.out.println("\nIngrese fecha de nacimiento(DD-MM-AA): ");
		String fechaNacimientoCliente = scanner.nextLine();
		System.out.println("\nIngrese Nacionalidad: ");
		String nacionalidadCliente = scanner.nextLine();
		System.out.println("\nIngrese un login para registrarse: ");
		String loginCliente = scanner.nextLine();
		System.out.println("\nIngrese contraseña: ");
		String contraseñaCliente = scanner.nextLine();
		
		agregarClienteBasicos(nombreCliente+";"+datoContactoCliente+";"+fechaNacimientoCliente+";"+nacionalidadCliente+";"+loginCliente+";"+contraseñaCliente);
		
		DatosBasicos datosBasicosCliente = new DatosBasicos(nombreCliente,datoContactoCliente,fechaNacimientoCliente,nacionalidadCliente,loginCliente,contraseñaCliente,"cliente");
		
		System.out.println("\nDatos basicos registrados!");
		
		return datosBasicosCliente;
		
	}
	
	public DatosLicencia datoslicenciaCliente(){
		Scanner scanner = new Scanner(System.in); 
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("\t\t\tDatos Licencia\n");
		System.out.println("Ingrese el pais: ");
		String paisLicenciaCliente = scanner.nextLine();
		System.out.println("\nIngrese el numero: ");
		String numeroLicenciaCliente = scanner.nextLine();
		System.out.println("\nIngrese fecha de vencimiento(MM-AA): ");
		String fechaVencimientoLicenciaCliente = scanner.nextLine();
		
		agregarClienteLicencias(paisLicenciaCliente+";"+numeroLicenciaCliente+";"+fechaVencimientoLicenciaCliente);
		
		DatosLicencia datosLicenciaCliente = new DatosLicencia(paisLicenciaCliente,Integer.parseInt(numeroLicenciaCliente),fechaVencimientoLicenciaCliente);
		System.out.println("\nDatos licencia registrados!");
		
		return datosLicenciaCliente;
		
	}
	
	
	
	public void agregarClienteBasicos(String texto){
		FileWriter filewriter;
		try {
			filewriter = new FileWriter("./inventario/clientes.txt",true);
			filewriter.write(texto.toLowerCase()+";");
			filewriter.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		}
		public void agregarClienteLicencias(String texto){
			FileWriter filewriter;
			try {
				filewriter = new FileWriter("./inventario/clientes.txt",true);
				filewriter.write(texto.toLowerCase()+"\n");
				filewriter.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
	
	
	}

}

