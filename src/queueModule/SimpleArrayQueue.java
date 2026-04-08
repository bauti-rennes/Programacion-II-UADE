package queueModule;

import java.util.NoSuchElementException;

public class SimpleArrayQueue<E> implements SimpleQueue<E> {

    private E[] elements;
    private static final int DEFAULT_CAPACITY = 4;
    private int size = 0;

    @Override
    public void enqueue(E element) {

    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty."); //si está vacía tiro un exception
        E element = elements [0]; //sacamos el primero
        elements[0] = null;
        shiftLeft(0); //copypastear el shiftleft del ejercicio de las listas
        size --;
        return element;
    }

    @Override
    public E peek() {
        return elements[0]; //devuelve el primero porque usa estrategia LIFO
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {return size;}

    @Override
    public boolean isEmpty() {return size == 0;}

    private void shiftLeft(int startingIndex) {


        for (int i = startingIndex; i < size - 1; i++)
            elements[i] = elements[i + 1];

        elements[size - 1] = null;
    }
}
