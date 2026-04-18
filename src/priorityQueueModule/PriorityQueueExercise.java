package priorityQueueModule;

import application.Exercise;
import priorityQueueModule.SimpleArrayPriorityQueue;

import java.util.Scanner;

public class PriorityQueueExercise extends Exercise {

    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleArrayPriorityQueue<Reporte> queue; //El tipo es reporte porque queremos una priorityqueue de reportes.

    //Constructor
    public PriorityQueueExercise(Scanner scnr) {
        super(scnr);
        queue = new SimpleArrayPriorityQueue<Reporte>();
    }

    //Copiamos la estructura del reporte
    @Override
    protected void exerciseLogic() {

        //El switch lo copypasteamos de ListExercise porqued va a ser igual
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                redactarLogic();
                break;
            case 2:
                visualizarLogic();
                break;
            case 3:
                clearLogic();
                break;
        }
    }


    private void menuLogic() {

        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the Queue Exercise");
        }

        System.out.println("\nElegir una opción:"
                + "\nr: Redactar un reporte "
                + "\nv: Visualizar un reporte "
                + "\nb: Borrar reporte"
                + "\nmm: main menu");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {

            case "r":
                currentPhase = 1;
                break;
            case "v":
                currentPhase = 2;
                break;
            case "":
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


    private void redactarLogic() //con el Scanner ponemos un dato y depsués lo agregamos a la lista
    {
        boolean bandera = true;

        while (bandera) {
            System.out.println("\nIngresar un título:");
            String titulo = scanner.nextLine();
            System.out.println("\nIngresar una descripcion:");
            String descripcion = scanner.nextLine();
            Reporte reporte = new Reporte(titulo,descripcion);
            queue.enqueue(Reporte);
            System.out.println("\n" + reporte + " added correctly"); //añade el dato que ingresa el usaurio


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

            System. out. println("\nQueue is null or empty");
            currentPhase = 0;
            return;
        }

        String element = queue.dequeue();
        System.out.printf("\n" + element + " successfully removed");

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
                    System. out.println("\nInvalid Input, try again");
                    break;
            }
        }
    }

    public void peekLogic(){
        if(queue == null || queue.isEmpty() ) {
            System.out.println("\nQueue is null or empty, return to main menu");
            currentPhase = 0;
            return;
        }

        String element = queue.peek();
        System.out.printf("\n" + element + " was at the front");
        currentPhase = 0;
        return;
    }


    public void clearLogic() {
        if(queue == null || queue.isEmpty() ) {
            System.out.println("\nQueue is null or empty, return to main menu");

        } else {
            queue.clear();
            System.out.println("\nQueue now is empty, returning to main menu");
        }
        currentPhase = 0;
        return;
    }

    private void printStatus(){
        String isEmptyString = "";
        if(queue.isEmpty()) isEmptyString = "\nQueue is empty.";
        else isEmptyString = "\nQueue isn't empty.";
        System.out.println("Queue size: " + queue.size());
    }

}

