package list;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import application.Exercise;

public class ListExercise extends Exercise {
	
	private int currentPhase = 0;
	private boolean firstTime = true;
	private List<String> list;
	
	
	public ListExercise(Scanner scnr) {
			
		super(scnr);
		list = new ArrayList<>();
		
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
			System.out.println("\nWelcome to the List Exercise");
		}
		else 
		{	
			printList();

		}

		System.out.println("Choose an option:"
				+ "\nadd: Add element "
				+ "\nremoveIndex: Remove element by Index "
				+ "\nremoveRef: Remove element by Reference "
				+ "\nclear: Clear list "
				+ "\nmm: main menu");
		
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
			System.out.println("Invalid choice, try again");
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
		System.out.println("\nCurrent List: " + fullList);	
	}
	
	
		
	private void addLogic() //con el Scanner ponemos un dato y depsués lo agregamos a la lista
	{
		boolean bandera = true;

		while (bandera) {
			System.out.println("\nEnter a string to add:");
			list.add(scanner.nextLine()); //añade el dato que ingresa el usaurio

			printList();
			//preguntar si repito operacion

			boolean bandera_2 = true;
			while (bandera_2) {
				System.out.println("\n¿Repeat operation? (y / n)");
				String rep = scanner.nextLine().toLowerCase();
				if (rep.equals("n")) {
					bandera = false;
					bandera_2 = false;
					System.out.println("\nGoing back to menu");
					currentPhase = 0;  //Esto es para que en la próxima pasada del while me lleve al menu
				} else {
					if (rep.equals("y")) {
						System.out.println("\nRepeating operation...");
						bandera_2 = false;
					} else {
						System.out.println("\nInvalid answer");
					}

				}
			}
		}

	}
	
	private void removeByIndexLogic() {
		
		boolean bandera = true;

		while (bandera) {

			System.out.println("\nEnter an index to remove:");
			int index = Integer.parseInt(scanner.nextLine());
			if (index < list.size()) {
				list.remove(index); //quita el dato según index

				System.out.println("\nElement removed");
				printList();
			}
			else {
				System.out.println("\nIndex does not exist");
			}

		//preguntar si repito operacion
			boolean bandera_2 = true;

			if (list.isEmpty()) {
				System.out.println("\nList is empty");
				bandera_2 = false;
				bandera = false;
				currentPhase = 0;
			}

			while (bandera_2){
				System.out.println("\n¿Repeat operation? (y / n)");
				String rep = scanner.nextLine().toLowerCase();
				if (rep.equals("n"))
				{
					bandera = false;
					bandera_2 = false;
					System.out.println("\nGoing back to menu");
					currentPhase = 0;
				}
				else  {
					if (rep.equals("y")){
						System.out.println("\nRepeating operation...");
						bandera_2 = false;
					}
					else{
						System.out.println("\nInvalid answer");
					}

				}
			}

		}
	}
	
	private void removeByReferenceLogic() {

		boolean bandera = true;

		while (bandera) {
			System.out.println("\nEnter a reference to remove:");
			String ref = scanner.nextLine();

			//Acá defino la variable removed como un booleano y lo agrego a la lista
			boolean removed = list.remove(ref);

			if (removed) {
				System.out.println("\nElement removed");
			} else {
				System.out.println("\nElement not found");
			}

			printList();
			//preguntar si repito operacion

			boolean bandera_2 = true;
			while (bandera_2){
				System.out.println("\n¿Repeat operation? (y / n)");
				String rep = scanner.nextLine().toLowerCase();
				if (rep.equals("n"))
				{
					bandera = false;
					bandera_2 = false;
					System.out.println("\nGoing back to menu");
					currentPhase = 0;
				}
				else  {
					if (rep.equals("y")){
						System.out.println("\nRepeating operation...");
						bandera_2 = false;
					}
					else{
						System.out.println("\nInvalid answer");
					}

				}
		}	}

	}
	
	private void clearLogic()
	{

		list.clear();
		System.out.println("\nList cleared");
		currentPhase = 0;

		//Acá no pregunto si volver a repetir la opreación porque no tiene sentido
	}
}

