import base.Jugador;
import base.JugadorPuntos;
import com.rubenescudero.aes.AES;
import com.rubenescudero.rsa.RSA;
import gui.Modelo;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.jws.WebParam;
import java.io.*;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

public class HiloServidor extends Thread{

    private Socket cliente;
    private DataInputStream entrada;
    private PrintStream salida;
    private Modelo modelo;

    private AES aes;
    private byte[] encriptado;
    private String textBase64;
    private String desencriptado;
    private RSA rsa;

    public HiloServidor(Socket cliente, Modelo modelo) throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cliente = cliente;
        this.modelo = modelo;

        aes = new AES();
        rsa = new RSA();
    }

    @Override
    public void run() {
        try {
            salida = new PrintStream(cliente.getOutputStream());
            entrada = new DataInputStream(cliente.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Jugador jugador;

        while (true){



            try {
                textBase64 = entrada.readLine();
            } catch (IOException e) {
                System.out.println("No se recibe mensaje");
            }

            System.out.println("Recibido base64: " + textBase64);
            encriptado = Base64.getDecoder().decode(textBase64);

            try {
                desencriptado = aes.desencriptar(encriptado);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
            System.out.println(desencriptado);
            String mensajeRecibido = desencriptado;
            /**
            String encriptadoRSA = null;
            try {
                encriptadoRSA = entrada.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Ruta donde se guardan las claves
            String file_private = "D:\\ProyectosIdea\\ProgramacionDeServicios\\Claves\\rsa.pri";
            String file_public = "D:\\ProyectosIdea\\ProgramacionDeServicios\\Claves\\rsa.pub";

            try {
                rsa.openFromDiskPrivateKey(file_private);
                rsa.openFromDiskPublicKey(file_public);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }


            try {
                desencriptado = rsa.Decrypt(encriptadoRSA);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }

            String mensajeRecibido = desencriptado;
            */
            if(mensajeRecibido.split("-")[0].equalsIgnoreCase("jugador")) {
                    jugador = new Jugador();
                    jugador.setNombre(mensajeRecibido.split("-")[1]);
                    jugador.setPuntosNivel1(Integer.parseInt(mensajeRecibido.split("-")[2]));
                    jugador.setPuntosNivel2(Integer.parseInt(mensajeRecibido.split("-")[3]));
                    jugador.setPuntosNivel3(Integer.parseInt(mensajeRecibido.split("-")[4]));
                    jugador.setPuntosNivel4(Integer.parseInt(mensajeRecibido.split("-")[5]));
                    jugador.setPuntosNivel5(Integer.parseInt(mensajeRecibido.split("-")[6]));
                    jugador.setPuntosNivel6(Integer.parseInt(mensajeRecibido.split("-")[7]));
                    jugador.setPuntosNivel7(Integer.parseInt(mensajeRecibido.split("-")[8]));
                try {
                    modelo.guardarJugador(jugador);
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }

                try {
                    encriptado = aes.encriptar("Jugador recibido");
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }

                textBase64 = Base64.getEncoder().encodeToString(encriptado);

                System.out.println("Salida base64: " + textBase64);
                salida.println(textBase64);
                }

                else if(mensajeRecibido.equalsIgnoreCase("recibir")){
                List<Jugador> listaJugadores = null;
                try {
                    listaJugadores = modelo.getJugadores();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
                List<JugadorPuntos> listaPuntosSumados = new ArrayList<>();
                    JugadorPuntos jugadorPuntos;

                    for(Jugador jugador1: listaJugadores){
                        int sumaPuntos = jugador1.getPuntosNivel1() + jugador1.getPuntosNivel2()
                                + jugador1.getPuntosNivel3() + jugador1.getPuntosNivel4()
                                + jugador1.getPuntosNivel5() + jugador1.getPuntosNivel6()
                                + jugador1.getPuntosNivel7() ;
                        jugadorPuntos = new JugadorPuntos();
                        jugadorPuntos.setNombre(jugador1.getNombre());
                        jugadorPuntos.setPuntos(sumaPuntos + "");
                        listaPuntosSumados.add(jugadorPuntos);
                    }

                    Collections.sort(listaPuntosSumados, new Comparator<JugadorPuntos>() {
                        @Override
                        public int compare(JugadorPuntos o1, JugadorPuntos o2) {
                            return new Integer(o2.getPuntos()).compareTo(new Integer(o1.getPuntos()));
                        }
                    });

                    String mensajeSalida = "";

                    for(int i = 0; i < 5; i++){
                        mensajeSalida = mensajeSalida + "," + listaPuntosSumados.get(i);
                    }


                    try {
                         encriptado = aes.encriptar(mensajeSalida);
                    } catch (InvalidKeyException e) {
                      e.printStackTrace();
                    } catch (BadPaddingException e) {
                      e.printStackTrace();
                    } catch (IllegalBlockSizeException e) {
                       e.printStackTrace();
                    }

                    textBase64 = Base64.getEncoder().encodeToString(encriptado);

                    System.out.println("Salida base64: " + textBase64);

                    salida.println(textBase64);
                     /**
                    try {
                        rsa.saveToDiskPrivateKey(file_private);
                        rsa.saveToDiskPublicKey(file_public);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    try {
                        String encriptadoRSA2 = rsa.Encrypt(mensajeSalida);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    } catch (IllegalBlockSizeException e) {
                        e.printStackTrace();
                    } catch (BadPaddingException e) {
                        e.printStackTrace();
                    } catch (InvalidKeySpecException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (NoSuchProviderException e) {
                        e.printStackTrace();
                    }

                    salida.println(encriptadoRSA);
                     */
            }
        }
    }
}
