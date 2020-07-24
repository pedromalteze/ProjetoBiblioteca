package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author pedro
 */
public class UsuarioDAO {

    public boolean checkLogin(String usuario, String senha) {

        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet res = null;
        boolean check = false;
        
        try {
            System.out.println(usuario);
            System.out.println(senha);
            stmt = conn.prepareStatement("select * from fmaced35_biblioteca.usuarios where usuario = ? and senha = ?");
            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            res = stmt.executeQuery();

            if (res.next()) {
                check = true;
            }

        } catch (SQLException err) {
            System.out.println(err);
        }
        System.out.println(check);
        return check;
    }

}
