import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Rummy {
    deck cardDeck = new deck(0); //Generate a new deck of cards with 0 jokers - a new deck is automatically shuffled
    ArrayList<player> party = new ArrayList<player>(); //Get an arrayList going to store the players in, allows us to rotate between them etc

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
                System.out.print("\n\tHow many AI opponents? > ");
                players = scan.next();
                numOfPlayers = (Integer.parseInt(players)+1); //the game sets up decks for the AI + you (1)
                retry = false;
                game.startGame(numOfPlayers);
                game.playGame(); //iterate through each of the players in the party and allows them to make a play. Performed inside a loop that ends when the game is over. Must accomodate deck ending
            }
            catch (NumberFormatException e){
                System.out.println("\t[!] That's not a number. Try again.");
            }
        }
        scan.close();
    }

    public void startGame(int howManyPlayers){
        cardDeck.printDeck();

        System.out.println("[!] Starting game with " + howManyPlayers + " players.");

        ArrayList<rummyCard> playerDeck = new ArrayList<rummyCard>();
        System.out.println("\n[!] Dealing to: Player ------");
        playerDeck = cardDeck.deal(7);
        player realPlayer = new player("Player", playerDeck, false);
        party.add(realPlayer);

        for (int i = 1; i < howManyPlayers; i++){ //create x number of player objects and assign them a name and some cards FOR THE AI
            String playerName = "AI-OPPONENT-" + i;
            ArrayList<rummyCard> theirDeck = new ArrayList<rummyCard>();
            System.out.println("\n[!] Dealing to: " + playerName + " ------");
            theirDeck = cardDeck.deal(7);
            player currPlayer = new player(playerName, theirDeck, true);
            party.add(currPlayer);     
        }

    }


    public void playGame(){
        rummyCard currentDiscard = cardDeck.discardFromDeck(); //The first card to be flipped over
        cardDeck.printDeck();
        cardDeck.printDiscard();
        boolean gameOver = false;

        while(gameOver == false){ //This is the game loop. Will continue looping until the game ends.
            for (player currPlayer : party) {
                if (currPlayer.isAI == true){ //ACTIONS PERFORMED PER TURN BY THE AI
                    System.out.println("\n\t[!] do AI things");
                    //For now, until an AI is made, each AI turn will consist of withdrawing from deck and discarding a random card from their deck
                    currPlayer.acceptCard(cardDeck.withdrawFromDeck());
                    Random rand = new Random();
                    currentDiscard = currPlayer.discardCard(rand.nextInt(7)); //Randomly discard one of the cards in their hand. Index is 8 elements (7 dealt + 1 withdrawn)
                    cardDeck.acceptDiscardedCard(currentDiscard);

                } else{ //ACTIONS PERFORMED PER TURN BY THE HUMAN
                    System.out.println("\n\t[!] do human things");
                    System.out.println("\n[!] The card showing on discard pile is: " + currentDiscard);
                    currPlayer.acceptCard(cardDeck.withdrawFromDiscarded());
                    Random rand = new Random();
                    currentDiscard = currPlayer.discardCard(rand.nextInt(7)); //Randomly discard one of the cards in their hand. Index is 8 elements (7 dealt + 1 withdrawn)
                    cardDeck.acceptDiscardedCard(currentDiscard);
                }
                
            cardDeck.printDeck();
            cardDeck.printDiscard();
            }



        }
    }
}



