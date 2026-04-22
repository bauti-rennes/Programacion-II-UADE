package dictionaryModule;

public class SimpleArrayDictionary<K,V> implements SimpleDictionary<K,V>{

    private K[] keys;
    private V[] values;
    private static final int DEFAULT_CAPACITY = 4;
    private int size = 0;

    //Constructor
    @SuppressWarnings("unchecked")
    public SimpleArrayDictionary() {
        keys = (K[]) new Object[DEFAULT_CAPACITY];
        values = (V[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public V put(K key, V value) {
        if (key == null) throw new NullPointerException("La llave no puede ser nula");
        if (value == null) throw new NullPointerException("El valor no puede ser nulo");

        //Vamos a tener que iterar
        for (int i = 0; i < size; i++)
        {
            if(key.equals(keys[i]))
            {
                V oldValue = values[i]
                values[i] = value;
                return oldValue; //Si existía un valor en esa key, reemplazo por el valor nuevo y devuelvo el valor viejo
            }
        }

        //si no encontró la llave, la agrega al final de la lista y retorna null
        validateSize (size +1);
        keys[size] = key; //Chequear
        values[size] = value;
        size ++;

        return null;
    }

    @Override
    public boolean remove(K key) {
        if (key == null) throw new NullPointerException("La llave no puede ser nula");
        for (int i = 0; i < size; i++)
        {
            if(key.equals(keys[i]))
            {
               if(i < size-1){ //Si no estoy en el último, agarro el último y lo reemplazo por esa posicion (acordarse de que en los diccionarios no importa el orden, entonces no me importa si cambio el orden de los elementos)
                   keys[i] = keys[size - 1]
                   values[i] = values[size - 1]
               }
               //Elimino los ultimos valores porque sino me quedan duplicados
               keys[size-1] = null;
               values[size - 1] = null;
               size --;
               return true; //me perdí acá, entender bien este metodo
            }
        }
        //si nunca encontró la llave, devuelvo false
        return false;
    }

    @Override
    public boolean containsKey(K key) {

        if (key == null) throw new NullPointerException("La llave no puede ser nula");

        for(int i = 0; i < size; i++)
            if(key.equals(keys[i]))
                return true; //Si encuentro la llave devuelvo true

        return false;
    }

    @Override
    public V get(K key) {

        if (key == null) throw new NullPointerException("La llave no puede ser nula");

        for(int i = 0; i < size; i++)
            if (key.equals(keys[i]))
                return values[i]; //Devuelvo el valor en la key

        return null;
    }

    @Override
    public K[] keys() {

        K[] result = (K[]) new Object[size];

        for (int i = 0; i < size; i++)
            result[i] = keys[i];

        return result;

    }

    @Override
    public V[] values() {

        V[] result = (V[]) new Object[size];

        for (int i = 0; i < size; i++)
            result[i] = values[i];

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

         for (int i = 0; i < size; i++) {
            keys[i] = null;
            values[i] = null;
        }
        size = 0;

    }

    private void validateSize(int newSize) {
        if (newSize > keys.length) resize(); //podríamos usar tanto keys.length como values.length porque deberían tener el mismo length
    }

    private void resize() {
        K[] tempK = (K[]) new Object[keys.length * 2];
        V[] tempV = (V[]) new Object[values.length * 2];
        for (int i = 0; i < size; i++) {
            tempK[i] = keys[i];
            tempV[i] = values[i];
        }
        keys = tempK; //chequear con la grabacion
        values = tempV; //chequear con la grabacion
    }
}

