/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UI.login;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author claudio
 */
public class LoginDAO {
    
    public int verificaLogin(String matricula, String senha){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int retorno = 0;
        
        try{
            stmt = con.prepareStatement("SELECT matricula,senha FROM funcionarios WHERE matricula = ?");
            stmt.setString(1, matricula);
            rs = stmt.executeQuery();
            while(rs.next()){
                if(rs.getString("senha").equals(senha)){
                    retorno = 1;
                }else{
                    retorno = 0;
                }
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = 0;
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return retorno;
    }
    
    public String retornaNomeFuncionario(String matricula){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String retorno = null;
        
        try{
            stmt = con.prepareStatement("SELECT nome FROM funcionarios WHERE matricula = ?");
            stmt.setString(1, matricula);
            rs = stmt.executeQuery();
            while(rs.next()){
                retorno = rs.getString("nome");
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = null;
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
}
