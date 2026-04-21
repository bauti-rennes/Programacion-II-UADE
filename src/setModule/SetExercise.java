package setModule;

import application.Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class SetExercise extends Exercise {

    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleSet<String> setA;
    private SimpleSet<String> setB;
    private SimpleSet<String> selectedSet;
    private String selectedSetName;

    public SetExercise(Scanner scnr) {
        super(scnr);
        setA = new SimpleArraySet<>();
        setB = new SimpleArraySet<>();
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0: menuLogic();      break;
            case 1: selectSetLogic(); break;
            case 2: addLogic();       break;
            case 3: removeLogic();    break;
        }
    }

    private void menuLogic() {

        // Limpiamos la consola antes de mostrar el menú
        limpiarConsola();

        if (firstTime) {
            firstTime = false;
            System.out.println("\n¡Bienvenido al ejercicio de Conjuntos!");
        }

        System.out.println("\n--- Conjunto A ---"
                + "\n  Elementos: " + Arrays.toString(setA.toArray())
                + "\n  Tamaño: " + setA.size()
                + "\n  Vacío: " + setA.isEmpty());

        System.out.println("\n--- Conjunto B ---"
                + "\n  Elementos: " + Arrays.toString(setB.toArray())
                + "\n  Tamaño: " + setB.size()
                + "\n  Vacío: " + setB.isEmpty());

        System.out.println("\nElegí una opción:"
                + "\na: Trabajar con el Conjunto A"
                + "\nb: Trabajar con el Conjunto B"
                + "\nunion: Mostrar A unión B"
                + "\nintersect: Mostrar A intersección B"
                + "\ndiff-ab: Mostrar A diferencia B"
                + "\ndiff-ba: Mostrar B diferencia A"
                + "\nmm: Menú principal");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {
            case "a":
                selectedSet = setA;
                selectedSetName = "A";
                currentPhase = 1;
                break;
            case "b":
                selectedSet = setB;
                selectedSetName = "B";
                currentPhase = 1;
                break;
            case "union":
                SimpleSet<String> union = setA.unionWith(setB);
                System.out.println("\nA unión B: " + Arrays.toString(union.toArray()));
                break;
            case "intersect":
                SimpleSet<String> intersect = setA.intersectWith(setB);
                System.out.println("\nA intersección B: " + Arrays.toString(intersect.toArray()));
                break;
            case "diff-ab":
                SimpleSet<String> diffAB = setA.differenceWith(setB);
                System.out.println("\nA diferencia B: " + Arrays.toString(diffAB.toArray()));
                break;
            case "diff-ba":
                SimpleSet<String> diffBA = setB.differenceWith(setA);
                System.out.println("\nB diferencia A: " + Arrays.toString(diffBA.toArray()));
                break;
            case "mm":
                running = false;
                break;
            default:
                System.out.println("\nOpción inválida, intentá de nuevo");
                break;
        }
    }

    private void selectSetLogic() {

        // Limpiamos la consola antes de mostrar el submenú del conjunto seleccionado
        limpiarConsola();

        System.out.println("\nTrabajando con el Conjunto " + selectedSetName
                + "\n  Elementos: " + Arrays.toString(selectedSet.toArray())
                + "\nElegí una opción:"
                + "\nadd: Agregar elemento"
                + "\nremove: Eliminar elemento"
                + "\nback: Volver al menú principal");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {
            case "add":
                currentPhase = 2;
                break;
            case "remove":
                currentPhase = 3;
                break;
            case "back":
                currentPhase = 0;
                break;
            default:
                System.out.println("\nOpción inválida, intentá de nuevo");
                break;
        }
    }

    private void addLogic() {
        boolean repeat = true;

        // Limpiamos la consola antes de mostrar la operación
        limpiarConsola();

        while (repeat) {
            System.out.println("\nIngresá el elemento a agregar al Conjunto " + selectedSetName + ":");
            String element = scanner.nextLine();
            boolean success = selectedSet.add(element);
            if (success) System.out.println("\n'" + element + "' agregado correctamente.");
            else         System.out.println("\n'" + element + "' ya existe en el Conjunto " + selectedSetName + ", no se agregó.");

            repeat = askRepeat();
        }
        currentPhase = 1;
    }

    private void removeLogic() {
        boolean repeat = true;

        // Limpiamos la consola antes de mostrar la operación
        limpiarConsola();

        while (repeat) {
            System.out.println("\nIngresá el elemento a eliminar del Conjunto " + selectedSetName + ":");
            String element = scanner.nextLine();
            boolean success = selectedSet.remove(element);
            if (success) System.out.println("\n'" + element + "' eliminado correctamente.");
            else         System.out.println("\n'" + element + "' no fue encontrado en el Conjunto " + selectedSetName + ".");

            repeat = askRepeat();
        }
        currentPhase = 1;
    }

    private boolean askRepeat() {
        while (true) {
            System.out.println("\n¿Repetir operación? (y / n)");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
            System.out.println("\nRespuesta inválida");
        }
    }
}
