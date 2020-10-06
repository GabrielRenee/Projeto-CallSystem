import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends JInternalFrame implements ActionListener{
    
    public static Container ctnLogin;    
    public static ImageIcon imgLogin;
    public static JLabel lblBanner, lblUsuario, lblSenha;
    public static JTextField txtUsuario;
    public static JPasswordField pwdSenha;
    public static JButton btnAcesso;
    
    public static Font fntLabel, fntTexto;
    
    
    public LoginView(){
        super("Login - Acesso ao sistema");
        
        ctnLogin = new Container();
        ctnLogin.setLayout(null);
        this.add(ctnLogin);
        
        imgLogin = new ImageIcon("img/icons/bannerLogin.png");
        lblBanner = new JLabel(imgLogin);
        lblBanner.setBounds(0,0,450,75);
        ctnLogin.add(lblBanner);
        
        fntLabel = new Font("Verdana", Font.BOLD, 20);
        fntTexto = new Font("Verdana", Font.PLAIN, 20);
        
        lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(fntLabel);
        lblUsuario.setBounds(15,100,250,30);
        ctnLogin.add(lblUsuario);
        
        lblSenha = new JLabel("Senha:");
        lblSenha.setFont(fntLabel);
        lblSenha.setBounds(15,150,250,30);
        ctnLogin.add(lblSenha);
        
        txtUsuario = new JTextField("administrador");
        txtUsuario.setFont(fntTexto);
        txtUsuario.setBounds(120,100,290,30);
        ctnLogin.add(txtUsuario);
        
        pwdSenha = new JPasswordField();
        pwdSenha.setFont(fntTexto);
        pwdSenha.setBounds(120,150,290,30);
        ctnLogin.add(pwdSenha);
        
        btnAcesso = new JButton("Acessar Sistema");
        btnAcesso.addActionListener(this);
        btnAcesso.setBounds(15,200,395,35);
        ctnLogin.add(btnAcesso);
        
        this.setResizable(false);
        this.setLocation(700,150);
        this.setIconifiable(true);
        this.setSize(450,300);
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == btnAcesso){
            
            if(validarCampos()){ //verificar se campos foram preenchidos
                
                try{
                    String tmpLogin = txtUsuario.getText();
                    String tmpSenha = pwdSenha.getText();
                    
                   SistemaControl.perfilUsuario = UsuariosDAO.validarUsuario(tmpLogin, tmpSenha);
                    
                    if(SistemaControl.perfilUsuario == null){
                        JOptionPane.showMessageDialog(null, "Dados Inválidos!", "LOGIN", JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Acesso ao Sistema: " + SistemaControl.perfilUsuario.getNome());
                        
                        this.dispose(); //fechando tela de login
                        SistemaView.habilitarBotoes(true);
                        
                        String tmpPermissao = "Administrador";
                        
                        if(SistemaControl.perfilUsuario.getPermissao() == 2){
                            SistemaView.btnModulos[6].setEnabled(false);
                            SistemaView.btnModulos[8].setEnabled(false);
                            tmpPermissao = "Usuário Comum";
                        }
                        
                        SistemaView.lblLogin.setText(SistemaView.lblLogin.getText() + SistemaControl.perfilUsuario.getLogin());
                        SistemaView.lblNome.setText(SistemaView.lblNome.getText() + SistemaControl.perfilUsuario.getNome());
                        SistemaView.lblPermissao.setText(SistemaView.lblPermissao.getText() + tmpPermissao);
                        
                    }
                    
                    
                }catch(Exception erro){
                    JOptionPane.showMessageDialog(null, erro.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
                }
                
            }
            
            
        }
        
    }//fechando actPerf
    
    public static boolean validarCampos(){
        
        String tmpLogin = txtUsuario.getText().trim();
        String tmpSenha = pwdSenha.getText().trim();
        
        if(tmpLogin.compareTo("") == 0){
            JOptionPane.showMessageDialog(null, "Preencha o nome de usuário!","LOGIN",JOptionPane.WARNING_MESSAGE);
            txtUsuario.grabFocus(); //move o cursos para o campo
            return false;
        }else if(tmpSenha.compareTo("") == 0){
            JOptionPane.showMessageDialog(null, "Preencha a senha!","LOGIN",JOptionPane.WARNING_MESSAGE);
            pwdSenha.grabFocus();
            return false;
        }else{
            return true;
        }
        
    } //fechando validarCampos
    
}//fechando class
