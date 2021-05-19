package clases;

import java.security.PublicKey;

public class EmpaquetadoDeLlaves {
	
	// Atributos
	private PublicKey llaveRemitente;
    private byte[] destinatarioIP;


	public EmpaquetadoDeLlaves(PublicKey llaveRemitente, byte[] destinatarioIP) {

		this.llaveRemitente = llaveRemitente;
		this.destinatarioIP = destinatarioIP;

	} // Constructor


	public PublicKey getLlaveRemitente() {
		return llaveRemitente;
	}

	public byte[] getDestinatarioIP() {
		return destinatarioIP;
	}

} // class
