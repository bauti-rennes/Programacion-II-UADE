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

        if (firstTime) {
            firstTime = false;
            System.out.println("\nWelcome to the Set Exercise!");
        }

        System.out.println("\n--- Set A ---"
                + "\n  Elements: " + Arrays.toString(setA.toArray())
                + "\n  Size: " + setA.size()
                + "\n  Empty: " + setA.isEmpty());

        System.out.println("\n--- Set B ---"
                + "\n  Elements: " + Arrays.toString(setB.toArray())
                + "\n  Size: " + setB.size()
                + "\n  Empty: " + setB.isEmpty());

        System.out.println("\nChoose an option:"
                + "\na: Work on Set A"
                + "\nb: Work on Set B"
                + "\nunion: Show A union B"
                + "\nintersect: Show A intersect B"
                + "\ndiff-ab: Show A difference B"
                + "\ndiff-ba: Show B difference A"
                + "\nmm: Main menu");

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
                System.out.println("\nA union B: " + Arrays.toString(union.toArray()));
                break;
            case "intersect":
                SimpleSet<String> intersect = setA.intersectWith(setB);
                System.out.println("\nA intersect B: " + Arrays.toString(intersect.toArray()));
                break;
            case "diff-ab":
                SimpleSet<String> diffAB = setA.differenceWith(setB);
                System.out.println("\nA difference B: " + Arrays.toString(diffAB.toArray()));
                break;
            case "diff-ba":
                SimpleSet<String> diffBA = setB.differenceWith(setA);
                System.out.println("\nB difference A: " + Arrays.toString(diffBA.toArray()));
                break;
            case "mm":
                running = false;
                break;
            default:
                System.out.println("\nInvalid option, try again");
                break;
        }
    }

    private void selectSetLogic() {
        System.out.println("\nWorking on Set " + selectedSetName
                + "\n  Elements: " + Arrays.toString(selectedSet.toArray())
                + "\nChoose an option:"
                + "\nadd: Add element"
                + "\nremove: Remove element"
                + "\nback: Back to main menu");

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
                System.out.println("\nInvalid option, try again");
                break;
        }
    }

    private void addLogic() {
        boolean repeat = true;
        while (repeat) {
            System.out.println("\nEnter element to add to Set " + selectedSetName + ":");
            String element = scanner.nextLine();
            boolean success = selectedSet.add(element);
            if (success) System.out.println("\n'" + element + "' added successfully.");
            else         System.out.println("\n'" + element + "' already exists in Set " + selectedSetName + ", not added.");

            repeat = askRepeat();
        }
        currentPhase = 1;
    }

    private void removeLogic() {
        boolean repeat = true;
        while (repeat) {
            System.out.println("\nEnter element to remove from Set " + selectedSetName + ":");
            String element = scanner.nextLine();
            boolean success = selectedSet.remove(element);
            if (success) System.out.println("\n'" + element + "' removed successfully.");
            else         System.out.println("\n'" + element + "' was not found in Set " + selectedSetName + ".");

            repeat = askRepeat();
        }
        currentPhase = 1;
    }

    private boolean askRepeat() {
        while (true) {
            System.out.println("\nRepeat operation? (y / n)");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("y")) return true;
            if (input.equals("n")) return false;
            System.out.println("\nInvalid answer");
        }
    }
}
