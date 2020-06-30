package deckOfCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

	private ArrayList<Card> deck = new ArrayList<Card>();

	public Deck() {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				deck.add(new Card(Rank.values()[j], Suit.values()[i]));
			}
		}

	}

	public void shuffle(Random randomNumberGenerator) {

		Collections.shuffle(deck, randomNumberGenerator);

	}

	public Card dealOneCard() { // returns first card from list

		Card c = deck.get(0);
		deck.remove(0);

		return c;
	}

}
