package treeModule;

import application.Exercise;
import listModule.SimpleLinkedList;
import java.util.Scanner;

public class BSTExercise extends Exercise {

     private int currentPhase = 0;
     private boolean firstTime = true;

     // protected para que AVLExercise pueda reemplazarlo por un AVL en su constructor
     protected BST<ScoreNode> bst; //El bst tiene nodos de tipo ScoreNode

    public BSTExercise(Scanner scnr) {
        super(scnr);
        bst = new BST<ScoreNode>();
    }

    @Override
    protected void exerciseLogic() {

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

    //De base entra acá
    private void menuLogic() {

        if (firstTime) {
            firstTime = false;
            System.out.println("\nBienvenido al ejercicio de árboles");
        }

        System.out.println("\nElegir una opción:"
                + "\na: Agregar un puntaje "
                + "\ne: Eliminar un puntaje "
                + "\nm: Mostrar todos los puntajes "
                + "\np: Cargar puntajes de prueba "
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

    //Agregar ScoreNodes al árbol
    private void addLogic(){

        //Llamo a las funciones para setear enemigos, segundos y nombre de jugador (cada una con sus respectivas validaciones)
        Integer enemies_destroyed = setEnemies();
        Float seconds = setSeconds();
        String player = setPlayer();

        // Creo el ScoreNode con los datos ingresados
        ScoreNode nodeValue = new ScoreNode(enemies_destroyed, seconds, player);

        // Inserto el ScoreNode en el bst
        bst.insert(nodeValue);

        System.out.println("\nPuntaje agregado: \n" + nodeValue.getPlayer() + ": " + nodeValue.getScore() + " puntos");

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

        String playerToDelete = scanner.nextLine().trim(); //Trim elimina los espacios en blanco al principio y al final del string

        // Recorremos el árbol en inOrder para buscar el ScoreNode con ese nombre de jugador
        // Para eso hacemos una lista con todos los NODOS
        SimpleLinkedList<ScoreNode> allScores = bst.inOrder();

        //found cambia de valor si encontramos el jugador
        ScoreNode found = null;

        //iteramos todos los nodos hasta encontrar el que coincida con el nombre del jugador
        for (int i = 0; i < allScores.size(); i++) {
            if (allScores.get(i).getPlayer().equals(playerToDelete)) {
                found = allScores.get(i);
                break; // Nos guardamos el primer nodo que coincida (el más alto)
            }
        }

        if (found == null)
        {
            System.out.println("\nNo se encontró un puntaje para el jugador '" + playerToDelete + "'.");
        }
        else
        {
            // Usamos el nodo encontrado para que remove() pueda encontrarlo exactamente en el árbol
            bst.remove(found);
            System.out.println("\nPuntaje de " + playerToDelete + " eliminado.");
        }

        boolean backToMenu = returnMenu();
        if (backToMenu) {
            currentPhase = 0;
        }
    }

    // Mostrar todos los puntajes ordenados de mayor a menor score (inOrder da ese orden gracias a la forma en que hicimos el compareTo)
    private void viewLogic() {

        if (bst.isEmpty()) {
            System.out.println("\nNo hay puntajes cargados.");
            currentPhase = 0;
            return;
        }

        SimpleLinkedList<ScoreNode> allScores = bst.inOrder();

        System.out.println("\n--- LEADERBOARD ---");

        //iteramos en toda la lista y mostramos el nombre del jugador, puntaje, enemigos y segundos
        for (int i = 0; i < allScores.size(); i++) {
            ScoreNode s = allScores.get(i);
            System.out.println(
                "#" + (i + 1) + " | " + s.getPlayer() //El +1 es porque sino arranca por el cero
                + " | Puntaje: " + s.getScore()
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

    //Agregar valores de ejemplo
    private void addTestValues() {
        bst.insert(new ScoreNode(50,  30.0f, "Bauti"));
        bst.insert(new ScoreNode(80,  60.0f, "Fran"));
        bst.insert(new ScoreNode(120, 45.0f, "Vir"));
        bst.insert(new ScoreNode(30,  15.0f, "Kevin"));
        bst.insert(new ScoreNode(200, 90.0f, "Ottoman"));

        System.out.println("\n5 puntajes de prueba cargados.");
        currentPhase = 0;
    }

    // Borrar todos los puntajes del árbol
    private void clearLogic() {

        // Pedimos confirmación antes de borrar para evitar borrar todo sin querer
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

        //Vuelvo directamente al menú
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
