package graphModule;

public class Edge {


    //COMPLETAR

    public desination

    public Edge(T destination, int weight){
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object other) {

        if (other.getClass() != getClass()) return false;

        @SuppressWarnings("unchecked")
        Edge<T> edge = (Edge<T>) other;

        if (destination != edge.destination) return false;
        if (weight != edge.weight) return false;
        return true;
    }

}
