import db.DatabaseConnection;
import service.AuxiliarService;


public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String printError = "\u001B[31m";
    public static final String printQuestion = "\u001B[34m";
    public static final String printOption = "\u001B[33m";
    public static final String printInitial = "\u001B[36m";

    public static void main(String[] args) {

        DatabaseConnection.getConnection();

        System.out.println(printInitial + " # -------- Seja bem vindo à BARBEARIA FORD ------- #" + ANSI_RESET);
        AuxiliarService.home();

    }


}

