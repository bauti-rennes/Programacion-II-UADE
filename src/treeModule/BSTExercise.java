package treeModule;

import application.Exercise;

import java.util.Scanner;

public class BSTExercise extends Exercise {

     private int currentPhase = 0;
     private boolean firstTime = true;
     private BST<ScoreNode> bst; //El árbol BST tiene nodos de tipo ScoreNode

    public BSTExercise(Scanner scnr) {
        super(scnr);
        bst = new BST<ScoreNode>;
    }

    @Override
    protected void exerciseLogic() {

        //El switch lo copypasteamos de ListExercise porqued va a ser igual
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                addLogic();
                break;
            case 2:
                deleteLogic();
                break;
            case 3:
                viewLogic();
                break;
            case 4:
                clearLogic();
                break;
            case 5:
                testLogic();
                break;

        }

    }

    private void menuLogic() {

        if (firstTime) {
            firstTime = false;
            System.out.println("\nBienvenido al ejercicio de árboles");
        }

        System.out.println("\nElegir una opción:"
                + "\na: Agregar un puntaje "
                + "\ne: Eliminar un punaje "
                + "\nm: Mostrar todos los puntajes "
                + "\nb: Borrar todo "
                + "\nmm: Menú principal");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {

            case "a":
                currentPhase = 1;
                break;
            case "e":
                currentPhase = 2;
                break;
            case "m":
                currentPhase = 3;
                break;
            case "b":
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

    //Agregar puntajes(ScoreNodes) al árbol
    private void addLogic(){


        System.out.println("\nAgregar puntuación (enemigos, tiempo)");

        String userInput = scanner.nextLine().toLowerCase();

        insertRecursive();

        backToMenu = returnMenu();
        if (backToMenu == true){
            currentPhase = 0;
        }

    }

private boolean returnMenu() {

    boolean returnToMenu = false;

    boolean bandera = true;

    while (bandera){
        System.out.println("\n¿Volver al menu? (s/n)");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {
            case "s":
                returnToMenu = true;
                bandera = false;
                break;
            case "n":
                returnToMenu = false;
                bandera = false;
                break;
            default:
                System.out.println("\nOpción inválida. Intente de nuevo");
        }
    }
    return returnToMenu;

}


}
