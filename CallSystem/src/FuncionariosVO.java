import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class FuncionariosVO {

    private String id, sobrenome, nome, cargo, tratamento, subordinado,endereco,
            cidade, estado, cep, pais, telefone, extensao, observacao, dataCon, dataNasc;
    private float salario;
    
    
    public FuncionariosVO(){
        this.setId(null);
        this.setSobrenome(null);
        this.setNome(null);
        this.setCargo(null);
        this.setTratamento(null);
        this.setDataNasc(null);
        this.setDataCon(null);
        this.setEndereco(null);
        this.setCidade(null);
        this.setEstado(null);
        this.setCep(null);
        this.setPais(null);
        this.setTelefone(null);
        this.setExtensao(null);
        this.setObservacao(null);
        this.setSubordinado(null);
        this.setSalario(0);
    }

    public FuncionariosVO(String tmpId, String tmpSobrenome, String tmpNome, String tmpCargo,
            String tmpTratamento,String tmpDataNasc, String tmpDataCon, String tmpEndereco, String tmpCidade, String tmpEstado,
            String tmpCep, String tmpPais, String tmpTelefone, String tmpExtensao, String tmpObservacao, String tmpSubordinado, float tmpSalario){
       
        this.setId(tmpId);
        this.setSobrenome(tmpSobrenome);
        this.setNome(tmpNome);
        this.setCargo(tmpCargo);
        this.setTratamento(tmpTratamento);
        this.setDataNasc(tmpDataNasc);
        this.setDataCon(tmpDataCon);
        this.setEndereco(tmpEndereco);
        this.setCidade(tmpCidade);
        this.setEstado(tmpEstado);
        this.setCep(tmpCep);
        this.setPais(tmpPais);
        this.setTelefone(tmpTelefone);
        this.setExtensao(tmpExtensao);
        this.setObservacao(tmpObservacao);
        this.setSubordinado(tmpSubordinado);
        this.setSalario(tmpSalario);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String tmpId) {
        this.id = tmpId;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }

    public void setSobrenome(String tmpSobrenome) {
        this.sobrenome = tmpSobrenome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String tmpNome) {
        this.nome = tmpNome;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String tmpCargo) {
        this.cargo = tmpCargo;
    }

    public String getTratamento() {
        return this.tratamento;
    }

    public void setTratamento(String tmpTratamento) {
        this.tratamento = tmpTratamento;
    }

    public String getSubordinado() {
        return this.subordinado;
    }

    public void setSubordinado(String tmpSubordinado) {
        this.subordinado = tmpSubordinado;
    }

    public float getSalario() {
        return this.salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
    

    public String getDataNasc() {
        return this.dataNasc;
    }

    public void setDataNasc(String tmpDataNasc){
        this.dataNasc = tmpDataNasc;
    }

    public String getDataCon(){
        return this.dataCon;
    }

    public void setDataCon(String tmpDataCon){
        this.dataCon = tmpDataCon;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String tmpEndereco) {
        this.endereco = tmpEndereco;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String tmpCidade) {
        this.cidade = tmpCidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String tmpEstado) {
        this.estado = tmpEstado;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String tmpCep) {
        this.cep = tmpCep;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String tmpPais) {
        this.pais = tmpPais;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String tmpTelefone) {
        this.telefone = tmpTelefone;
    }

    public String getExtensao() {
        return this.extensao;
    }

    public void setExtensao(String tmpExtensao) {
        this.extensao = tmpExtensao;
    }

    public String getObservacao() {
        return this.observacao;
    }

    public void setObservacao(String tmpObservacao) {
        this.observacao = tmpObservacao;
    }

    
}
