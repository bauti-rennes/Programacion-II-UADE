package stackModule;

import application.Exercise;

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

        // Limpiamos la consola antes de mostrar el menú
        limpiarConsola();

        if (firstTime) {
            firstTime = false;
            System.out.println("\nBienvenido al ejercicio de pilas");
        }

        System.out.println("Elegir una opción:"
                + "\npush: Agregar elemento al tope de la pila "
                + "\npop: Eliminar y mostrar el último elemento "
                + "\npeek: Mostrar el último elemento de la pila "
                + "\nclear: Vaciar la pila "
                + "\nmm: Menú principal");

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
                System.out.println("Opción inválida, intentar de nuevo");
                break;

        }

    }

        private void pushLogic() //con el Scanner ponemos un dato y depsués lo agregamos a la lista
    {
        boolean bandera = true;

        // Limpiamos la consola antes de mostrar la operación
        limpiarConsola();

        while (bandera) {
            System.out.println("\nIngresar un string para agregar:");
            String element = scanner.nextLine();
            stack.push(element);
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


    private void popLogic()
    {

        // Limpiamos la consola antes de mostrar la operación
        limpiarConsola();

        //Primero chequeamos que haya una lista y no est
        //Si no existe o está vacía no se puede remover
        if(stack == null || stack.isEmpty()){

            System.out.println("\nLa pila está vacía");
            currentPhase = 0;
            return;
        }

        String element = stack.pop();
        System.out.println("\n" + element + " eliminado correctamente");

        boolean validInput = false;

        while(!validInput)
        {
            System.out.println("\n¿Hacer pop de otro elemento? y/n");
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

        // Limpiamos la consola antes de mostrar la operación
        limpiarConsola();

        if(stack == null || stack.isEmpty() ) {
            System.out.println("\nLa pila está vacía, volviendo al menú principal");
            currentPhase = 0;
            return;
        }

        String element = stack.peek();
        System.out.println("\n" + element + " está en el tope");
        currentPhase = 0;
        return;
    }


    public void clearLogic() {
        if(stack == null || stack.isEmpty() ) {
            System.out.println("\nLa pila está vacía, volviendo al menú principal");

        } else {
            stack.clear();
            System.out.println("\nLa pila fue vaciada, volviendo al menú principal");
        }
    }

    private void printStatus(){
        String isEmptyString = "";
        if(stack.isEmpty()) isEmptyString = "\nLa pila está vacía.";
        else isEmptyString = "\nLa pila no está vacía.";
        System.out.println("Tamaño de la pila: " + stack.size());
    }

}
