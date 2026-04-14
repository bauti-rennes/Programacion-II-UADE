package setModule;

public class SimpleLinkedSet<E> implements SimpleSet<E> {
    @Override
    public boolean add(E element) {
        
        return false;
    }

    @Override
    public boolean remove(E element) {
        return false;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E[] toArray() {
        return null;
    }

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        return null;
    }

    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {
        return null;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        return null;
    }
}
