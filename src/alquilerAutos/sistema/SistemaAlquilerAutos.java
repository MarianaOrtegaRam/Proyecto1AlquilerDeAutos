package alquilerAutos.sistema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import alquilerAutos.modelo.Administrador;
import alquilerAutos.modelo.AdministradorSede;
import alquilerAutos.modelo.Categoria;
import alquilerAutos.modelo.DatosBasicos;
import alquilerAutos.modelo.DatosCliente;
import alquilerAutos.modelo.DatosLicencia;
import alquilerAutos.modelo.Empleado;
import alquilerAutos.modelo.InfoCliente;
import alquilerAutos.modelo.InfoEmpleado;
import alquilerAutos.modelo.Sede;
import alquilerAutos.modelo.Seguro;
import alquilerAutos.modelo.Vehiculo;

public class SistemaAlquilerAutos {

	public ArrayList<Reserva> reservas = new ArrayList<>();
	public ArrayList<Vehiculo> vehiculos = new ArrayList<>();
	public ArrayList<DatosCliente> clientes = new ArrayList<>();
	public ArrayList<DatosBasicos> empleados = new ArrayList<>();
	public ArrayList<Seguro> seguros = new ArrayList<>();
	public Map<String, ArrayList<String>> sedes = new HashMap<>();
	public Map<String, String[]> condicionesCategoria = new HashMap<>();
	public Reserva reserva;
	public ArrayList<AdministradorSede> adminsedes = new ArrayList<>();

	public void nuevoCliente(String nombreCliente, String datoContactoCliente, String fechaNacimientoCliente,
			String nacionalidadCliente,
			String loginCliente, String contraseñaCliente, String paisLicenciaCliente, String numeroLicenciaCliente,
			String fechaVencimientoLicenciaCliente) {

		InfoCliente infoCliente = new InfoCliente();
		DatosCliente cliente = infoCliente.crearCliente(nombreCliente, datoContactoCliente, fechaNacimientoCliente,
				nacionalidadCliente, loginCliente, contraseñaCliente, paisLicenciaCliente, numeroLicenciaCliente,
				fechaVencimientoLicenciaCliente);

		this.clientes.add(cliente);
	}

	public boolean nuevoEmpleado(String nombreEmpleado, String datoContactoEmpleado, String fechaNacimientoEmpleado,
			String nacionalidadEmplado,
			String loginEmpleado, String contraseñaEmpleado) {

		Administrador administrador = new Administrador();

		DatosBasicos empleado = administrador.registrarEmpleado(nombreEmpleado, datoContactoEmpleado,
				fechaNacimientoEmpleado, nacionalidadEmplado,
				loginEmpleado, contraseñaEmpleado);

		if (empleado != null) {
			this.empleados.add(empleado);
			return true; // Se registró exitosamente
		} else {
			return false; // No se pudo registrar (puedes agregar más lógica según sea necesario)
		}
	}

	public void registrarAdminSede(String nombre, String cedula, String login, String contraseña, String sede) {

		Administrador administrador = new Administrador();

		AdministradorSede adminsede = administrador.registrarAdminSede(nombre, cedula, login, contraseña, sede);

		this.adminsedes.add(adminsede);
	}

	public void cargarAdminSedes() throws FileNotFoundException, IOException {

		BufferedReader br = new BufferedReader(new FileReader("./inventario/adminsedes.txt"));
		String linea = br.readLine();

		while (linea != null) {
			String[] partes = linea.split(";");
			String nombre = partes[0];
			String cedula = partes[1];
			String login = partes[2];
			String contraseña = partes[3];
			String sede = partes[4];
			AdministradorSede adminSede = new AdministradorSede(nombre, cedula, login, contraseña, sede);

			this.adminsedes.add(adminSede);
			linea = br.readLine();
		}

		br.close();
	}

