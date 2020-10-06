
public class TiposVO {
    private int id;
    private String descricao;

    public TiposVO(){
        this.setId(0);
        this.setDescricao(null);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int tmpId) {
        this.id = tmpId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String tmpDescricao) {
        this.descricao = tmpDescricao;
    }
    
    
          
    
}
