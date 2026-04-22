package listModule;

public class LinkedNode<E> {

    public LinkedNode<E> prev = null;
    public LinkedNode<E> next = null;
    public E value = null;

    // Constructor: recibe un dato y lo guarda en value
    // Se llama cuando hacemos new LinkedNode<E>()
    public LinkedNode(E newValue) {
        value = newValue;
    }
}
