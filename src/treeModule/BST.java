package treeModule;

import listModule.SimpleArrayList;
import listModule.SimpleLinkedList;

//E extiende comparable para que se pueda hacer la comparación de los valores de los nodos
public class BST <E  extends Comparable<E>>{

    //Inicializamos la raíz y el tamaño
    protected TreeNode<E> root = null;
    protected int size = 0;

    public BST() {root = null;}

    //Si paso por parámetro un valor a la hora de inicializar el BST, lo toma como la raíz
    public BST (E firstValue) {root = new TreeNode<E> (firstValue); size = 1;}

    //Función para insertar un valor --> llama a insertRecursive empezando por la raíz
    public void insert(E value) {
        root = insertRecursive(root, value);
    }

    // Recibe el nodo como estaba antes de insertar
    // Devuelve el nuevo nodo en ese mismo lugar
    // (Puede recibir null con el espacio vacio)
    // (Puede que reciba el mismo nodo que recibio)
    protected TreeNode<E> insertRecursive(TreeNode<E> current, E value) {
        // Caso base: encontramos un lugar vacio
        // Insertamos en ese lugar, devolviendo un nodo nuevo
        if (current == null) {
            size ++;
            return new TreeNode<E>(value);
        }

        // La comparacion es una funcion que devuelve -1, 1, o 0;
        // -1 seria menor -> Izq
        // 1 seria mayor -> Der
        // 0 seria igual -> Misma posicion
        int comparison = value.compareTo(current.value); //Compara el valor que quiero agregar vs el del nodo en el qude estoy parado

        // Si es menor seguimos por el hijo izq
        if (comparison < 0) {
            current.left = insertRecursive(current.left, value); //Paso por parámetro el current.left y lo guardo sobre el mismo current.left
        }

        // Si es "Mayor seguimos por el hijo derecho
        // Este caso no admite duplicados (si comparison = 0 sigue de largo hasta el final)
        else if (comparison > 0) {
            current.right = insertRecursive(current.right, value);
        }

        // Si llegamos aca no hubo cambios
        // Retornamos el mismo nodo
        // SIEMPRE LLEGA ACÁ a menos que se haya insertado finalmente el nodo
        return current;

    }

    public void remove(E value) {
        root = removeRecursive(root, value);
    }

    protected TreeNode<E> removeRecursive(TreeNode<E> current, E value) {
        // Caso Base: Llegamos al final y no estaba el value
        if (current == null) {
            return null;
        }

        //Compara el valor que quiero agregar vs el del nodo en el qude estoy parado
        int comparison = value.compareTo(current.value);

        //Entra al if cuando encontré el valor
        if (comparison == 0) {
            // Caso 1: hoja (no tiene hijos)
            if (current.left == null &&  current.right == null) {
                size--;
                return null; //Devuelvo un nodo NULL para esa posición
            }
            // Caso 2: tiene un solo hijo
            //el bloque condicional solo llega acá si alguno de los dos (left o right) NO es null
            else if (current.left == null) {
                size--;
                return current.right; //El hijo reemplaza al padre
            } else if (current.right == null) {
                size--;
                return current.left;
            }
            // Caso 3 tiene ambos hijos
            // Buscamos el minimo hijo derecho
            TreeNode<E> succesor = getMinNode(current.right);

            // Pisamos al nodo con el sucesor
            current.value = succesor.value; //Cambiamos el valor del nodo nada más, porque las aristas a los hijos se mantienen

            //Ahora removemos al sucesor para que no duplique
            current.right = removeRecursive(current.right, succesor.value); //Empiezo desde current.right porque ya sé que está en el subárbol derecho

        }
        else if (comparison < 0) {
            current.left = removeRecursive(current.left, value);
        }
        else { //(comparison > 0)
            current.right = removeRecursive(current.right, value);
        }

        //Llego acá solo en el caso 3; o si todavía no encontré el valor
        return current;
    }

    // Para encontrar el minimo vamos para la izq a fondo
    protected TreeNode<E> getMinNode(TreeNode<E> current) {
        //Mientras haya hijo izquierdo sigo bajando
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }


    public int height()
    {
        return getNodeHeightRecursive(root);
    }

    protected int getNodeHeightRecursive(TreeNode<E> current)
    {
        //Caso base: llegamos al final del árbol
        if (current == null) return -1; //Devuelve -1 para restar ese punto de más que agregó en el último llamado recursivo

        //Paso recursivo: contamos +1
        //Nos quedamos con la mayor altura de entre los hijos porque la altura del árbol es la mayor posible
        return 1 + Math.max(getNodeHeightRecursive(current.left),getNodeHeightRecursive(current.right));
    }

    //Obtener el FB de un nodo
    protected int getNodeBalanceFactor(TreeNode<E> node)
    {
        //Altura del subárbol izquierdo - Altura del subárbol derecho
        return getNodeHeightRecursive(node.left)  - getNodeHeightRecursive(node.right);
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

    public int size() {return size;}

    public boolean isEmpty() {return size == 0;}

    //Si borramos la raíz queda inaccesible el resto de nodos
    public void clear()
    {
        root = null;
        size = 0;
    }

}
