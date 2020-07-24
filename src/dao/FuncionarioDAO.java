package dao;

import factory.ConnectionFactory;
import modelo.Funcionario;
import java.sql.*;
import java.sql.PreparedStatement;

public class FuncionarioDAO {

    private Connection connection;

   
    public FuncionarioDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO usuario(nome,senha,ramal,telefone) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSenha());
            stmt.setString(3, funcionario.getRamal());
            stmt.setString(4, funcionario.getTelefone());
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
            
    }

}
