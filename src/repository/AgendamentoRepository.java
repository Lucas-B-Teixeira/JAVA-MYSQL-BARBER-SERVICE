package repository;

import db.DatabaseConnection;
import entity.Agendamento;
import entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoRepository {

    public static List<Agendamento> getAgend(int id_user){
        Connection connection = DatabaseConnection.getConnection();

        List<Agendamento> agendamentos = new ArrayList<Agendamento>();

        try{

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT a.id, a.agendamento, a.data_agend, a.hora_inicio, a.hora_termino, a.id_usuario," +
                            " s.tipo, s.modelo, s.preco, s.duracao " +
                            "FROM agendamentos a " +
                            "INNER JOIN servicos_has_agendamentos sa ON a.id = sa.agendamento_id " +
                            "INNER JOIN servicos s ON sa.servico_id = s.id " +
                            "WHERE a.id_usuario = ?;"
            );
            stmt.setInt(1, id_user);
            ResultSet result = stmt.executeQuery();

            while(result.next()){
                int id = result.getInt("id");
                String agendamento = result.getString("agendamento");
                String data_agend = result.getString("data_agend");
                String hora_inicio = result.getString("hora_inicio");
                String hora_termino = result.getString("hora_termino");
                int id_usuario = result.getInt("id_usuario");
                String tipo = result.getString("tipo");
                String modelo = result.getString("modelo");
                double preco = result.getDouble("preco");
                String duracao = result.getString("duracao");

                agendamentos.add(new Agendamento(id, agendamento,data_agend,hora_inicio,hora_termino,id_usuario,
                        modelo, tipo, preco, duracao));
            }

            return agendamentos;

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void addAgend(Agendamento agendamento ){

        Connection connection = DatabaseConnection.getConnection();

        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO agendamentos (agendamento, data_agend, hora_inicio, hora_termino, id_usuario) " +
                            "VALUE (?,?,?,?,?);"
            );
            stmt.setString(1, agendamento.getAgedamento());
            stmt.setString(2, agendamento.getData_agend());
            stmt.setString(3, agendamento.gethora_inicio());
            stmt.setString(4, agendamento.getHora_termino());
            stmt.setInt(5, agendamento.getId_usuario());

            stmt.executeUpdate();


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }


    }

    public static List<Agendamento> getAll(){

        Connection connection = DatabaseConnection.getConnection();

        List<Agendamento> agendamentos = new ArrayList<Agendamento>();

        try{

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT a.id, a.agendamento, a.data_agend, a.hora_inicio, a.hora_termino, a.id_usuario," +
                            " s.tipo, s.modelo, s.preco, s.duracao " +
                            "FROM agendamentos a " +
                            "INNER JOIN servicos_has_agendamentos sa ON a.id = sa.agendamento_id " +
                            "INNER JOIN servicos s ON sa.servico_id = s.id"
            );
            ResultSet result = stmt.executeQuery();

            while(result.next()){
                int id  = result.getInt("id");
                String agendamento = result.getString("agendamento");
                String data_agend = result.getString("data_agend");
                String hora_inicio = result.getString("hora_inicio");
                String hora_termino = result.getString("hora_termino");
                int id_usuario = result.getInt("id_usuario");
                String tipo = result.getString("tipo");
                String modelo = result.getString("modelo");
                double preco = result.getDouble("preco");
                String duracao = result.getString("duracao");

                agendamentos.add(new Agendamento(id, agendamento,data_agend,hora_inicio,hora_termino,id_usuario,
                        modelo, tipo, preco, duracao));
            }

            return agendamentos;

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return agendamentos;
    }

    public static int getIDAgend(Agendamento a, Usuario u){

        Connection connection = DatabaseConnection.getConnection();

        int agendId = 0;

        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM agendamentos WHERE agendamento = ?"+
                            "AND data_agend = ?" +
                            "AND hora_inicio = ?" +
                            "AND hora_termino = ?"+
                            "AND id_usuario = ?;"
            );
            stmt.setString(1, a.getAgedamento());
            stmt.setString(2, a.getData_agend());
            stmt.setString(3, a.gethora_inicio());
            stmt.setString(4, a.getHora_termino());
            stmt.setInt(5, u.getId());
            ResultSet result = stmt.executeQuery();

            while(result.next()){
                agendId = result.getInt("id");
            }

            return agendId;


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return agendId;
    }

    public static void delAgend(int idAgend){

        Connection connection = DatabaseConnection.getConnection();

        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM agendamentos WHERE id = ?;"
            );
            stmt.setInt(1, idAgend);
            stmt.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
