package ClassesComuns;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AtorJogador extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected Gerenciador umGerenciador;
	protected AtorNetGames rede;
	protected JFrame frame;
	protected String nome;
	protected ImagemDaColmeia panel;
	protected Colmeia umaColmeia;
	protected String[][] tab;

	// construcao da interface grafica

	final static int BSIZE = 24; // tamanho do tabuleiro
	final static int HEXSIZE = 18; // hex size in pixels
	final static int BORDERS = 25;
	final static int SCRSIZE = HEXSIZE * (BSIZE + 1) + BORDERS * 2; // screen size
	protected ImageIcon imagemCapa;
	protected ImageIcon abelha;
	protected ImageIcon formiga;
	protected ImageIcon aranha;
	protected ImageIcon grilo;
	protected ImageIcon besouro;
	protected JButton bAbelha;
	protected JButton bFormiga0;
	protected JButton bFormiga1;
	protected JButton bFormiga2;
	protected JButton bAranha0;
	protected JButton bAranha1;
	protected JButton bGrilo0;
	protected JButton bGrilo1;
	protected JButton bGrilo2;
	protected JButton bBesouro0;
	protected JButton bBesouro1;
	protected JPanel armazemDePecas;

	public AtorJogador() {

		super();
		rede = new AtorNetGames(this);
		this.nome = JOptionPane.showInputDialog(null, "Digite seu nome:", "Hive", JOptionPane.QUESTION_MESSAGE);
		if (nome.isEmpty()) {
			this.nome = "";
		}
		this.umGerenciador = new Gerenciador(rede, this);
		this.umaColmeia = umGerenciador.getColmeia();

		// instanciar hexagonos
		tab = new String[BSIZE][BSIZE];
		for (int i = 0; i < BSIZE; i++) {
			for (int j = 0; j < BSIZE; j++) {
				tab[i][j] = "";
			}
		}

		iniciaJogo();
	}

	public AtorJogador(GraphicsConfiguration arg0) {
		super(arg0);
	}

	public AtorJogador(String arg0) {
		super(arg0);
	}

	public AtorJogador(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
	}

	public void iniciaJogo() {

		this.criarGUI();
	}

	protected void criarGUI() {
		this.panel = new ImagemDaColmeia(tab, rede, umGerenciador, this);

		bAbelha = new JButton("abelha"); // 1
		bAbelha.setActionCommand("inserir abelha");
		bAbelha.addActionListener(this);
		bAbelha.setEnabled(true);
		bFormiga0 = new JButton("formiga 1"); // 2
		bFormiga0.setActionCommand("inserir formiga 1");
		bFormiga0.addActionListener(this);
		bFormiga0.setEnabled(true);
		bFormiga1 = new JButton("formiga 2"); // 3
		bFormiga1.setActionCommand("inserir formiga 2");
		bFormiga1.addActionListener(this);
		bFormiga1.setEnabled(true);
		bFormiga2 = new JButton("formiga 3"); // 4
		bFormiga2.setActionCommand("inserir formiga 3");
		bFormiga2.addActionListener(this);
		bFormiga2.setEnabled(true);
		bAranha0 = new JButton("aranha 1"); // 5
		bAranha0.setActionCommand("inserir aranha 1");
		bAranha0.addActionListener(this);
		bAranha0.setEnabled(true);
		bAranha1 = new JButton("aranha 2"); // 6
		bAranha1.setActionCommand("inserir aranha 2");
		bAranha1.addActionListener(this);
		bAranha1.setEnabled(true);
		bGrilo0 = new JButton("grilo 1"); // 7
		bGrilo0.setActionCommand("inserir grilo 1");
		bGrilo0.addActionListener(this);
		bGrilo0.setEnabled(true);
		bGrilo1 = new JButton("grilo 2"); // 8
		bGrilo1.setActionCommand("inserir grilo 2");
		bGrilo1.addActionListener(this);
		bGrilo1.setEnabled(true);
		bGrilo2 = new JButton("grilo 3"); // 9
		bGrilo2.setActionCommand("inserir grilo 3");
		bGrilo2.addActionListener(this);
		bGrilo2.setEnabled(true);
		bBesouro0 = new JButton("besouro 1"); // 10
		bBesouro0.setActionCommand("inserir besouro 1");
		bBesouro0.addActionListener(this);
		bBesouro0.setEnabled(true);
		bBesouro1 = new JButton("besouro 2"); // 11
		bBesouro1.setActionCommand("inserir besouro 2");
		bBesouro1.addActionListener(this);
		bBesouro1.setEnabled(true);

		armazemDePecas = new JPanel(new FlowLayout());
		armazemDePecas.setBackground(new Color(255, 80, 60));
		armazemDePecas.setPreferredSize(new Dimension(160, 448));
		armazemDePecas.setLayout(new GridLayout(11, 1));
		armazemDePecas.add(bAbelha);
		armazemDePecas.add(bFormiga0);
		armazemDePecas.add(bFormiga1);
		armazemDePecas.add(bFormiga2);
		armazemDePecas.add(bAranha0);
		armazemDePecas.add(bAranha1);
		armazemDePecas.add(bGrilo0);
		armazemDePecas.add(bGrilo1);
		armazemDePecas.add(bGrilo2);
		armazemDePecas.add(bBesouro0);
		armazemDePecas.add(bBesouro1);
		this.add(armazemDePecas).isVisible();

		// construcao do menu

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Arquivo");
		JMenuItem fechar = new JMenuItem("Fechar");
		fechar.setActionCommand("fechar");
		fechar.addActionListener(this);
		fileMenu.add(fechar);
		JMenu editMenu = new JMenu("Opcoes");
		JMenuItem conectar = new JMenuItem("Conectar");
		conectar.setActionCommand("conectar");
		conectar.addActionListener(this);
		JMenuItem iniciarJogo = new JMenuItem("Iniciar Jogo");
		iniciarJogo.setActionCommand("iniciarPartida");
		iniciarJogo.addActionListener(this);
		JMenuItem desconectar = new JMenuItem("Desconectar");
		desconectar.setActionCommand("desconectar");
		desconectar.addActionListener(this);
		JMenuItem finalizar = new JMenuItem("Finalizar Partida");
		finalizar.setActionCommand("finalizar");
		finalizar.addActionListener(this);
		editMenu.add(conectar);
		editMenu.add(iniciarJogo);
		editMenu.add(desconectar);
		menuBar.add(fileMenu);
		menuBar.add(editMenu);

		// construcao do frame
		this.frame = new JFrame("Hive     " + "jogador: " + nome);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = frame.getContentPane();
		content.add(panel);
		frame.setJMenuBar(menuBar);
		content.add(armazemDePecas, BorderLayout.WEST);
		frame.setSize((int) (SCRSIZE * 2), (int) (SCRSIZE * 1.4));
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public Gerenciador getGerenciador() {
		return this.umGerenciador;
	}

	public ImagemDaColmeia getImagemDaColmeia() {

		return this.panel;
	}

	public void iniciarPartida() {
		if (umGerenciador.isPartidaEmAndamento()) {
			JOptionPane.showMessageDialog(null, "Ja existe partida em andamento");
		} else {
			if (!umGerenciador.isConectado()) {
				JOptionPane.showMessageDialog(null, "Voce nao esta conectado.");
			} else {
				umGerenciador.iniciarPartida();
				JOptionPane.showMessageDialog(null, this.getNome()
						+ " eh sua vez. Clique nas pecas do lado esquerdo e escolha a posicao no tabuleiro para inserir.");
			}
		}
	}

	public void iniciarPartidaRede(boolean comecoJogando) {

		String apelido2 = rede.obterNomeAdversario();

		if (comecoJogando) {
			Jogador jogador1 = new Jogador(1);
			jogador1.setApelido(nome);
			Jogador jogador2 = new Jogador(2);
			jogador2.setApelido(apelido2);
			umGerenciador.setJogador1(jogador1);
			umGerenciador.setJogador2(jogador2);

		} else {

			Jogador jogador1 = new Jogador(1);
			jogador1.setApelido(apelido2);
			Jogador jogador2 = new Jogador(2);
			jogador2.setApelido(nome);
			umGerenciador.setJogador1(jogador1);
			umGerenciador.setJogador2(jogador2);
		}

		umGerenciador.getJogador1().setDaVez(true);
		umGerenciador.getJogador2().setDaVez(false);
		umGerenciador.setConectado(true);
		umGerenciador.setPartidaEmAndamento(true);
		umGerenciador.setRodada(0);
	}

	public void modificaGrafico(int x, int y, String z) {

		panel.modificaGrafico(x, y, z);
	}

	public void receberMensagemRede(int x, int y, String z, int n) {

		panel.modificaGrafico(x, y, z);
		Peca p = new Peca();
		p.setTipo(z);
		p.setNroPeca(n);
		Jogador oponente;
		if (umGerenciador.jogadorDaVez().equals(umGerenciador.getJogador1())) {
			oponente = umGerenciador.getJogador2();
		} else {
			oponente = umGerenciador.getJogador1();
		}
		String corOp = oponente.getCor();
		p.setCor(corOp);
		umGerenciador.getColmeia().setPeca(x, y, p);
	}

	public void limparTab() {

		panel = new ImagemDaColmeia(tab, rede, umGerenciador, this);
	}

	public void conectar() {

		if (!umGerenciador.isConectado()) {
			String servidor = "127.0.0.1";
			servidor = JOptionPane.showInputDialog("Entre com o endereço do servidor", servidor);
			rede.conectar(nome, servidor);
			umGerenciador.setConectado(true);
			JOptionPane.showMessageDialog(null, "Voce se conectou");
		} else {
			JOptionPane.showMessageDialog(null, "Ja esta conectado.");
		}
	}

	protected void desconectar() {
		if (umGerenciador.isConectado()) {
			rede.desconectar();
			umGerenciador.setPartidaEmAndamento(false);
			umGerenciador.setConectado(false);
			JOptionPane.showMessageDialog(null, "Voce se desconectou");
		} else {
			JOptionPane.showMessageDialog(null, "Voce nao esta conectado");
		}
	}

	public String getNome() {
		return this.nome;
	}

	public void executarLance(int x, int y, int origX, int origY, int nroPeca) {
		String notif = this.umGerenciador.executarLance(x, y, origX, origY, nroPeca);

		JOptionPane.showMessageDialog(null, notif);
	}

	public void finalizarPartida() {
		String notif = "";
		notif = umGerenciador.finalizarPartida();
		JOptionPane.showMessageDialog(null, notif);

	}

	@Override
	public void actionPerformed(ActionEvent botaoPressionado) {
		String textoBotao = botaoPressionado.getActionCommand();
		if (textoBotao.equals("conectar")) {
			this.conectar();
		}

		else if (textoBotao.equals("desconectar")) {

			this.desconectar();
		} else if (textoBotao.equals("iniciarPartida")) {
			this.iniciarPartida();
		} else if (textoBotao.equals("reiniciar")) {
			Object[] options = { "Sim", "Nao" };
			JOptionPane.showOptionDialog(null, "Reiniciar o programa?", "Certeza?", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			if (options[0] == "Sim") {
				this.iniciarPartida();
			}
		}

		else if (textoBotao.equals("fechar")) {

			System.exit(0);
		} else if (textoBotao.equals("finalizar")) {

			this.finalizarPartida();
		}

		else if (textoBotao.equals("inserir abelha")) {
			this.getImagemDaColmeia().setBufferInt(0);
		}

		else if (textoBotao.equals("inserir formiga 1")) {
			this.getImagemDaColmeia().setBufferInt(1);
		}

		else if (textoBotao.equals("inserir formiga 2")) {
			this.getImagemDaColmeia().setBufferInt(2);
		}

		else if (textoBotao.equals("inserir formiga 3")) {
			this.getImagemDaColmeia().setBufferInt(3);
		}

		else if (textoBotao.equals("inserir aranha 1")) {
			this.getImagemDaColmeia().setBufferInt(4);
		}

		else if (textoBotao.equals("inserir aranha 2")) {
			this.getImagemDaColmeia().setBufferInt(5);
		}

		else if (textoBotao.equals("inserir grilo 1")) {
			this.getImagemDaColmeia().setBufferInt(6);
		}

		else if (textoBotao.equals("inserir grilo 2")) {
			this.getImagemDaColmeia().setBufferInt(7);
		}

		else if (textoBotao.equals("inserir grilo 3")) {
			this.getImagemDaColmeia().setBufferInt(8);
		}

		else if (textoBotao.equals("inserir besouro 1")) {
			this.getImagemDaColmeia().setBufferInt(9);
		} else if (textoBotao.equals("inserir besouro 2")) {
			this.getImagemDaColmeia().setBufferInt(10);
		}

	}

}
