package setModule;

public class SimpleArraySet<E> implements SimpleSet<E> {

    private E[] elements;
    private static final int DEFAULT_CAPACITY = 4;
    private int size = 0;

    public SimpleArraySet() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean add(E element) {
        if (contains(element)) return false; //Si ya está el elemento, devuelvo false porque no puede haber duplicados
        validateSize(size + 1);
        elements[size] = element; //Lo agregamos al final del array
        size++;
        return true;
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                // corremos todos los elementos a la izquierda para no dejar espacios vacíos
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[size - 1] = null;
                size--;
                return true;
            }
        }
        return false; //si nunca encontró el elemento, llega acá
    }

    //Recorro el array buscando el elemento
    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) return true;
        }
        return false;
    }

    @Override
    public void clear() { //Nulleo todos los elementos y reseteo size
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    //Esto es para transformar el tipo de dato de SimpleArraySet a Array en realidad, aunque de por sí ya es un array
    @Override
    public E[] toArray() {
        E[] result = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = elements[i];
        }
        return result;
    }

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {

        //Inicializo resultado
        SimpleArraySet<E> result = new SimpleArraySet<E>();

        //elements es mi set "principal", lo agrego primero
        for (int i = 0; i < size; i++) {
            result.add(elements[i]);
        }

        //other es el set que se pasa por parámetro, lo agrego después. El add ya contempla los repetidos
        E[] otherArray = other.toArray();
        for (int i = 0; i < otherArray.length; i++) {
            result.add(otherArray[i]);
        }

        //Siempre va a haber resultado
        return result;
    }

    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {

        SimpleArraySet<E> result = new SimpleArraySet<E>();

        //Recorro todos los elementos de elements, pero solo los agrego si other.contains me da true
        for (int i = 0; i < size; i++) {
            if (other.contains(elements[i])) {
                result.add(elements[i]);
            }
        }
        return result;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {

        SimpleArraySet<E> result = new SimpleArraySet<E>();

        //Es igual a la intersección solo que agregamos un "!" para que los elementos que no se agreguen sean los que comparten
        for (int i = 0; i < size; i++) {
            if (!other.contains(elements[i])) {
                result.add(elements[i]);
            }
        }
        return result;
    }

    private void validateSize(int newSize) {
        if (newSize > elements.length) resize();
    }

    private void resize() {
        E[] temp = (E[]) new Object[elements.length * 2];
        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }
}
