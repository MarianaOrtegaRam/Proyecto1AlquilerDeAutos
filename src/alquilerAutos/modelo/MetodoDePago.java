package alquilerAutos.modelo;

import java.util.Random;

public abstract class MetodoDePago {
    public int numTarjeta;
    public String fechaVencimiento;
    public int codigoSeguridad;
    public String password;
    public int numTransaccion;
    public boolean estadoTransccion;

    public MetodoDePago(int numTarjeta, String fechaVencimiento, int codigoSeguridad, String password) {
        this.codigoSeguridad = codigoSeguridad;
        this.fechaVencimiento = fechaVencimiento;
        this.numTarjeta = numTarjeta;
        Random rand = new Random();
        this.numTransaccion = rand.nextInt(1000000);
        this.password = password;
    }

    public abstract int getNumTransaccion();

    public abstract void setEstadoTransaccion();

    public abstract boolean getEstadoTransaccion();

    public abstract boolean verificarPago();
}
