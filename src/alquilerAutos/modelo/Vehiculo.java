package alquilerAutos.modelo;

import java.util.ArrayList;

public class Vehiculo {
    private String placa;
    private String marca;
    private String tamaño;
    private String modelo;
    private String color;
    private String caja;
    private int precioPorDia;
    private int maletas;
    private int capacidad;
    private boolean disponible;
    private Sede sede;
    private Categoria categoria;
    
    public Vehiculo(String placa, String marca, String tamaño, String modelo, String color, String caja,
			int precioPorDia, int maletas, int capacidad, boolean disponible) {
		
		this.placa = placa;
		this.marca = marca;
		this.tamaño = tamaño;
		this.modelo = modelo;
		this.color = color;
		this.caja = caja;
		this.precioPorDia = precioPorDia;
		this.maletas = maletas;
		this.capacidad = capacidad;
		this.disponible = disponible;
	}
    
    public void asignarTarifaDiaria(float precioPorCategoria){
    	
    }

	public String getPlaca() {
		return placa;
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
	
	public String getMarca() {
		return marca;
	}
	
	

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	public boolean verificarCondiciones(ArrayList<String> condiciones) {
		boolean cumple = false;
		for (String condition : condiciones) {
			String[] partes = condition.split("|");
			String atributo = partes[0];
			String segunda = partes[1];
			if (atributo == "maletas") {
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

			else if (atributo == "capacidad") {
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

			} else if (atributo == "precioPorDia") {
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
			}
			else if (atributo =="marca"){
				String marca = getMarca();
				if ((marca == "Mercedes-Benz")|| (marca =="BMV") || (marca=="Audi")||(marca=="Land Rover")|| (marca=="Tesla"))
				{
					cumple =true;
				}
			}

		}
		return cumple;
	}

	
    
    
}

