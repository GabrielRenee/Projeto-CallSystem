
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

public class ClientesView extends JInternalFrame implements ActionListener {

    //declaração de objetos
    public static JMenuBar mbrClientes;
    public static JMenu mnuArquivo, mnuDados, mnuAjuda;
    public static JMenuItem mniNovo, mniEditar, mniSalvar,
            mniExcluir, mniFechar,
            mniConsulta, mniDesativar,
            mniSobre;

    public static Container ctnClientes;

    public static JToolBar tbrClientes;
    public static ImageIcon icnNovo, icnEditar,
            icnSalvar, icnDesativar,
            icnBuscar, icnExcluir,
            icnImprimir, icnFechar;
    public static BotoesBarra bbrNovo, bbrEditar,
            bbrSalvar, bbrDesativar,
            bbrBuscar, bbrExcluir,
            bbrImprimir, bbrFechar;

    public static String strCampos[] = {"Id:", "Empresa:", "Contato:", "Cargo:", "Endereço:", "Cidade:", "Estado:", "CEP:", "Pais:", "Telefone:", "E-mail:"};
    public static JLabel lblCampos[];
    public static JTextField txtCampos[];

    public static ImageIcon imgFoto;
    public static JLabel lblFoto;

    public static String strTopo[] = {"ID", "Nome", "Cidade", "Telefone"};
    public static JScrollPane scrClientes; //barra de rolagem da tabela
    public static JTable tblClientes;//tabela
    public static DefaultTableModel mdlClientes;//Classe que gerencia o conteudo da tabela

    public static JLabel lblBusca;
    public static JTextField txtBusca;
    public static JButton btnBusca;

    public static JButton btnNovo, btnSalvar, btnDesativar, btnFoto, btnEditar;
    public static ImageIcon icnPais, icnUsuario, icnRestaurar, icnBloquear;
    public static JButton btnCidade, btnNome, btnRestaurar;

    //Declaração de variaveis e objetos auxiliares
    public static FileChannel flcOrigem, flcDestino;//cópia
    public static FileInputStream flsEntrada;//leitura
    public static FileOutputStream flsSaida;//leitura
    public static String strCaminhoOrigem, strCaminhoDestino, strNomeArquivoOrigem, extensao;
    public static int statusFoto;
    public static int statusAtual = 0, acao;
    public static boolean status;

    public static java.util.List<ClientesVO> lstClientes = new ArrayList<ClientesVO>();

