package alquilerAutos.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Vehiculo {
	private String placa;
	private String marca;
	private String tamaño;
	private String modelo;
	private String color;
	private int precioPorDia;
	private int maletas;
	private int capacidad;
	private boolean disponible;
	private String categoria;
	private String sede;
	private boolean existe;
	private TipoVehiculo tipoVehiculo;

	public Vehiculo(String placa, String marca, String tamaño, String modelo, String color,
			int precioPorDia, int maletas, int capacidad, boolean disponible, String categoria, String sede,
			String tipoVehiculo) {

		this.placa = placa;
		this.marca = marca;
		this.tamaño = tamaño;
		this.modelo = modelo;
		this.color = color;
		// this.caja = caja;
		this.precioPorDia = precioPorDia;
		this.maletas = maletas;
		this.capacidad = capacidad;
		this.disponible = disponible;
		this.categoria = categoria;
		this.sede = sede;
		this.existe = true;
		this.tipoVehiculo = new TipoVehiculo(tipoVehiculo);
	}

	public void asignarTarifaDiaria(float precioPorCategoria) {

	}

	public String getPlaca() {
		return placa;
	}

	public String getsede() {
		return sede;
	}

	public String getTamaño() {
		return tamaño;
	}

	public int getMaletas() {
		return maletas;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public int getPrecioPorDia() {
		return precioPorDia;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getMarca() {
		return marca;
	}

	public boolean getDisponibilidad() {
		return disponible;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public boolean verificarCondiciones(String[] condiciones) {
		boolean cumple = false;
		for (int i = 0; i < condiciones.length; i++) {

			String condition = condiciones[i];
			String[] partes = condition.split("A");
			String atributo = partes[0];
			String segunda = partes[1];
			if (atributo.equals("maletas")) {
				int maletasVehiculo = getMaletas();
				if (segunda.contains("/")) {
					String[] rango = segunda.split("/");
					int minimo = Integer.parseInt(rango[0]);
					int maximo = Integer.parseInt(rango[1]);
					if (maletasVehiculo >= minimo && maletasVehiculo <= maximo) {
						cumple = true;
					}
				} else {
					if (maletasVehiculo == Integer.parseInt(segunda)) {
						cumple = true;
					}
				}

			}

			else if (atributo.equals("capacidad")) {
				int capacidadVehiculo = getCapacidad();
				if (segunda.contains("/")) {
					String[] rango = segunda.split("/");
					int minimo = Integer.parseInt(rango[0]);
					int maximo = Integer.parseInt(rango[1]);
					if (capacidadVehiculo >= minimo && capacidadVehiculo <= maximo) {
						cumple = true;
					}
				} else {
					if (capacidadVehiculo == Integer.parseInt(segunda)) {
						cumple = true;
					}
				}

			} else if (atributo.equals("precioPorDia")) {
				int precioPorDia = getPrecioPorDia();

				if (segunda.contains("/")) {
					String[] rango = segunda.split("/");
					int minimo = Integer.parseInt(rango[0]);
					int maximo = Integer.parseInt(rango[1]);
					if (precioPorDia >= minimo && precioPorDia <= maximo) {
						cumple = true;
					}
				} else if (segunda.contains("max")) {
					String precio = segunda.substring(4);
					if (precioPorDia <= Integer.parseInt(precio)) {
						cumple = true;
					}
				}
			} else if (atributo.equals("marca")) {
				String marca = getMarca();
				if ((marca.equals("mercedes-benz")) || (marca.equals("mercedes-benz")) || (marca.equals("audi"))
						|| (marca.equals("land rover")) || (marca.equals("tesla"))) {
					cumple = true;
				}
			}

		}
		return cumple;
	}
}
