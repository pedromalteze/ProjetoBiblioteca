/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Ramal;
/**
 *
 * @author pedro
 */
public class RamalDAO {

    private Connection conn = null;

    public RamalDAO() {
        conn = ConnectionFactory.getConnection();
    }

    @SuppressWarnings("null")
    public List<Object> carregarRamais() {

        
        ArrayList listaRamais = new ArrayList<Object>();
        
        try {

            String sql = "SELECT * FROM fmaced35_biblioteca.ramais;";

            PreparedStatement stmt;
            
            stmt = conn.prepareStatement(sql);
          
            if (!stmt.execute()) {
             
                    //tratar o erro. Tabela vazia
            
            } else {
                ResultSet resultado = stmt.executeQuery(sql);

                listaRamais.clear();

                while (resultado.next()) {
                    
                    Ramal ramal = new Ramal();

                    System.out.println("");

                    ramal.setId(resultado.getInt("id"));
                    ramal.setNomeDoRamal(resultado.getString("nomeDoRamal"));
                    ramal.setEndereco(resultado.getString("endereco"));


                    listaRamais.add(ramal);
                     
                }
            }

            conn.close();

            return listaRamais;

        } catch (SQLException ex) {
            System.out.println("o erro foi " + ex);
        }

        return null;
    }

}