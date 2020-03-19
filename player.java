import java.util.ArrayList;

public class player {
    String playerName; 
    ArrayList<rummyCard> hand = new ArrayList<rummyCard>(); //The hand of the player. Capable of holding 7 cards PLUS swap (1)

    public player(String name, ArrayList<rummyCard> cards){
        playerName = name;
        hand = cards;
    }



}