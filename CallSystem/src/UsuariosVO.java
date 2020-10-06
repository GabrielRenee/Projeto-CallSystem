
public class UsuariosVO {
 
    //declaração dos atributos
    private int permissao;
    private String nome, login, senha, imagem;
    
    //método construtor (vazio, cheio)
    public UsuariosVO(){
        this.setPermissao(0);
        this.setNome(null);
        this.setLogin(null);
        this.setSenha(null);
        this.setImagem(null);
    }
    
    public UsuariosVO(int tmpPermissao, String tmpNome, String tmpLogin, String tmpSenha, String tmpImagem){
        this.setPermissao(tmpPermissao);
        this.setNome(tmpNome);
        this.setLogin(tmpLogin);
        this.setSenha(tmpSenha);
        this.setImagem(tmpImagem);
    }
    
    //métodos set e get (para todos os atributos)
    public void setPermissao(int tmpPermissao){
        this.permissao = tmpPermissao;
    }
    
    public int getPermissao(){
        return this.permissao;
    }
    
    public void setNome(String tmpNome){
        this.nome = tmpNome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setLogin(String tmpLogin){
        this.login = tmpLogin;
    }
    
    public String getLogin(){
        return this.login;
    }
    
    public void setSenha(String tmpSenha){
        this.senha = tmpSenha;
    }
    
    public String getSenha(){
        return this.senha;
    }
    
    public void setImagem(String tmpImagem){
        this.imagem = tmpImagem;
    }
   
    public String getImagem(){
        return this.imagem;
    }
    
} //fechando classe