	public boolean nuevoSeguro(String nombreSeguro, String precioSeguro, String beneficiosVehiculo) {
		Administrador administrador = new Administrador();
		Seguro seguro = administrador.configurarSeguro(nombreSeguro, precioSeguro, beneficiosVehiculo);

		if (seguro != null) {
			this.seguros.add(seguro);
			return true; // Se configuró el seguro exitosamente
		} else {
			return false; // No se pudo configurar el seguro (puedes agregar más lógica según sea
							// necesario)
		}
	}

	public boolean registrarNuevoVehiculo(String placaVehiculo, String marcaVehiculo, String tamañoVehiculo,
			String modeloVehiculo, String colorVehiculo, String cajaVehiculo, String precioPorDiaVehiculo,
			String maletasVehiculo, String capacidadVehiculo, String categoriaVehiculo, String sedeVehiculo) {

		Administrador administrador = new Administrador();

		Vehiculo vehiculo = administrador.registrarNuevoVehiculo(placaVehiculo, marcaVehiculo, tamañoVehiculo,
				modeloVehiculo, colorVehiculo, cajaVehiculo,
				precioPorDiaVehiculo, maletasVehiculo, capacidadVehiculo, categoriaVehiculo, sedeVehiculo);

		if (vehiculo != null) {
			this.vehiculos.add(vehiculo);
			String sede = vehiculo.getsede();

			ArrayList<String> sedeExiste = sedes.get(sede);
			if (sedeExiste == null) {
				ArrayList<String> carros = new ArrayList<>();
				carros.add(vehiculo.getPlaca());
				sedes.put(sede, carros);
			} else {
				ArrayList<String> lista = sedes.get(sede);
				lista.add(vehiculo.getPlaca());
			}
			return true; // Se registró exitosamente
		} else {
			return false; // No se pudo registrar (puedes agregar más lógica según sea necesario)
		}
	}

	public void cargarInformacionCliente() throws FileNotFoundException, IOException {

		BufferedReader br = new BufferedReader(new FileReader("./inventario/clientes.txt"));
		String linea = br.readLine();

		while (linea != null) {

			String[] partes = linea.split(";");
			String nombre = partes[0];
			String datoContacto = partes[1];
			String fechaNacimiento = partes[2];
			String nacionalidad = partes[3];
			String login = partes[4];
			String contraseña = partes[5];
			String pais = partes[6];
			String numero = partes[7];
			String fechaVencimiento = partes[8];

			DatosBasicos datosBasicos = new DatosBasicos(nombre, datoContacto, fechaNacimiento, nacionalidad, login,
					contraseña, "cliente");
			DatosLicencia datosLicencia = new DatosLicencia(pais, Integer.parseInt(numero), fechaVencimiento);

			DatosCliente datosCliente = new DatosCliente(datosBasicos, datosLicencia);
			this.clientes.add(datosCliente);
			linea = br.readLine();
		}

		br.close();
	}

	public boolean verificarCliente(String login, String contraseña) {

		boolean verificacion = false;

		for (int i = 0; i < clientes.size(); i++) {
			String loginUnCliente = clientes.get(i).getDatosBasicos().getLogin();
			String contraseñaUnCliente = clientes.get(i).getDatosBasicos().getContraseña();
			if (loginUnCliente.equals(login) && contraseñaUnCliente.equals(contraseña)) {
				verificacion = true;

			}
		}
		return verificacion;

	}

	public boolean verificarAdminSede(String login, String contraseña) {

		boolean verificacion = false;

		for (int i = 0; i < adminsedes.size(); i++) {
			String loginAdminSede = adminsedes.get(i).getLogin();
			String contraseñaAdminSede = adminsedes.get(i).getContraseña();

			if (loginAdminSede.equals(login) && contraseñaAdminSede.equals(contraseña)) {
				verificacion = true;

			}
		}
		return verificacion;

	}

	public boolean verificarAdministrador(String login, String contraseña) {
		boolean verificacion = false;
		if (Administrador.getLogin().equals(login)
				&& Administrador.getContraseña().equals(contraseña)) {
			verificacion = true;
		}
		return verificacion;
	}

