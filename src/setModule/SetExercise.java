package setModule;

import application.Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class SetExercise extends Exercise {

    private int currentPhase = 0; //Esto es para que entre directamente al menu logic
    private boolean firstTime = true;
    private SimpleSet<String> setA;
    private SimpleSet<String> setB;
    private SimpleSet<String> selectedSet;
    private String selectedSetName;

    //Constructor para crear los dos conjuntos. En un futuro se podrían agregar más sets si quisiereamos trabajar con más
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

        if (firstTime) {
            firstTime = false;
            System.out.println("\n¡Bienvenido al ejercicio de Conjuntos!");
        }

        //Cada vez que vuelvo a este menú muestro los dos conjuntos, su tamaño y si están vacíos o no
        System.out.println("\n--- Conjunto A ---"
                + "\n  Elementos: " + Arrays.toString(setA.toArray()) //Arrays.toString convierte un array String para que aparezca así: "[ , , ]". Para eso primero lo tenés que transformar en un array
                + "\n  Tamaño: " + setA.size()
                + "\n  Vacío: " + setA.isEmpty());

        System.out.println("\n--- Conjunto B ---"
                + "\n  Elementos: " + Arrays.toString(setB.toArray())
                + "\n  Tamaño: " + setB.size()
                + "\n  Vacío: " + setB.isEmpty());

        System.out.println("\nElegí una opción:"
                + "\na: Trabajar con el Conjunto A"
                + "\nb: Trabajar con el Conjunto B"
                + "\nun: Mostrar A unión B"
                + "\nin: Mostrar A intersección B"
                + "\ndif-ab: Mostrar A diferencia B"
                + "\ndif-ba: Mostrar B diferencia A"
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
            case "un":
                SimpleSet<String> union = setA.unionWith(setB);
                System.out.println("\nA unión B: " + Arrays.toString(union.toArray()));
                break;
            case "in":
                SimpleSet<String> intersect = setA.intersectWith(setB);
                System.out.println("\nA intersección B: " + Arrays.toString(intersect.toArray()));
                break;
            case "dif-ab":
                SimpleSet<String> diffAB = setA.differenceWith(setB);
                System.out.println("\nA menos B: " + Arrays.toString(diffAB.toArray()));
                break;
            case "dif-ba":
                SimpleSet<String> diffBA = setB.differenceWith(setA);
                System.out.println("\nB menos A: " + Arrays.toString(diffBA.toArray()));
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

        //Cuando entrás acá es porque elegiste un conjunto
        System.out.println("\nTrabajando con el Conjunto " + selectedSetName
                + "\n  Elementos: " + Arrays.toString(selectedSet.toArray())
                + "\nElegí una opción:"
                + "\na: Agregar elemento"
                + "\nr: Eliminar elemento"
                + "\nb: Volver al menú principal");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {
            case "a":
                currentPhase = 2;
                break;
            case "r":
                currentPhase = 3;
                break;
            case "b":
                currentPhase = 0;
                break;
            default:
                System.out.println("\nOpción inválida, intentá de nuevo"); //Vuelve a entrar a la función porque currentPhase sigue siendo = 1 (no hace falta una bandera)
                break;
        }
    }

    private void addLogic() {

        boolean repeat = true;

        while (repeat) {
            System.out.println("\nIngresá el elemento a agregar al Conjunto " + selectedSetName + ":");
            String element = scanner.nextLine();

            boolean success = selectedSet.add(element); //add devuelve false si el elemento ya existe

            if (success){
                System.out.println("\n'" + element + "' agregado correctamente.");
            }
            else{
                System.out.println("\n'" + element + "' ya existe en el Conjunto " + selectedSetName + ", no se agregó.");
            }

            repeat = askRepeat();
        }
        currentPhase = 1; //te lleva de vuelta a las operaciones del mismo conjunto
    }

    private void removeLogic() {
        boolean repeat = true;
         
        while (repeat) {
            System.out.println("\nIngresá el elemento a eliminar del Conjunto " + selectedSetName + ":");
            String element = scanner.nextLine();

            boolean success = selectedSet.remove(element); //remove devuelve false si el elemento no existe en el conjunto

            if (success){
                System.out.println("\n'" + element + "' eliminado correctamente.");
            }
            else{
                System.out.println("\n'" + element + "' no fue encontrado en el Conjunto " + selectedSetName + ".");
            }

            repeat = askRepeat();
        }
        currentPhase = 1;
    }

    //Función que pregunta si querés repetir
    private boolean askRepeat() {

        while (true) {
            System.out.println("\n¿Repetir operación? (s / n)");

            String input = scanner.nextLine().toLowerCase();

            if (input.equals("s")){
                return true;
            }
            if (input.equals("n")){
                return false;
            }
            System.out.println("\nRespuesta inválida");
        }
    }
}
