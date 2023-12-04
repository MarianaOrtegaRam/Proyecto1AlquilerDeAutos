package alquilerAutos.modelo;

public class Stripe extends MetodoDePago {

    public Stripe(int numTarjeta, String fechaVencimiento, int codigoSeguridad, String password) {
        super(numTarjeta, fechaVencimiento, codigoSeguridad, password);
    }

    @Override
    public int getNumTransaccion() {
        return this.numTransaccion;
    }

    @Override
    public void setEstadoTransaccion() {
        int numTrans = getNumTransaccion();
        if (numTrans % 2 == 0) {
            this.estadoTransccion = false;
        } else {
            this.estadoTransccion = true;
        }
    }

    @Override
    public boolean getEstadoTransaccion() {
        return this.estadoTransccion;
    }

    @Override
    public boolean verificarPago() {
        boolean estadoTrans = getEstadoTransaccion();
        if (estadoTrans == false) {
            return false;
        } else {
            return true;
        }
    }

}
