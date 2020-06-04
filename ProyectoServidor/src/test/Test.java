package test;

import base.Jugador;
import base.JugadorPuntos;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class Test {

    private static Jugador jugador;
    private static JugadorPuntos jugadorPuntos;

    Test() throws NoSuchPaddingException, NoSuchAlgorithmException {
        jugador = new Jugador();
        jugadorPuntos = new JugadorPuntos();
    }


    @org.junit.jupiter.api.Test
    void testGetNombre(){
        jugador.setNombre("Juan");
        String actual = jugador.getNombre();
        String esperado = "Juan";
        assertEquals(esperado, actual);
    }

    @org.junit.jupiter.api.Test
    void testGetPuntosDiferentesNivel1(){
        jugador.setPuntosNivel1(10);
        int actual = jugador.getPuntosNivel1();
        int esperado = 5;
        assertNotEquals(esperado, actual);
    }

    @org.junit.jupiter.api.Test
    void testGetPuntosNivel2(){
        jugador.setPuntosNivel2(10);
        int acutal = jugador.getPuntosNivel2();
        int esperado = 10;
        assertEquals(esperado, acutal);
    }

    @org.junit.jupiter.api.Test
    void testGetJugadorPuntos() {
        jugadorPuntos.setPuntos("10");
        String actual = jugadorPuntos.getPuntos();
        String esperado = "10";
        assertEquals(esperado, actual);
    }

    @org.junit.jupiter.api.Test
    void testGetJugadoresPuntosNombreDiferente() {
        jugadorPuntos.setNombre("Pedro");
        String actual = jugadorPuntos.getNombre();
        String esperado = "Otro";
        assertNotEquals(esperado, actual);
    }

}