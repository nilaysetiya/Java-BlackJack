package nz.ac.auckland.softeng281.a2;

import java.util.List;
import java.util.Random;

/**
 * you should change this class for TASK 2
 */
public class BotDealer extends Participant {

	private List<Participant> players;

	public BotDealer(String name, List<Participant> players) {
		super(name);
		// ADDHERE
		this.players = players;
	}

	@Override
	public Action decideAction() {
		// TODO
		
		// Gets the score of the dealer
		Hand dealerHand = getCurrentHand();
		int dealerScore = dealerHand.getScore();
		
		// To count the number of winning players
		int count = 0;
		
		// For loop to check all players
		for (Participant players: this.players) {
			
			// Gets hand of a player and checks if that player is winning
			Hand currentPlayerHand = players.getCurrentHand();
			if (currentPlayerHand.getScore() == 21) {
				count++;
			} else if (currentPlayerHand.getScore() <= 21 && dealerScore > 21) {
				count++;
			} else if (currentPlayerHand.getScore() > dealerScore && currentPlayerHand.getScore() <= 21 && dealerScore <= 21) {
				count++;
			}
		}
		
		// Returns the correct action depending on the number of winning players
		if (count >= 2) {
			return Action.HIT;
		} else {
			return Action.HOLD;
		}

	}

	@Override
	/**
	 * do not touch this method
	 */
	public int makeABet() {
		// the Dealer doesn't bet so is always zero
		return 0;
	}
}
