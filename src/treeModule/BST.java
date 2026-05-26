package treeModule;

import listModule.SimpleArrayList;
import listModule.SimpleLinkedList;

public class BST <E  extends Comparable<E>>{

    public TreeNode<E> root = null;
    private int size = 0;

    public void insert(E value) {
        root = insertRecursive(root, value);
    }


    // Recibe el nodo como estaba antes de insertar
    // Devuelve el nuevo nodo en ese mismo lugar
    // (Puede recibir null con el espacio vacio)
    // (Puede que reciba el mismo nodo que recibio)
    private TreeNode<E> insertRecursive(TreeNode<E> current, E value) {
        // Caso base: encontramos un lugar vacio
        // Insertamos en ese lugar, devolviendo uno nuevo
        if (current == null) {
            size ++;
            return new TreeNode<E>(value);
        }
        // La comparacion es una funcion que devuelve -1, 1, o 0;
        // -1 seria menor -> Izq
        // 1 seria mayor -> Der
        // 0 seria igual -> Misma posicion
        int comparison = value.compareTo(current.value);

        // Si es menor seguimos por el hijo izq
        if (comparison < 0) {
            current.left = insertRecursive(current.left, value); //Paso por parámetro el current.left y lo guardo sobre el mismo current.left
        }

        // Si es "Mayor seguimos por el hijo derecho
        // Este caso no admite duplicados
        else if (comparison > 0) {
            current.right = insertRecursive(current.right, value);
        }

        // Si llegamos aca no hubieron cambios
        // Retornamos el mismo nodo
        return current;

    }

    public void remove(E value) {
        root = removeRecursive(root, value);
    }

    public TreeNode<E> removeRecursive(TreeNode<E> current, E value) {
        // Caso Base: Llegamos al final y no estaba el value
        if (current == null) {
            return null;
        }

        int comparison = value.compareTo(current.value);

        if (comparison == 0) {
            // Caso 1: hoja (sin hijos)
            if (current.left == null &&  current.right == null) {
                size--;
                return null;
            }
            // Caso 2: tiene un solo hijo
            else if (current.left == null) { //el bloque condicional solo llega acá si alguno de los dos (left o right) no es null
                size--;
                return current.right;
            } else if (current.right == null) {
                size--;
                return current.left;
            }
            // Caso 3 tiene ambos hijos
            // Buscamos el minimo de la derecha
            TreeNode<E> succesor = getMinNode(current.right);

            // Pisamos al nodo con el sucesor
            current.value = succesor.value;

            //Ahora removemos al sucesor para que no duplique
            current.right = removeRecursive(current.right, succesor.value);

        }
        else if (comparison < 0) {
            current.left = removeRecursive(current.left, value);
        }
        else {
            current.right = removeRecursive(current.right, value);
        }

        return current;
    }

    // Para encontrar el minimo vamos para la izq a fondo
    private TreeNode<E> getMinNode(TreeNode<E> current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // DFS
    // Pre-order
    public SimpleLinkedList<E> preOrder() {
        SimpleLinkedList<E> result = new SimpleLinkedList<E>();
        preOrderDFS(root, result);
        return result;
    }

    private void preOrderDFS(TreeNode<E> current, SimpleLinkedList<E> list) {
        if (current == null) {
            return;
        }
        list.add(current.value);
        preOrderDFS(current.left, list);
        preOrderDFS(current.right, list);
    }

    //hacer los otros dos tipos de order

    // In-order
    public SimpleLinkedList<E> inOrder() {
        SimpleLinkedList<E> result = new SimpleLinkedList<E>();
        inOrderDFS(root, result);
        return result;
    }

    private void inOrderDFS(TreeNode<E> current, SimpleLinkedList<E> list) {
        if (current == null) {
            return;
        }
        inOrderDFS(current.left, list);
        list.add(current.value);
        inOrderDFS(current.right, list);

    }

    // Post-order
    public SimpleLinkedList<E> postOrder() {
        SimpleLinkedList<E> result = new SimpleLinkedList<E>();
        postOrderDFS(root, result);
        return result;
    }

    private void postOrderDFS(TreeNode<E> current, SimpleLinkedList<E> list) {
        if (current == null) {
            return;
        }
        postOrderDFS(current.left, list);
        postOrderDFS(current.right, list);
        list.add(current.value);

    }

}
