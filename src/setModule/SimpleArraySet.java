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
        if (contains(element)) return false;
        validateSize(size + 1);
        elements[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                // Shift elements left to fill the gap
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) return true;
        }
        return false;
    }

    @Override
    public void clear() {
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
        SimpleArraySet<E> result = new SimpleArraySet<E>();
        for (int i = 0; i < size; i++) {
            result.add(elements[i]);
        }
        E[] otherArray = other.toArray();
        for (int i = 0; i < otherArray.length; i++) {
            result.add(otherArray[i]);
        }
        return result;
    }

    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {
        SimpleArraySet<E> result = new SimpleArraySet<E>();
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
