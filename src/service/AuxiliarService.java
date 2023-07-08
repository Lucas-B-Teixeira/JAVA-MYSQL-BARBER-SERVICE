package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AuxiliarService {

    public static Scanner scanner = new Scanner(System.in);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String printError = "\u001B[31m";
    public static final String printNext = "\u001B[32m";
    public static final String printQuestion = "\u001B[34m";
    public static final String printOption = "\u001B[33m";
    public static final String printInitial = "\u001B[36m";

    public static void home(){
            System.out.println(printOption + "\n[1] - Entrar como Administrador.");
            System.out.println("[2] - Entrar como Cliente.");
            System.out.println("[3] - Sair." + ANSI_RESET);
            System.out.println(printQuestion + "Digite o número da opção que deseja: " + ANSI_RESET);

            boolean valid = false;

            while(!valid){
                try{
                    int a = scanner.nextInt();

                    switch(a){
                        case 1:
                            valid = true;
                            AdministradorService.administrador();
                            break;
                        case 2:
                            valid = true;
                            ClienteService.cliente();
                            break;
                        case 3:
                            valid = true;
                            System.out.println(printInitial + "Obrigado por entrar em nossa barbearia, volte sempre!" + ANSI_RESET);
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

    public static boolean isValid(int optionSelectedInt, String optionSelectedString, int optionInitial,
                                  int optionFinal, String errorMensage) {

        boolean isValid;

        try {
            if (optionSelectedString != null) {
                isValid = true;
                return isValid;
            }else{
                if (optionSelectedInt >= optionInitial && optionSelectedInt <= optionFinal) {
                    isValid = true;
                    return isValid;
                } else {
                    throw new InputMismatchException();
                }
            }
        } catch (InputMismatchException e) {
            isValid = false;
            System.out.println(printError + errorMensage + ANSI_RESET);
        }


        return isValid;
    }

    public static String formatTime(String date, boolean HH_mm_ss, boolean HH_mm, boolean dd_MM_yyyy,
                                    boolean yyyy_MM_dd, boolean yyyy_MM_dd__HH_mm_ss, boolean EEE_MMM_dd_HH_mm_ss_zzz_yyyy) throws ParseException {

        String result = null;

        String[] formatPatterns = {
                "EEE MMM dd HH:mm:ss zzz yyyy",
                "E MMM dd HH:mm:ss Z yyyy",
                "HH:mm:ss",
                "HH:mm",
                "dd/MM/yyyy",
                "yyyy-MM-dd",
                "yyyy-MM-dd HH:mm:ss",
                "E MMM dd HH:mm:ss z yyyy"

        };

        Date dateFormted = null;

        for (String f : formatPatterns){
            SimpleDateFormat format = new SimpleDateFormat(f, Locale.ENGLISH);
            try{
                dateFormted = format.parse(date);
                break;
            }catch(ParseException e){}
        }


        if(HH_mm_ss){
            SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");
            result = dataFormat.format(dateFormted);
            return result;
        }
        if(HH_mm){
            SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm");
            result = dataFormat.format(dateFormted);
            return result;
        }
        if(dd_MM_yyyy){
            SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
            result = dataFormat.format(dateFormted);
            return result;
        }
        if(yyyy_MM_dd){
            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
            result = dataFormat.format(dateFormted);
            return result;
        }
        if(yyyy_MM_dd__HH_mm_ss){
            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = dataFormat.format(dateFormted);
            return result;
        }
        if(EEE_MMM_dd_HH_mm_ss_zzz_yyyy){
            SimpleDateFormat dataFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            result = dataFormat.format(dateFormted);
            return result;
        }

        return result;
    }
}
