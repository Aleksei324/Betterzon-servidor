package clases;

import java.io.Serializable;

public class EmpaquetadoDeMensaje implements Serializable{

    // Atributos
    private byte[] texto, destinatarioIP, multimedia;

    
	public EmpaquetadoDeMensaje(byte[] destinatarioIP, byte[] texto, byte[] multimedia) {

		this.destinatarioIP = destinatarioIP;
		this.texto = texto;
		this.multimedia = multimedia;

	} // Constructor


	public byte[] getTexto() {
		return texto;
	}

	public byte[] getDestinatarioIP() {
		return destinatarioIP;
	}

	public byte[] getMultimedia() {
		return multimedia;
	}

} // Clase
