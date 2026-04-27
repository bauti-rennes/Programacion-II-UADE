package queueModule;

import application.Exercise;

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
            System.out.println("\nBienvenido al ejercicio de colas");
        }

        System.out.println("\nElegir una opción:"
                + "\nenqueue: Agregar elemento al final de la cola "
                + "\ndequeue: Eliminar y mostrar el primer elemento "
                + "\npeek: Mostrar el primer elemento de la cola "
                + "\nclear: Vaciar la cola "
                + "\nmm: Menú principal");

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
                System.out.println("Opción inválida, intentar de nuevo");
                break;

        }

    }


    private void enqueueLogic() //con el Scanner ponemos un dato y depsués lo agregamos a la lista
    {
        boolean bandera = true;

         
        while (bandera) {
            System.out.println("\nIngresar un string para agregar:");
            String element = scanner.nextLine();
            queue.enqueue(element);
            System.out.println("\n" + element + " agregado correctamente"); //añade el dato que ingresa el usaurio

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

    private void dequeueLogic()
    {

         
        //Primero chequeamos que haya una lista y no est
        //Si no existe o está vacía no se puede remover
        if(queue == null || queue.isEmpty()){

            System.out.println("\nLa cola está vacía");
            currentPhase = 0;
            return;
        }

        String element = queue.dequeue();
        System.out.println("\n" + element + " eliminado correctamente");

        boolean validInput = false;

        while(!validInput)
        {
            System.out.println("\n¿Hacer dequeue de otro elemento? y/n");
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
                    System.out.println("\nOpción inválida, intentar de nuevo");
                    break;
            }
        }
    }

    public void peekLogic(){

         
        if(queue == null || queue.isEmpty() ) {
            System.out.println("\nLa cola está vacía, volviendo al menú principal");
            currentPhase = 0;
            return;
        }

        String element = queue.peek();
        System.out.println("\n" + element + " está al frente");
        currentPhase = 0;
        return;
    }


    public void clearLogic() {
        if(queue == null || queue.isEmpty() ) {
            System.out.println("\nLa cola está vacía, volviendo al menú principal");

        } else {
            queue.clear();
            System.out.println("\nLa cola fue vaciada, volviendo al menú principal");
        }
        currentPhase = 0;
        return;
    }

    private void printStatus(){
        String isEmptyString = "";
        if(queue.isEmpty()) isEmptyString = "\nLa cola está vacía.";
        else isEmptyString = "\nLa cola no está vacía.";
        System.out.println("Tamaño de la cola: " + queue.size());
    }

}
