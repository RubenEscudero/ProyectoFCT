package gui;

import base.Jugador;

import javax.swing.*;
import java.awt.*;

public class Vista {
    private JPanel panel;
    JTabbedPane tabbedPane1;
    JList listaJugadores;
    JTextField txtNombreNuevoJugador;
    JLabel lblNivel1;
    JLabel lblNivel2;
    JLabel lblNivel3;
    JLabel lblNivel4;
    JLabel lblNivel5;
    JLabel lblNivel6;
    JLabel lblNivel7;
    JTextField txtPuntosNivel1;
    JTextField txtPuntosNivel2;
    JTextField txtPuntosNivel3;
    JTextField txtPuntosNivel4;
    JTextField txtPuntosNivel5;
    JTextField txtPuntosNivel6;
    JTextField txtPuntosNivel7;
    JButton btnInsertar;
    JList listaJugadoresModificar;
    JTextField txtModificarNivel1;
    JTextField txtModificarNivel2;
    JTextField txtModificarNivel3;
    JTextField txtModificarNivel4;
    JTextField txtModificarNivel5;
    JTextField txtModificarNivel6;
    JTextField txtModificarNivel7;
    JButton btnModificar;
    JButton btnActualizar;
    JButton btnEliminar;
    JTextField txtModificarNombre;

    DefaultListModel<Jugador> dlmJugador;
    DefaultListModel<Jugador> dlmJugadorModificar;

    public Vista() {
        JFrame frame = new JFrame("Vista");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(720, 480));
        frame.setVisible(true);
        init();
    }

    private void init(){
        dlmJugador = new DefaultListModel<>();
        listaJugadores.setModel(dlmJugador);

        dlmJugadorModificar = new DefaultListModel<>();
        listaJugadoresModificar.setModel(dlmJugadorModificar);
    }
}
