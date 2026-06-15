package treeModule;

import java.util.Scanner;

// AVLExercise extiende BSTExercise porque reutiliza toda la lógica del menú, agregar, eliminar, etc.
// La única diferencia es que usa un AVL en vez de un BST para hacer rebalanceo automático
public class AVLExercise extends BSTExercise {

    public AVLExercise(Scanner scnr) {
        super(scnr);
        // Nada más reemplazamos el BST por un AVL (Esto lo podemos hacer gracias a que AVL extiende de BST)
        bst = new AVL<ScoreNode>();
    }

}
