package alquilerAutos.consola;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import alquilerAutos.sistema.SistemaAlquilerAutos;

public class PanelCategoria extends JPanel {

    SistemaAlquilerAutos sistema;
    JPanel panelEconomico;
    JPanel panelIntermedio;
    JPanel panelTransporteMultiple;
    JPanel panelVehiculoLujo;
    JPanel panelCategorias;

    public PanelCategoria(SistemaAlquilerAutos sistema) {

        this.sistema = sistema;
        this.panelEconomico = new JPanel();
        this.panelIntermedio = new JPanel();
        this.panelTransporteMultiple = new JPanel();
        this.panelVehiculoLujo = new JPanel();
        setLayout(new GridLayout(2, 2));
        // this.panelCategorias = new JPanel();
        // panelCategorias.setLayout(new GridLayout(0, 2));

        Map<String, String[]> mapaCategoriaCondiciones = sistema.condicionesCategoria;
        for (Map.Entry<String, String[]> entry : mapaCategoriaCondiciones.entrySet()) {
            String categoria = entry.getKey();
            String[] condicionesBasicas = entry.getValue();
            if (categoria.equals("economico")) {
                creacionPanelEconomico(categoria, condicionesBasicas);
            } else if (categoria.equals("intermedio")) {
                creacionPanelIntermedio(categoria, condicionesBasicas);
            } else if (categoria.equals("transportemultiple")) {
                creacionPanelTransporteMultiple(categoria, condicionesBasicas);
            } else if (categoria.equals("vehiculolujo")) {
                creacionVehiculoLujo(categoria, condicionesBasicas);
            }
        }

        add(panelEconomico);
        add(panelIntermedio);
        add(panelTransporteMultiple);
        add(panelVehiculoLujo);

    }

    public void creacionPanelEconomico(String categoria, String[] condiciones) {
        Border border = BorderFactory.createLineBorder(Color.RED, 2);
        panelEconomico.setLayout(new BorderLayout());
        JLabel lblnombreCategoria = new JLabel("Categoria:");
        lblnombreCategoria.setHorizontalAlignment(JLabel.LEFT);
        JLabel lblcapacidadVehiculo = new JLabel("Capacidad:");
        lblcapacidadVehiculo.setHorizontalAlignment(JLabel.LEFT);
        JLabel lblprecioPorDia = new JLabel("Precio por Dia:");
        lblprecioPorDia.setHorizontalAlignment(JLabel.LEFT);

        JTextField txtnombreCategoria = new JTextField();
        txtnombreCategoria.setEditable(false);
        txtnombreCategoria.setFont(new Font("Dialog", Font.BOLD, 12));
        txtnombreCategoria.setText(categoria);

        JTextField txtcapacidadVehiculo = new JTextField();
        txtcapacidadVehiculo.setEditable(false);
        txtcapacidadVehiculo.setFont(new Font("Dialog", Font.BOLD, 12));

        JTextField txtprecioPorDia = new JTextField();
        txtprecioPorDia.setEditable(false);
        txtprecioPorDia.setFont(new Font("Dialog", Font.BOLD, 12));

        JPanel panelInformacionDatos = new JPanel();
        JPanel panelImagenC1 = new JPanel();
        panelImagenC1.setLayout(new BorderLayout());
        ImageIcon fotoVehiculo = new ImageIcon(
                new ImageIcon("./data/economico.png").getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));

        JLabel temp = new JLabel();
        temp.setHorizontalAlignment(JLabel.CENTER);
        temp.setIcon(fotoVehiculo);
        panelImagenC1.add(temp, BorderLayout.CENTER);
        panelEconomico.add(panelImagenC1, BorderLayout.CENTER);

        for (String condicion : condiciones) {
            String[] partes = condicion.split("A");
            String atributo = partes[0];
            String explicacion = partes[1];
            if (atributo.equals("capacidad")) {
                txtcapacidadVehiculo.setText(explicacion);
            } else if (atributo.equals("precioPorDia")) {
                txtprecioPorDia.setText(explicacion);
            }
        }

