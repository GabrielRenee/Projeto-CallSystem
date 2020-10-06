
import java.sql.*;
import java.util.*;

public class ClientesDAO {

    public static Statement stClientes;
    public static ResultSet rsClientes;
    public static String msgErro;

    public static List<ClientesVO> listarClientes(int tmpTipo, String tmpBusca) throws Exception {

        List<ClientesVO> lstClientes = new ArrayList<ClientesVO>();

        try {
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {
            
            String sqlClientes="";
            
            if(tmpTipo == 1) //todos
                sqlClientes = "SELECT * FROM customers order by CustomerId";
            else if (tmpTipo == 2) //iniciais
                sqlClientes = "SELECT * FROM customers where CompanyName like '" + tmpBusca + "%' order by CustomerId";            
            else if (tmpTipo == 3) //nome
                sqlClientes = "SELECT * FROM customers where CompanyName like '%" + tmpBusca + "%' order by CustomerId";
            else if (tmpTipo == 4) //cidade
                sqlClientes = "SELECT * FROM customers where city like '%" + tmpBusca + "%' order by CustomerId";
            

            stClientes = ConexaoDAO.connSistema.createStatement();
            rsClientes = stClientes.executeQuery(sqlClientes);

            while (rsClientes.next()) { //enquanto houver clientes no result

                ClientesVO tmpCliente = new ClientesVO();//instanciando

                //preenchendo
                tmpCliente.setId(rsClientes.getString("CustomerId"));
                tmpCliente.setNomeEmpresa(rsClientes.getString("CompanyName"));
                tmpCliente.setRepresentante(rsClientes.getString("ContactName"));
                tmpCliente.setCargo(rsClientes.getString("ContactTitle"));
                tmpCliente.setEndereco(rsClientes.getString("Address"));
                tmpCliente.setCidade(rsClientes.getString("City"));
                tmpCliente.setEstado(rsClientes.getString("Region"));
                tmpCliente.setCep(rsClientes.getString("PostalCode"));
                tmpCliente.setPais(rsClientes.getString("Country"));
                tmpCliente.setTelefone(rsClientes.getString("Phone"));
                tmpCliente.setEmail(rsClientes.getString("Fax"));

                //adicionando
                lstClientes.add(tmpCliente);
            }

        } catch (Exception erro) {
            msgErro = "Falha no processo de listagem dos dados do módulo Clientes.\n";
            msgErro += "Verifique a sintaxe da instrução SQL, nome de campos e tabelas.\n\n";
            msgErro += "Erro Original: " + erro.getMessage();

            throw new Exception(msgErro);

        }

        try {
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        return lstClientes;

    } //fechando listarClientes

    public static ClientesVO consultarCliente(String tmpId) throws Exception {

        ClientesVO tmpCliente = new ClientesVO();

        try {//1
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }

        try {//2

            String sqlCliente = "SELECT * FROM customers where CustomerID like '" + tmpId + "'";
            stClientes = ConexaoDAO.connSistema.createStatement();
            rsClientes = stClientes.executeQuery(sqlCliente);

            if (rsClientes.next()) {
                tmpCliente.setId(rsClientes.getString("CustomerId"));
                tmpCliente.setNomeEmpresa(rsClientes.getString("CompanyName"));
                tmpCliente.setRepresentante(rsClientes.getString("ContactName"));
                tmpCliente.setCargo(rsClientes.getString("ContactTitle"));
                tmpCliente.setEndereco(rsClientes.getString("Address"));
                tmpCliente.setCidade(rsClientes.getString("City"));
                tmpCliente.setEstado(rsClientes.getString("Region"));
                tmpCliente.setCep(rsClientes.getString("PostalCode"));
                tmpCliente.setPais(rsClientes.getString("Country"));
                tmpCliente.setTelefone(rsClientes.getString("Phone"));
                tmpCliente.setEmail(rsClientes.getString("Fax"));
            }

        } catch (Exception erro) {
            msgErro = "Falha na busca por detalhes do Cliente.\n";
            msgErro += "Verifique a sintaxe da instrução SQL, nome de campos e tabelas.\n\n";
            msgErro += "Erro Original: " + erro.getMessage();

            throw new Exception(msgErro);
        }

        try {//3
            ConexaoDAO.fechaConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }
        return tmpCliente;
        
    }//fechando consultarCliente
    
    public static void cadastrarCliente(ClientesVO tmpCliente) throws Exception{
       try {//1
            ConexaoDAO.abreConexao();
        } catch (Exception erro) {
            throw new Exception(erro.getMessage());
        }  
        
        try {
            String sqlCadCli;
            
            sqlCadCli = "Insert into customers(customerID, companyName, contactName, contacttitle,";
            sqlCadCli += "address, city, region, postalcode, country, phone, fax) values (";
            sqlCadCli += "'" + tmpCliente.getId() + "',";
            sqlCadCli += "'" + tmpCliente.getNomeEmpresa()+ "',";
            sqlCadCli += "'" + tmpCliente.getRepresentante()+ "',";
            sqlCadCli += "'" + tmpCliente.getCargo()+ "',";
            sqlCadCli += "'" + tmpCliente.getEndereco()+ "',";
            sqlCadCli += "'" + tmpCliente.getCidade()+ "',";
            sqlCadCli += "'" + tmpCliente.getEstado()+ "',";
            sqlCadCli += "'" + tmpCliente.getCep()+ "',";
            sqlCadCli += "'" + tmpCliente.getPais() + "',";
            sqlCadCli += "'" + tmpCliente.getTelefone()+ "',";
            sqlCadCli += "'" + tmpCliente.getEmail()+ "')";
            
            
            stClientes = ConexaoDAO.connSistema.createStatement();
            stClientes.executeUpdate(sqlCadCli); //executeUpdate - executa instruções que nao retornam valor
            
            
        } catch (Exception erro) {
            msgErro = "Falha na inserção de dados  do Cliente.\n";
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

}//fechando class
