package tests;

import org.junit.Test;

import blackjack.BlackjackModel;
import deckOfCards.Card;
import deckOfCards.Rank;
import deckOfCards.Suit;

import java.util.ArrayList;


public class StudentTests {

	@Test
	public void testAssessHand() {
		BlackjackModel game = new BlackjackModel();

		ArrayList<Card> a = new ArrayList<Card>();
		a.add(new Card(Rank.ACE, Suit.CLUBS));
		a.add(new Card(Rank.EIGHT, Suit.SPADES));
		a.add(new Card(Rank.QUEEN, Suit.CLUBS));
		a.add(new Card(Rank.KING, Suit.CLUBS));

		
		System.out.println(BlackjackModel.assessHand(a));
		System.out.println(BlackjackModel.possibleHandValues(a));
		
	}
	
	
	
	
	
}
