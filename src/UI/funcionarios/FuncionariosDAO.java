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
            stmt.setString(13, fBEAN.getRua());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void createFuncionarioDisponibilidade(String matricula, String disponibilidade){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO funcionarios_disponibilidade VALUES(?,?)");
            stmt.setString(1, matricula);
            stmt.setString(2, disponibilidade);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
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
    
    public List<FuncionariosBEAN> retornaDisponibilidade(String matricula){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<FuncionariosBEAN> retorno = new ArrayList();
        
        try{
            stmt = con.prepareStatement("SELECT horario_por_dia FROM funcionarios_disponibilidade WHERE matricula = ?");
            stmt.setString(1, matricula);
            rs = stmt.executeQuery();
            while(rs.next()){
                FuncionariosBEAN aux = new FuncionariosBEAN();
                aux.setCargaHorariaSemanal(rs.getString("horario_por_dia"));
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
    
    public void atualizaRegistroFuncionario(FuncionariosBEAN bean, String matricula){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE funcionarios SET nome = ?, cpf = ?, data_nascimento = ?, data_admissao = ?,"
                                        + "setor = ?, cargo = ?, senha = ?, rg = ?, endereco = ?, bairro = ?, cep = ?,"
                                        + "rua = ? WHERE matricula = ?");
            stmt.setString(1, bean.getNome());
            stmt.setString(2, bean.getCpf());
            stmt.setString(3, bean.getDataNascimento());
            stmt.setString(4, bean.getDataAdmissao());
            stmt.setString(5, bean.getSetor());
            stmt.setString(6, bean.getCargo());
            stmt.setString(7, bean.getSenha());
            stmt.setString(8, bean.getRg());
            stmt.setString(9, bean.getEndereco());
            stmt.setString(10, bean.getBairro());
            stmt.setString(11, bean.getCep());
            stmt.setString(12, bean.getRua());
            stmt.setString(13, matricula);
            stmt.executeUpdate();
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void atualizaRegistroDisponibilidade(FuncionariosBEAN bean, String matricula){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE funcionarios_disponibilidade SET horario_por_dia = ? WHERE matricula = ?");
            stmt.setString(1, bean.getCargaHorariaSemanal());
            stmt.setString(2, matricula);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void excluiDisponibilidade(String matricula){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM funcionarios_disponibilidade WHERE matricula = ?");
            stmt.setString(1, matricula);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void excluiFuncionario(String matricula){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM funcionarios WHERE matricula = ?");
            stmt.setString(1, matricula);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
