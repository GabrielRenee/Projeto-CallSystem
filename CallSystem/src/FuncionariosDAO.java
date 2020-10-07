
import java.sql.*;
import java.util.*;

public class FuncionariosDAO {

    public static Statement stFuncionarios, stVendas, stPreco;
    public static ResultSet rsFuncionarios, rsVendas, rsPreco;
    public static String msgErro;
    public static float tmpPreco, total;

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
                tmpFuncionarios.setTratamento(rsFuncionarios.getString("TitleOfCourtesy"));
                tmpFuncionarios.setDataNasc(rsFuncionarios.getDate("BirthDate"));
                tmpFuncionarios.setDataCon(rsFuncionarios.getDate("HireDate"));
                tmpFuncionarios.setEndereco(rsFuncionarios.getString("Address"));
                tmpFuncionarios.setCidade(rsFuncionarios.getString("City"));
                tmpFuncionarios.setEstado(rsFuncionarios.getString("Region"));
                tmpFuncionarios.setCep(rsFuncionarios.getString("PostalCode"));
                tmpFuncionarios.setPais(rsFuncionarios.getString("Country"));
                tmpFuncionarios.setTelefone(rsFuncionarios.getString("HomePhone"));
                tmpFuncionarios.setExtensao(rsFuncionarios.getString("Extension"));
                tmpFuncionarios.setObservacao(rsFuncionarios.getString("Notes"));
                tmpFuncionarios.setSubordinado(rsFuncionarios.getString("ReportsTo"));
                tmpFuncionarios.setSalario(rsFuncionarios.getFloat("Salary"));

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

