import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class values
{
    int money;
    int winningsAmount;
    int betAmount;
    int hitCounter = 1;

    int playerHandValue = 0;
    int computerHandValue = 0;

    String gameLog = "";
    ArrayList<Integer> allCardsDrawn = new ArrayList<Integer>();
    ArrayList<Integer> playerHand = new ArrayList<Integer>();
    ArrayList<Integer> computerHand = new ArrayList<Integer>();
    public values(int m){
        this.money = m;
    }

    /**All Get methods**/
    public int getMoney(){ return money; } //returns how much money the player currently has 

    public int getBetAmount(){ return winningsAmount;} //returns that value that the player would win. 

    public String getGameLogText(){ return this.gameLog;}

    public int getHitCounter(){ return this.hitCounter;} //retuns the amount of times the "hit button has been clicked

    public int getHandValue(int playerSelect){ return getHandValueCalc(playerHand, computerHand, playerSelect);}

    public int getBetAmountTester(){return this.betAmount;}

    /**All set Methods**/

    public void setMoney(int m){ this.money = money - m; this.betAmount = m;} //sets the money after the player hits "bet" to be money - betAmount

    public void setBetAmount(int b){ this.winningsAmount = b;} //stores the amount that the player will win

    public void setMoneyWin(){this.money += this.winningsAmount;} //sets the players money to what they have, plus the winningAmount

    public void setMoneyTie(){this.money += this.betAmount;}

    public void setGameLogText(String s){this.gameLog += "\n" + s;} //adding onto the gameLogText box. 

    public void setHitCounterNew(int c){ hitCounter = hitCounter + c;} //increases how many times the player has clicked the "hit" button

    public void setPlayerCardInHand(int cardValue){
        this.allCardsDrawn.add(cardValue); //adding the new card value to the array of all cards drawn
        this.playerHand.add(cardValue); //adding the new card value to the array of the players hand
        Collections.sort(allCardsDrawn); //sorts the array so that Aces are at the end. Used to decide if Ace = 1 or 11
        Collections.sort(playerHand);
    }

    public void setCompCardInHand(int cardValue){
        this.allCardsDrawn.add(cardValue);
        this.computerHand.add(cardValue); 
        Collections.sort(allCardsDrawn);
        Collections.sort(computerHand);
    }

    /**Reset Values method**/
    public void resetValues(){ 
        this.hitCounter = 1;
        this.gameLog = "";
        allCardsDrawn.clear(); playerHand.clear(); computerHand.clear(); //clears the ArrayLists at the end of each round
        this.winningsAmount = 0;
    }

    /**All Methods for computation and comparisons**/
    public boolean checkCard(int r){
        boolean returnValue = false;
        if(allCardsDrawn.size() == 0){ returnValue = false;}
        else{
            for(int i = 0; i < allCardsDrawn.size(); i++){
                if(r == allCardsDrawn.get(i)){returnValue = true; break;}
            }
        }
        return returnValue;
    }

    public int getHandValueCalc(ArrayList<Integer> playerHand, ArrayList<Integer> computerHand, int playerSelect){
        System.out.println("\nPlayers hand: " + playerHand + "\nComputers hand: " + computerHand);
        ArrayList<Integer> handToCheck = selectPlayerHand(playerHand, computerHand, playerSelect);
        int currentHandValue = 0;

        for(int i = 0; i < handToCheck.size(); i++){
            playerHandValue = currentHandValue;
            computerHandValue = currentHandValue;
            switch(handToCheck.get(i)){
                case 0: case 1: case 2: case 3: 
                currentHandValue += 2; break;
                case 4: case 5: case 6: case 7: 
                currentHandValue += 3; break;
                case 8: case 9: case 10: case 11:
                currentHandValue += 4; break;
                case 12: case 13: case 14: case 15: 
                currentHandValue += 5; break;
                case 16: case 17: case 18: case 19:
                currentHandValue += 6; break;
                case 20: case 21: case 22: case 23: 
                currentHandValue += 7; break;
                case 24: case 25: case 26: case 27: 
                currentHandValue += 8; break;
                case 28: case 29: case 30: case 31: 
                currentHandValue += 9; break;
                case 32: case 33: case 34: case 35: 
                currentHandValue += 10; break;
                case 36: case 37: case 38: case 39: 
                currentHandValue += 10; break;
                case 40: case 41: case 42: case 43:
                currentHandValue += 10; break;
                case 44: case 45: case 46: case 47:
                currentHandValue += 10; break;
                case 48: case 49: case 50: case 51: 
                if(playerSelect == 0){
                    if(playerHandValue < 11){currentHandValue += 11; }
                    if(playerHandValue >= 11){ currentHandValue += 1; } break;
                }
                if(playerSelect == 1){
                    if(computerHandValue < 11){currentHandValue += 11; }
                    if(computerHandValue >= 11){ currentHandValue += 1; } break;
                }
            }

        }
        return currentHandValue;
    }

    public static ArrayList<Integer> selectPlayerHand(ArrayList<Integer> playerHand, ArrayList<Integer> computerHand, int playerSelect){
        ArrayList<Integer> chosenHand = new ArrayList<Integer>();
        if(playerSelect == 0){ chosenHand = playerHand;}
        if(playerSelect == 1){ chosenHand = computerHand;}
        return chosenHand;
    } //Method used to decide which players hand to compare in the switch and for loop (in getHandValueCalc())
}

