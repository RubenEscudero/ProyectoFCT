import gui.Controlador;
import gui.Modelo;
import gui.Vista;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);

        ServerSocket serverSocket = new ServerSocket(4444);
        System.out.println("servidor iniciado");
        while (true){
            Socket cliente = serverSocket.accept();
            HiloServidor hiloServidor = new HiloServidor(cliente, modelo);
            hiloServidor.start();
        }
    }

}
