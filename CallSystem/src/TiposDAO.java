import java.sql.*;
import java.util.*;

public class TiposDAO {
    
    public static Statement stTipos;
    public static ResultSet rsTipos;
    public static String msgErro;

    public static List<TiposVO> listarTipos() throws Exception {

        List<TiposVO> lstTipos = new ArrayList<TiposVO>();

        try {
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {
            
            String sqlTipos="Select * from calltypes";
            
            stTipos = ConexaoDAO.connSistema.createStatement();
            rsTipos = stTipos.executeQuery(sqlTipos);

            while (rsTipos.next()) { //enquanto houver clientes no result

                TiposVO tmpTipo = new TiposVO();//instanciando

                //preenchendo
                tmpTipo.setId(rsTipos.getInt("TypeID"));
                tmpTipo.setDescricao(rsTipos.getString("TypeName"));
                
                //adicionando
                lstTipos.add(tmpTipo);
            }

        } catch (Exception erro) {
            msgErro = "Falha no carregamento das categorias de chamados.\n";
            msgErro += "Verifique a sintaxe da instrução SQL, nome de campos e tabelas.\n\n";
            msgErro += "Erro Original: " + erro.getMessage();

            throw new Exception(msgErro);

        }

        try {
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        return lstTipos;

    } //fechando listarClientes
}
