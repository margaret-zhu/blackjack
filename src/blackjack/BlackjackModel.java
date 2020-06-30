package blackjack;

import java.util.ArrayList;
import java.util.Random;

import deckOfCards.*;

public class BlackjackModel {

	private ArrayList<Card> dealerCards = new ArrayList<Card>();
	private ArrayList<Card> playerCards = new ArrayList<Card>();
	private Deck deck = new Deck();
	
	
	public ArrayList<Card> getDealerCards() {
		return new ArrayList<Card>(dealerCards);
	}

	public ArrayList<Card> getPlayerCards() {
		return new ArrayList<Card>(playerCards);
	}

	public void setDealerCards(ArrayList<Card> cards) {

		dealerCards = cards;

	}

	public void setPlayerCards(ArrayList<Card> cards) {

		playerCards = cards;

	}

	public void createAndShuffleDeck(Random random) {

		deck = new Deck();
		deck.shuffle(random);

	}

	public void initialDealerCards() {

		dealerCards.clear();
		dealerCards.add(deck.dealOneCard());
		dealerCards.add(deck.dealOneCard());
	}

	public void initialPlayerCards() {

		playerCards.clear();
		playerCards.add(deck.dealOneCard());
		playerCards.add(deck.dealOneCard());

	}

	public void playerTakeCard() {

		playerCards.add(deck.dealOneCard());

	}

	public void dealerTakeCard() {

		dealerCards.add(deck.dealOneCard());

	}

	public static ArrayList<Integer> possibleHandValues(ArrayList<Card> hand) {

		ArrayList<Integer> possibleValues = new ArrayList<Integer>();

		int aces = 0;

		for (Card x : hand) {

			if (x.getRank() == Rank.ACE) {
				aces++;
			}
		}

		if (aces == 0) {
			// just get sum
			int sum = 0;

			for (Card x : hand) {

				sum += x.getRank().getValue();

			}

			possibleValues.add(sum);

		} else {

			int sum1 = 0, sum2 = 0;
			int aceCount = 0;

			for (Card x : hand) {

				if (x.getRank() == Rank.ACE) {

					if (aceCount == 0) {
						sum1 += 1;
						sum2 += 11;
					} else {
						sum1 += 1;
						sum2 += 1;
					}

					aceCount++;

				} else {

					sum1 += x.getRank().getValue();
					sum2 += x.getRank().getValue();

				}
			}

			possibleValues.add(sum1);

			if (sum2 < 22) {
				possibleValues.add(sum2);
			}

		}

		return possibleValues;

	}

	public static HandAssessment assessHand(ArrayList<Card> hand) {

		if (hand.size() < 2 || hand == null) {
			return HandAssessment.INSUFFICIENT_CARDS;
		} else if (possibleHandValues(hand).size() == 2 && possibleHandValues(hand).contains(21)) {
			return HandAssessment.NATURAL_BLACKJACK;
		} else if (possibleHandValues(hand).size() == 1 && possibleHandValues(hand).get(0) > 21) {
			return HandAssessment.BUST;
		} else {
			return HandAssessment.NORMAL;
		}

	}

	public GameResult gameAssessment() {

		if (assessHand(playerCards) == HandAssessment.NATURAL_BLACKJACK) {
			if (assessHand(dealerCards) != HandAssessment.NATURAL_BLACKJACK) {
				return GameResult.NATURAL_BLACKJACK;
			} else {
				return GameResult.PUSH;
			}
		} else {
			if (assessHand(playerCards) == HandAssessment.BUST) {
				return GameResult.PLAYER_LOST;
			} else {
				if (assessHand(dealerCards) == HandAssessment.BUST) {
					return GameResult.PLAYER_WON;
				} else {

					ArrayList<Integer> p = possibleHandValues(playerCards);
					ArrayList<Integer> d = possibleHandValues(dealerCards);

					int x = p.get(p.size() - 1);
					int y = d.get(d.size() - 1);

					if (x > y) {
						return GameResult.PLAYER_WON;
					} else if (x < y) {
						return GameResult.PLAYER_LOST;
					} else {
						return GameResult.PUSH;
					}

				}
			}
		}

	}

	public boolean dealerShouldTakeCard() {

		ArrayList<Integer> d = possibleHandValues(dealerCards);

		int y = d.get(d.size() - 1);

		if (y <= 16) {
			return true;
		} else if (y >= 18) {
			return false;
		} else {

			if (d.size() > 1 && y == 17) {
				return true;
			}

			if (d.size() == 1 && d.get(0) == 17) {
				return false;
			}

		}

		return false;

	}

}
