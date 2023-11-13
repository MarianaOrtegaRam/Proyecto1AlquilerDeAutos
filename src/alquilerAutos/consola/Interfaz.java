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
import alquilerAutos.modelo.AdministradorSede;

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
					sistema.cargarAdminSedes();
					//sistema.printSede();

					System.out.println(
							"\n----------------------------------------------------------------------------------------");
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
							System.out.println("\n2. Modificar su reserva");
							System.out.println("\n3. saber datosCliente");
							int opcion = Integer.parseInt(input("\nPor favor seleccione una opcion"));

							if (opcion == 1) {
								Scanner scanner_reserva = new Scanner(System.in);
								System.out.println(
										"----------------------------------------------------------------------------------------");
								System.out.println("\t\t\tCrear Reserva\n");
								System.out.println("\nIngrese la categoria: ");
								String categoriaVehiculo = scanner_reserva.nextLine();
								System.out.println("\nIngrese sede para recoger: ");
								String sedeRecogerVehiculo = scanner_reserva.nextLine();
								System.out.println("\nIngrese sede para entregar: ");
								String sedeEntregarrVehiculo = scanner_reserva.nextLine();
								System.out.println("\nIngrese fecha para recoger (DD-MM-AA): ");
								String fechaHoraRecogerVehiculo = scanner_reserva.nextLine();
								System.out.println("\nIngrese un rango de hora para recoger (N-N): ");
								String rangoHoraRecogerVehiculo = scanner_reserva.nextLine();
								System.out.println("\nIngrese fecha para entrega (DD-MM-AA): ");
								String fechaEntregaVehiculo = scanner_reserva.nextLine();
								sistema.ofrecerSeguro();
								System.out.println("\nIngrese seguro (Opcional): ");
								String seguroVehiculo = scanner_reserva.nextLine();

								Reserva reserva = sistema.crearReserva(sedeRecogerVehiculo, sedeEntregarrVehiculo,
										fechaHoraRecogerVehiculo, rangoHoraRecogerVehiculo, fechaEntregaVehiculo,
										seguroVehiculo, categoriaVehiculo,loginCliente);

								sistema.setReserva(loginCliente, reserva);
								
								
							} else if (opcion == 2) {
								Scanner scanner_modificar_reserva = new Scanner(System.in);
								System.out.println(
										"----------------------------------------------------------------------------------------");
								System.out.println("\t\t\tModificar Reserva\n");
								System.out.println("\n1. Modificar la sede de entrega");
								System.out.println("2. Modificar Rango de hora de entrega");
								System.out.println("3. Modicar fecha entrega");

								int opcionSeleccionada_ = Integer.parseInt(input("\nPor favor seleccione una opcion"));

								if (opcionSeleccionada_ == 1) {
									String sedeEntrega = scanner_modificar_reserva.nextLine();
									sistema.modificarSedeEntrega(sedeEntrega);

								}

								if (opcionSeleccionada_ == 2) {
									String rangoEntrega = scanner_modificar_reserva.nextLine();
									sistema.modificarRangoEntrega(rangoEntrega);
								}

								if (opcionSeleccionada_ == 3) {
									String fechaEntrega = scanner_modificar_reserva.nextLine();
									sistema.modificarFechaEntrega(fechaEntrega);
								}
							}
						}

						else {
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
							System.out.println("\n2. Recibir vehiculo");
							int opcion = Integer.parseInt(input("\nPor favor seleccione una opcion"));

							if (opcion == 1) {
								Scanner scanner_entregar = new Scanner(System.in);
								System.out.println(
										"----------------------------------------------------------------------------------------");
								System.out.println("\nIngrese la placa del vehiculo a entregar: ");
								String placaAuto = scanner_entregar.nextLine();
								sistema.entregaVehiculo(placaAuto);
							} else if (opcion == 2) {
								Scanner scanner_recibir = new Scanner(System.in);
								System.out.println(
										"----------------------------------------------------------------------------------------");
								System.out.println("\nIngrese la placa del vehiculo a recibir: ");
								String placaAuto = scanner_recibir.nextLine();
								sistema.recibirVehiculo(placaAuto);
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
							System.out.println("\n4. Registrar nuevo AdminSede");
							int opcion = Integer.parseInt(input("\nPor favor seleccione una opcion"));

							if (opcion == 1) {
								Scanner scanner_n_empleado = new Scanner(System.in);
								System.out.println(
										"----------------------------------------------------------------------------------------");
								System.out.println("\t\t\tDatos Basicos Empleado\n");
								System.out.println("Ingrese su nombre: ");
								String nombreEmpleado = scanner_n_empleado.nextLine();
								System.out.println("\nIngrese dato de contacto: ");
								String datoContactoEmpleado = scanner_n_empleado.nextLine();
								System.out.println("\nIngrese fecha de nacimiento(DD-MM-AA): ");
								String fechaNacimientoEmpleado = scanner_n_empleado.nextLine();
								System.out.println("\nIngrese Nacionalidad: ");
								String nacionalidadEmplado = scanner_n_empleado.nextLine();
								System.out.println("\nIngrese login para registrarse: ");
								String loginEmpleado = scanner_n_empleado.nextLine();
								System.out.println("\nIngrese contraseña: ");
								String contraseñaEmpleado = scanner_n_empleado.nextLine();

								sistema.nuevoEmpleado(nombreEmpleado, datoContactoEmpleado, fechaNacimientoEmpleado,
										nacionalidadEmplado, loginEmpleado, contraseñaEmpleado);

							} else if (opcion == 2) {
								Scanner scanner_auto = new Scanner(System.in);
								System.out.println(
										"----------------------------------------------------------------------------------------");
								System.out.println("\t\t\tDatos Vehiculo\n");
								System.out.println("Ingrese placa: ");
								String placaVehiculo = scanner_auto.nextLine();
								System.out.println("\nIngrese marca: ");
								String marcaVehiculo = scanner_auto.nextLine();
								System.out.println("\nIngrese tamaño: ");
								String tamañoVehiculo = scanner_auto.nextLine();
								System.out.println("\nIngrese modelo: ");
								String modeloVehiculo = scanner_auto.nextLine();
								System.out.println("\nIngrese color: ");
								String colorVehiculo = scanner_auto.nextLine();
								System.out.println("\nIngrese caja: ");
								String cajaVehiculo = scanner_auto.nextLine();
								System.out.println("\nIngrese precio por dia: ");
								String precioPorDiaVehiculo = scanner_auto.nextLine();
								System.out.println("\nIngrese maletas: ");
								String maletasVehiculo = scanner_auto.nextLine();
								System.out.println("\nIngrese capacidad: ");
								String capacidadVehiculo = scanner_auto.nextLine();
								System.out.println("\nIngrese categoria: ");
								String categoriaVehiculo = scanner_auto.nextLine();
								System.out.println("\nIngrese sede: ");
								String sedeVehiculo = scanner_auto.nextLine();
								sistema.registrarNuevoVehiculo(placaVehiculo, marcaVehiculo, tamañoVehiculo,
										modeloVehiculo, colorVehiculo, cajaVehiculo,
										precioPorDiaVehiculo, maletasVehiculo, capacidadVehiculo, categoriaVehiculo,
										sedeVehiculo);

							} else if (opcion == 3) {
								Scanner scanner_seguro = new Scanner(System.in);
								System.out.println(
										"----------------------------------------------------------------------------------------");
								System.out.println("\t\t\tConfiguracion nuevo seguro\n");
								System.out.println("Ingrese nombre: ");
								String nombreSeguro = scanner_seguro.nextLine();
								System.out.println("\nIngrese precio: ");
								String precioSeguro = scanner_seguro.nextLine();
								System.out.println("\nIngrese beneficios: ");
								String beneficiosVehiculo = scanner_seguro.nextLine();
								sistema.nuevoSeguro(nombreSeguro, precioSeguro, beneficiosVehiculo);
							}
							else if (opcion == 4) {
								Scanner scanner_admin = new Scanner(System.in);
								System.out.println(
										"----------------------------------------------------------------------------------------");
								System.out.println("\t\t\tConfiguracion nuevo seguro\n");
								System.out.println("Ingrese nombre: ");
								String nombre = scanner_admin.nextLine();
								System.out.println("\nIngrese cedula: ");
								String cedula = scanner_admin.nextLine();
								System.out.println("\nIngrese login: ");
								String login = scanner_admin.nextLine();
								System.out.println("\nIngrese contraseña: ");
								String contraseña = scanner_admin.nextLine();
								System.out.println("\nIngrese sede: ");
								String sede = scanner_admin.nextLine();
								
								sistema.registrarAdminSede(nombre, cedula, login, contraseña,sede);
							}
							}

						} else {
							System.out.println("\nUsuario o contraseña incorrectos");
						}
					
					if (opcionSeleccionada == 4) {
						Scanner scanner = new Scanner(System.in);
						System.out.println(
								"----------------------------------------------------------------------------------------");
						System.out.println("\nLogin: ");
						String loginAdminSede = scanner.nextLine();
						System.out.println("\nContraseña: ");
						String contraseñaAdminSede = scanner.nextLine();
						if (sistema.verificarAdminSede(loginAdminSede, contraseñaAdminSede)) {
							
						
						System.out.println(
								"----------------------------------------------------------------------------------------");
						System.out.println("\nBienvenido ");
						System.out.println("\n1. Ofrecer Seguros");
						System.out.println("\n2. Cambiar de sede un vehiculo");
						int opcion = Integer.parseInt(input("\nPor favor seleccione una opcion"));

						if (opcion == 1) {
							sistema.ofrecerSeguro();
						} else if(opcion == 2) {
							System.out.println("Ingrese la placa del vehiculo: ");
							String placa = scanner.nextLine();
							System.out.println("Ingrese la sede a la que va a ser ingresado: ");
							String sede = scanner.nextLine();
							sistema.cambiarVehiculoSede(placa,sede);
						}
						
					}
						else {
							System.out.println("\nUsuario o contraseña incorrectos");
						}
					}

					if (opcionSeleccionada == 5) {
						Scanner scanner_nuevo_cliente = new Scanner(System.in);
						System.out.println(
								"----------------------------------------------------------------------------------------");
						System.out.println("\t\t\tDatos Basicos\n");
						System.out.println("Ingrese su nombre: ");
						String nombreCliente = scanner_nuevo_cliente.nextLine();
						System.out.println("\nIngrese su dato de contacto: ");
						String datoContactoCliente = scanner_nuevo_cliente.nextLine();
						System.out.println("\nIngrese fecha de nacimiento(DD-MM-AA): ");
						String fechaNacimientoCliente = scanner_nuevo_cliente.nextLine();
						System.out.println("\nIngrese Nacionalidad: ");
						String nacionalidadCliente = scanner_nuevo_cliente.nextLine();
						System.out.println("\nIngrese un login para registrarse: ");
						String loginCliente = scanner_nuevo_cliente.nextLine();
						System.out.println("\nIngrese contraseña: ");
						String contraseñaCliente = scanner_nuevo_cliente.nextLine();

						System.out.println(
								"----------------------------------------------------------------------------------------");
						System.out.println("\t\t\tDatos Licencia\n");
						System.out.println("Ingrese el pais: ");
						String paisLicenciaCliente = scanner_nuevo_cliente.nextLine();
						System.out.println("\nIngrese el numero: ");
						String numeroLicenciaCliente = scanner_nuevo_cliente.nextLine();
						System.out.println("\nIngrese fecha de vencimiento(MM-AA): ");
						String fechaVencimientoLicenciaCliente = scanner_nuevo_cliente.nextLine();

						sistema.nuevoCliente(nombreCliente, datoContactoCliente, fechaNacimientoCliente,
								nacionalidadCliente, loginCliente, contraseñaCliente, paisLicenciaCliente,
								numeroLicenciaCliente, fechaVencimientoLicenciaCliente);

						System.out.println("\nDatos Cliente y Licencia registrados!");

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
