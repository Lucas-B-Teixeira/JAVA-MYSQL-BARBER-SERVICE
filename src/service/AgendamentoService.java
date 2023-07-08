package service;

import entity.Agendamento;
import entity.Horarios;
import entity.Servico;
import entity.Usuario;
import repository.AgendamentoRepository;
import repository.ServicoHasAgendamentoRepository;
import repository.ServicoRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class AgendamentoService {

    public static Scanner scanner = new Scanner(System.in);

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String printError = "\u001B[31m";
    public static final String printNext = "\u001B[32m";
    public static final String printQuestion = "\u001B[34m";
    public static final String printOption = "\u001B[33m";
    public static final String printInitial = "\u001B[36m";

    public static List<Agendamento> getAgendamento(Usuario user) {

        List<Agendamento> agendamento = AgendamentoRepository.getAgend(user.getId());
        AtomicReference<Agendamento> agendAnterior = new AtomicReference<>(new Agendamento(null, null, null,
                null, 0, null, null, 0, null));

        if(agendamento.size() > 0){
            System.out.println(printInitial + "\nSegue abaixo todos os seus agendamentos(formato da data ano-mes-dia):");
            agendamento.forEach(agend -> {
                if(agend.getData_agend().equals(agendAnterior.get().getData_agend())
                        && agend.getAgedamento().equals(agendAnterior.get().getAgedamento())
                        && agend.gethora_inicio().equals(agendAnterior.get().gethora_inicio())
                        && agend.getHora_termino().equals(agendAnterior.get().getHora_termino())){
                    System.out.println( printOption + "Tipo: " + agend.getTipo() +
                            ", Modelo: " + agend.getModelo() +
                            ", Preço: " + agend.getPreco() +
                            ", Duração: " + agend.getDuracao() + ANSI_RESET );
                }else{
                    System.out.println(printNext + "\nData do Agendamento: " + agend.getAgedamento() +
                            ", Dia: " + agend.getData_agend() +
                            ", Horario: " + agend.gethora_inicio() +
                            ", Termino: " + agend.getHora_termino() + ANSI_RESET +
                            printOption + "\nTipo: " + agend.getTipo() +
                            ", Modelo: " + agend.getModelo() +
                            ", Preço: " + agend.getPreco() +
                            ", Duração: " + agend.getDuracao() + ANSI_RESET);
                }

                agendAnterior.set(agend);
            });
            ClienteService.menu(user, "#---------# Selecione outra opção ! #---------#");
        }else{
            System.out.println("Você não possuí um agendamento, gostaria de marcar um horario?");
            ClienteService.menu(user, "Selecione outra opção !");
        }

        return agendamento;
    }

    public static Agendamento getAgend(String dia, String initialTime){

        List<Agendamento> listAgendamento = AgendamentoRepository.getAll();

        AtomicReference<Agendamento> agendamento = new AtomicReference<>();

        try {

            String dataFormat = AuxiliarService.formatTime(dia, false,
                    false, false, true, false, false);

            String horaFormat = AuxiliarService.formatTime(initialTime, true,
                    false, false, false, false, false);

            if(listAgendamento.size() > 0){
                listAgendamento.forEach(a -> {
                    if(Objects.equals(dataFormat, a.getData_agend()) && Objects.equals(horaFormat, a.gethora_inicio())){
                        agendamento.set(a);
                    }
                });
            }
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return agendamento.get();

    }

    public static void newAgendameno(Usuario user) throws ParseException {

        List<Horarios> listTime = getHorariosDisp();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");

        System.out.println(printInitial + "\nPrecisamos de algumas informações para realizar seu agendamento!" + ANSI_RESET);

        listTime.forEach(h -> {
            if (h.getAgendamento() == null) {
                System.out.println(printQuestion + "[" + h.getOpcao() + "]" + " - " + "Dia: " + ANSI_RESET  +
                        printNext + h.getDia() + ANSI_RESET + printQuestion + " | Horario: " + ANSI_RESET +
                        printNext + h.getHora() + ANSI_RESET);
            }
        });

        System.out.println(printInitial + "Digite o número de uma data e horario para seu agendamento:" + ANSI_RESET);
        boolean isValid = false;

        while(!isValid){
            int escolha = scanner.nextInt();
            scanner.nextLine();
            isValid = AuxiliarService.isValid(escolha, null, 1, listTime.size(),
                    "Digite uma opção válida em número (ex. de 1 à " + listTime.size() +" )!");

            if(isValid){
                listTime.forEach(h -> {
                    if(h.getOpcao() == escolha){
                        try{
                            Date hour = horaFormat.parse(h.getHora());

                            String dayAgend = AuxiliarService.formatTime(h.getDia(), false,
                                    false, false, true, false, false);

                            String atualTime = AuxiliarService.formatTime(calendar.getTime().toString(), false,
                                    false, false, false, true, false);

                            String initialTimeAgend = AuxiliarService.formatTime(h.getHora(), false,
                                    true, false, false, false, false);

                            calendar.setTime(hour);
                            calendar.add(Calendar.HOUR_OF_DAY, 1);
                            String finishTimeAgend = AuxiliarService.formatTime(calendar.getTime().toString(), true,
                                    false, false, false, false, false);

                            List<Servico> listServicos = ServicoRepository.getAll();

                            for(int i = 0; i < listServicos.size(); i++){
                                System.out.println( printQuestion + "[" + i + "] - " + ANSI_RESET +
                                        printOption + "Tipo: " + listServicos.get(i).getTipo() +
                                        ", Modelo: " + listServicos.get(i).getModelo() +
                                        ", Preço: " + listServicos.get(i).getPreco() +
                                        ", Duração: " + listServicos.get(i).getDuracao() + ANSI_RESET );
                            }


                            System.out.println(printInitial + "Digite os números dos serviços que voce deseja(ex. 1 10 17):" + ANSI_RESET);
                            boolean validServic = false;

                            while(!validServic) {
                                String escolhaServic = scanner.nextLine();
                                validServic = AuxiliarService.isValid(0, escolhaServic, 0, 0,
                                        "Digite somente números (ex. 1 10 17)!");

                                if(validServic){
                                    String[] escolhaServiceArray = escolhaServic.split(" ");

                                    System.out.println(printInitial + "Os dados do seu agendamento estão corretos:" + ANSI_RESET);
                                    System.out.println(printNext + "Data do Agendamento: " + atualTime +
                                            ", Dia: " + dayAgend +
                                            ", Horario: " + initialTimeAgend +
                                            ", Termino: " + finishTimeAgend + ANSI_RESET);


                                    for (int i = 0; i < escolhaServiceArray.length; i++) {
                                        if (!escolhaServiceArray[i].isEmpty()) {
                                            int num = Integer.parseInt(escolhaServiceArray[i]);
                                            System.out.println(printOption + "Tipo: " + listServicos.get(num).getTipo() +
                                                    ", Modelo: " + listServicos.get(num).getModelo() +
                                                    ", Preço: " + listServicos.get(num).getPreco() +
                                                    ", Duração: " + listServicos.get(num).getDuracao() + ANSI_RESET);
                                        }
                                    }


                                    boolean isValidAgend  = false;

                                    System.out.println(printNext + "[1] - Sim" + ANSI_RESET + printError + "\n[2] - Não" + ANSI_RESET);

                                    while(!isValidAgend){
                                        int confirmAgend = scanner.nextInt();
                                        isValidAgend = AuxiliarService.isValid(confirmAgend, null, 1,
                                                2, "Digite uma opção valida( ex. 1 ou 2)!");

                                        if (isValidAgend){
                                            if(confirmAgend == 1){
                                                for(String numero : escolhaServiceArray){
                                                    int i = Integer.parseInt(numero);
                                                    Agendamento agendamento = new Agendamento(atualTime, dayAgend, initialTimeAgend, finishTimeAgend,
                                                            user.getId(), listServicos.get(i).getModelo(), listServicos.get(i).getTipo(),
                                                            listServicos.get(i).getPreco(), listServicos.get(i).getDuracao());

                                                    AgendamentoRepository.addAgend(agendamento);

                                                    int agendId = AgendamentoRepository.getIDAgend(agendamento, user);
                                                    int servicoId = ServicoRepository.getIDServico(listServicos.get(i));

                                                    ServicoHasAgendamentoRepository.addServicoHasAgendamento(servicoId, agendId);
                                                }
                                                System.out.println(printNext + "Agendamento realizado com sucesso!" + ANSI_RESET);
                                                ClienteService.menu(user, "Selecione outra opção !");
                                            }else{
                                                System.out.println(printError + "O agendamento não foi realizado!" + ANSI_RESET);
                                                ClienteService.menu(user, "Selecione outra opção !");
                                            }
                                        }else{
                                            scanner.nextLine();
                                        }
                                    }
                                }
                            }
                        }catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }else{
                scanner.nextLine();
            }
        }
    }

    public static List<Horarios> getHorariosDisp() throws ParseException {

        List<Horarios> listTime = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();

        String initialTime = "08:00";
        String finishTime = "18:00";

        int daysOfWeek = 2;
        int viewWeek = 1;

        int option = 1;

        calendar.set(Calendar.MINUTE, 0);


        for (int i = 0; i < (daysOfWeek * viewWeek); i++) {
            for (int j = 0; j < 24; j++) {
                String atualTimeFormat = AuxiliarService.formatTime(calendar.getTime().toString(), false,
                        true, false, false, false, false);

                String atualDateFormat = AuxiliarService.formatTime(calendar.getTime().toString(), false,
                        false, true, false, false, false);

                if (atualTimeFormat.compareTo(initialTime) >= 0 && atualTimeFormat.compareTo(finishTime) <= 0) {
                    if(getAgend(atualDateFormat, atualTimeFormat) == null){
                        listTime.add(new Horarios(option, atualDateFormat, atualTimeFormat, null));
                        option++;
                    }
                }

                calendar.add(Calendar.HOUR_OF_DAY, 1);
            }
        }

        return listTime;
    }

    public static void deleteAgend(Usuario user){

        List<Agendamento> agendamentos = AgendamentoRepository.getAgend(user.getId());

        List<Agendamento> agendamentosOption = new ArrayList<>();

        AtomicReference<Agendamento> agendAnterior = new AtomicReference<>(new Agendamento(null, null, null,
                null, 0, null, null, 0, null));

        if(agendamentos.size() > 0){
            for(int i = 0; i < agendamentos.size(); i++) {
                if (!Objects.equals(agendamentos.get(i).getData_agend(), agendAnterior.get().getData_agend()) &&
                        !Objects.equals(agendamentos.get(i).getAgedamento(), agendAnterior.get().getAgedamento()) &&
                        !Objects.equals(agendamentos.get(i).gethora_inicio(), agendAnterior.get().gethora_inicio()) &&
                        !Objects.equals(agendamentos.get(i).getHora_termino(), agendAnterior.get().getHora_termino())) {

                    agendamentosOption.add(agendamentos.get(i));
                }

                agendAnterior.set(agendamentos.get(i));
            }
        }


        System.out.println(printInitial + "\nDigite o número do agendamento que vc deseja excluir(ex. 1):");

        if(agendamentosOption.size() > 0){
            for(int i = 0; i < agendamentosOption.size(); i++){
                System.out.println(printQuestion + "[" + (i+1) + "] - " + ANSI_RESET +
                        printNext + "Data do Agendamento: " + agendamentosOption.get(i).getAgedamento() +
                        ", Dia: " + agendamentosOption.get(i).getData_agend() +
                        ", Horario: " + agendamentosOption.get(i).gethora_inicio() +
                        ", Termino: " + agendamentosOption.get(i).getHora_termino() + ANSI_RESET );
                }

            boolean optionIsValid = false;

            while(!optionIsValid){
                int option = scanner.nextInt();
                optionIsValid = AuxiliarService.isValid(option, null, 1,
                        agendamentosOption.size(), "Opcão invalida, digite novamente uma opção valida!");
                scanner.nextLine();

                if(optionIsValid){
                    System.out.println(printInitial + "\nDeseja Realmente excluir o agendamento:");
                    System.out.println(printOption + "Data do Agendamento: " + agendamentosOption.get(option - 1).getAgedamento() +
                            ", Dia: " + agendamentosOption.get(option - 1).getData_agend() +
                            ", Horario: " + agendamentosOption.get(option - 1).gethora_inicio() +
                            ", Termino: " + agendamentosOption.get(option - 1).getHora_termino() + ANSI_RESET );
                    System.out.println(printNext + "[1] - Sim" + ANSI_RESET +
                                     printError + "\n[2] - Não" + ANSI_RESET);

                    boolean optionSecondIsValid = false;

                    while(!optionSecondIsValid){
                        int optionSecond = scanner.nextInt();
                        optionSecondIsValid = AuxiliarService.isValid(optionSecond, null, 1,
                                2, "Opcão invalida, digite novamente uma opção valida!");
                        scanner.nextLine();
                        if(optionSecondIsValid){
                            if(optionSecond == 1){
                                for(int i =0; i < agendamentos.size(); i++){
                                    if(Objects.equals(agendamentos.get(i).getData_agend(), agendamentosOption.get(option - 1).getData_agend()) &&
                                            Objects.equals(agendamentos.get(i).getAgedamento(), agendamentosOption.get(option - 1).getAgedamento())){
                                        ServicoHasAgendamentoRepository.delServicoHasAgendamento(agendamentos.get(i).getId());
                                        AgendamentoRepository.delAgend(agendamentos.get(i).getId());
                                    }
                                }
                                System.out.println(printNext + "Seu agendamento foi excluido com sucesso!" + ANSI_RESET);
                                ClienteService.menu(user, "Selecione outra opção !");
                            }else{
                                System.out.println(printQuestion + "Seu agendamento NÂO foi cancelado!" + ANSI_RESET);
                                ClienteService.menu(user, "Selecione outra opção !");
                            }

                        }else{
                            optionSecondIsValid= false;
                        }
                    }
                }
                else{
                    optionIsValid = false;
                }

            }





        }else{
            System.out.println(printError + "Você não possui agendamento para cacelar!" + ANSI_RESET);
            ClienteService.menu(user, "Selecione outra opção !");
        }

    }

}
