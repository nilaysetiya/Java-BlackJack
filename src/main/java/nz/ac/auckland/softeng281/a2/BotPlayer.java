package nz.ac.auckland.softeng281.a2;

import java.util.Random;

/**
 * you should change this class for TASK 1
 */
public class BotPlayer extends Participant {

	public BotPlayer(String name) {
		super(name);
	}

	@Override
	public Action decideAction() {
		// TODO
		
		// Gets current hand
		Hand Hand = getCurrentHand();
		
		// Gets score of current hand
		int score = Hand.getScore();
		
		// Returns Hit or Hold depending on score
		if (score < 17) {
			return Action.HIT;
		} else {
			return Action.HOLD;
		}
		
	}

	@Override
	public int makeABet() {
		// TODO
		
		// Gets a random number between 1 and 100 (inclusive) to 
		// return as the result
		Random rnd = new Random();
		int low = 1;
		int high = 101;
		int result = rnd.nextInt(high-low) + low;
		return result;
	}
}
