package application;

// Esto hay que importarlo otra vez
import java.util.Scanner;

public class TestExercise extends Exercise {

	public TestExercise(Scanner scnr) {

		//Terminar esto de super(scnr)!!!!!!!!!!!!!!!!!!!!!!!
		super(scnr);
	}

	//Esto de override se pone solo y es lo que se hereda de la clase padre
	//"Sí o sí tenemos que overridearlo porque no hay nada en el original respecto de esta función"

	@Override
	protected void exerciseLogic() {


		System.out.println("Bienvenido al ejercicio de prueba"
				+ "\nmm: Menú principal");

		String userInput = scanner.nextLine().toLowerCase();

		//acá no necesitamos un switch porque solo hay una sola posibilidad

		if (userInput.equals("mm")) {
			running = false;
		}

	}

}
