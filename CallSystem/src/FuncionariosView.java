
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.filechooser.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionariosView extends JInternalFrame implements ActionListener {

    public static Container ctnFuncionarios;

    public static String strCampos[] = {"Id:", "Sobrenome:", "Nome", "Cargo:",
        "Endereço:", "Cidade:", "Estado:", "CEP:", "Pais:", "Telefone:", "Extensão:"};

    public static JLabel lblCampos[];
    public static JTextField txtCampos[];

    public static ImageIcon icnNovo, icnEditar,
            icnSalvar, icnDesativar,
            icnBuscar, icnExcluir,
            icnImprimir, icnFechar;

    public static String strTopo[] = {"ID", "Nome", "Cidade", "Telefone"};
    public static JScrollPane scrFuncionarios;
    public static JTable tblFuncionarios;
    public static DefaultTableModel mdlFuncionarios;

    public static ImageIcon imgFoto;
    public static JLabel lblFoto;
    public static JDateChooser calDataNasc, calDataCon;
    public static JLabel lblCampos1, lblCampos2;

    public static JLabel lblBusca;
    public static JTextField txtBusca;
    public static JButton btnBusca;

    public static JButton btnNovo, btnSalvar, btnDesativar, btnFoto, btnEditar;
    public static ImageIcon icnPais, icnUsuario, icnRestaurar, icnBloquear;
    public static JButton btnCidade, btnNome, btnRestaurar;

    public static String strCaminhoOrigem, strCaminhoDestino, strNomeArquivoOrigem, extensao;
    public static int statusFoto;
    public static int statusAtual = 0, acao;
    public static boolean status;
    public static SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static SimpleDateFormat f2 = new SimpleDateFormat("dd/MM/yyyy");

    public static java.util.List<FuncionariosVO> lstFuncionarios = new ArrayList<FuncionariosVO>();

    public FuncionariosView() {
        super("Gerenciamento de Funcionários");

        icnNovo = new ImageIcon("img/icons/new.png");
        icnEditar = new ImageIcon("img/icons/edit.png");
        icnSalvar = new ImageIcon("img/icons/save.png");
        icnDesativar = new ImageIcon("img/icons/block.png");
        icnBuscar = new ImageIcon("img/icons/search.png");
        icnExcluir = new ImageIcon("img/icons/delete.png");
        icnImprimir = new ImageIcon("img/icons/report.png");
        icnFechar = new ImageIcon("img/icons/exit.png");

        ctnFuncionarios = new Container();
        ctnFuncionarios.setLayout(null);
        this.add(ctnFuncionarios);

        tblFuncionarios = new JTable();
        scrFuncionarios = new JScrollPane(tblFuncionarios);
        mdlFuncionarios = (DefaultTableModel) tblFuncionarios.getModel();

        lblCampos = new JLabel[strCampos.length];
        txtCampos = new JTextField[strCampos.length];

        for (int i = 0; i < lblCampos.length; i++) {
            lblCampos[i] = new JLabel(strCampos[i]);
            lblCampos[i].setBounds(30, 30 + (i * 30), 150, 20);
            ctnFuncionarios.add(lblCampos[i]);

            txtCampos[i] = new JTextField();
            txtCampos[i].setBounds(160, 30 + (i * 30), 240, 20);
            ctnFuncionarios.add(txtCampos[i]);

        }//fechando for

        lblCampos1 = new JLabel("Data de nascimento");
        lblCampos1.setBounds(30, 30 + (11 * 30), 150, 20);
        ctnFuncionarios.add(lblCampos1);

        calDataNasc = new JDateChooser();
        calDataNasc.setBounds(160, 360, 240, 20);
        ctnFuncionarios.add(calDataNasc);

        lblCampos2 = new JLabel("Data de contratação");
        lblCampos2.setBounds(30, 30 + (12 * 30), 150, 20);
        ctnFuncionarios.add(lblCampos2);

        calDataCon = new JDateChooser();
        calDataCon.setBounds(160, 390, 240, 20);
        ctnFuncionarios.add(calDataCon);

        btnEditar = new JButton("Editar Dados");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(this);
        btnEditar.setBounds(250, 450, 150, 30);
        ctnFuncionarios.add(btnEditar);

        imgFoto = new ImageIcon("img/user.png");
        lblFoto = new JLabel(imgFoto);
        lblFoto.setBounds(440, 75, 128, 128);
        ctnFuncionarios.add(lblFoto);

        btnFoto = new JButton("Selecionar imagem");
        btnFoto.setBounds(430, 215, 150, 20);
        btnFoto.addActionListener(this);
        ctnFuncionarios.add(btnFoto);
        for (int i = 0; i < strTopo.length; i++) {
            mdlFuncionarios.addColumn(strTopo[i]);
        }
        scrFuncionarios.setBounds(600, 105, 550, 290);
        ctnFuncionarios.add(scrFuncionarios);

        carregarFuncionarios(1, "");

        tblFuncionarios.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {

                String idFuncionarios = tblFuncionarios.getValueAt(tblFuncionarios.getSelectedRow(), 0).toString();

                try {
                    carregarCampos(FuncionariosDAO.consultarFuncionarios(idFuncionarios));
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        lblBusca = new JLabel("Busca Rápida:");
        lblBusca.setBounds(600, 75, 100, 20);
        ctnFuncionarios.add(lblBusca);

        txtBusca = new JTextField();
        txtBusca.setBounds(690, 75, 450, 20);
        ctnFuncionarios.add(txtBusca);
        txtBusca.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {

                String tmpBusca = txtBusca.getText();

                try {
                    carregarFuncionarios(2, tmpBusca);
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btnNovo = new JButton("Novo Cliente", icnNovo);
        btnNovo.addActionListener(this);
        btnNovo.setBounds(430, 265, 150, 30);
        ctnFuncionarios.add(btnNovo);

        btnSalvar = new JButton("Salvar dados", icnSalvar);
        btnSalvar.addActionListener(this);
        btnSalvar.setBounds(430, 315, 150, 30);
        ctnFuncionarios.add(btnSalvar);

        icnBloquear = new ImageIcon("img/icons/block.png");

        btnDesativar = new JButton("Desativar", icnBloquear);
        btnDesativar.setEnabled(false);
        btnDesativar.addActionListener(this);
        btnDesativar.setBounds(430, 365, 150, 30);
        ctnFuncionarios.add(btnDesativar);

        icnPais = new ImageIcon("img/icons/country.png");
        icnUsuario = new ImageIcon("img/icons/user.png");
        icnRestaurar = new ImageIcon("img/icons/restore.png");

        btnCidade = new JButton("por Cidade", icnPais);
        btnCidade.addActionListener(this);
        btnCidade.setBounds(1170, 105, 150, 30);
        ctnFuncionarios.add(btnCidade);

        btnNome = new JButton("por Nome", icnUsuario);
        btnNome.addActionListener(this);
        btnNome.setBounds(1170, 155, 150, 30);
        ctnFuncionarios.add(btnNome);

        btnRestaurar = new JButton("Restaurar", icnRestaurar);
        btnRestaurar.addActionListener(this);
        btnRestaurar.setBounds(1170, 205, 150, 30);
        ctnFuncionarios.add(btnRestaurar);

        desbloquearCampos(false);

        this.setIconifiable(true);
        this.setClosable(true);
        this.setSize(1400, 550);
        this.show();
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnNovo) {
            acao = 1; //cadastro
            btnEditar.setEnabled(false);
            desbloquearCampos(true);
            btnDesativar.setEnabled(false);
            limparCampos();
        } else if (evt.getSource() == btnEditar) {
            acao = 2;
            desbloquearCampos(true);
            txtCampos[0].setEditable(false);
            btnEditar.setEnabled(false);
        } else if (evt.getSource() == btnSalvar) {
            status = validarCampos();

            if (acao == 1) { //CADASTRANDO

                if (status) {
                    FuncionariosVO tmpFuncionarios = new FuncionariosVO();

                    txtCampos[0].setText(tmpFuncionarios.getId());
                    txtCampos[1].setText(tmpFuncionarios.getSobrenome());
                    txtCampos[2].setText(tmpFuncionarios.getNome());
                    txtCampos[3].setText(tmpFuncionarios.getCargo());
                    txtCampos[4].setText(tmpFuncionarios.getEndereco());
                    txtCampos[5].setText(tmpFuncionarios.getCidade());
                    txtCampos[6].setText(tmpFuncionarios.getEstado());
                    txtCampos[7].setText(tmpFuncionarios.getCep());
                    txtCampos[8].setText(tmpFuncionarios.getPais());
                    txtCampos[9].setText(tmpFuncionarios.getTelefone());
                    txtCampos[10].setText(tmpFuncionarios.getExtensao());
                    txtCampos[11].setText(tmpFuncionarios.getObservacao());
                    try {
                        calDataNasc.setDate(f2.parse(f1.format(tmpFuncionarios.getDataNasc())));
                    } catch (ParseException ex) {
                        Logger.getLogger(FuncionariosView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        calDataCon.setDate(f2.parse(f1.format(tmpFuncionarios.getDataCon())));
                    } catch (ParseException ex) {
                        Logger.getLogger(FuncionariosView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        FuncionariosDAO.cadastrarFuncionarios(tmpFuncionarios);
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
                    }

                    carregarFuncionarios(1, "");

                    JOptionPane.showMessageDialog(null, "Funcionário " + tmpFuncionarios.getNome() + " cadastrado!");

                    desbloquearCampos(false);

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
        } else if (evt.getSource() == btnDesativar) {

        } else if (evt.getSource() == btnCidade) {
            String tmpBusca = JOptionPane.showInputDialog("Entre com o nome da cidade:");
            carregarFuncionarios(4, tmpBusca);
        } else if (evt.getSource() == btnNome) {
            String tmpBusca = JOptionPane.showInputDialog("Entre com o nome do funcionário:");
            carregarFuncionarios(3, tmpBusca);
        } else if (evt.getSource() == btnRestaurar) {
            carregarFuncionarios(1, "");
        }
    }

    public static void desbloquearCampos(boolean tmpStatus) {
        for (int i = 0; i < txtCampos.length; i++) {
            txtCampos[i].setEditable(tmpStatus);
        }
        calDataCon.setEnabled(tmpStatus);
        calDataNasc.setEnabled(tmpStatus);
        btnFoto.setEnabled(tmpStatus);
        btnDesativar.setEnabled(tmpStatus);
        btnSalvar.setEnabled(tmpStatus);
        btnNovo.setEnabled(!tmpStatus);

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

    public static void carregarFuncionarios(int tmpTipo, String tmpBusca) {

        while (mdlFuncionarios.getRowCount() > 0) {
            mdlFuncionarios.removeRow(0);
        }

        try {
            lstFuncionarios = FuncionariosDAO.listarFuncionarios(tmpTipo, tmpBusca);

            for (FuncionariosVO tmpFuncionarios : lstFuncionarios) {
                String dados[] = new String[4];

                dados[0] = tmpFuncionarios.getId();
                dados[1] = tmpFuncionarios.getNome();
                dados[2] = tmpFuncionarios.getCidade();
                dados[3] = tmpFuncionarios.getTelefone();

                mdlFuncionarios.addRow(dados);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void carregarCampos(FuncionariosVO tmpFuncionarios) {

        try {

            txtCampos[0].setText(tmpFuncionarios.getId());
            txtCampos[1].setText(tmpFuncionarios.getSobrenome());
            txtCampos[2].setText(tmpFuncionarios.getNome());
            txtCampos[3].setText(tmpFuncionarios.getCargo());
            txtCampos[6].setText(tmpFuncionarios.getEndereco());
            txtCampos[7].setText(tmpFuncionarios.getCidade());
            txtCampos[8].setText(tmpFuncionarios.getEstado());
            txtCampos[9].setText(tmpFuncionarios.getCep());
            txtCampos[10].setText(tmpFuncionarios.getPais());
            txtCampos[11].setText(tmpFuncionarios.getTelefone());
            txtCampos[12].setText(tmpFuncionarios.getExtensao());
            txtCampos[13].setText(tmpFuncionarios.getObservacao());
            try {
                calDataNasc.setDate(f1.parse(f2.format(tmpFuncionarios.getDataNasc())));
            } catch (ParseException ex) {
                Logger.getLogger(FuncionariosView.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                calDataCon.setDate(f2.parse(f1.format(tmpFuncionarios.getDataCon())));
            } catch (ParseException ex) {
                Logger.getLogger(FuncionariosView.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

}
