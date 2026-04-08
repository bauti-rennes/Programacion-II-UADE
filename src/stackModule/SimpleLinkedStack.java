package stackModule;

import list.LinkedNode;

import java.util.NoSuchElementException;

public class SimpleLinkedStack<E> implements SimpleStack<E> {

    private LinkedNode<E> last = null;
    private int size = 0;

    @Override
    public void push(E element) {

        LinkedNode<E> addedNode = new LinkedNode<E>(element);

        if (!isEmpty()){
            addedNode.prev = last;
        }

        last = addedNode;
        size ++;

    }

    @Override
    public E pop() {
        if(isEmpty()) throw new NoSuchElementException("Stack is empty");
        E element = last.value;
        if (size == 1) last = null;
        else last = last.prev;
        size --;
        return element;
    }

    @Override
    public E peek() {

        if(isEmpty()) throw new NoSuchElementException("Stack is empty");

        return last.value;
    }

    @Override
    public void clear() {
        last = null; // hago esto para que se vuelva todo inaccesible
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() { return size == 0;}
}
