package treeModule;

import application.Exercise;

import java.util.Scanner;

public class BSTExercise extends Exercise {

     private int currentPhase = 0;
     private boolean firstTime = true;
     private BST<ScoreNode> bst; //El árbol BST tiene nodos de tipo ScoreNode

    public BSTExercise(Scanner scnr) {
        super(scnr);
        bst = new BST<ScoreNode>();
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
            /*

                case 2:
                deleteLogic();
                break;
            case 3:
                viewLogic();
                break;
            case 4:
                addTestValues();
            break;
            case 5:
                clearLogic();
            break;
            *
             */

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
                + "\np: Agregar puntajes de prueba "
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
            case "p":
                currentPhase = 4;
                break;
            case "b":
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

    //Agregar puntajes(ScoreNodes) al árbol
    private void addLogic(){

        //TODO agregar funciones while para validacion de inputs

        //Llamo a las funciones para setear enemigos, segundos y nombre de jugador (cada una con sus respectivas validaciones)
        Integer enemies_destroyed = setEnemies();
        Float seconds = setSeconds();
        String player = setPlayer();


        //Inicializar nodo

        ScoreNode nodeValue = new ScoreNode(enemies_destroyed, seconds, player);

        TreeNode<ScoreNode> newValue = new TreeNode<ScoreNode>(nodeValue);

        bst.insertRecursive(newValue, nodeValue);

        boolean backToMenu = returnMenu();

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

private Integer setEnemies() {

    //Inicializo enemies destroyed
    Integer enemies_destroyed = null;

    //Obligo a entrar en el bucle
    boolean bandera = true;

    while (bandera) {

        System.out.println("\nIngrese cantidad de enemigos eliminados:");

        //Si lo que ingresa el usuario es un Integer
        if (scanner.hasNextInt()) {

            enemies_destroyed = scanner.nextInt();

            //Revisa que sea un número válido
            if (enemies_destroyed >= 0) {
                //Sale del bucle
                bandera = false;
            } else {
                System.out.println("\nEl valor tiene que ser cero o más");
            }

            //Si no era un Integer, se repite el bucle
        } else {
            System.out.println("\nEl valor tiene que ser un entero");
        }
    }

    return enemies_destroyed;
}

private Float setSeconds() {

    //Inicializo enemies destroyed
    Float seconds = null;

    //Obligo a entrar en el bucle
    boolean bandera = true;

    while (bandera) {

        System.out.println("\nIngrese cantidad de tiempo transcurrido:");

        //Si lo que ingresa el usuario es un Float
        if (scanner.hasNextFloat()) {

            seconds = scanner.nextFloat();

            //Revisa que sea un número válido
            if (seconds >= 0) {
                //Sale del bucle
                bandera = false;
            } else {
                System.out.println("\nEl valor tiene que ser cero o más");
            }

            //Si no era un Float, se repite el bucle
        } else {
            System.out.println("\nEl valor tiene que ser de tipo Float");
        }
    }

    return seconds;

}

    private String setPlayer() {

        //Inicializo enemies destroyed
        String player = null;

        //Obligo a entrar en el bucle
        boolean bandera = true;

        while (bandera) {

            System.out.println("\nIngrese número del jugador:");

            player = scanner.nextLine();

            //Revisa que sea un nombre que no exista
            if (player == "") { //TODO: ver cómo hacer esto
                //Sale del bucle
                bandera = false;
            } else {
                System.out.println("\nEl nombre ya existe");
            }
        }

        return player;

    }

}
