package repository;

import db.DatabaseConnection;
import entity.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository {

    public static Usuario getUser(String email){

        Connection connection = DatabaseConnection.getConnection();

        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM usuarios WHERE email = ?"
            );
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();

            while(result.next()){
                int id = result.getInt("id");
                String nome = result.getString("nome");
                String sobrenome = result.getString("sobrenome");
                String e_mail = result.getString("email");
                String nascimento = result.getString("nascimento");
                String telefone = result.getString("telefone");

                return new Usuario(id,nome, sobrenome, e_mail, nascimento, telefone);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void addUser(Usuario user){
        Connection connection = DatabaseConnection.getConnection();

        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO usuarios (nome, sobrenome, email, nascimento, telefone) VALUE (?,?,?,?,?)"
            );
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getSobrenome());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getNascimento());
            stmt.setString(5, user.getTelefone());

            stmt.executeUpdate();


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
