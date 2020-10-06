public class ChamadosVO {
    
    private int id, status, idCategoria;
    private String dataAbertura, dataFechamento, titulo, idCliente, descricao, solucao, loginUsuario ;

    public ChamadosVO(){
        this.setId(0);
        this.setStatus(0);
        this.setIdCategoria(0);
        this.setDataAbertura(null);
        this.setDataFechamento(null);
        this.setTitulo(null);
        this.setIdCliente(null);
        this.setDescricao(null);
        this.setSolucao(null);
        this.setLoginUsuario(null);        
    }
    
    public ChamadosVO(int tmpId, int tmpStatus, int tmpIdCategoria, String tmpDataAbertura, String tmpDataFechamento, String tmpTitulo, String tmpIdCliente, String tmpDescricao, String tmpSolucao, String tmpLoginUsuario){
        this.setId(tmpId);
        this.setStatus(tmpStatus);
        this.setIdCategoria(tmpIdCategoria);
        this.setDataAbertura(tmpDataAbertura);
        this.setDataFechamento(tmpDataFechamento);
        this.setTitulo(tmpTitulo);
        this.setIdCliente(tmpIdCliente);
        this.setDescricao(tmpDescricao);
        this.setSolucao(tmpSolucao);
        this.setLoginUsuario(tmpLoginUsuario);
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int tmpId) {
        this.id = tmpId;
    } 

    public int getStatus() {
        return this.status;
    } 

    public void setStatus(int tmpStatus) {
        this.status = tmpStatus;
    } 

    public int getIdCategoria() {
        return this.idCategoria;
    } 

    public void setIdCategoria(int tmpIdCategoria) {
        this.idCategoria = tmpIdCategoria;
    } 

    public String getDataAbertura() {
        return this.dataAbertura;
    } 

    public void setDataAbertura(String tmpDataAbertura) {
        this.dataAbertura = tmpDataAbertura;
    } 

    public String getDataFechamento() {
        return this.dataFechamento;
    }

    public void setDataFechamento(String tmpDataFechamento) {
        this.dataFechamento = tmpDataFechamento;
    }

    public String getTitulo() {
        return this.titulo;
    } 

    public void setTitulo(String tmpTitulo) {
        this.titulo = tmpTitulo;
    } 

    public String getIdCliente() {
        return this.idCliente;
    } 

    public void setIdCliente(String tmpIdCliente) {
        this.idCliente = tmpIdCliente;
    } 

    public String getDescricao() {
        return this.descricao;
    } 

    public void setDescricao(String tmpDescricao) {
        this.descricao = tmpDescricao;
    } 

    public String getSolucao() {
        return this.solucao;
    } 

    public void setSolucao(String tmpSolucao) {
        this.solucao = tmpSolucao;
    } 

    public String getLoginUsuario() {
        return this.loginUsuario;
    } 

    public void setLoginUsuario(String tmpLoginUsuario) {
        this.loginUsuario = tmpLoginUsuario;
    }
}