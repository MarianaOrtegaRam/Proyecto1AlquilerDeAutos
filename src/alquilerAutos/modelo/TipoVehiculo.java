package alquilerAutos.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TipoVehiculo {
    String nombreTipo;
    float porcentajeAdicional;
    int nivelPeligro;

    public TipoVehiculo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
        setNivelPeligro();
        setPorcentajeAdicional();
    }

    public void setNivelPeligro() {
        if ((nombreTipo.equals("automovil")) || (nombreTipo.equals("moto")) || (nombreTipo.equals("atv"))
                || (nombreTipo.equals("motocicleta deportiva"))) {
            this.nivelPeligro = 3;
        } else if ((nombreTipo.equals("bicicleta")) || (nombreTipo.equals("bicicleta electrica"))) {
            this.nivelPeligro = 2;
        } else if (nombreTipo.equals("patineta electrica")) {
            this.nivelPeligro = 1;
        }
    }

    public void setPorcentajeAdicional() {
        if (nivelPeligro == 1) {
            this.porcentajeAdicional = 10;
        } else if (nivelPeligro == 2) {
            this.porcentajeAdicional = 20;
        } else if (nivelPeligro == 3) {
            this.porcentajeAdicional = 30;
        }
    }

    public float getPorcentajeAdicional() {
        return porcentajeAdicional;
    }

    public String getNombreTipo() {
        return this.nombreTipo;
    }
}
