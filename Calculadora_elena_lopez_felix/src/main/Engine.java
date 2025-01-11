package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Clase Engine
 * 
 * @author elena
 */
public class Engine implements ActionListener {

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
	// Nuevas funcionalidades
	private JButton delete;
	private JButton raiz;
	private JButton potencia;
	private JButton factorial;

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
		this.display = new JTextField();

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
		this.divide = new JButton("/");

		this.multiply = new JButton("X");

		this.subtract = new JButton("-");

		this.add = new JButton("+");

		this.equal = new JButton("=");

		this.reset = new JButton("C");

		this.delete = new JButton("<-");

		this.raiz = new JButton("√");

		this.potencia = new JButton("^");

		this.factorial = new JButton("!");

		this.num1 = 0;
		this.num2 = 0;
		this.result = 0;

		// LLamada al metodo para configurar todos los componentes visuales
		setSettings();
		addActionEvent();

	}

	/**
	 * Metodo que establece la configuracion principal de todos los componentes
	 * visuales de la ventana
	 */
	public void setSettings() {

		// Configuracion del display
		this.display.setPreferredSize(new Dimension(800, 150));
		this.display.setHorizontalAlignment(JTextField.RIGHT);
		this.display.setEditable(false);

		/*
		 * Font(nombre de la fuente, estilo de la fuente (0 - sin estilo, 1 - negrita, 2
		 * - cursiva, 3 - negrita + cursiva)
		 */
		this.display.setFont(new Font("Calibri", 2, 50));
		this.display.setForeground(Color.BLACK);

		// Configuracion del panel principal
		this.contentPanel.setLayout(new BorderLayout());

		// Configuracion del panel que contiene el display. (Panel Norte)
		this.displayPanel.setLayout(new GridLayout(1, 1));
		this.displayPanel.add(this.display);

		// Configuracion del panel que contiene los botones. (Panel Sur)
		this.buttonPanel.setLayout(new GridLayout(5, 4));

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
		this.buttonPanel.add(this.delete);
		setFeaturesButton(this.delete, ButtonType.OPERATOR);
		this.buttonPanel.add(this.raiz);
		setFeaturesButton(this.raiz, ButtonType.OPERATOR);
		this.buttonPanel.add(this.divide);
		setFeaturesButton(this.divide, ButtonType.OPERATOR);

		// Quinta fila
		this.buttonPanel.add(this.factorial);
		setFeaturesButton(this.factorial, ButtonType.OPERATOR);
		this.buttonPanel.add(this.reset);
		setFeaturesButton(this.reset, ButtonType.OPERATOR);
		this.buttonPanel.add(this.equal);
		setFeaturesButton(this.equal, ButtonType.OPERATOR);
		this.buttonPanel.add(this.potencia);
		setFeaturesButton(this.potencia, ButtonType.OPERATOR);

		// Insertar el panel que contiene el display (Panel Norte) al panel principal
		this.contentPanel.add(this.displayPanel, BorderLayout.NORTH);

		// Insertar el panel que contiene los botones (Panel Sur) al panel principal
		this.contentPanel.add(this.buttonPanel, BorderLayout.SOUTH);

		// Propiedades de la ventana
		this.frame.add(this.contentPanel);
		this.frame.setLocation(200, 200);
		this.frame.setSize(450, 395);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		;

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
		if (_type.equals(ButtonType.REGULAR)) {
			_button.setBackground(Color.MAGENTA);
			_button.setFont(new Font("Calibri", 1, 25));

		} else {
			_button.setBackground(Color.CYAN);
			_button.setFont(new Font("Calibri", 1, 25));

		}

	}

	/**
	 * Metodo que registra los ActionListener para todos los botones de la
	 * aplicacion
	 */
	public void addActionEvent() {
		this.n0.addActionListener(this);
		this.n1.addActionListener(this);
		this.n2.addActionListener(this);
		this.n3.addActionListener(this);
		this.n4.addActionListener(this);
		this.n5.addActionListener(this);
		this.n6.addActionListener(this);
		this.n7.addActionListener(this);
		this.n8.addActionListener(this);
		this.n9.addActionListener(this);
		this.add.addActionListener(this);
		this.subtract.addActionListener(this);
		this.multiply.addActionListener(this);
		this.divide.addActionListener(this);
		this.equal.addActionListener(this);
		this.reset.addActionListener(this);
		this.delete.addActionListener(this);
		this.raiz.addActionListener(this);
		this.potencia.addActionListener(this);
		this.factorial.addActionListener(this);

	}

	/**
	 * Metodo que realiza las operaciones y divide el display en los operandos
	 */
	public void operation() {
		switch (this.operation) {
		case '+':
			this.result = this.num1 + this.num2;
			break;
		case '-':
			this.result = this.num1 - this.num2;
			break;
		case 'X':
			this.result = this.num1 * this.num2;
			break;
		case '/':
			this.result = this.num1 / this.num2;
			break;
		case '\u221A':
			this.result = (int) Math.sqrt(this.num1);
			break;
		case '^':
			this.result = (int) Math.pow(this.num1, this.num2);
			break;
		case '!':
			this.result = factorial(this.num1);
			break;
		default:
			break;

		}

	}

	/**
	 * Metodo que se encarga de obtener la informacion que haya en el display y
	 * llamar al metodo operation() para ejecutar dicha operacion
	 * 
	 * @param e es el objeto que lee los valores de los botones que ha pulsado el
	 *          usuario
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String input_text = e.getActionCommand();

		String texto = this.display.getText();
		// Expresion regular para separar el texto del display
		String regex = "(-?\\d+)([+-X/^])(-?\\d)";
		// Expresion regular especial para la raiz cuadrada
		String regexRaiz = "(√)(\\d+)";
		// Expresion regular especial para el factorial
		String regexFactorial = "(\\d+)(!)";
		Pattern pattern = Pattern.compile(regex);
		Pattern patternRaiz = Pattern.compile(regexRaiz);
		Pattern patternFactorial = Pattern.compile(regexFactorial);
		Matcher matcher = pattern.matcher(texto);
		Matcher matcherRaiz = patternRaiz.matcher(texto);
		Matcher matcherFactorial = patternFactorial.matcher(texto);

		if (matcher.matches()) {
			this.num1 = Integer.parseInt(matcher.group(1));
			this.operation = matcher.group(2).charAt(0);
			this.num2 = Integer.parseInt(matcher.group(3));

		} else if (matcherRaiz.matches()) {
			this.operation = matcherRaiz.group(1).charAt(0);
			this.num1 = Integer.parseInt(matcherRaiz.group(2));

		} else if (matcherFactorial.matches()) {
			this.num1 = Integer.parseInt(matcherFactorial.group(1));
			this.operation = matcherFactorial.group(2).charAt(0);

		} else {
			this.result = 0;
		}

		// El usuario pulsa reset
		if (source.equals(this.reset)) {
			this.display.setText("");
			this.result = 0;
			this.num1 = 0;
			this.num2 = 0;

			// El usuario pulsa equal
		} else if (source.equals(this.equal)) {
			if (this.operation == '/' && this.num2 == 0) {
				this.display.setText("ERROR");
			} else {
				operation();
				this.display.setText(String.valueOf(this.result));
			}

			// El usuario pulsa delete
		} else if (source.equals(this.delete)) {
			this.display.setText(this.display.getText().substring(0, this.display.getText().length() - 1));

			// El usuario pulsa cualquier otro boton
		} else {
			this.display.setText(this.display.getText() + input_text);
		}
	}

	/**
	 * Metodo para calcular el factorial de un numero, sirve para efectuar la
	 * operacion del factorial
	 * 
	 * @param num es el numero del que se quiere calcular el factorial (this.num1)
	 * @return devuelve el resultado del factorial (this.result)
	 */
	public int factorial(int num) {
		int factorial = 1;
		for (int i = num; i > 0; i--) {
			factorial *= i;
		}
		return factorial;
	}

}
