package queueModule;

import application.Exercise;
import stackModule.SimpleArrayStack;
import stackModule.SimpleStack;

import java.util.Scanner;

public class QueueExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleQueue<String> queue;

    //Constructor heredado
    public QueueExercise(Scanner scnr) {
        super(scnr);
        queue = new SimpleArrayQueue<>();
    }

    @Override
    protected void exerciseLogic() {

        //El switch lo copypasteamos de ListExercise porqued va a ser igual
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                enqueueLogic();
                break;
            case 2:
                dequeueLogic();
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
            System.out.println("\nWelcome to the Queue Exercise");
        } else {
            printList();

        }

        System.out.println("Choose an option:"
                + "\nenqueue: Add element at the end of the Queue "
                + "\ndequeue: Remove and show first element "
                + "\npeek: Show the first element of the Queue "
                + "\nclear: Clear list "
                + "\nmm: main menu");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {

            case "enqueue":
                currentPhase = 1;
                break;
            case "dequeue":
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

    private void printList() {

        String fullList = "";

        for (int i = 0; i < queue.size(); i++) //(contador, condición que se tiene que cumplir, paso del contador)
        {
            fullList += queue.get(i);

            if (i < queue.size() - 1) {
                fullList += ", ";
            }

        }
        System.out.println("\nCurrent List: " + fullList);
    }



    private void enqueueLogic() //con el Scanner ponemos un dato y depsués lo agregamos a la lista
    {
        boolean bandera = true;

        while (bandera) {
            System.out.println("\nEnter a string to add:");
            String element = scanner.nextLine();
            queue.enqueue(element);
            System.out.println("\n" + element + "added correctly"); //añade el dato que ingresa el usaurio

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



    private void dequeueLogic()
    {

        //Primero chequeamos que haya una lista y no est
        //Si no existe o está vacía no se puede remover
        if(queue == null || queue.isEmpty()){

            System. out. println("\nStack is null or empty");
            currentPhase = 0;
            return;
        }

        String element = queue.dequeue();
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
        if(queue == null || queue.isEmpty() ) {
            System.out.println("\nStack is null or empty, return to main menu");
            currentPhase = 0;
            return;
        }

        String element = queue.peek();
        System.out.printf("\n" + element + " was at the top");
    }


    public void clearLogic() {
        if(queue == null || queue.isEmpty() ) {
            System.out.println("\nStack is null or empty, return to main menu");

        } else {
            queue.clear();
            System.out.println("\nStack now is empty, returning to main menu");
        }
    }

    private void printStatus(){
        String isEmptyString = "";
        if(queue.isEmpty()) isEmptyString = "\nStack is empty.";
        else isEmptyString = "\nStack isn't empty.";
        System.out.println("Stack size: " + queue.size());
    }

}


