package graphModule;

public interface Graph<T> {

    //CHEQUEAR CON GRABACION

    public T[] vertices();
    public boolean addVertex(T vertex);
    public boolean removeVertex(T vertex);
    public boolean addEdge(T from, T to, int weight);
    public boolean RemoveEdge(T from, T to);
    public boolean containsVertex(T vertex);
    public boolean containsEdge(T from, T to);
    public int getWeight(T from, T to);


}
