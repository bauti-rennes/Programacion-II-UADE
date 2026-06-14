package treeModule;

import java.util.Scanner;

// AVLExercise extiende BSTExercise: reutiliza toda la lógica del menú, agregar, eliminar, etc.
// La única diferencia es que usa un AVL en vez de un BST, lo que garantiza rebalanceo automático
public class AVLExercise extends BSTExercise {

    public AVLExercise(Scanner scnr) {
        super(scnr);
        // Reemplazamos el BST del padre por un AVL (AVL extiende BST, así que es compatible)
        bst = new AVL<ScoreNode>();
    }

}
