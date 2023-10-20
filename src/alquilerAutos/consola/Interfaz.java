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
import alquilerAutos.sistema.Reserva;
import alquilerAutos.sistema.SistemaAlquilerAutos;

public class Interfaz {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Interfaz consola = new Interfaz();
		consola.ejecutarOpcion();

	}

	public void ejecutarOpcion() throws FileNotFoundException, IOException {
		SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();
		{
			boolean continuar = true;
			while (continuar) {
				try {
					sistema.cargarInformacionVehiculos();
					sistema.cargarInformacionCliente();
					sistema.cargarInformacionEmpleado();
					sistema.cargarInformacionCondicionesCategoria();
					sistema.cargarInformacionSeguros();
					sistema.cargarInformacionSedes();

					System.out.println(
							"----------------------------------------------------------------------------------------");
					System.out.println("\nBienvenido al Sistema Alquiler de Vehiculos");
					System.out.println("\n1. Iniciar sesion como cliente");
					System.out.println("2. Iniciar sesion como empleado");
					System.out.println("3. Iniciar sesion como administrador");
					System.out.println("4. Iniciar sesion como administrador de sede");
					System.out.println("5. Registrarse como nuevo cliente");
					System.out.println("6. Salir de la aplicacion");

					int opcionSeleccionada = Integer.parseInt(input("\nPor favor seleccione una opcion"));

					if (opcionSeleccionada == 1) {
						Scanner scanner = new Scanner(System.in);
						System.out.println(
								"----------------------------------------------------------------------------------------");
						System.out.println("\nLogin: ");
						String loginCliente = scanner.nextLine();
						System.out.println("\nContraseña: ");
						String contraseñaCliente = scanner.nextLine();

						if (sistema.verificarCliente(loginCliente, contraseñaCliente)) {
							System.out.println(
									"----------------------------------------------------------------------------------------");
							System.out.println("\nBienvenido cliente " + loginCliente);
							System.out.println("\n1. Realizar una reserva");
							int opcion = Integer.parseInt(input("\nPor favor seleccione una opcion"));

							if (opcion == 1) {
								Reserva reserva = sistema.crearReserva();
								sistema.setReserva(loginCliente, reserva);
							}
						} else {
							System.out.println("\nUsuario o contraseña incorrectos");
						}

					}

					if (opcionSeleccionada == 2) {
						Scanner scanner = new Scanner(System.in);
						System.out.println(
								"----------------------------------------------------------------------------------------");
						System.out.println("\nLogin: ");
						String loginEmpleado = scanner.nextLine();
						System.out.println("\nContraseña: ");
						String contraseñaEmpleado = scanner.nextLine();

						if (sistema.verificarEmpleado(loginEmpleado, contraseñaEmpleado)) {
							System.out.println(
									"----------------------------------------------------------------------------------------");
							System.out.println("\nBienvenido empleado " + loginEmpleado);
							System.out.println("\n1. Entregar vehiculo");
							System.out.println("\n1. Recibir vehiculo");
							int opcion = Integer.parseInt(input("\nPor favor seleccione una opcion"));

							if (opcion == 1) {
								sistema.entregaVehiculo();
							} else if (opcion == 2) {
								sistema.recibirVehiculo();
							}
						} else {
							System.out.println("\nUsuario o contraseña incorrectos");
						}

					}

					if (opcionSeleccionada == 3) {
						Scanner scanner = new Scanner(System.in);
						Administrador administrador = new Administrador();
						System.out.println(
								"----------------------------------------------------------------------------------------");
						System.out.println("\nLogin: ");
						String loginAdmin = scanner.nextLine();
						System.out.println("\nContraseña: ");
						String contraseñaAdmin = scanner.nextLine();

						if (Administrador.getLogin().equals(loginAdmin)
								&& Administrador.getContraseña().equals(contraseñaAdmin)) {
							System.out.println(
									"----------------------------------------------------------------------------------------");
							System.out.println("\nBienvenido administrador " + Administrador.getLogin());
							System.out.println("\n1. Registrar empleado");
							System.out.println("\n2. Registrar nuevo vehiculo");
							System.out.println("\n3. Configurar seguro");
							int opcion = Integer.parseInt(input("\nPor favor seleccione una opcion"));

							if (opcion == 1) {
								sistema.nuevoEmpleado();
							} else if (opcion == 2) {
								sistema.registrarNuevoVehiculo();
							} else if (opcion == 3) {
								sistema.nuevoSeguro();
							}
						} else {
							System.out.println("\nUsuario o contraseña incorrectos");
						}
					}
					if (opcionSeleccionada == 4) {
						Scanner scanner = new Scanner(System.in);
						System.out.println(
								"----------------------------------------------------------------------------------------");
						System.out.println("\nLogin: ");
						String loginAdminSede = scanner.nextLine();
						System.out.println("\nContraseña: ");
						String contraseñaAdminSede = scanner.nextLine();

						System.out.println(
								"----------------------------------------------------------------------------------------");
						System.out.println("\nBienvenido ");
						System.out.println("\n1. Ofrecer Seguros");
						int opcion = Integer.parseInt(input("\nPor favor seleccione una opcion"));

						if (opcion == 1) {
							sistema.ofrecerSeguro();
						}
					}

					if (opcionSeleccionada == 5) {
						Scanner scanner = new Scanner(System.in);
						System.out.println(
								"----------------------------------------------------------------------------------------");
						sistema.nuevoCliente();

					} else if (opcionSeleccionada == 6) {
						System.out.println("Saliendo de la aplicacion ...");
						continuar = false;
					}
				} catch (NumberFormatException e) {
					System.out.println("Debe seleccionar uno de los números de las opciones.");
				}
			}
		}
	}

	public String input(String mensaje) {
		try {
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

}
