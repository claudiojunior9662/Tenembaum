/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UI.funcionarios;

import Connection.ConnectionFactory;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author claudio
 */
public class FuncionariosDAO {
    public void createFuncionario(FuncionariosBEAN fBEAN){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO funcionarios(matricula,nome,"
                                        + "cpf,data_nascimento,data_admissao,"
                                        + "setor,cargo,senha,rg,endereco,bairro,cep,rua) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, String.valueOf(fBEAN.getMatricula()));
            stmt.setString(2, fBEAN.getNome());
            stmt.setString(3, String.valueOf(fBEAN.getCpf()));
            stmt.setString(4, fBEAN.getDataNascimento());
            stmt.setString(5, fBEAN.getDataAdmissao());
            stmt.setString(6, fBEAN.getSetor());
            stmt.setString(7, fBEAN.getCargo());
            stmt.setString(8, fBEAN.getSenha());
            stmt.setString(9, fBEAN.getRg());
            stmt.setString(10, fBEAN.getEndereco());
            stmt.setString(11, fBEAN.getBairro());
            stmt.setString(12, fBEAN.getCep());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<FuncionariosBEAN> verificaRepeticao(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<FuncionariosBEAN> retorno = new ArrayList();
        
        try{
            stmt = con.prepareStatement("SELECT matricula, cpf, rg FROM funcionarios");
            rs = stmt.executeQuery();
            while(rs.next()){
                FuncionariosBEAN aux = new FuncionariosBEAN();
                aux.setMatricula(rs.getString("matricula"));
                aux.setCpf(rs.getString("cpf"));
                aux.setRg(rs.getString("rg"));
                retorno.add(aux);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    public List<FuncionariosBEAN> retornaPesquisa(String matricula){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<FuncionariosBEAN> retorno = new ArrayList();
        
        try{
            stmt = con.prepareStatement("SELECT nome, setor, cargo FROM funcionarios WHERE matricula = ?");
            stmt.setString(1, matricula);
            rs = stmt.executeQuery();
            while(rs.next()){
                FuncionariosBEAN aux = new FuncionariosBEAN();
                aux.setNome(rs.getString("nome"));
                aux.setSetor(rs.getString("setor"));
                aux.setCargo(rs.getString("cargo"));
                retorno.add(aux);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    public List<FuncionariosBEAN> preenchePesquisa(String matricula){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<FuncionariosBEAN> retorno = new ArrayList();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM funcionarios WHERE matricula = ?");
            stmt.setString(1, matricula);
            rs = stmt.executeQuery();
            while(rs.next()){
                FuncionariosBEAN aux = new FuncionariosBEAN();
                aux.setNome(rs.getString("nome"));
                aux.setCpf(rs.getString("cpf"));
                aux.setDataNascimento(rs.getString("data_nascimento"));
                aux.setDataAdmissao(rs.getString("data_admissao"));
                aux.setSetor(rs.getString("setor"));
                aux.setCargo(rs.getString("cargo"));
                aux.setSenha(rs.getString("senha"));
                aux.setEndereco(rs.getString("endereco"));
                aux.setRg(rs.getString("rg"));
                aux.setBairro(rs.getString("bairro"));
                aux.setCep(rs.getString("cep"));
                aux.setRua(rs.getString("rua"));
                retorno.add(aux);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    public List<FuncionariosBEAN> mostraTodos(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<FuncionariosBEAN> retorno = new ArrayList();
        
        try{
            stmt = con.prepareStatement("SELECT matricula, nome, setor, cargo FROM funcionarios");
            rs = stmt.executeQuery();
            while(rs.next()){
                FuncionariosBEAN aux = new FuncionariosBEAN();
                aux.setMatricula(rs.getString("matricula"));
                aux.setNome(rs.getString("nome"));
                aux.setSetor(rs.getString("setor"));
                aux.setCargo(rs.getString("cargo"));
                retorno.add(aux);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
    
    public List<FuncionariosBEAN> retornaTelaPrincipal(String matricula){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<FuncionariosBEAN> retorno = new ArrayList();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM funcionarios WHERE matricula = ?");
            stmt.setString(1, matricula);
            rs = stmt.executeQuery();
            while(rs.next()){
                FuncionariosBEAN aux = new FuncionariosBEAN();
                aux.setMatricula(matricula);
                aux.setNome(rs.getString("nome"));
                aux.setCpf(rs.getString("cpf"));
                aux.setDataNascimento(rs.getString("data_nascimento"));
                aux.setDataAdmissao(rs.getString("data_admissao"));
                aux.setSetor(rs.getString("setor"));
                aux.setCargo(rs.getString("cargo"));
                aux.setRg(rs.getString("rg"));
                aux.setEndereco(rs.getString("endereco"));
                aux.setBairro(rs.getString("bairro"));
                aux.setCep(rs.getString("cep"));
                aux.setRua(rs.getString("rua"));
                retorno.add(aux);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return retorno;
    }
}
