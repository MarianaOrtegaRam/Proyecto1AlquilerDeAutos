package alquilerAutos.pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import alquilerAutos.modelo.Administrador;
import alquilerAutos.sistema.SistemaAlquilerAutos;

public class TestCargadeDatos {

    @Test
    @DisplayName("Carga de Cliente")
    public void testCargaCliente() {
    	SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();
        assertEquals(true, sistema.nuevoCliente("Jose", "Jose.l@uniandes.edu.co", "12-02-03", "Colombia", "alejo7", "tres56",
                "colombia", "10020303", "27-02", "PayU", 2151617, "01/25", 124, "clave4"),
                "La carga de cliente debería ser exitosa");
    }

    @Test
    @DisplayName("Carga de empleado")
    public void testCargaEmpleado() {
    	SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();
        assertEquals(true, sistema.nuevoEmpleado("Jairo", "J.as@uniandes.edu.co", "12-02-02", "Colombia", "Jair23", "eso09"),
                "La carga de empleado debería ser exitosa");
    }
    
    @Test
    @DisplayName("Carga de seguro")
    public void testCargaSeguro() {
    	SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();
        assertEquals(true,sistema.nuevoSeguro("Proteccion contra choque", "300324", "protege contra cualquier choque involuntario")
        		, "La carga del seguro debería ser exitosa");
    }
    
    @Test
    @DisplayName("Carga de vehiculo")
    public void testvehiculo() {
    	SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();
        assertEquals(true,sistema.registrarNuevoVehiculo("AFG-124","Kia","mediano","Sienna","azul","3490000","4","8","transportemultiple",
        	"Aeropuerto Internacional","automovil"),"La carga de vehiculo debería ser exitosa");
    }
}
 