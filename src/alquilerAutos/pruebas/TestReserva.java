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

public class TestReserva {
	
	@Test
    @DisplayName("Reserva")
	public void testResrevaNoAuto() {

	   SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();
	   assertEquals(true, sistema.crearReservaNoAuto("automovil", "Aeropuerto Internacional", "Aeropuerto Internacional", "12-10-2023", "3-5", "14-10-2023",
	                "Responsabilidad Civil", "alejo7", "ABC-123"),"La carga de cliente debería ser exitosa");
	    }
	@Test
    @DisplayName("Reserva")
	public void testResrevaAuto() {

	   SistemaAlquilerAutos sistema = new SistemaAlquilerAutos();
	   assertEquals(true, sistema.crearReserva("ABC-123","Kia","pequeño","Picanto","rojo","1100000","1","4","hola"),"La carga de cliente debería ser exitosa");
	    }
}

