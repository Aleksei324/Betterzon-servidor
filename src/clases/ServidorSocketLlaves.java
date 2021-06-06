package clases;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import gui.PanelPrincipal;

public class ServidorSocketLlaves implements Runnable{
	
	// Atributos
	private static PanelPrincipal panel = new PanelPrincipal();
	private static String llave = "Escribe tu contraseña para la llave aqui"; // TODO: 2021-06-06 this
	private static int puerto = 2222; // TODO: 2021-06-06 Ajusta los puertos
    private EmpaquetadoDeLlaves paqueteLlaves;
    private ServerSocket servidorDeEscucha;
    private Socket socketLocal;
    private Socket socketDeEnvio;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    
	public void inicializarHilo() {

    	Thread hiloParaServidor = new Thread(this);
    	hiloParaServidor.start();

    } // InicializarHilo()


    @Override
    public void run(){

        try {

            while (true){
            	
            	servidorDeEscucha = new ServerSocket(puerto);

                // espera una petición en el puerto especificado.
                // Al recibirla establece una conexión con el otro programa.
                socketLocal = servidorDeEscucha.accept();

                // El flujo de datos de entrada va a circular por el puente virtual del socket
                entrada = new ObjectInputStream(socketLocal.getInputStream());

                // Guarda el objeto que obtenga como EmpaquetadoDeMensaje
                paqueteLlaves = (EmpaquetadoDeLlaves) entrada.readObject();
                
                entrada.close();
                socketLocal.close();
                
                // Logs
                panel.getLogDeEnvios().append(
                		"<<Servidor>> Nuevo chat detectado a las " + LocalDateTime.now() + ".\n");

                // Crea un nuevo socket para enviar las llaves al destinatario
                socketDeEnvio = new Socket(
                		EncriptadoAES.decifrar(
                		paqueteLlaves.getDestinatarioIP(), llave), puerto + 10);
                
                // El flujo de datos de salida va a circular por el puente virtual del socket
                salida = new ObjectOutputStream(socketDeEnvio.getOutputStream());
                
                // Se envia
                salida.writeObject(paqueteLlaves);

                salida.close();
                socketDeEnvio.close();
                servidorDeEscucha.close();
                
                // Logs
                panel.getLogDeEnvios().append(
                		"<<Servidor>> Llaves sincronizadas a las " + LocalDateTime.now() + ".\n\n");

            } // while

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    } // run()
	
} // class