    public static List<FuncionariosVO> listarVendas(int tmpTipo) throws Exception {

        List<FuncionariosVO> lstVendas = new ArrayList<FuncionariosVO>();

        try {
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {
            String sqlPreco = "";
            String sqlVendas = "";
            
            sqlVendas = "SELECT orders.CustomerID, orders.ShipName, orders.OrderID, orders.EmployeeID, orderdetails.UnitPrice FROM orders "
                    + "INNER JOIN orderdetails ON orders.OrderID = orderdetails.OrderID where orders.EmployeeID = " + tmpTipo;

            stVendas = ConexaoDAO.connSistema.createStatement();
            rsVendas = stVendas.executeQuery(sqlVendas);

            while (rsVendas.next()) { //enquanto houver clientes no result

                FuncionariosVO tmpVendas = new FuncionariosVO();//instanciando

                //preenchendo
                tmpVendas.setClienteID(rsVendas.getString("CustomerID"));
                tmpVendas.setEmpresa(rsVendas.getString("ShipName"));
                tmpVendas.setVendaID(rsVendas.getString("OrderID"));
                tmpVendas.setPreco(rsVendas.getFloat("UnitPrice"));
                
                //adicionando
                lstVendas.add(tmpVendas);
            }

        } catch (Exception erro) {
            msgErro = "Falha no processo de listagem dos dados do módulo Vendas.\n";
            msgErro += "Verifique a sintaxe da instrução SQL, nome de campos e tabelas.\n\n";
            msgErro += "Erro Original: " + erro.getMessage();

            throw new Exception(msgErro);
        }

        try {
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        return lstVendas;
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
                tmpFuncionarios.setTratamento(rsFuncionarios.getString("TitleOfCourtesy"));
                tmpFuncionarios.setDataNasc(rsFuncionarios.getDate("BirthDate"));
                tmpFuncionarios.setDataCon(rsFuncionarios.getDate("HireDate"));
                tmpFuncionarios.setEndereco(rsFuncionarios.getString("Address"));
                tmpFuncionarios.setCidade(rsFuncionarios.getString("City"));
                tmpFuncionarios.setEstado(rsFuncionarios.getString("Region"));
                tmpFuncionarios.setCep(rsFuncionarios.getString("PostalCode"));
                tmpFuncionarios.setPais(rsFuncionarios.getString("Country"));
                tmpFuncionarios.setTelefone(rsFuncionarios.getString("HomePhone"));
                tmpFuncionarios.setExtensao(rsFuncionarios.getString("Extension"));
                tmpFuncionarios.setObservacao(rsFuncionarios.getString("Notes"));
                tmpFuncionarios.setSubordinado(rsFuncionarios.getString("ReportsTo"));
                tmpFuncionarios.setSalario(rsFuncionarios.getFloat("Salary"));
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

    public static void cadastrarFuncionarios(FuncionariosVO tmpFuncionarios) throws Exception {
        try {//1
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {
            String sqlCadFun;

            sqlCadFun = "INSERT INTO `northwind`.`employees` (`EmployeeID`, `LastName`, `FirstName`, `Title`, `TitleOfCourtesy`, `BirthDate`, `HireDate`, `Address`, `City`, `Region`, `PostalCode`, `Country`, "
                    + "`HomePhone`, `Extension`,`Photo`, `Notes`, `ReportsTo`, "
                    + "`PhotoPath`, `Salary`) VALUES(";
            sqlCadFun += "'" + tmpFuncionarios.getId() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getSobrenome() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getNome() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getCargo() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getTratamento() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getDataNasc() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getDataCon() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getEndereco() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getCidade() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getEstado() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getCep() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getPais() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getTelefone() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getExtensao() + "',";
            sqlCadFun += "'" + "''" + "',";
            sqlCadFun += "'" + tmpFuncionarios.getObservacao() + "',";
            sqlCadFun += "'" + tmpFuncionarios.getSubordinado() + "',";
            sqlCadFun += "'" + "''" + "',";
            sqlCadFun += "'" + tmpFuncionarios.getSalario() + "')";

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

    public static void atualizarFuncionario(FuncionariosVO tmpFuncionarios) throws Exception {
        try {//1
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {
            String sqlAttFun;

            sqlAttFun = "UPDATE `northwind`.`employees` SET ";
            sqlAttFun += "`LastName` =" + "'" + tmpFuncionarios.getSobrenome() + "',";
            sqlAttFun += "`FirstName` =" + "'" + tmpFuncionarios.getNome() + "',";
            sqlAttFun += "`Title` =" + "'" + tmpFuncionarios.getCargo() + "',";
            sqlAttFun += "`TitleOfCourtesy` =" + "'" + tmpFuncionarios.getTratamento() + "',";
            sqlAttFun += "`BirthDate` =" + "'" + tmpFuncionarios.getDataNasc() + "',";
            sqlAttFun += "`HireDate` =" + "'" + tmpFuncionarios.getDataCon() + "',";
            sqlAttFun += "`Address` =" + "'" + tmpFuncionarios.getEndereco() + "',";
            sqlAttFun += "`City` =" + "'" + tmpFuncionarios.getCidade() + "',";
            sqlAttFun += "`Region` =" + "'" + tmpFuncionarios.getEstado() + "',";
            sqlAttFun += "`PostalCode` =" + "'" + tmpFuncionarios.getCep() + "',";
            sqlAttFun += "`Country` =" + "'" + tmpFuncionarios.getPais() + "',";
            sqlAttFun += "`HomePhone` =" + "'" + tmpFuncionarios.getTelefone() + "',";
            sqlAttFun += "`Extension` =" + "'" + tmpFuncionarios.getExtensao() + "',";
            sqlAttFun += "`Notes` =" + "'" + tmpFuncionarios.getObservacao() + "',";
            sqlAttFun += "`ReportsTo` =" + "'" + tmpFuncionarios.getSubordinado() + "',";
            sqlAttFun += "`Salary` =" + "'" + tmpFuncionarios.getSalario() + "'";
            sqlAttFun += "WHERE `employees`.`EmployeeID` =" + tmpFuncionarios.getId() + ";";

            stFuncionarios = ConexaoDAO.connSistema.createStatement();
            stFuncionarios.executeUpdate(sqlAttFun); //executeUpdate - executa instruções que nao retornam valor

        } catch (Exception erro) {
            msgErro = "Falha na atualização de dados  do Funcionario.\n";
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
