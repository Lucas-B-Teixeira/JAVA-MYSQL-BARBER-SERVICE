package service;

import entity.Usuario;
import repository.UsuarioRepository;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClienteService {

    public static Scanner scanner = new Scanner(System.in);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String printError = "\u001B[31m";
    public static final String printNext = "\u001B[32m";
    public static final String printQuestion = "\u001B[34m";
    public static final String printOption = "\u001B[33m";
    public static final String printInitial = "\u001B[36m";

    public static void cliente(){
        System.out.println(printInitial + "\nVocê entrou como CLIENTE" + ANSI_RESET);
        System.out.println(printOption + "[1] - Já sou cliente");
        System.out.println("[2] - Ainda não sou cliente");
        System.out.println("[3] - Voltar" + ANSI_RESET);
        System.out.println(printQuestion + "Digite o número da opção que deseja: " + ANSI_RESET);

        boolean valid = false;

        while(!valid){
            try{
                int a = scanner.nextInt();
                switch(a){
                    case 1:
                        valid = true;
                        System.out.println(printQuestion + "Digite seu email: " + ANSI_RESET);
                        String email = scanner.next();
                        Usuario user = UsuarioRepository.getUser(email);
                        menu(user, "Olá " + user.getNome() + " " +  user.getSobrenome() +
                                ", temos as seguintes opções:");
                        break;

                    case 2:
                        valid = true;
                        System.out.println(printInitial + "Vamos criar seu cadastro, é bem rapido!" + ANSI_RESET);
                        UsuarioService.addUsuario();
                        break;

                    case 3:
                        valid = true;
                        AuxiliarService.home();
                        break;

                    default:
                        throw new InputMismatchException();
                }
            }catch(InputMismatchException e){
                valid = false;
                System.out.println(printError + "Digite uma opção válida em número (ex. 1)!" + ANSI_RESET);
                scanner.nextLine();
            }
        }
    }

    public static void menu(Usuario user, String messageInitial){

        System.out.println(printNext + "\n" + messageInitial + ANSI_RESET);

        System.out.println(printOption + "[1] - Realizar agendamento.");
        System.out.println("[2] - Consultar agendamento.");
        System.out.println("[3] - Cancelar agendamento.");
        System.out.println("[4] - Voltar." + ANSI_RESET);
        System.out.println(printQuestion + "Digite o número da opção que deseja: " + ANSI_RESET);

        int b = scanner.nextInt();

        boolean validB = false;

        while(!validB) {
            try {
                switch (b) {
                    case 1:
                        validB = true;
                        AgendamentoService.newAgendameno(user);
                        break;
                    case 2:
                        validB = true;
                        AgendamentoService.getAgendamento(user);
                        break;
                    case 3:
                        validB = true;
                        AgendamentoService.deleteAgend(user);
                        break;
                    case 4:
                        validB = true;
                        AuxiliarService.home();
                        break;
                    default:
                        throw new InputMismatchException();
                }
            }catch (InputMismatchException e) {
                validB = false;
                System.out.println(printError + "Digite uma opção válida em número (ex. 1)!" + ANSI_RESET);
                scanner.nextLine();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
