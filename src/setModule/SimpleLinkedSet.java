package setModule;

import list.LinkedNode;

public class SimpleLinkedSet<E> implements SimpleSet<E> {

    private LinkedNode<E> first = null;
    private int size = 0;

    @Override
    public boolean add(E element) {
        if (contains(element)) return false;

        LinkedNode<E> newNode = new LinkedNode<>(element);

        if (isEmpty()) {
            first = newNode;
        } else {
            LinkedNode<E> current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(E element) {
        LinkedNode<E> current = first;

        while (current != null) {
            if (current.value.equals(element)) {
                if (current.prev != null) current.prev.next = current.next;
                else first = current.next; // era el primero

                if (current.next != null) current.next.prev = current.prev;

                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public boolean contains(E element) {
        LinkedNode<E> current = first;
        while (current != null) {
            if (current.value.equals(element)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() {
        first = null;
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
        LinkedNode<E> current = first;
        int i = 0;
        while (current != null) {
            result[i] = current.value;
            i++;
            current = current.next;
        }
        return result;
    }

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        SimpleLinkedSet<E> result = new SimpleLinkedSet<>();
        LinkedNode<E> current = first;
        while (current != null) {
            result.add(current.value);
            current = current.next;
        }
        E[] otherArray = other.toArray();
        for (int i = 0; i < otherArray.length; i++) {
            result.add(otherArray[i]);
        }
        return result;
    }

    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {
        SimpleLinkedSet<E> result = new SimpleLinkedSet<>();
        LinkedNode<E> current = first;
        while (current != null) {
            if (other.contains(current.value)) result.add(current.value);
            current = current.next;
        }
        return result;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        SimpleLinkedSet<E> result = new SimpleLinkedSet<>();
        LinkedNode<E> current = first;
        while (current != null) {
            if (!other.contains(current.value)) result.add(current.value);
            current = current.next;
        }
        return result;
    }
}
