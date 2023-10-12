package alquilerAutos.modelo;

public class Vehiculo {
    String placa;
    String marca;
    String tamaño;
    String modelo;
    String color;
    String caja;
    float precioPorDia;
    int maletas;
    int capacidad;
    boolean disponible;
    
    public Vehiculo(String placa, String marca, String tamaño, String modelo, String color, String caja,
			float precioPorDia, int maletas, int capacidad, boolean disponible) {
		
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

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	
    
    
}

