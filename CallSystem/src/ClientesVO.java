
public class ClientesVO {

    private String id, nomeEmpresa, representante, cargo, endereco,
            cidade, estado, cep, pais, telefone, email;

    //método construtor
    public ClientesVO() {
        this.setId(null);
        this.setNomeEmpresa(null);
        this.setRepresentante(null);
        this.setCargo(null);
        this.setEndereco(null);
        this.setCidade(null);
        this.setEstado(null);
        this.setCep(null);
        this.setPais(null);
        this.setTelefone(null);
        this.setEmail(null);        
    }
    //método construtor
    public ClientesVO(String tmpId, String tmpNomeEmpresa, String tmpRepresentante, String tmpCargo, String tmpEndereco, String tmpBairro, String tmpCidade, String tmpEstado, String tmpCep, String tmpPais, String tmpTelefone, String tmpEmail){
        this.setId(tmpId);
        this.setNomeEmpresa(tmpNomeEmpresa);
        this.setRepresentante(tmpRepresentante);
        this.setCargo(tmpCargo);
        this.setEndereco(tmpEndereco);
        this.setCidade(tmpCidade);
        this.setEstado(tmpEstado);
        this.setCep(tmpCep);
        this.setPais(tmpPais);
        this.setTelefone(tmpTelefone);
        this.setEmail(tmpEmail);        
    }
    
    //métodos set e get
    public String getId() {
        return this.id;
    }

    public void setId(String tmpId) {
        this.id = tmpId;
    }

    public String getNomeEmpresa() {
        return this.nomeEmpresa;
    }

    public void setNomeEmpresa(String tmpNomeEmpresa) {
        this.nomeEmpresa = tmpNomeEmpresa;
    }

    public String getRepresentante() {
        return this.representante;
    }

    public void setRepresentante(String tmpRepresentante) {
        this.representante = tmpRepresentante;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String tmpCargo) {
        this.cargo = tmpCargo;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String tmpEmail) {
        this.email = tmpEmail;
    }

}
