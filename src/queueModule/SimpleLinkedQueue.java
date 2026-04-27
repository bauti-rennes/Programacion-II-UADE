package queueModule;

import listModule.LinkedNode;

public class SimpleLinkedQueue<E> implements SimpleQueue<E>{

    LinkedNode<E> first = null;
    LinkedNode<E> last = null;
    private int size = 0;

    @Override
    public void enqueue(E element) {

        LinkedNode<E> nodeToAdd = new LinkedNode<E>(element);
        //Si está vacía, agrego al principio
        if (isEmpty()){
            last = nodeToAdd;
            first = nodeToAdd;
        }
        //Si no está vacía, agrego al final
        //Nota: optimizamos el nodo al simplemente enlazarlo al next y no al prev. No nos pareció necesario usar prev.
        else {
            last.next = nodeToAdd;
            last = nodeToAdd;
        }

        size ++;
    }

    @Override
    public E dequeue() {

        //Si está vacía, no remuevo nada
        if (isEmpty()){
            throw new NullPointerException("La cola está vacía");
        }
        //Si no está vacía:
        else {
            E element = first.value; //Guardo el valor del primer elemento porque lo quiero devolver

            if (size == 1){ //Si solo tengo un nodo, nulleo todo
                first = null;
                last = null;
            }
            else { //Sino, hago que el segundo elemento sea el nuevo primero y así el primero se elimina
                first = first.next;
            }
            size --;
            return element;
        }


    }

    @Override
    public E peek() {

        //Si está vacía, no puedo peekear nada
        if (isEmpty()){
            throw new NullPointerException("La cola está vacía");
        }
        //Si no está vacía:
        else {
            E element = first.value;
            return element;
        }

    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
