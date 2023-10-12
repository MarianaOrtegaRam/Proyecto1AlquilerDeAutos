package alquilerAutos.modelo;

import java.util.ArrayList;

public class Sede {
    String nombreSede;
    String direccion;
    Categoria categoriasDisponibles;
    ArrayList<Vehiculo> vehiculosSede;

    public Sede(String nombreSede, String direccion) {
        this.nombreSede = nombreSede;
        this.direccion = direccion;
    }

    public void agregarVehiculo(Vehiculo carro) {
        vehiculosSede.add(carro);
    }

}
