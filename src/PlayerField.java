import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Component;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayerField extends JPanel {
	private JTextField textFieldInventory;
	private JTextField textFieldBetThisRound;
	private JButton btnDraw;
	private JButton btnBet;
	private JButton btnGiveUp;
	private JButton btnDone;
	
	private CardDeck cardDeck;
	private boolean automatic = false;
	private int id;
	/**
	 * Create the panel.
	 */
	public PlayerField(int id, String title) {
		this.id=id;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inventory");
		lblNewLabel.setBounds(37, 49, 55, 14);
		add(lblNewLabel);
		
		textFieldInventory = new JTextField();
		textFieldInventory.setEditable(false);
		textFieldInventory.setBounds(135, 46, 86, 20);
		add(textFieldInventory);
		textFieldInventory.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Bet this round");
		lblNewLabel_1.setBounds(37, 91, 86, 14);
		add(lblNewLabel_1);
		
		textFieldBetThisRound = new JTextField();
		textFieldBetThisRound.setBounds(135, 88, 86, 20);
		add(textFieldBetThisRound);
		textFieldBetThisRound.setColumns(10);
		
		btnDraw = new JButton("Draw");
		btnDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card card = ShowHandLogic.getInstance().draw(id);
			}
		});
		btnDraw.setBounds(34, 165, 89, 23);
		add(btnDraw);
		
		btnBet = new JButton("Bet");
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amount = Integer.parseInt(textFieldBetThisRound.getText());
				if (amount <= 0) {
					JOptionPane.showMessageDialog(null, "Amount cannot be less or equal to 0.");
					return;
				}
				ShowHandLogic.getInstance().bet(id, amount);
				setState(State.draw);
			}
		});
		btnBet.setBounds(34, 131, 89, 23);
		add(btnBet);
		
		btnGiveUp = new JButton("Give Up");
		btnGiveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowHandLogic.getInstance().giveUp(id);
				setState(State.done);
			}
		});
		btnGiveUp.setBounds(34, 233, 89, 23);
		add(btnGiveUp);

		cardDeck = new CardDeck();
		cardDeck.setLocation(172, 131);
		cardDeck.setSize(268, 158);
		add(cardDeck);
		
		JLabel lblTitle = new JLabel(title);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(37, 11, 173, 14);
		add(lblTitle);
		
		btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(State.done);
				ShowHandLogic.getInstance().done(id);
			}
		});
		btnDone.setBounds(34, 199, 89, 23);
		add(btnDone);
	}

	public void displayBet(int amount) {
		// TODO Auto-generated method stub
		this.textFieldBetThisRound.setText(""+amount);
	}

	public void displayInventory(int amount) {
		// TODO Auto-generated method stub
		this.textFieldInventory.setText(""+amount);
	}

	public void setState(State state) {
		// TODO Auto-generated method stub
		if (this.automatic) return;
		if (state == State.bet) {
			this.textFieldBetThisRound.setEnabled(true);
			this.btnBet.setEnabled(true);
			this.btnDone.setEnabled(false);
			this.btnDraw.setEnabled(false);
			this.btnGiveUp.setEnabled(false);
		} else if (state == State.draw) {
			this.textFieldBetThisRound.setEnabled(false);
			this.btnBet.setEnabled(false);
			this.btnDone.setEnabled(true);
			this.btnDraw.setEnabled(true);
			this.btnGiveUp.setEnabled(true);
		} else if (state == State.done) {
			this.textFieldBetThisRound.setEnabled(false);
			this.btnBet.setEnabled(false);
			this.btnDone.setEnabled(false);
			this.btnDraw.setEnabled(false);
			this.btnGiveUp.setEnabled(false);
		}
	}

	public void displayCards(List<Card> cards) {
		// TODO Auto-generated method stub
		this.cardDeck.displayCards(cards);
	}

	public void setAutomatic(boolean b) {
		// TODO Auto-generated method stub
		this.automatic = b;
		if (b) {
			Component[] components = this.getComponents();
			for (Component comp:components) {
				if (comp.getClass() == JButton.class) {
					comp.setEnabled(false);
				} else if (comp.getClass() == JTextField.class) {
					comp.setEnabled(false);
				}
			}
		}
	}

	public void setHeadsUp(boolean b) {
		// TODO Auto-generated method stub
		this.cardDeck.setHeadsUp(b);
	}

	public void repaintCardDeck() {
		// TODO Auto-generated method stub
		this.cardDeck.repaintDeck();
	}
	
}
