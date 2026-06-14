package priorityQueueModule;

public class SimpledLinkedPriorityQueue<E> implements SimplePriorityQueue<E> {

    public LinkedPriorityNode<E> first;
    public LinkedPriorityNode<E> last;
    private int size;


    @Override
    public void enqueue(E element, int priority) {

        //Si el elemento es null tiro error
        if(element == null)
            throw new NullPointerException("Element cannot be null.");

        //Creo el nodo
        LinkedPriorityNode<E> nodeToAdd = new LinkedPriorityNode<E>(element, priority); //Este elemento y prioridad son los que se reciben por parametro

        //Si la cola está vacía, el primero que inserto es el primero y el ultimo
        if(isEmpty()) {
            first = nodeToAdd;
            last = nodeToAdd;
            size ++;
            return;
        }

        //Cuando trabajamos con nodos no podemos usar un for, sino while

        LinkedPriorityNode<E> current = last; //Empezamos a comparar desde el final, porque es más probable que el nuevo nodo tenga una prioridad menor que los nodos que ya están en la cola
        while(current.prev != null && priority < current.priority){

            current = current.prev; //acá voy comparando todo empezando desde el final

        }

        // El loop para por dos razones distintas:
        // 1) priority < current.priority y no hay más prev → el nuevo va ANTES de current (nuevo first)
        // 2) priority >= current.priority → encontramos el lugar, el nuevo va DESPUÉS de current
        if (priority < current.priority) {
            // Caso 1: el nuevo nodo tiene menor prioridad que todos → pasa a ser el primero
            nodeToAdd.next = current;
            current.prev = nodeToAdd;
            first = nodeToAdd;
        } else {
            // Caso 2: insertamos después de current (cubre tanto el medio como el último)
            nodeToAdd.prev = current;
            nodeToAdd.next = current.next;
            if (current.next != null) {
                current.next.prev = nodeToAdd;
            } else {
                // current era el último, el nuevo pasa a ser el último
                last = nodeToAdd;
            }
            current.next = nodeToAdd;
        }

        size ++;

    }

    @Override
    public E dequeue() {
        
        //La cola está vacía, no puedo sacar nada
        if(isEmpty() || first == null)
            throw new NullPointerException("No hay nada para remover.");
        
        LinkedPriorityNode<E> firstNode = first;

        if (firstNode.next != null) {
            firstNode.next.prev = null; //Nulleo la conexión del segundo (firstNode.next) hacia el primero
            first = firstNode.next; //El nuevo primero va a ser el nodo que estaba después
        } else { //Si es el único nodo, nulleo todo
            first = null;
            last = null;
        }

        size --;

        // Devolvemos el valor guardado en el nodo, no el nodo en sí
        return firstNode.value;
    }

    @Override
    public E peek() {
        // Devolvemos el valor guardado en el nodo, no el nodo en sí
        return first.value;
    }

    @Override
    public int getHighestPriority() {
        return first.priority;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }
}
