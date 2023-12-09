package alquilerAutos.sistema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.awt.*;



public class SistemaAlquilerAutos {

	public ArrayList<Reserva> reservas = new ArrayList<>();
	public ArrayList<Vehiculo> todosLosVehiculos = new ArrayList<>();
	public ArrayList<Vehiculo> vehiculos = new ArrayList<>();
	public ArrayList<Vehiculo> motos = new ArrayList<>();
	public ArrayList<Vehiculo> motoDeportiva = new ArrayList<>();
	public ArrayList<Vehiculo> patinetaElec = new ArrayList<>();
	public ArrayList<Vehiculo> bicicleta = new ArrayList<>();
	public ArrayList<Vehiculo> bicicletaElec = new ArrayList<>();
	public ArrayList<DatosCliente> clientes = new ArrayList<>();
	public ArrayList<DatosBasicos> empleados = new ArrayList<>();
	public ArrayList<Seguro> seguros = new ArrayList<>();
	public Map<String, ArrayList<String>> sedes = new HashMap<>();
	public Map<String, ArrayList<String>> sedesMoto = new HashMap<>();
	public Map<String, ArrayList<String>> sedesMotoDepor = new HashMap<>();
	public Map<String, ArrayList<String>> sedesPatinetaEle = new HashMap<>();
	public Map<String, ArrayList<String>> sedesBicicleta = new HashMap<>();
	public Map<String, ArrayList<String>> sedesBicicletaElec = new HashMap<>();
	public Map<String, String[]> condicionesCategoria = new HashMap<>();
	public Reserva reserva;
	public ArrayList<AdministradorSede> adminsedes = new ArrayList<>();

