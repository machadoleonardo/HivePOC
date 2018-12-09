package estruturaInterna;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ClassesComuns.Colmeia;
import ClassesComuns.CommunicationContainer;
import ClassesComuns.CommunicationKind;
import ClassesComuns.State;
import InterfacesComuns.InterfacePlayerProxy;
import interfaceDoComponente.PortPlayerProxy;
import interfaceDoComponente.PortPlayerProxyOutbox;

public class HiveGUI extends JFrame implements InterfacePlayerProxy, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected PortPlayerProxy playerPort;
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

	protected enum PlayerStateValue {
		disconnected, connected, playing
	}

	protected PlayerStateValue playerState = PlayerStateValue.disconnected;
	private boolean enabledAction = false;

	public HiveGUI() throws HeadlessException {
		super();
		initialize();
	}

	public HiveGUI(GraphicsConfiguration arg0) {
		super(arg0);
		initialize();
	}

	public HiveGUI(String arg0) throws HeadlessException {
		super(arg0);
		initialize();
	}

	public HiveGUI(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		initialize();
	}

	private void initialize() {
		this.nome = JOptionPane.showInputDialog(null, "Digite seu nome:", "Hive", JOptionPane.QUESTION_MESSAGE);
		if (nome.isEmpty()) {
			this.nome = "";
		}
		this.umaColmeia = new Colmeia();

		// instanciar hexagonos
		tab = new String[BSIZE][BSIZE];
		for (int i = 0; i < BSIZE; i++) {
			for (int j = 0; j < BSIZE; j++) {
				tab[i][j] = "";
			}
		}

		iniciaJogo();

	}

	private void iniciaJogo() {
		this.criarGUI();

	}

	private void criarGUI() {
		this.panel = new ImagemDaColmeia(tab, this);

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

	@Override
	public void updateState(State state) {
		int value = 0;
		Icon vazia = new ImageIcon(ClassLoader.getSystemResource("vazia.gif"));
		Icon xis = new ImageIcon(ClassLoader.getSystemResource("xis.gif"));
		Icon bola = new ImageIcon(ClassLoader.getSystemResource("bola.gif"));
		String stateMessage = state.getStateMessage();
		// STATE UPDATE
		if (playerState == PlayerStateValue.connected) {
			playerState = PlayerStateValue.playing;
			if (stateMessage.contains(nome)) {
				enabledAction  = true;
			} else {
				enabledAction = false;
			}
		}
		if ((playerState == PlayerStateValue.playing) &&
			((stateMessage.contains("gave up")) || (stateMessage.contains("Game ended")) || (stateMessage.contains("Winner:"))))	{
			playerState = PlayerStateValue.connected;
			enabledAction = false;
		} else {
			if (stateMessage.contains(nome)) {
				enabledAction = true;
			} else {
				enabledAction = false;
			}
		}
		// INTERFACE UPDATE
		for (int linha = 1; linha < 4; linha++) {
			for (int coluna = 1; coluna < 4; coluna++) {				
				value = state.getValue(linha, coluna);
				switch (value) {
				case 0:
//					mapVPosition[(linha - 1)][(coluna - 1)].setIcon(vazia);
					break;
				case 1:
//					mapVPosition[(linha - 1)][(coluna - 1)].setIcon(xis);
					break;
				case 2:
//					mapVPosition[(linha - 1)][(coluna - 1)].setIcon(bola);
				}
			}
		}
	}

	@Override
	public void receberMensagem(String message) {
		this.comunicarMensagem("Message of the opponent: " + message);

	}

	@Override
	public void comunicar(CommunicationContainer notification) {
		String content = notification.getContent();
		CommunicationKind kind = notification.getKind();
		String title = "";
		switch (kind) {
		case connectionNotification:
			title = "Connection notification: ";
			if (content.equals("Server not connected")) {
				playerState = PlayerStateValue.disconnected;
			}
			if (content.equals("Connected to TTT Server")) {
				playerState = PlayerStateValue.connected;
			}
			break;
		case disconnectionNotification:
			title = "Disconnection notification: ";
			playerState = PlayerStateValue.disconnected;
			break;
		case irregularMoveNotification:
			title = "Irregular move notification: ";
			break;
		case connectedPlayerNotification:
			title = "Connected players: ";
			break;
		default:
			break;
		}
		this.comunicarMensagem(title + content);

	}

	public void comunicarMensagem(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public PortPlayerProxy getPlayerPort() {
		return playerPort;
	}

	public void setPlayerPort(PortPlayerProxy playerPort) {
		this.playerPort = playerPort;
	}

	protected void enviarMensagem() {
		if (playerState == PlayerStateValue.playing) {
			PortPlayerProxyOutbox portOutbox = (PortPlayerProxyOutbox) playerPort.getOutbox();
			String message = JOptionPane.showInputDialog(this, "What is the message to your opponent?");
			portOutbox.enviarMensagem(message);
		} else {
			this.comunicarMensagem("There is no game in progress");
		}
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
			panel.setBufferInt(0);
		}

		else if (textoBotao.equals("inserir formiga 1")) {
			panel.setBufferInt(1);
		}

		else if (textoBotao.equals("inserir formiga 2")) {
			panel.setBufferInt(2);
		}

		else if (textoBotao.equals("inserir formiga 3")) {
			panel.setBufferInt(3);
		}

		else if (textoBotao.equals("inserir aranha 1")) {
			panel.setBufferInt(4);
		}

		else if (textoBotao.equals("inserir aranha 2")) {
			panel.setBufferInt(5);
		}

		else if (textoBotao.equals("inserir grilo 1")) {
			panel.setBufferInt(6);
		}

		else if (textoBotao.equals("inserir grilo 2")) {
			panel.setBufferInt(7);
		}

		else if (textoBotao.equals("inserir grilo 3")) {
			panel.setBufferInt(8);
		}

		else if (textoBotao.equals("inserir besouro 1")) {
			panel.setBufferInt(9);
		} else if (textoBotao.equals("inserir besouro 2")) {
			panel.setBufferInt(10);
		}
		
	}
	
	private void finalizarPartida() {
		// TODO Auto-generated method stub
		
	}

	private void iniciarPartida() {
		// TODO Auto-generated method stub
		
	}

	private void desconectar() {
		if (playerState != PlayerStateValue.disconnected) {
			PortPlayerProxyOutbox portOutbox = (PortPlayerProxyOutbox) playerPort.getOutbox();
			if (playerState == PlayerStateValue.playing) {
				portOutbox.desistir(nome);				
			}
			portOutbox.conectar(nome);
		} else {
			this.comunicarMensagem(	"Você já está desconectado");
		}
		
	}

	private void conectar() {
		if (playerState == PlayerStateValue.disconnected) {
			PortPlayerProxyOutbox portOutbox = (PortPlayerProxyOutbox) playerPort.getOutbox();
			if (nome.equals("")) {
				int randomInt = 0;
				Random randomGenerator = new Random();
				randomInt = randomGenerator.nextInt(100000);
				nome = "jogador" + Integer.toString(randomInt);
			}			
			portOutbox.conectar(nome);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				portOutbox.confirmarConexao();
		} else {
			this.comunicarMensagem("Voce esta conectado");
		}
		
	}

	public void executarLance(int x, int y, int origX, int origY, int nroPeca) {
//		String notif = this.umGerenciador.executarLance(x, y, origX, origY, nroPeca);

//		JOptionPane.showMessageDialog(null, notif);
	}


}
