package graphModule;

import dictionaryModule.SimpleArrayDictionary;
import dictionaryModule.SimpleDictionary;
import listModule.SimpleList;
import priorityQueueModule.SimplePriorityQueue;
import priorityQueueModule.SimpledLinkedPriorityQueue;
import setModule.SimpleArraySet;
import setModule.SimpleSet;

public class DjikstraSolver {

    public static <T> SimpleDictionary<T, Edge<T>> dijkstraAllNodes(Graph<T> graph, T origin) {

        //Inicializamos el diccionario ("tabla")
        SimpleDictionary<T, Edge<T>> result = new SimpleArrayDictionary<T, Edge<T>>();

        //Cargamos todos los vertices del grfo al diccionario
        SimpleList<T> vertices = graph.vertices();
        int vertexCount = vertices.size();

        //Inicializamos todos los edges a prev null, weight max
        for (int i = 0; i < vertexCount; i++)
        {
            //Inicializo el resultado con todos los vertices y un peso infinito
            result.put(vertices.get(i), new Edge<T>(null, Integer.MAX_VALUE));

        }

        //Agarra el primer nodo y le asigna peso cero
        result.get(origin).weight = 0;


        //Colecciones para nodos visitados y no visitados
        SimplePriorityQueue<T> unvisited = new SimpledLinkedPriorityQueue<T>();
        SimpleSet<T> visited = new SimpleArraySet<T>();

        //Prioridad cero porque es la más alta
        unvisited.enqueue(origin,0);

        //Mientras haya nodos no visitados, visitamos
        while(!unvisited.isEmpty())
        {
            //Guardamos el nodo actual
            T current = unvisited.dequeue();

            //Si ya lo visitamos, no hay más nada que hacer
            if (visited.contains(current)) continue;

            //Guardamos el costo total hasta current segun la tabla
            int costToCurrent = result.get(current).weight;

            //Buscamos todos los vecinos de current (segun el grafo)
            SimpleList<Edge<T>> neighbors = graph.getNeighbors(current);
            int neighborCount = neighbors.size();

            //Evaluamos todos los vecinos de current
            for (int i = 0; i < neighborCount; i++)
            {
                //Guardamos el nodo vecino individual
                T neighbor = neighbors.get(i).destination;

                //Si el nodo que está en este edge ya lo vi, me lo salteo
                if(visited.contains(neighbors.get(i).destination)) continue;

                //Guardamos el costo total hasta current segun la tabla
                int totalCost = costToCurrent + neighbors.get(i).weight;


                if (totalCost < result.get(neighbor).weight)
                {
                    //El nodo previo es el current
                    result.get(neighbor).destination = current;

                    //El costo es el que calculamos
                    result.get(neighbor).weight = totalCost;
                }
            }

            visited.add(current);
        }

        return result;
    }

}
