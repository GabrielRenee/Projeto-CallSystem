
import java.sql.*;
import java.util.*;

public class FuncionariosDAO {

    public static Statement stFuncionarios;
    public static ResultSet rsFuncionarios;
    public static String msgErro;

    public static List<FuncionariosVO> listarFuncionarios(int tmpTipo, String tmpBusca) throws Exception {

        List<FuncionariosVO> lstFuncionarios = new ArrayList<FuncionariosVO>();

        try {
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {

            String sqlFuncionarios = "";

            if (tmpTipo == 1) //todos
            {
                sqlFuncionarios = "SELECT * FROM employees order by EmployeeID";
            } else if (tmpTipo == 2) //iniciais
            {
                sqlFuncionarios = "SELECT * FROM employees where FirstName like '" + tmpBusca + "%' order by EmployeeID";
            } else if (tmpTipo == 3) //nome
            {
                sqlFuncionarios = "SELECT * FROM employees where FirstName like '%" + tmpBusca + "%' order by EmployeeID";
            } else if (tmpTipo == 4) //cidade
            {
                sqlFuncionarios = "SELECT * FROM employees where city like '%" + tmpBusca + "%' order by EmployeeID";
            }

            stFuncionarios = ConexaoDAO.connSistema.createStatement();
            rsFuncionarios = stFuncionarios.executeQuery(sqlFuncionarios);

            while (rsFuncionarios.next()) { //enquanto houver clientes no result

                FuncionariosVO tmpFuncionarios = new FuncionariosVO();//instanciando

                //preenchendo
                tmpFuncionarios.setId(rsFuncionarios.getString("EmployeeID"));
                tmpFuncionarios.setSobrenome(rsFuncionarios.getString("LastName"));
                tmpFuncionarios.setNome(rsFuncionarios.getString("FirstName"));
                tmpFuncionarios.setCargo(rsFuncionarios.getString("Title"));
                tmpFuncionarios.setDataNasc(rsFuncionarios.getString("BirthDate"));
                tmpFuncionarios.setDataCon(rsFuncionarios.getString("HireDate"));
                tmpFuncionarios.setEndereco(rsFuncionarios.getString("Address"));
                tmpFuncionarios.setCidade(rsFuncionarios.getString("City"));
                tmpFuncionarios.setEstado(rsFuncionarios.getString("Region"));
                tmpFuncionarios.setCep(rsFuncionarios.getString("PostalCode"));
                tmpFuncionarios.setPais(rsFuncionarios.getString("Country"));
                tmpFuncionarios.setTelefone(rsFuncionarios.getString("HomePhone"));
                tmpFuncionarios.setExtensao(rsFuncionarios.getString("Extension"));
                tmpFuncionarios.setObservacao(rsFuncionarios.getString("Notes"));

                //adicionando
                lstFuncionarios.add(tmpFuncionarios);
            }
        } catch (Exception erro) {
            msgErro = "Falha no processo de listagem dos dados do módulo Funcionarios.\n";
            msgErro += "Verifique a sintaxe da instrução SQL, nome de campos e tabelas.\n\n";
            msgErro += "Erro Original: " + erro.getMessage();

            throw new Exception(msgErro);
        }
        
        try {
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        return lstFuncionarios;
    }
    
    public static FuncionariosVO consultarFuncionarios(String tmpId) throws Exception {
        FuncionariosVO tmpFuncionarios = new FuncionariosVO();
        try {//1
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
        
        try {//2

            String sqlFuncionarios = "SELECT * FROM employees where EmployeeID like '" + tmpId + "'";
            stFuncionarios = ConexaoDAO.connSistema.createStatement();
            rsFuncionarios = stFuncionarios.executeQuery(sqlFuncionarios);
            
            if (rsFuncionarios.next()) {
                tmpFuncionarios.setId(rsFuncionarios.getString("EmployeeID"));
                tmpFuncionarios.setSobrenome(rsFuncionarios.getString("LastName"));
                tmpFuncionarios.setNome(rsFuncionarios.getString("FirstName"));
                tmpFuncionarios.setCargo(rsFuncionarios.getString("Title"));
                tmpFuncionarios.setDataNasc(rsFuncionarios.getString("BirthDate"));
                tmpFuncionarios.setDataCon(rsFuncionarios.getString("HireDate"));
                tmpFuncionarios.setEndereco(rsFuncionarios.getString("Address"));
                tmpFuncionarios.setCidade(rsFuncionarios.getString("City"));
                tmpFuncionarios.setEstado(rsFuncionarios.getString("Region"));
                tmpFuncionarios.setCep(rsFuncionarios.getString("PostalCode"));
                tmpFuncionarios.setPais(rsFuncionarios.getString("Country"));
                tmpFuncionarios.setTelefone(rsFuncionarios.getString("HomePhone"));
                tmpFuncionarios.setExtensao(rsFuncionarios.getString("Extension"));
                tmpFuncionarios.setObservacao(rsFuncionarios.getString("Notes"));
            }

        } catch (Exception erro) {
            msgErro = "Falha na busca por detalhes do Funcionário.\n";
            msgErro += "Verifique a sintaxe da instrução SQL, nome de campos e tabelas.\n\n";
            msgErro += "Erro Original: " + erro.getMessage();

            throw new Exception(msgErro);
        }

        try {//3
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
        return tmpFuncionarios;
     
    }
    
    public static void cadastrarFuncionarios(FuncionariosVO tmpFuncionarios) throws Exception{
        try {//1
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
        
        try {
            String sqlCadFun;
            
            sqlCadFun = "INSERT INTO `northwind`.`employees` (`EmployeeID`, `LastName`, `FirstName`, `Title`, `TitleOfCourtesy`, `BirthDate`, `HireDate`, `Address`, `City`, `Region`, `PostalCode`, `Country`, "
                    + "`HomePhone`, `Extension`, `Notes`, `ReportsTo`, "
                    + "`PhotoPath`, `Salary`) VALUES ";
            sqlCadFun += "'" + tmpFuncionarios.getId() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getSobrenome()+ "',";
            sqlCadFun += "'" + tmpFuncionarios.getNome()+ "',";
            sqlCadFun += "'" + tmpFuncionarios.getCargo()+ "',";
            sqlCadFun += "'" + "''" + "',";
            sqlCadFun += "'" + tmpFuncionarios.getDataNasc()+ "',";
            sqlCadFun += "'" + tmpFuncionarios.getDataCon()+ "',";
            sqlCadFun += "'" + tmpFuncionarios.getEndereco()+ "',";
            sqlCadFun += "'" + tmpFuncionarios.getCidade()+ "',";
            sqlCadFun += "'" + tmpFuncionarios.getEstado()+ "',";
            sqlCadFun += "'" + tmpFuncionarios.getCep()+ "',";
            sqlCadFun += "'" + tmpFuncionarios.getPais()+ "',";
            sqlCadFun += "'" + tmpFuncionarios.getTelefone()+ "',";
            sqlCadFun += "'" + tmpFuncionarios.getExtensao()+ "',";
            sqlCadFun += "'" + tmpFuncionarios.getObservacao()+ "',";
            sqlCadFun += "'" + "''" + "',";
            sqlCadFun += "'" + "''" + "',";
            sqlCadFun += "'" + "''" + "')";
            
            
            stFuncionarios = ConexaoDAO.connSistema.createStatement();
            stFuncionarios.executeUpdate(sqlCadFun); //executeUpdate - executa instruções que nao retornam valor
            
            
        } catch (Exception erro) {
            msgErro = "Falha na inserção de dados  do Funcionario.\n";
            msgErro += "Verifique a sintaxe da instrução SQL, nome de campos e tabelas.\n\n";
            msgErro += "Erro Original: " + erro.getMessage();

            throw new Exception(msgErro); 
        }
        
         try {//36
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
    }
}
