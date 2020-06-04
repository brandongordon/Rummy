import java.util.ArrayList;

public class player {
    public String playerName; 
    public ArrayList<rummyCard> hand = new ArrayList<rummyCard>(); //The hand of the player. Capable of holding 7 cards PLUS swap (1)
    public boolean isAI;

    public player(String name, ArrayList<rummyCard> cards, boolean AIorNot){
        playerName = name;
        hand = cards;
        isAI = AIorNot;
    }

    public void acceptCard(rummyCard drawn){
        hand.add(drawn); //Take the card into your hand from which ever source
        System.out.println("\n[!] " + playerName +" withdrew " + drawn);
    }

    public rummyCard discardCard(int index){
        rummyCard discarded = hand.get(index); 
        hand.remove(index); //Discard a card from your hand
        return discarded;
    }




}