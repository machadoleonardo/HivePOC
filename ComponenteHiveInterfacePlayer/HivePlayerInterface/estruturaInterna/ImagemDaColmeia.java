package estruturaInterna;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ClassesComuns.Lance;
import ClassesComuns.Peca;
import ClassesComuns.Posicao;

import br.ufsc.inf.leobr.cliente.Jogada;
import interfaceDoComponente.PortPlayerProxyOutbox;

public class ImagemDaColmeia extends JPanel implements Jogada {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[][] board;
	HiveGUI hiveGUI;
	final static Color COLOURBACK = Color.WHITE;
	final static int BSIZE = 24; // tamanho da colmeia em hexagonos
	final static int HEXSIZE = 60; // hex size in pixels
	final static int BORDERS = 15;
	protected Graphics2D g2;
	Colmeia umaColmeia;
	String buffer;
	int origX;
	int origY;
	int bufferInt;

	public ImagemDaColmeia(String[][] board, HiveGUI hiveGUI) {
		this.board = board;
		this.hiveGUI = hiveGUI;
		// this.umaColmeia = umaColmeia;
		setBackground(COLOURBACK);
		buffer = "";
		origX = 23; // padrao para sinalizar que nada foi clicado anteriormente (mover)
		origY = 23; // padrao para sinalizar que nada foi clicado anteriormente (mover)
		bufferInt = -1; // padrao: negativo

		// Posicao posicao = new Posicao(this.umGerenciador);
		Posicao.setXYasVertex(false); // RECOMMENDED: leave this as FALSE.
		Posicao.setHeight(HEXSIZE); // Either setHeight or setSize must be run to initialize the hex
		Posicao.setBorders(BORDERS);

		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);
	}

	public void paintComponent(Graphics g) {

		this.g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 13));
		super.paintComponent(g2);
		// draw grid
		for (int i = 0; i < BSIZE; i++) {
			for (int j = 0; j < BSIZE; j++) {
				Posicao p = new Posicao();
				p.drawHex(i, j, g2);

			}
		}
		// fill in hexes
		for (int i = 0; i < BSIZE; i++) {
			for (int j = 0; j < BSIZE; j++) {
				Posicao p = new Posicao();
				p.fillHex(i, j, board[i][j], g2);
			}
		}
	}

	public void modificaGrafico(int x, int y, String s) {
		board[x][y] = s;
		repaint();
	}

	class MyMouseListener extends MouseAdapter { // classe interna a ImagemDaColmeia
		public void mouseClicked(MouseEvent e) {

			Posicao po = new Posicao();
			Point p = new Point(po.pxtoHex(e.getX(), e.getY()));
			if (p.x < 0 || p.y < 0 || p.x >= BSIZE || p.y >= BSIZE)
				return;

			// O que fazer quando ocorre o clique do mouse?

			// click(p.x, p.y); //executa todos os testes no hexagono e movimenta a peca

			clickMouse(p.x, p.y);

		}

		public void clickMouse(int x, int y) {
			if ((hiveGUI.playerState == PlayerStateValue.playing) && (hiveGUI.ehMinhaVez == true)) {
				if (bufferInt < 0 && board[x][y].isEmpty()) {
					JOptionPane.showMessageDialog(null, "selecione uma peca nos botoes ao lado ou no tabuleiro.");
				} else {
					if (bufferInt >= 0 && board[x][y].isEmpty()) {
						Peca peca = new Peca();
						peca.setNroPeca(bufferInt);
						Lance move = new Lance(x, y, peca, hiveGUI.nome);

						PortPlayerProxyOutbox portOutbox = (PortPlayerProxyOutbox) hiveGUI.playerPort.getOutbox();
						portOutbox.lance(move);
						bufferInt = -1;
						origX = 23;
						origY = 23;
					} else {
						if (bufferInt >= 0 && !board[x][y].isEmpty()) {
							JOptionPane.showMessageDialog(null, "Posicao ocupada. Tente novamente.");
						} else { // bufferInt<0 && ! board[x][y].isEmpty()
							origX = x;
							origY = y;
							bufferInt = 12;
							JOptionPane.showMessageDialog(null, "Coordenadas carregadas. Escolha posicao destino");
						}
					}
				}
			} else {
				if (hiveGUI.playerState != PlayerStateValue.playing) {
					hiveGUI.comunicarMensagem("N�o h� jogo em andamento");
				} else {
					hiveGUI.comunicarMensagem("Voc� n�o est� habilitado para jogar");
				}
			}
		}
	}

	public Graphics2D getGrafico() {
		return this.g2;
	}

	public HiveGUI getAtorJogador() {
		return this.hiveGUI;
	}

	public void setBufferInt(int i) {
		if (hiveGUI.ehMinhaVez) {
			this.bufferInt = i;
		} else {
			JOptionPane.showMessageDialog(null, "aguarde sua vez");
		}
	}

}
