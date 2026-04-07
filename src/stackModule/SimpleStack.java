package stackModule;

//Todo igual que SimpleStack, pero:
//push -> enqueue
//pop -> dequeue

public interface SimpleStack<E> {
    public void push(E element);
    public E pop();
    public E peek();
    public void clear();
    public int size();
    public boolean isEmpty() ;
}
