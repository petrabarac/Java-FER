package hr.fer.zemris.lsystems.impl;

import java.awt.Color;

import hr.fer.zemris.java.custom.collections.*;
import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilder;
import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.commands.ColorCommand;
import hr.fer.zemris.lsystems.impl.commands.DrawCommand;
import hr.fer.zemris.lsystems.impl.commands.PopCommand;
import hr.fer.zemris.lsystems.impl.commands.PushCommand;
import hr.fer.zemris.lsystems.impl.commands.RotateCommand;
import hr.fer.zemris.lsystems.impl.commands.ScaleCommand;
import hr.fer.zemris.lsystems.impl.commands.SkipCommand;
import hr.fer.zemris.math.Vector2D;

import static java.lang.Math.*;

/**
 * Razred koji implementira sučelje LSystemBuilder.
 * Sučelje LSystemBuilder modelira objekte koje je moguće
 * konfigurirati i potom pozvati metodu build()
 * koja vraća jedan konkretan Lindermayerov sustav prema
 * zadanoj konfiguraciji.
 * Razred preko dva primjerka razreda Dictionary pamti registrirane
 * produkcije i registrirane akcije (tj.naredbe)   
 * 
 * (kornjača - sinonim za objekt koji kako bi lakše implementirali crtanje po ekranu.
 *   Nalazi se na ekranu te se može kretati po njemu i ostavljati trag)
 *  
 * @author Petra Barać
 *
 */
public class LSystemBuilderImpl implements LSystemBuilder{
	
	/**
	 * koliko je dugačak jedinični pomak kornjače; 
	 * primjerice, vrijednost 0.5 ako kornjača gleda u desno bi
	 *  odgovaralo polovici širine prozora
	 */
	private double unitLength;
	/**
	 * kako bi se dimenzije prikazanog fraktala zadržale manje-više 
	 * konstantnima, ako se generira niz za dubinu d , unitLength je 
	 * potrebno na odgovarajući način skalirati; stoga je početnu efektivnu
	 *  duljinu koraka za kornjaču potrebno postaviti na unitLength * (unitLengthDegreeScaler^d)
	 */
	private double unitLengthDegreeScaler;
	/**
	 * zadaje točku iz koje kornjača kreće
	 */
	private Vector2D origin;
	/**
	 * kut prema pozitivnoj osi-x smjera u kojem kornjača gleda
	 */
	private double angle;
	/**
	 * : početni niz iz kojeg kreće razvoj sustava
	 */
	private String axiom;
	
	/**
	 * Dictionary registriranih produkcija
	 */
	Dictionary productions;
	/**
	 * Dictionary registriranih akcija
	 */
	Dictionary actions;
	
	public LSystemBuilderImpl() {
		actions = new Dictionary();
		productions = new Dictionary();
		
		unitLength = 0.1;
		unitLengthDegreeScaler = 1;
		origin = new Vector2D(0, 0);
		angle = 0;
		axiom = "";
	}
	
	
	/**
	 * Razred implementira sučelje LSystems.
	 * 
	 * @author Petra Barać
	 *
	 */
	private class create implements LSystem{

		/**
		 * Metoda stvara novi kontekst, stvara novo stanje kornjače
		 * te ga gura na stog. Za zadanu dubinu poziva generiranje
		 * konačnog znakovnog niza te na kraju za svaki znak generiranog
		 * niza iz riječnika dohvaća naredbe (ako naredbe nema prelazi se na 
		 * sljedeči znak; ako naredba postoji, izvršava se, čime se ili modificira trenutno stanje,
		 * ili se nešto crta na zaslonu, ili se radi kopija trenutnog stanja i gura na stog, ili
		 * se sa stoga skida jedno stanje čime prethodno sa stoga postaje aktualno trenutno stanje)
		 * i zatim prelazi na sljedeći znak.)
		 * 
		 * @param level dubina za kou će se generirati konačni znakovni niz
		 * @param painter painter
		 */
		@Override
		public void draw(int level, Painter painter) {
			Context contex = new Context();
			TurtleState state = new TurtleState(origin.copy(), new Vector2D(cos(toRadians(angle)), sin(toRadians(angle))),
					Color.black, unitLength * pow(unitLengthDegreeScaler,level));
			
			contex.pushState(state);
			String generateString = generate(level);
			
			
			for(int i = 0, length = generateString.length(); i < length; i++) {
				Command value = (Command)actions.get(generateString.charAt(i)); 
				if(value != null) {
							value.execute(contex, painter);			
				}
			}
		}
		
		/**
		 * Metoda za zadanu dubinu generira konačni znakovni niz
		 * @param level zadana dubina
		 * @exception IllegalArgumentException ako je zadani level manji od 0
		 */

