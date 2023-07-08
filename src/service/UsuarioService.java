package service;

import entity.Usuario;
import repository.UsuarioRepository;

import java.util.Scanner;

public class UsuarioService {

    public static Scanner scanner = new Scanner(System.in);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String printError = "\u001B[31m";
    public static final String printNext = "\u001B[32m";
    public static final String printQuestion = "\u001B[34m";
    public static final String printOption = "\u001B[33m";
    public static final String printInitial = "\u001B[36m";

    public static void addUsuario(){
        String nome;
        String sobrenome;
        String email;
        String nascimento = null;
        String telefone;

        int diaNasc = 0;
        int mesNasc = 0;
        int anoNasc = 0;

        System.out.println(printQuestion + "Digite seu nome: " + ANSI_RESET);
        nome = scanner.next();

        System.out.println(printQuestion + "Digite seu sobrenome: " + ANSI_RESET);
        sobrenome = scanner.next();

        System.out.println(printQuestion + "Digite seu email: " + ANSI_RESET);
        email = scanner.next();

        System.out.println(printQuestion + "Digite o DIA que você nasceu: " + ANSI_RESET);
        boolean diaIsValid = false;

        while(!diaIsValid){
            diaNasc = scanner.nextInt();
            diaIsValid = AuxiliarService.isValid(diaNasc, null, 1, 31, "Digite um dia valido(ex. 1 à 31)!");

            if(!diaIsValid){
               scanner.nextLine();
            }

        }

        System.out.println(printQuestion + "Digite o MES que você nasceu: " + ANSI_RESET);
        boolean mesIsValid = false;

        while(!mesIsValid){
            mesNasc = scanner.nextInt();
            mesIsValid = AuxiliarService.isValid(mesNasc, null, 1, 12, "Digite um mes valido(ex. 1 à 12)!");

            if(!mesIsValid){
                scanner.nextLine();
            }

        }

        System.out.println(printQuestion + "Digite o ANO que você nasceu: " + ANSI_RESET);
        boolean anoIsValid = false;

        while(!anoIsValid){
            anoNasc = scanner.nextInt();
            anoIsValid = AuxiliarService.isValid(anoNasc, null, 1923, 2023, "Digite um dia valido(ex. 1923 à 2023)!");

            if(anoIsValid){
                nascimento = anoNasc+"-"+mesNasc+"-"+diaNasc;
                scanner.nextLine();
            }else{
                scanner.nextLine();
            }

        }


        System.out.println(printQuestion + "Digite seu telefone:  " + ANSI_RESET);
        telefone = scanner.nextLine();

        System.out.println(printOption + "\nConfira se seus dados estão corretos: " + ANSI_RESET);
        System.out.println(printNext + "Nome: " + ANSI_RESET + printOption + nome + ANSI_RESET +
                printNext + " Sobrenome: " + ANSI_RESET + printOption + sobrenome + ANSI_RESET +
                printNext + "\nemail: " + ANSI_RESET + printOption + email + ANSI_RESET +
                printNext + " Data de Nascimento: " + ANSI_RESET + printOption + nascimento + ANSI_RESET +
                printNext + "\nTelefone: " + ANSI_RESET + printOption + telefone + ANSI_RESET);


        System.out.println(printNext + "[1] - Sim" + ANSI_RESET +
                printError + "\n[2] - Não" + ANSI_RESET);
        boolean infoIsValid = false;

        while(!infoIsValid){
            int infoOption = scanner.nextInt();
            infoIsValid = AuxiliarService.isValid(infoOption, null, 1, 2, "Digite uma opção valida(ex.1)");

            if(infoIsValid){
                if (infoOption == 1){
                    UsuarioRepository.addUser(new Usuario(nome, sobrenome, email, nascimento, telefone));
                    System.out.println(printInitial + "Seu cadastro foi realizado com sucesso!" + ANSI_RESET);
                    AuxiliarService.home();
                }else{
                    System.out.println(printError + "Seu cadastro NÃO foi efetivado!" + ANSI_RESET);
                    AuxiliarService.home();
                }
            }else{
                scanner.nextLine();
            }

        }

    }

}
