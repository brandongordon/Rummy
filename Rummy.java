import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Rummy {
    deck cardDeck = new deck(0); //Generate a new deck of cards with 0 jokers - a new deck is automatically shuffled
    ArrayList<player> party = new ArrayList<player>(); //Get an arrayList going to store the players in, allows us to rotate between them etc
    
    public static boolean debug = true;

    public int getInt(Scanner scan, String question, String incorrect, int minRange, int maxRange){
    String rawInput;
    int cleanInput = 0;
    boolean retry = true;
    while (retry){
        try{
            System.out.print(question);
            rawInput = scan.next();
            cleanInput = Integer.parseInt(rawInput);
            if (cleanInput >= minRange && cleanInput <= maxRange) {retry = false;} else {System.out.println("\t[!] That's not a valid option. Try again.");}
        }
        catch (NumberFormatException e){
            System.out.println(incorrect);
        }
    }
    return cleanInput;
}

    public static void main(String[] args) {
        Rummy game = new Rummy();
        System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-(*)-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		System.out.println("\t\t\tWelcome to Rummy!");
		System.out.println("\t\t\tBy Brandon Gordon");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-(*)-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        
        Scanner mainScan = new Scanner(System.in);

        System.out.print("\tEnable debug? [T or F] > "); //Set the debug variable

        String debugChoice = mainScan.next().toUpperCase();
        if (debugChoice.contains("F")){
            debug = false;
            System.out.println ("\n[!] Debug statements disabled.");
        }
        else{
            System.out.println ("\n[!] Debug statements enabled.");
        }

        int numOfPlayers = game.getInt(mainScan, "\n\tHow many AI opponents? > ","\t[!] That's not a number. Try again.", 1, 9);
        game.startGame((numOfPlayers+1));
        game.playGame(); //iterate through each of the players in the party and allows them to make a play. Performed inside a loop that ends when the game is over. Must accomodate deck ending
        game.evaluateWinners(); //When the conditions are met such that playGame is finished, roll on to evaluate winners
        mainScan.close();
    }

    public void startGame(int howManyPlayers){
        if (debug) {cardDeck.printDeck();};

        System.out.println("\n[!] Starting game with " + (howManyPlayers) + " players.");

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
        if (debug) {cardDeck.printDeck();};
        if (debug) {cardDeck.printDiscard();};
        boolean gameOver = false;

        Scanner playScan = new Scanner(System.in);
        while(gameOver == false){ //This is the game loop. Will continue looping until the game ends.
            for (player currPlayer : party) {
                if (currPlayer.isAI == true){ //ACTIONS PERFORMED PER TURN BY THE AI
                    if (debug) {System.out.println("\n\t[!] do AI things");};
                    //For now, until an AI is made, each AI turn will consist of withdrawing from deck and discarding a random card from their hand
                    currPlayer.acceptCard(cardDeck.withdrawFromDeck());
                    Random rand = new Random();
                    currentDiscard = currPlayer.discardCard(rand.nextInt(7)); //Randomly discard one of the cards in their hand. Index is 8 elements (7 dealt + 1 withdrawn)
                    cardDeck.acceptDiscardedCard(currentDiscard);

                } else{ //ACTIONS PERFORMED PER TURN BY THE HUMAN
                    Boolean continueTurn = true;
                    while (continueTurn){                    
                        System.out.println("\n\t[-=-=-=-=-=-=(HUMAN, IT IS YOUR TURN)=-=-=-=-=-=-]");
                        System.out.println("\tYour hand currently consists of: " + currPlayer.hand);
                        System.out.println("\tThe card showing on the discard pile is: " + currentDiscard);
                        int menuChoice = getInt(playScan, "\n\tDo you wish to:" +
                        "\n\t\t[1] Re-order your HAND?" +
                        "\n\t\t[2] Withdraw from the DECK pile?" +
                        "\n\t\t[3] Withdraw the " + currentDiscard + " from the DISCARD pile?" +
                        "\n\t\t[4] Declare your hand as WINNER! \n\t> ", "\t[!] That's not a valid option. Try again.", 1, 4);
                        if (menuChoice == 1){ //Reorder your HAND
                            currPlayer.orderHand();
                        }
                        else if (menuChoice == 2){ //Withdraw from the DECK pile
                            currPlayer.acceptCard(cardDeck.withdrawFromDeck());
                            continueTurn = false;
                            System.out.println("\n\tNow you must DISCARD a card. Which card will it be?");
                            int handCounter =0;
                            for (rummyCard currCard : currPlayer.hand){
                                handCounter ++;
                                System.out.print("\n\t[" + handCounter + "] " + currCard);
                            }
                            int discardChoice = (getInt(playScan, "\n\t> ", "\t[!] That's not a valid option. Try again.", 1, 8)-1); //Negate one for index of hand array starting at 0
                            currentDiscard = currPlayer.discardCard(discardChoice); //Discard the card of that index value in their hand. Index is 8 elements (7 dealt + 1 withdrawn)
                            cardDeck.acceptDiscardedCard(currentDiscard);
                        }
                        else if (menuChoice == 3){ //Withdraw from the DISCARD pile
                            currPlayer.acceptCard(cardDeck.withdrawFromDiscarded());
                            continueTurn = false;
                            System.out.println("\n\tNow you must DISCARD a card. Which card will it be?");
                            int handCounter =0;
                            for (rummyCard currCard : currPlayer.hand){
                                handCounter ++;
                                System.out.print("\n\t[" + handCounter + "] " + currCard);
                            }
                            int discardChoice = (getInt(playScan, "\n\t> ", "\t[!] That's not a valid option. Try again.", 1, 8)-1); //Negate one for index of hand array starting at 0
                            currentDiscard = currPlayer.discardCard(discardChoice); //Discard the card of that index value in their hand. Index is 8 elements (7 dealt + 1 withdrawn)
                            cardDeck.acceptDiscardedCard(currentDiscard);
                        }
                        else if (menuChoice == 4){ //Declare your hand as WINNER
                            return;
                        }
                        else {
                            System.out.println("\t[!] Something went wrong.");
                        }
                        

                }
                    System.out.println("\n\t[-=-=-=-=-=-=(YOUR TURN IS NOW OVER)=-=-=-=-=-=-]");
                }
                
            if (debug) {cardDeck.printDeck();};
            if (debug) {cardDeck.printDiscard();};
            }



        }
        playScan.close();
    }

    public void evaluateWinners (){

        for (player currPlayer : party) {

        }

    }
}