		@Override
		public String generate(int level) {
			if(level < 0) {
				throw new IllegalArgumentException("Level can not be less than zero");
			}
			
			String string = axiom;
			
			for(int i = 0; i < level; i++) {
				StringBuilder sb = new StringBuilder();
								
				for(int j = 0, length = string.length(); j <length ; j++) {
					
					String production = (String)productions.get(string.charAt(j));
					if( production != null) {
						sb.append(production);
					} else {
						sb.append(string.charAt(j));
					}	
				}
				string = sb.toString();	
	
			}
			return string;
		}		
	}
	
	/**
	 * Stvara jedan konkretan LSystem
	 * @return primjerak razreda koji implementira sučelje LSystem
	 */
	@Override
	public LSystem build() {
		return new create();
	}
	
	/**
	 * Metoda za dobiveni niz linija radi konfiguraciju.
	 * Pohranjuje niz komandi i produkcija u dictinarye.
	 * Ostale konfiguracije postavlja odmah (npr. "axiom F" postavlja
	 * početni axsiom na "F")
	 * 
	 * @param data primjeni niz stringova
	 * @exception IllegalArgumentException ako je zadana nepodržavajuća komanda
	 */
	@Override
	public LSystemBuilder configureFromText(String[] data) {
		
		
		for(int i = 0; i < data.length; i++) {
			String input = data[i];
			
			String[] splitted = input.trim().split("\\s+");
			
			if(splitted[0].equalsIgnoreCase("command")) {
				if(splitted.length == 4) {
				 registerCommand(splitted[1].charAt(0), splitted[2] + " " + splitted[3]);
				 continue;
				} else if(splitted[2].toLowerCase().equals("push") || splitted[2].toLowerCase().equals("pop")) {
					 registerCommand(splitted[1].charAt(0), splitted[2]);
					 continue;
				}
				else {
					throw new IllegalArgumentException("Wrong input for command: command");
				}
			}
			
			if(splitted[0].equalsIgnoreCase("production")) {
				if(splitted.length == 3) {
					 registerProduction(splitted[1].charAt(0), splitted[2]);
					 continue;
				} else {
					throw new IllegalArgumentException("Wrong input for command: production");
				}				
			}
			
			if(splitted[0].equalsIgnoreCase("origin")) {
				if(splitted.length == 3) {
					double number1 = extractNumber(splitted, 1);
					double number2 =  extractNumber(splitted, 2);
				    setOrigin(number1,  number2);
				    continue;
			
				} else {
					throw new IllegalArgumentException("Wrong input for command: origin");
				}				
			}
			
			if(splitted[0].equalsIgnoreCase("angle")) {
				if(splitted.length == 2) {
					double angle = extractNumber(splitted, 1);
				    setAngle(angle);
				    continue;
				} else {
					throw new IllegalArgumentException("Wrong input for command: angle");
				}	
			}
			
			if(splitted[0].equalsIgnoreCase("axiom")) {
				if(splitted.length == 2) {
					 setAxiom(splitted[1]);
					 continue;
				} else {
					throw new IllegalArgumentException("Wrong input for command: axiom");
				}	
			}
			
			if(splitted[0].equalsIgnoreCase("unitLength")) {
				if(splitted.length == 2) {
					double unitLength = extractNumber(splitted, 1);
					 setUnitLength(unitLength);
					 continue;
				} else {
					throw new IllegalArgumentException("Wrong input for command: unitLength");
				}	
			}
			
			if(splitted[0].equalsIgnoreCase("unitLengthDegreeScaler")) {
				int beginIndex = splitted[0].length();
				int endIndex = input.length();
				double scaler = 0;
				
				String argument = input.substring(beginIndex, endIndex).trim();
			
				
				if(argument.contains("/")) {
					String[] numbers = argument.split("/");
					double number1 = extractNumber(numbers, 0);
					double number2 = extractNumber(numbers, 1);					
					scaler = number1 / number2;
					
				} else {
					scaler = extractNumber(splitted, 1);
				}
			
				 setUnitLengthDegreeScaler(scaler);
				 continue;
			}
		}
		return this;
	}
	

	/**
	 * Metoda pohranjuje symbol te komandu(akciju) vezanu za symbol u actions dictionary.
	 * @param symbol simbol kojim je akcija definirana
	 * @param command akcija koja se izvršava za predani symbol
	 */
	@Override
	public LSystemBuilder registerCommand(char symbol, String command) {
		
		Command extractedCommand = extractComand(command);
		actions.put(symbol, extractedCommand);
		return this;
	}
	
