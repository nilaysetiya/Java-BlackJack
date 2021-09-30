package nz.ac.auckland.softeng281.a2;

import java.util.ArrayList;
import java.util.List;

/**
 * you should change this class for TASK 1, 2, 3, 4.
 */
public class BlackJack {

	private List<Participant> players;
	private Participant dealer;

	public BlackJack() {
		players = new ArrayList<>();
		dealer = new BotDealer("Dealer", players); // FIXME Task 2
		players.add(new HumanPlayer("Player1"));
		// ADDHERE Task 1
		// Added Bot1 and Bot2
		players.add(new BotPlayer("Bot1"));
		players.add(new BotPlayer("Bot2"));
		
	}

	// getter setter for testing purposes
	public List<Participant> getPlayers() {
		return players;
	}

	public Participant getDealer() {
		return dealer;
	}

	public void setPlayers(List<Participant> players) {
		this.players = players;
	}

	public void setDealer(Participant dealer) {
		this.dealer = dealer;
	}

	public static void main(String[] args) {
		BlackJack game = new BlackJack();
		game.start();
	}

	protected void start() {
		Utils.printBlackJack();
		// create a new deck of cards
		Deck deck = new Deck();
		String result;
		do {
			for (Participant player : players) {
				player.play(deck);
			}
			// ADDHERE Task 2
			dealer.play(deck);
			checkWinner();
			System.out.println("Do you want to play again?");
			result = Utils.scanner.next();
			while (!result.equals("yes") && !result.equals("no")) {
				System.out.println("please type either \"yes\" or \"no\"");
				result = Utils.scanner.next();
			}
		} while (result.equals("yes"));
		printPlayerHighestGain();
	}

	public void checkWinner() {
		// TODO Task 3
		for (Participant player : players) { // KEEPTHIS
			// ADDHERE
			// Gets score of the dealer
			Hand dealerHand = this.dealer.getCurrentHand();
			int dealerScore = dealerHand.getScore();
			
			// Gets score of the player
			Hand playerHand = player.getCurrentHand();
			int playerScore = playerHand.getScore();
			
			// Checks if the player has won and prints out the correct statement
			if (playerScore == 21) {
				System.out.println(player.getName() + " wins"); 
			} else if (playerScore <= 21 && dealerScore > 21) {
				System.out.println(player.getName() + " wins"); 
			} else if (playerScore > dealerScore && playerScore <= 21 && dealerScore <= 21) {
				System.out.println(player.getName() + " wins"); 
			}
			
		}
	}

	public void printPlayerHighestGain() {
		// TODO Task 4
		
		// Get dealer hands
		List<Hand> dealerHands = new ArrayList<>();
		dealerHands = this.dealer.getHands();
		
		// For loop to cycle through all players
		for (int i = 0; i < players.size(); i++) {
			
			// Get player and list of player's hands
			Participant currentPlayer = players.get(i);
			List<Hand> playerHands = new ArrayList<>();
			playerHands = currentPlayer.getHands();
			
			// For loop to cycle through all hands
			for (int j = 0; j < playerHands.size(); j++) {
				
				// Get specific player and dealer hand
				Hand specificDealerHand = dealerHands.get(j);
				Hand specificPlayerHand = playerHands.get(j);
				
				// Get player and dealer hand score
				int specificDealerHandScore = specificDealerHand.getScore();
				int specificPlayerHandScore = specificPlayerHand.getScore();
				
				// Get current bet of player
				int playerBet = specificPlayerHand.getBet();
				
				// Checking if player wins or loses and sets gain accordingly
				if (specificPlayerHand.isBlackJack()) {
					currentPlayer.setTotalGain(currentPlayer.getTotalGain()+(3*playerBet)/2);
				} else if (specificPlayerHandScore <= 21 && specificDealerHandScore > 21) {
					currentPlayer.setTotalGain(currentPlayer.getTotalGain() + playerBet);
				} else if (specificPlayerHandScore > specificDealerHandScore && specificPlayerHandScore <= 21 && specificDealerHandScore <= 21) {
					currentPlayer.setTotalGain(currentPlayer.getTotalGain() + playerBet);
				} else {
					currentPlayer.setTotalGain(currentPlayer.getTotalGain() - playerBet);
				}
			}
		}
		
		// Set highest gain and player name to first player
		double highestOverallGain = players.get(0).getTotalGain();
		String playerName = players.get(0).getName();
		
		// Compare gain of subsequent players to get player with highest gain 
		for (int k = 1; k < players.size(); k++) {
			if (players.get(k).getTotalGain() > highestOverallGain) {
				highestOverallGain = players.get(k).getTotalGain();
				playerName = players.get(k).getName();
			}
		}
		
		
		System.out.println("The player with the highest gain is: " + playerName + " with "+ highestOverallGain + " chips"); // UNCOMMENT AND KEEPTHIS
	}
}
