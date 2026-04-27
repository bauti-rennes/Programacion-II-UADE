package dictionaryModule;

public class SimpleLinkedDictionary<K,V> implements SimpleDictionary<K,V>{

    private SimpleDictionaryNode<K,V> first = null;
    private int size = 0;

    @Override
    public V put(K key, V value) {

        //Contemplo posibilidad de valores nulos
        if (key == null) throw new NullPointerException("La llave no puede ser nula");
        if (value == null) throw new NullPointerException("El valor no puede ser nulo");

        //Creo el nodeToAdd
        SimpleDictionaryNode<K,V> nodeToAdd = new SimpleDictionaryNode<K,V>(key, value);

        //Si el diccionario está vacío, el nuevo nodo va a ser el primero
        if (isEmpty()) {
            first = nodeToAdd;
            size ++;
            return null;
        }

        //Evaluo si la primera key es la que quiero reemplazar
        if (first.key.equals(nodeToAdd.key)){
            V oldValue = first.value; //Guardo el value anterior para devolverlo después
            first.value = nodeToAdd.value; //Reemplazo el value por el nuevo
            return oldValue; //Devuelvo el value anterior
        }

        //Si existe ya la key, tengo que reemplazar el value
        SimpleDictionaryNode<K, V> current = first;

        while (current.next != null){

            if (current.next.key.equals(nodeToAdd.key)){
                V oldValue = current.value; //Guardo el value anterior para devolverlo después
                current.value = nodeToAdd.value; //Reemplazo el value por el nuevo
                return oldValue; //Devuelvo el value anterior
            }

            current = current.next;
        }

        //Si no existe la key, va a seguir de largo hasta acá
        //Como no importa el orden, lo agrego al principio porque es lo más fácil de hacer

        nodeToAdd.next = first;
        first = nodeToAdd; //Reasigno cuál consideramos el primero
        size ++;

        return null;
    }

    @Override
    public boolean remove(K key) {

        //Contemplo posibilidad de valores nulos
        if (key == null) throw new NullPointerException("La llave no puede ser nula");

        //Si el diccionario está vacío no puedo eliminar nada
        if (isEmpty()) {
            throw new NullPointerException("El diccionario está vacío");
        }

        //Si es el first, hago que el first sea first.next y así queda inaccesible
        if (first.key.equals(key)){
            first = first.next;
            size --;
            return true;
        }

        //Recorro el diccionario a ver si se encuentra la key
        SimpleDictionaryNode<K, V> current = first;

        while (current.next != null){

            //Si el next es el que quiero eliminar, lo elimino apuntando el current al current.next.next
            if (current.next.key.equals(key)){

                current.next = current.next.next;
                size --;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public boolean containsKey(K key) {

        //Contemplo posibilidad de valores nulos
        if (key == null) throw new NullPointerException("La llave no puede ser nula");

        //Evaluo si es el first
        if (first.key.equals(key)){
            return true;
        }

        //Creo el current
        SimpleDictionaryNode<K, V> current = first;

        //Recorro y si encuentro la key, devuelvo true
        while (current.next != null){

            if (current.next.key.equals(key)){
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public V get(K key) {

        //Contemplo posibilidad de valores nulos
        if (key == null) throw new NullPointerException("La llave no puede ser nula");

        //Evaluo si es el first
        if (first.key.equals(key)){
            return first.value;
        }

        //Creo el current
        SimpleDictionaryNode<K, V> current = first;

        //Recorro y si encuentro la key, devuelvo true
        while (current.next != null){

            if (current.next.key.equals(key)){
                return current.next.value;
            }

            current = current.next;
        }

        return null;
    }

    @Override
    public K[] keys() {

        //Inicializo lista de keys
        K[] result = (K[]) new Object[size];

        //Creo el current
        SimpleDictionaryNode<K, V> current = first;

        //Meto la primera key en el resultado
        result[0] = current.key;

        int i = 1;

        //Meto el resto de keys en el resultado
        while (current.next != null) {
            result[i] = current.next.key;
            i++;
            current = current.next;
        }

        return result;
    }

    @Override
    public V[] values() {

        //Inicializo lista de keys
        V[] result = (V[]) new Object[size];

        //Creo el current
        SimpleDictionaryNode<K, V> current = first;

        //Meto la primera key en el resultado
        result[0] = current.value;

        int i = 1;

        //Meto el resto de keys en el resultado
        while (current.next != null) {
            result[i] = current.next.value;
            i++;
            current = current.next;
        }

        return result;
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

        first = null; //Con esto se hace todo inaccesible
        size = 0; //Con esto reseteo la cantidad de elementos en la lista
    }
}