	/**
	 * Metoda za primjeni string provjerava o kojoj se komandi radi
	 * te ako je komanda ispravno zadana, vraća se.
	 * (npr. ako je komanda zadana strongom "draw" vraća se DrawCommand)
	 * 
	 * @param command komanda pohranjena u stringu
	 * @return jednu od komandi koja mora biti primjerak razreda hr.fer.zemris.lsystems.impl.commands
	 * @exception IllegalArgumentException ako je komanda krivo zadana
	 */
	private Command extractComand(String command)  {
		
		String[] splittedCommand = command.trim().split("\\s+");

		
		if(splittedCommand[0].startsWith("draw")) {
			if(splittedCommand.length == 2) {
				double step = extractNumber(splittedCommand, 1);
				return new DrawCommand(step);
			} else {
				throw new IllegalArgumentException("Command draw can not have more than 1 argument");
			}	
		}
		
		if(splittedCommand[0].startsWith("rotate")) {
			if(splittedCommand.length == 2) {
				double angle = extractNumber(splittedCommand, 1);
				return new RotateCommand(angle);
			} else {
				throw new IllegalArgumentException("Command rotate can not have more than 1 argument");
			}	
		}
		
		if(splittedCommand[0].startsWith("skip")) {
			if(splittedCommand.length == 2) {
				double step = extractNumber(splittedCommand, 1);
				return new SkipCommand(step);
			} else {
				throw new IllegalArgumentException("Command skip can not have more than 1 argument");
			}	
		}
		
		if(splittedCommand[0].startsWith("scale")) {
			if(splittedCommand.length == 2) {
				double factor = extractNumber(splittedCommand, 1);
				return new ScaleCommand(factor);
			} else {
				throw new IllegalArgumentException("Command scale can not have more than 1 argument");
			}	
		}
		
		if(splittedCommand[0].startsWith("push")) {
			if(splittedCommand.length == 1) {
				return new PushCommand();
			} else {
				throw new IllegalArgumentException("Command push can not have arguments");
			}	
		}
		
		if(splittedCommand[0].startsWith("pop")) {
			if(splittedCommand.length == 1) {
				return new PopCommand();
			} else {
				throw new IllegalArgumentException("Command pop can not have arguments");
			}	
		}
		
		if(splittedCommand[0].startsWith("color")) {
			if(splittedCommand.length == 2) {
				Color color = extractColor(splittedCommand[1]);
				return new ColorCommand(color);
			} else {
				throw new IllegalArgumentException("Command pop can not have arguments");
			}	
		} else {
			throw new IllegalArgumentException("Illigal command" + " " + command);
		}
		
	}
	
	/**
	 * Metoda iz zadanog stringa za boju izvodu boju te je vraća
	 * @param color zadana boja
	 * @return boju
	 * @exception NumberFormatException ako je boja krivo zadana
	 */
	private Color extractColor(String color) {
		try {
			return Color.decode("#" + color);
		} catch (NumberFormatException e) {
			System.out.println("Invalid hexadecimal representation of the requested color.");
		}

		return null;
	}
	
	/**
	 * 
	 * @param s korak(step) pohranjen u string formatu
	 * @param stepIndex mjesto na kojemu bi se step trebao nalaziti u danom stringu
	 * @exception ArithmeticException ako step nije momguće parsirati u double
	 * @return step
	 */
	private double extractNumber(String[] s, int stepIndex) {
		double step = 0;
		try {
		    step = Double.parseDouble(s[stepIndex]);
			
		} catch(ArithmeticException ex) {
			System.out.println(ex.getMessage());
		}
		return step;
	}
	
	/**
	 * Metoda pohranjuje symbol i produkciju u Dictionary productions 
	 * @param symbol prednai symbol za danu produkciju
	 * @param production produkcija koja se veže za symbol
	 *
	 */
	@Override
	public LSystemBuilder registerProduction(char symbol, String production) {
		productions.put(symbol, production);
		return this;
	}
	/**
	 * Metoda postavlja kut gledanja kornjače
	 * @param angle kut prema pozitivnoj osi-x smjera u kojem kornjača gleda
	 */
	@Override
	public LSystemBuilder setAngle(double angle) {
		this.angle = angle;
		return this;
	}
	
	/**
	 * Postavlja početni axiom na primljeni string
	 * @param axiom početni axiom
	 */
	@Override
	public LSystemBuilder setAxiom(String axiom) {
		this.axiom = axiom;
		return this;
	}
	
	/**
	 * Metoda postavlja početnu poziciju kornjače na primljenu vrijednost
	 * 
	 */
	@Override
	public LSystemBuilder setOrigin(double x, double y) {
		this.origin = new Vector2D(x, y);
		return this;
	}
	
	/**
	 * Postavlja veličinu od unitLength na primljenu vrijednost.
	 */
	@Override
	public LSystemBuilder setUnitLength(double unitLength) {
		this.unitLength = unitLength;
		return this;
	}
	
	/**
	 * postavlja unitLengthDegreeScaler na primljenu vrijednost
	 */
	@Override
	public LSystemBuilder setUnitLengthDegreeScaler(double unitLengthDegreeScaler) {
		this.unitLengthDegreeScaler = unitLengthDegreeScaler;
		return this;
	}
	
}