        panelInformacionDatos.setLayout(new GridLayout(3, 2));
        panelInformacionDatos.add(lblnombreCategoria);
        panelInformacionDatos.add(txtnombreCategoria);
        panelInformacionDatos.add(lblcapacidadVehiculo);
        panelInformacionDatos.add(txtcapacidadVehiculo);
        panelInformacionDatos.add(lblprecioPorDia);
        panelInformacionDatos.add(txtprecioPorDia);
        panelEconomico.add(panelInformacionDatos, BorderLayout.SOUTH);
        panelEconomico.setBorder(border);

    }

    public void creacionPanelIntermedio(String categoria, String[] condiciones) {
        Border border = BorderFactory.createLineBorder(Color.RED, 2);
        panelIntermedio.setLayout(new BorderLayout());
        JLabel lblnombreCategoria = new JLabel("Categoria:");
        lblnombreCategoria.setHorizontalAlignment(JLabel.LEFT);
        JLabel lblcapacidadVehiculo = new JLabel("Capacidad:");
        lblcapacidadVehiculo.setHorizontalAlignment(JLabel.LEFT);
        JLabel lblprecioPorDia = new JLabel("Precio por Dia:");
        lblprecioPorDia.setHorizontalAlignment(JLabel.LEFT);
        ;
        JLabel lblmaletas = new JLabel("Maletas:");
        lblmaletas.setHorizontalAlignment(JLabel.LEFT);

        JTextField txtnombreCategoria = new JTextField();
        txtnombreCategoria.setEditable(false);
        txtnombreCategoria.setFont(new Font("Dialog", Font.BOLD, 12));
        txtnombreCategoria.setText(categoria);

        JTextField txtcapacidadVehiculo = new JTextField();
        txtcapacidadVehiculo.setEditable(false);
        txtcapacidadVehiculo.setFont(new Font("Dialog", Font.BOLD, 12));

        JTextField txtprecioPorDia = new JTextField();
        txtprecioPorDia.setEditable(false);
        txtprecioPorDia.setFont(new Font("Dialog", Font.BOLD, 12));

        JTextField txtmaletas = new JTextField();
        txtmaletas.setEditable(false);
        txtmaletas.setFont(new Font("Dialog", Font.BOLD, 12));

        JPanel panelInformacionDatos = new JPanel();
        JPanel panelImagenC2 = new JPanel();
        panelImagenC2.setLayout(new BorderLayout());
        ImageIcon fotoVehiculo = new ImageIcon(
                new ImageIcon("./data/intermedio.png").getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));

        JLabel temp = new JLabel();
        temp.setHorizontalAlignment(JLabel.CENTER);
        temp.setIcon(fotoVehiculo);
        panelImagenC2.add(temp, BorderLayout.CENTER);
        panelIntermedio.add(panelImagenC2, BorderLayout.CENTER);

        for (String condicion : condiciones) {
            String[] partes = condicion.split("A");
            String atributo = partes[0];
            String explicacion = partes[1];
            if (atributo.equals("capacidad")) {
                txtcapacidadVehiculo.setText(explicacion);
            } else if (atributo.equals("precioPorDia")) {
                txtprecioPorDia.setText(explicacion);
            } else if (atributo.equals("maletas")) {
                txtmaletas.setText(explicacion);
            }
        }

        panelInformacionDatos.setLayout(new GridLayout(4, 2));
        panelInformacionDatos.add(lblnombreCategoria);
        panelInformacionDatos.add(txtnombreCategoria);
        panelInformacionDatos.add(lblcapacidadVehiculo);
        panelInformacionDatos.add(txtcapacidadVehiculo);
        panelInformacionDatos.add(lblmaletas);
        panelInformacionDatos.add(txtmaletas);
        panelInformacionDatos.add(lblprecioPorDia);
        panelInformacionDatos.add(txtprecioPorDia);
        panelIntermedio.add(panelInformacionDatos, BorderLayout.SOUTH);
        panelIntermedio.setBorder(border);
    }

    public void creacionPanelTransporteMultiple(String categoria, String[] condiciones) {
        Border border = BorderFactory.createLineBorder(Color.RED, 2);
        panelTransporteMultiple.setLayout(new BorderLayout());
        JLabel lblnombreCategoria = new JLabel("Categoria:");
        lblnombreCategoria.setHorizontalAlignment(JLabel.LEFT);
        JLabel lblcapacidadVehiculo = new JLabel("Capacidad:");
        lblcapacidadVehiculo.setHorizontalAlignment(JLabel.LEFT);
        JLabel lblmaletas = new JLabel("Maletas:");
        lblmaletas.setHorizontalAlignment(JLabel.LEFT);

        JTextField txtnombreCategoria = new JTextField();
        txtnombreCategoria.setEditable(false);
        txtnombreCategoria.setFont(new Font("Dialog", Font.BOLD, 12));
        txtnombreCategoria.setText(categoria);

        JTextField txtcapacidadVehiculo = new JTextField();
        txtcapacidadVehiculo.setEditable(false);
        txtcapacidadVehiculo.setFont(new Font("Dialog", Font.BOLD, 12));

        JTextField txtmaletas = new JTextField();
        txtmaletas.setEditable(false);
        txtmaletas.setFont(new Font("Dialog", Font.BOLD, 12));

        JPanel panelInformacionDatos = new JPanel();
        JPanel panelImagenC2 = new JPanel();
        panelImagenC2.setLayout(new BorderLayout());
        ImageIcon fotoVehiculo = new ImageIcon(
                new ImageIcon("./data/transportemultiple.png").getImage().getScaledInstance(250, 150,
                        Image.SCALE_DEFAULT));

        JLabel temp = new JLabel();
        temp.setHorizontalAlignment(JLabel.CENTER);
        temp.setIcon(fotoVehiculo);
        panelImagenC2.add(temp, BorderLayout.CENTER);
        panelTransporteMultiple.add(panelImagenC2, BorderLayout.CENTER);

        for (String condicion : condiciones) {
            String[] partes = condicion.split("A");
            String atributo = partes[0];
            String explicacion = partes[1];
            if (atributo.equals("capacidad")) {
                txtcapacidadVehiculo.setText(explicacion);
            } else if (atributo.equals("maletas")) {
                txtmaletas.setText(explicacion);
            }
        }

        panelInformacionDatos.setLayout(new GridLayout(3, 2));
        panelInformacionDatos.add(lblnombreCategoria);
        panelInformacionDatos.add(txtnombreCategoria);
        panelInformacionDatos.add(lblcapacidadVehiculo);
        panelInformacionDatos.add(txtcapacidadVehiculo);
        panelInformacionDatos.add(lblmaletas);
        panelInformacionDatos.add(txtmaletas);
        panelTransporteMultiple.add(panelInformacionDatos, BorderLayout.SOUTH);
        panelTransporteMultiple.setBorder(border);
    }

    public void creacionVehiculoLujo(String categoria, String[] condiciones) {
        Border border = BorderFactory.createLineBorder(Color.RED, 2);
        panelVehiculoLujo.setLayout(new BorderLayout());
        JLabel lblnombreCategoria = new JLabel("Categoria:");
        lblnombreCategoria.setHorizontalAlignment(JLabel.LEFT);
        JLabel lblmarcas = new JLabel("Marcas:");
        lblmarcas.setHorizontalAlignment(JLabel.LEFT);

        JTextField txtnombreCategoria = new JTextField();
        txtnombreCategoria.setEditable(false);
        txtnombreCategoria.setFont(new Font("Dialog", Font.BOLD, 12));
        txtnombreCategoria.setText(categoria);

        JTextField txtmarcas = new JTextField();
        txtmarcas.setEditable(false);
        txtmarcas.setFont(new Font("Dialog", Font.BOLD, 12));

        JPanel panelInformacionDatos = new JPanel();
        JPanel panelImagenC2 = new JPanel();
        panelImagenC2.setLayout(new BorderLayout());
        ImageIcon fotoVehiculo = new ImageIcon(
                new ImageIcon("./data/vehiculolujo.png").getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));

        JLabel temp = new JLabel();
        temp.setHorizontalAlignment(JLabel.CENTER);
        temp.setIcon(fotoVehiculo);
        panelImagenC2.add(temp, BorderLayout.CENTER);
        panelVehiculoLujo.add(panelImagenC2, BorderLayout.CENTER);

        for (String condicion : condiciones) {
            String[] partes = condicion.split("A");
            String atributo = partes[0];
            String explicacion = partes[1];
            if (atributo.equals("marca")) {
                txtmarcas.setText(explicacion);
            }
        }

        panelInformacionDatos.setLayout(new GridLayout(4, 2));
        panelInformacionDatos.add(lblnombreCategoria);
        panelInformacionDatos.add(txtnombreCategoria);
        panelInformacionDatos.add(lblmarcas);
        panelInformacionDatos.add(txtmarcas);
        panelVehiculoLujo.add(panelInformacionDatos, BorderLayout.SOUTH);
        panelVehiculoLujo.setBorder(border);
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
        JFrame frame = new JFrame("Ejemplo de Panel de Categorías");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SistemaAlquilerAutos sistema = new SistemaAlquilerAutos(); // Asegúrate de inicializar tu sistema aquí
        sistema.cargarInformacionVehiculos();
        sistema.cargarInformacionCliente();
        sistema.cargarInformacionEmpleado();
        sistema.cargarInformacionCondicionesCategoria();
        sistema.cargarInformacionSeguros();
        sistema.cargarInformacionSedes();
        sistema.cargarAdminSedes();
        PanelCategoria panelCategoria = new PanelCategoria(sistema);
        frame.getContentPane().add(panelCategoria);

        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar en la pantalla
        frame.setVisible(true);
    }

}
