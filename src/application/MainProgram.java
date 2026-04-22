package application;

// Importamos la clase Scanner que es propia de java
import java.util.Scanner;

import listModule.ListExercise;
import priorityQueueModule.PriorityQueueExercise;
import queueModule.QueueExercise;
import setModule.SetExercise;
import stackModule.StackExercise;

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
		System.out.println("Programa finalizado");
	}

	//la función crea un Scanner que llamamos scanner
	void selectExercise(Scanner scanner)
	{
		// Limpiamos la consola antes de mostrar el menú principal
		for (int i = 0; i < 50; i++) System.out.println();

		System.out.println("Seleccionar una opción: " +
				"\n0 - Terminar el programa" +
				"\n1 - Ejercicio de prueba"+
				"\n2 - Ejercicio de listas"+
				"\n3 - Ejercicio de pilas" +
				"\n4 - Ejercicio de colas" +
				"\n5 - Ejercicio de conjuntos" +
				"\n6 - Ejercicio de colas de prioridad");

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
		case "3":
			exercise = new StackExercise(scanner);
			break;
		case "4":
			exercise = new QueueExercise(scanner);
			break;
		case "5":
			exercise = new SetExercise(scanner);
			break;
		case "6":
			exercise = new PriorityQueueExercise(scanner);
			break;
		default:
			//Esto sería como el "else" de python
			System.out.println("Opción inválida, intentar de nuevo");
			//ahora con esto llamamos a la misma función otra vez:
			selectExercise(scanner);
			break;
		}

}
}
