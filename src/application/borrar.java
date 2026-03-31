package application;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class borrar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean bandera = true;
		
		
		List<String> names = new ArrayList<>();
		
		Scanner scanner = new Scanner(System.in);
		
		while(bandera) 
		{
			System.out.println("\nInserte un nombre");
			
			String nombre = scanner.nextLine();
			
			names.add(nombre);
			
			System.out.println("\nLista por ahora");
			
			for (int i = 0; i < names.size(); i ++) {
				System.out.println(names.get(i));
				
			}
			
			System.out.println("\nSeguir?");
			
			String respuesta = scanner.nextLine().toLowerCase();
			
			switch(respuesta)
			{
			case "no":
				System.out.println("\nPrograma terminado!");
				bandera = false;
				break;
			default:
				System.out.println("\nOutput inválido, intentar de nuevo!");
		}
		}
	}

}
