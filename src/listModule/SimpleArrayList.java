package listModule;

public class SimpleArrayList<E> implements SimpleList<E> {

    private static final int INITIAL_CAPACITY = 8;
    private Object[] data;
    private int size = 0;

    public SimpleArrayList() {
        data = new Object[INITIAL_CAPACITY];
    }

    @Override
    public boolean add(E element) {
        ensureCapacity();
        data[size] = element;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        // add al final usa el otro método
        if (index == size) {
            add(element);
            return;
        }
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        ensureCapacity();
        // Correr todos los elementos desde index hacia la derecha
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        validateIndex(index);
        E removed = (E) data[index];
        // Correr todos los elementos desde index+1 hacia la izquierda
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null; // Limpiar el último espacio liberado
        size--;
        return removed;
    }

    @Override
    public boolean remove(Object object) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(object)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(Object object) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(object))
                return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        validateIndex(index);
        return (E) data[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        validateIndex(index);
        E old = (E) data[index];
        data[index] = element;
        return old;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // --- Helpers privados ---

    private void validateIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    // Si el array está lleno, lo reemplaza por uno del doble de tamaño
    private void ensureCapacity() {
        if (size == data.length) {
            Object[] newData = new Object[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }
}
