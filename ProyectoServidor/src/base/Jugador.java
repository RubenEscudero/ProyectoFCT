package base;

import org.bson.types.ObjectId;

import java.util.Arrays;

public class Jugador {

    //id de tipo org.bson.types.ObjectId
    private ObjectId id;
    private String nombre;
    private int puntosNivel1;
    private int puntosNivel2;
    private int puntosNivel3;
    private int puntosNivel4;
    private int puntosNivel5;
    private int puntosNivel6;
    private int puntosNivel7;

    public Jugador(){
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosNivel1() {
        return puntosNivel1;
    }

    public void setPuntosNivel1(int puntosNivel1) {
        this.puntosNivel1 = puntosNivel1;
    }

    public int getPuntosNivel2() {
        return puntosNivel2;
    }

    public void setPuntosNivel2(int puntosNivel2) {
        this.puntosNivel2 = puntosNivel2;
    }

    public int getPuntosNivel3() {
        return puntosNivel3;
    }

    public void setPuntosNivel3(int puntosNivel3) {
        this.puntosNivel3 = puntosNivel3;
    }

    public int getPuntosNivel4() {
        return puntosNivel4;
    }

    public void setPuntosNivel4(int puntosNivel4) {
        this.puntosNivel4 = puntosNivel4;
    }

    public int getPuntosNivel5() {
        return puntosNivel5;
    }

    public void setPuntosNivel5(int puntosNivel5) {
        this.puntosNivel5 = puntosNivel5;
    }

    public int getPuntosNivel6() {
        return puntosNivel6;
    }

    public void setPuntosNivel6(int puntosNivel6) {
        this.puntosNivel6 = puntosNivel6;
    }

    public int getPuntosNivel7() {
        return puntosNivel7;
    }

    public void setPuntosNivel7(int puntosNivel7) {
        this.puntosNivel7 = puntosNivel7;
    }

    @Override
    public String toString() {
        return "Jugador: " + nombre;
    }
}
