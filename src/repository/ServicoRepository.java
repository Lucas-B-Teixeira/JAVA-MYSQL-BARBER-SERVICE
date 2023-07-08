package repository;

import db.DatabaseConnection;
import entity.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoRepository {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String printError = "\u001B[31m";
    public static final String printNext = "\u001B[32m";
    public static final String printQuestion = "\u001B[34m";
    public static final String printOption = "\u001B[33m";
    public static final String printInitial = "\u001B[36m";

    public static List<Servico> getAll(){

        Connection connection = DatabaseConnection.getConnection();

        List<Servico> servicos = new ArrayList<Servico>();

        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM servicos;"
            );

            ResultSet result = stmt.executeQuery();

            while(result.next()){
                String tipo = result.getString("tipo");
                String modelo = result.getString("modelo");
                double preco = result.getDouble("preco");
                String duracao = result.getString("duracao");

                servicos.add(new Servico(modelo, tipo, preco, duracao));
            }

            return servicos;

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return servicos;
    }

    public static int getIDServico(Servico s){

        Connection connection = DatabaseConnection.getConnection();

        int servicoId = 0;

        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM servicos WHERE tipo = ?" +
                            "AND modelo = ?" +
                            "AND duracao = ?" +
                            "AND preco =  ?;"
            );
            stmt.setString(1, s.getTipo());
            stmt.setString(2, s.getModelo());
            stmt.setString(3, s.getDuracao());
            stmt.setDouble(4, s.getPreco());

            ResultSet result = stmt.executeQuery();

            while(result.next()){
                servicoId = result.getInt("id");
            }

            return servicoId;


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return servicoId;
    }


}
