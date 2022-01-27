import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiShowHand implements GuiController {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	Service server = null;
	
	private PlayerField[] playerFields = new PlayerField[2];
	private JButton btnNewButton;
	private JPanel panel;
	private State state = State.done;
	
	
	
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
				setState(state);
				server.newRound();
				state = State.bet;
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.NORTH);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 20, 20));
		
		

		playerFields[1] = new PlayerField(1, "You");
		playerFields[0] = new PlayerField(0, "Computer");
		playerFields[0].setHeadsUp(false);
		playerFields[0].setAutomatic(true);
		
		
		
		for (int i=0;i<2;i++) {
			panel.add(playerFields[i]);
		}
	
		
		
		setState(State.done);
	}

	private void setState(State state) {
		// TODO Auto-generated method stub
		this.state = state;
		if (this.state == State.done) {
			this.btnNewButton.setEnabled(false);
		} else {
			this.btnNewButton.setEnabled(false);
		}
		this.playerFields[1].setState(state);
	}


	@Override
	public void displayCards(int player, Card[] cards) {
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

}
