package clases;

import java.io.Serializable;
import javax.swing.text.Document;

public class EmpaquetadoDeMensaje implements Serializable{

    // Atributos
    private String texto, destinatarioIP;
    private Document multimedia;


    public EmpaquetadoDeMensaje(Document multimedia, String destinatarioIP) {

		this.multimedia = multimedia;
		this.destinatarioIP = destinatarioIP;

	} // Constructor


	public EmpaquetadoDeMensaje(String texto, String destinatarioIP) {

		this.texto = texto;
		this.destinatarioIP = destinatarioIP;

	} // Constructor


	public String getTexto() {
        return texto;
    }

	public String getDestinatarioIP() {
		return destinatarioIP;
	}

	public Document getMultimedia() {
		return multimedia;
	}

} // Clase
