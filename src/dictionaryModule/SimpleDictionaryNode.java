package dictionaryModule;

public class SimpleDictionaryNode<K,V> {

    //Defino variables key, value y next
    public K key;
    public V value;
    public SimpleDictionaryNode<K,V> next = null;

    //Constructor recibe key y value y los guarda en el nodo

    public SimpleDictionaryNode(K key, V value){

        this.key = key;
        this.value = value;

    }

}
