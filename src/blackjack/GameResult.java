package blackjack;

/** These constants characterize the state of the 
 *  blackjack game at the end.  
 *  They are used by the GUI to determine
 *  how much money to give or take away from the player.
 * 
 */
public enum GameResult {
	PLAYER_WON, PLAYER_LOST, PUSH, NATURAL_BLACKJACK;
}
