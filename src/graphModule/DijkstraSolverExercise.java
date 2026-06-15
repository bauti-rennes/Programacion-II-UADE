package graphModule;

import java.util.Scanner;
import java.util.ArrayList;

import application.Exercise;
import listModule.SimpleList;



// Hay que pedirle al usuario que pida el camino mas corto posible.

public class DijkstraSolverExercise extends Exercise{

    //Inicializamos variables
    private int currentPhase = 0;
    private boolean firstTime = true;

    //Usamos vértices de tipo String porque son nombres de barrios
    private ListGraph<String> graph;

    //Constructor
    public DijkstraSolverExercise(Scanner scnr) {
        super(scnr);
        graph = new ListGraph<String>();
        //Metemos los valores de prueba directamente
        addTestValues();
    }

    @Override
    protected void exerciseLogic() {

        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                printGraph();
                break;
            case 2:
                shortestPathLogic();
                break;

        }

    }

    private void menuLogic() {

        if (firstTime) {
            firstTime = false;
            System.out.println("\nBienvenido al ejercicio de Dijkstra");
        }

        System.out.println("\nElegir una opción:"
                + "\nm: Mostrar grafo"
                + "\nc: Camino más corto entre dos nodos"
                + "\nmm: Menú principal");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {

            case "m":
                currentPhase = 1;
                break;
            case "c":
                currentPhase = 2;
                break;
            case "mm":
                running = false;
                break;
            default:
                System.out.println("Opción inválida, intentar de nuevo");
                break;

        }

    }

private boolean returnMenu() {

    boolean returnToMenu = false;

    boolean bandera = true;

    while (bandera){
        System.out.println("\n¿Volver al menu? (s/n)");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {
            case "s":
                returnToMenu = true;
                bandera = false;
                break;
            case "n":
                returnToMenu = false;
                bandera = false;
                break;
            default:
                System.out.println("\nOpción inválida. Intente de nuevo");
        }
    }
    return returnToMenu;


}
    private void addTestValues() {

        //Primero metemos vértices así después podemos meter edges
        graph.addVertex("Casa");
        graph.addVertex("Belgrano");
        graph.addVertex("Monserrat");
        graph.addVertex("Recoleta");
        graph.addVertex("Pinamar");

        graph.addEdge("Casa", "Belgrano", 1);
        graph.addEdge("Casa", "Recoleta", 8);
        graph.addEdge("Casa", "Pinamar", 250);
        graph.addEdge("Belgrano", "Monserrat", 4);
        graph.addEdge("Belgrano", "Recoleta", 6);
        graph.addEdge("Recoleta", "Monserrat", 2);
        graph.addEdge("Monserrat", "Pinamar", 170);

        return;


    }

    //Esto es para printear el grafo (ver conexiones entre barrios y costos)
    private void printGraph() {

        //Para mostrar el grafo, obtenemos la lista de vértices
        SimpleList<String> verticesGraph = graph.vertices();

        System.out.println("-----Conexiones-----");
        //Iteramos sobre la lista de vértices
        for (int i = 0; i < verticesGraph.size(); i++) {

            //Guardamos el nombre del vértice
            String vertex = verticesGraph.get(i);

            //Obtenemos lista de Edges para este vértice específico
            SimpleList<Edge<String>> edges = graph.getNeighbors(vertex);

            //Printeamos cada Edge
            for (int f = 0; f < edges.size(); f++) {
                System.out.println(vertex + " -> " + edges.get(f).destination + ": " + edges.get(f).weight);
            }

        }

        //para volver al menú
        currentPhase = 0;

        return;

    }

    //TODO esto vuela??????
    private void showDijkstra() {
        // <T> graphResultDijstra = dijkstra

        // nos va a dar la matriz resulta

        // aca agregamos un funcion que de la matriz devolvemos el resultado
        // aca va la logica
    }

    // Pide al usuario un nodo y valida que exista en el grafo antes de continuar
    private String askNode(String prompt) {

        //Conseguimos la lista de vertices para mostrarsela después al usuario
        SimpleList<String> vertices = graph.vertices();
        String input = null;
        boolean valid = false;

        //se reinicia el bucle si se selecciona un vértice inexistente
        while (!valid) {
            System.out.println(prompt);

            // Mostramos los nodos disponibles para que el usuario sepa qué ingresar
            System.out.print("Nodos disponibles: ");
            for (int i = 0; i < vertices.size(); i++) {
                System.out.print(vertices.get(i) + (i < vertices.size() - 1 ? ", " : "\n"));
                //La coma solo se printea si NO es el último vértice
            }

            input = scanner.nextLine().trim(); //El trim elimina espacios vacios en lo que ingresa el usuario

            // Verificamos que el nodo ingresado exista en el grafo
            boolean found = false;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).equalsIgnoreCase(input)) { //IgnoreCase hace que no importen mayusculas o minusculas
                    input = vertices.get(i); // Cambiamos el input por el nombre exacto del grafo (por tema mayusculas)
                    found = true;
                    break;
                }
            }

            if (found) {
                valid = true;
            } else {
                System.out.println("\nNodo " + input + " no encontrado. Intentá de nuevo.");
            }
        }

        return input;
    }

    // Pide origen y destino, calcula el camino más corto y lo muestra
    private void shortestPathLogic() {

        //Esas funciones incluyen validacion
        String origin = askNode("\nIngresá el nodo de origen:");
        String destination = askNode("\nIngresá el nodo de destino:");

        //result tiene 2 elementos: shorterPath y weight
        DijstraShorterPath<String> result = new DijstraShorterPath<>(graph, origin, destination);

        //Puede ser que no haya camino
        if (result.getWeight() == -1)
        {
            System.out.println("\nNo existe camino entre " + origin + " y " + destination + ".");
        }
        else //En caso de que sí haya camino...
        {
            //getShorterPath devuelve una lista con el camino más corto
            ArrayList<String> path = result.getShorterPath();

            System.out.print("\nCamino más corto: ");
            //Imprimo la lista iterándola
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i) + (i < path.size() - 1 ? " -> " : ""));
            }
            System.out.println("\nCosto total: " + result.getWeight());
        }

        boolean backToMenu = returnMenu();
        if (backToMenu) {
            currentPhase = 0;
        }
    }
}
