package graphModule;

import dictionaryModule.SimpleArrayDictionary;
import dictionaryModule.SimpleDictionary;
import listModule.SimpleList;
import priorityQueueModule.SimplePriorityQueue;
import priorityQueueModule.SimpledLinkedPriorityQueue;
import setModule.SimpleArraySet;
import setModule.SimpleSet;

public class DijkstraSolver {

    //Paso por parámetro un grafo y un origen (un nodo inicial)
    //Devuelve un diccionario con cada nodo del grafo como key, y un edge con el nodo previo y el peso total hasta ese nodo como value (devuelve la tabla que vimos en clase)
    // T = Nodo
    // Edge<T> = (Previo, Costo)
    //Esa <T> al lado de static está porque como el métoddo es static no puede usar el T de una clase genérica. Necesito declararlo.
    public static <T> SimpleDictionary<T, Edge<T>> dijkstraAllNodes(Graph<T> graph, T origin) {

        //Inicializamos el diccionario (la tabla)
        SimpleDictionary<T, Edge<T>> result = new SimpleArrayDictionary<T, Edge<T>>();

        //Cargamos todos los vertices del grafo al diccionario
        SimpleList<T> vertices = graph.vertices();

        //Contamos la cantidad de vertices porque después iteramos sobre eso
        int vertexCount = vertices.size();

        //Inicializamos todos los edges a: prev null, weight max (esto es por la teoría que vimos en clase)
        for (int i = 0; i < vertexCount; i++)
        {
            //Inicializo el resultado con todos los vertices, que tienen como previo un null y un peso infinito
            result.put(vertices.get(i), new Edge<T>(null, Integer.MAX_VALUE));
            // Lo que hace aca es darle valor maximo a cada vertice antes de visitarlo

        }

        //Agarra el primer nodo (el origen) y le asigna peso cero
        result.get(origin).weight = 0;


        //Colecciones para nodos visitados y no visitados
        SimplePriorityQueue<T> unvisited = new SimpledLinkedPriorityQueue<T>();
        SimpleSet<T> visited = new SimpleArraySet<T>();

        //Prioridad cero al origen porque es el nodo inicial, y lo agrego a la cola de no visitados
        unvisited.enqueue(origin,0);

        //Mientras haya nodos no visitados, visitamos
        while(!unvisited.isEmpty())
        {
            //Guardamos el nodo actual y lo sacamos de la lista de no visitados
            T current = unvisited.dequeue();

            //Si ya lo visitamos, no hay más nada que hacer
            //En Java, continue hace que se siga con la proxima iteracion del bucle
            if (visited.contains(current)) continue;

            //Guardamos el costo total hasta current segun la tabla
            int costToCurrent = result.get(current).weight;

            //Buscamos todos los vecinos de current (segun el grafo)
            //Esto te da una lista de edges de ese vertice [destino1, peso1; destino2, peso2;....]
            SimpleList<Edge<T>> neighbors = graph.getNeighbors(current);
            int neighborCount = neighbors.size();

            //Evaluamos todos los vecinos de current
            for (int i = 0; i < neighborCount; i++)
            {
                //Guardamos el nodo vecino individual
                T neighbor = neighbors.get(i).destination;

                //Si el nodo que está en este edge ya lo vi, me lo salteo (ya hay un camino más barato hacia él)
                if(visited.contains(neighbors.get(i).destination)) continue;

                //Guardamos el costo total hasta current segun la tabla
                int totalCost = costToCurrent + neighbors.get(i).weight;

                //Agregamos el vecino a la cola de no visitados
                unvisited.enqueue(neighbor, totalCost);

                //Si el costo que calculamos es menor al de la tabla

                //Actualizamos la tabla: si el costo que calculamos es menor al que está en la tabla (al principio infinito) se actualiza
                if (totalCost < result.get(neighbor).weight)
                {
                    //El nodo previo es el current ("destination" es el prev, no confundirse con el siguiente)
                    //Se llama destination ese elemento porque lo definimos así en la clase edge (destination, weight), pero en la tabla es el nodo previo
                    //result.get(neighbor) devuelve un Edge
                    result.get(neighbor).destination = current;

                    //El costo es el que calculamos
                    result.get(neighbor).weight = totalCost;
                }
            }
            //Lo agrego en la lista de visitados así no lo vuelve a recorrer
            visited.add(current);
        }
        return result;
    }

}
