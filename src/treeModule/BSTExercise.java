package treeModule;

import application.Exercise;
import listModule.SimpleLinkedList;

import java.util.Scanner;

public class BSTExercise extends Exercise {

     private int currentPhase = 0;
     private boolean firstTime = true;
     // protected para que AVLExercise pueda reemplazarlo por un AVL en su constructor
     protected BST<ScoreNode> bst; //El árbol BST tiene nodos de tipo ScoreNode

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
        }

    }

    private void menuLogic() {

        if (firstTime) {
            firstTime = false;
            System.out.println("\nBienvenido al ejercicio de árboles");
        }

        System.out.println("\nElegir una opción:"
                + "\na: Agregar un puntaje "
                + "\ne: Eliminar un puntaje "
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

        // Creo el ScoreNode con los datos ingresados
        ScoreNode nodeValue = new ScoreNode(enemies_destroyed, seconds, player);

        // Inserto el ScoreNode directamente en el BST (insert() ya se encarga de ubicarlo correctamente)
        bst.insert(nodeValue);

        System.out.println("\nPuntaje agregado: " + nodeValue.getPlayer() + " - " + nodeValue.getScore());

        boolean backToMenu = returnMenu();

        if (backToMenu == true){
            currentPhase = 0;
        }

    }

    // Eliminar un puntaje buscando por nombre de jugador
    private void deleteLogic() {

        // Si el árbol está vacío no hay nada que borrar
        if (bst.isEmpty()) {
            System.out.println("\nNo hay puntajes cargados.");
            currentPhase = 0;
            return;
        }

        System.out.println("\nIngrese el nombre del jugador a eliminar:");
        String playerToDelete = scanner.nextLine().trim();

        // Recorremos el árbol en inOrder para buscar el ScoreNode con ese nombre de jugador
        SimpleLinkedList<ScoreNode> allScores = bst.inOrder();
        ScoreNode found = null;

        for (int i = 0; i < allScores.size(); i++) {
            if (allScores.get(i).getPlayer().equals(playerToDelete)) {
                found = allScores.get(i);
                break; // tomamos el primer resultado que coincida
            }
        }

        if (found == null) {
            System.out.println("\nNo se encontró un puntaje para el jugador '" + playerToDelete + "'.");
        } else {
            // Usamos el ScoreNode encontrado para que remove() pueda localizarlo exactamente en el árbol
            bst.remove(found);
            System.out.println("\nPuntaje de '" + playerToDelete + "' eliminado.");
        }

        boolean backToMenu = returnMenu();
        if (backToMenu) {
            currentPhase = 0;
        }
    }

    // Mostrar todos los puntajes ordenados de mayor a menor score (inOrder da ese orden gracias al compareTo)
    private void viewLogic() {

        if (bst.isEmpty()) {
            System.out.println("\nNo hay puntajes cargados.");
            currentPhase = 0;
            return;
        }

        SimpleLinkedList<ScoreNode> allScores = bst.inOrder();

        System.out.println("\n--- LEADERBOARD ---");
        for (int i = 0; i < allScores.size(); i++) {
            ScoreNode s = allScores.get(i);
            System.out.println(
                "#" + (i + 1) + " | " + s.getPlayer()
                + " | Score: " + s.getScore()
                + " | Enemigos: " + s.getEnemies_destroyed()
                + " | Tiempo: " + s.getSeconds() + "s"
            );
        }
        System.out.println("-------------------");

        boolean backToMenu = returnMenu();
        if (backToMenu) {
            currentPhase = 0;
        }
    }

    // Base de datos pre-programada para facilitar el testeo de la aplicación (requerido por el enunciado)
    private void addTestValues() {
        bst.insert(new ScoreNode(50,  30.0f, "Alice"));
        bst.insert(new ScoreNode(80,  60.0f, "Bob"));
        bst.insert(new ScoreNode(120, 45.0f, "Carlos"));
        bst.insert(new ScoreNode(30,  15.0f, "Diana"));
        bst.insert(new ScoreNode(200, 90.0f, "Eve"));

        System.out.println("\nPuntajes de prueba cargados (5 entradas).");
        currentPhase = 0;
    }

    // Borrar todos los puntajes del árbol
    private void clearLogic() {

        // Pedimos confirmación antes de borrar para evitar pérdidas accidentales
        boolean confirmed = false;
        boolean bandera = true;

        while (bandera) {
            System.out.println("\n¿Seguro que querés borrar todos los puntajes? (s/n)");
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "s":
                    confirmed = true;
                    bandera = false;
                    break;
                case "n":
                    confirmed = false;
                    bandera = false;
                    break;
                default:
                    System.out.println("\nOpción inválida. Intente de nuevo");
            }
        }

        if (confirmed) {
            bst.clear();
            System.out.println("\nTodos los puntajes fueron eliminados.");
        } else {
            System.out.println("\nOperación cancelada.");
        }

        currentPhase = 0;
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
            // nextInt() no consume el salto de línea final — lo consumimos acá para no romper el nextLine() siguiente
            scanner.nextLine();

            //Revisa que sea un número válido
            if (enemies_destroyed >= 0) {
                //Sale del bucle
                bandera = false;
            } else {
                System.out.println("\nEl valor tiene que ser cero o más");
            }

            //Si no era un Integer, descartamos la línea inválida y se repite el bucle
        } else {
            System.out.println("\nEl valor tiene que ser un entero");
            scanner.nextLine();
        }
    }

    return enemies_destroyed;
}

private Float setSeconds() {

    //Inicializo seconds
    Float seconds = null;

    //Obligo a entrar en el bucle
    boolean bandera = true;

    while (bandera) {

        System.out.println("\nIngrese cantidad de tiempo transcurrido (en segundos):");

        //Si lo que ingresa el usuario es un Float
        if (scanner.hasNextFloat()) {

            seconds = scanner.nextFloat();
            // nextFloat() no consume el salto de línea final — lo consumimos acá para no romper el nextLine() siguiente
            scanner.nextLine();

            // Validamos mayor a cero porque ScoreNode no acepta segundos = 0 (división por cero en el score)
            if (seconds > 0) {
                //Sale del bucle
                bandera = false;
            } else {
                System.out.println("\nEl valor tiene que ser mayor a cero");
            }

            //Si no era un Float, descartamos la línea inválida y se repite el bucle
        } else {
            System.out.println("\nEl valor tiene que ser de tipo Float");
            scanner.nextLine();
        }
    }

    return seconds;

}

    private String setPlayer() {

        //Inicializo player
        String player = null;

        //Obligo a entrar en el bucle
        boolean bandera = true;

        while (bandera) {

            System.out.println("\nIngrese nombre del jugador:");

            player = scanner.nextLine().trim();

            // Validamos que el nombre no esté vacío
            if (!player.isEmpty()) {
                //Sale del bucle
                bandera = false;
            } else {
                System.out.println("\nEl nombre no puede estar vacío");
            }
        }

        return player;

    }

}
