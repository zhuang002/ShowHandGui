import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CardDeck extends JPanel {
	
	boolean headsUp = true;
	boolean automatic = false;
	List<Card> cards = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	

	public void displayCards(List<Card> cards) {
		// TODO Auto-generated method stub
		this.cards = cards;
		displayCards();
		
	}

	private void displayCards() {
		// TODO Auto-generated method stub
		this.removeAll();
		try {
			if (!this.headsUp) {
				
				BufferedImage img = ImageIO.read(new File("img/b.gif"));
				int x = 0;
				for (int i=0;i<cards.size();i++) {
					JLabel lCard = new JLabel(new ImageIcon(img));
					lCard.setBounds(x, 0, 73, 97);
					this.add(lCard);
					x+=20;
				}
			}
			else {
				if (this.cards==null) return;
				int x = (cards.size()-1)*20;
				for (int i=cards.size()-1;i>=0;i--) {
					String fname = "img/"+cards.get(i).toString()+".gif";
					BufferedImage img = ImageIO.read(new File(fname));
					JLabel lCard = new JLabel(new ImageIcon(img));
					lCard.setBounds(x, 0, 73, 97);
					this.add(lCard);
					x-=20;
				}
			}
			this.repaint();
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

	public void repaintDeck() {
		// TODO Auto-generated method stub
		List<JLabel> cards = new ArrayList<>();
		
		for (Component comp:this.getComponents()) {
			if (comp.getClass() == JLabel.class) {
				cards.add((JLabel)comp);
			}
		}
		
		int x = (cards.size()-1)*20;
		for (int i=cards.size()-1;i>=0;i--) {
			cards.get(i).setBounds(x,0, 73,97);
			x-=20;
		}
		this.repaint();
	}

	
	
}
