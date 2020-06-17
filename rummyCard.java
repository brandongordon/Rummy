//import java.util.ArrayList;

public class rummyCard{
    private String suit;
    private String rank;
    public int value;
    public int order;

    public rummyCard(String isuit, String irank){ //Construct a new card based on the incoming values
        suit = isuit;
        rank = irank;
        calculateValue(irank);
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

    private void calculateValue(String irank){ //Give the cards their associated value
        if(irank == "Ace"){
            value = 1;
            order = 1;
        }
        if(irank == "2"){
            value = 2;
            order = 2;
        }
        if(irank == "3"){
            value = 3;
            order = 3;
        }
        if(irank == "4"){
            value = 4;
            order = 4;
        }
        if(irank == "5"){
            value = 5;
            order = 5;
        }
        if(irank == "6"){
            value = 6;
            order = 6;
        }
        if(irank == "7"){
            value = 7;
            order = 7;
        }
        if(irank == "8"){
            value = 8;
            order = 8;
        }
        if(irank == "9"){
            value = 9;
            order = 9;
        }
        if(irank == "10"){
            value = 10;
            order = 10;
        }
        if(irank == "Jack"){
            value = 10;
            order = 11;
        }
        if(irank == "Queen"){
            value = 10;
            order = 12;
        }
        if(irank == "King"){
            value = 10;
            order = 13;
        }

        //If Rank = Joker then set a unique value per game
        if (irank == "Joker"){
            value = 100; //In this instance, Joker should never be used so im setting the value to be something crazy.
        }
    }
}