	public void cargarInformacionEmpleado() throws FileNotFoundException, IOException {

		BufferedReader br = new BufferedReader(new FileReader("./inventario/empleados.txt"));
		String linea = br.readLine();

		while (linea != null) {

			String[] partes = linea.split(";");
			String nombre = partes[0];
			String datoContacto = partes[1];
			String fechaNacimiento = partes[2];
			String nacionalidad = partes[3];
			String login = partes[4];
			String contraseña = partes[5];

			DatosBasicos datosBasicos = new DatosBasicos(nombre, datoContacto, fechaNacimiento, nacionalidad, login,
					contraseña, "empleado");

			this.empleados.add(datosBasicos);
			linea = br.readLine();
		}

		br.close();
	}

	public boolean verificarEmpleado(String login, String contraseña) {

		boolean verificacion = false;

		for (int i = 0; i < empleados.size(); i++) {
			String loginUnEmpleado = empleados.get(i).getLogin();
			String contraseñaEmpleado = empleados.get(i).getContraseña();

			if (loginUnEmpleado.equals(login) && contraseñaEmpleado.equals(contraseña)) {
				verificacion = true;

			}
		}
		return verificacion;

	}

	public void cargarInformacionVehiculos() throws FileNotFoundException, IOException {

		BufferedReader br = new BufferedReader(new FileReader("./inventario/vehiculos.txt"));
		String linea = br.readLine();

		while (linea != null) {

			String[] partes = linea.split(";");
			String placa = partes[0];
			String marca = partes[1];
			String tamaño = partes[2];
			String modelo = partes[3];
			String color = partes[4];
			String caja = partes[5];
			int precioPorDia = Integer.parseInt(partes[6]);
			int maletas = Integer.parseInt(partes[7]);
			int capacidad = Integer.parseInt(partes[8]);
			boolean disponible;
			String disponibilidadDoc = partes[9];
			if (disponibilidadDoc.equals("true")) {
				disponible = true;
			} else {
				disponible = false;
			}
			String categoria = partes[10];
			String sede = partes[11];

			Vehiculo elvehiculo = new Vehiculo(placa, marca, tamaño, modelo, color, caja, precioPorDia, maletas,
					capacidad, disponible, categoria, sede);
			this.vehiculos.add(elvehiculo);
			linea = br.readLine();
		}

		br.close();

	}

	public void cargarInformacionCondicionesCategoria() throws FileNotFoundException, IOException {

		BufferedReader br = new BufferedReader(new FileReader("./inventario/condicionesCategoria.txt"));
		String linea = br.readLine();

		while (linea != null) {

			String[] partes = linea.split(";");
			String nombreCategoria = partes[0];
			String condiciones = partes[1];
			String[] condiciones1 = condiciones.split("!");

			String[] condicionesCate = condicionesCategoria.get(nombreCategoria);
			if (condicionesCate == null) {
				condicionesCategoria.put(nombreCategoria, condiciones1);
			}

			linea = br.readLine();
		}

		br.close();

	}

	public void cargarInformacionSedes() throws FileNotFoundException, IOException {

		BufferedReader br = new BufferedReader(new FileReader("./inventario/sedesvehiculos.txt"));
		String linea = br.readLine();

		while (linea != null) {

			String[] partes = linea.split(";");
			String nombreSede = partes[0];
			String placa = partes[1];
			ArrayList<String> sedeExiste = sedes.get(nombreSede);
			if (sedeExiste == null) {
				ArrayList<String> carros = new ArrayList<>();
				carros.add(placa);
				sedes.put(nombreSede, carros);
			} else {
				ArrayList<String> lista = sedes.get(nombreSede);
				lista.add(placa);
			}

			linea = br.readLine();
		}

		br.close();

	}

