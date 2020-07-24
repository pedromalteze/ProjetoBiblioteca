// situa em qual package ou "pacote" está a classe 
package factory;
// faz as importações de classes necessárias para o funcionamento do programa 

import java.sql.Connection;
// conexão SQL para Java 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
// driver de conexão SQL para Java 
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
// classe para tratamento de exceções 

public class ConnectionFactory {

    public static String status = "Não conectou...";

    public ConnectionFactory() {

    }

    public static java.sql.Connection getConnection() {

        Connection connection = null;          //atributo do tipo Connection

        try {

            // Carregando o JDBC Driver padrão
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);

            // Configurando a nossa conexão com um banco de dados//
            String serverName = "fmacedo.com.br";    //caminho do servidor do BD
            String mydatabase = "fmaced35_biblioteca";        //nome do seu banco de dados
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "fmaced35_malteze";        //nome de um usuário de seu BD      
            String password = "pedro@123";      //sua senha de acesso
            connection = DriverManager.getConnection(url, username, password);

            //Testa sua conexão//  
            if (connection != null) {

                status = ("STATUS ---> Conectado com sucesso!");

            } else {

                status = ("STATUS ---> Não foi possivel realizar conexão");

            }

            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado

            System.out.println("O driver expecificado nao foi encontrado.");
            return null;

        } catch (SQLException e) {
            //Não conseguindo se conectar ao banco
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }

    }

    //Método que retorna o status da sua conexão//
    public static String statusConection() {

        return status;
    }

    //Método que fecha sua conexão//
    public static boolean closeConnection() {

        try {
           
            ConnectionFactory.getConnection().close();

            return true;

        } catch (SQLException e) {

            return false;

        }

    }

    //Método que reinicia sua conexão//
    public static Connection ReiniciarConexao() {

        closeConnection();

        return ConnectionFactory.getConnection();

    }

    public static void closeConnection(Connection conn) {

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt) {

        closeConnection(conn);

        try {

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet res) {

        closeConnection(conn, stmt);

        try {

            if (res != null) {
                res.close();

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
