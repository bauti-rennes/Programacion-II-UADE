package dictionaryModule;

import application.Exercise;
import listModule.SimpleArrayList;
import priorityQueueModule.Reporte;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginSystem extends Exercise {

    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleArrayDictionary<String,String> users = new SimpleArrayDictionary<String,String>();
    private List<String> blocked_users = new ArrayList<>();

    public LoginSystem(Scanner scnr) {
        super(scnr);
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                registerLogic();
                break;
            case 2:
                loginLogic();
                break;
            case 3:
                logoutLogic();
                break;
        }
    }

    private void menuLogic() {
        limpiarConsola();

        if (firstTime) {
            firstTime = false;
            System.out.println("\nBienvenido al ejercicio de diccionarios");
        }

        System.out.println("\nElegir una opción:"
                + "\nr: Registrar un nuevo usuario "
                + "\nl: Iniciar sesión "
                + "\no: Cerrar sesión "
                + "\nb: Volver al menú principal");

        String userInput = scanner.nextLine().toLowerCase();
        switch (userInput) {
            case "r":
                currentPhase = 1;
                break;
            case "l":
                currentPhase = 2;
                break;
            case "o":
                currentPhase = 3;
                break;
            case "mm":
                running = false;
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    private void registerLogic(){

        // Limpiamos la consola antes de mostrar la operación
        limpiarConsola();

        //Ingreso de nombre + Validacion del nombre de usuario
        String usuario = selectUsername();

        //Ingreso de contraseña + Validacion de la contraseña
        String password = selectPassword();

        //Agrego el nuevo usuario al diccionario
        users.put(usuario,password);

    }

    private void loginLogic(){

        //Pedirle al usuario que ponga un nombre de usuario
        String username = loginUsername();

        //Pedirle al usuario que ponga una contraseña
        boolean successfullLogin = loginPassword();

        if (successfullLogin){
            System.out.println("Ingreso correcto. Bienvenido " + username);
        }
        else {
            System.out.println("Demasiados intentos fallidos. ");
        }



    }



    private boolean validateUsername(String username) { //Lo unico que hace es validar que el usuario no exista
        if (users.containsKey(username)) {
            System.out.println("El nombre de usuario ya existe. Intente con otro.");
            return false;
        }
        return true;
    }

    private String selectUsername(){ //Función que valida nombre de usuario, en un futuro podemos validar si contiene numeros, caracteres especiales, etc (agregar más validacioens dentro de la misma funcion)
        @SuppressWarnings("ReassignedVariable") String username = "" ;
        while (username == "") {

            System.out.println("\n¿Cuál es el nombre de usuario?");

            String userInput = scanner.nextLine().toLowerCase();

            username = userInput;

            if (username == ""){
                System.out.println("\nNombre de usuario inválido")
            }
            else if (!validateUsername(username)) {
                username = "";
            }
        }
        return username;
    }

    private String selectPassword(){ //Función que valida contraseña, en un futuro podemos validar si contiene numeros, caracteres especiales, etc (agregar más validacioens dentro de la misma funcion)
        @SuppressWarnings("ReassignedVariable") String password = "" ;
        while (password == "") {

            System.out.println("\nCrear la contraseña:");

            String userInput = scanner.nextLine().toLowerCase();

            password = userInput;

            if (password == ""){
                System.out.println("\nConstraseña inválida (no puede ser vacía)")
            }

        }
        return password;
    }

    private String loginUsername(){ //Función que valida nombre de usuario, en un futuro podemos validar si contiene numeros, caracteres especiales, etc (agregar más validacioens dentro de la misma funcion)
        @SuppressWarnings("ReassignedVariable") String username = "" ;
        while (username == "") {

            System.out.println("\n¿Cuál es el nombre de usuario?");

            String userInput = scanner.nextLine();

            username = userInput;

            if (username == ""){
                System.out.println("\nNombre de usuario inválido")
            }
            else if (users.containsKey(username) == false) {
                System.out.println("\nEl nombre de usuario no existe. Intente de nuevo.");
                username = ""; //Esto simplemente lo hacemos para que se quede en el bucle while
            } else if (blocked_users.contains(username)) {
                System.out.println("\nEl usuario está bloqueado por demasiados intentos fallidos. Intente con otro usuario o vuelva a intentarlo más tarde.");
                username = ""; //Esto simplemente lo hacemos para que se quede en el bucle while
                // TODO: Fijarse la manera de contemplar la forma de volver al menu
            }

        }
        return username;
    }

    private boolean loginPassword(String username){ //

        String password = "" ;
        String correct_password = users.get(username);
        int tries = 0;

        while (tries<3){
            System.out.println("\n¿Cuál es la contraseña?");

            String userInput = scanner.nextLine();

            password = userInput;

            if (password == correct_password){
                return true;
            }
            tries++;
            System.out.println("\nContraseña incorrecta \nNúmero de intentos: " + tries);
        }

        blocked_users.add(username);

        return false;
    }


}
