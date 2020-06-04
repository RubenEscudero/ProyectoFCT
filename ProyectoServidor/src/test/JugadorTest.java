package test;

import base.Jugador;
import base.JugadorPuntos;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class JugadorTest {

    private static Jugador jugador;

    JugadorTest(){

    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @BeforeAll
    static void setNombre(){
        jugador = new Jugador();
        jugador.setNombre("Juan");
    }

    @Test
    void getNombre() {
        String actual = jugador.getNombre();
        String esperado = "Juan";
        assertEquals(esperado, actual);
    }

    @Test
    void getNombreError() {
        String actual = jugador.getNombre();
        String esperado = "Mario";
        assertNotEquals(esperado, actual);
    }
}