package stackModule;

import java.util.NoSuchElementException;

public class SimpleArrayStack<E> implements SimpleStack<E> {

    private E[] elements;
    private static final int DEFAULT_CAPACITY = 4;
    private int size = 0;

    public SimpleArrayStack() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];

    }

    @Override
    public void push(E element) {

        validateSize(size + 1); //COPIAR EN ALGÚN LADO LA FUNCIÓN validateSize que hizo él
        elements [size] = element;
        size++;

    }

    @Override
    public E pop() {

        if (isEmpty()) throw new NoSuchElementException("Stack is empty."); //si está vacía tiro un exception
        E element = elements [size-1];
        elements [size-1] = null;
        size --;
        return element;

    }

    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty."); //si está vacía tiro un exception
        return elements[size-1]; //devuelve el último elemento
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++){
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() {return size == 0;}
}

