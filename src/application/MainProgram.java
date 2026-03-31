package application;

// Importamos la clase Scanner qeu es propia de java
import java.util.Scanner;

import list.ListExercise;

//Acá definimos la clase
public class MainProgram {
	
	//Acá creamos una variable donde vaya a haber una instancia de un ejercicio
	//importante poner el private, no se sobreentiende
	private boolean running = true;
	private Exercise exercise;

	//Main se ejecuta acá al inicio, es la puerta de entrada al codigo. Se ejecuta sola
	public static void main(String[] args) {
		
		//Main ejecuta una instancia de esta misma clase, empieza la función run
		//Esto crea una instancia del programa y automáticamente la corre. 
		
		new MainProgram().run();
		
	}
	
	private void run()
	{
		
		//Scanner es una clase que ya creó alguien. No la tenemos que crear nosotros sino importar. AL principio de todo el programa  (linea 4) ya la invocamos
		//Scanner scanner ahí estamos definiendo al scanner con el nombre de scanner porque somos re creativos
		// new Scanner porque estamos dandole valor de scanner a ese nuevo scanner 
		//System.in es porque toma cosas del usuario
		Scanner scanner = new Scanner(System.in);
		
		// Esto es como poner "mientras running sea true"
		while(running)
		{
			//Acá invocamos al scanner (CREO QUE ES UN METODO. es para invocar al scanner)
			selectExercise(scanner);
			
			//Chequeamos que no sea null así no explota
			//NO hace falta ponerle llaves a los ifs si solo estás ejecutando una sola linea. 
			if(exercise != null) { 
				exercise.run();
			}
			

		}
		scanner.close();
		System.out.println("Program Terminated");
	}
	
	//la función crea un Scanner que llamamos scanner
	void selectExercise(Scanner scanner)
	{
		System.out.println("Select an option: " + 
				"\n0 - Terminate Program" +
				"\n1 - Test Exercise"+
				"\n2 - List Exercise");
		
		//Esto es para almacenar en un string lo que puso el usuario
		String userInput = scanner.nextLine();
		
		switch(userInput)
		{
		case "0":
			running = false; 
			break;
		case "1":
			//acá hay que instanciar un ejercicio concreto y almacenarlo en la variable exercise
			exercise = new TestExercise(scanner);
			break;
		case "2":
			exercise = new ListExercise(scanner);
			break;
		default:
			//Esto sería como el "else" de python
			System.out.println("Invalid input, try again");
			//ahora con esto llamamos a la misma función otra vez:
			selectExercise(scanner);
			break;
		}
		
}
}
