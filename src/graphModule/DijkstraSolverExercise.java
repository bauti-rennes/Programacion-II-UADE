package graphModule;

import java.util.Scanner;
import java.util.ArrayList;

import application.Exercise;
import listModule.SimpleList;



// TODO: Hay que calcular el dijkstra
// Hay que pedirle al usuario que pida el camino mas corto posible.

public class DijkstraSolverExercise extends Exercise{
    private int currentPhase = 0;
     private boolean firstTime = true;
     private ListGraph<String> graph;

    public DijkstraSolverExercise(Scanner scnr) {
        super(scnr);
        graph = new ListGraph<String>();
        addTestValues();
    }

    @Override
    protected void exerciseLogic() {

        //El switch lo copypasteamos de ListExercise porqued va a ser igual
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
    private void printGraph() {

        
        SimpleList<String> verticesGraph = graph.vertices();

        for (int i = 0; i < verticesGraph.size(); i++) {
            String vertex = verticesGraph.get(i);
            
            SimpleList<Edge<String>> edges = graph.getNeighbors(vertex);
            System.out.println();
            for (int f = 0; f < edges.size(); f++) {
                
                System.out.println(vertex + " -> " + edges.get(f).destination + ": " + edges.get(f).weight);
            }

        }

        currentPhase = 0;
        
        
        
        

        // Casa :
        // Belgrano(1) Recoleta(8) Pinamar(250)
        return;


    }

    private void showDijkstra() {
        // <T> graphResultDijstra = dijkstra

        // nos va a dar la matriz resulta

        // aca agregamos un funcion que de la matriz devolvemos el resultado
        // aca va la logica
    }

    // Pide al usuario un nodo y valida que exista en el grafo antes de continuar
    private String askNode(String prompt) {
        SimpleList<String> vertices = graph.vertices();
        String input = null;
        boolean valid = false;

        while (!valid) {
            System.out.println(prompt);

            // Mostramos los nodos disponibles para que el usuario sepa qué ingresar
            System.out.print("Nodos disponibles: ");
            for (int i = 0; i < vertices.size(); i++) {
                System.out.print(vertices.get(i) + (i < vertices.size() - 1 ? ", " : "\n"));
            }

            input = scanner.nextLine().trim();

            // Verificamos que el nodo ingresado exista en el grafo
            boolean found = false;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).equalsIgnoreCase(input)) {
                    input = vertices.get(i); // usamos el nombre exacto del grafo
                    found = true;
                    break;
                }
            }

            if (found) {
                valid = true;
            } else {
                System.out.println("\nNodo '" + input + "' no encontrado. Intentá de nuevo.");
            }
        }

        return input;
    }

    // Pide origen y destino, calcula el camino más corto y lo muestra
    private void shortestPathLogic() {

        String origin = askNode("\nIngresá el nodo de origen:");
        String destination = askNode("\nIngresá el nodo de destino:");

        DijstraShorterPath<String> result = new DijstraShorterPath<>(graph, origin, destination);

        if (result.getWeight() == -1) {
            System.out.println("\nNo existe camino entre '" + origin + "' y '" + destination + "'.");
        } else {
            ArrayList<String> path = result.getShorterPath();

            System.out.print("\nCamino más corto: ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i) + (i < path.size() - 1 ? " → " : ""));
            }
            System.out.println("\nCosto total: " + result.getWeight());
        }

        boolean backToMenu = returnMenu();
        if (backToMenu) {
            currentPhase = 0;
        }
    }
}
