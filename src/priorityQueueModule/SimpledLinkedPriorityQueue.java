package priorityQueueModule;

public class SimpledLinkedPriorityQueue<E> implements SimplePriorityQueue<E> {

    public LinkedPriorityNode<E> first;
    public LinkedPriorityNode<E> last;
    private int size;

    @Override
    public void enqueue(E element, int priority) {

        //Caso 1: El elemento es null
        if(element == null)
            throw new NullPointerException("Element cannot be null.");

        //Tengo que crear el nodo
        LinkedPriorityNode<E> nodeToAdd = new LinkedPriorityNode<E>(element, priority); //Este elemento y prioridad son los que se reciben por parametro

        //Caso 2: La cola está vacía, el primero que inserto es el primero y el ultimo
        if(isEmpty()) {
            first = nodeToAdd;
            last = nodeToAdd;
            size ++;
        }

        //Cuando trabajamos con nodos no podemos usar un for, sino while

        LinkedPriorityNode<E> current = last; //Empezamos a comparar desde el final, porque es más probable que el nuevo nodo tenga una prioridad menor que los nodos que ya están en la cola
        while(current.prev != null && priority < current.priority){

            current = current.prev; //acá voy comparando todo empezando desde el final

        }

        if (current == first){
            nodeToAdd.next = current;
            current.prev = nodeToAdd;
            first = nodeToAdd;
        }
        else if (current == last)
            {
                nodeToAdd.prev = current;
                current.next = nodeToAdd;
                last = nodeToAdd;
            }
        else {
            //4 conexiones
            nodeToAdd.prev = current; //primero modifico el que voy a agregar
            nodeToAdd.next = current.next; //sigo modificando el que voy a agregar
            current.next.prev = nodeToAdd; //siempre modifico el current.prev.next (si vengo por la izquierda) o current.next.prev (si vengo por la derecha)
            current.next = nodeToAdd; //por último modifico el current que tiene un solo punto (en este caso current.next)
        }

        size ++;

    }

    @Override
    public E dequeue() {
        
        //Caso 1: La cola está vacía, no puedo desencolar nada
        if(isEmpty() || first == null)
            throw new NullPointerException("There's nothing to dequeue.");


        
        LinkedPriorityNode<E> firstNode = first; //Empezamos a comparar desde el final, porque es más probable que el nuevo nodo tenga una prioridad menor que los nodos que ya están en la cola
        if (firstNode.next != null) {
            firstNode.next.prev = null; //El nodo que va a ser el nuevo primero, su prev va a ser null porque va a ser el nuevo primero
            first = firstNode.next; //El nuevo primero va a ser el nodo que estaba después
        } else {
            first = null; //Si el nodo que estoy desencolando es el único nodo que hay, entonces el nuevo primero va a ser null
            last = null; //Si el nodo que estoy desencolando es el único nodo que hay, entonces el nuevo último va a ser null
        }
        

        size --;


        return (E) firstNode;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public int getHighestPriority() {
        return 0;
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
}
