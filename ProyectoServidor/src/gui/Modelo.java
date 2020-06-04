package gui;

import base.Jugador;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rubenescudero.aes.AES;
import org.bson.Document;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

public class Modelo {
    private final static String COLECCION_JUGADORES = "Jugadores";
    private final static String DATABASE = "BBDDPuntuaciones";
    private AES aes;
    private byte[] encriptado;
    private String textBase64;
    private String desencriptado;

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection collectionJugadores;

    public Modelo() throws NoSuchAlgorithmException, NoSuchPaddingException {
        aes = new AES();
    }

    public void conectar() throws IOException {
        client = new MongoClient();
        database = client.getDatabase(DATABASE);
        collectionJugadores = database.getCollection(COLECCION_JUGADORES);

        System.out.println("Conectado");
    }

    public void descontectar(){
        client.close();
    }

    public void guardarJugador(Jugador jugador) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        collectionJugadores.insertOne(jugadorToDocument(jugador));
    }

    public List<Jugador> getJugadores() throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        ArrayList<Jugador> list = new ArrayList<>();
        Iterator<Document> it = collectionJugadores.find().iterator();
        while (it.hasNext()){
            list.add(documentToJugador(it.next()));
        }
        return list;
    }

    public void modificarJugador(Jugador jugador) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        collectionJugadores.replaceOne(new Document("_id", jugador.getId()), jugadorToDocument(jugador));
    }

    public void eliminarJugador(Jugador jugador) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        collectionJugadores.deleteOne(jugadorToDocument(jugador));
    }

    public Document jugadorToDocument(Jugador jugador) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        Document document = new Document();
        encriptado = aes.encriptar(jugador.getNombre());
        textBase64 = Base64.getEncoder().encodeToString(encriptado);
        document.append("nombre", textBase64);
        encriptado = aes.encriptar(String.valueOf(jugador.getPuntosNivel1()));
        textBase64 = Base64.getEncoder().encodeToString(encriptado);
        document.append("nivel1", textBase64);
        encriptado = aes.encriptar(String.valueOf(jugador.getPuntosNivel2()));
        textBase64 = Base64.getEncoder().encodeToString(encriptado);
        document.append("nivel2", textBase64);
        encriptado = aes.encriptar(String.valueOf(jugador.getPuntosNivel3()));
        textBase64 = Base64.getEncoder().encodeToString(encriptado);
        document.append("nivel3", textBase64);
        encriptado = aes.encriptar(String.valueOf(jugador.getPuntosNivel4()));
        textBase64 = Base64.getEncoder().encodeToString(encriptado);
        document.append("nivel4", textBase64);
        encriptado = aes.encriptar(String.valueOf(jugador.getPuntosNivel5()));
        textBase64 = Base64.getEncoder().encodeToString(encriptado);
        document.append("nivel5", textBase64);
        encriptado = aes.encriptar(String.valueOf(jugador.getPuntosNivel6()));
        textBase64 = Base64.getEncoder().encodeToString(encriptado);
        document.append("nivel6", textBase64);
        encriptado = aes.encriptar(String.valueOf(jugador.getPuntosNivel7()));
        textBase64 = Base64.getEncoder().encodeToString(encriptado);
        document.append("nivel7", textBase64);
        return document;
    }

    public Jugador documentToJugador(Document document) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        Jugador jugador = new Jugador();
        jugador.setId(document.getObjectId("_id"));
        encriptado = Base64.getDecoder().decode(document.getString("nombre"));
        desencriptado = aes.desencriptar(encriptado);
        jugador.setNombre(desencriptado);
        encriptado = Base64.getDecoder().decode(document.getString("nivel1"));
        desencriptado = aes.desencriptar(encriptado);
        jugador.setPuntosNivel1(Integer.valueOf(desencriptado));
        encriptado = Base64.getDecoder().decode(document.getString("nivel2"));
        desencriptado = aes.desencriptar(encriptado);
        jugador.setPuntosNivel2(Integer.valueOf(desencriptado));
        encriptado = Base64.getDecoder().decode(document.getString("nivel3"));
        desencriptado = aes.desencriptar(encriptado);
        jugador.setPuntosNivel3(Integer.valueOf(desencriptado));
        encriptado = Base64.getDecoder().decode(document.getString("nivel4"));
        desencriptado = aes.desencriptar(encriptado);
        jugador.setPuntosNivel4(Integer.valueOf(desencriptado));
        encriptado = Base64.getDecoder().decode(document.getString("nivel5"));
        desencriptado = aes.desencriptar(encriptado);
        jugador.setPuntosNivel5(Integer.valueOf(desencriptado));
        encriptado = Base64.getDecoder().decode(document.getString("nivel6"));
        desencriptado = aes.desencriptar(encriptado);
        jugador.setPuntosNivel6(Integer.valueOf(desencriptado));
        encriptado = Base64.getDecoder().decode(document.getString("nivel7"));
        desencriptado = aes.desencriptar(encriptado);
        jugador.setPuntosNivel7(Integer.valueOf(desencriptado));
        return jugador;
    }

    public int suma(int n1, int n2){
        return n1 + n2;
    }
}
