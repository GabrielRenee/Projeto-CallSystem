
import java.io.*;
import java.nio.file.*;
import java.nio.channels.*;
import javax.swing.filechooser.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class ChamadosView extends JInternalFrame {

    public static String strCampos[] = {"ID", "Titulo:", "Data de Abertura:", "Responsável:", "Cód. Cliente:", "Nome do Cliente:", "Categoria:"};
    public static JLabel lblCampos[], lblIdCli, lblIdCham;
    public static JTextField txtCampos[], txtIdCli, txtIdCham;
    public static JLabel lblDescricao, lblSolucao;
    public static JComboBox cmbTipos;
    public static JTextArea txaDescricao, txaSolucao;
    public static Container ctnChamados;
    public static JButton btnRegistrar, btnEncerrar, btnFiltrar;
    public static JTable tblChamados;
    public static JScrollPane scrChamados;
    public static DefaultTableModel mdlChamados;
    public static String strTopo[] = {"ID", "Titulo", "Cliente", "Categoria"};

    public ChamadosView() {

        super("Gerenciamento de Chamados");

        ctnChamados = new Container();
        ctnChamados.setLayout(null);
        this.add(ctnChamados);

        btnRegistrar = new JButton("Registrar Solicitação");
        btnRegistrar.setEnabled(false);
        btnRegistrar.setBounds(30, 460, 370, 30);
        ctnChamados.add(btnRegistrar);

        btnEncerrar = new JButton("Encerrar Chamado");
        btnEncerrar.setEnabled(false);
        btnEncerrar.setBounds(450, 460, 350, 30);
        ctnChamados.add(btnEncerrar);

        lblDescricao = new JLabel("Descrição do Problema:");
        lblDescricao.setBounds(30, 265, 150, 20);
        ctnChamados.add(lblDescricao);

        txaDescricao = new JTextArea();
        txaDescricao.setBounds(30, 295, 370, 150);
        ctnChamados.add(txaDescricao);

        lblSolucao = new JLabel("Solução");
        lblSolucao.setBounds(460, 30, 200, 20);
        ctnChamados.add(lblSolucao);

        txaSolucao = new JTextArea();
        txaSolucao.setBounds(450, 55, 350, 390);
        ctnChamados.add(txaSolucao);

        lblIdCli = new JLabel("Filtrar por Cliente:");
        lblIdCli.setBounds(820, 45, 150, 20);
        ctnChamados.add(lblIdCli);

        txtIdCli = new JTextField();
        txtIdCli.setBounds(925, 45, 100, 20);
        ctnChamados.add(txtIdCli);

        lblIdCham = new JLabel("Buscar por ID:");
        lblIdCham.setBounds(1050, 45, 150, 20);
        ctnChamados.add(lblIdCham);

        txtIdCham = new JTextField();
        txtIdCham.setBounds(1135, 45, 55, 20);
        ctnChamados.add(txtIdCham);

        btnFiltrar = new JButton("Buscar");
        btnFiltrar.setBounds(1200, 45, 110, 20);
        ctnChamados.add(btnFiltrar);

        tblChamados = new JTable();
        scrChamados = new JScrollPane(tblChamados);
        mdlChamados = (DefaultTableModel) tblChamados.getModel();

        for (int i = 0; i < strTopo.length; i++) {
            mdlChamados.addColumn(strTopo[i]);
        }

        scrChamados.setBounds(820, 75, 490, 415);
        ctnChamados.add(scrChamados);

        lblCampos = new JLabel[strCampos.length];
        txtCampos = new JTextField[strCampos.length - 1];
        for (int i = 0; i < lblCampos.length; i++) {
            lblCampos[i] = new JLabel(strCampos[i]);
            lblCampos[i].setBounds(30, 55 + (i * 30), 150, 20);
            ctnChamados.add(lblCampos[i]);

            if (i < lblCampos.length - 1) {
                txtCampos[i] = new JTextField();
                txtCampos[i].setBounds(160, 55 + (i * 30), 240, 20);
                ctnChamados.add(txtCampos[i]);
            }

            if (i == 3) { //carregando responsavel
                txtCampos[i].setText(SistemaControl.perfilUsuario.getLogin());
                txtCampos[i].setEditable(false);
            }

        }

        cmbTipos = new JComboBox(carregarTipos());
        cmbTipos.setBounds(160, 235, 240, 20);
        ctnChamados.add(cmbTipos);

        this.setClosable(true);
        this.setSize(1350, 540);
        this.show();

    }//fechando construtor
    
    public static String[] carregarTipos(){
        
        String[] tipos = null;
        try{
            
            java.util.List<TiposVO> lstTipos = TiposDAO.listarTipos();
            tipos = new String[lstTipos.size()];
            int i=0;
            
            for(TiposVO tmpTipo: lstTipos){
                tipos[i] = tmpTipo.getId() + " - " + tmpTipo.getDescricao();
                i++;
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        
        return tipos;
    }

}//fechando class
