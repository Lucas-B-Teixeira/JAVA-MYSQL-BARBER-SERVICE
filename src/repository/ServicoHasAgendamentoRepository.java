package repository;

import db.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServicoHasAgendamentoRepository {

    public static void  addServicoHasAgendamento(int idService, int idAgendamento){
        Connection connection = DatabaseConnection.getConnection();

        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO servicos_has_agendamentos (servico_id, agendamento_id) VALUES (?,?);"
            );
            stmt.setInt(1, idService);
            stmt.setInt(2, idAgendamento);
            stmt.executeUpdate();


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void delServicoHasAgendamento(int idAgend){
        Connection connection = DatabaseConnection.getConnection();

        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM servicos_has_agendamentos WHERE agendamento_id = ?;"
            );
            stmt.setInt(1, idAgend);
            stmt.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
