package estruturaInterna;

import java.awt.Rectangle;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SymbolDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="112,41"

	private JLabel labelEx = null;

	private JLabel labelBall = null;

	private JLabel labelQuestion = null;

	private JRadioButton buttonEx = null;

	private JRadioButton buttonBall = null;

	private JButton buttonOK = null;

	public SymbolDialog(java.awt.Frame parent, boolean modal) {
	        super(parent, modal);
	        initialize();
	        this.setTitle("Escolha do símbolo");
//	        this.setBounds(384, 250, 254, 215);
	 }
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(250, 250);
		this.setContentPane(getJContentPane());
	    this.addWindowListener(new java.awt.event.WindowAdapter() {
	    	public void windowClosing(java.awt.event.WindowEvent evt) {
	    		actionButtonOK();
	    	}
	    });
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			Icon xis = new ImageIcon(ClassLoader.getSystemResource("xis.gif"));
			Icon bola = new ImageIcon(ClassLoader.getSystemResource("bola.gif"));
			labelQuestion = new JLabel();
			labelQuestion.setBounds(new Rectangle(19, 12, 205, 25));
			labelQuestion.setText("Choose your symbol");
			labelBall = new JLabel();
			labelBall.setBounds(new Rectangle(160, 50, 70, 70));
			labelBall.setIcon(bola);
			labelEx = new JLabel();
			labelEx.setBounds(new Rectangle(10, 50, 70, 70));
			labelEx.setIcon(xis);
		    ButtonGroup grupoBotoesRadio = new ButtonGroup();
		    buttonEx = this.getButtonEx();
		    buttonBall = this.getButtonBall();
		    grupoBotoesRadio.add(buttonEx);
		    grupoBotoesRadio.add(buttonBall);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(labelEx, null);
			jContentPane.add(labelBall, null);
			jContentPane.add(labelQuestion, null);
			jContentPane.add(buttonEx, null);
			jContentPane.add(buttonBall, null);
			jContentPane.add(getButtonOK(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes buttonEx	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getButtonEx() {
		if (buttonEx == null) {
			buttonEx = new JRadioButton();
			buttonEx.setSelected(true);
			buttonEx.setBounds(new Rectangle(35, 130, 21, 21));
		}
		return buttonEx;
	}

	/**
	 * This method initializes buttonBall	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getButtonBall() {
		if (buttonBall == null) {
			buttonBall = new JRadioButton();
			buttonBall.setSelected(false);
			buttonBall.setBounds(new Rectangle(185, 130, 21, 21));
		}
		return buttonBall;
	}

	/**
	 * This method initializes buttonOK	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getButtonOK() {
		if (buttonOK == null) {
			buttonOK = new JButton();
			buttonOK.setText("OK");
			buttonOK.setBounds(new Rectangle(75, 170, 90, 28));
			buttonOK.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					actionButtonOK();
				}
			});
		}
		return buttonOK;
	}
	
	public void actionButtonOK() {
        setVisible(false);
        dispose();
	}
	
	public void defineQuestion(String question) {
		labelQuestion.setText(question);
	}
	
	public boolean exSelected() {
		return buttonEx.isSelected();
	}
	
	public static boolean getSymbolX(String question) {
	  	SymbolDialog x = new SymbolDialog(new javax.swing.JFrame(), true);
	  	x.defineQuestion(question);        
	  	x.setVisible(true);
	    return (x.exSelected());
	}
}
