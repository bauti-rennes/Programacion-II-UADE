package priorityQueueModule;

public interface SimplePriorityQueue<E> {

    public void enqueue(E element, int priority);
    public E dequeue (); //No recibe nada por parámetro porque siempre sacamos el primero
    public E peek (); //Me devuelve el primer elemento, que de por sí es el que tiene más prioridad
    public int getHighestPriority(); //Me devuelve la prioridad con más prioridad (por eso es un int) --> no quiero el objeto, quiero la prioridad
    public int size();
    public boolean isEmpty();
    public void clear();

}
