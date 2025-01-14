package main;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Clase VentanaEmergente
 * 
 * @author elena
 */
public class VentanaEmergente {
	/**
	 * Atributos
	 */
	private String msg;
	private JLabel mensaje;
	private JDialog dialogo;
	private JButton ok;

	/**
	 * Constructora
	 * 
	 * @param _msg es el mensaje que aparecera en el pop-up
	 */
	public VentanaEmergente(String _msg) {
		this.msg = _msg;

		// Personalizacion de mensaje
		this.mensaje = new JLabel(this.msg);
		this.mensaje.setFont(new Font("Source Code Pro", 0, 15));
		this.mensaje.setHorizontalAlignment(JLabel.CENTER);

		// Creacion del JDialog
		this.dialogo = new JDialog(this.dialogo, "Ventana de información");

		// Personalizacion de boton
		this.ok = new JButton("Ok");
		this.ok.setBackground(new Color(251, 96, 102));
		this.ok.setFont(new Font("Source Code Pro", 0, 15));
		this.ok.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		this.ok.setPreferredSize(new Dimension(50, 50));

		// Aniadimos los elementos al JDialog
		this.dialogo.add(this.mensaje);
		this.dialogo.add(this.ok);

		// Personalizacion del JDialog
		this.dialogo.setLayout(new GridLayout(2, 1));
		this.dialogo.setSize(700, 100);
		this.dialogo.setVisible(true);

	}

}
