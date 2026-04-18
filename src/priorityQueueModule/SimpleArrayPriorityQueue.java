package priorityQueueModule;

import java.util.NoSuchElementException;

public class SimpleArrayPriorityQueue<E> implements SimplePriorityQueue<E> {

    // ---Defino variables---
    private E[] elements; //Por qué es un E[] ??? qué signiica eso. ponerme al día
    private int[] priorities;
    public static final int DEFAULT_CAPACITY = 4;
    private int size = 0;


    // Esto es el CONSTRUCTOR
    public SimpleArrayPriorityQueue() {

        elements = (E[]) new Object[DEFAULT_CAPACITY]; //¿por qué acá puede poner Object sin definir el tipo de objeto?. Entender esto de java.
        priorities = new int[DEFAULT_CAPACITY];

    }

    public SimpleArrayPriorityQueue(int capacity) {

        elements = (E[]) new Object[capacity]; //¿por qué acá puede poner Object sin definir el tipo de objeto?. Entender esto de java.
        priorities = new int[capacity];
    }


    @SuppressWarnings("unchecked")
    @Override
    public void enqueue(E element, int priority) {

        if(element == null)
            throw new NullPointerException("Element cannot be null.");

        validateSize(size +1);

        int insertIndex = size;

        for(int i = size; i > 0 && priority < priorities[i]; i--) { //Empiezo por la derecha y voy moviendo elemento por elemento 1 posicion a la derecha

            //Correr el elemento a la derecha
            elements[i] = elements[i-1];
            priorities[i] = priorities[i-1];

            insertIndex = i - 1; //En el ultimo ciclo del for, este es el index en el que se te va a insertar despues el elemento

        }

        size++;
        elements[insertIndex] = element;
        priorities[insertIndex] = priority;
        size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty."); //si está vacía tiro un exception
        E element = elements [0]; //sacamos el primero
        elements[0] = null;
        shiftLeft(0);
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

        for (int i = startingIndex; i < size - 1; i++) //En el caso de queues, el sartingIndex siempre va a ser cero.
        {
            elements[i] = elements[i + 1];
            priorities[i] = priorities[i + 1];
        }

        elements[size - 1] = null; //nulleamos el ultimo porque es una referencia a un objeto --> ese objeto me quedaría en memoria cuando no lo quiero
        priorities[size - 1] = Integer.parseInt(null);
    }

    @SuppressWarnings("unchecked")
    private void resize() {

        E[] temp = (E[]) new Object[elements.length*2];
        int[] tempPriorities = new int[temp.length];

        for (int i = 0; i < size; i++) {

            temp[i] = elements[i];
            tempPriorities[i] = priorities[i];

        }
        elements = temp;
        priorities = tempPriorities;
    }

    @Override
    public int getHighestPriority()
    {
        return priorities[0]; //Devuelve la prioridad del primero (que es el que tiene más prioridad)
    }

    private void validateSize(int newSize) {
        if (newSize > elements.length) resize();
    }

    //Esta es una nueva función que agregamos para el priority queue exercise. Manipulamos el queue como si fuera un array
    public E removeIndex(int index){
        E element = elements [index]; //sacamos el primero
        elements[index] = null;
        shiftLeft(index);
        size --;
        return element;
    }
}
