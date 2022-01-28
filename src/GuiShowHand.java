import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class GuiShowHand implements GuiController {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	Service server = null;
	
	private PlayerField[] playerFields = new PlayerField[2];
	String[] names = {"Computer", "You"};
	private JButton btnNewButton;
	private JPanel panel;

	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiShowHand window = new GuiShowHand();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiShowHand() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		server = ShowHandLogic.getInstance(this);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnNewButton = new JButton("New Round");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if (btnNewButton.getText().equals("New Game")) {
					btnNewButton.setText("New Round");
					server.newGame();
				}*/
				server.newRound();
				playerFields[0].setHeadsUp(false);
				playerFields[1].setHeadsUp(true);
				setState(State.bet);
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.NORTH);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 20, 20));
		
		

		playerFields[1] = new PlayerField(1, names[1]);
		playerFields[0] = new PlayerField(0, names[0]);
		playerFields[0].setHeadsUp(false);
		playerFields[0].setAutomatic(true);
		
		
		
		for (int i=0;i<2;i++) {
			panel.add(playerFields[i]);
		}
	
		
		
		setState(State.done);
		this.server.newGame();
	}

	private void setState(State state) {
		// TODO Auto-generated method stub

		if (state == State.done) {
			this.btnNewButton.setEnabled(true);
		} else {
			this.btnNewButton.setEnabled(false);
		}
		this.playerFields[1].setState(state);
	}


	@Override
	public void displayCards(int player, List<Card> cards) {
		// TODO Auto-generated method stub
		this.playerFields[player].displayCards(cards);
	}

	@Override
	public void displayBets(int player, int amount) {
		// TODO Auto-generated method stub
		this.playerFields[player].displayBet(amount);
	}

	@Override
	public void displayInventories(int player, int inventory) {
		// TODO Auto-generated method stub
		this.playerFields[player].displayInventory(inventory);
	}

	@Override
	public void roundDone() {
		// TODO Auto-generated method stub
		for (int i=0;i<this.playerFields.length;i++) {
			this.playerFields[i].setHeadsUp(true);
		}
		this.setState(State.done);
	}

	@Override
	public void gameOver(int player) {
		// TODO Auto-generated method stub
		
		JOptionPane.showMessageDialog(null, names[player]+" run out of money, game over.");
		this.setState(State.done);
		//this.btnNewButton.setText("New Game");
		//for (PlayerField field:this.playerFields) {
			//field.repaintCardDeck();
		//}
		server.newGame();
		
	}

}
