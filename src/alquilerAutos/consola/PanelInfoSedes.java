package alquilerAutos.consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.plugins.tiff.TIFFDirectory;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import alquilerAutos.consola.PanelCategoria;
import alquilerAutos.modelo.Vehiculo;
import alquilerAutos.sistema.SistemaAlquilerAutos;

public class PanelInfoSedes extends JPanel {

    SistemaAlquilerAutos sistema;
    JPanel panelSede1;
    JPanel panelSede2;
    JPanel panelSede3;

    public PanelInfoSedes(SistemaAlquilerAutos sistema) {
        this.sistema = sistema;
        ArrayList<Vehiculo> vehiculos = sistema.vehiculos;
        Map<String, ArrayList<String>> sedes = sistema.sedes;
        panelSede1 = new JPanel();
        panelSede2 = new JPanel();
        panelSede3 = new JPanel();
        setLayout(new GridLayout(3, 1));
        for (Map.Entry<String, ArrayList<String>> entry : sedes.entrySet()) {
            String sedeString = entry.getKey();

            ArrayList<String> listaPlacas = entry.getValue();
            if (sedeString.equals("Oficina Central")) {
                creacionPanelSedeOficina(sedeString, listaPlacas, vehiculos, "1");
            } else if (sedeString.equals("Aeropuerto Internacional")) {
                creacionPanelSedeOficina(sedeString, listaPlacas, vehiculos, "2");
            } else if (sedeString.equals("Zona Costera")) {
                creacionPanelSedeOficina(sedeString, listaPlacas, vehiculos, "3");
            }

        }

        add(panelSede1);
        add(panelSede2);
        add(panelSede3);
    }

    public void creacionPanelSedeOficina(String sede, ArrayList<String> listaPlacas, ArrayList<Vehiculo> vehiculos,
            String numSede) {
        Border border = BorderFactory.createLineBorder(Color.RED, 2);
        JPanel panelInformacionDatos = new JPanel();
        JPanel panelImagenC1 = new JPanel();
        panelImagenC1.setLayout(new BorderLayout());
        ImageIcon fotoVehiculo = new ImageIcon(
                new ImageIcon("./data/sede" + numSede + ".png").getImage().getScaledInstance(250, 150,
                        Image.SCALE_DEFAULT));

        JLabel temp = new JLabel();
        temp.setHorizontalAlignment(JLabel.CENTER);
        temp.setIcon(fotoVehiculo);
        panelImagenC1.add(temp, BorderLayout.CENTER);

        JLabel lblnombreSede1 = new JLabel("Nombre Sede  /  Categorias Disponibles");
        lblnombreSede1.setFont(new Font("Dialog", Font.BOLD, 12));
        lblnombreSede1.setHorizontalAlignment(JLabel.LEFT);
        JTextField txtNombreSede1 = new JTextField();
        txtNombreSede1.setEditable(false);
        txtNombreSede1.setFont(new Font("Dialog", Font.BOLD, 12));
        String categoriasString = sede + "   /   ";
        for (String placa : listaPlacas) {
            for (Vehiculo unVehiculo : vehiculos) {
                String unaPlaca = unVehiculo.getPlaca();
                if (placa.equals(unaPlaca)) {
                    String categoriaUnVehiculo = unVehiculo.getCategoria();
                    if (!categoriasString.contains(categoriaUnVehiculo)) {
                        categoriasString += categoriaUnVehiculo + ",";

                    }
                }
            }
        }
        txtNombreSede1.setText(categoriasString);
        panelInformacionDatos.setLayout(new GridLayout(2, 2));
        panelInformacionDatos.add(lblnombreSede1);
        panelInformacionDatos.add(txtNombreSede1);
        if (sede.equals("Oficina Central")) {
            panelSede1.setLayout(new BorderLayout());
            panelSede1.add(panelImagenC1, BorderLayout.CENTER);
            panelSede1.add(panelInformacionDatos, BorderLayout.SOUTH);
            panelSede1.setBorder(border);
        } else if (sede.equals("Aeropuerto Internacional")) {
            panelSede2.setLayout(new BorderLayout());
            panelSede2.add(panelImagenC1, BorderLayout.CENTER);
            panelSede2.add(panelInformacionDatos, BorderLayout.SOUTH);
            panelSede2.setBorder(border);
        } else if (sede.equals("Zona Costera")) {
            panelSede3.setLayout(new BorderLayout());
            panelSede3.add(panelImagenC1, BorderLayout.CENTER);
            panelSede3.add(panelInformacionDatos, BorderLayout.SOUTH);
            panelSede3.setBorder(border);
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    private static void createAndShowGUI() throws FileNotFoundException, IOException {
        JFrame frame = new JFrame("Ejemplo de Panel de Sedes");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SistemaAlquilerAutos sistema = new SistemaAlquilerAutos(); // Asegúrate de inicializar tu sistema aquí
        sistema.cargarInformacionVehiculos();
        sistema.cargarInformacionCliente();
        sistema.cargarInformacionEmpleado();
        sistema.cargarInformacionCondicionesCategoria();
        sistema.cargarInformacionSeguros();
        sistema.cargarInformacionSedes();
        sistema.cargarAdminSedes();
        PanelInfoSedes panelInfo = new PanelInfoSedes(sistema);
        frame.getContentPane().add(panelInfo);

        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar en la pantalla
        frame.setVisible(true);
    }

}
