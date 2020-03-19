import java.util.ArrayList;
import java.util.Scanner;

public class Rummy {
    public static void main(String[] args) {
        Rummy game = new Rummy();
        Scanner scan = new Scanner(System.in);
        System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-(*)-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		System.out.println("\t\t\tWelcome to Rummy!");
		System.out.println("\t\t\tBy Brandon Gordon");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-(*)-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
              
        String players;
        int numOfPlayers;
        boolean retry = true;
        while (retry){
            try{
                System.out.print("\n\tHow many players? >");
                players = scan.next();
                numOfPlayers = Integer.parseInt(players);
                retry = false;
                game.startGame(numOfPlayers);
            }
            catch (NumberFormatException e){
                System.out.println("\t[!] That's not a number. Try again.");
            }
        }
        scan.close();
    }

    public void startGame(int howManyPlayers){
        deck cardDeck = new deck(0); //Generate a new deck of cards with 0 jokers - a new deck is automatically shuffled
        cardDeck.printDeck();

        ArrayList<player> party = new ArrayList<player>(); //Get an arrayList going to store the players in, allows us to rotate between them etc
        System.out.println("\n[!] Starting game with " + howManyPlayers + " players.");
        for (int i = 1; i <= howManyPlayers; i++){ //create x number of player objects and assign them a name and some cards
            String playerName = "player" + i;
            ArrayList<rummyCard> theirDeck = new ArrayList<rummyCard>();
            theirDeck = cardDeck.deal(7);
            player currPlayer = new player(playerName, theirDeck);
            party.add(currPlayer);
            System.out.println("\t[!] Generated " + playerName);
        
        }
        
        cardDeck.printDeck();
        //print each players hand
        //generate a new object "discarded" with .getTop()
    }
}



