/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jk
 */
public class Conexao {

    public static void main(String[] args) throws SQLException {
        Connection conexao = null;//fica aqui a variavel para poder fechar a conexao com o finally
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/javadb","root","");//conexao ao banco           
            
            ResultSet rsCliente = conexao.createStatement().executeQuery("SELECT * FROM cliente");//sql ao banco
            String nomes = "";
            while(rsCliente.next()){
                nomes += " - "+rsCliente.getString("nome")+"\n";
                System.out.println("Nome: "+rsCliente.getString("nome"));//mostra o valor do atributo 'nome'
            }
            
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f,nomes);
            
        } catch (ClassNotFoundException ex) {//notifica o Classe se não tiver conexao ao banco
            System.out.println("Driver do Banco de Dados Não Localizados");
        } catch(SQLException ex){
            System.out.println("Ocorreu um erro ao Acessar o Banco: "+ex.getMessage());
        }finally{
           if(conexao != null){
               conexao.close();//fecha a conexao do banco
           }
        }
    }
}
