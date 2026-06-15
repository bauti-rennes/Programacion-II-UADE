package treeModule;

//Extiende comparable para poder comparar valores de los nodos
//Extiende BST porque es un tipo de BST con funcionalidades extras
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

        //Si no queremos duplicados, cortamos como hacíamos en la clase BST
        //No hace falta rebalancear el nodo porque no estamos haciendo ninguna modificacion
        else return current;

        // Si llegamos aca no hubo cambios
        // Retornamos el mismo nodo
        // Lo que cambia con la clase BST es que acá le ponemos el rebalanceNode
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

        //Lo único que cambia con el BST es que rebalanceamos en cada iteracion
        return rebalanceNode(current);
    }

    private TreeNode<E> rebalanceNode(TreeNode<E> node)
    {

        //Se fija si es necesario rebalancear
        int bf = getNodeBalanceFactor(node);

        //Casos L (el subárbol izq. pesa más que el derecho)
        if(bf > 1)
        {
            //Caso LL (del subarbol izquierdo, pesa más su subarbol izquierdo)
            if (getNodeBalanceFactor(node.left)>= 0)
            {
                return rotateRight(node);
            }

            //Caso LR (del subarbol izquierdo, pesa más su subarbol derecho)
            else return rotateLeftRight(node);

        }

        //Casos R (el subárbol derecho pesa más que el izquierdo)
        if(bf < -1)
        {
            //Caso RR (del subárbol derecho, pesa más su subarbol derecho)
            if(getNodeBalanceFactor(node.right) <= 0)
            {
                return rotateLeft(node);
            }

            //Caso RL (del subárbol derecho, pesa más su subarbol izquierdo)
            else return rotateRightLeft(node);
        }

        //Si el nodo no está desbalanceado, no lo modificamos
        return node;

    }

    private TreeNode<E> rotateRight(TreeNode<E> y) {

        /*
                    y                   x
                   /                     \
                  x        -->            y
                   \                     /
                    t2                  t2
         */

        //Definimos variables auxiliares
        TreeNode<E> x = y.left;
        TreeNode<E> t2 = x.right;

        //Cambiamos de lugar las referencias
        x.right = y;
        y.left = t2;

        //X pasa a estar en el lugar de y
        return x;
    }

    private TreeNode<E> rotateLeft(TreeNode<E> x) {

        /*
                x                    y
                 \                  /
                  y      -->       x
                 /                 \
               t2                   t2
         */

        //Uso variables auxiliares
        TreeNode<E> y = x.right;
        // t2 es el hijo izquierdo de y, después de la rotación pasa a ser el hijo derecho de x
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

        /*
                           node                node
                          /                    /
                     node.left                y                    y
                         \                  /                    /  \
                          y      -->    node.left  -->   node.left   node
                         /                 \                   \
                       t2                   t2                  t2
         */

    }


    private TreeNode<E> rotateRightLeft(TreeNode<E> node)
    {
        node.right = rotateRight(node.right);
        return rotateLeft(node);

        /*
                           node                  node
                             \                      \
                            node.right                x                         x
                            /                            \                    /  \
                           x          -->            node.right  -->       node   node.right
                            \                          /                         /
                            t2                       t2                        t2
         */

    }
}
