package application;

import java.util.Scanner;

// abstracta es que no puedo instanciar esto, no puedo decir new Exercise, no la puedo ejecutar así nomás
public abstract class Exercise {

	//protected significa que lo pueden ver las clases hijas pero no el main program
	protected boolean running = true;
	protected Scanner scanner;

	//Acá hay que armar un constructor. Es como un metodo pero no es exacatmente un metodo que lo llamamos así nomas
	//Este metodo solo se llama cuando instanciamos una copia de esta clase
	//Cuando yo hago un exercise, instanciamos el construto
	//El Scanner scnr es el que se recibe por parámetro (la función no tiene void, o sea recibe parametros)
	//Esta va a ser la función que se ejecuta cuando pongo new.Exercise
	public Exercise (Scanner scnr) {

		scanner = scnr;

	}


	//Métodos:
	// es void porque no recibe parámetros
	public void run() {

		while (running) {

			exerciseLogic();


		}
	}

	//no sé qué va a hacer exerciseLogic, lo dejo como comodín
	protected abstract void exerciseLogic();


}
