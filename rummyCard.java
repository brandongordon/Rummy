//import java.util.ArrayList;

public class rummyCard{
    private String suit;
    private String rank;
    private int value;

    public rummyCard(String isuit, String irank){ //Construct a new card based on the incoming values
        suit = isuit;
        rank = irank;
        value = calculateValue(irank);
    }
    
    public String getSuit(){
        return suit;
    }

    public String getRank(){
        return rank;
    }

    public int getValue(){
        return value;
    }

    public String toString(){
        return rank + " of " + suit + " (" + value + ")";
    }

    private int calculateValue(String irank){ //Give the cards their associated value
        if(irank == "Ace"){
            value = 1;
        }
        if(irank == "1"){
            value = 1;
        }
        if(irank == "2"){
            value = 2;
        }
        if(irank == "3"){
            value = 3;
        }
        if(irank == "4"){
            value = 4;
        }
        if(irank == "5"){
            value = 5;
        }
        if(irank == "6"){
            value = 6;
        }
        if(irank == "7"){
            value = 7;
        }
        if(irank == "8"){
            value = 8;
        }
        if(irank == "9"){
            value = 9;
        }
        if(irank == "10"){
            value = 10;
        }
        if(irank == "Jack"){
            value = 10;
        }
        if(irank == "Queen"){
            value = 10;
        }
        if(irank == "King"){
            value = 10;
        }

        //If Rank = Joker then set a unique value per game
        if (irank == "Joker"){
            value = 100; //In this instance, Joker should never be used so im setting the value to be something crazy.
        }
        return value;
    }
}
