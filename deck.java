import java.util.ArrayList;
import java.util.Collections;

public class deck {
    private ArrayList<rummyCard> deckOfCards = new ArrayList<rummyCard>(); //This is the "Deck" - The Array of Card elements
    public ArrayList<rummyCard> discarded = new ArrayList<rummyCard>(); //This is the "Discard" pile with discarded(0) being tradable (check this index)

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
        int dealNumber = numToDeal -1; //Account for 0 index. If I want to deal 7 cards I gotta tell the program 6 because it will start from 0 and count to 6 which = 7.
        int deckLeft = deckOfCards.size(); //Keep track of how many cards left in the deck
        ArrayList<rummyCard> dealtCards = new ArrayList<rummyCard>(); //The cards that we are going to return to the player
        if (dealNumber <= deckLeft){
            for (int i = 0; i <= dealNumber; i++){
                dealtCards.add(deckOfCards.get(0)); //get the first element of the deck and put it into dealtCards. We always want the first element as we are deleting this element from the deck as soon as we deal it.
                System.out.println("Dealt " + dealtCards.get(i));
                deckOfCards.remove(0); //Remove the first element of the deck.
                deckLeft = deckLeft -1;
            }
        } else {
            //Think of a way to say "hey we cant deal anymore cards boss, you got too many players homie"
        }
        return dealtCards; 
        
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
        System.out.println("\nCurrent Deck consists of " + deckOfCards.size() + " cards: " + deckOfCards);
    }

    public void printDiscard(){
        System.out.println("\nCurrent Discard Pile consists of " + discarded.size() + " cards: " + discarded);
    }

    public int getSize(){
        return deckOfCards.size();
    }

    public rummyCard discardFromDeck(){ //Used at the start of the game to discard the first card off the deck
        if (deckOfCards.size() == 0){
            reviveDeck();
        }
        rummyCard theCard = deckOfCards.get(0); 
        discarded.add(theCard);
        System.out.println("\n[!] Discarded " + theCard + " from the deck");
        deckOfCards.remove(0);
        return theCard; 
    }

    private void reviveDeck(){
        deckOfCards = new ArrayList<rummyCard>(discarded);
        Collections.shuffle(deckOfCards);
        discarded.clear();
        System.out.println("\n[!] DECK HAS BEEN REVIVED");
        System.out.println("\nCurrent Deck consists of " + deckOfCards.size() + " cards: " + deckOfCards);
    }

    public rummyCard withdrawFromDeck(){
        if (deckOfCards.size() == 0){
            reviveDeck();
        }
        rummyCard theCard = deckOfCards.get(0);
        deckOfCards.remove(0);
        System.out.println("\n[!] A card was drawn from the DECK pile");
        return theCard;
    }

    public rummyCard withdrawFromDiscarded(){
        if (discarded.size() == 0){
            System.out.println("\n[!] Discarded pile is empty. Withdrawing from deck instead");
            rummyCard theCard = deckOfCards.get(0);
            deckOfCards.remove(0);
            return theCard;
        }
        else{
            rummyCard theCard = discarded.get(discarded.size() - 1);
            discarded.remove(discarded.size() - 1);
            System.out.println("\n[!] A card was drawn from the DISCARD pile");
            return theCard;
        }
    }

    public void acceptDiscardedCard(rummyCard discardedCard){
        discarded.add(discardedCard);
        System.out.println("\n[!] " + discardedCard + " was discarded");
    }

}

