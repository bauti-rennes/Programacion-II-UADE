package graphModule;

import dictionaryModule.SimpleDictionary;
import java.util.ArrayList;

public class DijstraShorterPath<T> {
    private ArrayList<T> shorterPath;
    private int weight;

    public DijstraShorterPath(Graph<T> graph, T origin, T destination) {
        shorterPath = new ArrayList<T>();

        SimpleDictionary<T, Edge<T>> dijstraMatrix = DijkstraSolver.dijkstraAllNodes(graph, origin);

        // Si el destino no es alcanzable, weight queda -1 y el camino vacío
        if (dijstraMatrix.get(destination).weight == Integer.MAX_VALUE) {
            weight = -1;
            return;
        }

        weight = dijstraMatrix.get(destination).weight;

        // Reconstruimos el camino yendo para atrás desde destination hasta origin
        // usando el campo "destination" de cada Edge (que guarda el nodo previo)
        T current = destination;
        while (current != null) {
            shorterPath.add(0, current); // insertamos al frente para que quede en orden
            if (current.equals(origin)) break;
            current = dijstraMatrix.get(current).destination;
        }
    }

    public ArrayList<T> getShorterPath() {
        return shorterPath;
    }

    public int getWeight() {
        return weight;
    }
}
