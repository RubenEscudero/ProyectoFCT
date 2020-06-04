package gui;


import base.Jugador;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.List;

public class Controlador implements ActionListener, KeyListener, ListSelectionListener {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) throws IOException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        this.vista = vista;
        this.modelo = modelo;
        modelo.conectar();
        init();
    }

    private void init() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        addActionListener(this);
        addListSelectionListener(this);

        listarJugadores(modelo.getJugadores());
    }

    private void addActionListener(ActionListener listener){
        vista.btnInsertar.addActionListener(listener);
        vista.btnModificar.addActionListener(listener);
        vista.btnActualizar.addActionListener(listener);
        vista.btnEliminar.addActionListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener){
        vista.listaJugadores.addListSelectionListener(listener);
        vista.listaJugadoresModificar.addListSelectionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Jugador jugador;
        switch (e.getActionCommand()){
            case "Instertar":
                jugador = new Jugador();
                getCamposJugador(jugador);
                try {
                    modelo.guardarJugador(jugador);
                } catch (BadPaddingException ex) {
                    ex.printStackTrace();
                } catch (InvalidKeyException ex) {
                    ex.printStackTrace();
                } catch (IllegalBlockSizeException ex) {
                    ex.printStackTrace();
                }
                vaciarCampos();
                try {
                    listarJugadores(modelo.getJugadores());
                } catch (BadPaddingException ex) {
                    ex.printStackTrace();
                } catch (InvalidKeyException ex) {
                    ex.printStackTrace();
                } catch (IllegalBlockSizeException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Modificar":
                jugador = (Jugador) vista.listaJugadoresModificar.getSelectedValue();
                modificarJugador(jugador);
                try {
                    modelo.modificarJugador(jugador);
                } catch (BadPaddingException ex) {
                    ex.printStackTrace();
                } catch (InvalidKeyException ex) {
                    ex.printStackTrace();
                } catch (IllegalBlockSizeException ex) {
                    ex.printStackTrace();
                }
                vaciarCamposModificar();
                try {
                    listarJugadores(modelo.getJugadores());
                } catch (BadPaddingException ex) {
                    ex.printStackTrace();
                } catch (InvalidKeyException ex) {
                    ex.printStackTrace();
                } catch (IllegalBlockSizeException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Eliminar":
                jugador = (Jugador) vista.listaJugadoresModificar.getSelectedValue();
                try {
                    modelo.eliminarJugador(jugador);
                } catch (BadPaddingException ex) {
                    ex.printStackTrace();
                } catch (InvalidKeyException ex) {
                    ex.printStackTrace();
                } catch (IllegalBlockSizeException ex) {
                    ex.printStackTrace();
                }
                vaciarCamposModificar();
                try {
                    listarJugadores(modelo.getJugadores());
                } catch (BadPaddingException ex) {
                    ex.printStackTrace();
                } catch (InvalidKeyException ex) {
                    ex.printStackTrace();
                } catch (IllegalBlockSizeException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Actualizar":
                try {
                    listarJugadores(modelo.getJugadores());
                } catch (BadPaddingException ex) {
                    ex.printStackTrace();
                } catch (InvalidKeyException ex) {
                    ex.printStackTrace();
                } catch (IllegalBlockSizeException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    private void vaciarCampos(){
        vista.txtNombreNuevoJugador.setText("");
        vista.txtPuntosNivel1.setText("");
        vista.txtPuntosNivel2.setText("");
        vista.txtPuntosNivel3.setText("");
        vista.txtPuntosNivel4.setText("");
        vista.txtPuntosNivel5.setText("");
        vista.txtPuntosNivel6.setText("");
        vista.txtPuntosNivel7.setText("");
    }

    private void vaciarCamposModificar(){
        vista.txtModificarNombre.setText("");
        vista.txtModificarNivel1.setText("");
        vista.txtModificarNivel2.setText("");
        vista.txtModificarNivel3.setText("");
        vista.txtModificarNivel4.setText("");
        vista.txtModificarNivel5.setText("");
        vista.txtModificarNivel6.setText("");
        vista.txtModificarNivel7.setText("");
    }

    private void listarJugadores(List<Jugador> list){
        vista.dlmJugadorModificar.clear();
        vista.dlmJugador.clear();
        for(Jugador jugador: list){
            vista.dlmJugador.addElement(jugador);
            vista.dlmJugadorModificar.addElement(jugador);
        }
    }

    private void modificarJugador(Jugador jugador){
        jugador.setNombre(vista.txtModificarNombre.getText());
        jugador.setPuntosNivel1(Integer.valueOf(vista.txtModificarNivel1.getText()));
        jugador.setPuntosNivel2(Integer.valueOf(vista.txtModificarNivel2.getText()));
        jugador.setPuntosNivel3(Integer.valueOf(vista.txtModificarNivel3.getText()));
        jugador.setPuntosNivel4(Integer.valueOf(vista.txtModificarNivel4.getText()));
        jugador.setPuntosNivel5(Integer.valueOf(vista.txtModificarNivel5.getText()));
        jugador.setPuntosNivel6(Integer.valueOf(vista.txtModificarNivel6.getText()));
        jugador.setPuntosNivel7(Integer.valueOf(vista.txtModificarNivel7.getText()));
    }

    private void getCamposJugador(Jugador jugador){
        jugador.setNombre(vista.txtNombreNuevoJugador.getText());
        jugador.setPuntosNivel1(Integer.valueOf(vista.txtPuntosNivel1.getText()));
        jugador.setPuntosNivel2(Integer.valueOf(vista.txtPuntosNivel2.getText()));
        jugador.setPuntosNivel3(Integer.valueOf(vista.txtPuntosNivel3.getText()));
        jugador.setPuntosNivel4(Integer.valueOf(vista.txtPuntosNivel4.getText()));
        jugador.setPuntosNivel5(Integer.valueOf(vista.txtPuntosNivel5.getText()));
        jugador.setPuntosNivel6(Integer.valueOf(vista.txtPuntosNivel6.getText()));
        jugador.setPuntosNivel7(Integer.valueOf(vista.txtPuntosNivel7.getText()));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {


            if(e.getValueIsAdjusting()) {
            try {
                Jugador jugador1 = (Jugador) vista.listaJugadores.getSelectedValue();
                vista.lblNivel1.setText(String.valueOf(jugador1.getPuntosNivel1()));
                vista.lblNivel2.setText(String.valueOf(jugador1.getPuntosNivel2()));
                vista.lblNivel3.setText(String.valueOf(jugador1.getPuntosNivel3()));
                vista.lblNivel4.setText(String.valueOf(jugador1.getPuntosNivel4()));
                vista.lblNivel5.setText(String.valueOf(jugador1.getPuntosNivel5()));
                vista.lblNivel6.setText(String.valueOf(jugador1.getPuntosNivel6()));
                vista.lblNivel7.setText(String.valueOf(jugador1.getPuntosNivel7()));
            }catch (NullPointerException e3){
                System.out.println("Otra lista seleccionada");
            }

            try {
                Jugador jugador2 = (Jugador) vista.listaJugadoresModificar.getSelectedValue();
                vista.txtModificarNombre.setText(jugador2.getNombre());
                vista.txtModificarNivel1.setText(String.valueOf(jugador2.getPuntosNivel1()));
                vista.txtModificarNivel2.setText(String.valueOf(jugador2.getPuntosNivel2()));
                vista.txtModificarNivel3.setText(String.valueOf(jugador2.getPuntosNivel3()));
                vista.txtModificarNivel4.setText(String.valueOf(jugador2.getPuntosNivel4()));
                vista.txtModificarNivel5.setText(String.valueOf(jugador2.getPuntosNivel5()));
                vista.txtModificarNivel6.setText(String.valueOf(jugador2.getPuntosNivel6()));
                vista.txtModificarNivel7.setText(String.valueOf(jugador2.getPuntosNivel7()));
            }catch (NullPointerException e2){
                System.out.println("Otra lista seleccionada");
            }
        }
    }
}
