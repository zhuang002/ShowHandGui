import javax.swing.JPanel;
import javax.swing.JLabel;
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
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card card = ShowHandLogic.getInstance().draw(id);
			}
		});
		btnDraw.setBounds(34, 165, 89, 23);
		add(btnDraw);
		
		JButton btnBet = new JButton("Bet");
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amount = Integer.parseInt(textFieldBetThisRound.getText());
				ShowHandLogic.getInstance().bet(id, amount);
				setState(State.draw);
			}
		});
		btnBet.setBounds(34, 131, 89, 23);
		add(btnBet);
		
		JButton btnGiveUp = new JButton("Give Up");
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
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(State.done);
				ShowHandLogic.getInstance().done(id);
			}
		});
		btnDone.setBounds(34, 199, 89, 23);
		add(btnDone);
	}

	public void displayBet(int computerBet) {
		// TODO Auto-generated method stub
		
	}

	public void displayInventory(int computerInventory) {
		// TODO Auto-generated method stub
		
	}

	public void setState(State state) {
		// TODO Auto-generated method stub
		
	}

	public void displayCards(Card[] cards) {
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
	
}
