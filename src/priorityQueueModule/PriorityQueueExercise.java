package priorityQueueModule;

import application.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriorityQueueExercise extends Exercise {

    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleArrayPriorityQueue<Reporte> queue; //El tipo es reporte porque queremos una priorityqueue de reportes.

    //Constructor
    public PriorityQueueExercise(Scanner scnr) {
        super(scnr);
        queue = new SimpleArrayPriorityQueue<Reporte>();
    }

    //Copiamos la estructura del reporte
    @Override
    protected void exerciseLogic() {

        //El switch lo copypasteamos de ListExercise porqued va a ser igual
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                redactarLogic();
                break;
            case 2:
                visualizarLogic();
                break;
            case 3:
                clearLogic();
                break;
        }
    }


    private void menuLogic() {

        if (firstTime) {
            firstTime = false;
            System.out.println("\nBienvenido al ejercicio de colas de prioridad");
        }

        System.out.println("\nElegir una opción:"
                + "\nr: Redactar un reporte "
                + "\nv: Visualizar un reporte "
                + "\nb: Borrar todos los reportes "
                + "\nmm: Menú principal");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {

            case "r":
                currentPhase = 1;
                break;
            case "v":
                currentPhase = 2;
                break;
            case "b":
                currentPhase = 3;
                break;
            case "mm":
                running = false;
                break;
            default:
                System.out.println("Opción inválida, intentar de nuevo");
                break;

        }

    }


    private void redactarLogic() //con el Scanner ponemos un dato y depsués lo agregamos a la lista
    {
        boolean bandera = true;

        

        while (bandera) {
            System.out.println("\nIngresar un título:");
            String titulo = scanner.nextLine();
            System.out.println("\nIngresar una descripcion:");
            String descripcion = scanner.nextLine();
            Reporte reporte = new Reporte(titulo,descripcion);
            int prioridad = selectPriority();
            queue.enqueue(reporte, prioridad);
            System.out.println("\n" + reporte + "\n" + "Prioridad:" + translatePriority(prioridad)+ " agregado correctamente"); //añade el dato que ingresa el usaurio


            //preguntar si repito operacion

            boolean bandera_2 = true;
            while (bandera_2) {
                System.out.println("\n¿Repetir operación? (y / n)");
                String rep = scanner.nextLine().toLowerCase();
                if (rep.equals("n")) {
                    bandera = false;
                    bandera_2 = false;
                    System.out.println("\nVolviendo al menú");
                    currentPhase = 0;  //Esto es para que en la próxima pasada del while me lleve al menu
                } else {
                    if (rep.equals("y")) {
                        System.out.println("\nRepitiendo operación...");
                        bandera_2 = false;
                    } else {
                        System.out.println("\nRespuesta inválida");
                    }

                }
            }
        }

    }

    private void visualizarLogic() {

         
        if (queue == null || queue.isEmpty()) {
            System.out.println("\nNo hay reportes en la cola.");
            currentPhase = 0;
            return;
        }

        List<Reporte> omitidos = new ArrayList<>();
        List<Integer> omitidosPrioridad = new ArrayList<>();

        while (!queue.isEmpty()) {
            int prioridad = queue.getHighestPriority();
            Reporte reporte = queue.peek();

            System.out.println("\n========== REPORTE ==========");
            System.out.println(reporte);
            System.out.println("Urgencia: " + translatePriority(prioridad));
            System.out.println("=============================");
            System.out.println("\nOpciones:"
                    + "\nr: Marcar como resuelto (eliminar)"
                    + "\ns: Dejar en la cola (ver el siguiente)"
                    + "\nq: Volver al menú");

            boolean inputValido = false;
            while (!inputValido) {
                String input = scanner.nextLine().toLowerCase();
                switch (input) {
                    case "r":
                        queue.dequeue();
                        System.out.println("\nReporte marcado como resuelto y eliminado.");
                        inputValido = true;
                        break;
                    case "s":
                        omitidos.add(queue.dequeue());
                        omitidosPrioridad.add(prioridad);
                        System.out.println("\nReporte dejado en la cola.");
                        inputValido = true;
                        break;
                    case "q":
                        for (int i = 0; i < omitidos.size(); i++) {
                            queue.enqueue(omitidos.get(i), omitidosPrioridad.get(i));
                        }
                        System.out.println("\nVolviendo al menú.");
                        currentPhase = 0;
                        return;
                    default:
                        System.out.println("\nOpción inválida, intentar de nuevo.");
                        break;
                }
            }
        }

        for (int i = 0; i < omitidos.size(); i++) {
            queue.enqueue(omitidos.get(i), omitidosPrioridad.get(i));
        }

        System.out.println("\nNo hay más reportes para revisar.");
        currentPhase = 0;
    }

    private int selectPriority(){
        @SuppressWarnings("ReassignedVariable") int priority = 0 ;
        while (priority == 0) {

        System.out.println("\nCual es nivel de Urgencia:"
                + "\nc: nivel Crítico"
                + "\na: nivel Alto"
                + "\nm: nivel Medio"
                + "\nb: nivel Bajo");

        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput) {

            case "c":
                priority = 1;
                break;
            case "a":
                priority = 2;
                break;
            case "m":
                priority = 3;
                break;
            case "b":
                priority = 4;
                break;
            default:
                System.out.println("Opción inválida, intentar de nuevo");
                break;

        }
        }
        return priority;
    }

    private String translatePriority(int priority) {
            @SuppressWarnings("ReassignedVariable") String priorityTransalted = "";

            switch (priority) {

                case 1:
                    priorityTransalted = "Crítico";
                    break;
                case 2:
                    priorityTransalted = "Alto";
                    break;
                case 3:
                    priorityTransalted = "Medio";
                    break;
                case 4:
                    priorityTransalted = "Bajo";
                    break;
                default:
                    break;

            }

        return priorityTransalted;
    }


    public void clearLogic() {
        if(queue == null || queue.isEmpty() ) {
            System.out.println("\nLa cola está vacía, volviendo al menú principal");

        } else {
            queue.clear();
            System.out.println("\nLa cola fue vaciada, volviendo al menú principal");
        }
        currentPhase = 0;
        return;
    }

}
