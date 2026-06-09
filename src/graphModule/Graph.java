package graphModule;

import listModule.SimpleList;

public interface Graph<T> {

    //CHEQUEAR CON GRABACION
    public SimpleList<T> vertices();
    public boolean addVertex(T vertex);
    public boolean removeVertex(T vertex);
    public boolean addEdge(T from, T to, int weight);
    public boolean removeEdge(T from, T to);
    public boolean containsVertex(T vertex);
    public boolean containsEdge(T from, T to);
    public int getWeight(T from, T to);
    public Edge<T> getEdge(T from, T to);
    public SimpleList<Edge<T>> getNeighbors(T vertex);


}
