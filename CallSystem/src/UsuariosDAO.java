import java.sql.*;

public class UsuariosDAO {
    
    public static Statement stUsuarios; //Objeto Statement, gerencia a execução de instruções SQL
    public static ResultSet rsUsuarios; //Objeto ResultSet, armazena o resultado de uma consulta SQL
    public static String msgErro;
    
    public static UsuariosVO validarUsuario(String tmpNome, String tmpSenha) throws Exception{
        
        UsuariosVO tmpUsuario = new UsuariosVO(); //objeto que vai armazenar os dados do perfil do usuario
       
        /********************1. ABERTURA DA CONEXAO***************************/
        try{
            ConexaoDAO.abreConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        /********************2. EXECUÇÃO***************************/
        try{
           //Instruções para o login
            
            String sqlLogin = "";
            sqlLogin += "SELECT * FROM users WHERE UserId LIKE '" + tmpNome + "' AND ";
            sqlLogin += "Password LIKE '" + tmpSenha + "'";
            
            //VInculando o Statement à conexao aberta
            stUsuarios = ConexaoDAO.connSistema.createStatement();
            //Executando Select e armazenando resultado no ResultSet
            rsUsuarios = stUsuarios.executeQuery(sqlLogin);
            
            //Tratamento dos resultados
            if(rsUsuarios.next()){ //se houver dados de retorno
                //preenchendo o 'perfil' do usuario (objeto USUARIOSVO)
                tmpUsuario.setLogin(rsUsuarios.getString("UserId"));
                tmpUsuario.setNome(rsUsuarios.getString("UserName"));
                tmpUsuario.setSenha(rsUsuarios.getString("Password"));
                tmpUsuario.setPermissao(rsUsuarios.getInt("Permission"));
                tmpUsuario.setImagem(rsUsuarios.getString("Picture"));
                
            }else{
                 tmpUsuario = null;
            }
            
        }catch(Exception erro){
            
            msgErro = "Falha no processo de autenticação do usuário com o banco de dados.\n";
            msgErro += "Verifique a sintaxe da instrução SQL, nome de campos e tabelas.\n\n";
            msgErro += "Erro Original: " + erro.getMessage();
            
            throw new Exception (msgErro);
        }
        
        /********************3. FECHAMENTO DA CONEXÃO***************************/
        try{
            ConexaoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception (erro.getMessage());
        }
        
        
        return tmpUsuario; //retorno do método
    
    }
    
    public static boolean cadastrarUsuario(UsuariosVO tmpUsuario) throws Exception{
        
        return false;
    }
    
    
}//fechando classe
