package priorityQueueModule;

public class LinkedPriorityNode<E>{

    //Estos los necesito para guardar el valor y la prioridad del nodo:
    public E value;
    public int priority;

    //Estos los necesito para la referencia con otros nodos:
    public LinkedPriorityNode<E> prev = null;
    public LinkedPriorityNode<E> next = null;

    //Esto es el constructor, que recibe un valor y una prioridad y los guarda en el nodo:
    public LinkedPriorityNode(E value, int priority) {
        this.value = value;
        this.priority = priority;
    }
}
