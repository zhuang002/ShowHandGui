import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CardDeck extends JPanel {
	
	boolean headsUp = true;
	boolean automatic = false;
	Card[] cards = null;

	/**
	 * Create the panel.
	 */
	

	public void displayCards(Card[] cards) {
		// TODO Auto-generated method stub
		this.cards = cards;
		displayCards();
		
	}

	private void displayCards() {
		// TODO Auto-generated method stub
		this.removeAll();
		try {
			if (this.headsUp) {
				
				BufferedImage img = ImageIO.read(new File("image/b.gif"));
				int x = 0;
				for (int i=0;i<cards.length;i++) {
					JLabel lCard = new JLabel(new ImageIcon(img));
					lCard.setBounds(0, x, 73, 97);
					this.add(lCard);
					x+=20;
				}
			}
			else {
				if (this.cards==null) return;
				int x = 0;
				for (Card card:cards) {
					String fname = "img/"+card.toString()+".gif";
					BufferedImage img = ImageIO.read(new File(fname));
					JLabel lCard = new JLabel(new ImageIcon(img));
					lCard.setBounds(0, x, 73, 97);
					this.add(lCard);
					x+=20;
				}
			}
		} catch (Exception excpt) {
			JOptionPane.showMessageDialog(this, "image file is not found");
		}
	}

	public boolean isHeadsUp() {
		return headsUp;
	}

	public void setHeadsUp(boolean headsUp) {
		this.headsUp = headsUp;
		this.displayCards();
	}

	public boolean isAutomatic() {
		return automatic;
	}

	public void setAutomatic(boolean automatic) {
		this.automatic = automatic;
	}

	
	
}
