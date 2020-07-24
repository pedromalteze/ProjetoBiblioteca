package dao;

import factory.ConnectionFactory;
import modelo.Obras;
import java.sql.*;
import java.sql.PreparedStatement;/*


 /**
 *
 * @author pedro
 */

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ObrasDAO {

    private Connection connection;

    public ObrasDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void create(Obras obras) {
        String sql = "INSERT INTO obras(titulo,subtitulo,autor,editora,isbn) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, obras.getTitulo());
            stmt.setString(2, obras.getSubtitulo());
            stmt.setString(3, obras.getAutor());
            stmt.setString(4, obras.getEditora());
            stmt.setString(5, obras.getIsbn());

            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("o erro foi " + ex);
        }

    }

    public List<Obras> carregaObras() {

        ArrayList listaObras = new ArrayList<Object>();

        try {

            String sql = "SELECT * FROM fmaced35_biblioteca.obras;";

            PreparedStatement stmt;

            stmt = connection.prepareStatement(sql);

            if (!stmt.execute()) {
                //tratar o erro. Tabela vazia
            } else {
                ResultSet resultado = stmt.executeQuery(sql);

                listaObras.clear();

                while (resultado.next()) {

                    Obras obras = new Obras();

                    System.out.println("");

                    obras.setId(resultado.getInt("id"));
                    obras.setTitulo(resultado.getString("titulo"));
                    obras.setSubtitulo(resultado.getString("subtitulo"));
                    obras.setAutor(resultado.getString("autor"));
                    obras.setEditora(resultado.getString("editora"));
                    obras.setIsbn(resultado.getString("isbn"));

                    listaObras.add(obras);

                }
            }

            connection.close();

            return listaObras;

        } catch (SQLException ex) {
            System.out.println("o erro foi " + ex);
        }

        return null;
    }

    public void update(Obras obras) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("UPDATE obras SET titulo = ? ,subtitulo = ?,autor = ? ,editora = ? ,isbn = ? WHERE id = ?");
            stmt.setString(1, obras.getTitulo());
            stmt.setString(2, obras.getSubtitulo());
            stmt.setString(3, obras.getAutor());
            stmt.setString(4, obras.getEditora());
            stmt.setString(5, obras.getIsbn());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        }
    }

    public void delete(Obras obras) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM obras WHERE id = ?");
            stmt.setInt(1, obras.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);

        }
    }

    public List<Obras> carregaTipoDeObra() {

        ArrayList listaTipoObra = new ArrayList<Object>();

        try {

            String sql = "SELECT * FROM fmaced35_biblioteca.tipoDeObra where tipo = ?";

            PreparedStatement stmt;

            stmt = connection.prepareStatement(sql);

            if (!stmt.execute()) {
                //tratar o erro. Tabela vazia
            } else {
                ResultSet res = stmt.executeQuery(sql);

                listaTipoObra.clear();

                while (res.next()) {

                    Obras Tipoobras = new Obras();

                    Tipoobras.setId(res.getInt("id"));
                    Tipoobras.setTipodeobra(res.getString("tipo"));

                    listaTipoObra.add(Tipoobras);

                }
            }

            connection.close();

            return listaTipoObra;

        } catch (SQLException ex) {
            System.out.println("o erro foi " + ex);
        }

        return null;
    }

    public void inserir(Obras obras) {

        String sql = "INSERT INTO obras(titulo,subtitulo,autor,editora,isbn) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, obras.getTitulo());
            stmt.setString(2, obras.getSubtitulo());
            stmt.setString(3, obras.getAutor());
            stmt.setString(4, obras.getEditora());
            stmt.setString(5, obras.getIsbn());

            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("o erro foi " + ex);        }

    }

}
