package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Clase Engine
 * 
 * @author elena
 */
public class Engine {

	// Marco de la ventana
	private JFrame frame;

	// Panel general que ocupa toda la ventana
	private JPanel contentPanel;

	// Panel norte que contiene el display
	private JPanel displayPanel;

	// Panel sur que contiene los botones
	private JPanel buttonPanel;

	// Display
	private JTextField display;

	// Botones
	private JButton n0;
	private JButton n1;
	private JButton n2;
	private JButton n3;
	private JButton n4;
	private JButton n5;
	private JButton n6;
	private JButton n7;
	private JButton n8;
	private JButton n9;
	private JButton divide;
	private JButton multiply;
	private JButton subtract;
	private JButton add;
	private JButton equal;
	private JButton reset;

	// Tipos de boton
	private enum ButtonType {
		REGULAR, OPERATOR
	};

	// Almacenar temporalmente ciertos valores
	private int num1, num2, result;
	private char operation;

	/**
	 * Constructora
	 * 
	 */
	public Engine() {
		// Inicializacion de la ventana
		this.frame = new JFrame("Calculadora");

		// Inicializacion del panel que ocupa toda la ventana
		this.contentPanel = new JPanel();

		// Inicializacion del panel que contiene el display. (Panel Norte)
		this.displayPanel = new JPanel();

		// Inicializacion del panel que contiene los botones. (Panel Sur)
		this.buttonPanel = new JPanel();

		// Inicializacion del display que actua como la pantalla de la calculadora
		this.display = new JTextField(15);

		// Inicializacion de los botones de numeros
		this.n0 = new JButton("0");

		this.n1 = new JButton("1");

		this.n2 = new JButton("2");

		this.n3 = new JButton("3");

		this.n4 = new JButton("4");

		this.n5 = new JButton("5");

		this.n6 = new JButton("6");

		this.n7 = new JButton("7");

		this.n8 = new JButton("8");

		this.n9 = new JButton("9");

		// Inicializacion de los botones de acciones
		this.divide = new JButton("÷");

		this.multiply = new JButton("X");

		this.subtract = new JButton("-");

		this.add = new JButton("+");

		this.equal = new JButton("=");

		this.reset = new JButton("C");
		
		setSettings();

	}

	/**
	 * Metodo que establece la configuracion principal de todos los componentes
	 * visuales de la ventana
	 */
	public void setSettings() {
		
		// Configuracion del panel principal
		this.contentPanel.setLayout(new BorderLayout());
		
		// Configuracion del panel que contiene el display. (Panel Norte)
		this.displayPanel.setLayout(new GridLayout(1, 1));
		this.displayPanel.add(this.display);
		
		// Configuracion del panel que contiene los botones. (Panel Sur)
		this.buttonPanel.setLayout(new GridLayout(4, 4));

		// Primera fila
		this.buttonPanel.add(this.n7);
		setFeaturesButton(this.n7, ButtonType.REGULAR);
		this.buttonPanel.add(this.n8);
		setFeaturesButton(this.n8, ButtonType.REGULAR);
		this.buttonPanel.add(this.n9);
		setFeaturesButton(this.n9, ButtonType.REGULAR);
		this.buttonPanel.add(this.add);
		setFeaturesButton(this.add, ButtonType.OPERATOR);

		// Segunda fila
		this.buttonPanel.add(this.n4);
		setFeaturesButton(this.n4, ButtonType.REGULAR);
		this.buttonPanel.add(this.n5);
		setFeaturesButton(this.n5, ButtonType.REGULAR);
		this.buttonPanel.add(this.n6);
		setFeaturesButton(this.n6, ButtonType.REGULAR);
		this.buttonPanel.add(this.subtract);
		setFeaturesButton(this.subtract, ButtonType.OPERATOR);

		// Tercera fila
		this.buttonPanel.add(this.n1);
		setFeaturesButton(this.n1, ButtonType.REGULAR);
		this.buttonPanel.add(this.n2);
		setFeaturesButton(this.n2, ButtonType.REGULAR);
		this.buttonPanel.add(this.n3);
		setFeaturesButton(this.n3, ButtonType.REGULAR);
		this.buttonPanel.add(this.multiply);
		setFeaturesButton(this.multiply, ButtonType.OPERATOR);

		// Cuarta fila
		this.buttonPanel.add(this.n0);
		setFeaturesButton(this.n0, ButtonType.REGULAR);
		this.buttonPanel.add(this.equal);
		setFeaturesButton(this.equal, ButtonType.OPERATOR);
		this.buttonPanel.add(this.reset);
		setFeaturesButton(this.reset, ButtonType.OPERATOR);
		this.buttonPanel.add(this.divide);
		setFeaturesButton(this.divide, ButtonType.OPERATOR);
		
		// Insertar el panel que contiene el display (Panel Norte) al panel principal
		this.contentPanel.add(this.displayPanel, BorderLayout.NORTH);
		
		// Insertar el panel que contiene los botones (Panel Sur) al panel principal
		this.contentPanel.add(this.buttonPanel, BorderLayout.SOUTH);
		
		// Propiedades de la ventana
		this.frame.add(this.contentPanel);
		this.frame.setLocation(200, 200);
		this.frame.setSize(400, 700);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;

	}

	/**
	 * Metodo que distingue el tipo de boton y le edita en funcion de su tipo
	 * 
	 * @param _button identifica el boton sobre el que se van a cambiar las
	 *                caracteristicas
	 * @param _type   identifica de que tipo es el boton sobre el que van a cambiar
	 *                las caracteristicas
	 */
	public void setFeaturesButton(JButton _button, ButtonType _type) {

	}

	/**
	 * Metodo que registra los ActionListener para todos los botones de la
	 * aplicacion
	 */
	public void addActionEvent() {

	}

	/**
	 * Metodo que comprueba que operacion se debe realizar
	 */
	public void operation() {

	}

	/**
	 * Metodo que se encarga de obtener la informacion que haya en el display y
	 * llamar al metodo operation() para ejecutar dicha operacion
	 * 
	 * @param e es el objeto que lee el display
	 */
	public void actionPerformed(ActionEvent e) {
		// Recogemos el tipo de boton que se ha pulsado y su texto
		Object source = e.getSource();
		String input_text = e.getActionCommand();

	}

}
