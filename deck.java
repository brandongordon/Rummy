import java.util.ArrayList;
import java.util.Collections;

public class deck {
    private ArrayList<rummyCard> deckOfCards = new ArrayList<rummyCard>(); //This is the "Deck" - The Array of Card elements

    public deck(int numJokers) {
        generateDeck(numJokers);
        shuffle(deckOfCards);
    }

    public void generateDeck(int numOfJokers) { // Step through all the suits and assign each one the full set of ranks
        String[] suits = { "Diamonds", "Hearts", "Clubs", "Spades" };
        String[] ranks = { "Ace", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };

        if (numOfJokers == 0) {
            for (String suit : suits) {
                for (String rank : ranks) {
                    genCard(suit, rank);
                }
            }
        }

        if (numOfJokers == 1) {
            for (String suit : suits) {
                for (String rank : ranks) {
                    genCard(suit, rank);
                }
            }
            genCard("Joker", "Joker");
        }

        if (numOfJokers >= 2) {
            for (String suit : suits) {
                for (String rank : ranks) {
                    genCard(suit, rank);
                }
            }
            genCard("Joker", "Joker");
            genCard("Joker", "Joker");
        }
    }

    public ArrayList<rummyCard> deal(int numToDeal) {

    }

    public void shuffle(ArrayList<rummyCard> neatDeck) {
        Collections.shuffle(neatDeck);
    }

    private void genCard(String suit, String rank) { //Assign new card with the given suit and rank
        rummyCard newCard = new rummyCard(suit, rank);
        deckOfCards.add(newCard); //Push that card onto the "deck" Array
        //System.out.println("Added the " + rank + " of " + suit);
    }

    public void printDeck() {
        System.out.println(deckOfCards);
    }
}