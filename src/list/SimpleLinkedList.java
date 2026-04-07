package list;

public class SimpleLinkedList<E> implements SimpleList<E> {

    private LinkedNode<E> first = null;
    private LinkedNode<E> last = null;
    private int size = 0;

    @Override
    public boolean add(E element) {

        LinkedNode<E> addedNode = new LinkedNode<E>(element);

        if (size == 0) {
            first = addedNode;
            last = addedNode;
        } else {
            last.next = addedNode; // El que antes era el último, ahora tiene como "siguiente" al nuevo.
            addedNode.prev = last; // El nuevo nodo dice: "mi 'anterior' es el que era el último".
        }

        last = addedNode;
        size++;

        return true;
    }

    @Override
    public void add(int index, E element) {

        // Si el elemento que quiero insertar lo quiero poner en el ultimo indice, invoco a la función add
        if (index == size)
        {
            add(element);
            return;
        }

        validateIndex(index);
        LinkedNode<E> addedNode = new LinkedNode<E>(element);

        /*
        si estoy queriendo insertar entre dos nodos, primero quiero encontrar esos dos nodos:
        la unica forma de hacer eso es pasar por todos los nodos secuencialmente,
        no es como las listas que le pones un indice y te lleva directo ahi
         */

        //Si quiero insertar un nodo en el primer lugar,
        if (index == 0)
        {
            addedNode.next = first; //el siguiente al added es el que era el primero (first)
            first.prev = addedNode; //El previo al que era el primero se convierte en el added
            first = addedNode; // el primero se convierte en el added. EL ORDEN DE LAS INSRUCCIONES IMPORTA
        }
        else
        {
            //reconectar nodos intemedios
            LinkedNode<E> existingNode = getNodeByIndex(index);
            addedNode.next = existingNode;
            addedNode.prev = existingNode.prev;

            existingNode.prev.next = addedNode; //ENTENDER ESTO
            existingNode.prev = addedNode;
        }

        size ++;
    }

    @Override
    public E remove(int index) {

        validateIndex(index);
        LinkedNode<E> nodeToRemove = getNodeByIndex(index);
        E removedValue = nodeToRemove.value;

        if (size == 1) {
            // Lista queda vacía
            first = null;
            last = null;
        } else if (index == 0) {
            // Removemos el primero
            first = nodeToRemove.next;
            first.prev = null;
        } else if (index == size - 1) {
            // Removemos el último
            last = nodeToRemove.prev;
            last.next = null;
        } else {
            // Removemos un nodo intermedio
            nodeToRemove.prev.next = nodeToRemove.next;
            nodeToRemove.next.prev = nodeToRemove.prev;
        }

        size--;
        return removedValue;
    }

    @Override
    public boolean remove(Object object) {
        LinkedNode<E> current = first;
        int index = 0;
        while (current != null) {
            if (current.value.equals(object)) {
                remove(index);
                return true;
            }
            current = current.next;
            index++;
        }
        return false;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean contains(Object object) {
        LinkedNode<E> current = first;
        while (current != null) {
            if (current.value.equals(object))
                return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        return getNodeByIndex(index).value;
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index);
        LinkedNode<E> node = getNodeByIndex(index);
        E oldValue = node.value;
        node.value = element;
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void validateIndex(int index)
    {
    if(index < 0 || index >= size) //si el index es menor a cero o es mayor al tamaño de la lsita
        throw new IndexOutOfBoundsException();

    //si la función puede ejecutarse correctamente, es porque esta validación pasó
    }

    private LinkedNode<E> getNodeByIndex(int index)
    {
        //Acá voy a tener que ir secuencialmente nodo por nodo

        if (index < size - index) //Si estoy más cerca del principio que del final...
        {
            LinkedNode<E> currentNode = first;
            for(int i = 0; i < index; i++) //el for corta cuando llega al index
            {
                currentNode = currentNode.next;
            }
            return currentNode;
        }
        else //Si estoy más cerca del final que del principio...
        {
            LinkedNode<E> currentNode = last;
            for (int i = size - 1; i > index; i--)
                currentNode = currentNode.prev;
            return currentNode;
        }

    }
}
