package alquilerAutos.consola;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReservaIncorrecta extends JFrame {

    public ReservaIncorrecta() {

        setTitle("Reserva Fallida");
        JPanel mensajeError = new JPanel();
        mensajeError.add(new JLabel("Intente de nuevo!"));
        add(mensajeError);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Crear una instancia de la ventana ReservaIncorrecta y mostrarla
        ReservaIncorrecta ventanaError = new ReservaIncorrecta();
    }
}
