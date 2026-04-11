package stackModule;

import application.Exercise;
import list.SimpleList;

import java.util.Scanner;

public class StackExercise extends Exercise {

    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleStack<String> stack;

    //Constructor heredado
    public StackExercise(Scanner scnr) {
        super(scnr);
        stack = new SimpleArrayStack<String>();
    }

    @Override
    protected void exerciseLogic() {

        //El switch lo copypasteamos de ListExercise porqued va a ser igual
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                pushLogic();
                break;
            case 2:
                popLogic();
                break;
            case 3:
                peekLogic();
                break;
            case 4:
                clearLogic();
                break;
        }
    }


    private void menuLogic() {


        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the List Exercise");
        }

        System.out.println("Choose an option:"
                + "\npush: Add element at the end of the Stack "
                + "\npop: Remove and show last element "
                + "\npeek: Show the last element of the Stack "
                + "\nclear: Clear list "
                + "\nmm: main menu");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {

            case "push":
                currentPhase = 1;
                break;
            case "pop":
                currentPhase = 2;
                break;
            case "peek":
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

        private void pushLogic() //con el Scanner ponemos un dato y depsués lo agregamos a la lista
    {
        boolean bandera = true;

        while (bandera) {
            System.out.println("\nEnter a string to add:");
            String element = scanner.nextLine();
            stack.push(element);
            System.out.println("\n" + element + " added correctly"); //añade el dato que ingresa el usaurio

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


    private void popLogic()
    {

        //Primero chequeamos que haya una lista y no est
        //Si no existe o está vacía no se puede remover
        if(stack == null || stack.isEmpty()){

            System. out. println("\nStack is null or empty");
            currentPhase = 0;
            return;
        }

        String element = stack.pop();
        System.out.printf("\n" + element + "successfully removed");

        boolean validInput = false;

        while(!validInput)
        {
            System.out.println("\nPop another element? y/n");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput){
                case "y":
                    validInput = true;
                    break;
                case "n":
                    validInput = true;
                    currentPhase = 0;
                    break;
                default:
                    System. out.println("nInvalid Input, try again");
                    break;
            }
        }
    }

    public void peekLogic(){
        if(stack == null || stack.isEmpty() ) {
            System.out.println("\nStack is null or empty, return to main menu");
            currentPhase = 0;
            return;
        }

        String element = stack.peek();
        System.out.printf("\n" + element + " was at the top");
        currentPhase = 0;
        return;
    }


    public void clearLogic() {
        if(stack == null || stack.isEmpty() ) {
            System.out.println("\nStack is null or empty, return to main menu");

        } else {
            stack.clear();
            System.out.println("\nStack now is empty, returning to main menu");
        }
    }

    private void printStatus(){
        String isEmptyString = "";
        if(stack.isEmpty()) isEmptyString = "\nStack is empty.";
        else isEmptyString = "\nStack isn't empty.";
        System.out.println("Stack size: " + stack.size());
    }

}
