package base;

public class JugadorPuntos {
    private String nombre;
    private String puntos;

    public JugadorPuntos(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return nombre + "-" + puntos;
    }
}
