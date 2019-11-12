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
        
        deck cardDeck = new deck(0); //Generate a new deck of cards with 0 jokers - a new deck is automatically shuffled
        cardDeck.printDeck();
        
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
    }

    public void startGame(int players){
        System.out.println("\n[!] Starting game with " + players + " players.");
    }
}



