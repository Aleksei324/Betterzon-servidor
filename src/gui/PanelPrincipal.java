package gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelPrincipal extends JPanel{
	
	// Atributos
	private JTextArea logDeEnvios;

	
	public PanelPrincipal() {

		setLayout(new BorderLayout());

		logDeEnvios = new JTextArea("<<Servidor>> Servidor de Betterzon encendido.\n");
		add(logDeEnvios);

	} // Constructor

	
	public JTextArea getLogDeEnvios() {
		return logDeEnvios;
	}

} // Class
