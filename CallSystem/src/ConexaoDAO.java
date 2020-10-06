import java.sql.*;

public class ConexaoDAO { //DAO - Data Acess Object
    
    //Classe Connection - responsável por gerenciar a abertura e fechamento da conexao a banco de Dados
    public static Connection connSistema;
    public static String msgErro;
    
    public static void abreConexao() throws Exception{
        
        try{
            //abertura
            //1. Referenciar bilbioteca do MySql
            Class.forName("com.mysql.jdbc.Driver");            
            //2. Abrir e Armazenar conexao com BD
            connSistema = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind","root","");
            
            System.out.println("Conectado - NORTHWIND");
                    
        }catch(Exception erro){
            msgErro = "Falha na abertura da conexão com o Banco de Dados MYSQL.\n";
            msgErro += "Verifique a String de conexão e o nome da fonte de dados.\n\n";
            msgErro += "Erro Original: " + erro.getMessage();
            
            throw new Exception (msgErro);
            
        }
        
    }//fechando abreConexao
    
    public static void fechaConexao() throws Exception{
        
        try{
            //fechamento
            connSistema.close();
            
        }catch(Exception erro){
            msgErro = "Falha no fechamento da conexão com o Banco de Dados MYSQL.\n";
            msgErro += "Não há conexões ativas.\n\n";
            msgErro += "Erro Original: " + erro.getMessage();
            
            throw new Exception (msgErro);
        }
        
    }//fechando fechaConexao
    
}
