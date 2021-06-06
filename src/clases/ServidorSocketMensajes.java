package clases;

import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import gui.FramePrincipal;
import gui.PanelPrincipal;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ServidorSocketMensajes implements Runnable {
    
    // Atributos
	private static PanelPrincipal panel = new PanelPrincipal();
	private static String llave = "Escribe tu contraseña para la llave aqui"; // TODO: 2021-06-06 this
	private static int puerto = 1111; // TODO: 2021-06-06 Ajusta los puertos
    private EmpaquetadoDeMensaje mensaje;
    private ServerSocket servidorDeEscucha;
    private Socket socketLocal;
    private Socket socketDeEnvio;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;


    public static void main(String[] args) {

		// Iniciar el GUI
		FramePrincipal frame1 = new FramePrincipal();
		frame1.add(panel);
		frame1.setVisible(true);
		
		// Para que el servidor pueda recibir mensajes
		ServidorSocketMensajes servidorM = new ServidorSocketMensajes();
		servidorM.inicializarHilo();
		
		// Para que el servidor pueda recibir mensajes
		ServidorSocketLlaves servidorL = new ServidorSocketLlaves();
		servidorL.inicializarHilo();

	} // Main()


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
                mensaje = (EmpaquetadoDeMensaje) entrada.readObject();
                
                entrada.close();
                socketLocal.close();
                
                // Logs
                panel.getLogDeEnvios().append(
                		"<<Servidor>> Mensaje recibido a las " + LocalDateTime.now() + ".\n");

                // Guarda el mensaje en la base de datos...

                // Crea un nuevo socket para enviar el mensaje al destinatario
                socketDeEnvio = new Socket(
                		EncriptadoAES.decifrar(
                		mensaje.getDestinatarioIP(), llave), puerto + 10);
                
                // El flujo de datos de salida va a circular por el puente virtual del socket
                salida = new ObjectOutputStream(socketDeEnvio.getOutputStream());
                
                // Se envia
                salida.writeObject(mensaje);

                salida.close();
                socketDeEnvio.close();
                servidorDeEscucha.close();
                
                // Logs
                panel.getLogDeEnvios().append(
                		"<<Servidor>> Mensaje enviado a las " + LocalDateTime.now() + ".\n\n");

            } // while

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    } // run()

} // Class