	public boolean nuevoCliente(String nombreCliente, String datoContactoCliente, String fechaNacimientoCliente,
			String nacionalidadCliente,
			String loginCliente, String contraseñaCliente, String paisLicenciaCliente, String numeroLicenciaCliente,
			String fechaVencimientoLicenciaCliente, String metodoDePago, int numTarjeta,
			String fechaVencimiento,
			int codigoSeguridad, String password) {

		InfoCliente infoCliente = new InfoCliente();
		DatosCliente cliente = infoCliente.crearCliente(nombreCliente, datoContactoCliente, fechaNacimientoCliente,
				nacionalidadCliente, loginCliente, contraseñaCliente, paisLicenciaCliente, numeroLicenciaCliente,
				fechaVencimientoLicenciaCliente, metodoDePago, numTarjeta,
				fechaVencimiento,
				codigoSeguridad, password);

		if (cliente != null) {
			this.clientes.add(cliente);
			return true; // Se registró exitosamente
		} else {
			return false; // No se pudo registrar (puedes agregar más lógica según sea necesario)
		}
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
			return true; 
		} else {
			return false; 
		}
	}

	public boolean registrarNuevoVehiculo(String placaVehiculo, String marcaVehiculo, String tamañoVehiculo,
			String modeloVehiculo, String colorVehiculo, String precioPorDiaVehiculo,
			String maletasVehiculo, String capacidadVehiculo, String categoriaVehiculo, String sedeVehiculo,
			String tipoVehiculo) {

		Administrador administrador = new Administrador();

		Vehiculo vehiculo = administrador.registrarNuevoVehiculo(placaVehiculo, marcaVehiculo, tamañoVehiculo,
				modeloVehiculo, colorVehiculo,
				precioPorDiaVehiculo, maletasVehiculo, capacidadVehiculo, categoriaVehiculo, sedeVehiculo,
				tipoVehiculo);

		if (vehiculo != null) {
			this.todosLosVehiculos.add(vehiculo);// editar
			if (tipoVehiculo.equals("automovil")) {
				this.vehiculos.add(vehiculo);
				String sede = vehiculo.getsede();
				// CAMBIAR PARA CADA SEDE
				ArrayList<String> sedeExiste = sedes.get(sede);
				if (sedeExiste == null) {
					ArrayList<String> carros = new ArrayList<>();
					carros.add(vehiculo.getPlaca());
					sedes.put(sede, carros);
				} else {
					ArrayList<String> lista = sedes.get(sede);
					lista.add(vehiculo.getPlaca());
				}
			} else if (tipoVehiculo.equals("moto")) {
				this.motos.add(vehiculo);
				String sede = vehiculo.getsede();
				// CAMBIAR PARA CADA SEDE
				ArrayList<String> sedeExiste = sedesMoto.get(sede);
				if (sedeExiste == null) {
					ArrayList<String> motos = new ArrayList<>();
					motos.add(vehiculo.getPlaca());
					sedesMoto.put(sede, motos);
				} else {
					ArrayList<String> lista = sedesMoto.get(sede);
					lista.add(vehiculo.getPlaca());
				}
			} else if (tipoVehiculo.equals("motocicleta deportiva")) {
				this.motoDeportiva.add(vehiculo);
				String sede = vehiculo.getsede();
				// CAMBIAR PARA CADA SEDE
				ArrayList<String> sedeExiste = sedesMotoDepor.get(sede);
				if (sedeExiste == null) {
					ArrayList<String> motosD = new ArrayList<>();
					motosD.add(vehiculo.getPlaca());
					sedesMotoDepor.put(sede, motosD);
				} else {
					ArrayList<String> lista = sedesMotoDepor.get(sede);
					lista.add(vehiculo.getPlaca());
				}
			} else if (tipoVehiculo.equals("bicicleta")) {
				this.bicicleta.add(vehiculo);
				String sede = vehiculo.getsede();
				// CAMBIAR PARA CADA SEDE
				ArrayList<String> sedeExiste = sedesBicicleta.get(sede);
				if (sedeExiste == null) {
					ArrayList<String> bici = new ArrayList<>();
					bici.add(vehiculo.getPlaca());
					sedesBicicleta.put(sede, bici);
				} else {
					ArrayList<String> lista = sedesBicicleta.get(sede);
					lista.add(vehiculo.getPlaca());
				}
			} else if (tipoVehiculo.equals("bicicleta electrica")) {
				this.bicicletaElec.add(vehiculo);
				String sede = vehiculo.getsede();
				// CAMBIAR PARA CADA SEDE
				ArrayList<String> sedeExiste = sedesBicicletaElec.get(sede);
				if (sedeExiste == null) {
					ArrayList<String> biciE = new ArrayList<>();
					biciE.add(vehiculo.getPlaca());
					sedesBicicletaElec.put(sede, biciE);
				} else {
					ArrayList<String> lista = sedesBicicletaElec.get(sede);
					lista.add(vehiculo.getPlaca());
				}
			} else if (tipoVehiculo.equals("patineta electrica")) {
				this.patinetaElec.add(vehiculo);
				String sede = vehiculo.getsede();
				// CAMBIAR PARA CADA SEDE
				ArrayList<String> sedeExiste = sedesPatinetaEle.get(sede);
				if (sedeExiste == null) {
					ArrayList<String> patiE = new ArrayList<>();
					patiE.add(vehiculo.getPlaca());
					sedesPatinetaEle.put(sede, patiE);
				} else {
					ArrayList<String> lista = sedesPatinetaEle.get(sede);
					lista.add(vehiculo.getPlaca());
				}
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
			String metodoDePago = partes[9];
			int numTarjeta = Integer.parseInt(partes[10]);
			String fechaVencimientoTarjeta = partes[11];
			int codigoSeguridad = Integer.parseInt(partes[12]);
			String contraseñaTarjeta = partes[13];

			DatosBasicos datosBasicos = new DatosBasicos(nombre, datoContacto, fechaNacimiento, nacionalidad, login,
					contraseña, "cliente");
			DatosLicencia datosLicencia = new DatosLicencia(pais, Integer.parseInt(numero), fechaVencimiento);

			DatosCliente datosCliente = new DatosCliente(datosBasicos, datosLicencia, metodoDePago, numTarjeta,
					fechaVencimientoTarjeta, codigoSeguridad, contraseñaTarjeta);
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
			// String caja = partes[5]; una cicla no tiene caja
			int precioPorDia = Integer.parseInt(partes[5]);
			int maletas = Integer.parseInt(partes[6]);
			int capacidad = Integer.parseInt(partes[7]);
			boolean disponible;
			String disponibilidadDoc = partes[8];
			if (disponibilidadDoc.equals("true")) {
				disponible = true;
			} else {
				disponible = false;
			}
			String categoria = partes[9];
			String sede = partes[10];
			String tipoVehiculo = partes[11];

			Vehiculo elvehiculo = new Vehiculo(placa, marca, tamaño, modelo, color, precioPorDia, maletas,
					capacidad, disponible, categoria, sede, tipoVehiculo);
			this.vehiculos.add(elvehiculo);
			this.todosLosVehiculos.add(elvehiculo);
			linea = br.readLine();
		}

		br.close();

	}

	public void cargarInformacionOtros() throws FileNotFoundException, IOException {
		ArrayList<String> listaTipos = new ArrayList<>();
		listaTipos.add("moto");
		listaTipos.add("motocicleta deportiva");
		listaTipos.add("bicicleta");
		listaTipos.add("bicicleta electrica");
		listaTipos.add("patineta electrica");
		for (String tipoEsp : listaTipos) {

			BufferedReader br = new BufferedReader(new FileReader("./inventario/" + tipoEsp + ".txt"));
			String linea = br.readLine();

			while (linea != null) {

				String[] partes = linea.split(";");
				String placa = partes[0];
				String marca = partes[1];
				String tamaño = partes[2];
				String modelo = partes[3];
				String color = partes[4];
				int precioPorDia = Integer.parseInt(partes[5]);
				int maletas = Integer.parseInt(partes[6]);
				int capacidad = Integer.parseInt(partes[7]);
				boolean disponible;
				String disponibilidadDoc = partes[8];
				if (disponibilidadDoc.equals("true")) {
					disponible = true;
				} else {
					disponible = false;
				}
				String categoria = partes[9];
				String sede = partes[10];
				String tipoVehiculo = partes[11];

				Vehiculo elvehiculo = new Vehiculo(placa, marca, tamaño, modelo, color, precioPorDia, maletas,
						capacidad, disponible, categoria, sede, tipoVehiculo);
				if (tipoEsp.equals("moto")) {
					this.motos.add(elvehiculo);
				} else if (tipoEsp.equals("motocicleta deportiva")) {
					this.motoDeportiva.add(elvehiculo);
				} else if (tipoEsp.equals("bicicleta")) {
					this.bicicleta.add(elvehiculo);
				} else if (tipoEsp.equals("bicicleta electrica")) {
					this.bicicletaElec.add(elvehiculo);
				} else if (tipoEsp.equals("patineta electrica")) {
					this.patinetaElec.add(elvehiculo);
				}
				this.todosLosVehiculos.add(elvehiculo);
				linea = br.readLine();
			}

			br.close();
		}

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

	public void cargarInformacionSedesOtras() throws FileNotFoundException, IOException {
		ArrayList<String> listaTipos = new ArrayList<>();
		listaTipos.add("moto");
		listaTipos.add("motocicleta deportiva");
		listaTipos.add("bicicleta");
		listaTipos.add("bicicleta electrica");
		listaTipos.add("patineta electrica");
		for (String tipoEsp : listaTipos) {
			BufferedReader br = new BufferedReader(new FileReader("./inventario/sedes" + tipoEsp + ".txt"));
			String linea = br.readLine();

			while (linea != null) {

				String[] partes = linea.split(";");
				String nombreSede = partes[0];
				String placa = partes[1];

				if (tipoEsp.equals("moto")) {
					ArrayList<String> sedeExiste = sedesMoto.get(nombreSede);
					if (sedeExiste == null) {
						ArrayList<String> motos = new ArrayList<>();
						motos.add(placa);
						sedesMoto.put(nombreSede, motos);
					} else {
						ArrayList<String> lista = sedesMoto.get(nombreSede);
						lista.add(placa);
					}
				}

				else if (tipoEsp.equals("motocicleta deportiva")) {
					ArrayList<String> sedeExiste = sedesMotoDepor.get(nombreSede);
					if (sedeExiste == null) {
						ArrayList<String> motos = new ArrayList<>();
						motos.add(placa);
						sedesMotoDepor.put(nombreSede, motos);
					} else {
						ArrayList<String> lista = sedesMotoDepor.get(nombreSede);
						lista.add(placa);
					}
				}

				else if (tipoEsp.equals("bicicleta")) {
					ArrayList<String> sedeExiste = sedesBicicleta.get(nombreSede);
					if (sedeExiste == null) {
						ArrayList<String> bicis = new ArrayList<>();
						bicis.add(placa);
						sedesBicicleta.put(nombreSede, bicis);
					} else {
						ArrayList<String> lista = sedesBicicleta.get(nombreSede);
						lista.add(placa);
					}
				}

				else if (tipoEsp.equals("bicicleta electrica")) {
					ArrayList<String> sedeExiste = sedesBicicletaElec.get(nombreSede);
					if (sedeExiste == null) {
						ArrayList<String> bicis = new ArrayList<>();
						bicis.add(placa);
						sedesBicicletaElec.put(nombreSede, bicis);
					} else {
						ArrayList<String> lista = sedesBicicletaElec.get(nombreSede);
						lista.add(placa);
					}
				}

				else if (tipoEsp.equals("patineta electrica")) {
					ArrayList<String> sedeExiste = sedesPatinetaEle.get(nombreSede);
					if (sedeExiste == null) {
						ArrayList<String> patineta = new ArrayList<>();
						patineta.add(placa);
						sedesPatinetaEle.put(nombreSede, patineta);
					} else {
						ArrayList<String> lista = sedesPatinetaEle.get(nombreSede);
						lista.add(placa);
					}
				}

				linea = br.readLine();
			}

			br.close();
		}

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
			String tipoVehiculo = partes[0];
			String sedeRecoger = partes[1];
			String sedeEntrega = partes[2];
			String fechaHoraRecoger = partes[3];
			String rangoHoraEntrega = partes[4];
			String fechaEntrega = partes[5];
			String seguro = partes[6];
			String login = partes[7];
			String placa = partes[8];

			Reserva laReserva = new Reserva(tipoVehiculo, sedeRecoger, sedeEntrega, fechaHoraRecoger, rangoHoraEntrega,
					fechaEntrega,
					seguro);
			laReserva.setLoginCliente(login);
			for (Vehiculo unVehiculo : todosLosVehiculos) {
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

	public Reserva crearReservaNoAuto(String tipoVehiculo, String sedeRecogerVehiculo, String sedeEntregarrVehiculo,
			String fechaHoraRecogerVehiculo, String rangoHoraRecogerVehiculo, String fechaEntregaVehiculo,
			String seguroVehiculo, String categoriaVehiculo, String login) {

		Reserva reserva = new Reserva(tipoVehiculo, sedeRecogerVehiculo, sedeEntregarrVehiculo,
				fechaHoraRecogerVehiculo, rangoHoraRecogerVehiculo, fechaEntregaVehiculo, seguroVehiculo);
		reserva.setLoginCliente(login);
		Vehiculo vehiculoReserva = null;
		if (tipoVehiculo.equals("moto")) {
			ArrayList<String> listaPlacas = sedesMoto.get(sedeRecogerVehiculo);
			for (int i = 0; i < listaPlacas.size(); i++) {
				String unaPlaca = listaPlacas.get(i);
				for (int j = 0; j < this.motos.size(); j++) {
					Vehiculo unaMoto = motos.get(j);
					String placaMoto = unaMoto.getPlaca();
					if ((unaPlaca.equals(placaMoto)) & (unaMoto.getDisponibilidad())) {
						vehiculoReserva = unaMoto;
					}
				}

			}

		} else if (tipoVehiculo.equals("motocicleta deportiva")) {
			ArrayList<String> listaPlacas = sedesMotoDepor.get(sedeRecogerVehiculo);
			for (int i = 0; i < listaPlacas.size(); i++) {
				String unaPlaca = listaPlacas.get(i);
				for (int j = 0; j < this.motoDeportiva.size(); j++) {
					Vehiculo unaMotoD = motoDeportiva.get(j);
					String placaMotoD = unaMotoD.getPlaca();
					if ((unaPlaca.equals(placaMotoD)) & (unaMotoD.getDisponibilidad()))
						vehiculoReserva = unaMotoD;
				}
			}

		} else if (tipoVehiculo.equals("bicicleta")) {
			ArrayList<String> listaPlacas = sedesBicicleta.get(sedeRecogerVehiculo);
			for (int i = 0; i < listaPlacas.size(); i++) {
				String unaPlaca = listaPlacas.get(i);
				for (int j = 0; j < this.bicicleta.size(); j++) {
					Vehiculo unaBici = bicicleta.get(j);
					String placaBici = unaBici.getPlaca();
					if ((unaPlaca.equals(placaBici)) & (unaBici.getDisponibilidad())) {
						vehiculoReserva = unaBici;
					}
				}

			}
		} else if (tipoVehiculo.equals("bicicleta electrica")) {
			ArrayList<String> listaPlacas = sedesBicicletaElec.get(sedeRecogerVehiculo);
			for (int i = 0; i < listaPlacas.size(); i++) {
				String unaPlaca = listaPlacas.get(i);
				for (int j = 0; j < this.bicicletaElec.size(); j++) {
					Vehiculo unaBiciE = bicicletaElec.get(j);
					String placaBiciE = unaBiciE.getPlaca();
					if ((unaPlaca.equals(placaBiciE)) & (unaBiciE.getDisponibilidad())) {
						vehiculoReserva = unaBiciE;
					}
				}

			}
		} else if (tipoVehiculo.equals("patineta electrica")) {
			ArrayList<String> listaPlacas = sedesPatinetaEle.get(sedeRecogerVehiculo);
			for (int i = 0; i < listaPlacas.size(); i++) {
				String unaPlaca = listaPlacas.get(i);
				for (int j = 0; j < this.patinetaElec.size(); j++) {
					Vehiculo unaPatineta = patinetaElec.get(j);
					String placaPatineta = unaPatineta.getPlaca();
					if ((unaPlaca.equals(placaPatineta)) & (unaPatineta.getDisponibilidad())) {
						vehiculoReserva = unaPatineta;
					}
				}

			}
		}

		int diasReserva = diasReserva(fechaHoraRecogerVehiculo, fechaEntregaVehiculo);
		if (vehiculoReserva != null) {
			reserva.setVehiculo(vehiculoReserva);
			vehiculoReserva.setDisponible(false);
			reserva.setCategoriaSeleccionada("na");
			int precioFinalReserva = calucularTarifas("na", vehiculoReserva, diasReserva);
			reserva.setPrecioFinal(precioFinalReserva);
			// System.out.println("Reserva exitosa!");
			reserva.registrarReserva(reserva);
			reservas.add(reserva);
			setReserva(login, reserva);
		}
		return reserva;
	}

	public Reserva crearReserva(String tipoVehiculo, String sedeRecogerVehiculo, String sedeEntregarrVehiculo,
			String fechaHoraRecogerVehiculo, String rangoHoraRecogerVehiculo, String fechaEntregaVehiculo,
			String seguroVehiculo, String categoriaVehiculo, String login) {

		Reserva reserva = new Reserva(tipoVehiculo, sedeRecogerVehiculo, sedeEntregarrVehiculo,
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
			// System.out.println("Reserva exitosa!");
			reserva.registrarReserva(reserva);
			reservas.add(reserva);
			setReserva(login, reserva);

		} else if (candidato != null) {
			reserva.setVehiculo(candidato);
			String categoria_ = candidato.getCategoria();
			reserva.setCategoriaSeleccionada(categoria_);
			int precioFinalReserva = calucularTarifas(categoria_, candidato, diasReserva);
			reserva.setPrecioFinal(precioFinalReserva);
			// System.out.println(
			// "No se encontro disponible la categoria deseada, sin embargo, este vehiculo
			// cumple sus nececidades");
			reserva.registrarReserva(reserva);
			reservas.add(reserva);
			setReserva(login, reserva);
			candidato.setDisponible(false);
		} else {
			reserva = null;
			// System.out.println("No se pudo realizar la reserva");
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

		int porcentajeAdicional = (carro.getTipoVehiculo()).getPorcentajeAdicional();
		int precioInicial = ((carro.getPrecioPorDia()) * porcentajeAdicional) * dias;
		int excedente = 0;
		int precioFinal = 0;

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
		} else if (nombreCategoria.equals("na")) {

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

	// CAMBAIRLO
	public boolean cambiarVehiculoSede(String placa, String sede) {
		for (Vehiculo vehiculoesp : todosLosVehiculos) {
			String placaUna = vehiculoesp.getPlaca();
			if (placaUna.equals(placa)) {
				String tipoVehiculo = vehiculoesp.getTipoVehiculo().getNombreTipo();
				for (int i = 0; i < adminsedes.size(); i++) {
					String sede_ = adminsedes.get(i).getSede();
					if (sede_.equals(sede)) {
						AdministradorSede adminSede = adminsedes.get(i);
						if (tipoVehiculo.equals("automovil")) {
							adminSede.cambiarSede(placa, sede, sedes);
							return true;
						} else if (tipoVehiculo.equals("moto")) {
							adminSede.cambiarSede(placa, sede, sedesMoto);
							return true;
						} else if (tipoVehiculo.equals("motocicleta deportiva")) {
							adminSede.cambiarSede(placa, sede, sedesMotoDepor);
							return true;
						} else if (tipoVehiculo.equals("bicicleta")) {
							adminSede.cambiarSede(placa, sede, sedesBicicleta);
							return true;
						} else if (tipoVehiculo.equals("bicicleta electrica")) {
							adminSede.cambiarSede(placa, sede, sedesBicicletaElec);
							return true;
						} else if (tipoVehiculo.equals("patineta electrica")) {
							adminSede.cambiarSede(placa, sede, sedesPatinetaEle);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean eliminarVehiculo(String placa, String Tipo) {
		AdministradorSede adminsede = new AdministradorSede("n", "n", "n", "n", "n");
		String tipo = Tipo.toLowerCase();
		boolean vehiculoEliminado = false;
		
		if("bicicleta".equals(tipo)) {
			vehiculoEliminado = adminsede.eliminarVehiculo(placa, "bicicleta");
		}else if("bicicleta electrica".equals(tipo)){
			vehiculoEliminado = adminsede.eliminarVehiculo(placa, "bicicleta electrica");
		}else if("moto".equals(tipo)){
			vehiculoEliminado = adminsede.eliminarVehiculo(placa, "moto");
		}else if("motocicleta deportiva".equals(tipo)){
			vehiculoEliminado = adminsede.eliminarVehiculo(placa, "motocicleta deportiva");
		}else if("patineta electrica".equals(tipo)){
			vehiculoEliminado = adminsede.eliminarVehiculo(placa, "patineta electrica");
		}else if("automovil".equals(tipo)){
			vehiculoEliminado = adminsede.eliminarVehiculo(placa, "vehiculos");
	}
		return vehiculoEliminado;
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

		return elCliente;

	}
	
	
	  public void crearReciboPDF(String id, String sedeRecoger, String sedeEntrega,
	  String fechaRecoger, String rangoHoraEntrega, String fechaEntrega, String
	  seguro, String categoriaDeseada){ Document documento = new Document();
	  
	  try {
	  
	  PdfWriter.getInstance(documento, new
	  FileOutputStream("recibosPDF/recibo"+id+".pdf")); documento.open();
	  documento.add(new Paragraph("Sede Recoger: "+sedeRecoger)); documento.add(new
	  Paragraph("Sede Entrega: "+sedeEntrega)); documento.add(new
	  Paragraph("Fecha a recoger: "+fechaRecoger)); documento.add(new
	  Paragraph("Rango de horas entrega: "+rangoHoraEntrega)); documento.add(new
	  Paragraph("Fecha a entregar: "+fechaEntrega)); documento.add(new
	  Paragraph("Seguro: "+seguro)); documento.add(new
	  Paragraph("Categoria: "+categoriaDeseada));
	  
	  
	  } catch (DocumentException | FileNotFoundException e) { ((Throwable)
	  e).printStackTrace(); } finally { if (documento != null &&
	  documento.isOpen()) { documento.close(); } } }
	 

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
