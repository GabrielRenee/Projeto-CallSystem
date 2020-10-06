import java.sql.*;
import java.util.*;

public class ChamadosDAO {
    public static Statement stChamados;
    public static ResultSet rsChamados;
    public static String msgErro;
    
    public static List<ChamadosVO> listarChamados() throws Exception {

        List<ChamadosVO> lstChamados = new ArrayList<ChamadosVO>();

        try {
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {
            String sqlChamados = "SELECT * FROM calls";

            stChamados = ConexaoDAO.connSistema.createStatement();
            rsChamados = stChamados.executeQuery(sqlChamados);

            while (rsChamados.next()) {
                ChamadosVO tmpChamados = new ChamadosVO();

                tmpChamados.setId(rsChamados.getInt("CallID"));
                tmpChamados.setStatus(rsChamados.getInt("Status"));
                tmpChamados.setIdCategoria(rsChamados.getInt("TypeID"));
                tmpChamados.setDataAbertura(rsChamados.getString("CallDate"));
                tmpChamados.setDataFechamento(rsChamados.getString("FinishDate"));
                tmpChamados.setTitulo(rsChamados.getString("Title"));
                tmpChamados.setIdCliente(rsChamados.getString("CustomerID"));
                tmpChamados.setDescricao(rsChamados.getString("Description"));
                tmpChamados.setSolucao(rsChamados.getString("Solution"));
                tmpChamados.setLoginUsuario(rsChamados.getString("UserID"));
                

                lstChamados.add(tmpChamados);

            }
        } catch (Exception erro) {
            msgErro = "Falha no processo de listagem dos dados do módulo Chamados.\n";
            msgErro += "Verifique a sintaxe da instrução SQL, nome de campos e tabelas.\n\n";
            msgErro += "Erro Original: " + erro.getMessage();

            throw new Exception(msgErro);
        }

        try {
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        return lstChamados;
    }
    
    public static ChamadosVO consultarChamados(String tmpId) throws Exception {
        ChamadosVO tmpChamados = new ChamadosVO();
        try {
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
        try {
            String sqlChamados = "SELECT * FROM calls where CallID like '" + tmpId + "'";
            stChamados = ConexaoDAO.connSistema.createStatement();
            rsChamados = stChamados.executeQuery(sqlChamados);

            if (rsChamados.next()) {
                tmpChamados.setId(rsChamados.getInt("CallID"));
                tmpChamados.setStatus(rsChamados.getInt("Status"));
                tmpChamados.setIdCategoria(rsChamados.getInt("TypeID"));
                tmpChamados.setDataAbertura(rsChamados.getString("CallDate"));
                tmpChamados.setDataFechamento(rsChamados.getString("FinishDate"));
                tmpChamados.setTitulo(rsChamados.getString("Title"));
                tmpChamados.setIdCliente(rsChamados.getString("CostumerID"));
                tmpChamados.setDescricao(rsChamados.getString("Decription"));
                tmpChamados.setSolucao(rsChamados.getString("Solution"));
                tmpChamados.setLoginUsuario(rsChamados.getString("UserID"));
            }

        } catch (Exception erro) {
            msgErro = "Falha na busca por detalhes do Chamado.\n";
            msgErro += "Verifique a sintaxe da instrução SQL, nome de campos e tabelas.\n\n";
            msgErro += "Erro Original: " + erro.getMessage();
            throw new Exception(erro.getMessage());
        }

        try {
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
        
        return tmpChamados;
    }
}
