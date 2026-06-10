package graphModule;

import java.util.Scanner;

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
            

        }

    }

    private void menuLogic() {

        if (firstTime) {
            firstTime = false;
            System.out.println("\nBienvenido al ejercicio de Dijkstra");
        }

        System.out.println("\nElegir una opción:"
                + "\nm: Mostrar todos los puntajes "
                + "\nmm: Menú principal");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {

            case "m":
                currentPhase = 1;
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
        graph.addVertex("Moserrat");
        graph.addVertex("Recoleta");
        graph.addVertex("Pinamar");

        graph.addEdge("Casa", "Belgrano", 1);
        graph.addEdge("Casa", "Recoleta", 8);
        graph.addEdge("Casa", "Pinamar", 250);
        graph.addEdge("Belgrano", "Moserrat", 4);
        graph.addEdge("Belgrano", "Recoleta", 6);
        graph.addEdge("Recoleta", "Monserrat", 2);
        graph.addEdge("Moserrat", "Pinamar", 170);

        return;


    }
    private void printGraph() {

        
        SimpleList<String> verticesGraph = graph.vertices();

        for (int i = 0; i < verticesGraph.size(); i++) {
            String vertex = verticesGraph.get(i);
            
            SimpleList<Edge<String>> edges = graph.getNeighbors(vertex);

            for (int f = 0; i < edges.size(); i++) {
                
                System.out.println(vertex + " -> " + edges.get(f).destination + ": " + edges.get(f).weight);
            }
            System.out.println();
            System.out.println();
        }
        
        
        
        

        // Casa :
        // Belgrano(1) Recoleta(8) Pinamar(250)
        return;


    }
}