    public ClientesView() {//construtor
        super("Gerenciamento de Clientes");

        //Construção dos objetos
        icnNovo = new ImageIcon("img/icons/new.png");
        icnEditar = new ImageIcon("img/icons/edit.png");
        icnSalvar = new ImageIcon("img/icons/save.png");
        icnDesativar = new ImageIcon("img/icons/block.png");
        icnBuscar = new ImageIcon("img/icons/search.png");
        icnExcluir = new ImageIcon("img/icons/delete.png");
        icnImprimir = new ImageIcon("img/icons/report.png");
        icnFechar = new ImageIcon("img/icons/exit.png");

        mbrClientes = new JMenuBar();
        this.setJMenuBar(mbrClientes);

        mnuArquivo = new JMenu("Arquivo");
        mnuArquivo.setMnemonic('a');
        mbrClientes.add(mnuArquivo);

        mniNovo = new JMenuItem("Novo Cliente", icnNovo);
        mniNovo.addActionListener(this);
        mnuArquivo.add(mniNovo);

        mnuArquivo.add(new JSeparator());

        mniEditar = new JMenuItem("Editar Dados", icnEditar);
        mniEditar.setEnabled(false);
        mniEditar.addActionListener(this);
        mnuArquivo.add(mniEditar);

        mniSalvar = new JMenuItem("Salvar Informações", icnSalvar);
        mniSalvar.addActionListener(this);
        mnuArquivo.add(mniSalvar);

        mnuArquivo.add(new JSeparator());

        mniExcluir = new JMenuItem("Excluir cliente atual", icnExcluir);
        mniExcluir.setEnabled(false);
        mniExcluir.addActionListener(this);
        mnuArquivo.add(mniExcluir);

        mnuArquivo.add(new JSeparator());

        mniFechar = new JMenuItem("Fechar módulo CLIENTES", icnFechar);
        mniFechar.addActionListener(this);
        mnuArquivo.add(mniFechar);

        mnuDados = new JMenu("Dados");
        mnuDados.setMnemonic('d');
        mbrClientes.add(mnuDados);

        mniConsulta = new JMenuItem("Consulta por CPF", icnBuscar);
        mniConsulta.addActionListener(this);
        mnuDados.add(mniConsulta);

        mnuDados.add(new JSeparator());

        mniDesativar = new JMenuItem("Alterar Status", icnBloquear);
        mniDesativar.setEnabled(false);
        mniDesativar.addActionListener(this);
        mnuDados.add(mniDesativar);

        mnuAjuda = new JMenu("Ajuda");
        mnuAjuda.setMnemonic('h');
        mbrClientes.add(mnuAjuda);

        mniSobre = new JMenuItem("Sobre o sistema");
        mniSobre.addActionListener(this);
        mnuAjuda.add(mniSobre);

        ctnClientes = new Container();
        ctnClientes.setLayout(null);
        this.add(ctnClientes);

        tbrClientes = new JToolBar();
        tbrClientes.setBounds(0, 0, 285, 35);
        ctnClientes.add(tbrClientes);

        /**
         * ******************************BARRA DE
         * FERRAMENTAS*****************************
         */
        bbrNovo = new BotoesBarra(0, icnNovo, "Novo Cliente");
        tbrClientes.add(bbrNovo);

        bbrEditar = new BotoesBarra(1, icnEditar, "Editar Dados");
        bbrEditar.setEnabled(false);
        tbrClientes.add(bbrEditar);

        bbrSalvar = new BotoesBarra(2, icnSalvar, "Salvar Informações");
        tbrClientes.add(bbrSalvar);

        bbrDesativar = new BotoesBarra(3, icnDesativar, "Alterar Status");
        bbrDesativar.setEnabled(false);
        tbrClientes.add(bbrDesativar);

        bbrBuscar = new BotoesBarra(4, icnBuscar, "Buscar por CPF");
        tbrClientes.add(bbrBuscar);

        bbrExcluir = new BotoesBarra(5, icnExcluir, "Remover cliente atual");
        bbrExcluir.setEnabled(false);
        tbrClientes.add(bbrExcluir);

        bbrImprimir = new BotoesBarra(6, icnImprimir, "Gerar arquivo texto");
        tbrClientes.add(bbrImprimir);

        bbrFechar = new BotoesBarra(7, icnFechar, "Fechar módulo");
        tbrClientes.add(bbrFechar);

        lblCampos = new JLabel[strCampos.length];
        txtCampos = new JTextField[strCampos.length];

        for (int i = 0; i < lblCampos.length; i++) {
            lblCampos[i] = new JLabel(strCampos[i]);
            lblCampos[i].setBounds(30, 75 + (i * 30), 150, 20);
            ctnClientes.add(lblCampos[i]);

            txtCampos[i] = new JTextField();
            txtCampos[i].setBounds(160, 75 + (i * 30), 240, 20);
            ctnClientes.add(txtCampos[i]);

        }//fechando for

        btnEditar = new JButton("Editar Dados");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(this);
        btnEditar.setBounds(250, 415, 150, 30);
        ctnClientes.add(btnEditar);

        imgFoto = new ImageIcon("img/user.png");
        lblFoto = new JLabel(imgFoto);
        lblFoto.setBounds(440, 75, 128, 128);
        ctnClientes.add(lblFoto);

        btnFoto = new JButton("Selecionar imagem");
        btnFoto.addActionListener(this);
        btnFoto.setBounds(430, 215, 150, 20);
        ctnClientes.add(btnFoto);

        tblClientes = new JTable();
        scrClientes = new JScrollPane(tblClientes);
        mdlClientes = (DefaultTableModel) tblClientes.getModel();

        //Inserindo elementos no topo da tabela
        for (int i = 0; i < strTopo.length; i++) {
            mdlClientes.addColumn(strTopo[i]);
        }

        scrClientes.setBounds(600, 105, 550, 290);
        ctnClientes.add(scrClientes);

        /*CARREGAR DADOS NA TABELA*/
        carregarClientes(1, "");

        tblClientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                String idCliente = tblClientes.getValueAt(tblClientes.getSelectedRow(), 0).toString();

                try {
                    carregarCampos(ClientesDAO.consultarCliente(idCliente));
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        );

        lblBusca = new JLabel("Busca Rápida:");
        lblBusca.setBounds(600, 75, 100, 20);
        ctnClientes.add(lblBusca);

        txtBusca = new JTextField();
        txtBusca.setBounds(690, 75, 450, 20);
        ctnClientes.add(txtBusca);

        txtBusca.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {

                String tmpBusca = txtBusca.getText();

                try {
                    carregarClientes(2, tmpBusca);
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
        );

        btnNovo = new JButton("Novo Cliente", icnNovo);
        btnNovo.addActionListener(this);
        btnNovo.setBounds(430, 265, 150, 30);
        ctnClientes.add(btnNovo);

        btnSalvar = new JButton("Salvar dados", icnSalvar);
        btnSalvar.addActionListener(this);
        btnSalvar.setBounds(430, 315, 150, 30);
        ctnClientes.add(btnSalvar);

        icnBloquear = new ImageIcon("img/icons/block.png");

        btnDesativar = new JButton("Desativar", icnBloquear);
        btnDesativar.setEnabled(false);
        btnDesativar.addActionListener(this);
        btnDesativar.setBounds(430, 365, 150, 30);
        ctnClientes.add(btnDesativar);

        icnPais = new ImageIcon("img/icons/country.png");
        icnUsuario = new ImageIcon("img/icons/user.png");
        icnRestaurar = new ImageIcon("img/icons/restore.png");

        btnCidade = new JButton("por Cidade", icnPais);
        btnCidade.addActionListener(this);
        btnCidade.setBounds(1170, 105, 150, 30);
        ctnClientes.add(btnCidade);

        btnNome = new JButton("por Nome", icnUsuario);
        btnNome.addActionListener(this);
        btnNome.setBounds(1170, 155, 150, 30);
        ctnClientes.add(btnNome);

        btnRestaurar = new JButton("Restaurar", icnRestaurar);
        btnRestaurar.addActionListener(this);
        btnRestaurar.setBounds(1170, 205, 150, 30);
        ctnClientes.add(btnRestaurar);

        desbloquearCampos(false);

        this.setIconifiable(true);
        this.setClosable(true);
        this.setSize(1400, 550);
        this.show();

    }//fechando construtor

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnNovo || evt.getSource() == mniNovo) {
            acao = 1; //cadastro
            mniExcluir.setEnabled(false);
            bbrExcluir.setEnabled(false);
            btnEditar.setEnabled(false);
            bbrEditar.setEnabled(false);
            mniEditar.setEnabled(false);
            desbloquearCampos(true);
            btnDesativar.setEnabled(false);
            bbrDesativar.setEnabled(false);
            mniDesativar.setEnabled(false);
            limparCampos();

        } else if (evt.getSource() == btnEditar || evt.getSource() == mniEditar) {
            acao = 2;
            desbloquearCampos(true);
            txtCampos[0].setEditable(false);
            mniExcluir.setEnabled(false);
            bbrExcluir.setEnabled(false);
            btnEditar.setEnabled(false);
            mniEditar.setEnabled(false);
            bbrEditar.setEnabled(false);

        } else if (evt.getSource() == btnSalvar || evt.getSource() == mniSalvar) {
            status = validarCampos();

            if (acao == 1) { //CADASTRANDO

                if (status) {
                    ClientesVO tmpCliente = new ClientesVO();
                    tmpCliente.setId(txtCampos[0].getText());
                    tmpCliente.setNomeEmpresa(txtCampos[1].getText());
                    tmpCliente.setRepresentante(txtCampos[2].getText());
                    tmpCliente.setCargo(txtCampos[3].getText());
                    tmpCliente.setEndereco(txtCampos[4].getText());
                    tmpCliente.setCidade(txtCampos[5].getText());
                    tmpCliente.setEstado(txtCampos[6].getText());
                    tmpCliente.setCep(txtCampos[7].getText());
                    tmpCliente.setPais(txtCampos[8].getText());
                    tmpCliente.setTelefone(txtCampos[9].getText());
                    tmpCliente.setEmail(txtCampos[10].getText());

                    try {
                        ClientesDAO.cadastrarCliente(tmpCliente);
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                    }

                    carregarClientes(1, "");

                    JOptionPane.showMessageDialog(null, "Cliente " + tmpCliente.getNomeEmpresa() + " cadastrado!");

                    desbloquearCampos(false);
                    limparCampos();

                }

            } else if (acao == 2) { //EDITANDO

            }

        } else if (evt.getSource() == btnFoto) {

            JFileChooser flcFoto = new JFileChooser("C:\\Users\\280104398\\Documents");
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos de imagem(*.png, *.jpg)", "png", "jpg");
            flcFoto.setFileFilter(filtro);//vinculando chooser ao filtro
            statusFoto = flcFoto.showOpenDialog(this);//abre o explorer

            //preview da imagem
            strCaminhoOrigem = flcFoto.getSelectedFile().getPath();
            strNomeArquivoOrigem = flcFoto.getSelectedFile().getName();
            lblFoto.setIcon(new ImageIcon(strCaminhoOrigem));

        } else if (evt.getSource() == btnDesativar || evt.getSource() == mniDesativar) {

        } else if (evt.getSource() == btnCidade) {
            String tmpBusca = JOptionPane.showInputDialog("Entre com o nome da cidade:");
            carregarClientes(4, tmpBusca);

        } else if (evt.getSource() == btnNome) {
            String tmpBusca = JOptionPane.showInputDialog("Entre com o nome do cliente:");
            carregarClientes(3, tmpBusca);
            
        }else if (evt.getSource() == btnRestaurar){
            carregarClientes(1, "");
        }

    }//fechando actionPerformed

    public static void desbloquearCampos(boolean tmpStatus) {
        for (int i = 0; i < txtCampos.length; i++) {
            txtCampos[i].setEditable(tmpStatus);
        }
        btnFoto.setEnabled(tmpStatus);
        btnDesativar.setEnabled(tmpStatus);
        mniDesativar.setEnabled(tmpStatus);
        bbrDesativar.setEnabled(tmpStatus);
        btnSalvar.setEnabled(tmpStatus);
        mniSalvar.setEnabled(tmpStatus);
        bbrSalvar.setEnabled(tmpStatus);
        btnNovo.setEnabled(!tmpStatus);
        mniNovo.setEnabled(!tmpStatus);
        bbrNovo.setEnabled(!tmpStatus);
    }

    public static void limparCampos() {
        for (int i = 0; i < txtCampos.length; i++) {
            txtCampos[i].setText(null);
        }
        lblFoto.setIcon(new ImageIcon("img/user.png"));
    }

    public static boolean validarCampos() {

        for (int i = 0; i < txtCampos.length; i++) {
            if (txtCampos[i].getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Preencha o campo " + strCampos[i]);
                txtCampos[i].grabFocus();//move o cursor pro campo espec.
                return false;
            }//fechando if
        }//fechando for                
        return true;
    }//fechando validar

    public static void carregarClientes(int tmpTipo, String tmpBusca) {

        while (mdlClientes.getRowCount() > 0) {
            mdlClientes.removeRow(0);
        }

        try {
            lstClientes = ClientesDAO.listarClientes(tmpTipo, tmpBusca);

            for (ClientesVO tmpCliente : lstClientes) {
                String dados[] = new String[4];

                dados[0] = tmpCliente.getId();
                dados[1] = tmpCliente.getNomeEmpresa();
                dados[2] = tmpCliente.getCidade();
                dados[3] = tmpCliente.getTelefone();

                mdlClientes.addRow(dados);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//fechando carregarClientes

    public static void carregarCampos(ClientesVO tmpCliente) {

        try {
            txtCampos[0].setText(tmpCliente.getId());
            txtCampos[1].setText(tmpCliente.getNomeEmpresa());
            txtCampos[2].setText(tmpCliente.getRepresentante());
            txtCampos[3].setText(tmpCliente.getCargo());
            txtCampos[4].setText(tmpCliente.getEndereco());
            txtCampos[5].setText(tmpCliente.getCidade());
            txtCampos[6].setText(tmpCliente.getEstado());
            txtCampos[7].setText(tmpCliente.getCep());
            txtCampos[8].setText(tmpCliente.getPais());
            txtCampos[9].setText(tmpCliente.getTelefone());
            txtCampos[10].setText(tmpCliente.getEmail());

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

}//fechando classe
