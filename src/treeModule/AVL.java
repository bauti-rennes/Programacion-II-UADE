package treeModule;

public class AVL<E extends Comparable<E>> extends BST<E> {


    // Recibe el nodo como estaba antes de insertar
    // Devuelve el nuevo nodo en ese mismo lugar
    // (Puede recibir null con el espacio vacio)
    // (Puede que reciba el mismo nodo que recibio)
    @Override
    protected TreeNode<E> insertRecursive(TreeNode<E> current, E value) {
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

        //Si no queremos cuplidacods, cortamos como hacíamos en la clase BST
        else return current;

        // Si llegamos aca no hubieron cambios
        // Retornamos el mismo nodo
        //Lo que cambia con al clase BST es que acá le ponemos el rebalanceNode
        return rebalanceNode(current);

    }

    @Override
    protected TreeNode<E> removeRecursive(TreeNode<E> current, E value) {
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

        return rebalanceNode(current);
    }

    private TreeNode<E> rebalanceNode(TreeNode<E> node)
    {

        int bf = getNodeBalanceFactor(node);

        //Casos L
        if(bf > 1)
        {
            //Caso LL

            if (getNodeBalanceFactor(node.left)>= 0)
            {
                return rotateRight(node);
            }

            //Caso LR
            else return rotateLeftRight(node);

        }

        //Casos R
        // bf < -1 significa que el subárbol derecho tiene altura 2 o más que el izquierdo
        if(bf < -1)
        {
            //Caso RR
            if(getNodeBalanceFactor(node.right) <= 0)
            {
                return rotateLeft(node);
            }

            //Caso RL
            else return rotateRightLeft(node);
        }

        //Si el nodo no esta desbalanceado, lo devolvemos igual
        return node;

    }

    private TreeNode<E> rotateRight(TreeNode<E> y) {

        //Variables auxiliares
        TreeNode<E> x = y.left;
        TreeNode<E> t2 = x.right;

        //Cambiamos de lugar las referencias
        x.right = y;
        y.left = t2;

        //X pasa a estar en el lugar de y
        return x;
    }

    private TreeNode<E> rotateLeft(TreeNode<E> x) {

        //Variables auxiliares
        TreeNode<E> y = x.right;
        // t2 es el hijo izquierdo de y: después de la rotación pasa a ser el hijo derecho de x
        // (antes era x.left, lo que era incorrecto — ese es el subárbol A, que no se mueve)
        TreeNode<E> t2 = y.left;

        //Cambiamos de lugar las referencias
        y.left = x;
        x.right = t2;

        //X pasa a estar en el lugar de y
        return y;
    }

    private TreeNode<E> rotateLeftRight(TreeNode<E> node)
    {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    private TreeNode<E> rotateRightLeft(TreeNode<E> node)
    {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }
}
