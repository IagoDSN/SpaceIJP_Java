package janelas;

import Script.UsuarioDAO;
import Script.UsuarioJA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.math.BigDecimal;

public class PrimeiraVezFrame extends JDialog {

    private UsuarioJA usuario;
    private UsuarioDAO usuarioDao;

    private JLabel lblMensagem;
    private JLabel lblImagem;
    private JTextField txtNome;
    private JButton btnConfirmar;

    public PrimeiraVezFrame(Frame parent, UsuarioJA usuario, UsuarioDAO usuarioDao) {
        super(parent, "Bem-vindo ao Space IJP!", true); // modal
        this.usuario = usuario;
        this.usuarioDao = usuarioDao;

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // evita fechar pelo X
        setResizable(false);

        initComponents();
        pack();
        setLocationRelativeTo(parent);

        // Bloqueia o fechamento por ALT+F4 / Close - mostra aviso
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // não fecha - opcional: mostrar mensagem
                JOptionPane.showMessageDialog(PrimeiraVezFrame.this,
                    "Você precisa escolher um nome para continuar.",
                    "Ação necessária",
                    JOptionPane.WARNING_MESSAGE);
            }
        });

        // Desativa tecla ESC (evita fechar)
        String cancelName = "none";
        getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        getRootPane().getActionMap().put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                // não faz nada
            }
        });
    }

    private void initComponents() {
        // painel principal
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painel.setBackground(new Color(30, 58, 95));

        // Mensagem (lado esquerdo)
        lblMensagem = new JLabel("<html><div style='color:white; font-size:18px; line-height:1.4;'>\"\n" +
"        * \"<b>Olá, Comandante!</b><br><br>\"\n" +
"        * \"Você foi escolhido pelo <b>Programa Espacial IJP</b> para liderar sua própria base de lançamentos!<br><br>\"\n" +
"        * \"O grande <b>financiador galáctico</b> decidiu investir em seu potencial e liberou um crédito inicial de \n <b style='color:#00FF88;'>$200.000</b> para começar sua jornada rumo às estrelas.<br><br>\"\n" +
"        * \"Use esse valor com sabedoria para comprar seu primeiro <b>foguete</b>, montar sensores e iniciar suas operações espaciais.<br><br>\"\n" +
"        * \"<b>Digite abaixo o nome que ficará registrado na história da exploração espacial!</b>\n </div></html>");
        lblMensagem.setVerticalAlignment(SwingConstants.BOTTOM);

        // Imagem (lado direito) - redimensiona
        lblImagem = new JLabel();
        carregarImagem("/imagens/Financiador.png", 100, 250); // ajuste caminho/tamanho se precisar

        // Painel inferior com texto + imagem (lado a lado)
        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setOpaque(false);
        painelInferior.add(lblMensagem, BorderLayout.CENTER);

        JPanel painelImagemWrap = new JPanel(new BorderLayout());
        painelImagemWrap.setOpaque(false);
        painelImagemWrap.add(lblImagem, BorderLayout.SOUTH); // imagem em baixo do lado direito
        painelInferior.add(painelImagemWrap, BorderLayout.EAST);

        painel.add(Box.createVerticalGlue(), BorderLayout.CENTER); // empurra para baixo
        painel.add(painelInferior, BorderLayout.SOUTH);

        // Painel de input (ocupa a parte de baixo inteira)
        JPanel painelInput = new JPanel(new BorderLayout(10, 10));
        painelInput.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        painelInput.setOpaque(false);

        txtNome = new JTextField();
        txtNome.setColumns(20);
        txtNome.setFont(txtNome.getFont().deriveFont(14f));
        txtNome.setToolTipText("Digite seu nome de comandante");

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(e -> confirmarNome());

        // Permitir Enter no campo para confirmar
        txtNome.addActionListener(e -> confirmarNome());

        painelInput.add(txtNome, BorderLayout.CENTER);
        painelInput.add(btnConfirmar, BorderLayout.EAST);

        // Colocar o painelInput logo acima do rodapé (assim fica junto com o conteúdo inferior)
        JPanel sul = new JPanel(new BorderLayout());
        sul.setOpaque(false);
        sul.add(painelInput, BorderLayout.NORTH);

        // Combina tudo
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(painel, BorderLayout.CENTER);
        getContentPane().add(sul, BorderLayout.SOUTH);
    }

    private void carregarImagem(String resourcePath, int width, int height) {
        try {
            URL url = getClass().getResource(resourcePath);
            ImageIcon icon;
            if (url != null) {
                icon = new ImageIcon(url);
            } else {
                // fallback para caminho relativo em desenvolvimento
                icon = new ImageIcon("src/imgs/Financiador.png");
            }
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            lblImagem.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void confirmarNome() {
        String nome = txtNome.getText();
        if (nome == null) nome = "";
        nome = nome.trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um nome válido.", "Nome inválido", JOptionPane.WARNING_MESSAGE);
            txtNome.requestFocus();
            return;
        }

        // Salva somente aqui — depois de nome válido
        usuario.setNomeUsuario(nome);
        usuario.setPrimeiraVez(1);
        // Se estiver usando BigDecimal para dinheiro e quiser dar quantia inicial, faça aqui:
        if (usuario.getDinheiro() == null || usuario.getDinheiro().compareTo(BigDecimal.ZERO) == 0) {
            usuario.setDinheiro(BigDecimal.valueOf(200000.00)); // opcional
        }

        // atualiza no BD (assegure que atualizarUsuario faz preparedstatement correto)
        usuarioDao.alterar(usuario);

        JOptionPane.showMessageDialog(this, "Bem-vindo, " + nome + "!");
        dispose(); // fecha o diálogo — o chamador (Main) pode prosseguir
    }
}
