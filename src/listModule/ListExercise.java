package listModule;

import java.util.Scanner;

import application.Exercise;

public class ListExercise extends Exercise {

	private int currentPhase = 0;
	private boolean firstTime = true;
	private SimpleList<String> list;


	public ListExercise(Scanner scnr) {

		super(scnr);
		list = new SimpleLinkedList<>();

	}

	@Override
	protected void exerciseLogic() {

		switch(currentPhase) {
			case 0:
				menuLogic();
				break;
			case 1:
				addLogic();
				break;
			case 2:
				removeByIndexLogic();
				break;
			case 3:
				removeByReferenceLogic();
				break;
			case 4:
				clearLogic();
				break;
		}
	}


	private void menuLogic() {


		if (firstTime)
		{
			firstTime = false;
			System.out.println("\nBienvenido al ejercicio de listas");
		}
		else
		{
			printList();

		}

		System.out.println("Elegir una opción:"
				+ "\nadd: Agregar elemento "
				+ "\nremoveIndex: Eliminar elemento por índice "
				+ "\nremoveRef: Eliminar elemento por referencia "
				+ "\nclear: Vaciar la lista "
				+ "\nmm: Menú principal");

		String userInput = scanner.nextLine().toLowerCase();

		switch(userInput) {

		case "add":
			currentPhase = 1;
			break;
		case "removeindex":
			currentPhase = 2;
			break;
		case "removeref":
			currentPhase = 3;
			break;
		case "clear":
			currentPhase = 4;
			break;
		case "mm":
			running = false;
			break;
		default:
			System.out.println("Opción inválida, intentar de nuevo");
			break;

		}

	}

	private void printList()
	{
		String fullList = "";

		for(int i = 0; i < list.size(); i++) //(contador, condición que se tiene que cumplir, paso del contador)
		{
			fullList += list.get(i);

			if (i < list.size()-1)
			{
				fullList += ", ";
			}

		}
		System.out.println("\nLista actual: " + fullList);
	}



	private void addLogic() //con el Scanner ponemos un dato y depsués lo agregamos a la lista
	{
		boolean bandera = true;

		
		while (bandera) {
			System.out.println("\nIngresar un string para agregar:");
			list.add(scanner.nextLine()); //añade el dato que ingresa el usaurio

			printList();
			//preguntar si repito operacion

			boolean bandera_2 = true;
			while (bandera_2) {
				System.out.println("\n¿Repetir operación? (y / n)");
				String rep = scanner.nextLine().toLowerCase();
				if (rep.equals("n")) {
					bandera = false;
					bandera_2 = false;
					System.out.println("\nVolviendo al menú");
					currentPhase = 0;  //Esto es para que en la próxima pasada del while me lleve al menu
				} else {
					if (rep.equals("y")) {
						System.out.println("\nRepitiendo operación...");
						bandera_2 = false;
					} else {
						System.out.println("\nRespuesta inválida");
					}

				}
			}
		}

	}

	private void removeByIndexLogic() {

		boolean bandera = true;

		
		while (bandera) {

			System.out.println("\nIngresar un índice para eliminar:");
			int index = Integer.parseInt(scanner.nextLine());
			if (index < list.size()) {
				list.remove(index); //quita el dato según index

				System.out.println("\nElemento eliminado");
				printList();
			}
			else {
				System.out.println("\nEl índice no existe");
			}

		//preguntar si repito operacion
			boolean bandera_2 = true;

			if (list.isEmpty()) {
				System.out.println("\nLa lista está vacía");
				bandera_2 = false;
				bandera = false;
				currentPhase = 0;
			}

			while (bandera_2){
				System.out.println("\n¿Repetir operación? (y / n)");
				String rep = scanner.nextLine().toLowerCase();
				if (rep.equals("n"))
				{
					bandera = false;
					bandera_2 = false;
					System.out.println("\nVolviendo al menú");
					currentPhase = 0;
				}
				else  {
					if (rep.equals("y")){
						System.out.println("\nRepitiendo operación...");
						bandera_2 = false;
					}
					else{
						System.out.println("\nRespuesta inválida");
					}

				}
			}

		}
	}

	private void removeByReferenceLogic() {

		boolean bandera = true;

		

		while (bandera) {
			System.out.println("\nIngresar una referencia para eliminar:");
			String ref = scanner.nextLine();

			//Acá defino la variable removed como un booleano y lo agrego a la lista
			boolean removed = list.remove(ref);

			if (removed) {
				System.out.println("\nElemento eliminado");
			} else {
				System.out.println("\nElemento no encontrado");
			}

			printList();
			//preguntar si repito operacion

			boolean bandera_2 = true;
			while (bandera_2){
				System.out.println("\n¿Repetir operación? (y / n)");
				String rep = scanner.nextLine().toLowerCase();
				if (rep.equals("n"))
				{
					bandera = false;
					bandera_2 = false;
					System.out.println("\nVolviendo al menú");
					currentPhase = 0;
				}
				else  {
					if (rep.equals("y")){
						System.out.println("\nRepitiendo operación...");
						bandera_2 = false;
					}
					else{
						System.out.println("\nRespuesta inválida");
					}

				}
		}	}

	}

	private void clearLogic()
	{

		list.clear();
		System.out.println("\nLista vaciada");
		currentPhase = 0;

		//Acá no pregunto si volver a repetir la opreación porque no tiene sentido
	}
}
