package main;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

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

	// Panel central que contiene el display
	private JPanel displayPanel;

	// Panel norte que contiene informacion sobre la calculadora
	private JPanel infoPanel;

	// Panel izquierdo de infoPanel, indica la base en la que se esta operando
	private JPanel basePanel;

	// Panel derecho de infoPanel, indica la marca de la calculadora
	private JPanel brandPanel;

	// Panel sur que contiene los botones
	private JPanel buttonPanel;

	// Display
	private JTextField display;

	// Botones V1.0
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
	// Nuevas funcionalidades V1.0
	private JButton delete;
	private JButton raiz;
	private JButton potencia;
	private JButton factorial;
	// Botones V2.0
	private JButton brand;
	private JButton baseBinaria;
	private JButton baseOctal;
	private JButton baseDecimal;
	private JButton baseHexa;
	private JButton nD;
	private JButton nE;
	private JButton nF;
	private JButton info;
	private JButton nA;
	private JButton nB;
	private JButton nC;
	private JButton owner;
	private JTextField baseActual;

	// Tipos de boton
	private enum ButtonType {
		REGULAR, OPERATOR, BASE, BRAND, LETRAS, OTRO
	};

	// Almacenar temporalmente ciertos valores
	private String num1, num2;
	private int result;
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

		// Inicializacion del panel que contiene el display. (Panel Central)
		this.displayPanel = new JPanel();

		// Inicializacion del panel que contiene los botones. (Panel Sur)
		this.buttonPanel = new JPanel();

		// Inicialización del panel que contiene información de la calculadora (Panel
		// Norte)
		this.infoPanel = new JPanel();

		// Inicialización del panel izquierdo del infoPanel
		this.basePanel = new JPanel();

		// Inicialización del panel derecho del infoPanel
		this.brandPanel = new JPanel();

		// Inicializacion del display que actua como la pantalla de la calculadora
		this.display = new JTextField();

		// Inicializacion de los botones V1.0
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

		this.divide = new JButton("/");

		this.multiply = new JButton("X");

		this.subtract = new JButton("-");

		this.add = new JButton("+");

		this.equal = new JButton("=");

		this.reset = new JButton("AC");

		this.delete = new JButton("<");

		this.raiz = new JButton("√");

		this.potencia = new JButton("^");

		this.factorial = new JButton("!");

		// Inicializacion de los botones V2.0

		this.brand = new JButton("CASIO");

		this.baseBinaria = new JButton("B2");

		this.baseOctal = new JButton("B8");

		this.baseDecimal = new JButton("B10");

		this.baseHexa = new JButton("B16");

		this.nD = new JButton("D");

		this.nE = new JButton("E");

		this.nF = new JButton("F");

		this.nA = new JButton("A");

		this.nB = new JButton("B");

		this.nC = new JButton("C");

		this.info = new JButton("Info");

		this.owner = new JButton("Owner");

		this.baseActual = new JTextField();

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
		this.display.setFont(new Font("Source Code Pro", 2, 40));
		this.display.setForeground(Color.BLACK);

		this.baseActual.setFont(new Font("Source Code Pro", 2, 20));
		this.baseActual.setForeground(Color.BLACK);
		this.baseActual.setPreferredSize(new Dimension(200, 50));
		this.baseActual.setEditable(false);
		this.baseActual.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// Configuracion del panel principal
		this.contentPanel.setLayout(new BorderLayout());

		// Configuracion del panel que contiene el display. (Panel Central)
		this.displayPanel.setLayout(new GridLayout(1, 1));
		this.displayPanel.add(this.display);

		// Configuracion del panel que contiene los botones. (Panel Sur)
		this.buttonPanel.setLayout(new GridLayout(8, 4));

		// Configuración del panel de informacion. (Panel Norte)
		this.infoPanel.setLayout(new GridLayout(1, 2));
		this.basePanel.add(this.baseActual);
		this.infoPanel.add(this.basePanel, BorderLayout.WEST);
		this.brandPanel.add(this.brand);
		setFeaturesButton(this.brand, ButtonType.BRAND);
		this.infoPanel.add(this.brandPanel, BorderLayout.EAST);

		// Primera fila
		this.buttonPanel.add(this.baseBinaria);
		setFeaturesButton(this.baseBinaria, ButtonType.BASE);
		this.buttonPanel.add(this.baseOctal);
		setFeaturesButton(this.baseOctal, ButtonType.BASE);
		this.buttonPanel.add(this.baseDecimal);
		setFeaturesButton(this.baseDecimal, ButtonType.BASE);
		this.buttonPanel.add(this.baseHexa);
		setFeaturesButton(this.baseHexa, ButtonType.BASE);

		// Segunda fila
		this.buttonPanel.add(this.nD);
		setFeaturesButton(this.nD, ButtonType.LETRAS);
		this.buttonPanel.add(this.nE);
		setFeaturesButton(this.nE, ButtonType.LETRAS);
		this.buttonPanel.add(this.nF);
		setFeaturesButton(this.nF, ButtonType.LETRAS);
		this.buttonPanel.add(this.info);
		setFeaturesButton(this.info, ButtonType.OTRO);

		// Tercera fila
		this.buttonPanel.add(this.nA);
		setFeaturesButton(this.nA, ButtonType.LETRAS);
		this.buttonPanel.add(this.nB);
		setFeaturesButton(this.nB, ButtonType.LETRAS);
		this.buttonPanel.add(this.nC);
		setFeaturesButton(this.nC, ButtonType.LETRAS);
		this.buttonPanel.add(this.owner);
		setFeaturesButton(this.owner, ButtonType.OTRO);

		// Cuarta fila
		this.buttonPanel.add(this.raiz);
		setFeaturesButton(this.raiz, ButtonType.OPERATOR);
		this.buttonPanel.add(this.potencia);
		setFeaturesButton(this.potencia, ButtonType.OPERATOR);
		this.buttonPanel.add(this.factorial);
		setFeaturesButton(this.factorial, ButtonType.OPERATOR);
		this.buttonPanel.add(this.divide);
		setFeaturesButton(this.divide, ButtonType.OPERATOR);

		// Quinta fila
		this.buttonPanel.add(this.n7);
		setFeaturesButton(this.n7, ButtonType.REGULAR);
		this.buttonPanel.add(this.n8);
		setFeaturesButton(this.n8, ButtonType.REGULAR);
		this.buttonPanel.add(this.n9);
		setFeaturesButton(this.n9, ButtonType.REGULAR);
		this.buttonPanel.add(this.multiply);
		setFeaturesButton(this.multiply, ButtonType.OPERATOR);

		// Sexta fila
		this.buttonPanel.add(this.n4);
		setFeaturesButton(this.n4, ButtonType.REGULAR);
		this.buttonPanel.add(this.n5);
		setFeaturesButton(this.n5, ButtonType.REGULAR);
		this.buttonPanel.add(this.n6);
		setFeaturesButton(this.n6, ButtonType.REGULAR);
		this.buttonPanel.add(this.subtract);
		setFeaturesButton(this.subtract, ButtonType.OPERATOR);

		// Septima fila
		this.buttonPanel.add(this.n1);
		setFeaturesButton(this.n1, ButtonType.REGULAR);
		this.buttonPanel.add(this.n2);
		setFeaturesButton(this.n2, ButtonType.REGULAR);
		this.buttonPanel.add(this.n3);
		setFeaturesButton(this.n3, ButtonType.REGULAR);
		this.buttonPanel.add(this.add);
		setFeaturesButton(this.add, ButtonType.OPERATOR);

		// Octava fila
		this.buttonPanel.add(this.reset);
		setFeaturesButton(this.reset, ButtonType.OPERATOR);
		this.buttonPanel.add(this.n0);
		setFeaturesButton(this.n0, ButtonType.REGULAR);
		this.buttonPanel.add(this.delete);
		setFeaturesButton(this.delete, ButtonType.OPERATOR);
		this.buttonPanel.add(this.equal);
		setFeaturesButton(this.equal, ButtonType.OPERATOR);

		// Insertar el panel que contiene el display (Panel Norte) al panel principal
		this.contentPanel.add(this.displayPanel, BorderLayout.CENTER);

		// Insertar el panel que contiene los botones (Panel Sur) al panel principal
		this.contentPanel.add(this.buttonPanel, BorderLayout.SOUTH);

		// Insertar el panel que contiene la info (Panel Norte) al panel principal
		this.contentPanel.add(this.infoPanel, BorderLayout.NORTH);

		// Propiedades de la ventana
		this.frame.add(this.contentPanel);
		this.frame.setLocation(200, 200);
		this.frame.setSize(500, 650);
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
		switch (_type) {
		case REGULAR:
			_button.setBackground(new Color(255, 164, 99)); // Color 4
			_button.setFont(new Font("Source Code Pro", 0, 25));
			_button.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			break;
		case OPERATOR:
			_button.setBackground(new Color(253, 216, 110)); // Color 3
			_button.setFont(new Font("Source Code Pro", 0, 25));
			_button.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			break;
		case BASE:
			_button.setBackground(new Color(251, 96, 102)); // Color 1
			_button.setFont(new Font("Source Code Pro", 0, 25));
			_button.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			break;
		case BRAND:
			_button.setBackground(new Color(212, 206, 95)); // Color 6
			_button.setFont(new Font("Source Code Pro", 0, 25));
			_button.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			break;
		case LETRAS:
			_button.setBackground(new Color(246, 107, 64)); // Color 5
			_button.setFont(new Font("Source Code Pro", 0, 25));
			_button.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			break;
		case OTRO:
			_button.setBackground(new Color(255, 239, 193)); // Color 2
			_button.setFont(new Font("Source Code Pro", 0, 25));
			_button.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			break;

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
		// V2.0
		this.brand.addActionListener(this);
		this.baseBinaria.addActionListener(this);
		this.baseOctal.addActionListener(this);
		this.baseDecimal.addActionListener(this);
		this.baseHexa.addActionListener(this);
		this.nD.addActionListener(this);
		this.nE.addActionListener(this);
		this.nF.addActionListener(this);
		this.info.addActionListener(this);
		this.nA.addActionListener(this);
		this.nB.addActionListener(this);
		this.nC.addActionListener(this);
		this.owner.addActionListener(this);
	}

	/**
	 * Metodo que realiza las operaciones y divide el display en los operandos.
	 */
	public void operation() {
		int num1 = 0;
		int num2 = 0;
		switch (this.baseActual.getText()) {
		case "Base: decimal":
			String texto = this.display.getText();
			// Expresion regular para separar el texto del display
			String regex = "(-?\\d+)([+-X/^])(-?\\d+)";
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
				this.num1 = matcher.group(1);
				num1 = Integer.parseInt(this.num1);
				this.operation = matcher.group(2).charAt(0);
				this.num2 = matcher.group(3);
				num2 = Integer.parseInt(this.num2);

			} else if (matcherRaiz.matches()) {
				this.operation = matcherRaiz.group(1).charAt(0);
				this.num1 = matcherRaiz.group(2);
				num1 = Integer.parseInt(this.num1);

			} else if (matcherFactorial.matches()) {
				this.num1 = matcherFactorial.group(1);
				num1 = Integer.parseInt(this.num1);
				this.operation = matcherFactorial.group(2).charAt(0);

			} else {
				this.result = 0;
			}

			switch (this.operation) {
			case '+':
				this.result = num1 + num2;
				break;
			case '-':
				this.result = num1 - num2;
				break;
			case 'X':
				this.result = num1 * num2;
				break;
			case '/':
				if (num2 == 0) {
					this.display.setText("Error: División entre 0");
				} else {
					this.result = num1 / num2;
				}
				break;
			case '\u221A':
				this.result = (int) Math.sqrt(num1);
				break;
			case '^':
				this.result = (int) Math.pow(num1, num2);
				break;
			case '!':
				this.result = factorial(num1);
				break;
			default:
				break;

			}
			break;
		case "Base: binaria":
			String texto_binario = this.display.getText();
			String regex_binario = "([01]+)([+-X/])([01]+)";
			Pattern pattern_binario = Pattern.compile(regex_binario);
			Matcher matcher_binario = pattern_binario.matcher(texto_binario);

			if (matcher_binario.matches()) {
				this.num1 = matcher_binario.group(1);
				num1 = Integer.parseInt(this.num1, 2);
				this.operation = matcher_binario.group(2).charAt(0);
				this.num2 = matcher_binario.group(3);
				num2 = Integer.parseInt(this.num2, 2);
			} else {
				this.result = 0;
			}

			switch (this.operation) {
			case '+':
				this.result = num1 + num2;
				break;
			case '-':
				this.result = num1 - num2;
				break;
			case 'X':
				this.result = num1 * num2;
				break;
			case '/':
				if (num2 != 0) {
					this.result = num1 / num2;
				} else {
					this.display.setText("ERROR: División entre 0");
				}

				break;
			default:
				this.result = 0;
			}
			break;
		case "Base: octal":
			String texto_octal = this.display.getText();
			String regex_octal = "([0-7]+)([+-X/])([0-7]+)";
			Pattern pattern_octal = Pattern.compile(regex_octal);
			Matcher matcher_octal = pattern_octal.matcher(texto_octal);

			if (matcher_octal.matches()) {
				this.num1 = matcher_octal.group(1);
				num1 = Integer.parseInt(this.num1, 8);
				this.operation = matcher_octal.group(2).charAt(0);
				this.num2 = matcher_octal.group(3);
				num2 = Integer.parseInt(this.num2, 8);
			} else {
				this.result = 0;
			}

			switch (this.operation) {
			case '+':
				this.result = num1 + num2;
				break;
			case '-':
				this.result = num1 - num2;
				break;
			case 'X':
				this.result = num1 * num2;
				break;
			case '/':
				if (num2 != 0) {
					this.result = num1 / num2;
				} else {
					this.display.setText("ERROR: División entre 0");
				}
				break;
			default:
				this.result = 0;
			}
			break;
		case "Base: hexadecimal":
			String texto_hexa = this.display.getText();
			String regex_hexa = "([0-9a-fA-F]+)([+-/X])([0-9a-fA-F]+)";
			Pattern pattern_hexa = Pattern.compile(regex_hexa);
			Matcher matcher_hexa = pattern_hexa.matcher(texto_hexa);

			if (matcher_hexa.matches()) {
				this.num1 = matcher_hexa.group(1).toLowerCase();
				num1 = Integer.parseInt(this.num1, 16);
				this.operation = matcher_hexa.group(2).charAt(0);
				this.num2 = matcher_hexa.group(3).toLowerCase();
				num2 = Integer.parseInt(this.num2, 16);
			} else {
				this.result = 0;
			}

			switch (this.operation) {
			case '+':
				this.result = num1 + num2;
				break;
			case '-':
				this.result = num1 - num2;
				break;
			case '/':
				if (num2 != 0) {
					this.result = num1 / num2;
				} else {
					this.display.setText("ERROR: División entre 0");
				}
				break;
			case 'X':
				this.result = num1 * num2;
				break;
			default:
				this.result = 0;
			}
			break;

		default:
			this.result = 0;
			break;

		}
		transformaResultados();

	}

	/**
	 * Metodo que transforma el resultado de las operaciones a la base
	 * correspondiente y lo escribe en el display
	 */
	public void transformaResultados() {
		switch (this.baseActual.getText()) {
		case "Base: decimal":
			this.display.setText(String.valueOf(this.result));
			break;
		case "Base: binaria":
			String resultado_binario = Integer.toBinaryString(this.result);
			this.display.setText(resultado_binario);
			break;
		case "Base: octal":
			String resultado_octal = Integer.toOctalString(this.result);
			this.display.setText(resultado_octal);
			break;
		case "Base: hexadecimal":
			String resultado_hexa = Integer.toHexString(this.result).toUpperCase();
			this.display.setText(resultado_hexa);
			break;
		default:
			this.display.setText("ERROR");
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
		// El usuario pulsa reset
		if (source.equals(this.reset)) {
			this.display.setText("");
			this.baseActual.setText("Elige una base");
			this.result = 0;
			this.num1 = null;
			this.num2 = null;

			// El usuario pulsa equal con la base decimal
		} else if (source.equals(this.equal)) {
			operation();

			// El usuario pulsa delete
		} else if (source.equals(this.delete))

		{
			if (this.display.getText().length() > 0) {
				this.display.setText(this.display.getText().substring(0, this.display.getText().length() - 1));
			} else {
				this.display.setText("");
			}

			// El usuario pulsa el boton de la marca de la calculadora
		} else if (source.equals(this.brand)) {
			try {
				Desktop.getDesktop().browse(new URI("https://www.casio.com/es/scientific-calculators/"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// El usuario pulsa el boton de base binaria
		} else if (source.equals(this.baseBinaria)) {
			if (this.baseActual.getText().length() > 0) {
				int texto_display = Integer.valueOf(this.display.getText());
				String texto_nuevo = Integer.toBinaryString(texto_display);
				this.display.setText(texto_nuevo);
			} else {
				this.baseActual.setText("Base: binaria");
			}

			// El usuario pulsa el boton de la base decimal
		} else if (source.equals(this.baseDecimal)) {
			if (this.baseActual.getText().length() > 0) {
				int texto_nuevo = Integer.parseInt(this.display.getText());
				this.display.setText(String.valueOf(texto_nuevo));
			} else {
				this.baseActual.setText("Base: decimal");
			}

			// El usuario pulsa el boton de la base octal
		} else if (source.equals(this.baseOctal)) {
			if (this.baseActual.getText().length() > 0) {
				int texto_display = Integer.valueOf(this.display.getText());
				String texto_nuevo = Integer.toString(texto_display);
				this.display.setText(texto_nuevo);
			} else {
				this.baseActual.setText("Base: octal");
			}

			// El usuario pulsa el boton de la base hexadecimal
		} else if (source.equals(this.baseHexa)) {
			if (this.baseActual.getText().length() > 0) {
				int texto_display = Integer.valueOf(this.display.getText());
				String texto_nuevo = Integer.toHexString(texto_display);
				this.display.setText(texto_nuevo);
			} else {
				this.baseActual.setText("Base: hexadecimal");
			}
			// El usuario pulsa el boton de owner
		} else if (source.equals(this.owner)) {
			VentanaEmergente pop = new VentanaEmergente("Esta calculadora es propiedad de Elena López Félix");

			// El usuario pulsa el boton de info
		} else if (source.equals(this.info)) {
			VentanaEmergente pop = new VentanaEmergente(
					"Esta calculadora se ha hecho en la asignatura de Desarrollo de Interfaces");

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