	public void cargarInformacionSeguros() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("./inventario/seguros.txt"));
		String linea = br.readLine();

		while (linea != null) {

			String[] partes = linea.split(";");
			String nombreSeguro = partes[0];
			String precioSeguro = partes[1];
			String descripcionSeguro = partes[2];

			Seguro seguro = new Seguro(nombreSeguro, Integer.parseInt(precioSeguro), descripcionSeguro);

			this.seguros.add(seguro);
			linea = br.readLine();
		}

		br.close();
	}

	public void cargarInformacionReservas() throws FileNotFoundException, IOException {

		BufferedReader br = new BufferedReader(new FileReader("./inventario/reservas.txt"));
		String linea = br.readLine();

		while (linea != null) {

			String[] partes = linea.split(";");
			String sedeRecoger = partes[0];
			String sedeEntrega = partes[1];
			String fechaHoraRecoger = partes[2];
			String rangoHoraEntrega = partes[3];
			String fechaEntrega = partes[4];
			String seguro = partes[5];
			String login = partes[6];
			String placa = partes[7];

			Reserva laReserva = new Reserva(sedeRecoger, sedeEntrega, fechaHoraRecoger, rangoHoraEntrega, fechaEntrega,
					seguro);
			laReserva.setLoginCliente(login);
			for (Vehiculo unVehiculo : vehiculos) {
				String unaPlaca = unVehiculo.getPlaca();
				if (placa.equals(unaPlaca)) {
					unVehiculo.setDisponible(false);
					laReserva.setVehiculo(unVehiculo);

				}
			}
			setReserva(login, laReserva);
			this.reservas.add(laReserva);
			linea = br.readLine();
		}

		br.close();

	}

	public void ofrecerSeguro() {
		for (int i = 0; i < seguros.size(); i++) {
			String nombreSeguro = seguros.get(i).getNombreSeguro();
			int precioSeguro = seguros.get(i).getPrecio();
			String beneficiosSeguro = seguros.get(i).getBeneficios();

			System.out.println("\n- Nombre del seguro: " + nombreSeguro);
			System.out.println("\n- Precio del seguro: " + String.valueOf(precioSeguro));
			System.out.println("\n- Beneficios del seguro: " + beneficiosSeguro);
			System.out.println(
					"\n----------------------------------------------------------------------------------------");
		}

	}

	public Reserva crearReserva(String sedeRecogerVehiculo, String sedeEntregarrVehiculo,
			String fechaHoraRecogerVehiculo, String rangoHoraRecogerVehiculo, String fechaEntregaVehiculo,
			String seguroVehiculo, String categoriaVehiculo, String login) {

		Reserva reserva = new Reserva(sedeRecogerVehiculo, sedeEntregarrVehiculo,
				fechaHoraRecogerVehiculo, rangoHoraRecogerVehiculo, fechaEntregaVehiculo, seguroVehiculo);
		reserva.setLoginCliente(login);

		ArrayList<String> listaPlacas = sedes.get(sedeRecogerVehiculo);

		Vehiculo candidato = null;
		Vehiculo categoriaDeseada = null;
		int diasReserva = diasReserva(fechaHoraRecogerVehiculo, fechaEntregaVehiculo);
		for (int i = 0; i < listaPlacas.size(); i++) {
			String unaPlaca = listaPlacas.get(i);
			for (int j = 0; j < this.vehiculos.size(); j++) {
				Vehiculo unVehiculo = vehiculos.get(j);
				String placa_vehiculo = unVehiculo.getPlaca();
				if (unaPlaca.equals(placa_vehiculo)) {
					String categoriaUnVehiculo = unVehiculo.getCategoria();
					if (categoriaUnVehiculo.equals(categoriaVehiculo) && unVehiculo.getDisponibilidad()) {
						categoriaDeseada = unVehiculo;
					} else if (categoriaUnVehiculo != categoriaVehiculo && unVehiculo.getDisponibilidad()) {
						String[] condicionesUnaCategoria = condicionesCategoria.get(categoriaVehiculo);
						boolean cumple = unVehiculo.verificarCondiciones(condicionesUnaCategoria);
						if (cumple) {
							candidato = unVehiculo;

						}

					}
				}
			}
		}

		if (categoriaDeseada != null) {
			reserva.setVehiculo(categoriaDeseada);
			categoriaDeseada.setDisponible(false);
			String categoria_ = categoriaDeseada.getCategoria();
			reserva.setCategoriaSeleccionada(categoria_);
			int precioFinalReserva = calucularTarifas(categoria_, categoriaDeseada, diasReserva);
			reserva.setPrecioFinal(precioFinalReserva);
			System.out.println("Reserva exitosa!");
			reserva.registrarReserva(reserva);
			reservas.add(reserva);
			setReserva(login, reserva);

		} else if (candidato != null) {
			reserva.setVehiculo(candidato);
			String categoria_ = candidato.getCategoria();
			reserva.setCategoriaSeleccionada(categoria_);
			int precioFinalReserva = calucularTarifas(categoria_, candidato, diasReserva);
			reserva.setPrecioFinal(precioFinalReserva);
			System.out.println(
					"No se encontro disponible la categoria deseada, sin embargo, este vehiculo cumple sus nececidades");
			reserva.registrarReserva(reserva);
			reservas.add(reserva);
			setReserva(login, reserva);
			candidato.setDisponible(false);
		} else {
			reserva = null;
			System.out.println("No se pudo realizar la reserva");
		}

		return reserva;

	}

	public void modificarSedeEntrega(String sedeEntrega) {
		this.reserva.setSedeEntrega(sedeEntrega);
	}

	public void modificarRangoEntrega(String rangoEntrega) {
		this.reserva.setRangoHoraEntrega(rangoEntrega);
	}

	public void modificarFechaEntrega(String fechaEntrega) {
		this.reserva.setFechaEntrega(fechaEntrega);
	}

	public int calucularTarifas(String nombreCategoria, Vehiculo carro, int dias) {

		int precioInicial = (carro.getPrecioPorDia()) * dias;
		int excedente = 0;
		int precioFinal = 0;
		// String nombreCategoria = categoria.getNombreCategoria();

		if (nombreCategoria.equals("economico")) {
			excedente += 50000;
			precioFinal = precioInicial + excedente;
		} else if (nombreCategoria.equals("intermedio")) {
			excedente += 70000;
			precioFinal = precioInicial + excedente;
		} else if (nombreCategoria.equals("tranportemultiple")) {
			excedente += 90000;
			precioFinal = precioInicial + excedente;
		} else if (nombreCategoria.equals("vehiculolujo")) {
			excedente += 100000;
			precioFinal = precioInicial + excedente;
		}

		return precioFinal;
	}

	public String buscarVehiculo(Vehiculo auto) {
		String placa = auto.getPlaca();
		String sede = "No se encuentra!";

		for (int i = 0; i < vehiculos.size(); i++) {
			Vehiculo prueba = vehiculos.get(i);
			if (placa.equals(prueba.getPlaca())) {
				sede = auto.getsede();
			}
		}

		return sede;

	}

	public String disponibilidadVehiculo(Vehiculo auto) {

		String placa = auto.getPlaca();
		String disponibilidad = "No se encuentra disponible!";

		for (int i = 0; i < vehiculos.size(); i++) {
			Vehiculo prueba = vehiculos.get(i);
			if (placa.equals(prueba.getPlaca()) && prueba.getDisponibilidad()) {
				disponibilidad = "Se encuentra disponible";
			}
		}

		return disponibilidad;
	}

	public boolean entregaVehiculo(String placaAuto) {

		Empleado empleado = new Empleado();
		String placa = empleado.entregarAuto(placaAuto);

		for (int i = 0; i < vehiculos.size(); i++) {
			Vehiculo prueba = vehiculos.get(i);
			if (placa.equals(prueba.getPlaca())) {
				prueba.setDisponible(false);
				return true;
			}
		}
		return false;
	}

	public boolean recibirVehiculo(String placaAuto) {

		Empleado empleado = new Empleado();

		String placa = empleado.recibirAuto(placaAuto);
		for (int i = 0; i < vehiculos.size(); i++) {
			Vehiculo prueba = vehiculos.get(i);
			if (placa.equals(prueba.getPlaca())) {
				prueba.setDisponible(true);
				return true;
			}
		}
		return false;
	}

	public void setReserva(String login, Reserva reserva) {

		for (int i = 0; i < clientes.size(); i++) {
			String loginUnCliente = clientes.get(i).getDatosBasicos().getLogin();

			if (loginUnCliente.equals(login)) {
				clientes.get(i).setReservaActiva(reserva);
			}
		}
	}

	public boolean cambiarVehiculoSede(String placa, String sede) {
		for (int i = 0; i < adminsedes.size(); i++) {
			String sede_ = adminsedes.get(i).getSede();
			if (sede_.equals(sede)) {
				AdministradorSede adminSede = adminsedes.get(i);
				adminSede.cambiarSede(placa, sede, sedes);
				return true;
			}

		}
		return false;
	}

	public boolean eliminarVehiculo(String placa) {
		for (int i = 0; i < vehiculos.size(); i++) {
			Vehiculo vehiculo = vehiculos.get(i);
			String placaPehiculo = vehiculos.get(i).getPlaca();
			if (placaPehiculo.equals(placa)) {
				vehiculo.setExiste(false);
				return true;
			}

		}
		return false;
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

	public int diasReserva(String fechaRecoger, String fechaEntregar) {
		String[] partesRecoger = fechaRecoger.split("-");
		int diaRecoger = Integer.parseInt(partesRecoger[0]);
		int mesRecoger = Integer.parseInt(partesRecoger[1]);
		int anioRecoger = Integer.parseInt(partesRecoger[2]);
		int diasRecoger = diaRecoger + (mesRecoger * 30) + (anioRecoger * 365);
		String[] partesEntregar = fechaEntregar.split("-");
		int diaEntregar = Integer.parseInt(partesEntregar[0]);
		int mesEntregar = Integer.parseInt(partesEntregar[1]);
		int anioEntregar = Integer.parseInt(partesEntregar[2]);
		int diasEntregar = diaEntregar + (mesEntregar * 30) + (anioEntregar * 365);

		int diasTotales = diasEntregar - diasRecoger;
		return diasTotales;
	}

	public DatosCliente getDatosCliente(String usuario) {
		DatosCliente elCliente = null;
		for (int i = 0; i < clientes.size(); i++) {

			String userName = clientes.get(i).getDatosBasicos().getLogin();
			if (usuario.equals(userName)) {
				elCliente = clientes.get(i);
			}
		}
		System.out.println(elCliente.getDatosBasicos().getLogin());
		return elCliente;

	}

	public ArrayList<String> getDatosReserva(Reserva reserva) {
		String sedeRecoger = reserva.getSedeRecoger();
		String sedeEntregar = reserva.getSedeEntrega();
		String fechaRecoger = reserva.getFechaHoraRecoger();
		String rango = reserva.getRangoHoraEntrega();
		String fechaEntrega = reserva.getFechaEntrega();
		String seguro = reserva.getSeguro();
		String idReserva = String.valueOf(reserva.getIdReserva());
		String categoria = reserva.getCategoria();
		String loginCliente = reserva.getLoginCliente();
		String placa = reserva.getVehiculo().getPlaca();
		String precio = String.valueOf(reserva.getPrecioFinal());
		ArrayList<String> datos = new ArrayList<>();
		datos.add(idReserva);
		datos.add(sedeRecoger);
		datos.add(sedeEntregar);
		datos.add(fechaRecoger);
		datos.add(rango);
		datos.add(fechaEntrega);
		datos.add(seguro);
		datos.add(categoria);
		datos.add(loginCliente);
		datos.add(placa);
		datos.add(precio);

		return datos;

	}

	public void printSede() {
		System.out.print(sedes);
	}
}
