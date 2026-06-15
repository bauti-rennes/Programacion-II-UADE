package graphModule;

import listModule.SimpleList;
import listModule.SimpleLinkedList;
import dictionaryModule.SimpleDictionary;
import dictionaryModule.SimpleArrayDictionary;

public class ListGraph<T> implements Graph<T> {

    //La lista de adyacencia es un diccionario que tiene T como llaves y una lista de Edges (aristas) como valores
    /*
    Usamos interfaces SimpleDictionary y SimpleList
    en lugar de las implementaciones SimpleArrayDictionary y SimpleLinkedList
    porque queremos que puedan tomar cualquier valor a la hora de instanciarlas.
    No se pueden instanciar interfaces, solo implementaciones así que por eso
    en el constructor se usan las implementaciones
     */
    private SimpleDictionary<T, SimpleLinkedList<Edge<T>>> adjacencyList;

    public ListGraph() {
        adjacencyList = new SimpleArrayDictionary<T,SimpleLinkedList<Edge<T>>>();
    }

    @Override
    //Esta es la función más importante, la usa el resto de funciones
    //Devolver la arista que va de "from" a "to"
    public Edge<T> getEdge(T from, T to)
    {
        // Si no está el nodo de origen en adjacencyList, no va a estar el edge
        if(!containsVertex(from)) return null;

        // Si llegamos hasta aca, podemos buscar la lista de edge:
        SimpleList<Edge<T>> edges = adjacencyList.get(from); //diccionario.get(key) busca el valor de esa key, que en este caso es una lista

        //Iteramos la lista hasta encontrar el edge a devolver
        //Acá se entiende por qué usamos ArrayList y no LinkedList (es más fácil de recorrer)
        for(int i = 0; i < edges.size(); i++)
        {
            if (edges.get(i).destination.equals(to)) return edges.get(i);
        }

        //Si llegamos acá, no está el edge
        return null;
    }

    //Devuelve lista de vertices
    @Override
    public SimpleList<T> vertices() {return adjacencyList.keys();}

    //False si ya está el vértice; True si se añadió el vértice
    @Override
    public boolean addVertex(T vertex) {
        if(containsVertex(vertex)) return false;

        adjacencyList.put(vertex, new SimpleLinkedList<Edge<T>>());
        return true;
    }

    @Override
    public boolean removeVertex(T vertex) {

        //Si no está el vértice, no se puede remover
        if(!containsVertex(vertex)) return false;

        //Eliminamos el vértice como llave del diccionario, eso también elimina su lista de edges
        adjacencyList.remove(vertex) ;

        //Y después lo sacamos de las listas de edges de los otros vértices
        //Para eso hacemos lista de los vértices
        SimpleList<T> vertices = vertices();
        //Para cada vértice...
        for(int i= 0; i < vertices.size(); i++) {
            //Si existe arista que vaya al vértice a eliminar, la borro
            removeEdge(vertices.get(i), vertex);
        }

        return true;
    }

    @Override
    public boolean addEdge(T from, T to, int weight) {

        // Agregamos los vertices
        // Si ya están, sigue de largo
        addVertex(from);
        addVertex(to);

        //Devuelve el Edge si existe. Si no existe, devuelve null
        Edge<T> edge = getEdge(from,to);

        //Si no existe el edge, lo creamos
        if(edge == null)
        {
            //Agregamos el edge de from a to
            adjacencyList.get(from).add(new Edge<T>(to, weight));
            return true;
        }

        //Si llega acá es porque existía el edge, entonces chequeamos weight
        //Si los weights son distintos, lo actualiza
        if(edge.weight!= weight){
            edge.weight = weight;
            return true;
        }

        // Si llega acá es porque no cambió nada, devuelve false
        return false;
    }

    @Override
    public boolean removeEdge(T from, T to) {

        // Buscamos el edge de from a to
        Edge<T> edge = getEdge(from, to);

        // Si existe el edge, lo borra
        if(edge != null)
        {
            adjacencyList.get(from).remove(edge);
            return true;
        }

        //Si llegamos acá, no existía el edge
        return false;
    }

    //True si el vértice es una llave en la adjacencyList
    @Override
    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    //Devuelve la lista de edges del vértice que recibe por parámetro
    @Override
    public SimpleList<Edge<T>> getNeighbors(T vertex) {
        return adjacencyList.get(vertex);
    }

    //True si el getEdge() devuelve un edge
    @Override
    public boolean containsEdge(T from, T to) {
        return getEdge(from, to) != null;
    }


    public int getWeight(T from, T to) {
        Edge<T> targetEdge = getEdge(from, to);
        if (targetEdge == null) return -1; //Si no existe el edge, devuelve -1
        return targetEdge.weight;
    }


}
