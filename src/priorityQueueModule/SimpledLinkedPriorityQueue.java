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
            //son 4 conexiones
            nodeToAdd.prev = current; //primero modifico el que voy a agregar
            nodeToAdd.next = current.next; //sigo modificando el que voy a agregar
            current.next.prev = nodeToAdd; //siempre modifico el current.prev.next (si vengo por la izquierda) o current.next.prev (si vengo por la derecha)
            current.next = nodeToAdd; //por último modifico el current que tiene un solo punto (en este caso current.next)
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


        return (E) firstNode;
    }

    @Override
    public E peek() {
        return (E) first;
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
