package recursionModule;

import application.Exercise;

import java.util.Scanner;

public class RecursionExercise extends Exercise {

    private int currentPhase = 0;
    private boolean firstTime = true;


    public RecursionExercise(Scanner scnr) {

        super(scnr);

    }

    @Override
    protected void exerciseLogic() {

        switch(currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                factorial();
                break;
            case 2:
                fibonacci();
                break;
            case 3:
                exclusiveSum();
                break;
            case 4:
                pyramid();
                break;
            case 5:
                isPalindrome();
                break;

        }
    }


    private void menuLogic() {


        if (firstTime)
        {
            firstTime = false;
            System.out.println("\nBienvenido al ejercicio de listas");
        }


        System.out.println("Elegir una opción:"
                + "\nfa: Factorial "
                + "\nfi: Fibonacci "
                + "\ne: Suma Exclusiva "
                + "\np: Pirámide "
                + "\ni: Palíndrome "
                + "\nmm: Menú principal");

        String userInput = scanner.nextLine().toLowerCase();

        switch(userInput) {

            case "fa":
                currentPhase = 1;
                break;
            case "fi":
                currentPhase = 2;
                break;
            case "e":
                currentPhase = 3;
                break;
            case "p":
                currentPhase = 4;
                break;
            case "i":
                currentPhase = 5;
                break;
            case "mm":
                running = false;
                break;
            default:
                System.out.println("Opción inválida, intentar de nuevo");
                break;

        }

    }


    private void factorial(){

        System.out.println("Ingrese un número para calcular su factorial: ");

        int numero = scanner.nextInt();

        Integer resultado = factorialCalculo(numero);

        System.out.println("El resultado es: " + resultado.toString());


    }

    private void fibonacci() {

        System.out.println("Ingrese una posición de la secuencia de Fibonacci: ");

        int numero = scanner.nextInt();

        Integer resultado = fibonacciCalculo(numero);

        System.out.println("El resultado es: " + resultado.toString());

    }

    private void exclusiveSum() {


    }

    private void pyramid() {
        // Implementación pendiente
    }

    private void isPalindrome() {
        // Implementación pendiente
    }

    //Funciones utiles

    public int factorialCalculo(int numero) {

        if (numero == 0 || numero == 1) {
            return 1;
        }

        return numero * factorialCalculo(numero - 1);
    }

    public int fibonacciCalculo(int numero) {

        if (numero == 0)
        {
            return 0;
        }

        else if (numero == 1)
        {
            return 1;
        }

        else
        {
            return fibonacciCalculo(numero - 1) + fibonacciCalculo(numero - 2);
        }
    }
}

